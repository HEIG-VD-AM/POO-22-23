public class HelloWorld
{
    public static void main(String[] args)
    {
        int number;

        if (args.length == 0)
            number = 1 ;
        else
            // to obtain the value of the parameter
            // the args are the same as in C or C++
            number = Integer.parseInt(args[0]);


        for (int i = 0; i < number; ++i)
            // println -> carriage return
            // print   -> without return or space
            System.out.println(i + " Hello World!");
    }
}