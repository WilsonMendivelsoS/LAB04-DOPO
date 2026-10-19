package domain;


/**
 * Represent the NeoFlixException Class
 * 
 * @author Wilson Mendivelso - David Garzon 
 * @version 2/10/2026
 */
public class NeoFlixException extends Exception
{
    /** Exception when a method has not yet been implemented */
    public static String TO_BE_IMPLEMENTED = "To be implemented";
    
    /** Exception when a value cannot be calculated due to missing data */
    public static String VALUE_UNKNOWN = "Value unknown";
    
    /** Exception when there is a logical inconsistency in the data */
    public static String DATA_ERROR = "Data error";
    
    /** Exception when a content lacks internal elements (e.g., a series without episodes) */
    public static String CONTENT_EMPTY="Content empty";
    
    /** Exception when trying to register an element that already exists */
    public static String CONTENT_ALREADY_EXISTS =  "Content already exists";
    
    /** Exception when a numeric value is expected but another data type is received */
    public static String NOT_NUMBER = "Some of the content isn't a number";
    /**
     * Constructor for objects of class NeoFlixException
     * @param message of Exception
     */
    public NeoFlixException(String message)
    {
       super(message);
    }
}