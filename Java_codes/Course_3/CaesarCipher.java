package Course_3;

import edu.duke.FileResource;

public class CaesarCipher {
    public static String encrypt(String input, int key) {
        StringBuilder encrypted = new StringBuilder(input);
        String alphabateString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabateString = alphabateString.substring(key) + alphabateString.substring(0, key);

        for (int i = 0; i < encrypted.length(); i++) {
            // current char check position in original and at same position check in shifted
            char currChar = encrypted.charAt(i);
            //convert char to upper car to match
            char tempChar = Character.toUpperCase(currChar);
            int pos = alphabateString.indexOf(tempChar);
            // pos -1 if not found in string 
            if (pos != -1) {
                //replace original char to encrypted char at same index
                //if currChar was uppercase add uppercase vice verse
                if (Character.isUpperCase(currChar)) {
                    encrypted.setCharAt(i, shiftedAlphabateString.charAt(pos));
                } else {
                    encrypted.setCharAt(i, shiftedAlphabateString.toLowerCase().charAt(pos));
                }
            }
        }

        // System.out.println(encrypted);
        return encrypted.toString();
    }

    public static String encrypt(String input, int key1, int key2) {
        StringBuilder encrypted = new StringBuilder(input);
        String alphabateString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabateStringFirst = alphabateString.substring(key1) + alphabateString.substring(0, key1);
        String shiftedAlphabateStringSecond = alphabateString.substring(key2) + alphabateString.substring(0, key2);

        for (int i = 0; i < encrypted.length(); i++) {
            // current char check position in original and at same position check in shifted
            char currChar = encrypted.charAt(i);
            // convert char to upper car to match
            char tempChar = Character.toUpperCase(currChar);
            int pos = alphabateString.indexOf(tempChar);
            // pos -1 if not found in string
            if (pos != -1) {
                // replace original char to encrypted char at same index
                // if currChar was uppercase add uppercase vice verse
                if (i % 2 == 0) {
                    if (Character.isUpperCase(currChar)) {
                        encrypted.setCharAt(i, shiftedAlphabateStringFirst.charAt(pos));
                    } else {
                        encrypted.setCharAt(i, shiftedAlphabateStringFirst.toLowerCase().charAt(pos));
                    }
                }
                else {
                    if (Character.isUpperCase(currChar)) {
                        encrypted.setCharAt(i, shiftedAlphabateStringSecond.charAt(pos));
                    } else {
                        encrypted.setCharAt(i, shiftedAlphabateStringSecond.toLowerCase().charAt(pos));
                    }
                }
            }
        }

        // System.out.println(encrypted);
        return encrypted.toString();
    }

    public static void main(String[] args) {
        int key = 17;
        FileResource fr = new FileResource("Java_codes\\input\\Course_3_ip\\test2.txt");
        // String message = fr.lines();
        for (String messageLine : fr.lines()) {
            String encrypted = encrypt(messageLine, key);
            System.out.println(encrypted);
            // opposite of encrption
            String decrypted = encrypt(encrypted, 26 - key);
            System.out.println(decrypted);
        }
        System.out.println(encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 15));
        System.out.println(encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 8, 21));


    }
}
