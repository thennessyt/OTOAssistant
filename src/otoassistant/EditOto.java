package otoassistant;

import java.util.*;
import java.io.*;
import otoassistant.OtoObject;
import static java.nio.charset.StandardCharsets.*;




public class EditOto
{
    /*
     * Taylor Hennessy
     * celestrai
     *
     * This program is a collection of oto.ini permuters
     * for use with the program UTAU.
     *
     * Currently it includes:
     *       - adding a suffix (to all aliases)
     *       - counting lines
     *
     * Soon it will include:
     *       - truncating decimal numbers (unnecessary but looks cleaner)
     *       - sort by alias
     *       - sort by file location
     *       - convert to DeepVocal configuration
     *       - SmartConvert (VCV to CVVC)
     *       - SmartAdjust (Moresampler Arpasing Auto Oto Adjustment)
     *       - Remove duplicated entries
     *
     *
     * NOTE: This program requires the oto to be within
     *       the same file folder as it, and to be named
     *       oto_ini.txt for it to run. Soon this won't be a problem.
     *
     */

    //-----------------------GLOBAL VARIABLES------------------------


    //create the oto object array
    //this holds the entire oto.ini file other than the first line
    static ArrayList<OtoObject> otoini = new ArrayList<OtoObject>();

    //this program also only results in UTF-8 oto.inis
    // (rather than Shift-JIS which is sometimes used by older
    //  oto files))
    static String otocharset = "#Charset:UTF-8";


    //set up file to be used in main and save functions
    static File file = new File("src/oto_ini.txt");;

    static boolean isSaved = true;


    // The main procedure for oto edit application.
    public static void main(String[] args){
        //create scanner
        Scanner scanit = new Scanner(System.in);
        Scanner scanfile = null;
        try {
            scanfile = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Enter the path to your OTO.INI file.");

        //file path is unverified
        boolean badFile = true;

        //while the file is not verified/good
        while (badFile == true) {
            //set the file to good
            badFile = false;

            //catch and set file back to bad if the file doesn't exist in that place
            try {
                //Load in OTO given a file path
                file = new File(scanit.nextLine());
                scanfile = new Scanner(file);
            } catch (FileNotFoundException e) {
                //e.printStackTrace();
                badFile = true;
                System.out.println("Sorry, that file does not exist. The program will exit.");
                System.exit(0);
            }
        }


        //if it works(escaped while loop), convert!
        //first, consume the first line that sets the character set
        String otocharacterline = scanfile.nextLine();

        //then, convert each next line into an OtoObject and
        //add it to the list of lines
        while (scanfile.hasNextLine()){
            String line = scanfile.nextLine();
            otoini.add(new OtoObject(line));
        }

        //while the user has not chosen to exit
        boolean stopsignal = false;
        //print the menu and wait
        while (!stopsignal) {
            stopsignal=menu();
        }

        //close the scanner
        scanit.close();
    }

    //
    public static void addSuffix(){

        Scanner scanit = new Scanner(System.in);
        String suff = "";

        //first, get the suffix from the user.
        System.out.println("What suffix would you like to add?");

        suff = scanit.nextLine();

        System.out.println(suff);


        //close the scanner
        scanit.close();


        //check if saved


        //exit the program
        System.exit(0);

    }


    //returns the # of lines from the oto
    //WHY: very useful when configuring or commissioning configuration
    //Will also be useful in integration with RECLISTSCRIPT (a recording
    //list generator) once RECLISTSCRIPT is finished
    public static int countLines(){

        //the # of lines is just the number of entries in the otoini arraylist
        return (otoini.size());

        //this is made into a function for simplicity in menu
    }



    //prints the menu and handles user choice
    public static boolean menu(){

        Scanner scanit = new Scanner(System.in);

        //prints out menu
        System.out.println("Choose an option:");
        System.out.println("1 - Count Lines");
        System.out.println("2 - Add Suffix");
        System.out.println("3 - Save Changes");
        System.out.println("X - Exit");
        System.out.println();

        //takes in next choice
        String choice = scanit.nextLine();
        //converts choice toUpper
        choice = choice.toUpperCase();

        switch(choice){

            case "1":
                System.out.println("You have chosen to count the oto lines.\n");
                System.out.println("There are " + countLines() + " lines in this configuration.");
                return false;
            case "2":
                System.out.println("You have chosen to add a suffix.");
                isSaved = false;
                return false;
            case "3":
                System.out.println("You have chosen to save your changes to the file.");
                save();
                return false;
            case "X":
                System.out.println("You have chosen to exit the program.");
                return true;
            //default case is they didn't choose any real option
            default:
                System.out.println("Please choose a menu option.\n");
                //call itself again when they don't pick a real option
                menu();
                return false;
        }
        //end of menu
    }



    //write out to the oto.ini file
    public static void save(){

        String filename = file.getName();

        PrintWriter writer;

        try {
            try {
                 writer = new PrintWriter(filename, "UTF-8");
                 writer.println(otocharset);

                 for (int i = 0; i < otoini.size(); i++){
                     //System.out.println(otoini.get(i).toString());
                     writer.println(otoini.get(i).toString());
                 }
            } catch (UnsupportedEncodingException f){
                System.out.println("Sorry, we messed up with UTF-8.");
            }
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            //badFile = true;
                System.out.println("Somehow, your file doesn't exist!");
        }

        isSaved = true;
    }

}
