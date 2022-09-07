package main.java.view;


import java.util.ResourceBundle;
import java.util.Scanner;

public class Story {
    Scanner scanner = new Scanner(System.in);

    public static final ResourceBundle bundle = ResourceBundle.getBundle("main.resources.strings");

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
        System.out.println(("Welcome to Seventy Two Hours!\nType \"play\" to play, \"help\" for more information, or \"quit\" to quit.\n\n"));
        while (true) {
            String intro = scanner.next();
            if (intro.equalsIgnoreCase("play")) {
                System.out.println("Starting the game...\n");
                sleep(1000);
                break;
            } else if (intro.equalsIgnoreCase("help")) {
                System.out.println(infoMenu + "\n" + infoBanner + infoMenu);
                sleep(1000);
                System.out.println("\nYou have been selected to go back in time and save humanity, you must" +
                        "find the first werewolf, collect his blood and escape through the Time Portal before " +
                        "72 hours runs out and the portal closes. \n" +
                        "You can go to a room by typing \"go [direction]\"\n" +
                        "You can use an item by typing \"use [item]\"\n" +
                        "You can equip armor and weapons by typing \"equip [item]\"\n" +
                        "You can attack a werewolf by typing \"attack wolf\"\n" +
                        "You can look for items in a room by typing \"look\"\n" +
                        "You can check your inventory by typing \"inventory\"\n" +
                        "You can quit the game by typing \"quit\"\n");
                System.out.println("Directions are: North, East, South, West");
                sleep(1000);
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
            System.out.println(menu + "\n" + storyBanner + menu);
            System.out.println("Type \"read\" to read the game storyline, or \"skip\" to skip...");
            String intro = scanner.next();
            if (intro.equalsIgnoreCase("skip")) {
                System.out.println(bundle.getString("skip_intro"));
                sleep(425);
                System.out.println(bundle.getString("start_game"));
                sleep(425);

                break;
            } else if (intro.equalsIgnoreCase("read")) {

                textStream(bundle.getString("story_one"), 120);
                sleep(650);
                textStream(bundle.getString("story_two"), 120);
                textStream(bundle.getString("story_slowTwo"), 210);
                sleep(650);
                textStream(bundle.getString("story_three"), 120);
                sleep(650);
                textStream(bundle.getString("story_four"), 120);
                sleep(650);
                textStream(bundle.getString("story_five"), 120);
                textStream("forever.\n", 390);
                break;
            } else {
                System.out.println("Invalid input. Please try again.");
            }

        }

    }

    private String textStream(String text, int speed) {
        for (int i = 0; i < text.length(); i++) {
            System.out.printf("%c", text.charAt(i));
            sleep(speed);
        }
        return text;
    }

    private void sleep(int timer) {
        try {
            Thread.sleep(timer);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}

