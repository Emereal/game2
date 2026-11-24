package labubu;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class NPC {
	
	private String name;
	
	public NPC(String n) {
		name = n;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void say(String dia) {
		CozyAdventureGUI.addStory("\n"+name.toUpperCase()+"\n"+dia);
	}
	
	public void talk() {
		CozyAdventureGUI.addStoryDtl("\nYou can't talk to "+name+".");
	}
	
	public void give(Item tem) {
		CozyAdventureGUI.addStoryDtl("\nDespite the generous offer, it seems "+name+" does not\n want your "+tem.getName()+".");
	}
	
	public int getResponse(String[] opt) {
		int ch = 0;
		for (int i=0;i<opt.length;i++) {
			CozyAdventureGUI.addStory((i+1)+") "+opt[i]);
		}
		String choice = JOptionPane.showInputDialog(null, "Choose an option: ");
		ch = Integer.valueOf(choice);
		return ch;
	}

}
