package lab06;

import javax.swing.JOptionPane;

public class Parse {

	String port;
	
	public String SplitExpression(String string) {
		
		String[] parts = string.split(";");
		port = parts[0];
		
		return port;
	}
	
	public int GetPort(String s) {
		
		switch(s) {
			case("+"): {
				return 2000;
			}
			case("-"): {
				return 2500;
			}
			case("*"): {
				return 3000;
			}
			default: {
				JOptionPane.showMessageDialog(null, "B³êdne wyra¿enie, wpisz ponownie", "Powiadomienie", JOptionPane.WARNING_MESSAGE);
				return 0;
			}
		}
	}
}
