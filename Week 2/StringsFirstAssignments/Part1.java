import edu.duke.*;
import java.io.*;

public class Part1
{
    public String findSimpleGene(String dna)
    {
        int start = dna.indexOf("ATG");
        int stop = dna.indexOf("TAA", start+3);
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
        String a1 = "AAAGTTTGTAA";
        String a2= "AATGASDASTTT";
        String a3 = "QWERTY";
        String a4 = "";
        String a5 = "AATGGGGGGGTAA";
        String a6 = "AATGGGGGGGGTAA";

        System.out.println("GENE: : " + findSimpleGene(a1));
        System.out.println("GENE: : " + findSimpleGene(a2));
        System.out.println("GENE: : " + findSimpleGene(a3));
        System.out.println("GENE: : " + findSimpleGene(a4));
        System.out.println("GENE: : " + findSimpleGene(a5));
        System.out.println("GENE: : " + findSimpleGene(a6));
    }
}


