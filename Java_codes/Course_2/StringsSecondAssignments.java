package Course_2;

public class StringsSecondAssignments {

    public String FindGene(String dna, int pos) {
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
        int count = 0;
        // find start index
        int startIndex = 0;
        while (true) {
            String currGene = FindGene(dna, startIndex);
            // break if no gene find
            if (currGene.isEmpty()) {
                System.out.println("total number of gene in dna is " + count);
                break;
            }
            count += 1;
            System.out.println(currGene);
            startIndex = dna.indexOf(currGene, startIndex) + currGene.length();
        }
    }

    public void HowMany(String a, String b) {
        int count = 0;
        int startIndex = 0;
        while (true) {
            startIndex = b.indexOf(a, startIndex);
            if (startIndex == -1) {
                System.out.println("total occurance of " + a + " in " + b + " is " + count);
                break;
            }
            count += 1;
            startIndex += a.length();
        }
    }

    public static void main(String[] args) {
        StringsSecondAssignments strAssignment = new StringsSecondAssignments();

        strAssignment.PrintAllGeneInDna("AATGCTAACTAGCTGACTAAT");
        strAssignment.HowMany("GAA", "ATGAACGAATTGAATC");
    }
}