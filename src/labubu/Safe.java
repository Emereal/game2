package labubu;

public class Safe extends Item {
	
	private boolean opensesame;
	private double change;
	private boolean open;
	
	public Safe() {
		super("safe");
		opensesame = false;
		change = 0.0;
		open = false;
	}
	
	@Override
	public void checkChange() {
		if (open) {
			CozyAdventureGUI.addStoryDtl("\nAfter opening the safe, you take the time out of your day\n and count that you have $"+String.format("%.2f", change)+" in change.");
		} else {
			CozyAdventureGUI.addStoryDtl("\nThe safe isn't open, idiot.");
		}
	}
	
	@Override
	public void open() {
		if (opensesame) {
			CozyAdventureGUI.addStoryDtl("\nSince you've already opened the safe, you decide that you\n could use a piggy bank and put one of your\n pennies in there now that you know the password. You'll grab the change\n later.");
			change += 0.01;
			open = true;
		} else {
			CozyAdventureGUI.addStoryDtl("\nYou open the safe, and a notebook falls onto the floor along\n many papers! Maybe it will prove useful?");
			opensesame = true;
			open = true;
			Game.getCurrentRoom().addItem("notebook", new Notebook());
		}
	}
	
	@Override
	public void close() {
		if (opensesame) {
			CozyAdventureGUI.addStoryDtl("\nYou close the safe once more after trusting it to hold all of\n your very valuable change.\n Goodbye Piggy Bank!");
			open = false;
		} else {
			CozyAdventureGUI.addStoryDtl("\nYou close the safe after putting all the papers back inside.\n Goodbye Safe!");
			open = false;
		}
	}
	
	

}
