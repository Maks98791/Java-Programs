package lab07;

import java.io.Serializable;

public class Ticket implements Serializable {
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String category;
	public int number;
	public char status; // 'i' - issued, 'c' - called, 's' - served
	  
	Ticket(String category,int number)
	{
		this.category=category;
		this.number=number;
		this.status='i';
	}
}

