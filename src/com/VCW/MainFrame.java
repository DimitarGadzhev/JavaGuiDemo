package com.VCW;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

//import com.VCW.threads.MyThread;
import com.VCW.threads.MyThread2;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Choice;

public class MainFrame {

	private JFrame frmDcDido;
	private JTextField textUserInput;
	private JTextField textServerAddress;
	private JTextField textIdent;
	private MyThread2 myThread = null;
	private JTextField textPort;
	private JTextField textFilePath;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frmDcDido.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDcDido = new JFrame();
		frmDcDido.setTitle("DC = Dido the Counter");
		frmDcDido.setResizable(false);
		frmDcDido.setBounds(100, 100, 600, 450);
		frmDcDido.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDcDido.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(30, 30, 524, 361);
		frmDcDido.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(Color.GREEN));
		panel_1.setBounds(10, 120, 504, 90);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblMessage = new JLabel("Your message:");
		lblMessage.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMessage.setBounds(10, 13, 90, 14);
		panel_1.add(lblMessage);
		
		textUserInput = new JTextField();
		textUserInput.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textUserInput.setBounds(100, 10, 394, 20);
		panel_1.add(textUserInput);
		textUserInput.setColumns(10);
		
		JLabel lblCounterName = new JLabel("Web Counter:");
		lblCounterName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCounterName.setBounds(10, 40, 90, 14);
		panel_1.add(lblCounterName);
		
		JLabel lblCounterContent = new JLabel("0");
		lblCounterContent.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCounterContent.setForeground(Color.RED);
		lblCounterContent.setBackground(Color.LIGHT_GRAY);
		lblCounterContent.setBounds(100, 39, 394, 14);
		panel_1.add(lblCounterContent);
		
		JLabel lblContentName = new JLabel("File Content:");
		lblContentName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblContentName.setBounds(10, 67, 90, 14);
		panel_1.add(lblContentName);
		
		JLabel lblFileResult = new JLabel("");
		lblFileResult.setForeground(new Color(0, 128, 0));
		lblFileResult.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFileResult.setBounds(100, 67, 394, 14);
		panel_1.add(lblFileResult);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(Color.RED));
		panel_2.setBounds(10, 220, 504, 130);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblServerAddress = new JLabel("Web Address:");
		lblServerAddress.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblServerAddress.setBounds(10, 13, 90, 14);
		panel_2.add(lblServerAddress);
		
		textServerAddress = new JTextField();
		textServerAddress.setText("https://aqueous-citadel-64267.herokuapp.com");
		textServerAddress.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textServerAddress.setBounds(100, 10, 394, 20);
		panel_2.add(textServerAddress);
		textServerAddress.setColumns(10);
		
		JLabel lblTEST = new JLabel("TEST LABEL");
		lblTEST.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTEST.setBackground(new Color(176, 196, 222));
		lblTEST.setBounds(100, 100, 394, 14);
		panel_2.add(lblTEST);
		
		JLabel lblIdent = new JLabel("Identification:");
		lblIdent.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblIdent.setBounds(284, 40, 90, 14);
		panel_2.add(lblIdent);
		
		textIdent = new JTextField();
		textIdent.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textIdent.setBounds(374, 37, 120, 20);
		panel_2.add(textIdent);
		textIdent.setColumns(10);
		
		textPort = new JTextField();
		textPort.setText("17873");
		textPort.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textPort.setBounds(100, 37, 120, 20);
		panel_2.add(textPort);
		textPort.setColumns(10);
		
		JLabel lblPort = new JLabel("Server Port:");
		lblPort.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPort.setBounds(10, 40, 90, 14);
		panel_2.add(lblPort);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(Color.BLUE));
		panel_3.setBounds(10, 10, 504, 100);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblFilePath = new JLabel("File Path:");
		lblFilePath.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFilePath.setBounds(10, 13, 80, 14);
		panel_3.add(lblFilePath);
		
		textFilePath = new JTextField();
		textFilePath.setText("C:\\Users\\dimitar.gadzhev\\Desktop\\DC.txt");
		textFilePath.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFilePath.setBounds(100, 10, 394, 20);
		panel_3.add(textFilePath);
		textFilePath.setColumns(10);
		
		JLabel lblSelectFileValue = new JLabel("");
		lblSelectFileValue.setForeground(Color.BLUE);
		lblSelectFileValue.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSelectFileValue.setBounds(100, 73, 394, 14);
		panel_3.add(lblSelectFileValue);
		
		JLabel lblSelectFile = new JLabel("Select File:");
		lblSelectFile.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSelectFile.setBounds(10, 73, 80, 14);
		panel_3.add(lblSelectFile);
		
		JButton btnConnect = new JButton("(re) Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (myThread != null) {
					myThread = null;
				}
				myThread = new MyThread2(textServerAddress.getText(), textIdent.getText(), lblCounterContent, lblFileResult, textUserInput, lblSelectFileValue);
				myThread.start();
				lblTEST.setText(textServerAddress.getText());
			}
		});
		btnConnect.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnConnect.setBounds(100, 70, 120, 23);
		panel_2.add(btnConnect);
		
		JButton btnDisconnect = new JButton("Disconnect");
		btnDisconnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myThread.closeComunication();
				lblCounterContent.setText("0");
				lblFileResult.setText("");
			}
		});
		btnDisconnect.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnDisconnect.setBounds(230, 70, 120, 23);
		panel_2.add(btnDisconnect);
		
		
		
		JButton btnNewButton = new JButton("Select");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblSelectFileValue.setText(textFilePath.getText());
			}
		});
		btnNewButton.setBounds(100, 40, 120, 23);
		panel_3.add(btnNewButton);
		

	
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
