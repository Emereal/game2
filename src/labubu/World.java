package labubu;

//import java.util.Scanner;

public class World {
	public static Room buildWorld() {
		Room livingRoom = new Room("Living Room", Game.loadDescription("Living Room"));
		Room office = new Room("Office", Game.loadDescription("Office"));
		Room kitchen = new Room("Kitchen", Game.loadDescription("Kitchen"));
		Room bedroom = new Room("Bedroom", Game.loadDescription("Bedroom"));
		Room attic = new Room("Attic", Game.loadDescription("Attic"));
		Room garage = new Room("Garage", Game.loadDescription("Garage"));
		
		livingRoom.addExit(office, 1); // 1=west
		livingRoom.addExit(bedroom, 2); // 2=north
		livingRoom.addExit(kitchen, 0); // 0=east
		office.addExit(livingRoom, 0);
		bedroom.addExit(attic, 4); // 4=up
		bedroom.addExit(livingRoom, 3); // 3=south
		attic.addExit(bedroom, 5); // 5=down
		kitchen.addExit(livingRoom, 1);
		kitchen.addExit(garage, 3);
		garage.addExit(kitchen, 2);
		bedroom.addItem("officekey", new OfficeKey());
		livingRoom.addItem("tool", new Tool());
		livingRoom.addItem("musicbox", new Item("Music Box"));
		livingRoom.addHeavyItem("couch", new Item("Couch"));
		office.addItem("Satchel", new Satchel());
		kitchen.addItem("knife", new Item("Knife"));
		kitchen.addItem("flashlight", new Flashlight());
		kitchen.addItem("mysteriousorb", new Item("Mysterious Orb"));
		attic.addHeavyItem("safe", new Safe());
		bedroom.addNPC("connor", new Connor());
		office.setLockStatus(true);
		attic.setLockStatus(true);
		garage.setLockStatus(true);

		return livingRoom;
	}
	public static void main(String[] args) {
		System.out.println("Wait why are you in World bro go to the Game file");
	}
	
}
