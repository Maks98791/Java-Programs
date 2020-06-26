package lab07;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class xd extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					xd frame = new xd();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public xd() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNastpny = new JButton("Nast\u0119pny");
		btnNastpny.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
}
