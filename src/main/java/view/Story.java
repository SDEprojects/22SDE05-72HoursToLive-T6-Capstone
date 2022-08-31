package main.java.view;





import main.java.controller.GameSettings;

import java.util.ResourceBundle;
import java.util.Scanner;

public class Story {
    Scanner scanner = new Scanner(System.in);

    public static final ResourceBundle bundle = ResourceBundle.getBundle("main.resources.strings");

    public void titleScreen() {

        String menu = "===========================================================================================================================================\n\n";
        String infoMenu = "======================================================================\n";
        String titleBanner =

                "███████╗███████╗██╗   ██╗███████╗███╗   ██╗████████╗██╗   ██╗    ████████╗██╗    ██╗ ██████╗     ██╗  ██╗ ██████╗ ██╗   ██╗██████╗ ███████╗\n" +
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
                System.out.println("Setting: Castle");
                sleep(1000);
                System.out.println("Press enter to return to the menu screen...");
                String readString = scanner.nextLine();
                if (scanner.hasNextLine()) {
                    titleScreen();
                    break;
                }
            }  else {
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
        System.out.println(storyBanner);

    }

    public String textStream(String text, int speed) {
        for (int i = 0; i < text.length(); i++) {
            System.out.printf("%c", text.charAt(i));
            sleep(speed);
        }
        return text;
    }

    public void sleep(int timer) {
        try {
            Thread.sleep(timer);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void menu() {
        String menu = "=================";
        System.out.println("You have entered the " + GameSettings.roomName);
        sleep(1500);
        System.out.printf("%nCurrent Room:%4s %6s North: %2s %5sSouth:%-21.21s %4sEast:%4s", "aa", "", "aa", "", "aa", "", "Location East");
        System.out.printf("%nConnecting Rooms:%4s %6s North: %2s %5sSouth:%-21.21s %4sEast:%4s %n", "aa", "", "aa", "", "aa", "", "aa");
        System.out.printf("%s %10d %15d %15d %14sItem: %s\n", "Current Room:", 1, 2, 3, "", "aa");
        System.out.printf("%s %10d %15d %15d %14sItem: %s\n", "Items:", 1, 2, 3, "", "aa");
        System.out.printf("%s %12s %12s %12s %12s\n", menu, menu, menu, menu, menu);
        sleep(500);
        System.out.printf("%s %10d %15d %15d %14sAction: %s\n", "Enter:", 1, 2, 3, "", "aa");
        System.out.printf("%s %12s %12s %12s %12s\n", menu, menu, menu, menu, menu);
    }
}

