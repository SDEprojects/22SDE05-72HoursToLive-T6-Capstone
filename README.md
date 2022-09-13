# 22SDE05-Project2

**Description**

72HoursToLive is a text-based adventure game. At the start of the game, you would
find yourself in a random room. There is a total of 16 rooms. You goal would be to
navigate to the throne room, kill the Werewolf King and obtain a sample of his
blood. As you navigate through the rooms, you may encounter several werewolf
attacks. You may choose to fight back by typing “attack” or simply move on to the
next room by typing “go <direction>”. 
After obtaining the werewolf kings blood sample, navigate back to the time portal
where you use the blood to exit the portal. 

**At any point in the game, you can type:**
- Help to see a list of menu options available to you.
- Look to see a list of items available for your use.
- Map to see a map of the game and what room you are presently in
- Music to start or stop the music from playing
- Pickup <item> to pick an add an item to your inventory
- Equip <item> to utilize and item from your inventory

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
- Controller classes include:  
  - GameController, GameSettings, InputScanner, Response, & Text Parser. 
- A Client Class that is used to start/restart the game.
- A resource directory is also included for additional resources such as music, json files, & string contents.
  
**Roadmap:**
Future releases of the game would include:
- The ability to pause and resume the game.
- A graphic user interface (GUI) version of the game
  
**Support:**
For support in regards to this project,  reach out to a team member through
henblair@amazon.com, tairobea@amazon.com, adsalami@amazon.com, ebenadda@amazon.com 
  
**Contributing:**
  
The team is open to suggestions on how to improve the game. Reach out to a team
member   through henblair@amazon.com, tairobea@amazon.com, adsalami@amazon.com, ebenadda@amazon.com

  **Authors and acknowledgment:**

This project was produced by the collaborative effort of Blair Hendrickson, Robert
Tai, Adewale Salami, and Ebenezer Addae with supervision from Rennie Araucto and
Technical advice from Tom Fulton.
  
**Project Status:**
  
This project was part of a Capstone Project for an SDE apprenticeship training. Due
to time and requirement constrains, members were not able to implement some
intended features and plan to do so in the future.  

**Additional Notes:**

- When you are in the map you have to press enter to exit the map before
putting in the next command
- Windows users may not see text color: 
  - If desired, you may request a new JAR file for Windows users. Please email a contributor to make this request.
  
- Typing music to turn on/off the sound does not work in the:
  - Difficulty selection level
  - Story line stage
