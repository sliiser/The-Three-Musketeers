package ee.ut.math.tvt.t3m;

import java.awt.*; 
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.swing.*;

import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.PatternLayout;

public class IntroUI extends JFrame {

	private static final Logger log = Logger.getLogger(IntroUI.class);
	private static final long serialVersionUID = 1L;
	
	JPanel panel;
	
	JLabel teamName, teamDesc;
	
	IntroUI() {
		super("Hello!");
		ConsoleAppender consoleAppender=new ConsoleAppender(new PatternLayout("%d %p [%c] - %m%n"));
		BasicConfigurator.configure(consoleAppender);
		setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
		setSize(500,400);
		setLocation(300, 200);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		try {
			File file = new File("application.properties");
			File fileVersion = new File("version.properties");
			FileInputStream fileInput = new FileInputStream(file);
			FileInputStream fileInputVersion = new FileInputStream(fileVersion);
			Properties properties = new Properties();
			properties.load(fileInput);
			properties.load(fileInputVersion);
			fileInput.close();
			fileInputVersion.close();
		
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
			add(new JLabel(new ImageIcon("res/logo.png")));
		    add(teamName); 
		    add(teamDesc);
			log.info("window is open");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
