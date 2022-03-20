import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.*;

// instruction page which invites players to play the game and explains how to play the game
public class instructionPage extends JFrame {
	private static final long serialVersionUID = 1L;
	public instructionPage(){
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
		
		// instructions
		String para = "Four colored blocks will appear on the screen. "
				+ "Three of the blocks will be the same color while the last block will be slightly off colored. "
				+ "<br><br>In the normal mode, it is up to the player to figure out which of the four blocks is off colored! A player has 3 lives and once they hit 0, the game ends. "
				+ "<br><br>In the frenzy mode, there are only 5 seconds to click the correct block. It is better to lose a life than to let the timer run out as it is an automatic loss if the timer reaches 0 even if there are lives remaining. If the player runs out of lives or runs out of time, the game ends.  "
				+ "<br><br>A colorblind feature will alter the game modes to make colors more colorblind-friendly. Options exist for Protanopia (red blind), Tritanopia (blue blind), and Deuteranopia (green blind)." 
				+ "<br><br>The goal of the game is to see how many off colored blocks a player can identify!";
		JLabel instructionParagraph = new JLabel("<html>"+para+"</html>");
		instructionParagraph.setFont(new Font("Arial Rounded MT", Font.PLAIN, 15));
		instructionParagraph.setForeground(Color.BLACK);
		instructionParagraph.setBounds(50, 170, 500, 400);
		contentPane.add(instructionParagraph);
		
		// return to home screen
		JButton back = new JButton("B A C K");
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