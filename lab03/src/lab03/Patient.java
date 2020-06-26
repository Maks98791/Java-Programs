package lab03;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class Patient {

	String name;
	String surname;
	String ID;
	
	public Patient() {}
	
	public Patient(String name, String surname, String ID) {
		this.name = name;
		this.surname = surname;
		this.ID = ID;
	}
	
	public List<Patient> SearchPatients(List<Patient>person) {
		
		try {
			
            File patFile = new File("E:\\Java\\lab03\\Patients.txt");
            Scanner input = new Scanner(patFile);

            while (input.hasNextLine()) {
            	
            	String[] split = input.nextLine().split(";");
            	person.add(new Patient(split[0], split[1], split[2]));
            }
            input.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
		return person;
	}
}
