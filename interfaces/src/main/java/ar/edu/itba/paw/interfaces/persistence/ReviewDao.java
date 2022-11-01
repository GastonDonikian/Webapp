package ar.edu.itba.paw.interfaces.persistence;


import ar.edu.itba.paw.models.Review;
import ar.edu.itba.paw.models.ReviewFilter;

import java.util.List;
import java.util.Optional;

public interface ReviewDao {
    Optional<Review> findById(Integer reviewId);
    List<Review> getReviews(ReviewFilter reviewFilter,int pageNumber, int size);
    int getReviewsCount(ReviewFilter reviewFilter);
    void newReview(Integer lessonId,Integer userId,String message,int rating);
    void changeReview(Review review);
    void deleteReview(Integer reviewId);
    Integer getAverageReviews(ReviewFilter reviewFilter);
}
