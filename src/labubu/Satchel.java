package labubu;

public class Satchel extends Item{
	
	private static double change;
	
	public Satchel() {
		super("satchel");
		change = 0.0;
	}
	
	@Override
	public void checkChange() {
		CozyAdventureGUI.addStoryDtl("After digging out your satchel, you find that you have about $"+String.format("%.2f", change)+" in change.");
	}
	
	@Override
	public void takeChange(double ch) {
		CozyAdventureGUI.addStoryDtl("You've recieved $"+String.format("%.2f", ch)+" in change!");
		change += ch;
	}

}
