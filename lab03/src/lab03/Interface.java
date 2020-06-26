package lab03;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Interface {
	
	public static void clearScreen() {  
	    System.out.print("\033[H\033[2J");  
	    System.out.flush();  
	} 
	
	public static void Menu() {
		
		System.out.println("__________MENU__________\n" 
				+ "1. Search doctors by specialization.\n"
				+ "2. Search registrations.\n"
				+ "3. Edit doctors list.\n"
				+ "4. Edit rooms list.\n"
				+ "5. Register a patient.\n"
				+ "6. Quit.");
		
		Scanner input = new Scanner(System.in);
		int i = input.nextInt();
		
		switch(i) {
		case(1): {
			//wyszukanie lekarza o zadanej specjalizacji
			clearScreen();
			System.out.println("Dostêpne specjalizacje w naszej przychodzi:\n");
			
			List<Doctor> docs = new ArrayList<Doctor>();
			docs = Doctor.SearchDoctors(docs);
			int a=0;
			
			for(Doctor object : docs) {
				System.out.println(a + ". " + object.specialization);
				a++;
			}
			
			System.out.println("Wpisz nazwe specjalizacji, która Ciê interesuje:\n");
			Scanner read = new Scanner(System.in);
			String in = read.nextLine();
			
			for(Doctor object : docs) {
				if(object.specialization.equals(in)) {
					System.out.println("Imiê lekarza: " + object.name +
							"\nNazwisko lekarza: " + object.surname +
							"\nSpecjalizacja: " + object.specialization);
				}
			}
			
			System.out.println("\n\n\n1. Wróæ do Menu.");
			if(read.nextLine().equals("1")) {
				Interface.Menu();
			}
			
			break;
		}
		case(2): {
			
			List<Registration> regist = new ArrayList<Registration>();
			Registration reg = new Registration();
			reg.SearchRegistrations(regist);
			break;
		}
		case(3): {
			
			Scanner read = new Scanner(System.in);
			System.out.println("Wpisz dane lekarza: \n");
			System.out.println("Imiê: ");
			String name = read.nextLine();
			System.out.println("Nazwisko: ");
			String surname = read.nextLine();
			System.out.println("Specjalizacja: ");
			String spec = read.nextLine();
			
			Doctor.EditDoctorsList(name, surname, spec);
			
			System.out.println("\n\n\n1. Wróæ do Menu.");
			if(read.nextLine().equals("1")) {
				Interface.Menu();
			}
			break;
		}
		case(4): {
			
			Scanner read = new Scanner(System.in);
			System.out.println("Wpisz dane nowego gabinetu: \n");
			System.out.println("Numer: ");
			String number = read.nextLine();
			System.out.println("Godzina otwarcia: ");
			String open = read.nextLine();
			System.out.println("Godzina zamkniêcia: ");
			String close = read.nextLine();
			
			Room.EditRoomsList(Integer.parseInt(number), Integer.parseInt(open), Integer.parseInt(close));
			
			System.out.println("\n\n\n1. Wróæ do Menu.");
			if(read.nextLine().equals("1")) {
				Interface.Menu();
			}
			
			break;
		}
		case(5): {
			
			System.out.println("1. Zarejestruj pacjenta.\n2. Wyrejestruj pacjenta");
			int a = input.nextInt();
			
			switch(a) {
			case(1): {
				
				System.out.println("Podaj dane pacjenta:\n");
				System.out.println("Imiê: ");
				String name = input.next();
				System.out.println("Nazwisko: ");
				String surname = input.next();
				System.out.println("Pesel: ");
				String ID = input.next();
				System.out.println("Podaj specjalizacje lekarza do którego chcesz siê zapisaæ: ");
				String spec = input.next();
				
				List<Doctor> docs = new ArrayList<Doctor>();
				docs = Doctor.SearchDoctors(docs);
				List<Room> rooms = new ArrayList<Room>();
				rooms = Room.GettingRooms(rooms);
				
				Registration.RegisterPatient(name, surname, ID, spec, docs, rooms);
			}
			case(2): {
				
				System.out.println("Podaj pesel pacjenta:\n");
				System.out.println("Pesel: ");
				String ID = input.next();
				
				Registration.UnregisterPatient(ID);
			}
			}
			break;
		}
		case(6): {
			
			System.exit(0);
			break;
		}		
		}
	}
}
