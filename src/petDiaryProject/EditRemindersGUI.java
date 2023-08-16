package petDiaryProject;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class EditRemindersGUI extends JFrame {

	private JPanel contentPane;
	
	// title
	private JTextArea txtTitle;
	
	// vet & groomer text areas 
	private JTextArea txtVetVisit;
	private JTextArea txtGroomerVisit;
	private JTextField txtAnsVetVisit;
	private JTextField txtAnsGroomerVisit;
	
	// confirmation message
	private JLabel lblConfirmation;
	
	// confirm buttons
	private JButton btnConfirm;
	
	// back to diary buttons
	private JButton btnDiary;
	
	// user dog & date
	private String userDog;
	private String basicDate;

	// reminders file path
	private String remindersPath;
	
	public EditRemindersGUI(String dog, String date) {
		
		userDog = dog;
		basicDate = date;
		remindersPath = System.getProperty("user.dir") + "/" + dog + "/Reminders/reminders.txt";

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtTitle = new JTextArea();
		txtTitle.setFont(new Font("Monospaced", Font.BOLD, 20));
		txtTitle.setBounds(206, 41, 173, 34);
		txtTitle.setText("Edit Reminders");
		contentPane.add(txtTitle);
		
		txtAnsVetVisit = new JTextField();
		txtAnsVetVisit.setBounds(25, 139, 367, 34);
		contentPane.add(txtAnsVetVisit);
		txtAnsVetVisit.setColumns(10);
		
		txtAnsGroomerVisit = new JTextField();
		txtAnsGroomerVisit.setColumns(10);
		txtAnsGroomerVisit.setBounds(25, 246, 367, 34);
		contentPane.add(txtAnsGroomerVisit);
		
		txtVetVisit = new JTextArea();
		txtVetVisit.setFont(new Font("Monospaced", Font.PLAIN, 18));
		txtVetVisit.setText("Enter New Vet Visit Date (ex. 20230819):");
		txtVetVisit.setBounds(25, 95, 600, 34);
		contentPane.add(txtVetVisit);
		
		txtGroomerVisit = new JTextArea();
		txtGroomerVisit.setFont(new Font("Monospaced", Font.PLAIN, 18));
		txtGroomerVisit.setText("Enter New Groomer Visit Date (ex. 20231024):");
		txtGroomerVisit.setBounds(25, 202, 600, 34);
		contentPane.add(txtGroomerVisit);
		
		lblConfirmation = new JLabel("");
		lblConfirmation.setForeground(new Color(255, 99, 71));
		lblConfirmation.setFont(new Font("Monospaced", Font.PLAIN, 18));
		lblConfirmation.setBounds(25, 321, 532, 34);
		contentPane.add(lblConfirmation);
		
		btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnConfirmClick();
			}
		});
		btnConfirm.setForeground(Color.BLACK);
		btnConfirm.setFont(new Font("Monospaced", Font.PLAIN, 18));
		btnConfirm.setBorderPainted(false);
		btnConfirm.setBackground(new Color(250, 235, 215));
		btnConfirm.setOpaque(true);
		btnConfirm.setBounds(25, 376, 236, 33);
		contentPane.add(btnConfirm);
		
		btnDiary = new JButton("Diary");
		btnDiary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDiaryClick();
			}
		});
		btnDiary.setForeground(Color.BLACK);
		btnDiary.setFont(new Font("Monospaced", Font.PLAIN, 18));
		btnDiary.setBorderPainted(false);
		btnDiary.setBackground(new Color(250, 235, 215));
		btnDiary.setOpaque(true);
		btnDiary.setBounds(321, 376, 236, 33);
		contentPane.add(btnDiary);
	}
	
	void btnDiaryClick() {
		dispose();
		DiaryGUI diaryFrame = new DiaryGUI(userDog, basicDate);
		diaryFrame.show();
	}
	
	void btnConfirmClick() {
		try {
			String previousVet = getVetVisit(userDog, basicDate);
			String previousGroomer = getGroomerVisit(userDog, basicDate);
			
			String currentVet = txtAnsVetVisit.getText();
			String currentGroomer = txtAnsGroomerVisit.getText();
			
			FileWriter remindersOutput = new FileWriter(remindersPath);
			
			remindersOutput.write("Next vet visit" + "\n");
			if (currentVet.equals(""))
				remindersOutput.write(previousVet + "\n");
			else
				remindersOutput.write(currentVet + "\n");
			
			remindersOutput.write("Next groomer visit" + "\n");
			if (currentGroomer.equals(""))
				remindersOutput.write(previousGroomer + "\n");
			else
				remindersOutput.write(currentGroomer + "\n");
			
			remindersOutput.close();
			
			lblConfirmation.setText("Date successfully changed.");
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	String getVetVisit(String dog, String date) {
		File remindersFile = new File(remindersPath);
		String vetVisit = "";
		try {
			Scanner remindersScanner = new Scanner(remindersFile);
			while (remindersScanner.hasNextLine()) {
				String currentLine = remindersScanner.nextLine();
				if (currentLine.equals("Next vet visit")) {
					vetVisit = remindersScanner.nextLine();
					break;
				} remindersScanner.nextLine();
			}
			remindersScanner.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vetVisit;
	}
	
	String getGroomerVisit(String dog, String date) {
		File remindersFile = new File(remindersPath);
		String groomerVisit = "";
		try {
			Scanner remindersScanner = new Scanner(remindersFile);
			while (remindersScanner.hasNextLine()) {
				String currentLine = remindersScanner.nextLine();
				if (currentLine.equals("Next groomer visit")) {
					groomerVisit = remindersScanner.nextLine();
					break;
				} remindersScanner.nextLine();
			}
			remindersScanner.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return groomerVisit;
	}
	
}
