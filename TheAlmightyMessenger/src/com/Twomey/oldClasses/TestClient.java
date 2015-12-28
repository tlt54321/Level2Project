package com.Twomey.oldClasses;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TestClient implements ActionListener{
	JFrame frame;
	JPanel controlPanel;
	JPanel chatPanel;
	JButton connect;
	JTextArea chatText;
	JTextArea inputTextArea;
	JTextField inputText;
	
 	private ObjectOutputStream output;
 	private ObjectInputStream input;
 	private Socket connection;
	
	public static void main(String[] args) {
		new TestClient();
	}

	TestClient() {
		frame = new JFrame("Client");
		controlPanel = new JPanel();
		chatPanel = new JPanel();
		connect = new JButton("Connect");
		
		connect.addActionListener(this);
		
		chatText = new JTextArea(10,20);
		chatText.setEditable(false);
		chatText.setLineWrap(true);
		
		controlPanel.add(connect);
		
		inputText = new JTextField();
		inputText.setSize(350, 25);
		inputText.setEditable(true);
		inputText.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						sendMessage(event.getActionCommand());
						inputText.setText("");
					}
				}
			);
		
		inputTextArea = new JTextArea(2,22);
		inputTextArea.setEditable(false);
		inputTextArea.setLineWrap(true);
		inputTextArea.add(inputText,BorderLayout.CENTER);
		
		chatPanel.add(inputTextArea, BorderLayout.NORTH);
		
		JScrollPane chatTextScrollPane = new JScrollPane(chatText, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		chatPanel.add(chatTextScrollPane, BorderLayout.SOUTH);
		
		frame.setLayout(new BorderLayout());
		frame.add(controlPanel, BorderLayout.NORTH);
		frame.add(chatPanel, BorderLayout.SOUTH);
		
		frame.setSize(300, 250);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void startClient() {
		new Thread() {
			public void run() {
				try {
					connection = new Socket("localhost", 1738);
				while(!isConnected) {
					setupStreams();
				}
				showMessage("Connection established");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}.start();
	}
	
	boolean isConnected = false;
	
	private void setupStreams() throws IOException {
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		input = new ObjectInputStream(connection.getInputStream());
		isConnected = true;
	}
	
	private void showMessage(String message) {
		chatText.append("\n" + message);
	}
	
	private void sendMessage(String message) {
		try{
			output.writeObject("\nCLIENT - " + message);
			output.flush();
			chatText.append("\nCLIENT - " + message);
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == connect) {
			startClient();
		}
	}
	
}