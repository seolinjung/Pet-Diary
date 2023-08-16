package petDiaryProject;

import java.awt.Color;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.border.EtchedBorder;

public class ManageGUI extends JFrame {

	private JPanel contentPane;
	private JTextArea txtTitle;
	private JTextArea txtList;
	private JButton btnAdd;
	private JButton btnDelete;
	private JButton btnMain;

	public ManageGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 800);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtTitle = new JTextArea();
		txtTitle.setFont(new Font("Monospaced", Font.BOLD, 20));
		txtTitle.setBounds(170, 41, 245, 34);
		txtTitle.setText("Your Registered Dogs");
		contentPane.add(txtTitle);
		
		txtList = new JTextArea();
		txtList.setFont(new Font("Monospaced", Font.PLAIN, 18));
		txtList.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		txtList.setBounds(61, 96, 283, 202);
		contentPane.add(txtList);
		
		btnAdd = new JButton("Add");
		btnAdd.setBackground(new Color(250, 235, 215));
		btnAdd.setOpaque(true);
		btnAdd.setBorderPainted(false);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAddClick();
			}
		});
		btnAdd.setFont(new Font("Monospaced", Font.PLAIN, 18));
		btnAdd.setBounds(386, 93, 143, 34);
		contentPane.add(btnAdd);
		
		btnDelete = new JButton("Delete");
		btnDelete.setOpaque(true);
		btnDelete.setBackground(new Color(250, 235, 215));
		btnDelete.setBorderPainted(false);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDeleteClick();
			}
		});
		btnDelete.setFont(new Font("Monospaced", Font.PLAIN, 18));
		btnDelete.setBounds(386, 178, 143, 34);
		contentPane.add(btnDelete);
		
		btnMain = new JButton("Main");
		btnMain.setActionCommand("Main");
		btnMain.setFont(new Font("Monospaced", Font.PLAIN, 18));
		btnMain.setBorderPainted(false);
		btnMain.setOpaque(true);
		btnMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnMainClick();
			}
		});
		btnMain.setBackground(new Color(250, 235, 215));
		btnMain.setBounds(386, 264, 143, 34);
		contentPane.add(btnMain);
		
		try {
			File file = new File("petList.txt");
			Scanner input = new Scanner(file);
			String fileContent = "";
			while (input.hasNextLine()) {
				fileContent = fileContent + input.nextLine() + "\n";
			}
			txtList.setText(fileContent);
			input.close();

			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
	}
	
	void btnAddClick() {
		dispose();
		AddGUI addFrame = new AddGUI();
		addFrame.show();
	}
	
	void btnDeleteClick() {
		dispose();
		DeleteGUI deleteFrame = new DeleteGUI();
		deleteFrame.show();
	}
	
	void btnMainClick() {
		dispose();
		MainGUI mainFrame = new MainGUI();
		mainFrame.show();
	}
}
