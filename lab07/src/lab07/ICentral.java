package lab07;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface ICentral extends Remote{

	public boolean Register(IMonitor m) throws RemoteException;
	
	public Ticket TicketQuery(Ticket ticketServed,String category) throws RemoteException;
	
	public Ticket GetTicket(String category) throws RemoteException;
	
	public int Office() throws RemoteException;
	
	public int DeletingOffice(int Office) throws RemoteException;
}