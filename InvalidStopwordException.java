import java.io.IOException;

public class InvalidStopwordException extends IOException
{
    public InvalidStopwordException(String pass)
    {
        super("Couldn't find stopword: " + pass);
    }
}
