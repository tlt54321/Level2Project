package com.Twomey.TheAlmightyMessenger;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
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
	}

	private void setLookandFeel(){
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}
}
