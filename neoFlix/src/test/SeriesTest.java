package test;
import domain.*;


import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class SeriesTest{
   
 
    @Test
    public void shouldCalculateSeriessRating(){                              
        Series s = new Series("The Neo Ninja", 2020);
        s.addEpisode(new Episode("The Hidden Village",s,120,199,50,430));
        s.addEpisode(new Episode("The First Mission",s,140,126,60,510));
        s.addEpisode(new Episode("The Rival",s,120,199,50,430));
        s.addEpisode(new Episode("The Tournament",s,180, 162, 90, 810));
        s.addEpisode(new Episode("The Final Battle",s, 200, 190, 120, 1080));
        try {
           assertEquals(8,s.rating());
        } catch (NeoFlixException e){
            fail("Threw a exception "+e.getMessage());
        }    
    }    
    @Test
    public void shouldThrowExceptionIfTheSeriessHasNoEpisodes(){
        Series s = new Series("The Neo Ninja", 2020);
        try { 
           int value=s.rating();
           fail("Did not throw exception");
        } catch (NeoFlixException e) {
            assertEquals(NeoFlixException.CONTENT_EMPTY,e.getMessage());
        }    
    }    
    @Test
    public void shouldThrowExceptionWhenAnEpisodeRatingIsUnknown(){
        Series s = new Series("The Neo Ninja", 2020);
        s.addEpisode(new Episode("The Hidden Village",s,120,199,50,430));
        s.addEpisode(new Episode("The First Mission",s,140,126,60,510));
        s.addEpisode(new Episode("The Rival",s,120,199,0,0));
        s.addEpisode(new Episode("The Tournament",s,180, 162, 90, 810));
        s.addEpisode(new Episode("The Final Battle",s, 200, 190, 120, 1080));
        try { 
           int value=s.rating();
           fail("Did not throw exception");
        } catch (NeoFlixException e) {
            assertEquals(NeoFlixException.VALUE_UNKNOWN,e.getMessage());
        }    
    } 
   @Test
    public void shouldThrowExceptionIfAEpisodeContainsInvalidRatingData(){
        Series s = new Series("The Neo Ninja", 2020);
        s.addEpisode(new Episode("The Hidden Village",s,120,199,50,430));
        s.addEpisode(new Episode("The First Mission",s,140,126,60,610));
        s.addEpisode(new Episode("The Rival",s,120,199,0,0));
        s.addEpisode(new Episode("The Tournament",s,180, 162, 90, 810));
        s.addEpisode(new Episode("The Final Battle",s, 200, 190, 120, 1080));
        try { 
           int value=s.rating();
           fail("Did not throw exception");
        } catch (NeoFlixException e) {
            assertEquals(NeoFlixException.DATA_ERROR,e.getMessage());
        }    
    }     
    
    
    /**
     * If we give a default value for rating, but all episodes have rating, the result shouldn't change
     */
    @Test
    public void shouldCountAllNormallyIfADefaultValueIsGiven(){
        Series s = new Series("Breaking Bad", 2008);
        s.addEpisode(new Episode("Ozymandias", s, 120, 199, 100, 1000));
        s.addEpisode(new Episode("Felina", s, 110, 180, 50, 400));
        s.addEpisode(new Episode("Heisenberg", s, 120, 199, 20, 120));
        s.addEpisode(new Episode("the fly", s, 120, 199, 10, 40));
        
        try{
            assertEquals(7, s.rating(10));
        }
        catch(NeoFlixException e){
            fail("It throw an exception");
        }
    }
    /**
     * If we give a default value for rating, and there's at least one with a possible UNKOWN_VALUE, we change it's rating value for the default value.
     */
    @Test
    public void shoulfChangeIfThereIsAnUnkownValueAndADefaultValueIsGiven(){
        Series s = new Series("Breaking Bad", 2008);
        s.addEpisode(new Episode("Ozymandias", s, 120, 199, 100, 1000));
        s.addEpisode(new Episode("Felina", s, 110, 180, 50, 400));
        s.addEpisode(new Episode("Heisenberg", s, 120, 199, 0, 0));
        s.addEpisode(new Episode("the fly", s, 120, 199, 10, 40));
        
        try{
            assertEquals(8, s.rating(10));
        }
        catch(NeoFlixException e){
            fail("It throw an exception");
        }
    }
    /**
     * If we give a default value for rating a there's a Data Error it should ignore it.
     */
    @Test
    public void shouldIgnoreIfThereIsDataErrorAndADefaultValueIsGiven(){
        Series s = new Series("Breaking Bad", 2008);
        s.addEpisode(new Episode("Ozymandias", s, 120, 199, 100, 1000));
        s.addEpisode(new Episode("Felina", s, 110, 180, 50, 540));
        s.addEpisode(new Episode("Heisenberg", s, 120, 199, 20, 120));
        s.addEpisode(new Episode("the fly", s, 120, 199, 10, 40));
        
        try{
            assertEquals(20/3, s.rating(10));
        }
        catch(NeoFlixException e){
            fail("It throw an exception");
        }
    }
    /**
     * If there is an unkown value and there is a data error, it should ignore the data error and should
     * change the unkown value.
     */
    @Test
    public void shouldIgnoreDataErrorAndChangeUnkownValueIfADefaultValueIsGiven(){
        Series s = new Series("Breaking Bad", 2008);
        s.addEpisode(new Episode("Ozymandias", s, 120, 199, 100, 1000));
        s.addEpisode(new Episode("Felina", s, 110, 180, 50, 540));
        s.addEpisode(new Episode("Heisenberg", s, 120, 199, 0, 0));
        s.addEpisode(new Episode("the fly", s, 120, 199, 10, 40));
        
        try{
            assertEquals(8, s.rating(10));
        }
        catch(NeoFlixException e){
            fail("It throw an exception");
        }
    }
    
    
    
    /**
     * If previous is true, but all episodes have rating, it should count rating normally
     */
    @Test
    public void shouldCountNormallyIfPreviousIsTrue(){
        Series s = new Series("Breaking Bad", 2008);
        s.addEpisode(new Episode("Ozymandias", s, 120, 199, 100, 1000));
        s.addEpisode(new Episode("Felina", s, 110, 180, 50, 400));
        s.addEpisode(new Episode("Heisenberg", s, 120, 199, 20, 120));
        s.addEpisode(new Episode("the fly", s, 120, 199, 10, 40));
        
        try{
            assertEquals(7, s.rating(true));
        }
        catch(NeoFlixException e){
            fail("It throw an exception");
        }
    }
    /**
     * If previous is true and there's an unkown value it should calculate with its rating with the previous episodes. 
     */
    @Test
    public void shouldCountPreviousIfValueIsUnknown(){
        Series s = new Series("Breaking Bad", 2008);
        s.addEpisode(new Episode("Ozymandias", s, 120, 199, 100, 1000));
        s.addEpisode(new Episode("Felina", s, 110, 180, 50, 400));
        s.addEpisode(new Episode("Heisenberg", s, 120, 199, 0, 0));
        s.addEpisode(new Episode("the fly", s, 120, 199, 10, 40));
        
        try{
            assertEquals(31/4, s.rating(true));
        }
        catch(NeoFlixException e){
            fail("It throw an exception");
        }
    }
    /**
     * If previous is true and there's a data error it should calculate with its rating with the previous episodes. 
     */
    @Test
    public void shouldCountPreviousIfValueIsDataError(){
        Series s = new Series("Breaking Bad", 2008);
        s.addEpisode(new Episode("Ozymandias", s, 120, 199, 100, 1000));
        s.addEpisode(new Episode("Felina", s, 110, 180, 50, 540));
        s.addEpisode(new Episode("Heisenberg", s, 120, 199, 20, 120));
        s.addEpisode(new Episode("the fly", s, 120, 199, 10, 40));
        
        try{
            assertEquals(15/2, s.rating(true));
        }
        catch(NeoFlixException e){
            fail("It throw an exception");
        }
    }
    /**
     *If previous is true and there's a data error or an unkown value it should calculate with its rating with the previous episodes. 
     */
    @Test
    public void shouldCountPreviousIfValueIsDataErrorOrAnUnkownValue(){
        Series s = new Series("Breaking Bad", 2008);
        s.addEpisode(new Episode("Ozymandias", s, 120, 199, 100, 1000));
        s.addEpisode(new Episode("Felina", s, 110, 180, 50, 540));
        s.addEpisode(new Episode("Heisenberg", s, 120, 199, 0, 0));
        s.addEpisode(new Episode("the fly", s, 120, 199, 10, 40));
        
        try{
            assertEquals(17/2, s.rating(true));
        }
        catch(NeoFlixException e){
            fail("It throw an exception");
        }
    }
    
    
    /**
     * If previous is false, but all episodes have rating it should calculate with its original rating
     */
    @Test
    public void shouldCountNormallyIfPreviousIsFalse(){
        Series s = new Series("Breaking Bad", 2008);
        s.addEpisode(new Episode("Ozymandias", s, 120, 199, 100, 1000));
        s.addEpisode(new Episode("Felina", s, 110, 180, 50, 400));
        s.addEpisode(new Episode("Heisenberg", s, 120, 199, 20, 120));
        s.addEpisode(new Episode("the fly", s, 120, 199, 10, 40));
        
        try{
            assertEquals(7, s.rating(false));
        }
        catch(NeoFlixException e){
            fail("It throw an exception");
        }
    }
    /**
     * If previous is false, and there is an unkown value, first calculate the other rating values and then it put that average to the unkown value
     */
    @Test
    public void shouldCountAllIfValueIsUnknown(){
        Series s = new Series("Breaking Bad", 2008);
        s.addEpisode(new Episode("Ozymandias", s, 120, 199, 100, 1000));
        s.addEpisode(new Episode("Felina", s, 110, 180, 50, 400));
        s.addEpisode(new Episode("Heisenberg", s, 120, 199, 0, 0));
        s.addEpisode(new Episode("the fly", s, 120, 199, 10, 40));
        
        try{
            assertEquals(22/3, s.rating(false));
        }
        catch(NeoFlixException e){
            fail("It throw an exception");
        }
    }
    /**
     * If previous is false, and there is a data error, first calculate the other rating values and then it put that average to the data error 
     */
    @Test
    public void shouldCountAllIfThereIsADataError(){
        Series s = new Series("Breaking Bad", 2008);
        s.addEpisode(new Episode("Ozymandias", s, 120, 199, 100, 1000));
        s.addEpisode(new Episode("Felina", s, 110, 180, 50, 540));
        s.addEpisode(new Episode("Heisenberg", s, 120, 199, 20, 120));
        s.addEpisode(new Episode("the fly", s, 120, 199, 10, 40));
        
        try{
            assertEquals(6, s.rating(false));
        }
        catch(NeoFlixException e){
            fail("It throw an exception");
        }
    }
    /**
     * If previous is false, and there's at least one data error and an unkown value, it will put the rating of all the others to those.
     */
    @Test
    public void shouldCountAllIfValueIsDataErrorOrAnUnkownValue(){
        Series s = new Series("Breaking Bad", 2008);
        s.addEpisode(new Episode("Ozymandias", s, 120, 199, 100, 1000));
        s.addEpisode(new Episode("Felina", s, 110, 180, 50, 540));
        s.addEpisode(new Episode("Heisenberg", s, 120, 199, 0, 0));
        s.addEpisode(new Episode("the fly", s, 120, 199, 10, 40));
        
        try{
            assertEquals(7, s.rating(false));
        }
        catch(NeoFlixException e){
            fail("It throw an exception");
        }
    }
    
    /**
     * Should count the popularity if all the values are known.
     */
    @Test
    public void shouldCalculatePopularityIfAllValuesAreKnown(){
        Series s = new Series("Breaking Bad", 2008);
        s.addEpisode(new Episode("Ozymandias", s, 199, 120, 100, 1000));
        s.addEpisode(new Episode("Felina", s, 180, 110, 50, 400));
        s.addEpisode(new Episode("Heisenberg", s, 199, 120, 20, 120));
        s.addEpisode(new Episode("the fly", s, 199, 120, 10, 40));
        
        try{
            assertEquals(60, s.popularity());
        }
        catch(NeoFlixException e){
            fail("It throw an exception");
        }
    }
    /**
     * If a serie has no episodes it should launch an exception.
     */
    @Test
    public void shouldLaunchAnExceptionIfSerieHasNoEpisodes(){
        Series s = new Series("Breaking Bad", 2008);
        try{
            int popularity = s.popularity();
            fail("It didn't throw an exception");
        }
        catch (NeoFlixException e){
            assertEquals(NeoFlixException.CONTENT_EMPTY,e.getMessage());
        }
    }
    /**
     * Should put 0 in popularity, if episode has unkown values. 
     */
    @Test
    public void shouldPutZeroInPopularityForEpisodesWithUnkownValues(){
        Series s = new Series("Breaking Bad", 2008);
        s.addEpisode(new Episode("Ozymandias", s, 199, 120, 100, 1000));
        s.addEpisode(new Episode("Felina", s, 0, 180, 50, 400));
        s.addEpisode(new Episode("Heisenberg", s, 199, 120, 20, 120));
        s.addEpisode(new Episode("the fly", s, 0, 199, 10, 40));
        
        try{
            assertEquals(30, s.popularity());
        }
        catch(NeoFlixException e){
            fail("It throw an exception");
        }
    }
    /**
     * Should put 0 in popularity, if episodes has data error values.
     */
    @Test
    public void shouldPutZeroInPopularityIfHasEpisodesWithDataError(){
        Series s = new Series("Breaking Bad", 2008);
        s.addEpisode(new Episode("Ozymandias", s, 199, 120, 100, 1000));
        s.addEpisode(new Episode("Felina", s, 20, 180, 50, 400));
        s.addEpisode(new Episode("Heisenberg", s, 199, 120, 20, 120));
        s.addEpisode(new Episode("the fly", s, 20, 199, 10, 40));
        
        try{
            assertEquals(30, s.popularity());
        }
        catch(NeoFlixException e){
            fail("It throw an exception");
        }
    }
}