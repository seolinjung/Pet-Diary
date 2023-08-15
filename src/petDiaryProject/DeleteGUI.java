package petDiaryProject;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class DeleteGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtAnswer;
	private JTextArea txtEnterName;
	private JButton btnMain;
	private JButton btnConfirm;
	private JLabel lblConfirmation;

	public DeleteGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 250);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtAnswer = new JTextField();
		txtAnswer.setBorder(new LineBorder(new Color(171, 173, 179)));
		txtAnswer.setBounds(316, 31, 209, 29);
		contentPane.add(txtAnswer);
		txtAnswer.setColumns(10);
		
		txtEnterName = new JTextArea();
		txtEnterName.setFont(new Font("Monospaced", Font.PLAIN, 18));
		txtEnterName.setText("Enter name to delete:");
		txtEnterName.setBounds(65, 28, 241, 29);
		contentPane.add(txtEnterName);
		
		btnMain = new JButton("Main");
		btnMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnMainClick();
			}
		});
		
		btnMain.setOpaque(false);
		btnMain.setForeground(Color.BLACK);
		btnMain.setFont(new Font("Monospaced", Font.PLAIN, 18));
		btnMain.setBackground(Color.WHITE);
		btnMain.setBounds(292, 85, 143, 33);
		contentPane.add(btnMain);
		
		btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userAnswer = txtAnswer.getText();
				btnConfirmClick(userAnswer);
			}
		});
		btnConfirm.setOpaque(false);
		btnConfirm.setForeground(Color.BLACK);
		btnConfirm.setFont(new Font("Monospaced", Font.PLAIN, 18));
		btnConfirm.setBackground(Color.WHITE);
		btnConfirm.setBounds(139, 85, 143, 33);
		contentPane.add(btnConfirm);
		
		lblConfirmation = new JLabel("");
		lblConfirmation.setFont(new Font("Monospaced", Font.PLAIN, 18));
		lblConfirmation.setBounds(65, 140, 429, 29);
		contentPane.add(lblConfirmation);
		
	}
	
	void btnConfirmClick(String userAnswer) {

		try {
			File pets = new File("petList.txt");
			Scanner scan = new Scanner(pets);
			
			String currentPet;
			String list = "";
			
			while (scan.hasNextLine()) {
				currentPet = scan.nextLine();

				if (userAnswer.equals(currentPet) == false) {
					list += currentPet + "\n";
				}
			}
			
			FileWriter writer = new FileWriter(pets, false);
			writer.write(list);

			writer.close();
			
			lblConfirmation.setText("Name successfully deleted.");
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	void btnMainClick() {
		dispose();
		MainGUI mainFrame = new MainGUI();
		mainFrame.show();
	}
}
