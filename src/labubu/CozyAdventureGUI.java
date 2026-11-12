package labubu;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Locale;

public class CozyAdventureGUI extends JFrame {
	
	private Room curr = Game.room;

    private static final Color DARK_BROWN =
            new Color(72, 53, 42);
    private static final Color CREAM =
            new Color(250, 242, 222);
    private static final Color LIGHT_TAN =
            new Color(225, 207, 174);

    private final static JTextArea storyText = new JTextArea();
    private final JTextField commandField = new JTextField();

    private final JLabel locationValue =
            new JLabel(curr.getName());

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    
    public CozyAdventureGUI() {
        setTitle("The Home Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 650);
        setMinimumSize(new Dimension(700, 500));
        setLocationRelativeTo(null);

        // Main background: a repeating checkered pattern
        CheckerboardPanel background = new CheckerboardPanel();
        background.setLayout(new BorderLayout(8, 8));
        background.setBorder(new EmptyBorder(12, 12, 12, 12));
        setContentPane(background);

        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(DARK_BROWN);
        header.setBorder(new EmptyBorder(12, 16, 12, 16));

        JLabel title = new JLabel("The Home Game");
        title.setFont(new Font("Serif", Font.BOLD, 28));
        title.setForeground(CREAM);

        JLabel subtitle = new JLabel("An adventure awaits...");
        subtitle.setFont(new Font("Serif", Font.ITALIC, 14));
        subtitle.setForeground(LIGHT_TAN);

        JPanel headingText = new JPanel();
        headingText.setLayout(new BoxLayout(
                headingText, BoxLayout.Y_AXIS));
        headingText.setOpaque(false);
        headingText.add(title);
        headingText.add(Box.createVerticalStrut(4));
        headingText.add(subtitle);

        header.add(headingText, BorderLayout.CENTER);

        // Story panel
        JPanel storyPanel = new JPanel(new BorderLayout(0, 8));
        storyPanel.setBackground(CREAM);
        storyPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(DARK_BROWN, 2),
                new EmptyBorder(12, 12, 12, 12)));

        JLabel storyHeading = new JLabel("YOUR PROGRESS");
        storyHeading.setFont(
                new Font("SansSerif", Font.BOLD, 13));
        storyHeading.setForeground(DARK_BROWN);

        storyText.setEditable(false);
        storyText.setLineWrap(true);
        storyText.setWrapStyleWord(true);
        storyText.setFont(new Font("Serif", Font.PLAIN, 17));
        storyText.setForeground(DARK_BROWN);
        storyText.setBackground(CREAM);
        storyText.setMargin(new Insets(8, 4, 8, 4));

        JScrollPane scrollPane = new JScrollPane(storyText);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(CREAM);

        storyPanel.add(storyHeading, BorderLayout.NORTH);
        storyPanel.add(scrollPane, BorderLayout.CENTER);

        // Side panel: location and inventory
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(
                sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(CREAM);
        sidebar.setPreferredSize(new Dimension(180, 200));
        sidebar.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(DARK_BROWN, 2),
                new EmptyBorder(14, 12, 14, 12)));

        JLabel locationHeading = makeHeading("LOCATION");

        locationValue.setFont(new Font("Serif", Font.PLAIN, 15));
        locationValue.setForeground(DARK_BROWN);

        sidebar.add(locationHeading);
        sidebar.add(Box.createVerticalStrut(8));
        sidebar.add(locationValue);
        sidebar.add(Box.createVerticalStrut(24));
//        sidebar.add(new JSeparator());
//        sidebar.add(Box.createVerticalStrut(20));
//        sidebar.add(inventoryHeading);
//        sidebar.add(Box.createVerticalStrut(8));
//        sidebar.add(inventoryValue);
//        sidebar.add(Box.createVerticalGlue());

        JPanel mainArea = new JPanel(new BorderLayout(8, 0));
        mainArea.setOpaque(false);
        mainArea.add(storyPanel, BorderLayout.CENTER);
        mainArea.add(sidebar, BorderLayout.EAST);

        // Click choices
        JPanel choices = new JPanel(new FlowLayout(
                FlowLayout.LEFT, 8, 4));
        choices.setOpaque(false);

