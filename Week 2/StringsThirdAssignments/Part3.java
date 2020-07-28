import edu.duke.*;

public class Part3
{
    public int findStopCodon(String dna, int startIndex, String stopCodon)
    {

        int stopIndex = dna.indexOf(stopCodon, startIndex + 3);

        while(stopIndex != -1){

            if((stopIndex - startIndex) % 3 == 0){
                return stopIndex;
            }
            else{
                stopIndex = dna.indexOf(stopCodon, stopIndex + 1);
            }
        }

        return -1;
    }

    public String findGene(String dna, int Index)
    {
        int startIndex = dna.indexOf("ATG", Index);

        if(startIndex == -1)
        {
            return "";
        }
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int minIndex = 0;

        if (taaIndex == -1 || (tgaIndex != -1 && tgaIndex < taaIndex))
        {
            minIndex = tgaIndex;
        }
        else {
            minIndex = taaIndex;
        }
        if (minIndex == -1 || (tagIndex != -1 && tagIndex < minIndex))
        {
            minIndex = tagIndex;
        }
        if (minIndex == -1){
            return "";
        }
        return dna.substring(startIndex, minIndex + 3);
    }

    public void printAllGenes(String dna)
    {
        int startIndex = 0;

        while ( true )
        {
            String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty())
            {
                break;
            }
            System.out.println(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
    }
    
    public StorageResource getAllGenes(String dna)
    {
        StorageResource geneList = new StorageResource();
        int startIndex = 0;
        int totalGenes = 0;

        while ( true )
        {
            String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty())
            {
                break;
            }
            geneList.add(currentGene);
            totalGenes++;
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
        System.out.println("Total Number of Genes: " + totalGenes);
        return geneList;
    }

    public double cgRatio (String dna)
    {
        double cgRatio = 0.0;
        for(int i = 0; i<dna.length(); i++)
        {
            if(dna.charAt(i) == 'C' || dna.charAt(i) == 'G')
            {
                cgRatio++;
            }
        }
        cgRatio = cgRatio/dna.length();
        return cgRatio;
    }
    
    public int countCTG (String dna)
    {
        int startIndex = 0,countCTG = 0;
        String stringa = "CTG";
        String stringb = dna;
        
        int currIndex = stringb.indexOf(stringa, startIndex);
        while(true)
        {
            if(currIndex != -1)
            {
                countCTG++;
            }
            else
            {
                break;
            }
            currIndex = stringb.indexOf(stringa,currIndex + stringa.length());
        }
        System.out.println("TOTAL CTG: " + countCTG);
        return countCTG;
    }
    
    
    public void processGenes(StorageResource sr)
    {
        int longerThanNine = 0;
        for(String gene: sr.data()) // print longer than 9 characters, and count them...
        {
            if(gene.length() > 60)
            {    
                System.out.println("String longer than 60 characters: " + gene);
                longerThanNine++;
            } 
        }
        System.out.println("TOTAL NUMBER OF Strings longer than 60 characters: " + longerThanNine);
        
        int cgRatioHigh = 0;
        for(String gene: sr.data()) // print the Strings in sr whose C-G-ratio is higher than 0.35 and count em
        {
            if(cgRatio(gene) > 0.35)
            {    
                System.out.println("String with high C-G-Ratio 0.35: " + gene);
                cgRatioHigh++;
            } 
        }
        System.out.println("TOTAL NUMBER OF Strings with high C-G-Ratio 0.35: " + cgRatioHigh);
        
        int maxLength = 0;
        for(String gene: sr.data()) //  length of the longest gene in sr
        {
            if(gene.length() > maxLength)
            {    
                maxLength = gene.length();
            } 
        }
        System.out.println("Length of the longest gene: " + maxLength);
    }
    
    
    
    
    public void testOn(String dna)
    {  
        countCTG(dna);
        System.out.println("Testing getAllGenes on " + dna);
        StorageResource geneList = getAllGenes(dna);
        
        for(String gene: geneList.data())
        {
            System.out.println("Genes: " + gene);
            
        }
        
        System.out.println("Processing Genes: ");
        processGenes(geneList);
    }
    
    
    public void test()
    {
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString().toUpperCase();
        testOn(dna);
    }

    public static void main (String[] args)
    {
        Part3 DNA = new Part3();
        DNA.test();
    }
}