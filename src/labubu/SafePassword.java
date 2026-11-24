package labubu;

public class SafePassword extends Item {
	
	private boolean used;
	
	public SafePassword() {
		super("Safe Password");
		used = false;
	}
	
	@Override
	public void use() {
		if (Game.getCurrentRoom().getName().equals("Attic")&&used==false) {
			CozyAdventureGUI.addStory("\nYou use the safe password to unlock the safe, and you hear a\n beep! The safe is now unlocked.");
			Safe.isLocked(false);
			used = true;
		} else if (used==true){
			CozyAdventureGUI.addStory("\nYou realize you've already unlocked the safe, and you put the\n password away.");
		} else {
			CozyAdventureGUI.addStory("\nYou cannot use the Safe Password here!");
		}
			
	}

}
