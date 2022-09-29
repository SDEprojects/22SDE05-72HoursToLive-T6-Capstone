# 22SDE05-Project2

**Description**

72HoursToLive is an adventure game. At the start of the game, you will
find yourself in a random room. There is a total of 16 rooms. Your goal will be to
navigate to the throne room, kill the Werewolf King and obtain a sample of his
blood. As you navigate through the rooms, you may encounter several werewolf
attacks. You may choose to fight back by pressing “attack” or simply move on to the
next room by pressing directional buttons. 
After obtaining the Werewolf King's blood sample, navigate back to the time portal
where you use the blood-sample to exit the portal. 

**At any point in the game, you can press the following buttons:**
- "Help" to see a list of options available to you.
- "Map" to see a map of the rooms.
- "Settings" to manipulate sound effects and music.
- "Quit" to exit the game.

**To play the game using the jar file:**
- Download the zip/jar file
- Extract/move the zip/jar file to a specified location
- Open a new terminal 
- Navigate to the location of your jar file in your terminal
- Type the command "java -jar 22SDE05-72HoursToLive.jar" and enter. 
- Follow the prompts on the screen to play your game
  
**Code Design Pattern:**
This code was architected with the Model-View-Controller Design Pattern in mind.
- Model classes include:
  - Character, Room, RoomMovement, Soldier, Werewolf, & WerewolfKing.
- View classes include:  
  - Art, GameMap, Music, Story, TextColor, & View.
- GUI classes include:  
  - BackgroundPanel, Controller, EndingMenu, FullMoon, GamePlay, GUI, Map, PanelSetup, SettingsMenu, StartMenu, UpdatePanel 
- A Client Class that is used to start/restart the game.
- A resource directory is also included for additional resources such as music, json files, & string contents, and images
  
**Roadmap:**
Future releases of the game would include:
- The ability to save and load the game
  
**Support:**
For support in regard to this project,  reach out to a team member through the following GitHub accounts:
henblair, robtai29, adsalami, ebenadda, ruanek, sandesh2041, averagetec

  
**Contributing:**
We are open to suggestions on how to improve the game. Reach out to a team member through the following GitHub accounts:
henblair, robtai29, adsalami, ebenadda, ruanek, sandesh2041, averagetec

**Authors and acknowledgment:**
This project was produced by the collaborative effort of Blair Hendrickson, Robert
Tai, Adewale Salami, and Ebenezer Addae, Kyle Ruane, Sam Maskey, and Jay Flowers.
  
**Project Status:**
This project was part of a Capstone Project for an SDE apprenticeship training. Due
to time and requirement constrains, members were not able to implement some
intended features and plan to do so in the future.

