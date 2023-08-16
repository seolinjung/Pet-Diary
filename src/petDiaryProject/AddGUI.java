package petDiaryProject;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;

public class AddGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtAnswer;
	private JTextArea txtEnterName;
	private JButton btnMain;
	private JButton btnConfirm;
	private JLabel lblConfirmation;

	public AddGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 250);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtAnswer = new JTextField();
		txtAnswer.setFont(new Font("Monospaced", Font.PLAIN, 18));
		txtAnswer.setBorder(new LineBorder(new Color(171, 173, 179)));
		txtAnswer.setBounds(284, 31, 209, 29);
		contentPane.add(txtAnswer);
		txtAnswer.setColumns(10);
		
		txtEnterName = new JTextArea();
		txtEnterName.setFont(new Font("Monospaced", Font.PLAIN, 18));
		txtEnterName.setText("Enter name to add:");
		txtEnterName.setBounds(65, 28, 198, 29);
		contentPane.add(txtEnterName);
		
		btnMain = new JButton("Main");
		btnMain.setBorderPainted(false);
		btnMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnMainClick();
			}
		});
		
		btnMain.setForeground(Color.BLACK);
		btnMain.setFont(new Font("Monospaced", Font.PLAIN, 18));
		btnMain.setBackground(new Color(250, 235, 215));
		btnMain.setBounds(292, 85, 143, 33);
		contentPane.add(btnMain);
		
		btnConfirm = new JButton("Confirm");
		btnConfirm.setBorder(null);
		btnConfirm.setBorderPainted(false);
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userAnswer = txtAnswer.getText();
				btnConfirmClick(userAnswer);
			}
		});
		
		btnConfirm.setForeground(Color.BLACK);
		btnConfirm.setFont(new Font("Monospaced", Font.PLAIN, 18));
		btnConfirm.setBackground(new Color(250, 235, 215));
		btnConfirm.setBounds(138, 85, 143, 33);
		contentPane.add(btnConfirm);

		
		lblConfirmation = new JLabel("");
		lblConfirmation.setForeground(new Color(255, 99, 71));
		lblConfirmation.setFont(new Font("Monospaced", Font.PLAIN, 18));
		lblConfirmation.setBounds(65, 140, 429, 29);
		contentPane.add(lblConfirmation);
		
	}
	
	
	
	void btnConfirmClick(String userAnswer) {
		try {

			String currentDir = System.getProperty("user.dir");
			
			String thisDir = currentDir + "/" + userAnswer;
			String entriesDir = currentDir + "/" + userAnswer + "/Entries";
			String infoDir = currentDir + "/" + userAnswer + "/Info";
			String notifsDir = currentDir + "/" + userAnswer + "/Notifications";
			String remindersDir = currentDir + "/" + userAnswer + "/Reminders";
			
			File thisFile = new File(thisDir);
			File entriesFile = new File(entriesDir);
			File infoFile = new File(infoDir);
			File notifsFile = new File(notifsDir);
			File remindersFile = new File(remindersDir);
			
			thisFile.mkdirs();
			entriesFile.mkdirs();
			infoFile.mkdirs();
			notifsFile.mkdirs();
			remindersFile.mkdirs();
			
			FileWriter infoOutput = new FileWriter(infoDir + "/info.txt");
			infoOutput.write("Age:"+ "\n\n");
			infoOutput.write("M/F:"+ "\n\n");
			infoOutput.write("Breed:"+ "\n\n");
			infoOutput.write("Weight:"+ "\n\n");
			infoOutput.write("Meal specifications:"+ "\n\n");
			infoOutput.write("Medicine:"+ "\n\n");
			infoOutput.close();
			
			FileWriter remindersOutput = new FileWriter(remindersDir + "/reminders.txt");
			remindersOutput.write("Next vet visit" + "\n\n");
			remindersOutput.write("Next groomer visit" + "\n\n");
			remindersOutput.close();

			FileWriter listOutput = new FileWriter("petList.txt", true);
			listOutput.write(userAnswer + "\n");
			listOutput.close();
			lblConfirmation.setText("Name successfully added.");
			
		} catch (Exception e1) {
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

