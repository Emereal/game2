package labubu;

import java.io.Serializable;
import java.util.HashMap;

//import java.util.ArrayList;

public class Room implements Serializable{
	private String name;
	private String desc;
	private Room[] femto;
	private HashMap<String, Item> items;
	private HashMap<String, Item> heavyitems;
	private HashMap<String, NPC> people;
	private boolean locked;
	
	public Room(String n, String d) {
		name = n;
		desc = d;
		femto = new Room[6];
		items = new HashMap<>();
		heavyitems = new HashMap<>();
		people = new HashMap<>();
		Game.rooms.put(this.getName(), this);
		locked = false;
	}
	public String getName() {
		return name;
	}
	public String getDesc() {
		return desc;
	}
	public void setName(String n) {
		name = n;
	}
	public void setDesc(String d) {
		desc = d;
	}
	public void addExit(Room r, int i) {
		femto[i] = r;
	}
	public Room getExit(int i) {
		return femto[i];
	}
	public void addItem(String name, Item tem) {
		items.put(name, tem);
	}
	
	public void addHeavyItem(String name, Item tem) {
		heavyitems.put(name, tem);
	}
	
	public Item getItem(String name) {
		if (items.get(name)!=null) 
			return items.get(name);
		else if (heavyitems.get(name)!=null)
			return heavyitems.get(name);
		return null;
	}
	
	public Item getHeavyItem(String name) {
		if (heavyitems.get(name)!=null)
			return heavyitems.get(name);
		return null;
	}
	
	public boolean isHeavy(String name) {
		if (heavyitems.get(name)!=null)
			return true;
		return false;
	}

	public void removeItem(String name) {
		if (items.isEmpty() == false);
			items.remove(name);
	}
	
	public NPC getNPC(String name) {
		if (people.get(name)!=null)
			return people.get(name);
		return null;
	}
	
	public boolean isNPC() {
		if (people.isEmpty()==true)
			return false;
		return true;
	}
	
	public void addNPC(String name, NPC person) {
		people.put(name, person);
	}
	
	public boolean getLockStatus() {
		return locked;
	}
	
	public void setLockStatus(boolean b) {
		locked = b;
	}
	
	public String toString() {
		String s = name;
		s += "\n\n" + desc;
		return s;
	}


}
