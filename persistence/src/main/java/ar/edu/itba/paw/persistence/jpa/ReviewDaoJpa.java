package ar.edu.itba.paw.persistence.jpa;

import ar.edu.itba.paw.interfaces.persistence.ReviewDao;
import ar.edu.itba.paw.models.Lesson;
import ar.edu.itba.paw.models.Review;
import ar.edu.itba.paw.models.ReviewFilter;
import ar.edu.itba.paw.models.User;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

@Primary
@Repository
public class ReviewDaoJpa implements ReviewDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<Review> findById(Integer reviewId) {
        return Optional.ofNullable(em.find(Review.class, reviewId));
    }

    @Override
    public List<Review> getReviews(ReviewFilter reviewFilter,int pageNumber, int size) {
        final Query nativeQuery = queryGetReviews("SELECT review_id ",reviewFilter,true,pageNumber,size);

        final List<Integer> reviewIdList = ((List<Integer>) nativeQuery.getResultList());

        if(reviewIdList.isEmpty())
            return Collections.emptyList();
        final TypedQuery<Review> query = em.createQuery("from Review where reviewId IN :reviewIdList",Review.class);
        query.setParameter("reviewIdList",reviewIdList);
        return query.getResultList();
    }

    @Override
    public int getReviewsCount(ReviewFilter reviewFilter) {
        final Query queryCount = queryGetReviews("SELECT * ",reviewFilter,false,0,0);
        return queryCount.getResultList().size();
    }

    @Override
    public void newReview(Integer lessonId,Integer userId,String message,int rating){
        Lesson lesson = em.getReference(Lesson.class, lessonId);
        User author = em.getReference(User.class,userId);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR,1);
        java.sql.Date date = new Date(calendar.getTime().getTime());
        final Review review = new Review(null, lesson, author,date,message,rating);
        em.persist(review);
    }
    @Override
    public void changeReview(Review review) {
        em.persist(review);
    }

    @Override
    public void deleteReview(Integer reviewId){
        em.remove(em.getReference(Review.class,reviewId));
    }

    @Override
    public Integer getAverageReviews(ReviewFilter reviewFilter) {
        final Query nativeQuery = queryGetReviews("SELECT AVG(rating) ",reviewFilter,false,0,0);
        return (Integer)nativeQuery.getSingleResult();
    }

    private Query queryGetReviews(String preString, final ReviewFilter reviewFilter,boolean orderAndPage,int pageNumber, int size) {
        StringBuilder query = new StringBuilder(preString);
        query.append(" FROM full_review WHERE 1=1 ");
        Map<String, Object> params = new HashMap<>();

        if (reviewFilter != null) {
            if (reviewFilter.getReviewId() != -1) {
                query.append("AND review_id = :reviewId ");
                params.put("reviewId", reviewFilter.getReviewId());
            }
            if (reviewFilter.getLessonId() != -1) {
                query.append("AND lesson_id = :lessonId ");
                params.put("lessonId", reviewFilter.getLessonId());
            }
            if(reviewFilter.getSubjectId() != -1){
                query.append("AND subject_id = :subjectId ");
                params.put("subjectId",reviewFilter.getSubjectId());
            }
            if (reviewFilter.getProfessorId() != -1) {
                query.append("AND professor_id = :professorId ");
                params.put("professorId", reviewFilter.getProfessorId());
            }
            if (reviewFilter.getStudentId() != -1) {
                query.append("AND student_id = :studentId ");
                params.put("studentId", reviewFilter.getStudentId());
            }
        }

        if(orderAndPage){
            int skip = 0;
            if(pageNumber != 1){
                skip = (pageNumber -1) * size;
            }
            query.append("LIMIT :size OFFSET :skip ");
            params.put("size",size);
            params.put("skip",skip);
        }

        final Query nativeQuery = em.createNativeQuery(query.toString());
        for(Map.Entry<String,Object> entry : params.entrySet()) {
            nativeQuery.setParameter(entry.getKey(),entry.getValue());
        }
        return nativeQuery;

    }
}
