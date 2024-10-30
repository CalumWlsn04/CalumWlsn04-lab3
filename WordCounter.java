import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class WordCounter {

    int processText(StringBuffer text, String stopword)
    {
        boolean wholeFile = false;
        int counter = 0;

        if (text.equals(null))
        {
            wholeFile = true;
        }

        Pattern regex = Pattern.compile("your regular expression here");
        Matcher regexMatcher = regex.matcher(text);
        while (regexMatcher.find()) {
            System.out.println("I just found the word: " + regexMatcher.group());
            counter++;
        }
        return counter;
    }

    StringBuffer processFile(String path)
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
