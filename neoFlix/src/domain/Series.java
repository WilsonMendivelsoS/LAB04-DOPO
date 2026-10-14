package domain;  
 
import java.util.ArrayList;

public class Series extends Content{
   
    private int year;
    private ArrayList<Episode> episodes;
    

    public Series(String title, int year){
        super(title);
        this.year=year;
        episodes= new ArrayList<Episode>();
    }


  
    public void addEpisode(Episode e){
        episodes.add(e);
    }
       
    
   /**
   * Returns the calculated rating
   * @return
   * @throws NeoFlixException, CONTENT_EMPTY if the series has no episodes
   *                         , VALUE_UNKNOWN if the rating for any episode is unknown
   *                         , DATA_ERROR if the rating for any episode cannot be calculated due to a data error
   */
   @Override
   public int rating() throws NeoFlixException{
       if(episodes.size() == 0){
           throw new NeoFlixException(NeoFlixException.CONTENT_EMPTY);
       }
       int sum = 0;
       for(Episode e: episodes){
           int rating = e.rating();
           sum= sum + rating;
       }
       return sum/episodes.size();
   }
    
 
   /**
   * Returns the calculated rating, using the default value for episodes with an unknown rating and ignoring those with errors.
   * @param default_ is the default value if rating has no value
   * @return the series rating
   * @throws NeoFlixException, UNKNOWN_VALUE if the rating cannot be calculated.
   */

   public int rating(int default_) throws NeoFlixException{
       if(episodes.size() == 0){
           throw new NeoFlixException(NeoFlixException.CONTENT_EMPTY);
       }
       int sum = 0;
       int cont = 0;
       for(Episode e: episodes){
           int rating = 0;
           try{
               rating = e.rating();
               cont++;
           }
           catch(NeoFlixException ex){
               if(ex.getMessage().equals(NeoFlixException.VALUE_UNKNOWN)){
                   rating = default_;
                   cont++;
               }
               
           }
           
           sum= sum + rating;
       }
       return sum/cont;
   }
 
   /**
    * If an episode has no rating, use the average of the previous episodes or of all episodes, depending on the value of the previous parameter.
    * @param previous says if it will take previous rating for the unkown or data error values, or if it will take all the values
    * @return the serie's rating 
    * @Throw CONTENT_EMPTY and VALUE_UNKNOWN if either of these cases occurs.
    */
   public int rating(boolean previous) throws NeoFlixException{
        if(episodes.size() == 0){
           throw new NeoFlixException(NeoFlixException.CONTENT_EMPTY);
        }
        int sum = 0;
        int unkownOrDataError = 0;
        for(Episode e: episodes){
            int rating = 0;
            try{
                rating = e.rating();
            }
            catch(NeoFlixException ex){
               if(previous){
                   if(episodes.indexOf(e) != 0){
                       rating = sum/episodes.indexOf(e);
                   }  
                   else{
                       throw new NeoFlixException(NeoFlixException.VALUE_UNKNOWN);
                   }
               }
               else{
                   unkownOrDataError++;
               }
            }
            sum = sum + rating;
        }
        while(unkownOrDataError > 0){
            sum+= sum/(episodes.size()-unkownOrDataError);
            unkownOrDataError--;
        }
        return sum/episodes.size();
   }
    
   /**
    * Calculates the popularity of a serie, if it has episodes with unkown values, it will take them as popularity of 0.
    * @Throws NeoFlixException CONTENT_EMPTY if the series has no episodes
    */
    public int popularity() throws NeoFlixException{
       int sum = 0;
       if(episodes.size() == 0){
           throw new NeoFlixException(NeoFlixException.CONTENT_EMPTY);
        }
       for(Episode e: episodes){
           try{
               sum += e.popularity();
           }
           catch(NeoFlixException ex){
               sum += 0;
           }
       }
       
       return sum/episodes.size();
   }
    
    
    @Override
    public String data(boolean withIndicators) throws NeoFlixException{
        StringBuffer answer=new StringBuffer();
        answer.append(title+": "+year+ ( withIndicators ? " ( " +popularity()+" - "+ rating()+" )":""));
        for(Episode e: episodes) {
            answer.append("\n\t"+(e.data(withIndicators)));
        }
        return answer.toString();
    } 
    

}
