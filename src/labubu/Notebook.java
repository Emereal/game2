package labubu;

public class Notebook extends Item {
	
	private boolean readnigga;
	
	public Notebook() {
		super("notebook");
		readnigga = false;
	}
	
	@Override
	public void read() {
		if (Game.inventory.containsKey("notebook")) {
			if (readnigga) {
				CozyAdventureGUI.addStoryDtl("\nYou read the notebook again, realizing that you don't need it because you already\n have the password. Instead, you draw a Shrek in the\n corner and close the book.");
			} else {
				CozyAdventureGUI.addStoryDtl("\nYou read the notebook, and find the password to your dad's computer! He likely\n had saved this for an emergency, but you consider taking the car important\n enough. You write down the Computer password and store it in your inventory,\n putting the Notebook back just in case.");
				readnigga = true;
				Game.inventory.put("password", new Item("password"));
			}
		} else {
			CozyAdventureGUI.addStoryDtl("\nYou don't have a notebook, so instead you cup your hands together and pretend\n to read your hands before remembering you have things to do.");
		}
	}

}
