package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Comportement cherchant à suivre Pac-Man en évitant les murs
 * @author Fabien Morival
 */
public class ShortestPathBehaviour implements Behaviour {
	
	private class Node implements Comparable<Node> {
		
		int cost, h;
		Coord2D coords;
		
		/**
		 * Noeud précédent celui-ci dans le déplacement
		 */
		Node pre = null;
		
		Node (Coord2D coords, int cout, int h) {
			
			this.coords = coords;
			this.cost = cout;
			this.h = h;
		}

		@Override
		public int compareTo(Node n) {

			return this.h > n.h ? 1 : (this.h == n.h ? 0 : -1);
		}
	}

	@Override
	public Way next(Actor a) {
		
		Grid g = a.getGrid();
		Node pacman = new Node(g.findPacMan().getPosition(), 0, 0);
		Node ghost = new Node(a.getPosition(), 0, 0);
		
		Node opt = ghost;

		// Liste fermée des noeuds traversés
		Queue<Node> cl = new LinkedList<Node>();
		// Liste ouverte des noeuds à explorer
		Queue<Node> ol = new PriorityQueue<Node>();
		ol.add(ghost);

		// Tant qu'il reste des noeuds a explorer
		int i = 0;
		while (!ol.isEmpty() && i++ < 200) {

			//On recupere le meilleur noeud a explorer
			Node u = ol.poll();
			
			int distance = u.coords.distanceManhattan(pacman.coords);
			// si le noeud est meilleur, on met a jour le noeud optimal
			if (distance < opt.coords.distanceManhattan(pacman.coords)) {
				// si le noeud est adjacent a Pac-Man, on y va
				if (distance <= 1)
					return goToNode(u);
				opt = u;
			}
			// on regarde les noeuds voisins
			for (Node v : neighbors(u, g)) {
				// s'ils n'ont pas déjà été exploré ou s'ils sont plus optimaux que la fois précédente
				if (getNodeWithCoords(v.coords, cl) != null && getNodeWithCoords(v.coords, cl).cost < u.cost ||
						getNodeWithCoords(v.coords, ol) != null && getNodeWithCoords(v.coords, ol).cost < u.cost);
				else {
					// on les ajoute dans la liste ouverte
					v.cost = u.cost+1;
					v.h = v.cost + v.coords.distanceManhattan(pacman.coords);
					v.pre = u;
					ol.add(v);
				}
			}
			// si aucun chemin n'a été trouvé, on se déplace vers le noeud le plus proche
			cl.add(u);
		}
		
		return goToNode(opt);
	}
	
	private Way wayTo(Node n1, Node n2) {
		
		if (n1 == null || n2 == null) return null;
		if (n1.coords.x > n2.coords.x) return Way.W;
		if (n1.coords.x < n2.coords.x) return Way.E;
		if (n1.coords.y > n2.coords.y) return Way.N;
		if (n1.coords.y < n2.coords.y) return Way.S;
		return null;
	}
	
	private Way goToNode (Node u) {
		
		Node v = null;
		while (u.pre != null) {
			v = u;
			u = u.pre;
		}
		return wayTo(u, v);
	}
	
	private List<Node> neighbors (Node n, Grid g) {
		
		List<Node> l = new ArrayList<Node>();
		for (Way w : Way.cardinal())
			if (g.getTile(n.coords.go(w)).isTransparent()) l.add(new Node(n.coords.go(w), 0, 0));
		return l;
	}
	
	/**
	 * Recupere les noeuds a des coordonnees specifiques
	 * @param coords coordonnees auxquelles chercher les noeuds
	 * @param q collection de noeuds dans laquelle effectuer la recherche
	 * @return noeud a ces coordonnees, null si non existant
	 */
	private Node getNodeWithCoords (Coord2D coords, Collection<Node> q) {
	
		for (Node n : q)
			if (n.coords.equals(coords))
				return n;
		return null;
	}
}
