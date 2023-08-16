package petDiaryProject;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;

public class ViewPastEntriesGUI extends JFrame {

	private JPanel contentPane;

	private JTextField txtAnswer;
	private JTextArea txtEnterDate;
	private JButton btnConfirm;
	private JButton btnMain;
	private JTextArea txtNotes;
	private JTextArea txtbxNotes;
	private JLabel lblConfirmation;
	
	private String userDog;

	public ViewPastEntriesGUI(String dog) {
		
		userDog = dog;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtAnswer = new JTextField();
		txtAnswer.setBorder(new LineBorder(new Color(171, 173, 179)));
		txtAnswer.setBounds(362, 33, 171, 29);
		contentPane.add(txtAnswer);
		txtAnswer.setColumns(10);
		
		txtEnterDate = new JTextArea();
		txtEnterDate.setEditable(false);
		txtEnterDate.setFont(new Font("Monospaced", Font.PLAIN, 18));
		txtEnterDate.setText("Enter date (ex. 20230814):");
		txtEnterDate.setBounds(61, 31, 299, 29);
		contentPane.add(txtEnterDate);
		
		btnConfirm = new JButton("Confirm");
		btnConfirm.setBorderPainted(false);
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userAnswer = txtAnswer.getText();
				btnConfirmClick(userDog, userAnswer);
			}
		});
		btnConfirm.setForeground(Color.BLACK);
		btnConfirm.setFont(new Font("Monospaced", Font.PLAIN, 18));
		btnConfirm.setBackground(new Color(250, 235, 215));
		btnConfirm.setBounds(138, 85, 143, 33);
		contentPane.add(btnConfirm);
		
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
		
		txtbxNotes = new JTextArea();
		txtbxNotes.setEditable(false);
		txtbxNotes.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		txtbxNotes.setWrapStyleWord(true);
		txtbxNotes.setFont(new Font("Monospaced", Font.PLAIN, 18));
		txtbxNotes.setBounds(61, 219, 472, 299);
		contentPane.add(txtbxNotes);
		
		txtNotes = new JTextArea();
		txtNotes.setForeground(new Color(255, 99, 71));
		txtNotes.setEditable(false);
		txtNotes.setFont(new Font("Monospaced", Font.BOLD, 18));
		txtNotes.setText("Notes on this day >");
		txtNotes.setBounds(61, 179, 220, 27);
		contentPane.add(txtNotes);
		
		lblConfirmation = new JLabel("");
		lblConfirmation.setForeground(new Color(255, 99, 71));
		lblConfirmation.setFont(new Font("Monospaced", Font.PLAIN, 18));
		lblConfirmation.setBounds(61, 137, 472, 29);
		contentPane.add(lblConfirmation);
		
	}
	
	void btnConfirmClick(String dog, String userAnswer) {
		
		try {
			String notesPath = System.getProperty("user.dir") + "/" + dog + "/Entries/" + userAnswer + "/notes.txt";
			File notesFile = new File(notesPath);
			String notes = "";
			try {
				Scanner notesScanner = new Scanner(notesFile);
				while (notesScanner.hasNext()) {
					notes += notesScanner.next() + " ";
				}
				notesScanner.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				lblConfirmation.setText("No entry for that date. Try again?");
			}
			txtbxNotes.setText(notes);
		} catch (Exception e) {
			lblConfirmation.setText("No entry for that date. Try again?");
		}

	}
	
	void btnMainClick() {
		dispose();
		MainGUI mainFrame = new MainGUI();
		mainFrame.show();
	}
}
