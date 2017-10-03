package com.VCW.threads;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.Socket;
import java.net.UnknownHostException;

public class MyThread extends Thread {

	private String webServerAddress = null;
	private String webServerPort;
	private String IP = null;
	private String ident = null;
	private JLabel lblCounterContent;
	private JLabel lblFileResult;
	private Socket clientSocket;
	private JTextField textUserInput;
//	private JTextField textIdent;
	
	private PrintWriter output = null;
	private BufferedReader input = null;
	
	public MyThread(String webAddress, String port, String ident, JPanel myPanel, JLabel lblCounterContent, JLabel lblFileResult, JTextField textUserInput) {

		this.webServerAddress = webAddress;
		this.setWebServerPort(port);
		this.ident = ident;
		this.setLblCounterContent(lblCounterContent);
		this.setLblFileResult(lblFileResult);
		this.textUserInput = textUserInput;
//		this.textIdent = textIdent;

	}
	
	public void run(){
		String result = "";
		int port;
		try {
			port = Integer.parseInt(webServerPort);
		} catch (Exception e) {
			// invalid port
			return;
		}
		try {
			clientSocket = new Socket(webServerAddress, port);
			output = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
			input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		
			// connect to webAddress and port
			output.println(ident); // ident
			output.flush();
			
		
			while (clientSocket.isConnected()) { // while connection.established
				String receive = input.readLine(); 
				if (receive != null) {
					String[] results = receive.split("~");
					result = results[1];
					if (!lblCounterContent.getText().equals(result)) {
						lblFileResult.setText(textUserInput.getText() + result);
						lblCounterContent.setText(result);
						writeOnFile(textUserInput.getText() + result); // filePathAndName
					}
//					sleep(100l);
				}
			}
		} catch (UnknownHostException e) {
		} catch (IOException e) {
//		} catch (InterruptedException e) {
			// for sleep
		} finally {
			closeComunication();
		}
	}

	public String getWebAddress() {
		return webServerAddress;
	}
	public void setWebAddress(String webAddress) {
		this.webServerAddress = webAddress;
	}
	public String getIP() {
		return IP;
	}
	public void setIP(String iP) {
		IP = iP;
	}
	public String getIdend() {
		return ident;
	}
	public void setIdent(String ident) {
		this.ident = ident;
	}
	
	public String getWebServerAddress() {
		return webServerAddress;
	}
	public void setWebServerAddress(String webServerAddress) {
		this.webServerAddress = webServerAddress;
	}
	public String getWebServerPort() {
		return webServerPort;
	}
	public void setWebServerPort(String webServerPort) {
		this.webServerPort = webServerPort;
	}
	public JLabel getLblCounterContent() {
		return lblCounterContent;
	}
	public void setLblCounterContent(JLabel lblCounterContent) {
		this.lblCounterContent = lblCounterContent;
	}
	public JLabel getLblFileResult() {
		return lblFileResult;
	}
	public void setLblFileResult(JLabel lblFileResult) {
		this.lblFileResult = lblFileResult;
	}

	public synchronized void disconnect() {
//		Socket clientSocket2 = new Socket(webServerAddress, 123);
		
		if (output != null) {
			output.println("close"); // ident
			output.flush();
		}
	}
	
	public synchronized void closeComunication() {
		try {
			if (output != null) output.close();
			if (input != null) input.close();
			if (clientSocket != null) clientSocket.close();
		} catch (IOException e) {
			output = null;
			input = null;
			clientSocket = null;
		}
	}

	void writeOnFile(String fileContent) {
		Writer writer = null;
		try {
			String filePath = "c:\\Users\\dimitar.gadzhev\\Desktop\\";
			String fileName = "DC.txt";
		    writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath + fileName)));
		    writer.write(fileContent);
		    writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
