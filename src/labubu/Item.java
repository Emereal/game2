package labubu;

import java.io.Serializable;

public class Item implements Serializable{
	private String tem;
	
	public Item(String t) {
		tem = t;
	}
	
	public String getName() {
		return tem;
	}
	public void setName(String t) {
		tem = t;
	}
	
	public void use() {
		CozyAdventureGUI.addStoryDtl("\nIt doesn't seem like this "+tem+" going to be any help."+"\n");
	}
	
	public void open() {
		CozyAdventureGUI.addStoryDtl("\nYou quickly realize that you can't open the "+tem+", and make sure\n that nobody is watching before putting it back."+"\n");
	}
	
	public void close() {
		CozyAdventureGUI.addStoryDtl("\nYou quickly realize that there's no way to close the "+tem+", and\n make sure that nobody is watching before putting it back."+"\n");
	}
	
	public void read() {
		CozyAdventureGUI.addStoryDtl("\nIt doesn't seem like there's anything to read on this "+tem+", so\n you put it back."+"\n");
	}
	
	public void checkChange() {
		CozyAdventureGUI.addStoryDtl("\nWhat are you talking about?? You can't do that here.");
	}
	
	public void takeChange(double ch) {
		CozyAdventureGUI.addStoryDtl("And where are you gonna put it? Your stinky shoes?");
	}
	
	public String toString() {
		return tem;
	}
	

}
