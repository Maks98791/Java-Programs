package lab03;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

public class Room {

	int number;
	int openTime;
	int closeTime;
	
	public Room() {}
	
	public Room(int number, int openTime, int closeTime) {
		this.number = number;
		this.openTime = openTime;
		this.closeTime = closeTime;
	}
	
	public static List<Room> GettingRooms(List<Room> rooms){
		try {
			
            File roomFile = new File("E:\\Java\\lab03\\Rooms.txt");
            Scanner input = new Scanner(roomFile);

            while (input.hasNextLine()) {
            	
            	String[] splitString = input.nextLine().split(";");
            	int[] splitInt = new int[3];
            	splitInt[0] = Integer.parseInt(splitString[0]);
            	splitInt[1] = Integer.parseInt(splitString[1]);
            	splitInt[2] = Integer.parseInt(splitString[2]);
            	rooms.add(new Room(splitInt[0], splitInt[1], splitInt[2]));
            }
            input.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
		return rooms;
	}
	
	public static void EditRoomsList(int nr, int op, int cl) {
		
		try(FileWriter fw = new FileWriter("E:\\Java\\lab03\\Rooms.txt", true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
			{
			    out.println(nr + ";" + op + ";" + cl);
			} catch (IOException e) {}
	}
}
