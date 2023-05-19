import java.io.*;
import java.util.*;
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;


public class Main {

    static Random rand = new Random();

    // variables to be used for the key

    static int x = rand.nextInt(2, 10);
    static int y = rand.nextInt(2, 10);
    static int z = rand.nextInt(2, 10);


    static int[] key = {x, y, z};

    public static void main(String[] args) {

        // create file

        File myObj = new File("filename.txt");
        try {
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


    }

    // encrypt method

    public static char[] encrypt(String filename) {

        File fileObj = new File(filename);

        long lengthOfFile = fileObj.length();
        char[] fullEncrypt = {};

        // encrypt file

        int counter = 0;


        for (int i = 0; i <= lengthOfFile; i++, counter++) {

            if (counter >= 3) {
                counter = 0;
            }

            // read the whole file

            try {
                Scanner readFile = new Scanner(fileObj);
                String strData = null;
                char[] encrypted = new char[0];

                while (readFile.hasNextLine()) {
                    strData = readFile.nextLine();

                    System.out.println(strData);

                    encrypted = strData.toCharArray();

                }

                readFile.close();


                for (int t = 0; t < lengthOfFile; t++) {
                    fullEncrypt = new char[]{encrypted[t] = (char) ((int) encrypted[t] + key[counter])};

                }


            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return fullEncrypt;
    }
}
