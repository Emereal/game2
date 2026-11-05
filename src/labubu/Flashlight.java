package labubu;

public class Flashlight extends Item {
	
	private boolean foundkey;
	
	public Flashlight() {
		super("flashlight");
		foundkey = false;
	}

	@Override
	public void use() {
		if (Game.getCurrentRoom().getName().equals("Bedroom")) {
			if (foundkey)
				CozyAdventureGUI.addStoryDtl("You search far and wide but it seems there's no items\n that hide.");
			else
				CozyAdventureGUI.addStoryDtl("You turn on the flashlight and search the room for hidden\n treasure and rats. Instead, you find the office key under\n the bed!");
				Game.getCurrentRoom().addItem("officekey", new OfficeKey());
				foundkey = true;
		} else {
			CozyAdventureGUI.addStoryDtl("You turn on the flashlight and realize that the much bigger\n flashlights in the ceiling are doing its job much better,\n so you turn it off.");
		}
	}
}
