package main.java.view;


import java.util.ResourceBundle;
import java.util.Scanner;

public class Story {
    Scanner scanner = new Scanner(System.in);
    public static int difficulty = 0;
    private static boolean isRunning = true;

    private static final ResourceBundle bundle = ResourceBundle.getBundle("main.resources.strings");

    public void titleScreen() {

        String menu = "===========================================================================================================================================\n";
        String infoMenu = "======================================================================\n";
        String titleBanner =

                "\n███████╗███████╗██╗   ██╗███████╗███╗   ██╗████████╗██╗   ██╗    ████████╗██╗    ██╗ ██████╗     ██╗  ██╗ ██████╗ ██╗   ██╗██████╗ ███████╗\n" +
                        "██╔════╝██╔════╝██║   ██║██╔════╝████╗  ██║╚══██╔══╝╚██╗ ██╔╝    ╚══██╔══╝██║    ██║██╔═══██╗    ██║  ██║██╔═══██╗██║   ██║██╔══██╗██╔════╝\n" +
                        "███████╗█████╗  ██║   ██║█████╗  ██╔██╗ ██║   ██║    ╚████╔╝        ██║   ██║ █╗ ██║██║   ██║    ███████║██║   ██║██║   ██║██████╔╝███████╗\n" +
                        "╚════██║██╔══╝  ╚██╗ ██╔╝██╔══╝  ██║╚██╗██║   ██║     ╚██╔╝         ██║   ██║███╗██║██║   ██║    ██╔══██║██║   ██║██║   ██║██╔══██╗╚════██║\n" +
                        "███████║███████╗ ╚████╔╝ ███████╗██║ ╚████║   ██║      ██║          ██║   ╚███╔███╔╝╚██████╔╝    ██║  ██║╚██████╔╝╚██████╔╝██║  ██║███████║\n" +
                        "╚══════╝╚══════╝  ╚═══╝  ╚══════╝╚═╝  ╚═══╝   ╚═╝      ╚═╝          ╚═╝    ╚══╝╚══╝  ╚═════╝     ╚═╝  ╚═╝ ╚═════╝  ╚═════╝ ╚═╝  ╚═╝╚══════╝\n" +
                        "                                                                                                                                           \n";

        String infoBanner =
                " ██████╗  █████╗ ███╗   ███╗███████╗    ██╗███╗   ██╗███████╗ ██████╗ \n" +
                        "██╔════╝ ██╔══██╗████╗ ████║██╔════╝    ██║████╗  ██║██╔════╝██╔═══██╗\n" +
                        "██║  ███╗███████║██╔████╔██║█████╗      ██║██╔██╗ ██║█████╗  ██║   ██║\n" +
                        "██║   ██║██╔══██║██║╚██╔╝██║██╔══╝      ██║██║╚██╗██║██╔══╝  ██║   ██║\n" +
                        "╚██████╔╝██║  ██║██║ ╚═╝ ██║███████╗    ██║██║ ╚████║██║     ╚██████╔╝\n" +
                        " ╚═════╝ ╚═╝  ╚═╝╚═╝     ╚═╝╚══════╝    ╚═╝╚═╝  ╚═══╝╚═╝      ╚═════╝ \n" +
                        "                                                                      \n";


        System.out.println(menu + titleBanner + menu);
        sleep(1); //change to 3000
        System.out.println(bundle.getString("title_screen"));
        while (true) {
            String intro = scanner.next();
            if (intro.equalsIgnoreCase("play")) {
                System.out.println(bundle.getString("start_game"));
                sleep(1000);
                break;
            } else if (intro.equalsIgnoreCase("help")) {
                System.out.println(infoMenu + "\n" + infoBanner + infoMenu);
                sleep(1800);
                System.out.println("You have been selected to go back in time and save humanity, you must\n" +
                        "find the first werewolf, collect his blood and escape through the\nTime Portal before" +
                        " 72 hours runs out and the portal closes.\n");
                System.out.println("You can go to a room by typing \"go [direction]\"\n" +
                        "You can use an item by typing \"use [item]\"\n" +
                        "You can equip armor and weapons by typing \"equip [item]\"\n" +
                        "You can attack a werewolf by typing \"attack wolf\"\n" +
                        "You can look for items in a room by typing \"look\"\n" +
                        "You can check your inventory by typing \"inventory\"\n" +
                        "You can quit the game by typing \"quit\"\n");
                System.out.println("Directions are: North, East, South, West\n");
                sleep(1500);
                System.out.println("Press enter to return to the menu screen...");
                String readString = scanner.nextLine();
                if (scanner.hasNextLine()) {
                    titleScreen();
                    break;
                }
            } else if (intro.equalsIgnoreCase("quit")) {
                System.out.println("Quitting the game...");
                sleep(1000);
                System.exit(0);
            } else {
                System.out.println("Invalid input. Valid options are play, help, or quit. Please try again.");
            }

        }
    }
    public void selectDifficulty(){
        while(true){
            System.out.println("Select a difficulty: Easy, Medium, Hard, or Impossible.");
            String choice = scanner.next();
            if(choice.equalsIgnoreCase("easy")){
                System.out.println("You have selected easy mode.");
                difficulty = -2;
                sleep(1000);
                break;
            } else if(choice.equalsIgnoreCase("medium")){
                System.out.println("You have selected medium mode.");
                sleep(1000);
                difficulty = 5;
                break;
            } else if(choice.equalsIgnoreCase("hard")){
                System.out.println("You have selected hard mode.");
                sleep(1000);
                difficulty = 10;
                break;
            } else if(choice.equalsIgnoreCase("impossible")){
                System.out.println("You have selected impossible mode.");
                sleep(1000);
                difficulty = 15;
                break;
            } else {
                System.out.println("Invalid input. Valid options are easy, medium, hard, or impossible. Please try again.");
            }
        }

    }

