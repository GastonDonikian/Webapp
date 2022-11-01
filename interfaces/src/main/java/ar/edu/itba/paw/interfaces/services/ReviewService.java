package ar.edu.itba.paw.interfaces.services;

import ar.edu.itba.paw.models.Review;
import ar.edu.itba.paw.models.ReviewFilter;

import java.util.List;

public interface ReviewService {
    List<Review> getReviews(ReviewFilter reviewFilter,int pageNumber);
    void newReview(Integer lessonId,Integer userId,String message,int rating);
    void changeReview(Integer reviewId,String message,Integer rating);
    int getReviewsCount(ReviewFilter reviewFilter);
    int getReviewsPages(ReviewFilter reviewFilter);
}
