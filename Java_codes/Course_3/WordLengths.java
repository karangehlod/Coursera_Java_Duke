package Course_3;

import edu.duke.FileResource;

public class WordLengths {
    /**
     * read in the words from resource and count the number of words of each length
     * for all the words in resource, storing these counts in the array counts.
     * 
     * @param resource
     * @param counts
     */
    public void CountWordlength(FileResource resource, int[] counts) {
        for (String word : resource.words()) {
            int len = word.length();
            boolean fLetter = Character.isLetter(word.charAt(0));
            boolean lLetter = true;
            if (len > 1) {
                lLetter = Character.isLetter(word.charAt(word.length() - 1));
            }
            // if first and last index is not char then on false condition reduce non-letter
            if (!fLetter && !lLetter) {
                len = len - 2;
            } else if (!fLetter || !lLetter) {
                len = len - 1;
            }
            if (len > counts.length) {
                counts[-1] += 1;
            } else {
                counts[len] += 1;
            }
        }
        // print
        for (int i = 0; i < counts.length; i++) {
            System.out.println(i + " : " + counts[i]);
        }
    }

    /**
     * Return string comprise of even or odd places character
     * 
     * @param str
     * @param evenOdd
     * @return
     */
    public String HalfOfString(String str, int evenOdd) {
        StringBuilder sb = new StringBuilder();
        for (int i = evenOdd; i < str.length(); i += 2) {
            sb.append(str.charAt(i));
        }
        return sb.toString();
    }

    /**
     * Decrypt using intutional flow
     * 
     * @param encrypted
     */
    public void CaesarCipherDecryptTwoKeys(String encrypted) {
        // create two parts with even at odd pos
        StringBuilder sbEven = new StringBuilder(HalfOfString(encrypted, 0));
        StringBuilder sbOdd = new StringBuilder(HalfOfString(encrypted, 1));
        BreakingCaesarCipher bcc = new BreakingCaesarCipher();
        // decipher both using decrypt method
        String evenPlace = bcc.Decrypt(sbEven.toString());
        String oddPlace = bcc.Decrypt(sbOdd.toString());

        // add both together for result
        StringBuilder decrypted = new StringBuilder();
        for (int i = 0; i < encrypted.length() / 2; i++) {
            decrypted.append(evenPlace.charAt(i));
            decrypted.append(oddPlace.charAt(i));
        }
        System.out.println(decrypted.toString());
    }

    /**
     * Decrypt using assignment flow prints keys used for encryption
     * 
     * @param encrypted
     */
    public void DecryptTwoKeys(String encrypted) {
        // create two parts with even at odd pos
        StringBuilder sbEven = new StringBuilder(HalfOfString(encrypted, 0));
        StringBuilder sbOdd = new StringBuilder(HalfOfString(encrypted, 1));
        BreakingCaesarCipher bcc = new BreakingCaesarCipher();
        // calculate key for half of each
        int key1 = bcc.getKey(sbEven.toString());
        int key2 = bcc.getKey(sbOdd.toString());

        CaesarCipher cc = new CaesarCipher();
        System.out.println(key1 + "    " + key2);
        String res = cc.encrypt(encrypted, 26 - key1, 26 - key2);
        System.out.println(res);
    }

    public static void main(String[] args) {
        WordLengths wordlenLengths = new WordLengths();
        FileResource fr = new FileResource("Java_codes\\input\\Course_3_ip\\assesment.txt");
        int[] counts = new int[31];
        wordlenLengths.CountWordlength(fr, counts);
        wordlenLengths.DecryptTwoKeys("Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!");

        StringBuilder sbv = new StringBuilder();
        for (String s : fr.lines()) {
            sbv.append(s);
        }
        wordlenLengths.DecryptTwoKeys(sbv.toString());
    }

}
