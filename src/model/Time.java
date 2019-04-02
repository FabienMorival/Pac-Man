package model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Classe responsable de la gestion du temps dans l'application.
 * Met � jour les {@link TimeBehaviour} � chaque frame.
 * 
 * @author Fabien
 */
public class Time {

	/**
	 * Frames par secondes vis�es par le gestionnaire
	 */
	public static final int TARGET_FPS = 60;

	/**
	 * Pr�cision du temps d'attente moyen entre deux frames
	 */
	private static final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;   

	/**
	 * Comportements � mettre � jour a chaque frame
	 */
	private static List<TimeBehaviour> behaviours = new ArrayList<>();
	
	/**
	 * Indique si le thread g�rant les mises � jour est actif
	 */
	private static boolean running = false;
	
	/**
	 * Nanosecondes �coul�es dans la seconde actuelle
	 */
	private static int nanoTime = 0;

	/**
	 * Nombre de frames dans la seconde actuelle
	 */
	private static int currentFps = 0;
	
	/**
	 * Frames par seconde de la derni�re seconde �coul�e
	 */
	private static int lastFps = 0;
	
	/**
	 * Secondes �coul�es depuis la derni�re mise � jour
	 */
	private static double deltaTime = 0;
	
	/**
	 * Ratio du temps de l'application par rapport au temps r�el.
	 * Le framerate reste constant.<br>
	 * <b>Exemple :</b> timeScale = 2 signifie que 2 secondes se seront �coul�es
	 * dans l'application en une seconde r�elle.<br>
	 */
	private static double timeScale = 1;
	
	/**
	 * Temps en secondes �coul�es depuis le d�marrage du gestionnaire.
	 */
	private static double timeSinceStart = 0;
	
	/**
	 * D�marre le gestionnaire de mises � jour. S'il n'est pas arr�t�, il
	 * continuera apr�s fermeture de l'application.
	 */
	public static void start () {
		
		if (!isRunning())
			new Thread(() -> loop()).start();;
	}
	
	/**
	 * Met fin � la gestion des mises � jour.
	 */
	public static void stop () {
		
		running = false;
		timeSinceStart = 0;
	}
	
	/**
	 * Effectue des mises � jour r�guli�res des {@link TimeBehaviour} jusqu'�
	 * l'arr�t du gestionnaire.
	 */
	private static void loop() {
		
		running = true;
		long lastLoopTime = System.nanoTime();

		// boucle jusqu'� l'arr�t du gestionnaire
		while (running)
		{
			// d�termine le temps �coul� depuis la derni�re frame
			long now = System.nanoTime();
			long updateLength = (long)((now - lastLoopTime)*timeScale);
			lastLoopTime = now;
			deltaTime = updateLength / 10e8;

			// met � jour les compteurs par frame
			nanoTime += updateLength;
			currentFps++;
			timeSinceStart += deltaTime;
	      
			// met � jour les compteurs par seconde
			if (nanoTime >= 1000000000)
			{
				lastFps = currentFps;
				nanoTime = 0;
				currentFps = 0;
			}
	      
			updateBehaviours();
	      
			// en fonction du temps �coul� depuis la derni�re frame,
			// on attend de mani�re � garder une dur�e constante � chaque boucle.
			try {
				Thread.sleep( (lastLoopTime-System.nanoTime() + OPTIMAL_TIME)/(long)(1000000) );
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Effectue une action sp�cifi�e sur tous les comportements li�s au gestionnaire.
	 * @param action action � effectuer sur chaque comportement
	 */
	private static void affectBehaviours(Consumer<TimeBehaviour> action) {
		
		for (TimeBehaviour behaviour : new ArrayList<TimeBehaviour>(behaviours))
			action.accept(behaviour);
	}
	
	/**
	 * Met � jour tous les comportements du gestionnaire.
	 */
	private static void updateBehaviours() {
		
		affectBehaviours(behaviour -> behaviour.update());
	}
	
	/**
	 * Ajoute un comportement au gestionnaire.
	 * La fonction update() sera appel�e � chaque frame.
	 * @param behaviour comportement � ajouter
	 */
	public static void capture (TimeBehaviour behaviour) {
		
		if (!behaviours.contains(behaviour))
			behaviours.add(behaviour);
	}
	
	/**
	 * Retire un comportement du gestionnaire.
	 * Il ne sera plus notifi� au cours du temps.
	 * @param behaviour comportement � retirer
	 */
	public static void free (TimeBehaviour behaviour) {
		
		if (behaviours.contains(behaviour))
			behaviours.remove(behaviour);
	}
	
	/**
	 * Frames par seconde de la derni�re seconde �coul�e
	 */
	public static int getFps () {
		return lastFps;
	}
	
	/**
	 * Secondes �coul�es depuis la derni�re mise � jour
	 */
	public static double getDeltaTime () {
		return deltaTime;
	}
	
	/**
	 * Change le ratio du temps de l'application par rapport au temps r�el.
	 * Le framerate reste constant.<br>
	 * <b>Exemple :</b> timeScale = 2 signifie que 2 secondes se seront �coul�es
	 * dans l'application en une seconde r�elle.<br>
	 * @param timeScale nouveau ratio
	 */
	public static void setTimeScale (double timeScale) {
		Time.timeScale = timeScale;
	}
	
	/**
	 * Donne le ratio du temps de l'application par rapport au temps r�el.
	 */
	public static double getTimeScale () {
		return timeScale;
	}
	
	/**
	 * Temps en secondes �coul�es depuis le d�marrage du gestionnaire.
	 */
	public static double getTimeSinceStart () {
		return timeSinceStart;
	}
	
	/**
	 * Indique si le thread g�rant les mises � jour est actif.
	 */
	public static boolean isRunning () {
		return running;
	}
}
