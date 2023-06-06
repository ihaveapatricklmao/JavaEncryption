import java.io.*;
import java.util.*;


public class Main {

    static Random rand = new Random();

    // variables to be used for the key

    static int x = rand.nextInt(3, 10);
    static int y = rand.nextInt(3, 10);
    static int z = rand.nextInt(3, 10);


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

        String data = getFileData("filename.txt");

        List<Character> chdata = convertToChar(data);

        System.out.println(chdata);
        //encrypt(chdata, "filename.txt");

        System.out.println(Arrays.toString(key));

        //988

        decrypt(chdata, "filename.txt", new int[]{9, 6, 5});


    }


    // get info in a text file and convert to string to be used in convert to char
    public static String getFileData(String filename) {
        File myObj = new File(filename);
        Scanner reader;
        try {
            reader = new Scanner(myObj);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String data = null;
        while(reader.hasNextLine()) {
            data = reader.nextLine();
        }
        reader.close();

        return data;
    }

    // convert string to char array
    public static List<Character> convertToChar(String strData) {
        List<Character> charList = new ArrayList<>();

        for (char ch: strData.toCharArray()) {
            charList.add(ch);
        }


        return charList;
    }


    // encrypt file by taking the character arraylist and indexing through it, replacing each character by shifting its ascii value by a certain amount and then writing to the file
    public static void encrypt(List<Character> charArray, String filename) {
        File file = new File(filename);

        int count = 0;


        Object[] charToArray = charArray.toArray();


        String toStr = Arrays.toString(charToArray);
        String replaceWeirdos = toStr.replaceAll("[\\[\\],\\s]", "");
        char[] toCharAgain = replaceWeirdos.toCharArray();
        System.out.println(toCharAgain);

        for (int i = 0; i <= toCharAgain.length-1; i++, count++) {

            if(count >= 3) {
                count = 0;
            }

            int curVal = toCharAgain[i];

            toCharAgain[i] = (char) (curVal + key[count]);

        }

        try {
            FileWriter writeToFile = new FileWriter(file);
            writeToFile.write(toCharAgain);
            writeToFile.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


    // decrypt method
    public static void decrypt(List<Character> charArray, String file, int[] key_value) {

        File fileToBeDecrypted = new File(file);

        int count = 0;


        Object[] charToArray = charArray.toArray();


        String toStr = Arrays.toString(charToArray);
        String replaceWeirdos = toStr.replaceAll("[\\[\\],\\s]", "");
        char[] toCharAgain = replaceWeirdos.toCharArray();
        System.out.println(toCharAgain);


        for (int i = 0; i <= toCharAgain.length-1; i++, count++) {

            if(count >= 3) {
                count = 0;
            }

            System.out.println(key_value[count]);
            int curVal = toCharAgain[i];

            toCharAgain[i] = (char) (curVal - key_value[count]);

        }

        try {
            FileWriter writeToFile = new FileWriter(fileToBeDecrypted);
            writeToFile.write(toCharAgain);
            writeToFile.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}