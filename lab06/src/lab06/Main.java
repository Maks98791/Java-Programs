/* istnieje aplikacja centralna komunikuj¹ca siê z innymi aplikacjami poprzez gniazda TCP/IP 
 * (klasy Socket, ServerSocket)
- metody udostêpnione w interfejsie tej aplikacji powininny pozwalaæ na zg³aszanie nowych zadañ, 
przegl¹danie zadañ istniej¹cych, pobieranie zadañ do wykonania i przes³anie wyników
- choæ kodowanie przesy³anej informacji mo¿e byæ dowolne, to jednak zalecane jest kodowanie 
tekstowe (np. ci¹g znaków: "+;20;40" mo¿e oznaczaæ zadanie sumowania liczb)
- wyklucza siê serializacjê obiektów (metody writeObject lub readObject nie powinny byæ stosowane)
- z³o¿onoœæ zadañ obliczeniowych nie musi byæ wysoka (chodzi raczej o przetestowanie pomys³u ni¿ 
o wykonanie przemys³owego wdro¿enia, na zajêciach zostanie to doprecyzowane)
- opis zadañ mo¿na rozszerzyæ (o priorytety, kategorie, adresata itp.)
- aplikacje wykonuj¹ce zadania obliczeniowe to procesy dzia³aj¹ce wspó³bie¿nie (mog¹ to byæ
 aplikacje uruchamiane na tym samym komputerze co aplikacja centralna lub na innych komputerach)
- nale¿y zastanowiæ siê nad sposobem przekazywania/sk³adowania wyników obliczeñ*/
package lab06;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.event.ActionEvent;

public class Main extends Thread{

	private JFrame frame;
	private JTextField textFieldPhrase;
	private JTextField textFieldResult;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 734, 472);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblWyraenieDoObliczenia = new JLabel("Wyra\u017Cenie do obliczenia:");
		lblWyraenieDoObliczenia.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblWyraenieDoObliczenia.setBounds(10, 223, 246, 42);
		frame.getContentPane().add(lblWyraenieDoObliczenia);
		
		textFieldPhrase = new JTextField();
		textFieldPhrase.setFont(new Font("Tahoma", Font.PLAIN, 22));
		textFieldPhrase.setBounds(10, 275, 246, 42);
		frame.getContentPane().add(textFieldPhrase);
		textFieldPhrase.setColumns(10);
		
		JButton buttonCompute = new JButton("Oblicz");
		buttonCompute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Parse server = new Parse();
				String mark = server.SplitExpression(textFieldPhrase.getText());
				int port = server.GetPort(mark);
				
				new Thread() {
					public void run() {
						try {
							ServerSocket serverSocket = new ServerSocket(port);
							Socket clientSocket = serverSocket.accept();
							
							JOptionPane.showMessageDialog(null, "Uda³o siê po³¹czyæ z klientem", "Powiadomienie", JOptionPane.WARNING_MESSAGE);
						
							PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
							out.println(textFieldPhrase.getText());
							
							BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
							String line = in.readLine();
							textFieldResult.setText(line);
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}.start();
			}
		});
		buttonCompute.setFont(new Font("Tahoma", Font.PLAIN, 22));
		buttonCompute.setBounds(10, 338, 246, 56);
		frame.getContentPane().add(buttonCompute);
		
		JLabel lblWynikWyraenia = new JLabel("Wynik wyra\u017Cenia:");
		lblWynikWyraenia.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblWynikWyraenia.setBounds(464, 223, 246, 42);
		frame.getContentPane().add(lblWynikWyraenia);
		
		textFieldResult = new JTextField();
		textFieldResult.setFont(new Font("Tahoma", Font.PLAIN, 22));
		textFieldResult.setColumns(10);
		textFieldResult.setBounds(464, 275, 246, 42);
		frame.getContentPane().add(textFieldResult);
		
		JLabel lblDostpneObliczenia = new JLabel("Dost\u0119pne obliczenia:");
		lblDostpneObliczenia.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblDostpneObliczenia.setBounds(10, 0, 246, 42);
		frame.getContentPane().add(lblDostpneObliczenia);
		
		JLabel lblDodawanie = new JLabel("-> Dodawanie \"+\"");
		lblDodawanie.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDodawanie.setBounds(10, 48, 246, 42);
		frame.getContentPane().add(lblDodawanie);
		
		JLabel lblOdejmowanie = new JLabel("-> Odejmowanie \"-\"");
		lblOdejmowanie.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblOdejmowanie.setBounds(10, 95, 246, 42);
		frame.getContentPane().add(lblOdejmowanie);
		
		JLabel lblMnoenia = new JLabel("-> Mno\u017Cenie \"*\"");
		lblMnoenia.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMnoenia.setBounds(10, 142, 246, 42);
		frame.getContentPane().add(lblMnoenia);
		
		JLabel lblFormua = new JLabel("Formu\u0142a:");
		lblFormua.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblFormua.setBounds(252, 0, 246, 42);
		frame.getContentPane().add(lblFormua);
		
		JLabel lblOperacja = new JLabel("operacja;liczba1;liczba2");
		lblOperacja.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblOperacja.setBounds(252, 48, 211, 42);
		frame.getContentPane().add(lblOperacja);
		
		JLabel lblNp = new JLabel("np.    +;20;10");
		lblNp.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNp.setBounds(252, 95, 246, 42);
		frame.getContentPane().add(lblNp);
		
		JLabel label = new JLabel("->     20+10=");
		label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label.setBounds(252, 142, 246, 42);
		frame.getContentPane().add(label);
	}
}
