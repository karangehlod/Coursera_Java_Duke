
public class FindGeneWhile {
    public String FindGene(String dna) {
        // find start codon and call its index startIndex
        String startCodon = "ATG";
        String endCodon = "TAA";
        int startIndex = dna.indexOf(startCodon);
        int currentIndex = dna.indexOf(endCodon, startIndex + 3);
        while (currentIndex != -1) {
            if ((currentIndex - startIndex) % 3 == 0) {
                return dna.substring(startIndex, currentIndex + 3);
            }
            currentIndex = dna.indexOf(endCodon, currentIndex + 1);
        }
        return "";
    }

    public String FindGeneMultipleStop(String dna) {
        String startCodon = "ATG";
        int startIndex = dna.indexOf(startCodon);
        if (startIndex == -1) {
            return "";
        }
        int taaIndex = FindStopCodon(dna, startIndex, "TAA");
        int tagIndex = FindStopCodon(dna, startIndex, "TAG");
        int tgaIndex = FindStopCodon(dna, startIndex, "TGA");
        //minimum of three using relative if FIndStopCodon return length of String
        // int temp = Math.min(tagIndex, tgaindex);
        // int minIndex = Math.min(taaIndex, temp);
        int minIndex = 0;
        //if FindStopCdon return -1 than boolean expression
        if (taaIndex == -1 || (tgaIndex != -1 && tgaIndex < taaIndex)) {
            minIndex = tgaIndex;
        } else {
            minIndex = taaIndex;
        }

        if (minIndex == -1 || (tagIndex != -1 && tagIndex < minIndex)) {
            minIndex = tagIndex;
        }

        if (minIndex == -1) {
            return "";
        }
        return dna.substring(startIndex, minIndex + 3);
    }
    
    public String FindGeneMultipleStop(String dna, int pos) {
        String startCodon = "ATG";
        int startIndex = dna.indexOf(startCodon, pos);
        if (startIndex == -1) {
            return "";
        }
        int taaIndex = FindStopCodon(dna, startIndex, "TAA");
        int tagIndex = FindStopCodon(dna, startIndex, "TAG");
        int tgaIndex = FindStopCodon(dna, startIndex, "TGA");
        // minimum of three using relative if FIndStopCodon return length of String
        // int temp = Math.min(tagIndex, tgaindex);
        // int minIndex = Math.min(taaIndex, temp);
        int minIndex = 0;
        // if FindStopCdon return -1 than boolean expression
        if (taaIndex == -1 || (tgaIndex != -1 && tgaIndex < taaIndex)) {
            minIndex = tgaIndex;
        } else {
            minIndex = taaIndex;
        }

        if (minIndex == -1 || (tagIndex != -1 && tagIndex < minIndex)) {
            minIndex = tagIndex;
        }

        if (minIndex == -1) {
            return "";
        }
        return dna.substring(startIndex, minIndex + 3);
    }

    public int FindStopCodon(String dna, int startIndex, String codon) {
        int currentIndex = dna.indexOf(codon, startIndex + 3);
        while (currentIndex != -1) {
            if ((currentIndex - startIndex) % 3 == 0) {
                return currentIndex;
            } else {
                currentIndex = dna.indexOf(codon, currentIndex + 1);
            }

        }
        return -1;
    }
    
    public void PrintAllGeneInDna(String dna) {
        //find start index
        int startIndex = 0;
        while (true) {
            String currGene = FindGeneMultipleStop(dna, startIndex);
            //break if no gene find 
            if (currGene.isEmpty()) {
                break;
            }
            System.out.println(currGene);
            startIndex = dna.indexOf(currGene, startIndex) + currGene.length();
        }        
    }

    public static void main(String[] args) {
        FindGeneWhile findGeneWhile = new FindGeneWhile();
        // String res = findGeneWhile.FindGene("ATGGCGGTAAGACTACGCATAAGACATGTGTAA");
        // System.out.println(res);

        // res = findGeneWhile.FindGeneMultipleStop("ATGGCGGTAAGACTACGCATAAGACATTGATAATAA");
        // System.out.println(res);

        findGeneWhile.PrintAllGeneInDna("ATGGCGGTAAGACTACGCATAAGACATTGATAATAAATGCGATGA");
        
    }
}
