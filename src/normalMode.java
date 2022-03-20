import java.awt.Color;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Random;
import javax.swing.*;

// normal mode - no timer and the goal is to see how many blocks you can correctly identify as the off colored block
public class normalMode extends JFrame {
	private static final long serialVersionUID = 1L;	
	private boolean correctBlock1, correctBlock2, correctBlock3, correctBlock4;
	// score is initially 0 and player starts off with 3 lives
	private int score = 0;
	private int lives = 3;
	private boolean lost = false;
	// colored blocks
	private JButton colorblock1 = new JButton("");
	private JButton colorblock2 = new JButton("");
	private JButton colorblock3 = new JButton("");
	private JButton colorblock4 = new JButton("");
	int r,g,b = 0;
	// determines if a colorblind feature is enabled
	private String currVersion;
	
	// get the rgb values of the color to change the three blocks
	int[] changeColorblock() {
		Random rand = new Random();
		if(currVersion == "off") {
			r = rand.nextInt((255 - 0) + 1) + 0;
			g = rand.nextInt((255 - 0) + 1) + 0;
			b = rand.nextInt((255 - 0) + 1) + 0;
		}
		else if(currVersion == "prot") {
			// protanopia is blindness to red
			// more yellow and blue colors will be generated and red colors appear green
			int[] protR = {204,185,167,148,130,111,93,74,56,37,19,0};
			int[] protG = {153,144,134,125,116,107,97,88,79,70,60,51};
			int[] protB = {0,24,38,52,66,80,93,107,121,135,149,163};
			
			Random numRand = new Random();
			int min = 0;
			int max = 11;
			int randomNum = numRand.nextInt((max - min) + 1) + min;
			int randomAdd = numRand.nextInt((50 - 20) + 1) + 20;
			
			r = protR[randomNum];
			g = protG[randomNum];
			b = protB[randomNum] + randomAdd;
			
		}
		else if(currVersion == "trit") {
			// tritanopia is blindness to blue 
			// blue appears more like green and yellow appears more like violet
			int[] tritR = {255,236,218,199,181,162,144,125,107,88,70,51};
			int[] tritG = {0,19,37,56,74,93,111,130,148,167,185,204};
			int[] tritB = {0,19,37,56,74,93,111,130,148,167,185,204};
			
			Random numRand = new Random();
			int min = 0;
			int max = 11;
			int randomNum = numRand.nextInt((max - min) + 1) + min;
			
			r = tritR[randomNum];
			g = tritG[randomNum];
			b = tritB[randomNum];
			
		}
		else if(currVersion == "deut") {
			// deuteranopia is blindness to green
			// more yellow and blue colors will be generated and green colors appear red
			int[] deutR = {204,185,167,148,130,111,93,74,56,37,19,0};
			int[] deutG = {153,144,134,125,116,107,97,88,79,70,60,51};
			int[] deutB = {0,14,28,42,56,70,83,97,111,125,139,153};
			
			Random numRand = new Random();
			int min = 0;
			int max = 11;
			int randomNum = numRand.nextInt((max - min) + 1) + min;
			int randomAdd = numRand.nextInt((50 - 20) + 1) + 20;
			
			r = deutR[randomNum] + randomAdd;
			g = deutG[randomNum];
			b = deutB[randomNum];
			
		}
		
		int[] colorVals = {r, g, b};
		return colorVals;
	}
	
	// use the same rgb values to create the off color
	// add 20 if the value does not exceed 255 or subtract 20 if the value is not negative
	Color changeOffColorblock(int red, int green, int blue) {
		
		if(red+20 <= 255) {
			red = red + 20;
		}
		else if(red-20 >= 0) {
			red = red - 20;
		}
		
		if(green+20 <= 255) {
			green = green + 20;
		}
		else if(green-20 >= 0) {
			green = green - 20;
		}
		
		if(blue+20 <= 255) {
			blue = blue + 20;
		}
		else if(blue-20 >= 0) {
			blue = blue - 20;
		}
		
		// create off color using the altered values
		Color offColors = new Color(red, green, blue);
		return offColors;
	}
	
	// set the background color of the blocks and decide randomly which of the blocks will be off colored
	void setBgCol() {
		// array that stores the rgb values
		int[] colorVals = changeColorblock();
		// set the color for the three blocks and the off colored block
		Color randomColor = new Color(colorVals[0], colorVals[1], colorVals[2]);
		Color offColorblock = changeOffColorblock(colorVals[0], colorVals[1], colorVals[2]);
			
		// randomly decide which of the four blocks will be off colored
		Random numRand = new Random();
		int min = 1;
		int max = 4;
		int randomNum = numRand.nextInt((max - min) + 1) + min;
		
		// use booleans to keep track of which block is correct; the correct block is the block that is off colored
		if(randomNum == 1) {
			colorblock1.setBackground(offColorblock);
			correctBlock1 = true; correctBlock2 = false; correctBlock3 = false; correctBlock4 = false;
			colorblock2.setBackground(randomColor);
			colorblock3.setBackground(randomColor);
			colorblock4.setBackground(randomColor);
		}
		else if(randomNum == 2) {
			colorblock1.setBackground(randomColor);
			colorblock2.setBackground(offColorblock);
			correctBlock1 = false; correctBlock2 = true; correctBlock3 = false; correctBlock4 = false;
			colorblock3.setBackground(randomColor);
			colorblock4.setBackground(randomColor);
		}
		else if(randomNum == 3) {
			colorblock1.setBackground(randomColor);
			colorblock2.setBackground(randomColor);
			colorblock3.setBackground(offColorblock);
			correctBlock1 = false; correctBlock2 = false; correctBlock3 = true; correctBlock4 = false;
			colorblock4.setBackground(randomColor);
		}
		else if(randomNum == 4) {
			colorblock1.setBackground(randomColor);
			colorblock2.setBackground(randomColor);
			colorblock3.setBackground(randomColor);
			colorblock4.setBackground(offColorblock);
			correctBlock1 = false; correctBlock2 = false; correctBlock3 = false; correctBlock4 = true;
		}
	}
	
