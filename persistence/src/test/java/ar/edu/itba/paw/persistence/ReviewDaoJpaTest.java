package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.models.Review;
import ar.edu.itba.paw.models.ReviewFilter;
import ar.edu.itba.paw.persistence.config.TestConfig;
import ar.edu.itba.paw.persistence.jpa.ReviewDaoJpa;
import org.hibernate.jpa.criteria.expression.function.AggregationFunction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@Rollback
@Sql(scripts = "classpath:/review-test-populator.sql")
@ContextConfiguration(classes = TestConfig.class)
public class ReviewDaoJpaTest {
    private static final int PAGE_NUMBER = 1;
    private static final int MAX_SIZE = 100;

    @Autowired
    private DataSource ds;

    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ReviewDaoJpa reviewDao;

    @Before
    public void setUp() {
        jdbcTemplate = new JdbcTemplate(ds);
    }

    @Test
    public void testGetReviewsByLesson(){
        //Precondiciones
        Integer lessonId = 1;
        int reviewCount = 1;
        ReviewFilter filter = new ReviewFilter.Builder().lesson(lessonId).build();

        //Stress
        List<Review> reviews = reviewDao.getReviews(filter,1, MAX_SIZE);


        //Assert
        Assert.assertEquals(reviewCount,reviews.size());
    }

    @Test
    public void testGetReviewsByProfessor(){
        //Precondiciones
        Integer professorId = 1;
        int reviewCount = 4;
        ReviewFilter filter = new ReviewFilter.Builder().professor(professorId).build();

        //Stress
        List<Review> reviews = reviewDao.getReviews(filter,PAGE_NUMBER, MAX_SIZE);

        //Assert
        Assert.assertEquals(reviewCount,reviews.size());
    }


    @Test
    public void testGetReviewsByStudent(){
        //Precondiciones
        Integer studentId = 1;
        int reviewCount = 4;
        ReviewFilter filter = new ReviewFilter.Builder().student(studentId).build();

        //Stress
        List<Review> reviews = reviewDao.getReviews(filter,PAGE_NUMBER,MAX_SIZE);

        //Assert
        Assert.assertEquals(reviewCount,reviews.size());
    }

    @Test
    public void testGetReviewsByLessonAverage(){
        //Precondiciones
        Integer lessonId = 1;
        int reviewAverage = 4;
        ReviewFilter filter = new ReviewFilter.Builder().lesson(lessonId).build();

        //Stress
        int reviews = reviewDao.getAverageReviews(filter);

        //Assert
        Assert.assertEquals(reviewAverage,reviews);
    }

    @Test
    public void testGetReviewsByProfessorAverage(){
        //Precondiciones
        Integer professorId = 1;
        int reviewAverage = 4;
        ReviewFilter filter = new ReviewFilter.Builder().professor(professorId).build();

        //Stress
        int reviews = reviewDao.getAverageReviews(filter);

        //Assert
        Assert.assertEquals(reviewAverage,reviews);
    }


    @Test
    public void testGetReviewsByStudentAverage(){
        //Precondiciones
        Integer studentId = 1;
        int reviewAverage = 4;
        ReviewFilter filter = new ReviewFilter.Builder().student(studentId).build();

        //Stress
        int reviews = reviewDao.getAverageReviews(filter);

        //Assert
        Assert.assertEquals(reviewAverage,reviews);
    }

}
