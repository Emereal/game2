package labubu;

public class Connor extends NPC{
	
	private boolean met;
	
	public Connor() {
		super("connor");
		met = false;
	}
	
	@Override
	public void talk() {
		if (met) {
			
		} else {
			say("Hey! What's got you searching around bro?");
			String[] opt = {
					"Do you know where the flashlight is?",
					"Do you have any change on you?",
					"I want to play fortnite."
			};
			int ch = getResponse(opt);
			switch (ch) {
				case 1:
					say("\nNah man, I have no clue. The last time I saw it is when Dad\n tried to sneak a midnight snack, but Mom caught\n him in the act.");
					break;
				case 2:
					say("\nOh yeah, I stole 20 bucks off of Pops before he left. Let me\n just grab it real quick...");
					CozyAdventureGUI.addStory("\nConnor jabbed you in the stomach! You lost 15HP!");
					CozyAdventureGUI.Wait("short");
					CozyAdventureGUI.addStory("\nYou suddenly remembered you don't have HP, and now you feel\n slightly embarrassed.");
					break;
				case 3:
					say("\nI've been trying to get the pirate token for the past 4 days,\n I can't let you log on and steal my luck.");
					break;
				default:
					say("\nSpeak up, I can't hear you Netenyahu.");
					break;
			}
		}
	}

}
