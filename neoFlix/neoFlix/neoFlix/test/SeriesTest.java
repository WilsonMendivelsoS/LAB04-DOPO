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
    @Test
    public void shouldCountAllNormally(){
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
    @Test
    public void shoulfChangeIfThereIsAnUnkownValue(){
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
    
    @Test
    public void shouldIgnoreIfThereIsDataError(){
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
    
    @Test
    public void shouldIgnoreDataErrorAndChangeUnkownValue(){
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
    @Test
    public void shouldCountPreviousIfValueUnknown(){
        Series s = new Series("Breaking Bad", 2008);
        s.addEpisode(new Episode("Ozymandias", s, 120, 199, 100, 1000));
        s.addEpisode(new Episode("Felina", s, 110, 180, 50, 400));
        s.addEpisode(new Episode("Heisenberg", s, 120, 199, 0, 0));
        s.addEpisode(new Episode("the fly", s, 120, 199, 10, 40));
        
        try{
            assertEquals(8, s.rating(false));
        }
        catch(NeoFlixException e){
            fail("It throw an exception");
        }
    }
    
    @Test
    public void shouldIgnoreIfThereIsDataError2(){
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
    
    @Test
    public void shouldIgnoreDataErrorAndChangeUnkownValue2(){
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
}