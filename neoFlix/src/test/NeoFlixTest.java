package test;

import domain.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the NeoFlix main system.
 */
public class NeoFlixTest {

    /**
     * Should add an episode without serie.
     */
    @Test
    public void shouldAddAndEpisode() {
        NeoFlix neoFlix = new NeoFlix();
        try {
            neoFlix.addEpisode("the fly", "?", "180", "170", "10", "40");
            assertTrue(neoFlix.consult("the fly") != null);
        } catch (NeoFlixException e) {
            fail("It throw an exception");
        }
    }

    /**
     * Should add a Serie without episodes.
     */
    @Test
    public void shouldAddASerie() {
        NeoFlix neoFlix = new NeoFlix();
        try {
            neoFlix.addSeries("Breaking bad", "2008", "The Hidden Village");
            assertTrue(neoFlix.consult("Breaking bad") != null);
        } catch (NeoFlixException e) {
            fail("It throw an exception");
        }
    }

    /**
     * Should add a Serie with an Episode.
     */
    @Test
    public void shouldAddASerieWithAnEpisode() {
        NeoFlix neoFlix = new NeoFlix();
        try {
            neoFlix.addEpisode("the fly", "Breaking bad", "180", "170", "10", "40");
            neoFlix.addSeries("Breaking bad", "2008", "the fly");
            assertTrue(neoFlix.consult("Breaking bad") != null);
            assertTrue(neoFlix.consult("the fly") != null);
        } catch (NeoFlixException e) {
            fail("It throw an exception");
        }
    }

    /**
     * Should list the serie that has by default.
     */
    @Test
    public void ShouldListTheSerieThatHasByDefault() {
        NeoFlix neoFlix = new NeoFlix();
        String esperado = "6 elementos\n" +
                          ">The Hidden Village\n" +
                          ">The First Mission\n" +
                          ">The Rival\n" +
                          ">The Tournament\n" +
                          ">The Final Battle\n" +
                          ">The Neo Ninja: 2020" +
                          "\n\tThe Hidden Village" +
                          "\n\tThe First Mission" +
                          "\n\tThe Rival" +
                          "\n\tThe Tournament" +
                          "\n\tThe Final Battle\n";
        assertEquals(esperado, neoFlix.toString());
    }

    /**
     * Should list if I add an episode and a Serie to neoFlix.
     */
    @Test
    public void shouldListIfIAddAnEpisodeAndASerie() {
        NeoFlix neoFlix = new NeoFlix();
        try {
            neoFlix.addEpisode("the fly", "Breaking bad", "180", "170", "10", "40");
            neoFlix.addSeries("Breaking bad", "2008", "the fly");
            String esperado = "8 elementos\n" +
                              ">The Hidden Village\n" +
                              ">The First Mission\n" +
                              ">The Rival\n" +
                              ">The Tournament\n" +
                              ">The Final Battle\n" +
                              ">The Neo Ninja: 2020" +
                              "\n\tThe Hidden Village" +
                              "\n\tThe First Mission" +
                              "\n\tThe Rival" +
                              "\n\tThe Tournament" +
                              "\n\tThe Final Battle\n" +
                              ">the fly\n" +
                              ">Breaking bad: 2008" +
                              "\n\tthe fly\n";
            assertEquals(esperado, neoFlix.toString());
        } catch (NeoFlixException e) {
            fail("It throw an exception");
        }
    }

    /**
     * Should fail if serie has an episode that does not exists yet.
     */
    @Test
    public void shouldFailIfSerieHasAnEpisodeThatDoesNotExistsYet() {
        NeoFlix neoFlix = new NeoFlix();
        try {
            neoFlix.addSeries("Breaking bad", "2008", "the fly");
            fail("Should throw an exception");
        } catch (NeoFlixException e) {
            assertEquals(NeoFlixException.EPISODE_DOES_NOT_EXISTS, e.getMessage());
        }
    }

    /**
     * Should not add a serie if the serie already exists.
     */
    @Test
    public void shouldNotAddASerieIfTheSerieAlreadyExists() {
        NeoFlix neoFlix = new NeoFlix();
        try {
            neoFlix.addSeries("The Neo Ninja", "2020", "The Hidden Village");
            fail("It didn't throw an exception");
        } catch (NeoFlixException e) {
            assertEquals(NeoFlixException.CONTENT_ALREADY_EXISTS, e.getMessage());
        }
    }

    /**
     * Should not add a episode if the episode already exists.
     */
    @Test
    public void shouldNotAddAnEpisodeIfTheEpisodeAlreadyExists() {
        NeoFlix neoFlix = new NeoFlix();
        try {
            neoFlix.addEpisode("The Hidden Village", "?", "199", "120", "50", "430");
            fail("It didn't throw an exception");
        } catch (NeoFlixException e) {
            assertEquals(NeoFlixException.CONTENT_ALREADY_EXISTS, e.getMessage());
        }
    }

    /**
     * Should not add an episode if some numeric value is not a number.
     */
    @Test
    public void shouldNotAddAnEpisodeIfValueIsNotANumber() {
        NeoFlix neoFlix = new NeoFlix();
        try {
            neoFlix.addEpisode("Wilson'sStart", "?", "100", "ninguno:c", "1", "0");
            fail("It didn't throw an exception");
        } catch (NeoFlixException e) {
            assertEquals(NeoFlixException.NOT_NUMBER, e.getMessage());
        }
    }

    /**
     * Should not add an episode if completed is greater than attempts or sumVotes is greater than 10*votes.
     */
    @Test
    public void shouldNotAddAnEpisodeIfDataError() {
        NeoFlix neoFlix = new NeoFlix();
        try {
            neoFlix.addEpisode("Wilson'sStart", "?", "100", "200", "50", "1000");
            fail("It didn't throw an exception");
        } catch (NeoFlixException e) {
            assertEquals(NeoFlixException.DATA_ERROR, e.getMessage());
        }
    }

    /**
     * Should not add a serie if an episode does not exists.
     */
    @Test
    public void shouldNotAddASerieIfEpisodeDoesNotExists() {
        NeoFlix neoFlix = new NeoFlix();
        try {
            neoFlix.addSeries("Wilson's Adventure", "2026", "Wilson's Start");
            fail("It didn't throw an exception");
        } catch (NeoFlixException e) {
            assertEquals(NeoFlixException.EPISODE_DOES_NOT_EXISTS, e.getMessage());
        }
    }

    /**
     * Should search the serie that has by default.
     */
    @Test
    public void shouldSearchTheSerieThatHasByDefault() {
        NeoFlix neoFlix = new NeoFlix();
        String esperado = "6 elementos\n" +
                          ">The Hidden Village ( 60 - 8 )\n" +
                          ">The First Mission ( 90 - 8 )\n" +
                          ">The Rival ( 60 - 8 )\n" +
                          ">The Tournament ( 90 - 9 )\n" +
                          ">The Final Battle ( 95 - 9 )\n" +
                          ">The Neo Ninja: 2020 ( 79 - 8 )" +
                          "\n\tThe Hidden Village ( 60 - 8 )" +
                          "\n\tThe First Mission ( 90 - 8 )" +
                          "\n\tThe Rival ( 60 - 8 )" +
                          "\n\tThe Tournament ( 90 - 9 )" +
                          "\n\tThe Final Battle ( 95 - 9 )\n";
        System.out.println(neoFlix.search("T"));
        assertEquals(esperado, neoFlix.search("T"));
    }
}