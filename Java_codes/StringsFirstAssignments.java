import edu.duke.URLResource;

public class StringsFirstAssignments {
    public static String findSimpleGene(String dnaString, String startCodon, String endCodon) {
        // art codon "ATG"
        int startCodonidx = dnaString.indexOf(startCodon);
        // end codon "TAA"
        int endCodonidx = dnaString.indexOf(endCodon, startCodonidx + 3);
        if ((endCodonidx - startCodonidx + 3) % 3 == 0) {
            return dnaString.substring(startCodonidx + 3, endCodonidx).toUpperCase();
        }
        return "";
    }

    public static boolean twoOccurrences(String stringa, String stringb) {
        // built-in
        // int resultCount = StringUtils.countMatches(stringb, stringa);

        int count = 0;
        int idx = 0;

        while (idx != -1) {
            // return -1 when no such value exists
            idx = stringb.indexOf(stringa, idx);

            if (idx != -1) {
                count += 1;
                idx += stringa.length();
            }
        }

        if (count > 1) {
            return true;
        }
        return false;
    }

    public static String lastPart(String stringa, String stringb) {
        int idx = stringb.indexOf(stringa);
        String res = "";
        if (idx != -1) {
            res = stringb.substring(idx + stringa.length());
        }
        if (res.isEmpty()) {
            return stringb;
        }
        return res;
    }

    public void FindWebLink(String url) {
        URLResource urls = new URLResource(url);
        System.out.println("Get urls from: " + url + "\n");
        for (String linkString : urls.words()) {
            // System.out.println(linkString);
            linkString.toLowerCase();

            String testString = "youtube.com";
            if (linkString.contains(testString)) {
                int pos = linkString.indexOf(testString);
                int start = linkString.lastIndexOf("href=\"", pos);
                int stop = linkString.indexOf("\">", pos);

                // get the substring between start and stop indices;
                String pureURL = linkString.substring(start + 6, stop);

                System.out.println("URL: " + linkString);
                System.out.println("URL: " + pureURL + "\n");

            }
        }

    }

    public static void main(String[] args) {
        String startCodon = "ATG";
        String endCodon = "TAA";
        // gene 1 without ATG
        String case1Gene = "TAACGCGAAAGGATAA";
        System.out.println("Result gene " + findSimpleGene(case1Gene, startCodon, endCodon));

        // gene 1 without TAA
        String case2Gene = "ATGGCGCGCGCTAGCGCG";
        System.out.println("Result gene " + findSimpleGene(case2Gene, startCodon, endCodon));

        // gene with ATG and TAA and between not multiple of 3
        String case3Gene = "ATGGCCACGGACGAGTATAA";
        System.out.println("Result gene " + findSimpleGene(case3Gene, startCodon, endCodon));

        // gene with ATG and TAA and between multiple of 3
        String case4Gene = "ATGGCCACGGACGACGTATAA";
        System.out.println("Result gene " + findSimpleGene(case4Gene, startCodon, endCodon));

        // two occurance true
        String a = "by";
        String b = "byby";
        System.out.println(twoOccurrences(a, b));

        // one occurance false
        String a1 = "by";
        String b1 = "bby";
        System.out.println(twoOccurrences(a1, b1));

        // last part
        String a2 = "an";
        String b2 = "banana";
        System.out.println("The part of the string after " + a2 + " in " + b2 + " is " + lastPart(a2, b2));

        // last part
        String a3 = "zoo";
        String b3 = "forest";
        System.out.println("The part of the string after " + a3 + " in " + b3 + " is " + lastPart(a3, b3));

        StringsFirstAssignments stringsFirstAssignments = new StringsFirstAssignments();
        stringsFirstAssignments.FindWebLink("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
    }
}
