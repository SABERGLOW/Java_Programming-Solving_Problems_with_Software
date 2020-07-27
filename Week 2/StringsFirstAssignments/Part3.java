public class Part3
{
    public boolean twoOccurrences(String stringa, String stringb)
    {
        if(stringb.indexOf(stringa) == -1)
        {
            return false;
        }
        int firstOccur = stringb.indexOf(stringa);
        if(stringb.indexOf(stringa, firstOccur+1) != -1)
        {
            return true;
        }
        return false;
    }

    public String lastPart(String stringa, String stringb)
    {
        if(stringb.indexOf(stringa) == -1)
        {
            return stringb;
        }
        int firstOccur = stringb.indexOf(stringa);
        return stringb.substring(firstOccur+stringa.length(), stringb.length());
    }

    public void testing()
    {
        System.out.println(twoOccurrences("by", "A story by Abby Long"));
        System.out.println(twoOccurrences("a", "banana"));
        System.out.println(twoOccurrences("atg", "ctgtatgta"));

        System.out.println("The part of the string after an in banana is " + lastPart("an", "banana"));
        System.out.println("The part of the string after zoo in forest is " + lastPart("zoo", "forest"));
    }

    public static void main (String[] args)
    {
        Part3 DNA = new Part3();
        DNA.testing();
    }
}
