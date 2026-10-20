package domain;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * NeoFlix
 * @author DOPO
 * @version ECI 2026
 */

public class NeoFlix{
    private ArrayList<Content> contents;
    private TreeMap<String,Episode> episodes;

    /**
     * Create a NeoFlix
     */
    public NeoFlix(){
        contents = new ArrayList<Content>();
        episodes = new TreeMap<String,Episode>();
        addSomeSeries();
    }

    
    /**
    * Adds sample series and episodes to NeoFlix.
    */
    private void addSomeSeries(){
        
        String [][] episodes= {{"The Hidden Village","199","120","50","430"},
                               {"The First Mission","140","126","60","510"},
                               {"The Rival","199","120","50","430"},
                               {"The Tournament","180","162", "90", "810"},
                               {"The Final Battle","200", "190", "120", "1080"}};
        StringBuffer titles=new StringBuffer();
        for (String [] e : episodes){
            titles.append(e[0]+"\n");
        }
        
        for (String [] e: episodes){
            try{
                addEpisode(e[0],"?", e[1],e[2],e[3],e[4]);
            }
            catch(NeoFlixException ex){
                
            }
        }
        String [][] series = {{"The Neo Ninja", "2020", titles.toString()}};
        for (String [] s: series){
            try{
                addSeries(s[0],s[1],s[2]);
            }
            catch(NeoFlixException ex){
                
            }
        }


    }


    /**
     * Consult a contents
     * @param name the name of the content to be consulted
     * @return the content if found, null otherwise
     */
    public Content consult(String name){
        Content c=null;
        for(int i=0;i<contents.size() && c == null;i++){
            if (contents.get(i).getTitle().compareToIgnoreCase(name)==0) 
               c=contents.get(i);
        }
        return c;
    }

    
    /**
     * Adds a new episode.
     * @param title
     * @param series
     * @param attempts
     * @param completed
     * @param votes
     * @param sumVotes
     * @throws CONTENT_ALREADY_EXISTS, if content already exists
     * @throws NOT_NUMBER if some of the content that must be a number isn't
     * @throws VALUE_UNKNOWN if some of the values are unknown.
     * @throws DATA_ERROR if the data presents an error
     */
    public void addEpisode(String title, String series, String attempts, String completed, String votes, String sumVotes) throws NeoFlixException{
        boolean newEpisode = newContent(title);
        int intAttempts = transformToNumber(attempts);
        int intCompleted = transformToNumber(completed);
        int intVotes = transformToNumber(votes);
        int intSumVotes = transformToNumber(sumVotes);
        Series s = (Series) consult(series);
        Episode e=new Episode(title,s, intAttempts, intCompleted, intVotes, intSumVotes);
        e.popularity();
        e.rating();
        contents.add(e);
        episodes.put(title.toUpperCase(),e);
    }
    
    
   /**
     * Add a new series
     * @param title
     * @param year
     * @param theEpisodes the titles of the episodes separated by newlines
     * @throws CONTENT_ALREADY_EXISTS, if content already exists
     * @throws NOT_NUMBER if some of the content that must be a number isn't
     * @throws VALUE_UNKNOWN if some of the values are unknown.
     * @throws DATA_ERROR if the data presents an error
     * @throws EPISODE_DOES_NOT_EXISTS if the episode doesn't exists
     */
    public void addSeries(String title, String year, String theEpisodes) throws NeoFlixException{
        boolean newSerie = false;
        newSerie = newContent(title);
        int intYear = transformToNumber(year);
        Series s = new Series(title,intYear);
        String [] aEpisodes= theEpisodes.split("\n");
        for (String te : aEpisodes){
            Episode ep = episodes.get(te.toUpperCase());
            if (ep == null){
                throw new NeoFlixException(NeoFlixException.EPISODE_DOES_NOT_EXISTS);
            }
            s.addEpisode(episodes.get(te.toUpperCase()));
        }
        contents.add(s);
    }

    /**
     * Returns the contents whose title starts with the given prefix
     * @param prefix
     * @return
     */
    public ArrayList<Content> select(String prefix){
        ArrayList <Content> answers=new ArrayList<Content>();
        prefix=prefix.toUpperCase();
        for(int i=0;i<contents.size();i++){
            if(contents.get(i).getTitle().toUpperCase().startsWith(prefix)){
                answers.add(contents.get(i));
            }
        }
        return answers;
    }


    
    /**
     * Return the data of the selected contents
     * @param selected the selected contents
     * @param withIndicators true if indicators should be included
     * @return the textual representation of the selected contents
     */
    public String data(ArrayList<Content> selected, boolean withIndicators){
        StringBuffer answer=new StringBuffer();
        answer.append(selected.size()+ " elementos\n");
        for(Content c : selected) {
            try{
                answer.append('>' + c.data(withIndicators));
                answer.append("\n");
            }catch(NeoFlixException e){
                answer.append("**** "+e.getMessage());
            }
        }    
        return answer.toString();
    }
    
    
    /**
     * Return the data of episodes with a prefix
     * @param prefix the prefix to search for
     * @return the data of the contents matching the prefix
     */ 
    public String search(String prefix){
        return data(select(prefix),true);
    }
    
    
    /**
     * Return the data of all episodes
     * @return the data of all registered contents
     */  
    public String toString(){
        return data(contents,false);
    }
    
    /**
     * Consult the number of episodes
     * @return the total number of contents in NeoFlix
     */
    public int numberContents(){
        return contents.size();
    }
    
    /**
     * Consults if it is a new content
     * @param title is the title of the content
     * @return if content exists
     * @throws NeoFlixException - CONTENT_ALREADY_EXISTS
     */
    
    private boolean newContent(String title) throws NeoFlixException{
        Content c = consult(title);
        if(c != null){
            throw new NeoFlixException(NeoFlixException.CONTENT_ALREADY_EXISTS);
        }
        return true;
    }
    
    /**
     * Transforms a String to an int
     * @param transform is the String to transform
     * @return the int
     * @throws NeoFlixException - NOT_NUMBER
     */
    private int transformToNumber(String transform) throws NeoFlixException{
        int return_ = 0;
        try{
            return_ = Integer.parseInt(transform);
        }
        catch(java.lang.NumberFormatException e){
            throw new NeoFlixException(NeoFlixException.NOT_NUMBER);
        }
        return return_;
    }
}
