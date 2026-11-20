package labubu;

public class OfficeKey extends Item{
	
	private boolean usedKey;
	
	public OfficeKey() {
		super("Office Key");
		usedKey = false;
	}
	
	@Override
	public void use() {
		if (Game.getCurrentRoom().getName().equals("Living Room")) {
			if (usedKey) {
				CozyAdventureGUI.addStoryDtl("You already opened the office door!");
			} else {
				Game.getCurrentRoom().getExit(1).setLockStatus(false);
				usedKey = true;
				CozyAdventureGUI.addStoryDtl("Using the office key, you are able to jam the lock so badly that\nthe lock breaks, rendering the security of your father's office null and void. Perhaps\nyou should stop using keys from now on. The Office is now unlocked!");
			}
		} else
			CozyAdventureGUI.addStoryDtl("You don't see any way to use the office key…");
	}
}
