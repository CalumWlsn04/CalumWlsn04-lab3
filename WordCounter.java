import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class WordCounter {

    public static void main(String[] args) throws EmptyFileException, TooSmallText, InvalidStopwordException
    {
        int option = 0;
        while(option != 1 && option != 2) //Makes sure that your input is correct, as in 1 or 2
        {
            if(option != 0)
            {
                System.out.println("You have entered bad input, please put in better input");
            }
            Scanner input = new Scanner(System.in);
            System.out.println("Please enter an option 1 or option 2: ");
            option = input.nextInt();
            input.close();
        }

        if (option == 1) //Tries to see if there is an argument or not within the try
        {
            String path = "";
            String stopword = "";

            try
            {
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
        int counter = 0;
        boolean found = false;
        int val = 0;

        Pattern regex = Pattern.compile("\\w+");
        Matcher regexMatcher = regex.matcher(text);

        while (regexMatcher.find()) {
            if (stopword != null && regexMatcher.group().equals(stopword) && found == false)
            {
                found = true;
                val = counter + 1;
            }
            counter++;
        }

        if (counter < 5) //If the number of words in the file is less than 5, throw an error
        {
            throw new TooSmallText(Integer.toString(counter) + " ");
        }
        if (stopword != null && found == false) //If the stopward is not null but it was not found, then throw an error
        {
            throw new InvalidStopwordException(stopword);
        }

        if (val == 0) //The val is equal to the counter if the stopward is never found
        {
            val = counter;
        }

        return val;
    }

    static StringBuffer processFile(String path) throws EmptyFileException
    {
        boolean forever = true; //This loop will run forever
        while(forever == true)
        {
            try{
                File file = new File(path);
                Scanner input = new Scanner(file);
                if (file.exists()) //Takes the path and gets the file
                {
                    StringBuffer buffed = new StringBuffer();
                    if (file.length() == 0) //A bad file
                    {
                        input.close();
                        throw new EmptyFileException(path);
                    }

                    while(input.hasNextLine()) //Appends a line in the stringbuffer
                    {
                        buffed.append(input.nextLine());
                    }
                    input.close();
                    return buffed;
                    
                }
                input.close();
            }
            catch(FileNotFoundException e)
            {
                System.out.println("Your file does not exist, please enter another file path: ");
                Scanner input = new Scanner(System.in);
                path = input.nextLine();
                input.close();
            }
        }
        return null; //You should never get here
    }
}