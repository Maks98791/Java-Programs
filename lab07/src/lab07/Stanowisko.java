package lab07;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Font;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Stanowisko extends JFrame {

	public static String host;
	public static Registry registry1;
	public static int stanowisko;
	
	Ticket ticket = new Ticket("start",0);
	
	public static JLabel lblStanowiskoNumer;
	public static JTextPane contentPane;
	public static String zakres;
	
	private JFrame frmStanowisko;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				host = (args.length < 1) ? null : args[0];
				try {
					Stanowisko window = new Stanowisko();
					window.frmStanowisko.setVisible(true);
					registry1 = LocateRegistry.getRegistry(host);
					
					OfficeNumber();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Stanowisko() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// inicjalizacja elementów ekranu dla ka¿deogo stanowiska
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JTextPane();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNastpny = new JButton("Nast\u0119pny");
		btnNastpny.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				ICentral stub;
				try {
					stub = (ICentral) registry1.lookup("Bilet");
					ticket = stub.TicketQuery(ticket, zakres);
				} catch (RemoteException | NotBoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();	
				}
				
				if(ticket.number==0)
				{
					contentPane.setText("BRAK");
				}
				else
				{
					contentPane.setText(Integer.toString(ticket.number));
				}
			}
		});
		btnNastpny.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNastpny.setBounds(104, 140, 194, 74);
		contentPane.add(btnNastpny);
		
		JLabel lblStanowiskoNr = new JLabel("Stanowisko nr");
		lblStanowiskoNr.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblStanowiskoNr.setBounds(42, 32, 194, 62);
		contentPane.add(lblStanowiskoNr);
		
		JLabel labelstanowisko = new JLabel("");
		labelstanowisko.setFont(new Font("Tahoma", Font.PLAIN, 22));
		labelstanowisko.setBounds(246, 32, 120, 67);
		contentPane.add(labelstanowisko);	
	}

	// 3 stanowiska
	public static void OfficeNumber()
	{
		ICentral stub;
		
		try {
			stub = (ICentral) registry1.lookup("Bilet");
			stanowisko = stub.Office();
		} catch (RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (stanowisko == 3)
		{
			System.exit(0);
		}
		else
		{
			lblStanowiskoNumer.setText("Stanowisko numer "+stanowisko);
		}
		
		if(stanowisko == 0)
		{
			zakres = "Rejestracja samochodów";	
		}
		else if(stanowisko == 1)
		{
			zakres = "Wydawanie dowodów";
		}
		else if(stanowisko == 2)
		{
			zakres = "Odbiór prawa jazdy";
		}
	}
}
	