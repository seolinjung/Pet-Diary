package petDiaryProject;

import java.awt.Color;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import javax.swing.JCheckBox;
import javax.swing.border.EtchedBorder;
import javax.swing.JButton;
import javax.swing.border.MatteBorder;
import java.awt.Cursor;

public class DiaryGUI extends JFrame {

	private JPanel contentPane;
	
	// four elements in title box
	private JTextArea txtDate;
	private JTextArea txtTitle;
	private JTextArea txtName;
	private JTextArea txtLine;
	
	// to-do & co.
	private JTextArea txtToDo;
	private JCheckBox bxWalk;
	private JCheckBox bxMeds;
	private JCheckBox bxMeals;
	private JCheckBox bxPlay;
	
	// notifications box top right
	private JTextArea txtbxNotifications1;
	
	// info & co. 
	private JTextArea txtInfo;
	private JTextArea txtAge;
	private JTextArea txtMF;
	private JTextArea txtBreed;
	private JTextArea txtWeight;
	private JTextArea txtMeals;
	private JTextArea txtMeds;
	private JTextArea txtAnsAge;
	private JTextArea txtAnsMF;
	private JTextArea txtAnsBreed;
	private JTextArea txtAnsWeight;
	private JTextArea txtAnsMeals;
	private JTextArea txtAnsMeds;	
	private JButton btnEditInfo;

	// notifications box bottom left
	private JTextArea txtbxNotifications2;

	// reminders & co
	private JTextArea txtReminders;
	private JTextArea txtVet;
	private JTextArea txtGroomer;	
	private JTextArea txtAnsVet;
	private JTextArea txtAnsGroomer;
	private JButton btnEditReminders;
	
	// notes & co.
	private JTextArea txtNotes;
	private JTextArea txtbxNotes;
	private JButton btnSaveNotes;
	
	// today's date
	private String basicDate;
	private String fancyDate;
	private String userDog;
	
	// paths of each data
	String infoPath;
	String remindersPath;
	String notesPath;
	
	public DiaryGUI(String dog, String date) {
		
		infoPath = System.getProperty("user.dir") + "/" + dog + "/Info/info.txt";
		remindersPath = System.getProperty("user.dir") + "/" + dog + "/Reminders/reminders.txt";
		notesPath = System.getProperty("user.dir") + "/" + dog + "/Entries/" + date + "/notes.txt";

		userDog = dog;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 800);
		contentPane = new JPanel();
		contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// let's create a folder for this date inside /dog/Entries... 
		// in order to do that, we need to extract the basic ISO format of today's date first. so...
		LocalDate localDate = LocalDate.now();
		// this monster returns a date in the format 20230813Z.
		String basicDateWithZ = ZonedDateTime.now( ZoneOffset.UTC ).format(DateTimeFormatter.BASIC_ISO_DATE);
		// so let's slice it, to make it look like 20230813.
		basicDate = basicDateWithZ.substring(0,8);
		
		// we finally start making our directory.
		// good practice to try/catch our folder creations...
		try {
			// we are stringing together a path that is consistent across platforms.
			String currentDir = System.getProperty("user.dir");
			String diaryDir = currentDir + "/" + dog + "/Entries/" + basicDate;
			// now we make the folder.
			File diaryFile = new File(diaryDir);
			diaryFile.mkdirs();
		}
		catch (Exception e) {
		}
	
