package ExtraTasks;

public class Task
{

    public static void main(String[] argv)
    {
        try
        {
            main(null);
        } catch(StackOverflowError e)
        {
            System.err.println("ouch! " + e);
        }
    }

}