/*Zadanie polega na zaprojektowaniu aplikacji, w której pewni aktorzy (dostawcy i kucharze) bêd¹ 
 * wykonywaæ dzia³ania na wspó³dzielonych zasobach (zasobniki przypraw i mieszacz).

Specyfikacja problemu do rozwi¹zania:
- istnieje "n" zasobników z przyprawami
- przyprawy w zasobnikach mo¿na uzupe³niaæ, jednak uzupe³nianie musi odbywaæ siê w sposób 
roz³¹czny z pobieraniem z zasobników
- przyprawy z zasobników mo¿na pobieraæ jedynie w postaci mieszanek za pomoc¹ mieszacza 
- mieszacz pozwala na równoczesne pobranie "m" mieszanek pod warunkiem, ¿e sk³adniki mieszanek s¹ ró¿ne
- przyprawy uzupe³niaj¹ "dostawcy"
- przyprawy pobieraj¹ "kucharze" */

package lab05;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.event.ActionEvent;

public class FrameMain extends Thread{

	private JFrame frame;
	private JTextField textFieldFlour;
	private JTextField textFieldMilk;
	private JTextField textFieldEggs;
	private JTextField textFieldWater;
	private JTextField textFieldCheese;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameMain window = new FrameMain();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	List<String> listOfIngredients = new ArrayList<String>();
	private JTextField textFieldThread;

	/**
	 * Create the application.
	 */
	public FrameMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 618, 524);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//model listy
		DefaultListModel model = new DefaultListModel();
		
		textFieldFlour = new JTextField("5");
		textFieldFlour.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textFieldFlour.setBounds(119, 11, 96, 41);
		frame.getContentPane().add(textFieldFlour);
		textFieldFlour.setColumns(10);
		
		textFieldMilk = new JTextField("5");
		textFieldMilk.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textFieldMilk.setColumns(10);
		textFieldMilk.setBounds(119, 83, 96, 41);
		frame.getContentPane().add(textFieldMilk);
		
		textFieldEggs = new JTextField("5");
		textFieldEggs.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textFieldEggs.setColumns(10);
		textFieldEggs.setBounds(119, 161, 96, 41);
		frame.getContentPane().add(textFieldEggs);
		
		textFieldWater = new JTextField("5");
		textFieldWater.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textFieldWater.setColumns(10);
		textFieldWater.setBounds(119, 239, 96, 41);
		frame.getContentPane().add(textFieldWater);
		
		textFieldCheese = new JTextField("5");
		textFieldCheese.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textFieldCheese.setColumns(10);
		textFieldCheese.setBounds(119, 324, 96, 41);
		frame.getContentPane().add(textFieldCheese);
		
		JLabel labelFlour = new JLabel("M\u0105ka");
		labelFlour.setFont(new Font("Century Gothic", Font.PLAIN, 22));
		labelFlour.setBounds(10, 11, 158, 47);
		frame.getContentPane().add(labelFlour);
		
		JLabel lblWybraneProdukty = new JLabel("Wybrane produkty");
		lblWybraneProdukty.setFont(new Font("Century Gothic", Font.PLAIN, 28));
		lblWybraneProdukty.setBounds(294, 11, 288, 76);
		frame.getContentPane().add(lblWybraneProdukty);
		
		JList list = new JList(model);
		list.setBounds(282, 98, 288, 197);
		frame.getContentPane().add(list);
		
		JButton buttonStart = new JButton("Rozpocznij");
		buttonStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Ingredients ingredients = new Ingredients();
				OperationsOnIngredients operations = new OperationsOnIngredients();
				
				listOfIngredients.add(ingredients.CheeseName);
				listOfIngredients.add(ingredients.EggsName);
				listOfIngredients.add(ingredients.WaterName);
				listOfIngredients.add(ingredients.MilkName);
				listOfIngredients.add(ingredients.FlourName);
				Random rand = new Random();
				
				new Thread () {
					public void run() {
						
						while(true) {

							int randNumber = rand.nextInt(5);
							String product = operations.RandIngredient(randNumber);
							model.addElement(product);
							int randNumber2 = rand.nextInt(4);
							
							
							// odejmowanie produktow z textboxow
							if(product == "M¹ka") {
								int num = Integer.parseInt(textFieldFlour.getText());
								num--;
								textFieldFlour.setText(String.valueOf(num));
							}
							else if(product == "Jajka") {
								int num = Integer.parseInt(textFieldEggs.getText());
								num--;
								textFieldEggs.setText(String.valueOf(num));
							}
							if(product == "Woda") {
								int num = Integer.parseInt(textFieldWater.getText());
								num--;
								textFieldWater.setText(String.valueOf(num));
							}
							if(product == "Mleko") {
								int num = Integer.parseInt(textFieldMilk.getText());
								num--;
								textFieldMilk.setText(String.valueOf(num));
							}
							if(product == "Ser") {
								int num = Integer.parseInt(textFieldCheese.getText());
								num--;
								textFieldCheese.setText(String.valueOf(num));
							}
							
							try {
								if(randNumber2 == 3) {
									JOptionPane.showMessageDialog(null, 
					                        "KONIEC W¥TKU KUCHARZA", 
					                        "Powiadomienie", 
					                        JOptionPane.WARNING_MESSAGE);
									Thread.currentThread().wait();
									}
								textFieldThread.setText("W¹tek Kucharz");
								Thread.sleep(2000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}.start();
				
					new Thread() {
						public void run() {
							// sychronizacja i watek uzupelniajacy liczbe produktow
							
							try {
								model.clear();
								list.setModel(model);
								textFieldThread.setText("W¹tek Dostawca");
								textFieldFlour.setText(String.valueOf(5));
								textFieldCheese.setText(String.valueOf(5));
								textFieldWater.setText(String.valueOf(5));
								textFieldMilk.setText(String.valueOf(5));
								textFieldEggs.setText(String.valueOf(5));
								Thread.currentThread().wait();
								Thread.currentThread().notify();
								
								JOptionPane.showMessageDialog(null, 
				                        "KONIEC W¥TKU DOSTAWCY", 
				                        "Powiadomienie", 
				                        JOptionPane.WARNING_MESSAGE);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}.start();
				}
		});
		
		buttonStart.setFont(new Font("Century Gothic", Font.PLAIN, 22));
		buttonStart.setBounds(282, 324, 288, 67);
		frame.getContentPane().add(buttonStart);
		
		JLabel labelMilk = new JLabel("Mleko");
		labelMilk.setFont(new Font("Century Gothic", Font.PLAIN, 22));
		labelMilk.setBounds(10, 88, 96, 27);
		frame.getContentPane().add(labelMilk);
		
		JLabel labelEggs = new JLabel("Jajka");
		labelEggs.setFont(new Font("Century Gothic", Font.PLAIN, 22));
		labelEggs.setBounds(10, 166, 96, 27);
		frame.getContentPane().add(labelEggs);
		
		JLabel labelWater = new JLabel("Woda");
		labelWater.setFont(new Font("Century Gothic", Font.PLAIN, 22));
		labelWater.setBounds(10, 244, 96, 27);
		frame.getContentPane().add(labelWater);
		
		JLabel labelCheese = new JLabel("Ser");
		labelCheese.setFont(new Font("Century Gothic", Font.PLAIN, 22));
		labelCheese.setBounds(13, 329, 96, 27);
		frame.getContentPane().add(labelCheese);
		
		textFieldThread = new JTextField();
		textFieldThread.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldThread.setBounds(13, 434, 202, 41);
		frame.getContentPane().add(textFieldThread);
		textFieldThread.setColumns(10);
	}
}
