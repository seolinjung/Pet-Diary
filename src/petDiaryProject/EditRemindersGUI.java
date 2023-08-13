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
	private JButton btnConfirm1;
	private JButton btnConfirm2;
	
	// back to main, back to diary buttons
	private JButton btnMain;
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
		setBounds(100, 100, 600, 800);
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
		txtVetVisit.setText("Enter New Vet Visit Date:");
		txtVetVisit.setBounds(25, 95, 339, 34);
		contentPane.add(txtVetVisit);
		
		txtGroomerVisit = new JTextArea();
		txtGroomerVisit.setFont(new Font("Monospaced", Font.PLAIN, 18));
		txtGroomerVisit.setText("Enter New Groomer Visit Date:");
		txtGroomerVisit.setBounds(25, 202, 384, 34);
		contentPane.add(txtGroomerVisit);
		
		lblConfirmation = new JLabel("");
		lblConfirmation.setForeground(new Color(255, 99, 71));
		lblConfirmation.setFont(new Font("Monospaced", Font.PLAIN, 18));
		lblConfirmation.setBounds(25, 321, 532, 34);
		contentPane.add(lblConfirmation);
		
		btnConfirm1 = new JButton("Confirm");
		btnConfirm1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userAnswer = txtAnsVetVisit.getText();
				btnConfirm1Click(userAnswer);
			}
		});
		btnConfirm1.setForeground(Color.BLACK);
		btnConfirm1.setFont(new Font("Monospaced", Font.PLAIN, 18));
		btnConfirm1.setBorderPainted(false);
		btnConfirm1.setBackground(new Color(250, 235, 215));
		btnConfirm1.setBounds(414, 140, 143, 33);
		contentPane.add(btnConfirm1);
		
		btnConfirm2 = new JButton("Confirm");
		btnConfirm2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userAnswer = txtAnsGroomerVisit.getText();
				btnConfirm2Click(userAnswer);
			}
		});
		btnConfirm2.setForeground(Color.BLACK);
		btnConfirm2.setFont(new Font("Monospaced", Font.PLAIN, 18));
		btnConfirm2.setBorderPainted(false);
		btnConfirm2.setBackground(new Color(250, 235, 215));
		btnConfirm2.setBounds(414, 247, 143, 33);
		contentPane.add(btnConfirm2);
		
		btnMain = new JButton("Main");
		btnMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnMainClick();
			}
		});
		btnMain.setForeground(Color.BLACK);
		btnMain.setFont(new Font("Monospaced", Font.PLAIN, 18));
		btnMain.setBorderPainted(false);
		btnMain.setBackground(new Color(250, 235, 215));
		btnMain.setBounds(25, 376, 236, 33);
		contentPane.add(btnMain);
		
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
		btnDiary.setBounds(321, 376, 236, 33);
		contentPane.add(btnDiary);
	}
	
	void btnMainClick() {
		dispose();
		MainGUI mainFrame = new MainGUI();
		mainFrame.show();
	}
	
	void btnDiaryClick() {
		dispose();
		DiaryGUI diaryFrame = new DiaryGUI(userDog, basicDate);
		diaryFrame.show();
	}
	
	void btnConfirm1Click(String userAnswer) {
		try {
			FileWriter remindersOutput = new FileWriter(remindersPath);
			remindersOutput.write("Next vet visit" + "\n");
			remindersOutput.write(userAnswer + "\n");
			remindersOutput.write("Next groomer visit" + "\n");
			remindersOutput.write(getVetVisit(userDog, basicDate) + "\n");
			remindersOutput.close();
			lblConfirmation.setText("Date successfully changed.");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	void btnConfirm2Click(String userAnswer) {
		try {
			FileWriter remindersOutput = new FileWriter(remindersPath);
			remindersOutput.write("Next vet visit" + "\n");
			remindersOutput.write(getVetVisit(userDog, basicDate) + "\n");
			remindersOutput.write("Next groomer visit" + "\n");
			remindersOutput.write(userAnswer + "\n");
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
