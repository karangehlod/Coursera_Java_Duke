package Course_3;

import java.util.Random;

public class BreakingCaesarCipher {
    public static void EyeBallDecyption() {
        CaesarCipher caesarCipher = new CaesarCipher();
        for (int i = 0; i < 26; i++) {
            System.out.println(i + " : " + caesarCipher.encrypt("null", i));
        }
    }

    /**
     * Count charaters in string and return as int array
     * 
     * @param str
     * @return
     */
    public static int[] CountCharacter(String str) {
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
     * Return index of largest value
     * 
     * @param arr
     * @return
     */
    public int MaxIndex(int[] arr) {
        int maxIdx = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > arr[maxIdx]) {
                maxIdx = i;
            }
        }

        return maxIdx;
    }

    /**
     * calculate the index of the largest letter frequency, which is the location of
     * the encrypted letter ‘e’, which leads to the key, which is returned.
     * 
     * @param str
     * @return
     */
    public int getKey(String str) {
        int[] temp = CountCharacter(str);
        int maxIdx = MaxIndex(temp);
        int dkey = maxIdx - 4;
        if (maxIdx < 4) {
            dkey = 26 - (4 - maxIdx);
        }
        return dkey;
    }

    /**
     * Decrypt the message
     * 
     * @param encrypted
     * @return
     */
    public String Decrypt(String encrypted) {
        CaesarCipher cc = new CaesarCipher();
        int[] freq = CountCharacter(encrypted);
        int maxIdx = MaxIndex(freq);
        // e being mostly used in english
        int dkey = maxIdx - 4;
        // boundary check
        if (maxIdx < 4) {
            dkey = 26 - (4 - maxIdx);
        }
        return cc.encrypt(encrypted, 26 - dkey);
    }

    public static void SimulateDice(int rolls) {
        Random rand = new Random();
        int[] count = new int[13];
        for (int i = 0; i < rolls; i++) {
            int d1 = rand.nextInt(6) + 1;
            int d2 = rand.nextInt(6) + 1;
            count[d1 + d2] += 1;
        }
        for (int i = 2; i < count.length; i++) {
            System.out.println(i + "\t" + count[i]);
        }
    }

    public static void main(String[] args) {
        // CountCharacter("aabbccdd");
        SimulateDice(10);
    }
}
