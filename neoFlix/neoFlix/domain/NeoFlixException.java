package domain;


/**
 * Represent the NeoFlixException Class
 * 
 * @author Wilson Mendivelso - David Garzon 
 * @version 2/10/2026
 */
public class NeoFlixException extends Exception
{
    public static String TO_BE_IMPLEMENTED = "To be implemented";
    public static String VALUE_UNKNOWN = "Value unknown";
    public static String DATA_ERROR = "Data error";
    public static String CONTENT_EMPTY="Content empty";
    /**
     * Constructor for objects of class NeoFlixException
     * @param message of Exception
     */
    public NeoFlixException(String message)
    {
       super(message);
    }
}