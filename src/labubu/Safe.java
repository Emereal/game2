package labubu;

public class Safe extends Item {
	
	private boolean opensesame;
	private boolean closesesame;
	private double change;
	private boolean open;
	private static boolean locked;
	
	public Safe() {
		super("safe");
		opensesame = false;
		closesesame = false;
		change = 0.0;
		open = false;
		locked = true;
	}
	
	public static void isLocked(boolean bool) {
		locked = bool;
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
		if (locked) {
			CozyAdventureGUI.addStoryDtl("\nThe safe is locked with a 4-digit passcode! You might need to\n find a password in order to open it.");
		} else if (opensesame&&open==false) {
			CozyAdventureGUI.addStoryDtl("\nYou open the safe, and since you've already opened it, you decide that you\n could use a piggy bank and put one of your\n pennies in there now that you know the password. You'll grab the change\n later.");
			change += 0.01;
			open = true;
		} else if (open==false) {
			CozyAdventureGUI.addStoryDtl("\nYou open the safe, and a notebook falls onto the floor along\n many papers! Maybe it will prove useful?");
			opensesame = true;
			open = true;
			Game.getCurrentRoom().addItem("notebook", new Notebook());
		} else {
			CozyAdventureGUI.addStoryDtl("The safe is already open, dummy.");
		}
	}
	
	@Override
	public void close() {
		if (closesesame&&open) {
			CozyAdventureGUI.addStoryDtl("\nYou close the safe once more after trusting it to hold all of\n your very valuable change.\n Goodbye Piggy Bank!");
			open = false;
		} else if (open) {
			CozyAdventureGUI.addStoryDtl("\nYou close the safe after putting all the papers back inside.\n Goodbye Safe!");
			closesesame = true;
			open = false;
		} else {
			CozyAdventureGUI.addStoryDtl("The safe is already closed, idiot.");
		}
	}
	
	

}
