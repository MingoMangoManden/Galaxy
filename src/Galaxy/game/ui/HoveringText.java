package Galaxy.game.ui;

public class HoveringText {
	
	String text;
	int counter;
	int maxCounter;
	boolean displayed;
	
	public HoveringText(String text, int maxCounter) {
		this.text = text;
		this.counter = maxCounter;
		this.maxCounter = counter;
		this.displayed = true;
	}
	
	public void update() {
		counter--;
		if (counter == 0) {
			displayed = !displayed;
			counter = (displayed) ? maxCounter : (int) (maxCounter*0.5);
		}
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public int getMaxCounter() {
		return maxCounter;
	}

	public void setMaxCounter(int maxCounter) {
		this.maxCounter = maxCounter;
	}

	public boolean isDisplayed() {
		return displayed;
	}

	public void setDisplayed(boolean displayed) {
		this.displayed = displayed;
	}

}
