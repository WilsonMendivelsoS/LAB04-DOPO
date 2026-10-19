package domain;



public abstract class Content{
    protected String title;

    
    public Content(String title){
        this.title=title;

    }
    
    
    /**
     * Return the title
     * @return
     */
    public String getTitle(){
        return title;
    }


    /**
     * Return the rating [0..10]
     * @return the content's rating
     * @throws NeoFlixException if it cannot be calculated
     */
    public abstract int rating() throws NeoFlixException;
    
    /**
     * Return the popularity [0..100]
     * @return the content's popularity
     * @throws NeoFlixException if the information cannot be calculated
     */    
    public abstract int popularity() throws NeoFlixException;
    
    /**
     * Return the textual representation
     * @param withIndicators if indicators should be included
     * @return the data representation of the content as a String
     * @throws NeoFlixException if it cannot be displayed
     */  
    public abstract String data(boolean withIndicators) throws NeoFlixException;

}
