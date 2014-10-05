package ee.ut.math.tvt.t3m;

import java.awt.*; 

import javax.swing.*;

public class IntroUI extends JFrame {
	
	private static final long serialVersionUID = 1L;

	// see on IntroUI
	
	JPanel panel;
	
	JLabel teamName, teamDesc;
	
	IntroUI() {
		super("Hello!");
		setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
		setSize(500,400);
		setLocation(300, 200);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		teamName = new JLabel("<html><p style='text-align:right;'>The<br>Three<br>Musketeers</p></html>", SwingConstants.RIGHT);  // construct a JLabel
		teamName.setVerticalTextPosition(JLabel.TOP);
		teamName.setFont(new Font("Serif", Font.PLAIN, 40));
		teamDesc = new JLabel("<html>"
				+ "<p><strong>Team leader:</strong> Ardo Aednik</p>"
				+ "<p><strong>E-mail: </strong>ardo.aednik@gmail.com</p><br>"
				+ "<p><strong>Team members:</strong></p>"
				+ "<p>Ardo Aednik</p>"
				+ "<p>Pärt Erikson</p>"
				+ "<p>Siim Liiser</p>"
				+ "</html>",SwingConstants.CENTER);
		teamDesc.setVerticalTextPosition(JLabel.TOP);
		teamDesc.setVerticalAlignment(JLabel.TOP);
		add(new JLabel(new ImageIcon("res/logo.png")));
	    add(teamName); 
	    add(teamDesc);
	}
}
