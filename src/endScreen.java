import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Random;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;
import javax.swing.*;

// player lost either by running out of chances or running out of time
public class endScreen extends JFrame {
	private static final long serialVersionUID = 1L;
	private int highScore;
	public endScreen(int endScore, String gamemode){
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
		
        // print a message to tell player they lost
		JLabel endMsg = new JLabel("YOU LOST!");
		endMsg.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 55));
		endMsg.setForeground(Color.BLACK);
		endMsg.setBounds(150, 310, 500, 55);
		contentPane.add(endMsg);
		
		JLabel endDash = new JLabel("-----------------");
		endDash.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 55));
		endDash.setForeground(Color.BLACK);
		endDash.setBounds(150, 350, 500, 55);
		contentPane.add(endDash);
		
		// print player's score for the game mode
		JLabel scoreMsg = new JLabel("Score: " + endScore);
		scoreMsg.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 30));
		scoreMsg.setForeground(Color.BLACK);
		scoreMsg.setBounds(150, 410, 500, 35);
		contentPane.add(scoreMsg);
		
		// create new file and write scores to the file
		// read file if it exists and use previous values to determine if a new high score has been achieved
		try {
			if (gamemode == "normal") {
				File normFile = new File("normalScores.txt");
				if (normFile.createNewFile()) {
					System.out.println("File created: " + normFile.getName());
					highScore = endScore;
	    			FileWriter myWriter = new FileWriter("normalScores.txt", false);
	    		    myWriter.write(Integer.toString(highScore));
	    		    myWriter.close();
			    } else {
			    	System.out.println("File already exists.");
			    	Scanner myReader = new Scanner(normFile);
			    	while (myReader.hasNextLine()) {
			    		String data = myReader.nextLine();
			    		int currHigh = Integer.parseInt(data);
			    		if (currHigh > endScore) {
			    			highScore = currHigh;
			    		}
			    		else {
			    			highScore = endScore;
			    			FileWriter myWriter = new FileWriter("normalScores.txt", false);
			    		    myWriter.write(Integer.toString(highScore));
			    		    myWriter.close();
			    		}
			        }
			    	myReader.close();
			    }
			}
			else if (gamemode == "frenzy") {
				File frenzyFile = new File("frenzyScores.txt");
				if (frenzyFile.createNewFile()) {
					System.out.println("File created: " + frenzyFile.getName());
					highScore = endScore;
	    			FileWriter myWriter = new FileWriter("frenzyScores.txt", false);
	    		    myWriter.write(Integer.toString(highScore));
	    		    myWriter.close();
			    } else {
			    	System.out.println("File already exists.");
			    	Scanner myReader = new Scanner(frenzyFile);
			    	while (myReader.hasNextLine()) {
			    		String data = myReader.nextLine();
			    		int currHigh = Integer.parseInt(data);
			    		if (currHigh > endScore) {
			    			highScore = currHigh;
			    		}
			    		else {
			    			highScore = endScore;
			    			FileWriter myWriter = new FileWriter("frenzyScores.txt", false);
			    		    myWriter.write(Integer.toString(highScore));
			    		    myWriter.close();
			    		}
			        }
			    	myReader.close();
			    }
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
		    e.printStackTrace();
		}
		
		// print high score of the player
		JLabel highScoreMsg = new JLabel("High Score: " + highScore);
		highScoreMsg.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 30));
		highScoreMsg.setForeground(Color.BLACK);
		highScoreMsg.setBounds(150, 470, 500, 35);
		contentPane.add(highScoreMsg);
		
		// return to home screen
		JButton back = new JButton("P L A Y   A G A I N");
		back.setBounds(150, 600, 300, 40);
		back.setBorderPainted(false);
		back.setOpaque(true);
		back.setBackground(new Color(159,181, 235));
		back.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		back.setForeground(Color.WHITE);
		contentPane.add(back);
		back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		back.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	colorSleuth first = new colorSleuth("off");   
		        setVisible(false); // hide current frame
		        first.setVisible(true);	
		    }
		});

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}