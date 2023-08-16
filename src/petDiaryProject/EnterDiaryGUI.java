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
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JRadioButton;

public class EnterDiaryGUI extends JFrame {

	private JPanel contentPane;

	private JTextField txtAnswer;
	private JTextArea txtEnterName;
	private JButton btnConfirm;
	private JLabel lblConfirmation;
	private JButton btnMain;

	public EnterDiaryGUI() {
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
		txtAnswer.setBounds(276, 31, 193, 29);
		contentPane.add(txtAnswer);
		txtAnswer.setColumns(10);
		
		txtEnterName = new JTextArea();
		txtEnterName.setEditable(false);
		txtEnterName.setFont(new Font("Monospaced", Font.PLAIN, 18));
		txtEnterName.setText("Open diary of:");
		txtEnterName.setBounds(104, 31, 162, 29);
		contentPane.add(txtEnterName);
		
		btnConfirm = new JButton("Confirm");
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
		lblConfirmation.setBounds(104, 141, 429, 29);
		contentPane.add(lblConfirmation);
		
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
		btnMain.setBounds(292, 85, 143, 33);
		contentPane.add(btnMain);
		
	}
	
	void btnConfirmClick(String userAnswer) {
		
		try {
			FileReader reader = new FileReader("petList.txt");
			BufferedReader input = new BufferedReader(reader);	
			String currentLine = input.readLine();

			boolean found = false;
			
			while (currentLine != null) {
				if (userAnswer.equals(currentLine)) {
					found = true;
					break;
				}
				currentLine = input.readLine();
			}
			
			input.close();
			
			if (found == true) {
				dispose();
				LocalDate localDate = LocalDate.now();
				// this monster returns a date in the format 20230813Z.
				String basicDateWithZ = ZonedDateTime.now( ZoneOffset.UTC ).format(DateTimeFormatter.BASIC_ISO_DATE);
				// so let's slice it, to make it look like 20230813.
				String basicDate = basicDateWithZ.substring(0,8);
				DiaryGUI diaryFrame = new DiaryGUI(userAnswer, basicDate);
				diaryFrame.show();
			}
			
			else {
				lblConfirmation.setText("Could not find name. Try again?");
			}
			
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
