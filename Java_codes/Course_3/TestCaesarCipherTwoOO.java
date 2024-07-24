package Course_3;

public class TestCaesarCipherTwoOO {

    /**
     * Return string comprise of even or odd places character
     * 
     * @param str
     * @param evenOdd
     * @return
     */
    private String HalfOfString(String str, int evenOdd) {
        StringBuilder sb = new StringBuilder();
        for (int i = evenOdd; i < str.length(); i += 2) {
            sb.append(str.charAt(i));
        }
        return sb.toString();
    }

    /**
     * Return index of largest value
     * 
     * @param arr
     * @return
     */
    private int MaxIndex(int[] arr) {
        int maxIdx = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > arr[maxIdx]) {
                maxIdx = i;
            }
        }

        return maxIdx;
    }

    /**
     * Count charaters in string and return as int array
     * 
     * @param str
     * @return
     */
    private static int[] CountCharacter(String str) {
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        int[] counter = new int[26]; // intialize each element to zero
        for (int i = 0; i < str.length(); i++) {
            char currChar = str.charAt(i);
            int idx = alpha.indexOf(Character.toLowerCase(currChar));
            if (idx != -1) {
                counter[idx] += 1;
            }
        }
        // to print count with char
        // for (int i = 0; i < counter.length; i++) {
        // System.out.println(alpha.charAt(i) + "\t" + counter[i]);
        // }
        return counter;
    }

    /**
     * Decrypt using assignment flow prints keys used for encryption
     * 
     * @param encrypted
     */
    public void breakCaesarCipher(String encrypted) {
        //get frequency 
        int[] freq = CountCharacter(encrypted);
        int maxIndex = MaxIndex(freq);
        int dkey = maxIndex - 4;
        //break condition corner case
        if (maxIndex < 4) {
            dkey = 26 - (4 - maxIndex);
            ;
        }
        CaesarCipherOO cc = new CaesarCipherOO(dkey);
        cc.decrypt(encrypted);
    }
}