//        choices.add(makeButton("Look Around", "look"));
//        choices.add(makeButton("Take Lantern", "take lantern"));
//        choices.add(makeButton("Enter Cottage", "enter cottage"));
//       choices.add(makeButton("Inventory", "inventory"));

        // Text command input
        JPanel commandPanel = new JPanel(new BorderLayout(8, 0));
        commandPanel.setOpaque(false);
        commandPanel.setBorder(new EmptyBorder(6, 0, 0, 0));

        JLabel commandLabel = new JLabel("Your action:");
        commandLabel.setForeground(DARK_BROWN);
        commandLabel.setFont(
                new Font("SansSerif", Font.BOLD, 13));

        commandField.setFont(new Font("Serif", Font.PLAIN, 16));
        commandField.setBackground(CREAM);
        commandField.setForeground(DARK_BROWN);
        commandField.addActionListener(e -> submitCommand());

        JButton sendButton = Game.makeButton("Go", "");
        sendButton.addActionListener(e -> submitCommand());

        commandPanel.add(commandLabel, BorderLayout.WEST);
        commandPanel.add(commandField, BorderLayout.CENTER);
        commandPanel.add(sendButton, BorderLayout.EAST);

        JPanel bottomArea = new JPanel(new BorderLayout());
        bottomArea.setOpaque(false);
        bottomArea.add(choices, BorderLayout.NORTH);
        bottomArea.add(commandPanel, BorderLayout.SOUTH);

        background.add(header, BorderLayout.NORTH);
        background.add(mainArea, BorderLayout.CENTER);
        background.add(bottomArea, BorderLayout.SOUTH);

        // Opening passage
        addStoryDtl(curr.getName()+"\n"+curr.getDesc()+"\n\nWhat would you like to do?\n");
    }
    
    // EDIT EVERYTHING BELOW HERE ------------------------------------------------------------------------------------------

    // A small heading for the side panel
    private JLabel makeHeading(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", Font.BOLD, 12));
        label.setForeground(DARK_BROWN);
        return label;
    }

    // Reads the player's typed command
    private void submitCommand() {
        String command = commandField.getText().trim();

        if (!command.isEmpty()) {
            commandField.setText("");
            Game.runGameGUI(command);
        }
    }

    // Basic example game logic
    public static void handleCommand(String rawCommand) {
    	Game.loadDescriptions();
    	String command = rawCommand.toLowerCase(Locale.ROOT).trim().replaceAll("\\b(the|to|out)\\b", "");
        String militia = command.replaceAll("\\s+", " ");
        String[] commando = militia.split(" ");
        addStory("\n> " + rawCommand);
        
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
					addStoryDtl("Go where?");
					break;
				}
			else
				addStoryDtl("Go where?");
			break;
		case "debug2":
			CozyAdventureGUI.addStoryDtl("This is the 'CozyAdventureGUI' class.");
			break;
		case "take":
			if (commando.length>1) {
				Game.takeItem(commando[1]);
			} else {
				addStoryDtl("Take what?");
			}
			break;
		case "i":
			if (Game.inventory.isEmpty() != true) {
				for (Item a:Game.inventory.values()) {
					addStoryDtl(a+" | ");
				}
			} else {
				addStoryDtl("You are carrying nothing.");
			}
			break;
		case "use":
			if (commando.length>1) {
				if (Game.inventory.containsKey(commando[1])==true)
					Game.inventory.get(commando[1]).use();
				else
					addStoryDtl("You don't have a "+commando[1]+"..."+"\n");
			} else {
				addStoryDtl("Use what?");
			}
			break;
		case "look":
			addStoryDtl("\n"+Game.room.getName()+"\n"+Game.house.get(Game.room.getName())+"\n");
			break;
		case "save":
			Game.saveGame();
			addStoryDtl("\n"+"Your progress has been saved!"+"\n");
			break;
		case "load":
			Game.loadGame();
			addStoryDtl("\n"+"Loaded save file."+"\n"+"Welcome back!"+"\n");
			break;
		case "help":
			addStoryDtl("To move, input a direction (north, up, down, east, etc.).\nYou can try other commands like 'take' or 'i'.\n");
			break;
		case "debug":
			for (String r:Game.house.keySet()) {
				addStoryDtl(r);
			}
			for (String a:Game.house.values())
				addStoryDtl(a+"\n");
			break;
		default:
			addStoryDtl("What did you just say?");
			break;
        }
    }

    // Appends new text and scrolls to the latest passage
    public static void addStory(String text) {
        storyText.append(text + "\n");
        storyText.setCaretPosition(
                storyText.getDocument().getLength());
    }
    
//    public static void addStoryDtl(String text) {
//    	char[] txt = text.toCharArray();
//    	for (char c:txt)
//    		try {
//    			storyText.setText(Character.toString(c));
//    			Thread.sleep(50);
//    		} catch (Exception e) {
//    			e.printStackTrace();
//    		}
//        storyText.setCaretPosition(
//                storyText.getDocument().getLength());
//    }
    private static class Typewriter extends Thread {

    	public String text;
    	
    	public void run() {
    		char[] txt = text.toCharArray();
        	for (char c:txt)
        		try {
        			storyText.append(Character.toString(c));
        			Thread.sleep(10);
        		} catch (Exception e) {
        			e.printStackTrace();
        		}
            storyText.setCaretPosition(
                    storyText.getDocument().getLength());
    	}
    	
    }
    
    private static class Hold extends Thread {
    	
    	public void run(boolean b) {
    		if (b)
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			else
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    	}
    	
    }
    
    public static void addStoryDtl(String text) {
    	Typewriter tw = new Typewriter();
    	tw.text = text;
    	tw.start();
    }
    
    public static void Wait(String choice) {
    	if (choice.toLowerCase().equals("long")) {
    		Hold w = new Hold();
    		w.run(true);
    	} else {
    		Hold w = new Hold();
    		w.run(false);
    	}
    }
  

    // Draws the repeating checkered background
    private static class CheckerboardPanel extends JPanel {
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

    // Start the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CozyAdventureGUI game = new CozyAdventureGUI();
            game.setVisible(true);
        });
    }
}

