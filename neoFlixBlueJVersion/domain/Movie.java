package domain;

/**
 * Represents a movie within the NeoFlix platform.
 */
public class Movie extends Audiovisual{
    private int budge;
    /**
     * Creates a new movie.
     * @param title the title of the movie
     * @param budge the budget of the movie
     */
    public Movie(String title, int budge){
        super(title);
        this.budge=budge;
    }
}