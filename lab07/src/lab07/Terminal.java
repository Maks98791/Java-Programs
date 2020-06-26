package lab07;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Font;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Terminal extends JFrame{

	public static String host;
	public static Registry registry;
	Ticket ticket = new Ticket("wstepny",1);
	
	private JPanel contentPane;
	private JFrame frmTerminal;
    
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				host = (args.length < 1) ? null : args[0];
				try {
					Terminal window = new Terminal();
					window.setVisible(true);
					registry = LocateRegistry.getRegistry(host);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Terminal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 472, 180);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton buttonprawojazdy = new JButton("Pobierz numer");
		buttonprawojazdy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TakingNumber("Odbiór prawa jazdy");
			}
		});
		buttonprawojazdy.setFont(new Font("Tahoma", Font.PLAIN, 18));
		buttonprawojazdy.setBounds(0, 0, 147, 70);
		contentPane.add(buttonprawojazdy);
		
		JButton buttonrejestracja = new JButton("Pobierz numer");
		buttonrejestracja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TakingNumber("Rejestracja samochodów");
			}
		});
		buttonrejestracja.setFont(new Font("Tahoma", Font.PLAIN, 18));
		buttonrejestracja.setBounds(147, 0, 147, 70);
		contentPane.add(buttonrejestracja);
		
		JButton buttondowody = new JButton("Pobierz numer");
		buttondowody.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TakingNumber("Wydawanie dowodów");
			}
		});
		buttondowody.setFont(new Font("Tahoma", Font.PLAIN, 18));
		buttondowody.setBounds(299, 0, 147, 70);
		contentPane.add(buttondowody);
		
		JLabel lblPrawoJazdy = new JLabel("Prawo jazdy");
		lblPrawoJazdy.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPrawoJazdy.setBounds(0, 81, 147, 46);
		contentPane.add(lblPrawoJazdy);
		
		JLabel lblRejestracja = new JLabel("Rejestracja");
		lblRejestracja.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRejestracja.setBounds(147, 81, 147, 46);
		contentPane.add(lblRejestracja);
		
		JLabel lblDowody = new JLabel("Dowody");
		lblDowody.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDowody.setBounds(299, 81, 147, 46);
		contentPane.add(lblDowody);
		
	}
	


	public void TakingNumber(String category)
	{
		ICentral stub;
		
		try {
			stub = (ICentral) registry.lookup("Bilet");
			ticket = stub.GetTicket(category);
		} catch (RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        System.out.println(ticket.category);
        System.out.println(ticket.status);
	}
}
