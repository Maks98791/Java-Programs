package lab07;

import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Monitor implements IMonitor {
	
	private JFrame frmMonitor;
	private JPanel contentPane;

	/**
	 * @wbp.parser.entryPoint
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				String host = (args.length < 1) ? null : args[0];
				try {
					Monitor window = new Monitor();
					window.frmMonitor.setVisible(true);
					
					Registry registry = LocateRegistry.getRegistry(host);
		            IMonitor m = (IMonitor) UnicastRemoteObject.exportObject( window, 0);
					ICentral stub= (ICentral) registry.lookup("Bilet");
					stub.Register(m);
					
					
				} catch (Exception e) {
					System.err.println("Client exception: " + e.toString());
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public Monitor() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @wbp.parser.entryPoint
	 */
	private void initialize() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 771, 556);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWydawanieDowodw = new JLabel("Wydawanie dowod\u00F3w");
		lblWydawanieDowodw.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblWydawanieDowodw.setBounds(0, 0, 185, 35);
		contentPane.add(lblWydawanieDowodw);
		
		JLabel lblPrawoJazdy = new JLabel("Odbi\u00F3r prawa jazdy");
		lblPrawoJazdy.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPrawoJazdy.setBounds(290, 0, 185, 35);
		contentPane.add(lblPrawoJazdy);
		
		JLabel lblRejestracja = new JLabel("Rejestracja samochod\u00F3w");
		lblRejestracja.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRejestracja.setBounds(565, 0, 185, 35);
		contentPane.add(lblRejestracja);
		
		JList listdowody = new JList();
		listdowody.setBounds(10, 75, 145, 214);
		contentPane.add(listdowody);
		
		JList listprawojazdy = new JList();
		listprawojazdy.setBounds(295, 75, 163, 214);
		contentPane.add(listprawojazdy);
		
		JList listrejestracja = new JList();
		listrejestracja.setBounds(579, 75, 171, 214);
		contentPane.add(listrejestracja);
		
		JLabel lblOczekujce = new JLabel("Oczekuj\u0105ce");
		lblOczekujce.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblOczekujce.setBounds(294, 359, 94, 35);
		contentPane.add(lblOczekujce);
		
		JLabel labeldowody = new JLabel("");
		labeldowody.setFont(new Font("Tahoma", Font.BOLD, 26));
		labeldowody.setBounds(20, 300, 185, 35);
		contentPane.add(labeldowody);
		
		JLabel labelprawojazdy = new JLabel("");
		labelprawojazdy.setFont(new Font("Tahoma", Font.BOLD, 26));
		labelprawojazdy.setBounds(290, 300, 185, 35);
		contentPane.add(labelprawojazdy);
		
		JLabel labelrejestracja = new JLabel("");
		labelrejestracja.setFont(new Font("Tahoma", Font.BOLD, 26));
		labelrejestracja.setBounds(565, 300, 185, 35);
		contentPane.add(labelrejestracja);
		
		JLabel lblAktaulnyNumere = new JLabel("aktaulny numerek");
		lblAktaulnyNumere.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAktaulnyNumere.setBounds(20, 341, 185, 35);
		contentPane.add(lblAktaulnyNumere);
	}

	private void setContentPane(JPanel contentPane2) {
		// TODO Auto-generated method stub
		
	}

	private void setBounds(int i, int j, int k, int l) {
		// TODO Auto-generated method stub
		
	}

	private void setDefaultCloseOperation(int exitOnClose) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	
	// aktualizowanie monitora
	@Override
	public void Update(Info[] i) throws RemoteException {
		
		DefaultListModel<String> model = new DefaultListModel<>();
		JList<String> listdowody = new JList<>( model );
		DefaultListModel<String> model2 = new DefaultListModel<>();
		JList<String> listrejestracja = new JList<>( model2 );
		DefaultListModel<String> model3 = new DefaultListModel<>();
		JList<String> listprawojazdy = new JList<>( model3 );
		
		int obecnaLiczbaBiletow = i.length;
		
		for(int x=0; x < obecnaLiczbaBiletow; x++) {
			
			// dodanie oczekujacych
			int bilety2 = i[x].Serving.length;
			
			for(int a=0; a < bilety2; a++)
			{
				if(i[x].Serving[a].category == "Rejestracja samochodów")
				{
					model3.addElement(String.valueOf(i[x].Waiting));
				}
				else if(i[x].Serving[a].category == "Wydawanie dowodów")
				{
					model.addElement(String.valueOf(i[x].Waiting));
				}
				else if(i[x].Serving[a].category == "Odbiór prawa jazdy")
				{
					model2.addElement(String.valueOf(i[x].Waiting));
				}
			}		
		}
	}

	// rozró¿nianie biletów
	public String CheckNumber(int a)
	{
		String number = null;
		
		if(a == 0)
		{
			number = "BRAK";
		}
		else
		{
			number = Integer.toString(a);	
		}
		return number;
	}
}
