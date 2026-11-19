package labubu;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Game {
	
	public static HashMap<String, Room> rooms = new HashMap<>();
	public static Room room = World.buildWorld();
	public static HashMap<String, String> house = new HashMap<>();
	protected static HashMap<String, Item> inventory = new HashMap<>();
	
	private final static JTextArea storyText = new JTextArea();
    private final static JTextField commandField = new JTextField();
    private static final Color DARK_BROWN =
            new Color(72, 53, 42);
    private static final Color CREAM =
            new Color(250, 242, 222);
    private static final Color LIGHT_TAN =
            new Color(225, 207, 174);
    private static final Color BUTTON_COLOR =
            new Color(232, 216, 186);
	
	public static void print(Object obj) {
		System.out.println(obj);
	}
	
	public static void loadDescriptions() {
		try {
			Scanner fileScan = new Scanner(new File("room_desc.txt"));
			while (fileScan.hasNextLine()) {
				int rep = 0;
				String title = fileScan.nextLine();
				String dess = "";
				while (rep==0) {
					String next = fileScan.nextLine();
					if (next.equals("#"))
						rep += 1;
					else
						dess+= ("\n"+next);
				}
				house.put(title, dess);
//				else if (fileScan.nextLine().equals("#")) {
//					house.put(r, dess);
//					r = "";
//					dess.clear();
//				} else
//					dess.add(fileScan.nextLine());		
			}
			fileScan.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String loadDescription(String r) {
		try {
			Scanner fileScan = new Scanner(new File("room_desc.txt"));
			while (fileScan.hasNextLine()) {
				int rep = 0;
				String title = fileScan.nextLine();
				String dess = "";
				while (rep==0) {
					String next = fileScan.nextLine();
					if (next.equals("#"))
						rep += 1;
					else
						dess+= ("\n"+next);
				}
				if (title.equals(r));
					return dess;
//				else if (fileScan.nextLine().equals("#")) {
//					house.put(r, dess);
//					r = "";
//					dess.clear();
//				} else
//					dess.add(fileScan.nextLine());		
			}
			fileScan.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Room getCurrentRoom() {
		return room;
	}
	
	public static void saveGame() {
		try {
			File saveFile = new File("hsave0.sav"); // name the file yourself
			saveFile.createNewFile(); // creates a new file
			ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream(saveFile));
			Room currentRoom = room;
			outStream.writeObject(currentRoom);
			HashMap<String, Item> currenti = inventory;
			outStream.writeObject(currenti);
			outStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void loadGame() {
		try {
			File saveFile = new File("hsave0.sav");
			ObjectInputStream inStream = new ObjectInputStream(new FileInputStream(saveFile));
			room = (Room) inStream.readObject();
			inventory = (HashMap<String, Item>) inStream.readObject();
			inStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
//-------------------------------------------------------------------------------------------------
    public static JLabel makeHeading(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", Font.BOLD, 12));
        label.setForeground(DARK_BROWN);
        return label;
    }
    
    public static JButton makeButton(String text, String command) {
        JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.BOLD, 12));
        button.setBackground(BUTTON_COLOR);
        button.setForeground(DARK_BROWN);
        button.setFocusPainted(false);
        button.setCursor(
                Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        if (!command.isEmpty()) {
            button.addActionListener(
                    e -> CozyAdventureGUI.handleCommand(command));
        }

        return button;
    }
    
	   public static void submitCommand() {
	        String command = commandField.getText().trim();

	        if (!command.isEmpty()) {
	            commandField.setText("");
	            runGameGUI(command);
	        }
	    }
    
    public static void addStory(String text) {
        storyText.append(text + "\n");
        storyText.setCaretPosition(
                storyText.getDocument().getLength());
    }

    // Draws the repeating checkered background
    public static class CheckerboardPanel extends JPanel {
        private static final int TILE_SIZE = 24;

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D) g.create();

            for (int y = 0; y < getHeight(); y += TILE_SIZE) {
                for (int x = 0; x < getWidth(); x += TILE_SIZE) {
                    boolean alternate =
                            ((x / TILE_SIZE) + (y / TILE_SIZE)) % 2 == 0;

                    g2.setColor(alternate ? CREAM : LIGHT_TAN);
                    g2.fillRect(x, y, TILE_SIZE, TILE_SIZE);
                }
            }

            g2.dispose();
        }
    }

//-------------------------------------------------------------------------------------------------
	
	public static void move(String dir) {
		if (dir.equals("east")) {
			if (room.getExit(0) == null) {
				CozyAdventureGUI.addStoryDtl("You cannot move east!");
			} else if (room.getExit(0).getLockStatus()==true){
				CozyAdventureGUI.addStoryDtl("The "+room.getExit(0).getName()+" is locked! Try to look around!");
			} else {
				room = room.getExit(0);
				JLabel lbl = new JLabel(room.getName());
				CozyAdventureGUI.changeLocHeader(lbl);
				CozyAdventureGUI.addStoryDtl("\nYou moved east to the "+room.getName()+"\n");
			}
		} else if (dir.equals("west")) {
			if (room.getExit(1) == null) {
				CozyAdventureGUI.addStoryDtl("You cannot move west!");
			} else if (room.getExit(1).getLockStatus()==true){
				CozyAdventureGUI.addStoryDtl("The "+room.getExit(1).getName()+" is locked! Try to look around!");
			} else {
				room = room.getExit(1);
				JLabel lbl = new JLabel(room.getName());
				CozyAdventureGUI.changeLocHeader(lbl);
				CozyAdventureGUI.addStoryDtl("\nYou moved west to the "+room.getName());
			}
		} else if (dir.equals("north")) {
			if (room.getExit(2) == null) {
				CozyAdventureGUI.addStoryDtl("You cannot move north!");
			} else if (room.getExit(2).getLockStatus()==true){
				CozyAdventureGUI.addStoryDtl("The "+room.getExit(2).getName()+" is locked! Try to look around!");
			} else {
				room = room.getExit(2);
				CozyAdventureGUI.addStoryDtl("\nYou moved north to the "+room.getName()+"\n");
			}
		} else if (dir.equals("south")) {
			if (room.getExit(3) == null) {
				CozyAdventureGUI.addStoryDtl("You cannot move south!");
			} else if (room.getExit(3).getLockStatus()==true){
				CozyAdventureGUI.addStoryDtl("The "+room.getExit(3).getName()+" is locked! Try to look around!");
			} else {
				room = room.getExit(3);
				CozyAdventureGUI.addStoryDtl("\nYou moved south to the "+room.getName()+"\n");
			}
		} else if (dir.equals("up")) {
			if (room.getExit(4) == null) {
				CozyAdventureGUI.addStoryDtl("You cannot move up!");
			} else if (room.getExit(4).getLockStatus()==true){
				CozyAdventureGUI.addStoryDtl("The "+room.getExit(4).getName()+" is locked! Try to look around!");
			} else {
				room = room.getExit(4);
				CozyAdventureGUI.addStoryDtl("\nYou moved up to the "+room.getName()+"\n");
			}
		} else if (dir.equals("down")) {
			if (room.getExit(5) == null) {
				CozyAdventureGUI.addStoryDtl("You cannot move down!");
			} else if (room.getExit(5).getLockStatus()==true){
				CozyAdventureGUI.addStoryDtl("The "+room.getExit(5).getName()+" is locked! Try to look around!");
			} else {
				room = room.getExit(5);
				CozyAdventureGUI.addStoryDtl("\nYou moved down to the "+room.getName()+"\n");
			}
		}
	}
	
	public static void takeItem(String item) {
		if (room.getItem(item.toLowerCase())!=null) {
			if (room.isHeavy(item.toLowerCase())!=true) {
				CozyAdventureGUI.addStoryDtl("\nYou've gotten a(n) "+room.getItem(item)+"!!");
				inventory.put(item, room.getItem(item));
				room.removeItem(item);
			} else
				CozyAdventureGUI.addStoryDtl("You can't lift the "+room.getItem(item)+"!");
		} else {
			CozyAdventureGUI.addStoryDtl("There is no "+item+" to be taken...");
		}
	}
	
	
	
	    // Basic example game logic
	    public static void runGameGUI(String rawCommand) {
	    	loadDescriptions();
	    	String command = rawCommand.toLowerCase(Locale.ROOT).trim().replaceAll("\\b(the|to|out)\\b", "");
	        String militia = command.replaceAll("\\s+", " ");
	        String[] commando = militia.split(" ");
	        CozyAdventureGUI.addStory("\n> " + rawCommand);
	        
	        //just change switch cases to ours
	        switch (commando[0]) {
			case "go":
				if (commando.length>1)
					switch(commando[1]) {
					case "up":
						Game.move(commando[1]);
						break;
					case "down":
						Game.move(commando[1]);
						break;
					case "north":
						Game.move(commando[1]);
						break;
					case "south":
						Game.move(commando[1]);
						break;
					case "east":
						Game.move(commando[1]);
						break;
					case "west":
						Game.move(commando[1]);
						break;
					default:
						CozyAdventureGUI.addStoryDtl("Go where?");
						break;
					}
				else
					CozyAdventureGUI.addStoryDtl("Go where?");
				break;
			case "take":
				if (commando.length>1) {
					Game.takeItem(commando[1]);
				} else {
					CozyAdventureGUI.addStoryDtl("Take what?");
				}
				break;
			case "i":
				if (inventory.isEmpty() != true) {
					CozyAdventureGUI.addStory("\n\nYou have:\n");
					for (Item a:inventory.values()) {
						CozyAdventureGUI.addStory("\n\t"+a);
					}
				} else {
					CozyAdventureGUI.addStoryDtl("You are carrying nothing.");
				}
				break;
			case "use":
				if (commando.length>1) {
					if (inventory.containsKey(commando[1])==true)
						inventory.get(commando[1]).use();
					else
						CozyAdventureGUI.addStoryDtl("You don't have a "+commando[1]+"..."+"\n");
				} else {
					CozyAdventureGUI.addStoryDtl("Use what?");
				}
				break;
			case "open":
				if (commando.length>1) {
					if (room.getHeavyItem(commando[1])!=null)
						room.getHeavyItem(commando[1]).open();
					else if (inventory.containsKey(commando[1]))
						inventory.get(commando[1]).open();
					else
						CozyAdventureGUI.addStoryDtl("There is no "+commando[1]+" to open..."+"\n");
				} else {
					CozyAdventureGUI.addStoryDtl("Close.");
				}
				break;
			case "close":
				if (commando.length>1) {
					if (room.getHeavyItem(commando[1])!=null)
						room.getHeavyItem(commando[1]).close();
					else if (inventory.containsKey(commando[1]))
						inventory.get(commando[1]).close();
					else
						CozyAdventureGUI.addStoryDtl("There is no "+commando[1]+" to close..."+"\n");
				} else {
					CozyAdventureGUI.addStoryDtl("Open.");
				}
				break;
			case "read":
				if (commando.length>1) {
					if (inventory.containsKey(commando[1])==true)
						inventory.get(commando[1]).read();
					else
						CozyAdventureGUI.addStoryDtl("You don't have a "+commando[1]+"..."+"\n");
				} else {
					CozyAdventureGUI.addStoryDtl("Read what?");
				}
				break;
			case "look":
				CozyAdventureGUI.addStoryDtl("\n"+room.getName()+"\n"+house.get(room.getName())+"\n");
				break;
			case "check":
				if (commando.length>1) {
					if (commando[1].equals("change")) {
						if (room.getName().equals("Attic")) {
							room.getHeavyItem("safe").checkChange();
						} else {
							CozyAdventureGUI.addStoryDtl("\nWhat are you talking about?? You can't do that here.");
						}
					} else {
						CozyAdventureGUI.addStoryDtl("You can't check that dude.");
					}
				} else {
					CozyAdventureGUI.addStoryDtl("You checkin me out or somethin?");
				}
				break;
			case "talk":
				if (commando.length>1) {
					if (room.isNPC()==true)
						room.getNPC(commando[1]).talk();
					else
						CozyAdventureGUI.addStoryDtl("But nobody came...");
				} else {
					CozyAdventureGUI.addStoryDtl("And who are you talking to? Yourself?");
				}
				break;
			case "save":
				saveGame();
				CozyAdventureGUI.addStoryDtl("\n"+"Your progress has been saved!"+"\n");
				break;
			case "load":
				loadGame();
				CozyAdventureGUI.addStoryDtl("\n"+"Loaded save file."+"\n"+"Welcome back!"+"\n");
				break;
			case "help":
				CozyAdventureGUI.addStoryDtl("To move, input a direction (north, up, down, east, etc.).\nYou can try other commands like 'take' or 'i'.\n");
				break;
			case "debug":
				for (String r:house.keySet()) {
					CozyAdventureGUI.addStory(r);
				}
				for (String a:house.values())
					CozyAdventureGUI.addStory(a+"\n");
				break;
			case "debug2":
				CozyAdventureGUI.addStoryDtl("This is the 'Game' class.");
				break;
			default:
				CozyAdventureGUI.addStoryDtl("What did you just say?");
				break;
	        }
	    }
//	            case "look":
//	            case "look around":
//	                addStory(
//	                        "The trees sway gently in the breeze. " +
//	                        "An old lantern rests beside the cottage " +
//	                        "door, and warm light flickers behind " +
//	                        "the little windows."
//	                );
//	                break;
//
//	            case "take lantern":
//	            case "get lantern":
//	    }

	    // Appends new text and scrolls to the latest passage

	    // Start the application
	public static void main(String[] args) {
		loadDescriptions();
		SwingUtilities.invokeLater(() -> {
			CozyAdventureGUI game = new CozyAdventureGUI();
			game.setVisible(true);
		});
	}
	
	
}

