package test;
import domain.*;


import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class NeoFlixTest{
    
    /**
     * Should add an episode without serie.
     */
    @Test
    public void shouldAddAndEpisode(){
        NeoFlix neoFlix = new NeoFlix();
        neoFlix.addEpisode("the fly", "?", "180", "170", "10", "40");
        assertTrue(neoFlix.consult("the fly") != null);
        
    }
    /**
     * Should add a Serie without episodes.
     */
    @Test
    public void shouldAddASerie(){
        NeoFlix neoFlix = new NeoFlix();
        neoFlix.addSeries("Breaking bad", "2008", "");
        assertTrue(neoFlix.consult("Breaking bad") != null);
    }
    /**
     * Should add a Serie with an Episode
     */
    @Test
    public void shouldAddASerieWithAnEpisode(){
        NeoFlix neoFlix = new NeoFlix();
        neoFlix.addEpisode("the fly", "Breaking bad", "180", "170", "10", "40");
        neoFlix.addSeries("Breaking bad", "2008", "the fly");
        assertTrue(neoFlix.consult("Breaking bad") != null);
        assertTrue(neoFlix.consult("the fly") != null);
    }
    
    /**
     * Should list the serie that has by default. 
     */
    @Test
    public void ShouldListTheSerieThatHasByDefault(){
        NeoFlix neoFlix = new NeoFlix();
        String esperado = "6 elementos\n"+
                            ">The Hidden Village\n"+
                            ">The First Mission\n"+
                            ">The Rival\n"+
                            ">The Tournament\n"+
                            ">The Final Battle\n"+
                            ">The Neo Ninja: 2020"+
                        	"\n\tThe Hidden Village"+
                        	"\n\tThe First Mission"+
                        	"\n\tThe Rival"+
                        	"\n\tThe Tournament"+
                            	"\n\tThe Final Battle\n";
        assertEquals(esperado, neoFlix.toString());
    }
    /**
     * Should list if I add an episode and a Serie to neoFlix
     */
    @Test
    public void shouldListIfIAddAnEpisodeAndASerie(){
        NeoFlix neoFlix = new NeoFlix();
        neoFlix.addEpisode("the fly", "Breaking bad", "180", "170", "10", "40");
        neoFlix.addSeries("Breaking bad", "2008", "the fly");
        String esperado = "8 elementos\n"+
                            ">The Hidden Village\n"+
                            ">The First Mission\n"+
                            ">The Rival\n"+
                            ">The Tournament\n"+
                            ">The Final Battle\n"+
                            ">The Neo Ninja: 2020"+
                        	"\n\tThe Hidden Village"+
                        	"\n\tThe First Mission"+
                        	"\n\tThe Rival"+
                        	"\n\tThe Tournament"+
                            	"\n\tThe Final Battle\n"+
                            ">the fly\n"+
                            ">Breaking bad: 2008"+
                                "\n\tthe fly\n";
        assertEquals(esperado, neoFlix.toString());
    }
    
    /**
     * Should fail if serie has an episode that does not exists yet.
     */
    @Test
    public void shouldFailIfSerieHasAnEpisodeThatDoesNotExistsYet(){
        NeoFlix neoFlix = new NeoFlix();
        neoFlix.addSeries("Breaking bad", "2008", "the fly");
        try{
            String resultado = neoFlix.toString();
            fail("Should throw an exception");
        }
        catch(NullPointerException e){
            
        }
    }
}