import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Random;
import javax.swing.*;

// colorblind page which gives players the option to change the colors of the game to be more colorblind friendly
public class colorblindOptions extends JFrame {
	private static final long serialVersionUID = 1L;
	private String currVersion;
	public colorblindOptions(String version){
		currVersion = version;
		// set size
		setSize(600, 700);
		JPanel contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// set background color 
		getContentPane().setBackground(new Color(255, 252, 247));
		
		// real time clock 
		Thread refreshAllTitleBar = new Thread(){
			public void run(){
				while (true){
					try {
						// Display time
						Date  date = new Date();
						String str = String.format("%tc", date);
						String title = "Color Sleuth | " + str;
						setTitle(title);
						sleep(1000L);
					}catch(InterruptedException e) {}
					finally{}
				}
			}
        };
        refreshAllTitleBar.start();
		
        // game title
		JLabel titleLabel = new JLabel("C   O   L   O   R");
		titleLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 45));
		titleLabel.setForeground(Color.BLACK);
		titleLabel.setBounds(150, 50, 400, 45);
		contentPane.add(titleLabel);
		
		JLabel titleLabel2 = new JLabel("S  L  E  U  T  H");
		titleLabel2.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 45));
		titleLabel2.setForeground(Color.BLACK);
		titleLabel2.setBounds(150, 100, 400, 45);
		contentPane.add(titleLabel2);
		
		// colored blocks
		JButton colorblock1 = new JButton("");
		colorblock1.setBounds(150, 200, 60, 60);
		colorblock1.setBorderPainted(false);
		colorblock1.setOpaque(true);
		
		JButton colorblock2 = new JButton("");
		colorblock2.setBounds(230, 200, 60, 60);
		colorblock2.setBorderPainted(false);
		colorblock2.setOpaque(true);
		
		JButton colorblock3 = new JButton("");
		colorblock3.setBounds(310, 200, 60, 60);
		colorblock3.setBorderPainted(false);
		colorblock3.setOpaque(true);
		
		JButton colorblock4 = new JButton("");
		colorblock4.setBounds(390, 200, 60, 60);
		colorblock4.setBorderPainted(false);
		colorblock4.setOpaque(true);
		
		contentPane.add(colorblock1);
		contentPane.add(colorblock2);
		contentPane.add(colorblock3);
		contentPane.add(colorblock4);
		
		Timer colorTimer = new Timer(1000, ae -> {
			Random rand = new Random();
			
			float r = rand.nextFloat();
			float g = rand.nextFloat();
			float b = rand.nextFloat();
			
			Color randomColor = new Color(r, g, b);
			
			colorblock1.setBackground(randomColor);
			colorblock2.setBackground(randomColor);
			colorblock3.setBackground(randomColor);
			colorblock4.setBackground(randomColor);
   
        });

        colorTimer.start();
		
		// protanopia - blindness to red
		JButton protanopia = new JButton("P R O T A N O P I A");
		protanopia.setBounds(150, 300, 300, 40);
		protanopia.setBorderPainted(false);
		protanopia.setOpaque(true);
		protanopia.setBackground(new Color(159,181, 235));
		protanopia.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		protanopia.setForeground(Color.WHITE);
		contentPane.add(protanopia);
		protanopia.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		protanopia.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	currVersion = "prot";
		    	colorSleuth first = new colorSleuth("prot");   
		        setVisible(false); // hide current frame
		        first.setVisible(true);	
		    }
		});
		
		// tritanopia - blindness to blue
		JButton tritanopia = new JButton("T R I T A N O P I A");
		tritanopia.setBounds(150, 350, 300, 40);
		tritanopia.setBorderPainted(false);
		tritanopia.setOpaque(true);
		tritanopia.setBackground(new Color(159,181, 235));
		tritanopia.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		tritanopia.setForeground(Color.WHITE);
		contentPane.add(tritanopia);
		tritanopia.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tritanopia.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	currVersion = "trit";
		    	colorSleuth first = new colorSleuth("trit");   
		        setVisible(false); // hide current frame
		        first.setVisible(true);	
		    }
		});
		
		// deuteranopia - blindness to green
		JButton deuteranopia = new JButton("D E U T E R A N O P I A");
		deuteranopia.setBounds(150, 400, 300, 40);
		deuteranopia.setBorderPainted(false);
		deuteranopia.setOpaque(true);
		deuteranopia.setBackground(new Color(159,181, 235));
		deuteranopia.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		deuteranopia.setForeground(Color.WHITE);
		contentPane.add(deuteranopia);
		deuteranopia.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		deuteranopia.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	currVersion = "deut";
		    	colorSleuth first = new colorSleuth("deut");   
		        setVisible(false); // hide current frame
		        first.setVisible(true);	
		    }
		});
		
		// turn off colorblind features
		JButton off = new JButton("O F F");
		off.setBounds(150, 450, 300, 40);
		off.setBorderPainted(false);
		off.setOpaque(true);
		off.setBackground(new Color(159,181, 235));
		off.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		off.setForeground(Color.WHITE);
		contentPane.add(off);
		off.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		off.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	currVersion = "off";
		    	colorSleuth first = new colorSleuth("off");   
		        setVisible(false); // hide current frame
		        first.setVisible(true);	
		    }
		});
		
		// return to home screen
		JButton back = new JButton("B A C K");
		back.setBounds(150, 500, 300, 40);
		back.setBorderPainted(false);
		back.setOpaque(true);
		back.setBackground(new Color(159,181, 235));
		back.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		back.setForeground(Color.WHITE);
		contentPane.add(back);
		back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		back.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	colorSleuth first = new colorSleuth(currVersion);   
		        setVisible(false); // hide current frame
		        first.setVisible(true);	
		    }
		});

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}