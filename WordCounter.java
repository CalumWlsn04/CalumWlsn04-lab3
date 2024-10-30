import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class WordCounter {

    public static int main(String[] args) throws EmptyFileException, TooSmallText, InvalidStopwordException
    {
        String option = "";
        while(!option.equals("1") || !option.equals("2"))
        {
            if(!option.equals(""))
            {
                System.out.println("You have entered bad input, please put in better input");
            }
            Scanner input = new Scanner(System.in);
            System.out.println("Please enter an option 1 or option 2: ");
            option = input.nextLine();
        }

        if (option.equals("1"))
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
                buffer = processFile(path);

                int counter = processText(buffer, stopword);
                return counter;


            }
            catch(InvalidStopwordException e)
            {
                System.out.println("BAD");
            }

        }
        else if (option.equals("2"))
        {
            System.out.println("Please enter a string for the input: ");
            Scanner input = new Scanner(System.in);

            String text = input.nextLine();
            StringBuffer buffer = new StringBuffer(text);
            int counter = processText(buffer, null);
            return counter;
        }
        return 0;
    }

    static int processText(StringBuffer text, String stopword) throws TooSmallText, InvalidStopwordException
    {
        //boolean wholeFile = false;
        int counter = 0;

        //System.out.println("This is the text: " + text);
        System.out.println("HELLO");

        Pattern regex = Pattern.compile("\\w+");
        Matcher regexMatcher = regex.matcher(text);
        while (regexMatcher.find()) {
            if (!stopword.equals(null) && regexMatcher.group().equals(stopword))
            {
                counter++;
                if (counter < 5)
                {
                    throw new TooSmallText(Integer.toString(counter));
                }
                return counter;
            }
            System.out.println("I just found the word: " + regexMatcher.group());
            counter++;
        }
        if (!stopword.equals(null))
        {
            throw new InvalidStopwordException(stopword);
        }

        return counter;
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
                    System.out.println("This exists!");
                    StringBuffer buffed = new StringBuffer();
                    if (file.length() == 0)
                    {
                        throw new EmptyFileException(path);
                    }

                    while(input.hasNextLine())
                    {
                        buffed.append(input.nextLine());
                    }
                    return buffed;
                    
                }
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
