/*Zadanie polega na zaimplementowaniu rozwi�zania pe�ni�cego rol� kolejkowego systemu obs�ugi 
 * klient�w przy za�atwianiu n typ�w spraw.

Specyfikacja problemu do rozwi�zania:
- elementami systemu s�: terminale, monitory, centrala, stanowiska,
- terminal wydaje klientom numerowane bilety po wybraniu typu sprawy,
- na monitorach dla ka�dego typu sprawy wy�wietlane s�: liczba oczekuj�cych, numery bilet�w 
  aktualnie obs�ugiwanych,
- centrala przekazuje terminalom na ��danie numery kolejnych bilet�w, 
- centrala pozwala zarejestrowa� si� monitorom, by potem przekazywa� im informacje aktualizacyjne,
- aktualizacja informacji na monitorze nast�puje przy ka�dym przekazaniu z centrali biletu 
  jakiemu� terminalowi b�d� po zg�oszeniu centrali gotowo�ci obs�ugi klienta na kt�rym� ze 
  stanowisk,
- na stanowisku mo�na poprosi� central� o numer biletu nast�pnego klienta oraz zg�osi�,
  �e klient z danym numerem biletu zosta� obs�u�ony. 
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
	//maksymalna liczba biur, do kt�rych mo�na bra� numerek
	private int[] Offices = {1,2,3};
	private static Info[] Info = new Info[3];
	
	ArrayList<Ticket> Tickets = new ArrayList<Ticket>();
	ArrayList<IMonitor> Monitors = new ArrayList<IMonitor>();
	
	public Central() {
		super();
	}
    
	// pobieranie biletu z kategori� oraz numerem
	public Ticket GetTicket(String category) throws RemoteException
	{
		Ticket ticket = new Ticket(category, TicketNumber);
		Tickets.add(ticket);
		TicketNumber++;
		
		int i = CategoryNumber(category);
		
		// wywo�ywanie funkcji aktualizuj�cej to co wy�wietla sie na monitorze
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

	// funkcja zwracaj�ca numer stanowiska  
	private int CategoryNumber(String category) {
		
		switch(category) {
		case "Rejestracja samochod�w":
			return 0;
			
		case "Wydawanie dowod�w":
			return 1;
			
		case "Odbi�r prawa jazdy":
			return 2;

		default:
			return 3;
		}
	}

	// utworzenie obiekt�w klasy Info
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

	// Sprawdzanie statusu bilet�w na dane stanowisko i zwr�cenie nastepnego w kolejce
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

	// usuwanie stanowiska i przesuwanie w tablicy �eby zape�ni� wolne miejsce
	public int DeletingOffice(int Office) throws RemoteException {
		
		Offices[Office-1] = Office;
		
		return 0;
	}
	
	public static void main(String args[]) {
        
		// wywo�anie funkcji tworz�cej informacje
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
