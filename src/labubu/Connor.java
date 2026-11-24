package labubu;

import java.util.Random;

public class Connor extends NPC {
	
	private boolean advance;
	private boolean first2;
	private boolean first3;
	private boolean task1;
	private boolean task2;
	private boolean moneygiven;
	private boolean passwordgiven;
	private int convo;
	
	public Connor() {
		super("connor");
		advance = false;
		first2 = true;
		first3 = true;
		task1 = false;
		task2 = false;
		moneygiven = false;
		passwordgiven = false;
		convo = 0;
	}
	
	@Override
	public void give(Item tem) {
		if (tem.getName().equals("Takis")) {
			if (advance) {
				if (convo==1) {
					if (Game.inventory.containsValue(tem)) { 
						say("\nOh nice, thanks for the grub! I needed this... talk to me\n again once you got something to hold some cash\n in. Did you need anything else?");
						Game.inventory.remove("takis", tem);
						task1 = true;
						advance = false;
					}
				}
			}
		} else {
			say("\nNah, I don't want your "+tem.getName()+".");		
		}
	}
	
	@Override
	public void talk() {
		if (advance) {
			if (convo==1) {
				if (!Game.inventory.containsKey("takis")) {
					say("\nYou must be talking to me because you got my Takis right?");
					String[] opt1 = {
							"No, I couldn't find them."
					};
					int ch = getResponse(opt1);
					switch (ch) {
						case 1:
							say("\nWell then, what are you waiting for? I need my snack!!\n Did you need anything else?");
							advance = false;
							break;
						default:
							say("\nWuzzat?");
							break;
					}
				} else {
					say("\nYou must be talking to me because you got my Takis right? Just\n give them here!");
				}
			} else if (convo==2) {
				say("\nI already told you no, what, do you want to try and open a chest\n or something? How about this, if you get the pirate\n token, I'll let you have this thing I found lying around. Deal?");
				String[] opt2 = {
						"I was born for this.",
						"No, I have to pee."
				};
				int ch = getResponse(opt2);
				switch (ch) {
					case 1:
						say("\nWell then, there's a chest right over there. Go open it!");
						boolean token = pirateTokenRoll();
						if (token) {
							CozyAdventureGUI.addStory("\nYou got the Pirate Token!!!\n");
							say("\nWHAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAT?!?!?!\n You got the token!!! HOW?!?! You know what, take this,\n you deserve it.");
							Game.inventory.put("garagekey", new Item("Garage Key"));
							CozyAdventureGUI.addStory("\nYou obtained a Garage Key!");
							
						} else {
							CozyAdventureGUI.addStory("\nYou got nothing...\n");
							say("\nWell, that was expected. Now shoo, I have a game to win.");
						}
						advance = false;
						break;
					case 2:
						say("\nWell, what are you doing then? Now shoo, I have a game to win.");
					default:
						say("\nWuzzat?");
						break;
				}
			}
		} else {
			say("Hey, What's got you searching around?");
			String[] opt = {
					"Do you know where the flashlight is?", // repeats the hint no matter what
					"Do you have the password to the safe?", // initiates a quest for extra change and safe password, changes after completion
					"I want to play fortnite." // allows you a 1 in 137,500 chance to get the fortnite token and complete the game early. Changes after completion
			};
			int ch = getResponse(opt);
			switch (ch) {
				case 1:
					say("\nNah man, I have no clue. The last time I saw it is when Dad\n tried to sneak a midnight snack, but Mom caught\n him in the act.");
					break;
				case 2:
					if (first2) {
						say("\nOh yeah, I stole 20 bucks and a piece of paper off of Pops\n before he left. Let me just grab it real quick...");
						CozyAdventureGUI.addStory("\nConnor jabbed you in the stomach! You lost 15HP!");
						CozyAdventureGUI.addStory("\nYou suddenly remembered you don't have HP, and now you feel\n slightly embarrassed.");
						say("\nHah! Totally got you!! Maybe if you get me some Takis I'll\n split some change with you and show you this paper.");
						first2 = false;
						advance = true;
						convo = 1;
					} else if (moneygiven) {
						say("\nThanks for the chips! Just let me know if you need anything else.");
					} else {
						if (task1&&Game.inventory.containsKey("satchel")) {
							if (!passwordgiven) {
								say("\nOh yeah, I almost forgot. Here's the paper AND some paper dude. Thanks\n for the chips.");
								Game.inventory.get("satchel").takeChange(3.0);
								Game.inventory.put("safepassword", new SafePassword());
							} else {
								say("\nOh yeah, I almost forgot. Here's the paper, dude. Thanks\n for the chips.");
								Game.inventory.get("satchel").takeChange(3.0);
							}
							moneygiven = true;
						} else if (task1) {
							if (passwordgiven)
								say("\nDoesn't look like you can put the money anywhere man...");
							else
								say("\nHere's the password, but it doesn't look like I can give you the\n money for now.");
							Game.inventory.put("safepassword", new SafePassword());
						} else {
							say("\nWell, only if you get my Takis. Otherwise, consider me a\n poor person that's never had money in his life.\n Now shoo.");
							convo = 1;
							advance = true;
						}
					}
					
					break;
				case 3:
					if (first3) {
						say("\nI've been trying to get the pirate token for the past 4 days,\n I can't let you log on and steal my luck.");
						advance = true;
						first3 = false;
						convo = 2;
					} else if (task2){
						say("Thanks for the token, but I'm busy buying out everything under\n the sun in the item shop. Maybe tomorrow, once\n I make my perfect outfit.");
					} else {
						say("\nWell I'M trying to play fortnite, can't you see? Buzz off.");
						advance = true;
						convo = 2;
					}
					break;
				default:
					say("\nSpeak up, I can't hear you Netenyahu.");
					advance = false;
					break;
			}
			}
		}
	
	
	public boolean pirateTokenRoll() {
		Random rand = new Random();
		double chance = rand.nextInt(137500);
		if (chance==1)
			return true;
		return false;
	}

}