	public normalMode(String version){
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
						String title = "Color Sleuth - Normal Mode | " + str;
						setTitle(title);
						sleep(1000L);
					}catch(InterruptedException e) {}
					finally{}
				}
			}
        };
        refreshAllTitleBar.start();
		
        // title of the game
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
		colorblock1.setBounds(150, 200, 130, 130);
		colorblock1.setBorderPainted(false);
		colorblock1.setOpaque(true);
		colorblock1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		colorblock2.setBounds(310, 200, 130, 130);
		colorblock2.setBorderPainted(false);
		colorblock2.setOpaque(true);
		colorblock2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		colorblock3.setBounds(150, 360, 130, 130);
		colorblock3.setBorderPainted(false);
		colorblock3.setOpaque(true);
		colorblock3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		colorblock4.setBounds(310, 360, 130, 130);
		colorblock4.setBorderPainted(false);
		colorblock4.setOpaque(true);
		colorblock4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		contentPane.add(colorblock1);
		contentPane.add(colorblock2);
		contentPane.add(colorblock3);
		contentPane.add(colorblock4);
		
		// lives that a player has
        JLabel liveLabel = new JLabel("LIVES: 3");
        liveLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
        liveLabel.setForeground(Color.BLACK);
        liveLabel.setBounds(150, 550, 200, 15);
		contentPane.add(liveLabel);
		
		// player's score
		JLabel scoreLabel = new JLabel("SCORE: 0");
		scoreLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		scoreLabel.setForeground(Color.BLACK);
		scoreLabel.setBounds(310, 550, 200, 15);
		contentPane.add(scoreLabel);
		
		// initialize block colors as the game begins
		setBgCol();
			
		// increase score if correct button is clicked
		// decrease number of lives if wrong button is clicked and if the num of lives is negative, send player to end screen
		colorblock1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				if(correctBlock1 == true) {
					score += 1;
					scoreLabel.setText("SCORE: " + score);
					setBgCol();
				}
				else if(correctBlock1 == false && lives > 0) {
					lives -= 1;
					if(lives <= 0) {
						lost = true;
						endScreen lostScreen = new endScreen(score, "normal");   
					    setVisible(false); // hide current frame
					    lostScreen.setVisible(true);
					}
					else {
						liveLabel.setText("LIVES: " + lives);
						setBgCol();
					}
				}
				else {
					lost = true;
				}
					
			}
		});
		colorblock2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				if(correctBlock2 == true) {
					score += 1;
					scoreLabel.setText("SCORE: " + score);
					setBgCol();
				}
				else if(correctBlock2 == false && lives > 0) {
					lives -= 1;
					if(lives <= 0) {
						lost = true;
						endScreen lostScreen = new endScreen(score, "normal");   
					    setVisible(false); // hide current frame
					    lostScreen.setVisible(true);
					}
					else {
						liveLabel.setText("LIVES: " + lives);
						setBgCol();
					}
				}
				else {
					lost = true;
				}
					
			}
		});
		colorblock3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				if(correctBlock3 == true) {
					score += 1;
					scoreLabel.setText("SCORE: " + score);
					setBgCol();
				}
				else if(correctBlock3 == false && lives > 0) {
					lives -= 1;
					if(lives <= 0) {
						lost = true;
						endScreen lostScreen = new endScreen(score, "normal");   
					    setVisible(false); // hide current frame
					    lostScreen.setVisible(true);
					}
					else {
						liveLabel.setText("LIVES: " + lives);
						setBgCol();
					}
				}
				else {
					lost = true;
				}
					
			}
		});
		colorblock4.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				if(correctBlock4 == true) {
					score += 1;
					scoreLabel.setText("SCORE: " + score);
					setBgCol();
				}
				else if(correctBlock4 == false && lives > 0) {
					lives -= 1;
					if(lives <= 0) {
						endScreen lostScreen = new endScreen(score, "normal");   
					    setVisible(false); // hide current frame
					    lostScreen.setVisible(true);
					}
					else {
						liveLabel.setText("LIVES: " + lives);
						setBgCol();
					}
				}
				else {
					lost = true;
				}
					
			}
		});
		
		// send player to end screen if they lost 
		if(lost == true) {
			endScreen lostScreen = new endScreen(score, "normal");   
		    setVisible(false); // hide current frame
		    lostScreen.setVisible(true);
		}
		
		// adding quit button - returns to home screen 
		JButton quitBtn = new JButton("Q U I T");
		quitBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				colorSleuth first = new colorSleuth("off");   
		        setVisible(false); // hide current frame
		        first.setVisible(true);	
			}
		});
		quitBtn.setBounds(220, 600, 150, 40);
		quitBtn.setBorderPainted(false);
		quitBtn.setOpaque(true);
		quitBtn.setBackground(new Color(159,181, 195));
		quitBtn.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		quitBtn.setForeground(Color.WHITE);
		quitBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(quitBtn);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}