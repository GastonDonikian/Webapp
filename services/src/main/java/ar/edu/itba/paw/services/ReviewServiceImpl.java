package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.persistence.ReviewDao;
import ar.edu.itba.paw.interfaces.services.ReviewService;
import ar.edu.itba.paw.models.Review;
import ar.edu.itba.paw.models.ReviewFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
    final int PAGE_SIZE = 6;

    @Autowired
    private ReviewDao reviewDao;

    @Override
    @Transactional(readOnly = true)
    public List<Review> getReviews(ReviewFilter reviewFilter,int pageNumber) {
        if(pageNumber < 0)
            return reviewDao.getReviews(reviewFilter,1,100);

        return reviewDao.getReviews(reviewFilter,pageNumber,PAGE_SIZE);
    }

    @Override
    @Transactional(readOnly = true)
    public int getReviewsCount(ReviewFilter reviewFilter) {
        return reviewDao.getReviewsCount(reviewFilter);
    }

    @Override
    @Transactional(readOnly = true)
    public int getReviewsPages(ReviewFilter reviewFilter) {
        return (int) Math.ceil((double) reviewDao.getReviewsCount(reviewFilter) / PAGE_SIZE);
    }

    @Override
    @Transactional
    public void newReview(Integer lessonId,Integer userId,String message,int rating){
        reviewDao.newReview(lessonId,userId,message,rating);
    }

    @Override
    @Transactional
    public void changeReview(Integer reviewId,String message, Integer rating) {
        Optional<Review> optionalReview = reviewDao.findById(reviewId);
        if(!optionalReview.isPresent())
            return;
        final Review review = optionalReview.get();
        if(rating != null)
            review.setRating(rating);
        if(message != null && !message.isEmpty())
            review.setMessage(message);
        reviewDao.changeReview(review);
    }
}
