package com.Twomey.TheAlmightyMessenger;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Client {

	JFrame ClientFrame;
	JPanel ClientPanel;
	
	public static void main(String[] args) {
		new Client();
	}
	
	Client()
	{
		ClientFrame = new JFrame("The Almighty Messenger");
	}

}