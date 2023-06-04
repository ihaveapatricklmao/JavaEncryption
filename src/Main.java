import java.io.*;
import java.util.*;


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

        String data = getFileData("filename.txt");

        List<Character> chdata = convertToChar(data);

        System.out.println(chdata);
        encrypt(chdata, "filename.txt");

        System.out.println(key[0]);
        System.out.println(key[1]);
        System.out.println(key[2]);


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
        
        charList.removeAll(Collections.singleton(','));
        charList.removeAll(Collections.singleton('['));
        charList.removeAll(Collections.singleton(']'));

        System.out.println(charList);

        return charList;
    }


    // encrypt file by taking the character arraylist and indexing through it, replacing each character by shifting its ascii value by a certain amount and then writing to the file
    public static void encrypt(List<Character> charArray, String filename) {
        File file = new File(filename);

        int count = 0;


        Object[] charToArray = charArray.toArray();

        String toStr = Arrays.toString(charToArray);
        char[] toCharAgain = toStr.toCharArray();

        for (int i = 0; i <= toCharAgain.length-1; i++, count++) {
            //System.out.println(toCharAgain);
            if(count >= 3) {
                count = 0;
            }

            //System.out.println("count "+count);
            int curVal = toCharAgain[i];
            //System.out.println(curVal);
            toCharAgain[i] = (char) (curVal + key[count]);
            //System.out.println(key[count]);
        }
        System.out.println(toCharAgain);

    }
}