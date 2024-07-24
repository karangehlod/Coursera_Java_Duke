package Course_2;

public class Gene {
    public static String FindGene(String dna) {
        String result = "";
        //start codon ATG
        int startIndex = dna.indexOf("ATG");
        // end codon TAA
        int endIndex = dna.indexOf("TAA", startIndex+3);
        result = dna.substring(startIndex, endIndex+3);
        return result;
    }

    public static void main(String[] args) {
        String dnaString = "AATGCGTAATATGGT";
        System.out.println("Gene is " + FindGene(dnaString));
    }
    
}
