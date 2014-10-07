package ee.ut.math.tvt.t3m;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
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
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		FileInputStream fileInput = null;
		FileInputStream fileInputVersion = null;
		
		try {
			fileInput = new FileInputStream(new File("application.properties"));
			fileInputVersion = new FileInputStream(new File("version.properties"));
			Properties properties = new Properties();
			properties.load(fileInput);
			properties.load(fileInputVersion);
		
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
			log.error("File not found", e);
		} catch (IOException e) {
			log.error("IOException", e);
		} finally{
			try{
				fileInput.close();
				fileInputVersion.close();
			}catch(RuntimeException rte){
				//ignore
			} catch (IOException e) {
				//ignore
			}
		}
		
	}
}
