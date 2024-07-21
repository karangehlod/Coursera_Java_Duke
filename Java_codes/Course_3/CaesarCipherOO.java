package Course_3;

public class CaesarCipherOO {
    private String alphabate;
    private String shiftedAlphabate;
    private int mainKey;

    /**
     * Constructor
     * @param key
     */
    public CaesarCipherOO(int key) {
        mainKey = key;
        alphabate = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabate = alphabate.substring(key) + alphabate.substring(0, key);
    }


    /**
     * Encrypt message with single key 
     * @param input
     * @return
     */
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
                if (Character.isUpperCase(currChar)) {
                    encrypted.setCharAt(i, shiftedAlphabate.charAt(pos));
                } else {
                    encrypted.setCharAt(i, shiftedAlphabate.toLowerCase().charAt(pos));
                }
            }
        }
        return encrypted.toString();
    }
    
    public String decrypt(String encrypted) {
        CaesarCipherOO cc = new CaesarCipherOO(26 - mainKey);
        return cc.encrypt(encrypted);
    }
}
