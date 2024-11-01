import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class WordCounter {

    public static void main(String[] args) throws EmptyFileException, TooSmallText, InvalidStopwordException
    {
        int option = 0;
        while(option != 1 && option != 2)
        {
            if(option != 0)
            {
                System.out.println("You have entered bad input, please put in better input");
            }
            Scanner input = new Scanner(System.in);
            System.out.println("Please enter an option 1 or option 2: ");
            option = input.nextInt();
            //System.out.println("This is output: " +option);
            //System.out.println(option != 1);
            input.close();
        }
        
        //System.out.println("HELLOOEE");

        if (option == 1)
        {
            //System.out.println("In here");
            String path = "";
            String stopword = "";

            try
            {
                //System.out.println("In herereer");
                path = args[0];

                if (args.length > 1)
                {
                    stopword = args[1];
                    //if its a bad input
                }
                else
                {
                    stopword = null;
                }
                StringBuffer buffer = new StringBuffer();
                try
                {
                    buffer = processFile(path);
                }
                catch(EmptyFileException e)
                {
                    buffer = new StringBuffer("");
                }
                try
                {
                    int counter = processText(buffer, stopword);
                    System.out.println("Found " + counter + " words.");
                }
                catch(TooSmallText e)
                {
                    System.out.println(e);
                }


            }
            catch(InvalidStopwordException e)
            {
                //System.out.println("BAD");
            }

        }
        else if (option == 2)
        {
            System.out.println("Please enter a string for the input: ");
            Scanner input = new Scanner(System.in);

            String text = input.nextLine();
            StringBuffer buffer = new StringBuffer(text);
            int counter = processText(buffer, null);
            input.close();

            System.out.println("Found " + counter + " words.");
        }
    }

    static int processText(StringBuffer text, String stopword) throws TooSmallText, InvalidStopwordException
    {
        //boolean wholeFile = false;
        int counter = 0;
        boolean found = false;
        int val = 0;

        //System.out.println("This is the text: " + text);
        //System.out.println("This is text: " + text);

        Pattern regex = Pattern.compile("\\w+");
        Matcher regexMatcher = regex.matcher(text);
        while (regexMatcher.find()) {
            if (stopword != null && regexMatcher.group().equals(stopword) && found == false)
            {
                //counter++;
                found = true;
                val = counter + 1;
                //System.out.println("Found the stopword!");
                //if (counter < 5)
                //{
                    //throw new TooSmallText(Integer.toString(counter));
                //}
                //return counter;
            }
            //System.out.println("I just found the word: " + regexMatcher.group());
            counter++;
        }
        //System.out.println("This is counter: " + counter);
        if (counter < 5)
        {
            throw new TooSmallText(Integer.toString(counter) + " ");
        }
        if (stopword != null && found == false)
        {
            throw new InvalidStopwordException(stopword);
        }

        if (val == 0)
        {
            val = counter;
        }

        return val;
    }

    static StringBuffer processFile(String path) throws EmptyFileException
    {
        boolean forever = true;
        while(forever == true)
        {
            try{
                File file = new File(path);
                Scanner input = new Scanner(file);
                if (file.exists())
                {
                    //System.out.println("This exists!");
                    StringBuffer buffed = new StringBuffer();
                    if (file.length() == 0)
                    {
                        input.close();
                        throw new EmptyFileException(path);
                    }

                    while(input.hasNextLine())
                    {
                        buffed.append(input.nextLine());
                    }
                    input.close();
                    return buffed;
                    
                }
                input.close();
                //Scanner input = new Scanner(file);

            }
            catch(FileNotFoundException e)
            {
                System.out.println("Your file does not exist, please enter another file path: ");
                Scanner input = new Scanner(System.in);
                path = input.nextLine();
                input.close();
            }
            /*catch(EmptyFileException e)
            {

            }*/
        }
        return null;
    }





}
