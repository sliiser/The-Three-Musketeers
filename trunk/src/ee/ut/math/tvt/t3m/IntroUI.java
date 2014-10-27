package ee.ut.math.tvt.t3m;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class IntroUI extends JFrame {

	private static final long serialVersionUID = 1L;
	
	JPanel panel;
	
	JLabel teamName, teamDesc;
	
	IntroUI() {
		super("Hello!");
		setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
		setSize(500,400);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		try {
			Properties properties = new Properties();
			properties.load(getClass().getResourceAsStream("/application.properties"));
			properties.load(getClass().getResourceAsStream("/version.properties"));
		
			teamName = new JLabel("<html><p style='text-align:center;'>"+ properties.getProperty("team.name") +"</p></html>", SwingConstants.LEFT);  // construct a JLabel
			teamName.setVerticalTextPosition(JLabel.TOP);
			teamName.setVerticalAlignment(JLabel.TOP);
			teamName.setFont(new Font("Serif", Font.PLAIN, 40));
			teamDesc = new JLabel("<html>"
					+ "<p><strong>Team leader: </strong>"+ properties.getProperty("team.lead") + "</p>"
					+ "<p><strong>E-mail: </strong>"+ properties.getProperty("team.leader.email") + "</p>"
					+ "<p><strong>Team members: </strong>" + properties.getProperty("team.members") +"</p>"
					+ "<p><strong>Software version number: </strong>" + properties.getProperty("build.number") +"</p>"
					+ "</html>",SwingConstants.CENTER);
			teamDesc.setVerticalTextPosition(JLabel.TOP);
			teamDesc.setVerticalAlignment(JLabel.TOP);
			add(new JLabel(new ImageIcon(getClass().getResource("/res/logo.png"))));
		    add(teamName); 
		    add(teamDesc);
		    Intro.log.info("window is open");
		} catch (IOException e) {
			Intro.log.error("IOException when loading properties or image", e);
		}
	}
}
