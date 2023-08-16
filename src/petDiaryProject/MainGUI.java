package petDiaryProject;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class MainGUI extends JFrame {

	private JPanel contentPane;
	private JTextArea txtTitle;
	private JTextArea txtSub1;
	private JTextArea txtSub2;
	private JButton btnManage;
	private JButton btnDiary;
	private JButton btnExit;
	
	public MainGUI() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 800);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtTitle = new JTextArea();
		txtTitle.setEditable(false);
		txtTitle.setFont(new Font("Monospaced", Font.BOLD, 30));
		txtTitle.setText("/ Diary /");
		txtTitle.setBounds(211, 117, 164, 52);
		contentPane.add(txtTitle);
		
		txtSub1 = new JTextArea();
		txtSub1.setEditable(false);
		txtSub1.setFont(new Font("Monospaced", Font.ITALIC, 18));
		txtSub1.setText("for you.");
		txtSub1.setBounds(248, 179, 89, 34);
		contentPane.add(txtSub1);
		
		txtSub2 = new JTextArea();
		txtSub2.setEditable(false);
		txtSub2.setFont(new Font("Monospaced", Font.ITALIC, 18));
		txtSub2.setText("for your new furry companion.");
		txtSub2.setBounds(132, 212, 321, 34);
		contentPane.add(txtSub2);
		
		btnManage = new JButton("Manage Pets");
		btnManage.setBackground(new Color(176, 196, 222));
		btnManage.setOpaque(true);
		btnManage.setBorderPainted(false);
		btnManage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnManageClick();
			}
		});
		btnManage.setFont(new Font("Monospaced", Font.PLAIN, 17));
		btnManage.setBounds(211, 300, 164, 34);
		contentPane.add(btnManage);
		
		btnDiary = new JButton("Open Diary");
		btnDiary.setBackground(new Color(176, 196, 222));
		btnDiary.setOpaque(true);
		btnDiary.setBorderPainted(false);
		btnDiary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDiaryClick();
			}
		});
		btnDiary.setFont(new Font("Monospaced", Font.PLAIN, 18));
		btnDiary.setBounds(211, 360, 164, 34);
		contentPane.add(btnDiary);
		
		btnExit = new JButton("Exit");
		btnExit.setBackground(new Color(176, 196, 222));
		btnExit.setOpaque(true);
		btnExit.setBorderPainted(false);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnExitClick();
			}
		});
		btnExit.setFont(new Font("Monospaced", Font.PLAIN, 18));
		btnExit.setBounds(211, 420, 164, 34);
		contentPane.add(btnExit);
		
	}
	
	void btnManageClick() {
		dispose();
		ManageGUI manageFrame = new ManageGUI();
		manageFrame.show();
	}
	
	void btnDiaryClick() {
		dispose();
		EnterDiaryGUI enterFrame = new EnterDiaryGUI();
		enterFrame.show();
	}
	
	void btnExitClick() {
		dispose();
	}
}
