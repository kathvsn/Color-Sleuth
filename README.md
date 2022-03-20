# Color Sleuth
### A simple color based game developed with Java and the Swing GUI widget toolkit.

## Instructions
Four colored blocks will appear on the screen. Three of the blocks will be the same color while the last block will be slightly off colored. The goal of the game is to see how many off colored blocks a player can identify!

In the normal mode, it is up to the player to figure out which of the four blocks is off colored! A player has 3 lives and once they hit 0, the game ends. In the frenzy mode, there are only 5 seconds to click the correct block. It is better to lose a life than to let the timer run out as it is an automatic loss if the timer reaches 0 even if there are lives remaining. If the player runs out of lives or runs out of time, the game ends. 

A colorblind feature will alter the game modes to make colors more colorblind-friendly. Options exist for Protanopia (red blind), Tritanopia (blue blind), and Deuteranopia (green blind). 

<table align = "center">
  <tr>
    <td>Normal Mode</td>
    <td>Frenzy Mode</td>
  </tr>
  <tr>
    <td><img src="https://i.imgur.com/7CQPBCT.gif" width=280 height=300></td>
    <td><img src="https://i.imgur.com/ojlZas2.gif" width=280 height=300></td>
  </tr>
 </table>

## Colorblind Features
The goal of the game focuses on detecting a difference in the brightness of a color, but to provide more accessibility, colorblind features are available to players. Depending on the option chosen, a specific palette of colors will be generated. Protanopia is blindness to red; therefore, the game will generate more yellow and blue colors, and traditionally red colors will appear more green. Tritanopia is blindness to blue, and the game will generate more green and violet colors as traditionally blue and yellow colors will appear this way. Deuteranopia is blindness to green; therefore, the game will generate more yellow and blue colors, and traditionally green colors will appear more red. 

<table align = "center">
  <tr>
    <td>Protanopia</td>
    <td>Tritanopia</td>
    <td>Deuteranopia</td>
  </tr>
  <tr>
    <td><img src="https://i.imgur.com/s56sfAq.gif" width=280 height=300></td>
    <td><img src="https://i.imgur.com/lOEqIRw.gif" width=280 height=300></td>
    <td><img src="https://i.imgur.com/xMCtMnu.gif" width=280 height=300></td>
  </tr>
 </table>

## Scores
The program will create files called ```normalScores.txt``` and ```frenzyScores.txt``` and write the player's highest score for each game mode. The program will read in the files to compare the player's current score to their highest score.

## Installation
To install this program, simply download the project as a ZIP file. Then, open the project folder in your IDE of choice and hit "Run" on the ```colorSleuth.java``` file.
