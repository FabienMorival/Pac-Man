package model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Classe responsable de la gestion du temps dans l'application.
 * Met à jour les {@link TimeBehaviour} à chaque frame.
 * 
 * @author Fabien
 */
public class Time {

	/**
	 * Frames par secondes visées par le gestionnaire
	 */
	public static final int TARGET_FPS = 60;

	/**
	 * Précision du temps d'attente moyen entre deux frames
	 */
	private static final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;   

	/**
	 * Comportements à mettre à jour a chaque frame
	 */
	private static List<TimeBehaviour> behaviours = new ArrayList<>();
	
	/**
	 * Indique si le thread gérant les mises à jour est actif
	 */
	private static boolean running = false;
	
	/**
	 * Nanosecondes écoulées dans la seconde actuelle
	 */
	private static int nanoTime = 0;

	/**
	 * Nombre de frames dans la seconde actuelle
	 */
	private static int currentFps = 0;
	
	/**
	 * Frames par seconde de la dernière seconde écoulée
	 */
	private static int lastFps = 0;
	
	/**
	 * Secondes écoulées depuis la dernière mise à jour
	 */
	private static double deltaTime = 0;
	
	/**
	 * Ratio du temps de l'application par rapport au temps réel.
	 * Le framerate reste constant.<br>
	 * <b>Exemple :</b> timeScale = 2 signifie que 2 secondes se seront écoulées
	 * dans l'application en une seconde réelle.<br>
	 */
	private static double timeScale = 1;
	
	/**
	 * Temps en secondes écoulées depuis le démarrage du gestionnaire.
	 */
	private static double timeSinceStart = 0;
	
	/**
	 * Démarre le gestionnaire de mises à jour. S'il n'est pas arrêté, il
	 * continuera après fermeture de l'application.
	 */
	public static void start () {
		
		if (!isRunning())
			new Thread(() -> loop()).start();;
	}
	
	/**
	 * Met fin à la gestion des mises à jour.
	 */
	public static void stop () {
		
		running = false;
		timeSinceStart = 0;
	}
	
	/**
	 * Effectue des mises à jour régulières des {@link TimeBehaviour} jusqu'à
	 * l'arrêt du gestionnaire.
	 */
	private static void loop() {
		
		running = true;
		long lastLoopTime = System.nanoTime();

		// boucle jusqu'à l'arrêt du gestionnaire
		while (running)
		{
			// détermine le temps écoulé depuis la dernière frame
			long now = System.nanoTime();
			long updateLength = (long)((now - lastLoopTime)*timeScale);
			lastLoopTime = now;
			deltaTime = updateLength / 10e8;

			// met à jour les compteurs par frame
			nanoTime += updateLength;
			currentFps++;
			timeSinceStart += deltaTime;
	      
			// met à jour les compteurs par seconde
			if (nanoTime >= 1000000000)
			{
				lastFps = currentFps;
				nanoTime = 0;
				currentFps = 0;
			}
	      
			updateBehaviours();
	      
			// en fonction du temps écoulé depuis la dernière frame,
			// on attend de manière à garder une durée constante à chaque boucle.
			try {
				Thread.sleep( (lastLoopTime-System.nanoTime() + OPTIMAL_TIME)/(long)(1000000) );
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Effectue une action spécifiée sur tous les comportements liés au gestionnaire.
	 * @param action action à effectuer sur chaque comportement
	 */
	private static void affectBehaviours(Consumer<TimeBehaviour> action) {
		
		for (TimeBehaviour behaviour : new ArrayList<TimeBehaviour>(behaviours))
			action.accept(behaviour);
	}
	
	/**
	 * Met à jour tous les comportements du gestionnaire.
	 */
	private static void updateBehaviours() {
		
		affectBehaviours(behaviour -> behaviour.update());
	}
	
	/**
	 * Ajoute un comportement au gestionnaire.
	 * La fonction update() sera appelée à chaque frame.
	 * @param behaviour comportement à ajouter
	 */
	public static void capture (TimeBehaviour behaviour) {
		
		if (!behaviours.contains(behaviour))
			behaviours.add(behaviour);
	}
	
	/**
	 * Retire un comportement du gestionnaire.
	 * Il ne sera plus notifié au cours du temps.
	 * @param behaviour comportement à retirer
	 */
	public static void free (TimeBehaviour behaviour) {
		
		if (behaviours.contains(behaviour))
			behaviours.remove(behaviour);
	}
	
	/**
	 * Frames par seconde de la dernière seconde écoulée
	 */
	public static int getFps () {
		return lastFps;
	}
	
	/**
	 * Secondes écoulées depuis la dernière mise à jour
	 */
	public static double getDeltaTime () {
		return deltaTime;
	}
	
	/**
	 * Change le ratio du temps de l'application par rapport au temps réel.
	 * Le framerate reste constant.<br>
	 * <b>Exemple :</b> timeScale = 2 signifie que 2 secondes se seront écoulées
	 * dans l'application en une seconde réelle.<br>
	 * @param timeScale nouveau ratio
	 */
	public static void setTimeScale (double timeScale) {
		Time.timeScale = timeScale;
	}
	
	/**
	 * Donne le ratio du temps de l'application par rapport au temps réel.
	 */
	public static double getTimeScale () {
		return timeScale;
	}
	
	/**
	 * Temps en secondes écoulées depuis le démarrage du gestionnaire.
	 */
	public static double getTimeSinceStart () {
		return timeSinceStart;
	}
	
	/**
	 * Indique si le thread gérant les mises à jour est actif.
	 */
	public static boolean isRunning () {
		return running;
	}
}