    public void introText() {

        String storyBanner = "███████╗████████╗ ██████╗ ██████╗ ██╗   ██╗██╗     ██╗███╗   ██╗███████╗\n" +
                "██╔════╝╚══██╔══╝██╔═══██╗██╔══██╗╚██╗ ██╔╝██║     ██║████╗  ██║██╔════╝\n" +
                "███████╗   ██║   ██║   ██║██████╔╝ ╚████╔╝ ██║     ██║██╔██╗ ██║█████╗  \n" +
                "╚════██║   ██║   ██║   ██║██╔══██╗  ╚██╔╝  ██║     ██║██║╚██╗██║██╔══╝  \n" +
                "███████║   ██║   ╚██████╔╝██║  ██║   ██║   ███████╗██║██║ ╚████║███████╗\n" +
                "╚══════╝   ╚═╝    ╚═════╝ ╚═╝  ╚═╝   ╚═╝   ╚══════╝╚═╝╚═╝  ╚═══╝╚══════╝\n" +
                "                                                                        \n";

        while (true) {

            String menu = "========================================================================\n";
            System.out.println("\n"+menu + "\n" + storyBanner + menu);
            System.out.println("Type \"read\" to read the game storyline, or \"skip\" to skip...");
            String intro = scanner.next();
            if (intro.equalsIgnoreCase("skip")) {

                System.out.println(bundle.getString("skip_intro"));
                sleep(1425);
                System.out.println(bundle.getString("start_game"));
                sleep(1425);
                for (int i = 0; i < 50; ++i) System.out.println();
                break;
            } else if (intro.equalsIgnoreCase("read")) {
                System.out.println("Press enter at any point to skip the intro.\n");
                sleep(1000);
                runThread();
                while (true){
                    String readString = scanner.nextLine();
                    if (scanner.hasNextLine()) {
                        isRunning = false;
                        break;
                    }
                }
                sleep(1000);
                System.out.println(bundle.getString("start_game"));
                sleep(1425);
                for (int i = 0; i < 50; ++i) System.out.println();
                break;
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }

    public void runThread(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(isRunning){
                    storyline(bundle.getString("storyline"), 120);

                    if (isRunning){
                        sleep(700);
                        System.out.println("\n\nPress enter when you are ready to start the game...Good luck!");
                    }
                    break;
                    }
            }
        });
        thread.start();
    }

    private String textStream(String text, int speed) {
        for (int i = 0; i < text.length(); i++) {
            System.out.printf("%c", text.charAt(i));
            sleep(speed);
        }
        return text;
    }
    private void storyline(String text, int speed) {
        for (int i = 0; i < text.length(); i++) {
            System.out.printf("%c", text.charAt(i));
            if (!isRunning) {
                try {
                    Thread.currentThread().interrupt();
                    break;
                } catch (Exception ignored) {
                    break;
                }
            }
            sleep(speed);
        }
    }

    private void sleep(int timer) {
        try {
            Thread.sleep(timer);
        } catch (InterruptedException ignored) {
        }
    }
}

