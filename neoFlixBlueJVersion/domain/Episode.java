package domain;
/**
 * Represents an episode that is part of a series.
 */
public class Episode extends Audiovisual{
    private Series series;
    /**
     * Creates a new episode and associates it with a series.
     * @param title the title of the episode
     * @param series the series to which it belongs
     */
    public Episode(String title, Series series){
        super(title);
        this.series=series;
    }
    /**
     * Creates a new episode with its viewing and rating metrics.
     * @param title the title of the episode
     * @param series the series to which it belongs
     * @param attempts number of viewing attempts
     * @param completed completed viewings
     * @param votes number of votes received
     * @param sumVotes total sum of the ratings
     */
    public Episode(String title, Series series, int attempts, int completed, int votes, int sumVotes){
        super(title, attempts, completed, votes, sumVotes);
        this.series=series;
    }
}