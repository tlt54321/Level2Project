package com.Twomey.TheAlmightyMessenger;

import javax.swing.JOptionPane;

public class ClientTest{

	public static void main(String[] args) {
		String ip = JOptionPane.showInputDialog("Enter the ip you want to connnect to");
		Client client = new Client(ip);
		client.startRunning();
	}

}