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

        Scanner userInp = new Scanner(System.in);
        System.out.println("Enter filename: ");
        String userResponse = userInp.nextLine();

        String data = getFileData(userResponse);
        List<Character> chdata = convertToChar(data);


        Scanner encryptInp = new Scanner(System.in);
        System.out.println("Do you want to \n 1) Encrypt the file \n 2) decrypt the file");

        String quesResponse = encryptInp.nextLine();

        if (Objects.equals(quesResponse, "1")) {
            encrypt(chdata, userResponse);
        } else if (Objects.equals(quesResponse, "2")) {
            Scanner getKeyQues = new Scanner(System.in);
            System.out.println("What is the key? ");

            String getKey = getKeyQues.nextLine();

            String[] turnToArray = getKey.split("");
            int[] turnToInt = new int[turnToArray.length];

            for (int x = 0; x < turnToArray.length; x++) {
                turnToInt[x] = Integer.parseInt(turnToArray[x]);
            }
            decrypt(chdata, userResponse, turnToInt);
        }


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

        char[] toCharAgain = new char[charArray.size()];

        for (int i = 0; i < charArray.size(); i++, count++) {
            if (count >= 3) {
                count = 0;
            }

            char curVal = charArray.get(i);
            if (curVal != ' ') {
                curVal = (char) (curVal + key[count]);
            }

            toCharAgain[i] = curVal;
        }
        System.out.println("The file has been encrypted!");
        System.out.println("Your key is "+Arrays.toString(key));

        try {
            FileWriter writeToFile = new FileWriter(file);
            writeToFile.write(toCharAgain);
            writeToFile.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void decrypt(List<Character> charArray, String file, int[] key_value) {
        File fileToBeDecrypted = new File(file);

        int count = 0;

        char[] toCharAgain = new char[charArray.size()];

        for (int i = 0; i < charArray.size(); i++, count++) {
            if (count >= 3) {
                count = 0;
            }

            char curVal = charArray.get(i);
            if (curVal != ' ') {
                curVal = (char) (curVal - key_value[count]);
            }

            toCharAgain[i] = curVal;
        }
        System.out.println("The file has been decrypted");

        try {
            FileWriter writeToFile = new FileWriter(fileToBeDecrypted);
            writeToFile.write(toCharAgain);
            writeToFile.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}