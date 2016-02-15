package com.Twomey.TheAlmightyMessenger;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Settings extends JFrame{
	public String username;
	private JPanel panel;
	private JTextField usernameTextField;
	private JLabel usernameLabel;
	private JButton saveChanges;
	private JLabel themeLabel;
	private JComboBox themeComboBox;
	private Boolean isServer;

	public Settings(Boolean isServer) {
		super("Settings");
		this.isServer = isServer;
		setLookandFeel();
		setSize(420, 275);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((dim.width/2-this.getSize().width/2) + 20, (dim.height/2-this.getSize().height/2) + 20);
		setVisible(true);
		
		panel = new JPanel();
		add(panel);
		
		saveChanges = new JButton("Save Changes");
		panel.add(saveChanges);
		saveChanges.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						username = usernameTextField.getText();
						setTheme();
						if(isServer){
							Server.username = username;
						}else{
							Client.username = username;
						}
					}
				}
			);
		
		//Username
		if(isServer){
			username = Server.username;
		}else{
			username = Client.username;
		}
		
		usernameLabel = new JLabel("Username: ");
		panel.add(usernameLabel);
		usernameTextField = new JTextField(20);
		panel.add(usernameTextField);
		usernameTextField.setText(username);
		usernameTextField.setEditable(true);
		
		//Theme
		themeLabel = new JLabel("Themes:");
		panel.add(themeLabel);
		themeComboBox = new JComboBox();
		themeComboBox.addItem("Light");
		themeComboBox.addItem("Dark");
		panel.add(themeComboBox);
	}

	private void setLookandFeel(){
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}
	
	private void setTheme(){
		Object selected = themeComboBox.getSelectedItem();
		if(isServer){
			if(selected == "Light"){
				Server.chatWindow.setBackground(Color.WHITE);
				Server.userTextPanel.setBackground(Color.WHITE);
				Server.menuBar.setBackground(Color.WHITE);
				Server.userText.setBackground(Color.WHITE);
				Server.chatWindow.setForeground(Color.BLACK);
				Server.userText.setForeground(Color.BLACK);
			}
			else if(selected == "Dark"){
				Server.chatWindow.setBackground(Color.BLACK);
				Server.userTextPanel.setBackground(Color.BLACK);
				Server.menuBar.setBackground(Color.BLACK);
				Server.userText.setBackground(Color.BLACK);
				Server.chatWindow.setForeground(Color.WHITE);
				Server.userText.setForeground(Color.WHITE);
			}
		}
		else{
			if(selected == "Light"){
				Client.chatWindow.setBackground(Color.WHITE);
				Client.userTextPanel.setBackground(Color.WHITE);
				Client.menuBar.setBackground(Color.WHITE);
				Client.userText.setBackground(Color.WHITE);
				Client.chatWindow.setForeground(Color.BLACK);
				Client.userText.setForeground(Color.BLACK);
			}
			else if(selected == "Dark"){
				Client.chatWindow.setBackground(Color.BLACK);
				Client.userTextPanel.setBackground(Color.BLACK);
				Client.menuBar.setBackground(Color.BLACK);
				Client.userText.setBackground(Color.BLACK);
				Client.chatWindow.setForeground(Color.WHITE);
				Client.userText.setForeground(Color.WHITE);
			}
		}
		
	}
}