		// same business with dates, but this time, we want a pretty diary-friendly date on top. so here we go again...
		fancyDate = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).format(localDate);
		txtDate = new JTextArea();
		txtDate.setEditable(false);
		txtDate.setBorder(null);
		txtDate.setForeground(new Color(0, 0, 0));
		txtDate.setFont(new Font("Monospaced", Font.ITALIC, 20));
		txtDate.setText(fancyDate);
		txtDate.setBounds(28, 26, 353, 31);
		contentPane.add(txtDate);
		
		// title
		txtTitle = new JTextArea();
		txtTitle.setEditable(false);
		txtTitle.setFont(new Font("Monospaced", Font.BOLD, 22));
		txtTitle.setText("Today's Diary For...");
		txtTitle.setBounds(28, 77, 263, 39);
		contentPane.add(txtTitle);
		
		// name
		txtName = new JTextArea();
		txtName.setEditable(false);
		txtName.setForeground(new Color(255, 215, 0));
		txtName.setFont(new Font("Monospaced", Font.BOLD, 22));
		txtName.setText("* " + dog + " *");
		txtName.setBounds(304, 77, 249, 39);
		contentPane.add(txtName);
		
		// line (um, I didn't really count the number of "-"s)
		txtLine = new JTextArea();
		txtLine.setText("----------------------------------------------------------------------------------------------------------------------------------------");
		txtLine.setBounds(28, 126, 525, 22);
		contentPane.add(txtLine);
		
		// to-do 
		txtToDo = new JTextArea();
		txtToDo.setEditable(false);
		txtToDo.setForeground(new Color(255, 99, 71));
		txtToDo.setFont(new Font("Monospaced", Font.BOLD, 20));
		txtToDo.setText("To-Do >");
		txtToDo.setBounds(28, 163, 113, 31);
		contentPane.add(txtToDo);
		
		// to-do: walk
		bxWalk = new JCheckBox("Walk");
		bxWalk.setBackground(new Color(255, 255, 255));
		bxWalk.setFont(new Font("Monospaced", Font.PLAIN, 18));
		bxWalk.setBounds(28, 195, 113, 22);
		contentPane.add(bxWalk);
		
		// to-do: meds
		bxMeds = new JCheckBox("Meds");
		bxMeds.setFont(new Font("Monospaced", Font.PLAIN, 18));
		bxMeds.setBackground(Color.WHITE);
		bxMeds.setBounds(28, 219, 113, 22);
		contentPane.add(bxMeds);
					
		// to-do: meals
		bxMeals = new JCheckBox("Meals");
		bxMeals.setFont(new Font("Monospaced", Font.PLAIN, 18));
		bxMeals.setBackground(Color.WHITE);
		bxMeals.setBounds(28, 243, 113, 22);
		contentPane.add(bxMeals);
		
		// to-do: play
		bxPlay = new JCheckBox("Play");
		bxPlay.setFont(new Font("Monospaced", Font.PLAIN, 18));
		bxPlay.setBackground(Color.WHITE);
		bxPlay.setBounds(28, 267, 113, 22);
		contentPane.add(bxPlay);
		
		// notifications top right
		txtbxNotifications1 = new JTextArea();
		txtbxNotifications1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		txtbxNotifications1.setBounds(177, 170, 376, 76);
		contentPane.add(txtbxNotifications1);
		
		// info
		txtInfo = new JTextArea();
		txtInfo.setEditable(false);
		txtInfo.setText("Info >");
		txtInfo.setForeground(new Color(255, 99, 71));
		txtInfo.setFont(new Font("Monospaced", Font.BOLD, 20));
		txtInfo.setBounds(28, 305, 161, 31);
		contentPane.add(txtInfo);

		// info: age
		txtAge = new JTextArea();
		txtAge.setEditable(false);
		txtAge.setFont(new Font("Monospaced", Font.BOLD, 18));
		txtAge.setText("Age..............................");
		txtAge.setBounds(28, 333, 132, 31);
		contentPane.add(txtAge);
		
		// info: mf
		txtMF = new JTextArea();
		txtMF.setEditable(false);
		txtMF.setText("M/F..................................");
		txtMF.setFont(new Font("Monospaced", Font.BOLD, 18));
		txtMF.setBounds(28, 363, 132, 31);
		contentPane.add(txtMF);
		
		// info: breed
		txtBreed = new JTextArea();
		txtBreed.setEditable(false);
		txtBreed.setText("Breed...........................");
		txtBreed.setFont(new Font("Monospaced", Font.BOLD, 18));
		txtBreed.setBounds(28, 393, 132, 31);
		contentPane.add(txtBreed);
		
		// info: weight
		txtWeight = new JTextArea();
		txtWeight.setEditable(false);
		txtWeight.setText("Weight..........................");
		txtWeight.setFont(new Font("Monospaced", Font.BOLD, 18));
		txtWeight.setBounds(28, 423, 132, 31);
		contentPane.add(txtWeight);
		
		// info: meals
		txtMeals = new JTextArea();
		txtMeals.setEditable(false);
		txtMeals.setText("Meal specifications");
		txtMeals.setFont(new Font("Monospaced", Font.BOLD, 18));
		txtMeals.setBounds(28, 454, 215, 31);
		contentPane.add(txtMeals);
		
		// info: meds
		txtMeds = new JTextArea();
		txtMeds.setEditable(false);
		txtMeds.setText("Meds specifications");
		txtMeds.setFont(new Font("Monospaced", Font.BOLD, 18));
		txtMeds.setBounds(28, 545, 215, 31);
		contentPane.add(txtMeds);
		
		// info: meals answer
		txtAnsMeals = new JTextArea();
		txtAnsMeals.setFont(new Font("Monospaced", Font.PLAIN, 18));
		txtAnsMeals.setText(getMealSpecifications(dog, date));
		txtAnsMeals.setBorder(null);
		txtAnsMeals.setBounds(28, 484, 248, 51);
		contentPane.add(txtAnsMeals);
		
		// info: meds answer
		txtAnsMeds = new JTextArea();
		txtAnsMeds.setFont(new Font("Monospaced", Font.PLAIN, 18));
		txtAnsMeds.setText(getMedsSpecifications(dog, date));
		txtAnsMeds.setBorder(null);
		txtAnsMeds.setBounds(28, 574, 248, 51);
		contentPane.add(txtAnsMeds);
		
		// info: age answer
		txtAnsAge = new JTextArea();
		txtAnsAge.setFont(new Font("Monospaced", Font.PLAIN, 18));
		txtAnsAge.setText(getAge(dog, date));
		txtAnsAge.setBounds(177, 333, 99, 31);
		contentPane.add(txtAnsAge);
		
		// info: mf answer
		txtAnsMF = new JTextArea();
		txtAnsMF.setFont(new Font("Monospaced", Font.PLAIN, 18));
		txtAnsMF.setText(getSex(dog, date));
		txtAnsMF.setBounds(177, 368, 99, 31);
		contentPane.add(txtAnsMF);
		
		// info: breed answer
		txtAnsBreed = new JTextArea();
		txtAnsBreed.setFont(new Font("Monospaced", Font.PLAIN, 18));
		txtAnsBreed.setText(getBreed(dog, date));
		txtAnsBreed.setBounds(177, 398, 99, 31);
		contentPane.add(txtAnsBreed);
		
		// info: weight answer
		txtAnsWeight = new JTextArea();
		txtAnsWeight.setFont(new Font("Monospaced", Font.PLAIN, 18));
		txtAnsWeight.setText(getWeight(dog, date));
		txtAnsWeight.setBounds(177, 428, 99, 31);
		contentPane.add(txtAnsWeight);
		
		// info edit btn
		btnEditInfo = new JButton("edit");
		btnEditInfo.setFont(new Font("Monospaced", Font.PLAIN, 16));
		btnEditInfo.setBorderPainted(false);
		btnEditInfo.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		btnEditInfo.setBackground(new Color(143, 188, 143));
		btnEditInfo.setBounds(213, 312, 51, 21);
		contentPane.add(btnEditInfo);
		
		// notifications bottom left
		txtbxNotifications2 = new JTextArea();
		txtbxNotifications2.setFont(new Font("Monospaced", Font.PLAIN, 18));
		txtbxNotifications2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		txtbxNotifications2.setBounds(28, 658, 248, 66);
		contentPane.add(txtbxNotifications2);
		
		// reminders 
		txtReminders = new JTextArea();
		txtReminders.setEditable(false);
		txtReminders.setText("Reminders >");
		txtReminders.setForeground(new Color(255, 99, 71));
		txtReminders.setFont(new Font("Monospaced", Font.BOLD, 20));
		txtReminders.setBounds(317, 264, 161, 31);
		contentPane.add(txtReminders);
		
		// reminders: vet
		txtVet = new JTextArea();
		txtVet.setEditable(false);
		txtVet.setText("Next vet visit");
		txtVet.setFont(new Font("Monospaced", Font.BOLD, 18));
		txtVet.setBounds(317, 294, 161, 22);
		contentPane.add(txtVet);
	
		// reminders: vet ans
		txtAnsVet = new JTextArea();
		txtAnsVet.setFont(new Font("Monospaced", Font.PLAIN, 18));
		txtAnsVet.setText(getVetVisit(dog, date));
		txtAnsVet.setBorder(null);
		txtAnsVet.setBounds(317, 325, 236, 39);
		contentPane.add(txtAnsVet);
		
		// reminders: groomer
		txtGroomer = new JTextArea();
		txtGroomer.setEditable(false);
		txtGroomer.setText("Next groomer visit");
		txtGroomer.setFont(new Font("Monospaced", Font.BOLD, 18));
		txtGroomer.setBounds(317, 368, 215, 31);
		contentPane.add(txtGroomer);
		
		// reminders: groomer ans
		txtAnsGroomer = new JTextArea();
		txtAnsGroomer.setFont(new Font("Monospaced", Font.PLAIN, 18));
		txtAnsGroomer.setText(getGroomerVisit(dog, date));
		txtAnsGroomer.setBorder(null);
		txtAnsGroomer.setBounds(317, 400, 236, 31);
		contentPane.add(txtAnsGroomer);
		
		// reminders edit btn
		btnEditReminders = new JButton("edit");
		btnEditReminders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnEditRemindersClick(userDog, basicDate);
			}
		});
		btnEditReminders.setFont(new Font("Monospaced", Font.PLAIN, 16));
		btnEditReminders.setBorderPainted(false);
		btnEditReminders.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		btnEditReminders.setBackground(new Color(143, 188, 143));
		btnEditReminders.setBounds(502, 271, 51, 21);
		contentPane.add(btnEditReminders);
		
		// notes
		txtNotes = new JTextArea();
		txtNotes.setEditable(false);
		txtNotes.setText("Notes >");
		txtNotes.setForeground(new Color(255, 99, 71));
		txtNotes.setFont(new Font("Monospaced", Font.BOLD, 20));
		txtNotes.setBounds(317, 452, 113, 31);
		contentPane.add(txtNotes);
		
		// notes text box
		txtbxNotes = new JTextArea();
		txtbxNotes.setWrapStyleWord(true);
		txtbxNotes.setFont(new Font("Monospaced", Font.PLAIN, 18));
		txtbxNotes.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		txtbxNotes.setBounds(317, 486, 236, 236);
		contentPane.add(txtbxNotes);
		
		// btn save notes
		btnSaveNotes = new JButton("save");
		btnSaveNotes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userNotes = txtbxNotes.getText();
				btnSaveNotesClick(userNotes);
			}
		});
		btnSaveNotes.setBackground(new Color(143, 188, 143));
		btnSaveNotes.setBorderPainted(false);
		btnSaveNotes.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		btnSaveNotes.setFont(new Font("Monospaced", Font.PLAIN, 16));
		btnSaveNotes.setBounds(502, 459, 51, 21);
		contentPane.add(btnSaveNotes);
		
	}
	
	/*String notifications;
	String vetVisit;
	String groomerVisit;
	String notes;
	String age;
	String sex;
	String breed;
	String weight;
	String mealSpecifications;
	String medsSpecifications;*/
	
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
	
	String getNotes(String dog, String date) {
		File notesFile = new File(notesPath);
		String notes = "";
		try {
			Scanner notesScanner = new Scanner(notesFile);
			notes = notesScanner.next();
			notesScanner.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return notes;
	}
	
	String getAge(String dog, String date) {
		File infoFile = new File(infoPath);
		String age = "";
		try {
			Scanner infoScanner = new Scanner(infoFile);
			while (infoScanner.hasNextLine()) {
				String currentLine = infoScanner.nextLine();
				if (currentLine.equals("Age")) {
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
				if (currentLine.equals("Sex")) {
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
				if (currentLine.equals("Breed")) {
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
				if (currentLine.equals("Weight")) {
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
				if (currentLine.equals("Meal Specifications")) {
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
				if (currentLine.equals("Age")) {
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
	
	void btnEditRemindersClick(String userDog, String basicDate) {
		EditRemindersGUI editRemindersFrame = new EditRemindersGUI(userDog, basicDate);
		editRemindersFrame.show();
	}
	
	void btnEditInfoClick(String userInfo) {
		
	}
	
	
	void btnSaveNotesClick(String userNotes) {
		try {
			String currentDir = System.getProperty("user.dir");
			// let's pray that this creates a "notes.txt" file within this directory...
			String notesDirectory = currentDir + "/" + userDog + "/Entries/" + basicDate + "/notes.txt";
			FileWriter notesOutput = new FileWriter(notesDirectory);
			String notes = txtbxNotes.getText();
			notesOutput.write(notes);
			txtbxNotes.setText(notes);
			notesOutput.close();
		}
		catch (Exception e) {
		}
	}
}
