/*Zadanie polega na zaimplementowaniu rozwi¹zania pe³ni¹cego rolê kolejkowego systemu obs³ugi 
 * klientów przy za³atwianiu n typów spraw.

Specyfikacja problemu do rozwi¹zania:
- elementami systemu s¹: terminale, monitory, centrala, stanowiska,
- terminal wydaje klientom numerowane bilety po wybraniu typu sprawy,
- na monitorach dla ka¿dego typu sprawy wyœwietlane s¹: liczba oczekuj¹cych, numery biletów 
  aktualnie obs³ugiwanych,
- centrala przekazuje terminalom na ¿¹danie numery kolejnych biletów, 
- centrala pozwala zarejestrowaæ siê monitorom, by potem przekazywaæ im informacje aktualizacyjne,
- aktualizacja informacji na monitorze nastêpuje przy ka¿dym przekazaniu z centrali biletu 
  jakiemuœ terminalowi b¹dŸ po zg³oszeniu centrali gotowoœci obs³ugi klienta na którymœ ze 
  stanowisk,
- na stanowisku mo¿na poprosiæ centralê o numer biletu nastêpnego klienta oraz zg³osiæ,
  ¿e klient z danym numerem biletu zosta³ obs³u¿ony. 
*/
package lab07;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Central implements ICentral{

	private int TicketNumber = 1;
	//maksymalna liczba biur, do których mo¿na braæ numerek
	private int[] Offices = {1,2,3};
	private static Info[] Info = new Info[3];
	
	ArrayList<Ticket> Tickets = new ArrayList<Ticket>();
	ArrayList<IMonitor> Monitors = new ArrayList<IMonitor>();
	
	public Central() {
		super();
	}
    
	// pobieranie biletu z kategori¹ oraz numerem
	public Ticket GetTicket(String category) throws RemoteException
	{
		Ticket ticket = new Ticket(category, TicketNumber);
		Tickets.add(ticket);
		TicketNumber++;
		
		int i = CategoryNumber(category);
		
		// wywo³ywanie funkcji aktualizuj¹cej to co wyœwietla sie na monitorze
		if (i<3)
		{
			Info[i].Waiting++;
			
			for(IMonitor m : Monitors)
			{
				m.Update(Info);
			}
		}
		
		return ticket;
	}

	// funkcja zwracaj¹ca numer stanowiska  
	private int CategoryNumber(String category) {
		
		switch(category) {
		case "Rejestracja samochodów":
			return 0;
			
		case "Wydawanie dowodów":
			return 1;
			
		case "Odbiór prawa jazdy":
			return 2;

		default:
			return 3;
		}
	}

	// utworzenie obiektów klasy Info
	private static void CreatingInformation() {
		
		for(int i=0;i<3;i++)
		{
			Info[i] = new Info();
			Info[i].Waiting = 0;
			Info[i].Serving = new Ticket[1];
			Info[i].Serving[0] = new Ticket("start",0);
		}
	}

	public boolean Register(IMonitor m) throws RemoteException {
		Monitors.add(m);
		m.Update(Info);
		return false;
	}

	// Sprawdzanie statusu biletów na dane stanowisko i zwrócenie nastepnego w kolejce
	public Ticket TicketQuery(Ticket ticketServed, String category) throws RemoteException {
		
		Ticket toReturn = new Ticket("start",0);
		boolean found = false;
		
		int i = CategoryNumber(category);
		
		for(Ticket bilet : Tickets) {
			
			if(bilet.number == ticketServed.number)
			{
				bilet.status='s';
			}
			
			// sprawdzanie statusu biletu i zmienianie go w razie bycia pierwszym w kolejce
			if(bilet.number > ticketServed.number && category.equals(bilet.category) && bilet.status != 's' && found == false)
			{
				bilet.status='c';
				Info[i].Waiting--;
				toReturn = bilet;
				found = true;
			}
		}
			Info[i].Serving[0] = toReturn;
			
			for(IMonitor m : Monitors)
			{
				m.Update(Info);
			}
			
			return toReturn;	
		}

		
	// sprawdzenie czy jest wolne stanowisko 
	public int Office() throws RemoteException {
		
		for(int i=0; i<3; i++)
		{
			if(Offices[i]!=0)
			{
				int a = Offices[i];
				Offices[i] = 0;
				
				return a;
			}
		}
		
		return 7;
	}

	// usuwanie stanowiska i przesuwanie w tablicy ¿eby zape³niæ wolne miejsce
	public int DeletingOffice(int Office) throws RemoteException {
		
		Offices[Office-1] = Office;
		
		return 0;
	}
	
	public static void main(String args[]) {
        
		// wywo³anie funkcji tworz¹cej informacje
    	CreatingInformation();
    	
        try {
        	Registry registry = LocateRegistry.createRegistry(1099);
            Central obj = new Central();
            ICentral stub = (ICentral) UnicastRemoteObject.exportObject((Remote) obj, 0);
            registry = LocateRegistry.getRegistry();
            registry.bind("Bilet", stub);
            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
