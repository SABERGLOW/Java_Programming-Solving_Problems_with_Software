public class Part2
{
    public String findSimpleGene(String dna, String startCodon , String stopCodon )
    {
        dna.toUpperCase();
        int start = dna.indexOf(startCodon);
        int stop = dna.indexOf(stopCodon, start+3);
        if (start == -1 || stop == -1)
        {
            return "";
        }
        if ((stop - start) % 3 == 0)
        {
            return dna.substring(start, stop+3);
        }
        return "";
    }

    public void testSimpleGene ()
    {
        String startCodon = "ATG";
        String stopCodon = "TAA";

        String a1 = "AAAGTTTGTAA";
        String a2= "AATGASDASTTT";
        String a3 = "QWERTY";
        String a4 = "";
        String a5 = "AATGGGGGGGTAA";
        String a6 = "AAATGCCCTAACTAGATTAAGAAACC";

        System.out.println("GENE: : " + findSimpleGene(a1, startCodon, stopCodon));
        System.out.println("GENE: : " + findSimpleGene(a2, startCodon, stopCodon));
        System.out.println("GENE: : " + findSimpleGene(a3, startCodon, stopCodon));
        System.out.println("GENE: : " + findSimpleGene(a4, startCodon, stopCodon));
        System.out.println("GENE: : " + findSimpleGene(a5, startCodon, stopCodon));
        System.out.println("GENE: : " + findSimpleGene(a6, startCodon, stopCodon));
    }


    public static void main (String[] args)
    {
        Part2 DNA = new Part2();
        DNA.testSimpleGene();
    }
}
