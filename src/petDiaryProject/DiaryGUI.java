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
	private JTextArea txtbxNotifications2;
	
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
	String notifsPath;

	// back to main
	private JButton btnMain;

	// view past entries
	private JButton btnPastEntries;
	
	public DiaryGUI(String dog, String date) {
		
		infoPath = System.getProperty("user.dir") + "/" + dog + "/Info/info.txt";
		remindersPath = System.getProperty("user.dir") + "/" + dog + "/Reminders/reminders.txt";
		notesPath = System.getProperty("user.dir") + "/" + dog + "/Entries/" + date + "/notes.txt";
		notifsPath = System.getProperty("user.dir") + "/" + dog + "/Entries/" + "/Notifications/notifications.txt";
		
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
		txtDate.setBounds(28, 37, 386, 31);
		contentPane.add(txtDate);
		
		// title
		txtTitle = new JTextArea();
		txtTitle.setEditable(false);
		txtTitle.setFont(new Font("Monospaced", Font.BOLD, 22));
		txtTitle.setText("Today's Diary For...");
		txtTitle.setBounds(28, 89, 263, 39);
		contentPane.add(txtTitle);
		
		// name
		txtName = new JTextArea();
		txtName.setEditable(false);
		txtName.setForeground(new Color(255, 215, 0));
		txtName.setFont(new Font("Monospaced", Font.BOLD, 22));
		txtName.setText("* " + dog + " *");
		txtName.setBounds(304, 89, 249, 39);
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
		txtbxNotifications1.setForeground(new Color(0, 0, 0));
		txtbxNotifications1.setFont(new Font("Monospaced", Font.ITALIC, 18));
		txtbxNotifications1.setText(getNotifications1(dog, date));
		txtbxNotifications1.setBorder(null);
		txtbxNotifications1.setBounds(177, 170, 376, 31);
		txtbxNotifications1.setLineWrap(true);
		txtbxNotifications1.setWrapStyleWord(true);
		contentPane.add(txtbxNotifications1);

		// notifications top right 2
		txtbxNotifications2 = new JTextArea();
		txtbxNotifications2.setWrapStyleWord(true);
		txtbxNotifications2.setText(getNotifications2(dog, date));
		txtbxNotifications2.setLineWrap(true);
		txtbxNotifications2.setFont(new Font("Monospaced", Font.ITALIC, 18));
		txtbxNotifications2.setBorder(null);
		txtbxNotifications2.setBounds(177, 210, 376, 31);
		contentPane.add(txtbxNotifications2); 

		if (getNotifications1(dog, date) == "No notifications today." && getNotifications2(dog, date) == "No notifications today.") {
			txtbxNotifications2.setText("");
		}
		
		if (getNotifications1(dog, date) == "No notifications today." && getNotifications2(dog, date) != "No notifications today.") {
			txtbxNotifications1.setText("");
		}

		if (getNotifications1(dog, date) != "No notifications today." && getNotifications2(dog, date) == "No notifications today.") {
			txtbxNotifications2.setText("");
		}
		
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
		txtAge.setBounds(28, 333, 144, 31);
		contentPane.add(txtAge);
		
		// info: mf
		txtMF = new JTextArea();
		txtMF.setEditable(false);
		txtMF.setText("M/F..................................");
		txtMF.setFont(new Font("Monospaced", Font.BOLD, 18));
		txtMF.setBounds(28, 363, 144, 31);
		contentPane.add(txtMF);
		
		// info: breed
		txtBreed = new JTextArea();
		txtBreed.setEditable(false);
		txtBreed.setText("Breed...........................");
		txtBreed.setFont(new Font("Monospaced", Font.BOLD, 18));
		txtBreed.setBounds(28, 393, 144, 31);
		contentPane.add(txtBreed);
		
		// info: weight
		txtWeight = new JTextArea();
		txtWeight.setEditable(false);
		txtWeight.setText("Weight..........................");
		txtWeight.setFont(new Font("Monospaced", Font.BOLD, 18));
		txtWeight.setBounds(28, 423, 144, 31);
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
		txtAnsMeals.setEditable(false);
		txtAnsMeals.setFont(new Font("Monospaced", Font.PLAIN, 16));
		txtAnsMeals.setText(getMealSpecifications(dog, date));
		txtAnsMeals.setBorder(null);
		txtAnsMeals.setBounds(28, 484, 200, 60);
		txtAnsMeals.setLineWrap(true);
		txtAnsMeals.setWrapStyleWord(true);
		contentPane.add(txtAnsMeals);
		
		// info: meds answer
		txtAnsMeds = new JTextArea();
		txtAnsMeds.setEditable(false);
		txtAnsMeds.setFont(new Font("Monospaced", Font.PLAIN, 16));
		txtAnsMeds.setText(getMedsSpecifications(dog, date));
		txtAnsMeds.setBorder(null);
		txtAnsMeds.setBounds(28, 574, 200, 60);
		txtAnsMeds.setLineWrap(true);
		txtAnsMeds.setWrapStyleWord(true);
		contentPane.add(txtAnsMeds);
		
		// info: age answer
		txtAnsAge = new JTextArea();
		txtAnsAge.setEditable(false);
		txtAnsAge.setFont(new Font("Monospaced", Font.PLAIN, 16));
		txtAnsAge.setText(getAge(dog, date));
		txtAnsAge.setBounds(177, 335, 99, 31);
		contentPane.add(txtAnsAge);
		
		// info: mf answer
		txtAnsMF = new JTextArea();
		txtAnsMF.setEditable(false);
		txtAnsMF.setFont(new Font("Monospaced", Font.PLAIN, 16));
		txtAnsMF.setText(getSex(dog, date));
		txtAnsMF.setBounds(177, 365, 99, 31);
		contentPane.add(txtAnsMF);
		
		// info: breed answer
		txtAnsBreed = new JTextArea();
		txtAnsBreed.setEditable(false);
		txtAnsBreed.setFont(new Font("Monospaced", Font.PLAIN, 16));
		txtAnsBreed.setText(getBreed(dog, date));
		txtAnsBreed.setBounds(177, 395, 99, 31);
		contentPane.add(txtAnsBreed);
		
		// info: weight answer
		txtAnsWeight = new JTextArea();
		txtAnsWeight.setEditable(false);
		txtAnsWeight.setFont(new Font("Monospaced", Font.PLAIN, 16));
		txtAnsWeight.setText(getWeight(dog, date));
		txtAnsWeight.setBounds(177, 425, 99, 31);
		contentPane.add(txtAnsWeight);
		
		// info edit btn
		btnEditInfo = new JButton("edit");
		btnEditInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnEditInfoClick(userDog, basicDate);
			}
		});
		btnEditInfo.setFont(new Font("Monospaced", Font.PLAIN, 16));
		btnEditInfo.setBorderPainted(false);
		btnEditInfo.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		btnEditInfo.setBackground(new Color(143, 188, 143));
		btnEditInfo.setBounds(213, 305, 51, 21);
		contentPane.add(btnEditInfo);
		
		// notifications bottom left
		txtbxNotifications2 = new JTextArea();
		txtbxNotifications2.setFont(new Font("Monospaced", Font.PLAIN, 18));
		txtbxNotifications2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		txtbxNotifications2.setBounds(28, 658, 248, 66);
		txtbxNotifications2.setLineWrap(true);
		txtbxNotifications2.setWrapStyleWord(true);
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
		txtbxNotes.setFont(new Font("Monospaced", Font.PLAIN, 15));
		txtbxNotes.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		txtbxNotes.setBounds(317, 486, 236, 236);
		txtbxNotes.setLineWrap(true);
		txtbxNotes.setWrapStyleWord(true);
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

		btnMain = new JButton("return to main");
		btnMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnMainClick();
			}
		});
		btnMain.setBackground(new Color(250, 235, 215));
		btnMain.setBorderPainted(false);
		btnMain.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		btnMain.setFont(new Font("Monospaced", Font.PLAIN, 16));
		btnMain.setBounds(28, 665, 155, 22);
		contentPane.add(btnMain);

		btnPastEntries = new JButton("view past entries");
		btnPastEntries.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnPastEntriesClick();
			}
		});
		btnPastEntries.setFont(new Font("Monospaced", Font.PLAIN, 16));
		btnPastEntries.setBorderPainted(false);
		btnPastEntries.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		btnPastEntries.setBackground(new Color(250, 235, 215));
		btnPastEntries.setBounds(28, 700, 182, 22);
		contentPane.add(btnPastEntries);
	}

	String getNotifications1(String dog, String date) {
		// MY CURRENT CHANGES
		String notifications = "No notifications today.";
		String vetVisit = getVetVisitBasic(dog, date);
		int basicDateInt = Integer.parseInt(date);
		
		if (!vetVisit.equals("")) {
			int vetVisitInt = Integer.parseInt(vetVisit);
			if (vetVisitInt - basicDateInt == 1) {
				notifications = "Your vet visit is tomorrow.";
			} 
			else if (vetVisitInt == basicDateInt) {
				notifications = "Your vet visit is today.";
			}
		}
		return notifications;
	
	}
	
	String getNotifications2(String dog, String date) {
		String notifications = "No notifications today.";
		String groomerVisit = getGroomerVisitBasic(dog, date);

		int basicDateInt = Integer.parseInt(date);
		
		if (!groomerVisit.equals("")) {
			int groomerVisitInt = Integer.parseInt(groomerVisit);
			if (groomerVisitInt - basicDateInt == 1) {
				notifications = "Your groomer visit is tomorrow.";
			} 
			else if (groomerVisitInt == basicDateInt) {
				notifications = "Your groomer visit is today.";
			}
		}
		return notifications;
	}
	
	String getVetVisitBasic(String dog, String date) {
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
	
	String getGroomerVisitBasic(String dog, String date) {
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
		int vetMonth;
		int vetDay;
		
		String fancyVetMonth;
		String fancyVetDay;
		
		// MY CURRENT CHANGES
		try {
			vetMonth = Integer.parseInt(vetVisit.substring(4, 6));
			vetDay = Integer.parseInt(vetVisit.substring(6, 8));

			fancyVetDay = vetDay + "";
					
			if (vetMonth == 1) {
				fancyVetMonth = "January";
			}
			else if (vetMonth == 2) {
				fancyVetMonth = "February";
			}
			else if (vetMonth == 3) {
				fancyVetMonth = "March";
			}
			else if (vetMonth == 4) {
				fancyVetMonth = "April";
			}
			else if (vetMonth == 5) {
				fancyVetMonth = "May";
			}
			else if (vetMonth == 6) {
				fancyVetMonth = "June";
			}
			else if (vetMonth == 7) {
				fancyVetMonth = "July";
			}
			else if (vetMonth == 8) {
				fancyVetMonth = "August";
			}
			else if (vetMonth == 9) {
				fancyVetMonth = "September";
			}
			else if (vetMonth == 10) {
				fancyVetMonth = "October";
			}
			else if (vetMonth == 11) {
				fancyVetMonth = "November";
			}
			else {
				fancyVetMonth = "December";
			}
			
			vetVisit = fancyVetMonth + " " + fancyVetDay;	
		} 
		catch (Exception e) {
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
		
		int groomerMonth;
		int groomerDay;
		
		String fancyGroomerMonth;
		String fancyGroomerDay;
		
		try {
			groomerMonth = Integer.parseInt(groomerVisit.substring(4, 6));
			groomerDay = Integer.parseInt(groomerVisit.substring(6, 8));
			fancyGroomerDay = groomerDay + "";
			
			if (groomerMonth == 1) {
				fancyGroomerMonth = "January";
			}
			else if (groomerMonth == 2) {
				fancyGroomerMonth = "February";
			}
			else if (groomerMonth == 3) {
				fancyGroomerMonth = "March";
			}
			else if (groomerMonth == 4) {
				fancyGroomerMonth = "April";
			}
			else if (groomerMonth == 5) {
				fancyGroomerMonth = "May";
			}
			else if (groomerMonth == 6) {
				fancyGroomerMonth = "June";
			}
			else if (groomerMonth == 7) {
				fancyGroomerMonth = "July";
			}
			else if (groomerMonth == 8) {
				fancyGroomerMonth = "August";
			}
			else if (groomerMonth == 9) {
				fancyGroomerMonth = "September";
			}
			else if (groomerMonth == 10) {
				fancyGroomerMonth = "October";
			}
			else if (groomerMonth == 11) {
				fancyGroomerMonth = "November";
			}
			else {
				fancyGroomerMonth = "December";
			}
			
			groomerVisit = fancyGroomerMonth + " " + fancyGroomerDay;		
		
		} 
		catch (Exception e) {
			
		}
		
		return groomerVisit;
	}

	String getNotes(String dog, String date) {
		File notesFile = new File(notesPath);
		String notes = "";
		try {
			Scanner notesScanner = new Scanner(notesFile);
			while(notesScanner.hasNext()) {
				notes += notesScanner.next()+" ";
			}
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
	
	void btnEditRemindersClick(String userDog, String basicDate) {
		dispose();
		EditRemindersGUI editRemindersFrame = new EditRemindersGUI(userDog, basicDate);
		editRemindersFrame.show();
	}
	
	void btnEditInfoClick(String userDog, String basicDate) {
		dispose();
		EditInfoGUI editInfoFrame = new EditInfoGUI(userDog, basicDate);
		editInfoFrame.show();
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

	void btnPastEntriesClick() {
		dispose();
		ViewPastEntriesGUI pastFrame = new ViewPastEntriesGUI(userDog);
		pastFrame.show();
	}

	void btnMainClick() {
		dispose();
		MainGUI mainFrame = new MainGUI();
		mainFrame.show();
	}
}
