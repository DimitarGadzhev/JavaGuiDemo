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
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;

public class MyThread2 extends Thread {

	private boolean isConnected = false;
	private int period = 1;
	
	private String ident = null;
	private JLabel lblCounterContent;
	private JLabel lblFileResult;
	private JTextField textUserInput;
	private JLabel lblSelectFileValue;
	
	String address = null;
	URLConnection connection = null;
	BufferedReader reader = null;
	String result = "";
	
	public MyThread2(
			String webAddress, 
			String ident, 
			JLabel lblCounterContent, 
			JLabel lblFileResult, 
			JTextField textUserInput, 
			JLabel lblSelectFileValue) {
		this.address = webAddress;
		this.ident = ident;
		this.setLblCounterContent(lblCounterContent);
		this.setLblFileResult(lblFileResult);
		this.textUserInput = textUserInput;
		this.lblSelectFileValue = lblSelectFileValue;
	}
	
	public void run(){
		
		try {
			if (!"".equals(ident)) {
				address = address + "/" + ident;
			}
			URL urlAddress = new URL(address);
			isConnected = true;
			while (isConnected) {
				connection = urlAddress.openConnection();
				reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String inputLine;
				
				while ((inputLine = reader.readLine()) != null) {
					System.out.println("receive " + inputLine);
					String[] results = inputLine.split("~");
					result = results[1];
					if (!lblCounterContent.getText().equals(result)) {
						System.out.println("------------");
						lblFileResult.setText(textUserInput.getText() + result);
						lblCounterContent.setText(result);
						writeOnFile(textUserInput.getText() + result); // filePathAndName
						period = 1;
					} else {
						period++;
						if (period > 5) period = 5;
					}
				}
				sleep(1000l * period);
			}
		} catch (IOException | InterruptedException e) {
			isConnected = false;
			e.printStackTrace();
		} finally {
			isConnected = false;
			closeComunication();
		}
	}

	public String getIdend() {
		return ident;
	}
	public void setIdent(String ident) {
		this.ident = ident;
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

	public synchronized void closeComunication() {
		isConnected = false;
		try {
			if (reader != null) {
				reader.close();
			}
			if (connection != null) {
				connection = null;
			}
		} catch (IOException e) {
			reader = null;
			connection = null;
		}
	}

	void writeOnFile(String fileContent) {
		Writer writer = null;
		try {
			String filePath = "c:\\Users\\dimitar.gadzhev\\Desktop\\";
			String fileName = "DC.txt";
			String pathAndName = lblSelectFileValue.getText();
		    writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath + fileName)));
		    writer.write(fileContent);
		    writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}

