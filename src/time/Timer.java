package time;

public class Timer implements TimeBehaviour {
	
	private boolean running = false;
	
	private float initialTime, remainingTime;
	
	private Runnable event;
	
	public Timer (float seconds, Runnable event) {
		
		this.initialTime = seconds;
		this.remainingTime = seconds;
		this.event = event;
	}
	
	public void start() {
		
		this.remainingTime = this.initialTime;
		this.running = true;
		this.resume();
	}
	
	public void resume() {

		if (this.running)
			Time.capture(this);
	}
	
	public void abort() {
		
		this.stop();
		this.running = false;
	}
	
	public void stop() {

		if (this.running)
			Time.free(this);
	}

	@Override
	public void update() {
		
		if (!this.running)
			return;
		this.remainingTime -= Time.getDeltaTime();
		if (this.remainingTime <= 0) {
			this.event.run();
			this.abort();
		}
	}

}
