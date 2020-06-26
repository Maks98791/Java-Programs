package lab03;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Doctor {
	
	String name;
	String surname;
	String specialization;
	
	public Doctor() {}
	
	public Doctor(String name, String surname, String specialization) {
		this.name = name;
		this.surname = surname;
		this.specialization = specialization;
	}
	
	public static List<Doctor> SearchDoctors(List<Doctor>doc){
	
		try {
			
            File docFile = new File("E:\\Java\\lab03\\Doctors.txt");
            Scanner input = new Scanner(docFile);

            while (input.hasNextLine()) {
            	
            	String[] split = input.nextLine().split(";");
            	doc.add(new Doctor(split[0], split[1], split[2]));
            }
            input.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
		
		return doc;
	}
	
	public static void EditDoctorsList(String nam, String surnam, String spec){
		
		try(FileWriter fw = new FileWriter("E:\\Java\\lab03\\Doctors.txt", true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
			{
			    out.println(nam + ";" + surnam + ";" + spec);
			} catch (IOException e) {}
	}
}
