public class Part2
{
    public int howMany(String stringa, String stringb)
    {
        int startIndex = 0,howMany = 0;
        int currIndex = stringb.indexOf(stringa, startIndex);
        while(true)
        {
            if(currIndex != -1)
            {
                howMany++;
            }
            else
            {
                break;
            }
            currIndex = stringb.indexOf(stringa,currIndex + stringa.length());
        }
        return howMany;
    }

    public void testHowMany()
    {
        String stringa = "GAA";
        String stringb = "ATGAACGAATTGAATC";
        System.out.println(howMany(stringa, stringb));
        stringa = "AA";
        stringb = "ATAAAATAAA";
        System.out.println(howMany(stringa, stringb));
    }

    public static void main (String[] args)
    {
        Part2 DNA = new Part2();
        DNA.testHowMany();
    }
}