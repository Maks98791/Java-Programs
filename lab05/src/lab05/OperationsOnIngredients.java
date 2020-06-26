package lab05;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class OperationsOnIngredients {
	
	String nameOfIngredient;
	int randomNumber;
	
	public int CompleteIngredients() {
		
		
		return randomNumber;
	}
	
	public String RandIngredient(int number) {
		
		switch(number) {
		case(0):{
			nameOfIngredient = "M¹ka";
			break;
		}
		case(1):{
			nameOfIngredient = "Ser";
			break;
		}
		case(2):{
			nameOfIngredient = "Woda";
			break;
		}
		case(3):{
			nameOfIngredient = "Mleko";
			break;
		}
		case(4):{
			nameOfIngredient = "Jajka";
			break;
		}
	}
		return nameOfIngredient;
	}
}