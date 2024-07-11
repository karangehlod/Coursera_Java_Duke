import edu.duke.FileResource;
import edu.duke.StorageResource;

public class AllGene {

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

    public StorageResource GetAllGene(String dna) {
        // List<String> geneList = new ArrayList<String>();
        StorageResource geneList = new StorageResource();
        int count = 0;
        // find start index
        int startIndex = 0;
        while (true) {
            String currGene = FindGene(dna, startIndex);
            // break if no gene find
            if (currGene.isEmpty()) {
                System.out.println("total number of gene in dna is " + count);
                return geneList;
            }
            count += 1;
            // System.out.println(currGene);
            geneList.add(currGene);
            startIndex = dna.indexOf(currGene, startIndex) + currGene.length();
        }
    }

    //Count CTG
    public void CountCTG(String dna) {
        int count = 0;
        int startIndex = 0;
        String ctg = "CTG";
        while (true) {
            startIndex = dna.indexOf(ctg, startIndex);
            if (startIndex == -1) {
                System.out.println("total occurance of " + ctg + " in " + dna + " is " + count);
                break;
            }
            count += 1;
            startIndex += ctg.length();
        }
    }

    public float CGRatio(String dna) {
        int count = 0;
        for (int i = 0; i < dna.length(); i++) {
            if (dna.charAt(i) == 'C' || dna.charAt(i) == 'G') {
                count += 1;
            }
        }
        float res = (float) count / dna.length();
        return res;
    }

    
    public void ProcessGene(StorageResource sr) {
        int sizeCount = 0;
        int cgCount = 0;
        int maxSize = 0;
        for (String gene : sr.data()) {
            if (gene.length() > 9) {
                System.out.println(gene);
                sizeCount += 1;
            }
            if (CGRatio(gene) > 0.35) {
                System.out.println(gene);
                cgCount += 1;
            }
            if (maxSize < gene.length()) {
                maxSize = gene.length();
            }
        }
        System.out.println("the number of Strings in sr that are longer than 9 characters : " + sizeCount);
        System.out.println("the number of Strings in sr whose C-G-ratio is higher than 0.35 : " + cgCount);
        System.out.println("length of the longest gene in sr : "+ maxSize);
    }

    public static void main(String[] args) {
        AllGene strAssignment = new AllGene();

        // StorageResource resListGene = strAssignment.GetAllGene("AATGCTAACTAGCTGACTAAT");
        // for (String gene : resListGene.data()) {
        //     System.out.println(gene);
        // }
        // System.out.println(strAssignment.CGRatio("ATGCCATAG"));
        // strAssignment.CountCTG("ATGCCTGATAG");

        FileResource fr = new FileResource("input/dna/brca1line.fa");
        String dna = fr.asString();
        StorageResource sr = new StorageResource();
        sr.add(dna);
        strAssignment.ProcessGene(sr);

    }
}
