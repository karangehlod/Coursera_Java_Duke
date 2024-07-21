package Course_3;

public class CaesarCipherTwoOO {
    private String alphabate;
    private String shift1;
    private String shift2;
    private int mainKey1;
    private int mainKey2;

    public CaesarCipherTwoOO(int key1, int key2) {
        mainKey1 = key1;
        mainKey2 = key2;
        alphabate = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shift1 = alphabate.substring(key1) + alphabate.substring(0, key1);
        shift2 = alphabate.substring(key2) + alphabate.substring(0, key2);
    }

    public String encrypt(String input) {
        StringBuilder encrypted = new StringBuilder(input);

        for (int i = 0; i < encrypted.length(); i++) {
            // current char check position in original and at same position check in shifted
            char currChar = encrypted.charAt(i);
            // convert char to upper car to match
            char tempChar = Character.toUpperCase(currChar);
            int pos = alphabate.indexOf(tempChar);
            // pos -1 if not found in string
            if (pos != -1) {
                // replace original char to encrypted char at same index
                // if currChar was uppercase add uppercase vice verse
                if (i % 2 == 0) {
                    if (Character.isUpperCase(currChar)) {
                        encrypted.setCharAt(i, shift1.charAt(pos));
                    } else {
                        encrypted.setCharAt(i, shift1.toLowerCase().charAt(pos));
                    }
                } else {
                    if (Character.isUpperCase(currChar)) {
                        encrypted.setCharAt(i, shift2.charAt(pos));
                    } else {
                        encrypted.setCharAt(i, shift2.toLowerCase().charAt(pos));
                    }
                }
            }
        }

        return encrypted.toString();
    }

    public String decrypt(String encrypted) {
        CaesarCipherTwoOO cc = new CaesarCipherTwoOO(26 - mainKey1, 26 - mainKey2);
        return cc.encrypt(encrypted);
    }

}
