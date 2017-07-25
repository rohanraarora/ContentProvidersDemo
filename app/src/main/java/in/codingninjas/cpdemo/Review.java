package in.codingninjas.cpdemo;

/**
 * Created by ralph on 25/07/17.
 */

public class Review {

    private String authorName;
    private String review;

    public Review(String authorName, String review) {
        this.authorName = authorName;
        this.review = review;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
