/*Zadanie polega na zaprojektowaniu aplikacji pe³ni¹cej rolê dziennika lekcyjnego widzianego z 
 * perspektywy nauczycieli (inne perspektywy nie s¹ wymagane). Do interakcji z aplikacj¹ maj¹ byæ 
 * u¿yte formularze (okna dialogowe z polami tekstowymi, zak³adkami, tabelami itp.).Podobnie jak do 
 * zadañ poprzednich zak³adamy, ¿e zostan¹ wykorzystane dostêpne struktury danych.
Aplikacja powinna obs³u¿yæ nastêpuj¹ce zestawy danych:
- lista uczniów (wystarczy, ¿e bêd¹ to uczniowie jednej klasy)
- lista przedmiotów
- oceny (na jednym przedmiocie dany uczeñ mo¿e otrzymaæ wiele ocen, przy czym wagi ocen mo¿na
 pomin¹æ)
- frekwencja
Aplikacja powinna umo¿liwiaæ:
- zarz¹dzanie list¹ uczniów (dopisanie do listy, usuniêcie z listy)
- zarz¹dzanie ocenami (wystawianie i anulowanie ocen w ramach przedmiotów)
- zarz¹dzanie obecnoœciami (nieobecny, usprawiedliwiony)
- generowanie zestawieñ (œrednia ocen danego ucznia, œrednia ocen klasy)
*/
package lab04;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class Frame1 {

	private JFrame frame;
	private JTextField textFieldName;
	private JTextField textFieldSurname;
	private JTextField textField_3;
	private JTextField textField_5;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_13;
	private JTextField textField_14;
	
	List<Student> listaUczniow = new ArrayList<Student>();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {	
			public void run() {
				try {
					Frame1 window = new Frame1();
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
	public Frame1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 910, 582);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 874, 522);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panelUczniowie = new JPanel();
		tabbedPane.addTab("Uczniowie", null, panelUczniowie, null);
		panelUczniowie.setLayout(null);
		
		DefaultListModel<String> model = new DefaultListModel<>();
		DefaultListModel<Byte> model2 = new DefaultListModel<>();
		DefaultListModel<String> model3 = new DefaultListModel<>();
		JList<String> listName = new JList(model);
		listName.setBounds(10, 11, 499, 472);
		panelUczniowie.add(listName);
		
		for(Student stud : listaUczniow) {
			model.addElement(stud.name + " " + stud.surname);
		}
		
		JButton btnUsu = new JButton("Usu\u0144");
		btnUsu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String selectedStudent = listName.getSelectedValue();
				int i=0;
				for(Student stud : listaUczniow) {
					if((stud.name + " " + stud.surname).equals(selectedStudent)) {
						listName.remove(i);
					}
					i++;
				}
				int selectedIndex = listName.getSelectedIndex();
				if (selectedIndex != -1) {
				    model.remove(selectedIndex);
				}
			}
		});
		btnUsu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnUsu.setBounds(708, 367, 151, 42);
		panelUczniowie.add(btnUsu);
		
		JButton btnDodaj = new JButton("Dodaj");
		btnDodaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Student nowy = new Student(textFieldName.getText(), textFieldSurname.getText());
				listaUczniow.add(nowy);
				model.addElement(textFieldName.getText() + " " + textFieldSurname.getText());
			}
		});
		btnDodaj.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDodaj.setBounds(546, 367, 151, 42);
		panelUczniowie.add(btnDodaj);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(633, 198, 187, 42);
		panelUczniowie.add(textFieldName);
		textFieldName.setColumns(10);
		
		textFieldSurname = new JTextField();
		textFieldSurname.setColumns(10);
		textFieldSurname.setBounds(633, 251, 187, 42);
		panelUczniowie.add(textFieldSurname);
		
		JLabel lblImi = new JLabel("Imi\u0119:");
		lblImi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblImi.setBounds(546, 210, 76, 14);
		panelUczniowie.add(lblImi);
		
		JLabel lblNazwisko = new JLabel("Nazwisko:");
		lblNazwisko.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNazwisko.setBounds(546, 263, 76, 14);
		panelUczniowie.add(lblNazwisko);
		
		JLabel lblNewLabel = new JLabel("Wpisz dane ucznia");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(633, 117, 171, 48);
		panelUczniowie.add(lblNewLabel);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Wychowanie fizyczne", null, panel_3, null);
		panel_3.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBounds(0, 0, 869, 494);
		panel_3.add(panel_2);
		
		JList<String> list_1 = new JList(model);
		list_1.setBounds(10, 11, 217, 472);
		panel_2.add(list_1);
		
		JButton button_3 = new JButton("Dodaj");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String selectedStudent = list_1.getSelectedValue();
				int i=0;
				for(Student stud : listaUczniow) {
					if((stud.name + " " + stud.surname).equals(selectedStudent)) {
						stud.mark.add(Byte.parseByte(textField_9.getText()));
						model2.addElement(Byte.parseByte(textField_9.getText()));
						stud.present = textField_5.getText();
						model3.addElement(textField_5.getText());
					}
					i++;
				}
			}
		});
		button_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		button_3.setBounds(651, 386, 151, 42);
		panel_2.add(button_3);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(633, 251, 187, 42);
		panel_2.add(textField_5);
		
		JLabel lblWybierzUczniaI = new JLabel("Wybierz ucznia i dodaj dane");
		lblWybierzUczniaI.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblWybierzUczniaI.setBounds(585, 128, 274, 48);
		panel_2.add(lblWybierzUczniaI);
		
		JLabel lblOcena_1 = new JLabel("Ocena:");
		lblOcena_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblOcena_1.setBounds(546, 320, 76, 14);
		panel_2.add(lblOcena_1);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(633, 304, 187, 42);
		panel_2.add(textField_9);
		
		JLabel lblObecno = new JLabel("Obecno\u015B\u0107:");
		lblObecno.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblObecno.setBounds(546, 263, 76, 14);
		panel_2.add(lblObecno);
		
		JList<Byte> list = new JList(model2);
		list.setBounds(237, 11, 186, 472);
		panel_2.add(list);
		
		JList<String> list_5 = new JList(model3);
		list_5.setBounds(433, 11, 103, 472);
		panel_2.add(list_5);
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Matematyka", null, panel_4, null);
		panel_4.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 869, 494);
		panel_4.add(panel);
		
		JList<String> list_2 = new JList(model);
		list_2.setBounds(10, 11, 256, 472);
		panel.add(list_2);
		
		JButton button_1 = new JButton("Dodaj");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String selectedStudent = list_2.getSelectedValue();
				int i=0;
				for(Student stud : listaUczniow) {
					if((stud.name + " " + stud.surname).equals(selectedStudent)) {
						stud.mark.add(Byte.parseByte(textField_10.getText()));
						model2.addElement(Byte.parseByte(textField_10.getText()));
						stud.present = textField_3.getText();
						model3.addElement(textField_3.getText());
					}
					i++;
				}
			}
		});
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		button_1.setBounds(654, 382, 151, 42);
		panel.add(button_1);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(633, 251, 187, 42);
		panel.add(textField_3);
		
		JLabel lblWybierzUczniaI_1 = new JLabel("Wybierz ucznia i dodaj dane");
		lblWybierzUczniaI_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblWybierzUczniaI_1.setBounds(578, 126, 256, 48);
		panel.add(lblWybierzUczniaI_1);
		
		textField_10 = new JTextField();
		textField_10.setColumns(10);
		textField_10.setBounds(633, 304, 187, 42);
		panel.add(textField_10);
		
		JLabel lblOcena_2 = new JLabel("Ocena:");
		lblOcena_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblOcena_2.setBounds(546, 318, 76, 14);
		panel.add(lblOcena_2);
		
		JLabel lblObecno_1 = new JLabel("Obecno\u015B\u0107:");
		lblObecno_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblObecno_1.setBounds(546, 263, 76, 14);
		panel.add(lblObecno_1);
		
		JList<Byte> list_6 = new JList(model2);
		list_6.setBounds(276, 11, 151, 472);
		panel.add(list_6);
		
		JList<String> list_7 = new JList(model3);
		list_7.setBounds(437, 11, 99, 472);
		panel.add(list_7);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Jêzyk polski", null, panel_1, null);
		panel_1.setLayout(null);
		
		JPanel panel_5 = new JPanel();
		panel_5.setLayout(null);
		panel_5.setBounds(0, 0, 869, 494);
		panel_1.add(panel_5);
		
		JList<String> list_3 = new JList(model);
		list_3.setBounds(10, 11, 215, 472);
		panel_5.add(list_3);
		
		JButton button_5 = new JButton("Dodaj");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String selectedStudent = list_3.getSelectedValue();
				int i=0;
				for(Student stud : listaUczniow) {
					if((stud.name + " " + stud.surname).equals(selectedStudent)) {
						stud.mark.add(Byte.parseByte(textField_8.getText()));
						model2.addElement(Byte.parseByte(textField_8.getText()));
						stud.present = textField_7.getText();
						model3.addElement(textField_7.getText());
					}
					i++;
				}
			}
		});
		button_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		button_5.setBounds(649, 382, 151, 42);
		panel_5.add(button_5);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(633, 251, 187, 42);
		panel_5.add(textField_7);
		
		JLabel lblWybierzUczniaI_2 = new JLabel("Wybierz ucznia i dodaj dane");
		lblWybierzUczniaI_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblWybierzUczniaI_2.setBounds(568, 142, 266, 48);
		panel_5.add(lblWybierzUczniaI_2);
		
		JLabel lblOcena = new JLabel("Ocena:");
		lblOcena.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblOcena.setBounds(546, 316, 76, 14);
		panel_5.add(lblOcena);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(633, 304, 187, 42);
		panel_5.add(textField_8);
		
		JLabel lblObecno_2 = new JLabel("Obecno\u015B\u0107:");
		lblObecno_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblObecno_2.setBounds(546, 265, 76, 14);
		panel_5.add(lblObecno_2);
		
		JList<Byte> list_8 = new JList(model2);
		list_8.setBounds(235, 11, 171, 472);
		panel_5.add(list_8);
		
		JList<String> list_9 = new JList(model3);
		list_9.setBounds(417, 11, 119, 472);
		panel_5.add(list_9);
		
		JPanel panel_6 = new JPanel();
		tabbedPane.addTab("Zestawienie", null, panel_6, null);
		panel_6.setLayout(null);
		
		JPanel panel_7 = new JPanel();
		panel_7.setLayout(null);
		panel_7.setBounds(0, 0, 869, 494);
		panel_6.add(panel_7);
		
		JList<String> list_4 = new JList(model);
		list_4.setBounds(10, 11, 492, 472);
		panel_7.add(list_4);
		
		JLabel lblrednia = new JLabel("\u015Arednia ucznia:");
		lblrednia.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblrednia.setBounds(546, 219, 126, 14);
		panel_7.add(lblrednia);
		
		textField_13 = new JTextField();
		textField_13.setColumns(10);
		textField_13.setBounds(682, 207, 144, 42);
		panel_7.add(textField_13);
		
		JLabel lblUczeNaTle = new JLabel("Ucze\u0144 na tle klasy");
		lblUczeNaTle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUczeNaTle.setBounds(613, 65, 196, 48);
		panel_7.add(lblUczeNaTle);
		
		textField_14 = new JTextField();
		textField_14.setColumns(10);
		textField_14.setBounds(682, 283, 144, 42);
		panel_7.add(textField_14);
		
		JLabel lblredniaKlasy = new JLabel("\u015Arednia klasy:");
		lblredniaKlasy.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblredniaKlasy.setBounds(546, 295, 126, 14);
		panel_7.add(lblredniaKlasy);
		
		JButton btnZaadujDane = new JButton("Za\u0142aduj dane");
		btnZaadujDane.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String selectedStudent = list_4.getSelectedValue();
				int sredniaUczniow = 0;
				int sredniaUcznia = 0;
				int liczbaOcenUczniow = 0;
				for(Student stud : listaUczniow) {
					if((stud.name + " " + stud.surname).equals(selectedStudent)) {
						for(int ocena : stud.mark) {
							sredniaUcznia += ocena;
						}
						sredniaUcznia /= stud.mark.size();
					}
					else {
						for(int ocena : stud.mark) {
							sredniaUczniow += ocena;
							liczbaOcenUczniow++;
						}
					}
				}
				sredniaUczniow /= liczbaOcenUczniow;
				
				textField_13.setText(Integer.toString(sredniaUcznia));
				textField_14.setText(Integer.toString(sredniaUczniow));
			}
		});
		btnZaadujDane.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnZaadujDane.setBounds(682, 372, 144, 42);
		panel_7.add(btnZaadujDane);
	}
}
