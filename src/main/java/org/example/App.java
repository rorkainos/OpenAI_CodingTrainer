package org.example;

/**
 * A simple command line application that takes user input as an argument
 * and outputs it to the console
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("--input"))
            {
                if (i + 1 < args.length && !args[i + 1].startsWith("--")) {
                    System.out.println(args[i + 1]);
                    return;
                }
            }
        }

        System.out.println( "No value was given for the --input argument");
    }
}
