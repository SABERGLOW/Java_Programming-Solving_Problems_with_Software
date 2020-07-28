public class quiz
{

public void findAbc(String input)
  {
    int index = input.indexOf("abc");
    System.out.println(" Index: " + index);
    while (true) 
    {
        if (index == -1 || index >= input.length()-3)
        {
            break;
        }

        String found = input.substring(index+1, index+4);
        System.out.println(found);
        index = input.indexOf("abc", index+3);
    }
        System.out.println(" Index updated: " + index);
  }
   public void test() 
   {
    //no code yet
     //findAbc("abcd");
      //        0123456789
     findAbc("abcabcabcabca");
   }
}