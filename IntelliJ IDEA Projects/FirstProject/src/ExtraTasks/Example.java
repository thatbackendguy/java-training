package ExtraTasks;


public class Example
{
    public static void main(String[] args)
    {
        String[] gameTerms = {"m", "pmg", "cpk"};

        int counter = 1;
        while(counter < 20)
        {
            for(int i = 0; i < gameTerms.length; i++)
            {
                for(int j = 0; j < counter; j++)
                {
                    System.out.print(counter + gameTerms[i] + '"' + "," + '"');
                }
            }
            counter++;
        }

    }
}