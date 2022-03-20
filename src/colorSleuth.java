import java.awt.Color;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Random;
import javax.swing.*;

// color sleuth - figure out which of the four colored blocks is off colored
public class colorSleuth extends JFrame {
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) throws InterruptedException {
		colorSleuth frame = new colorSleuth("off");
		frame.setVisible(true);
	}
	
	public colorSleuth(String version){
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
		
		JLabel titleLabel = new JLabel("C   O   L   O   R");
		titleLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 45));
		titleLabel.setForeground(Color.BLACK);
		titleLabel.setBounds(150, 50, 400, 45);
		contentPane.add(titleLabel);
		
		// game title
		JLabel titleLabel2 = new JLabel("S  L  E  U  T  H");
		titleLabel2.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 45));
		titleLabel2.setForeground(Color.BLACK);
		titleLabel2.setBounds(150, 100, 400, 45);
		contentPane.add(titleLabel2);
		
		JButton colorblock1 = new JButton("");
		colorblock1.setBounds(150, 200, 60, 60);
		colorblock1.setBorderPainted(false);
		colorblock1.setOpaque(true);
		
		// colored blocks on home screen that change color every second
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
        
		// how to play - provide instructions for players
		JButton instructions = new JButton("H O W   T O   P L A Y");
		instructions.setBounds(150, 300, 300, 40);
		instructions.setBorderPainted(false);
		instructions.setOpaque(true);
		instructions.setBackground(new Color(159,181, 235));
		instructions.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		instructions.setForeground(Color.WHITE);
		instructions.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(instructions);
		
		// normal mode - no timer involved
		JButton playBtn = new JButton("N O R M A L   M O D E");
		playBtn.setBounds(150, 350, 300, 40);
		playBtn.setBorderPainted(false);
		playBtn.setOpaque(true);
		playBtn.setBackground(new Color(159,181, 225));
		playBtn.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		playBtn.setForeground(Color.WHITE);
		playBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(playBtn);
		
		// frenzy mode - 5 second timer
		JButton frenzyBtn = new JButton("F R E N Z Y   M O D E");
		frenzyBtn.setBounds(150, 400, 300, 40);
		frenzyBtn.setBorderPainted(false);
		frenzyBtn.setOpaque(true);
		frenzyBtn.setBackground(new Color(159,181, 215));
		frenzyBtn.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		frenzyBtn.setForeground(Color.WHITE);
		frenzyBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(frenzyBtn);
		
		// make the colors colorblind friendly for more accessibility
		JButton colorblindFeature = new JButton("C O L O R B L I N D   O F F");
		colorblindFeature.setBounds(150, 450, 300, 40);
		colorblindFeature.setBorderPainted(false);
		colorblindFeature.setOpaque(true);
		colorblindFeature.setBackground(new Color(159,181, 205));
		colorblindFeature.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		colorblindFeature.setForeground(Color.WHITE);
		colorblindFeature.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(colorblindFeature);
		// tell player that a colorblind feature is enabled
		if(version == "prot") {
			colorblindFeature.setText("C O L O R B L I N D (PROT)  O N");
		}
		else if(version == "trit") {
			colorblindFeature.setText("C O L O R B L I N D (TRIT)  O N");
		}
		else if(version == "deut") {
			colorblindFeature.setText("C O L O R B L I N D (DEUT)  O N");
		}
		else if(version == "off") {
			colorblindFeature.setText("C O L O R B L I N D   O F F");
		}
		
	    // access instruction page
		instructions.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	instructionPage second = new instructionPage();   
		        setVisible(false); // hide current frame
		        second.setVisible(true);	
		    }
		});
		
		// normal mode - no timer
		playBtn.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	normalMode normalGame = new normalMode(version);   
		        setVisible(false); // hide current frame
		        normalGame.setVisible(true);	
		    }
		});
		
		// frenzy mode - 5 second timer
		frenzyBtn.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	frenzyMode frenzyGame = new frenzyMode(version);   
		        setVisible(false); // hide current frame
		        frenzyGame.setVisible(true);	
		    }
		});
		
		// colorblind feature - changes colors of the game to be more colorblind friendly
		colorblindFeature.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	colorblindOptions colorblindOpt = new colorblindOptions(version);   
		        setVisible(false); // hide current frame
		        colorblindOpt.setVisible(true);	
		    }
		});
		
		// adding exit button
		JButton exitButton = new JButton("E X I T");
		exitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				int result = JOptionPane.showConfirmDialog(null, "Do you really want to exit Color Sleuth?", "Exit Application", JOptionPane.INFORMATION_MESSAGE);
				if(result == JOptionPane.OK_OPTION){
					dispose();
					System.exit(0);
				}
			}
		});
		exitButton.setBounds(150, 500, 300, 40);
		exitButton.setBorderPainted(false);
		exitButton.setOpaque(true);
		exitButton.setBackground(new Color(159,181, 195));
		exitButton.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		exitButton.setForeground(Color.WHITE);
		exitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(exitButton);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}