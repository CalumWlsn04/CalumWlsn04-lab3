import java.io.IOException;

public class TooSmallText extends IOException
{
    public TooSmallText(String pass)
    {
        super("Only found " + pass + "words.");
    }
}