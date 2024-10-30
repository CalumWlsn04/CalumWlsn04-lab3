import java.io.IOException;

public class EmptyFileException extends IOException
{
    public EmptyFileException(String pass)
    {
        super(pass);
    }
}
