package Course_3;

public class BreakingCaesarCipher {
    public static void EyeBalDecyption() {
        CaesarCipher caesarCipher = new CaesarCipher();
        for (int i = 0; i < 26; i++) {
            System.out.println(i + " : " + caesarCipher.encrypt("null", i));
        }
    }

    public static void CountCharacter(String str) {
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        int[] counter = new int[26]; //intialize each element to zero
        for (int i = 0; i < str.length(); i++) {
            char currChar = str.charAt(i);
            //
            int idx = alpha.indexOf(Character.toLowerCase(currChar));
            if (idx != -1) {
                counter[idx] += 1;
            }
        }
        //to print count with char
        for (int i = 0; i < counter.length; i++) {
            System.out.println(alpha.charAt(i) + "\t" + counter[i]);
        }
    }
    
    public static void main(String[] args) {
        CountCharacter("aabbccdd");
    }
}
