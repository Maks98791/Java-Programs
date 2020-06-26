package lab03;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

public class Registration {

	Patient patient;
	Room room;
	Doctor doctor;
	
	public static void UnregisterPatient(String ID) {
		
	}

	public static void RegisterPatient(String name, String surname, String ID, String spec, List<Doctor> docs, List<Room> rooms) {
		
		for(Doctor doc : docs) {
			if(doc.specialization == spec) {
				Registration reg = new Registration();
				reg.doctor.specialization = spec;
				reg.patient.name = name;
				reg.patient.surname = surname;
				reg.patient.ID = ID;
				
				Date date = new Date();
				int day = date.getDay();
				
				if(day < 7) {
					reg.room = rooms.get(0);
				}
				else if(day >= 7 && day < 14) {
					reg.room = rooms.get(1);
				}
				else if(day >= 14 && day < 21) {
					reg.room = rooms.get(2);
				}
				else if(day >= 21 && day < 30) {
					reg.room = rooms.get(3);
				}
			}
			
			try(FileWriter fw = new FileWriter("E:\\Java\\lab03\\Registrations.txt", true);
				    BufferedWriter bw = new BufferedWriter(fw);
				    PrintWriter out = new PrintWriter(bw))
				{
				    out.println(name + ";" + surname + ";" + spec + ";" + ID);
				} catch (IOException e) {}
		}
	}
	
	public Registration SearchRegistrations(List<Registration>regist) {
		
		//dorobic
		return regist.get(1);
	}
}
