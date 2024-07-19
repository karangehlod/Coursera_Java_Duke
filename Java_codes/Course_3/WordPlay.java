package Course_3;

public class WordPlay {

    /**
     * return true if character is vowel else false
     * 
     * @param ch
     * @return
     */
    public boolean isVowel(char ch) {
        char tmp = Character.toLowerCase(ch);
        if (tmp == 'a' || tmp == 'e' || tmp == 'i' || tmp == 'o' || tmp == 'u') {
            return true;
        }
        return false;
    }

    public String ReplaceVowel(String phrase, char ch) {
        StringBuilder sb = new StringBuilder();
        for (char c : phrase.toCharArray()) {
            if (isVowel(c)) {
                sb.append(ch);
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public String Emphasize(String phrase, char ch) {
        // to count even odd properly
        int count = 1;
        char[] phraseArray = phrase.toCharArray();
        for (int i = 0; i < phraseArray.length; i++) {

            if (phraseArray[i] == ch) {
                if (count % 2 == 0) {
                    phraseArray[i] = '+';
                } else {
                    phraseArray[i] = '*';
                }
            }
            count++;
        }
        return (new String(phraseArray));
    }

    public static void main(String[] args) {
        WordPlay wordplay = new WordPlay();
        // System.out.println(wordplay.ReplaceVowel("Hello World", '*'));
        System.out.println(wordplay.Emphasize("Mary Bella Abracadabra", 'a'));

    }
}
