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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class EditInfoGUI extends JFrame {

	private JPanel contentPane;
	
	private JTextArea txtTitle;
	
	private JTextArea txtAge;
	private JTextArea txtMF;
	private JTextArea txtBreed;
	private JTextArea txtWeight;
	private JTextArea txtMeals;
	private JTextArea txtMeds;
	private JTextField txtAnsAge;
	private JTextField txtAnsMF;
	private JTextField txtAnsBreed;
	private JTextField txtAnsWeight;
	private JTextField txtAnsMeals;
	private JTextField txtAnsMeds;	
	
	private JButton btnSave;
	private JButton btnDiary;
	
	private JLabel lblConfirmation;
	
	private String userDog;
	private String basicDate;
	private String infoPath;
	
	public EditInfoGUI(String dog, String date) {
		
		userDog = dog;
		basicDate = date;
		infoPath = System.getProperty("user.dir") + "/" + dog + "/Info/info.txt";
		
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
		txtTitle.setText("Edit Pet Info");
		contentPane.add(txtTitle);
		
		txtAge = new JTextArea();
		txtAge.setFont(new Font("Monospaced", Font.PLAIN, 18));
		txtAge.setText("Enter Age:");
		txtAge.setBounds(25, 100, 141, 34);
		contentPane.add(txtAge);
		
		txtMF = new JTextArea();
		txtMF.setFont(new Font("Monospaced", Font.PLAIN, 18));
		txtMF.setText("Enter Sex (M/F):");
		txtMF.setBounds(25, 150, 200, 34);
		contentPane.add(txtMF);
		
		txtBreed = new JTextArea();
		txtBreed.setFont(new Font("Monospaced", Font.PLAIN, 18));
		txtBreed.setText("Enter Breed:");
		txtBreed.setBounds(25, 200, 200, 34);
		contentPane.add(txtBreed);
		
		txtWeight = new JTextArea();
		txtWeight.setFont(new Font("Monospaced", Font.PLAIN, 18));
		txtWeight.setText("Enter Weight:");
		txtWeight.setBounds(25, 250, 200, 34);
		contentPane.add(txtWeight);
		
		txtMeals = new JTextArea();
		txtMeals.setFont(new Font("Monospaced", Font.PLAIN, 18));
		txtMeals.setText("Enter Meal Specifications:");
		txtMeals.setBounds(25, 300, 291, 34);
		contentPane.add(txtMeals);
		
		txtMeds = new JTextArea();
		txtMeds.setFont(new Font("Monospaced", Font.PLAIN, 18));
		txtMeds.setText("Enter Meds Specifications:");
		txtMeds.setBounds(25, 394, 291, 34);
		contentPane.add(txtMeds);
		
		txtAnsAge = new JTextField();
		txtAnsAge.setFont(new Font("Monospaced", Font.PLAIN, 18));
		txtAnsAge.setColumns(10);
		txtAnsAge.setBounds(350, 98, 200, 34);
		contentPane.add(txtAnsAge);
		
		txtAnsMF = new JTextField();
		txtAnsMF.setFont(new Font("Monospaced", Font.PLAIN, 18));
		txtAnsMF.setColumns(10);
		txtAnsMF.setBounds(350, 149, 200, 34);
		contentPane.add(txtAnsMF);
		
		txtAnsBreed = new JTextField();
		txtAnsBreed.setFont(new Font("Monospaced", Font.PLAIN, 18));
		txtAnsBreed.setColumns(10);
		txtAnsBreed.setBounds(350, 199, 200, 34);
		contentPane.add(txtAnsBreed);
		
		txtAnsWeight = new JTextField();
		txtAnsWeight.setFont(new Font("Monospaced", Font.PLAIN, 18));
		txtAnsWeight.setColumns(10);
		txtAnsWeight.setBounds(350, 249, 200, 34);
		contentPane.add(txtAnsWeight);
		
		txtAnsMeals = new JTextField();
		txtAnsMeals.setFont(new Font("Monospaced", Font.PLAIN, 18));
		txtAnsMeals.setColumns(10);
		txtAnsMeals.setBounds(25, 347, 525, 34);
		contentPane.add(txtAnsMeals);
		
		txtAnsMeds = new JTextField();
		txtAnsMeds.setFont(new Font("Monospaced", Font.PLAIN, 18));
		txtAnsMeds.setColumns(10);
		txtAnsMeds.setBounds(25, 441, 525, 34);
		contentPane.add(txtAnsMeds);
		
		lblConfirmation = new JLabel("");
		lblConfirmation.setForeground(new Color(255, 99, 71));
		lblConfirmation.setFont(new Font("Monospaced", Font.PLAIN, 18));
		lblConfirmation.setBounds(25, 498, 525, 34);
		contentPane.add(lblConfirmation);
		
		btnSave = new JButton("Save Changes");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSaveClick();
			}
		});
		btnSave.setForeground(Color.BLACK);
		btnSave.setFont(new Font("Monospaced", Font.PLAIN, 18));
		btnSave.setBorderPainted(false);
		btnSave.setBackground(new Color(250, 235, 215));
		btnSave.setBounds(25, 554, 249, 33);
		contentPane.add(btnSave);
		
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
		btnDiary.setBounds(315, 553, 235, 33);
		contentPane.add(btnDiary);
		
	}
	
	String getAge(String dog, String date) {
		File infoFile = new File(infoPath);
		String age = "";
		try {
			Scanner infoScanner = new Scanner(infoFile);
			while (infoScanner.hasNextLine()) {
				String currentLine = infoScanner.nextLine();
				if (currentLine.equals("Age:")) {
					age = infoScanner.nextLine();
					break;
				} infoScanner.nextLine();
			}
			infoScanner.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return age;
	}
	
	String getSex(String dog, String date) {
		File infoFile = new File(infoPath);
		String sex = "";
		try {
			Scanner infoScanner = new Scanner(infoFile);
			while (infoScanner.hasNextLine()) {
				String currentLine = infoScanner.nextLine();
				if (currentLine.equals("M/F:")) {
					sex = infoScanner.nextLine();
					break;
				} infoScanner.nextLine();
			}
			infoScanner.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sex;
	}
	
	String getBreed(String dog, String date) {
		File infoFile = new File(infoPath);
		String breed = "";
		try {
			Scanner infoScanner = new Scanner(infoFile);
			while (infoScanner.hasNextLine()) {
				String currentLine = infoScanner.nextLine();
				if (currentLine.equals("Breed:")) {
					breed = infoScanner.nextLine();
					break;
				} infoScanner.nextLine();
			}
			infoScanner.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return breed;
	}
	
	String getWeight(String dog, String date) {
		File infoFile = new File(infoPath);
		String weight = "";
		try {
			Scanner infoScanner = new Scanner(infoFile);
			while (infoScanner.hasNextLine()) {
				String currentLine = infoScanner.nextLine();
				if (currentLine.equals("Weight:")) {
					weight = infoScanner.nextLine();
					break;
				} infoScanner.nextLine();
			}
			infoScanner.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return weight;
	}
	
	String getMealSpecifications(String dog, String date) {
		File infoFile = new File(infoPath);
		String mealSpecifications = "";
		try {
			Scanner infoScanner = new Scanner(infoFile);
			while (infoScanner.hasNextLine()) {
				String currentLine = infoScanner.nextLine();
				if (currentLine.equals("Meal specifications:")) {
					mealSpecifications = infoScanner.nextLine();
					break;
				} infoScanner.nextLine();
			}
			infoScanner.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mealSpecifications;
	}
	
	String getMedsSpecifications(String dog, String date) {
		File infoFile = new File(infoPath);
		String medsSpecifications = "";
		try {
			Scanner infoScanner = new Scanner(infoFile);
			while (infoScanner.hasNextLine()) {
				String currentLine = infoScanner.nextLine();
				if (currentLine.equals("Medicine:")) {
					medsSpecifications = infoScanner.nextLine();
					break;
				} infoScanner.nextLine();
			}
			infoScanner.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return medsSpecifications;
	}
	
	void btnSaveClick() {
		try {
			String previousAge = getAge(userDog, basicDate);
			String previousSex = getSex(userDog, basicDate);
			String previousBreed = getBreed(userDog, basicDate);;
			String previousWeight = getWeight(userDog, basicDate);
			String previousMeals = getMealSpecifications(userDog, basicDate);
			String previousMeds = getMedsSpecifications(userDog, basicDate);
			
			String currentAge = txtAnsAge.getText();
			String currentSex = txtAnsMF.getText();
			String currentBreed = txtAnsBreed.getText();
			String currentWeight = txtAnsWeight.getText();
			String currentMeds = txtAnsMeds.getText();
			String currentMeals = txtAnsMeals.getText();
			
			FileWriter infoOutput = new FileWriter(infoPath);
			
			infoOutput.write("Age:"+ "\n");
			if (txtAnsAge.getText().equals(""))
				infoOutput.write(previousAge+"\n");
			else
				infoOutput.write(currentAge+"\n");
			
			infoOutput.write("M/F:"+ "\n");
			if (txtAnsMF.getText().equals(""))
				infoOutput.write(previousSex+"\n");
			else
				infoOutput.write(currentSex+"\n");
			
			infoOutput.write("Breed:"+ "\n");
			if (txtAnsBreed.getText().equals(""))
				infoOutput.write(previousBreed+"\n");
			else
				infoOutput.write(currentBreed+"\n");
			
			infoOutput.write("Weight:"+ "\n");
			if (txtAnsWeight.getText().equals(""))
				infoOutput.write(previousWeight+"\n");
			else	
				infoOutput.write(currentWeight+"\n");
			
			infoOutput.write("Meal specifications:"+ "\n");
			if (txtAnsMeds.getText().equals(""))
				infoOutput.write(previousMeals+"\n");
			else
				infoOutput.write(currentMeals+"\n");
			
			infoOutput.write("Medicine:"+ "\n");
			if (txtAnsMeds.getText().equals(""))
				infoOutput.write(previousMeds+"\n");
			else
				infoOutput.write(currentMeds+"\n");
			
			infoOutput.close();
		
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		lblConfirmation.setText("Information successfully changed.");

	}
	
	void btnDiaryClick() {
		dispose();
		DiaryGUI diaryFrame = new DiaryGUI(userDog, basicDate);
		diaryFrame.show();
	}

}
