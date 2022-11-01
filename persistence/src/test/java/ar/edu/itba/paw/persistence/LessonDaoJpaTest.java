package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.persistence.LessonDao;
import ar.edu.itba.paw.models.Lesson;
import ar.edu.itba.paw.models.LessonFilter;
import ar.edu.itba.paw.persistence.config.TestConfig;
import ar.edu.itba.paw.persistence.jpa.LessonDaoJpa;
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
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@Rollback
@Sql(scripts = "classpath:/lesson-test-populator.sql")
@ContextConfiguration(classes = TestConfig.class)
@Transactional
public class LessonDaoJpaTest {
    private static final int TOTAL_LESSON_COUNT = 16;
    private static final int PAGE_NUMBER = 1;
    private static final int MAX_SIZE = 100;

    @Autowired
    private DataSource ds;

    private JdbcTemplate jdbcTemplate;

    @Autowired
    private LessonDaoJpa lessonDao;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void testDropLessonTrue() {
        //Precondiciones
        //Ejercitar
        lessonDao.dropLesson(1);
        //Assert
        Assert.assertNull( entityManager.find(Lesson.class,1));
    }

    @Test
    public void testDropLessonFalse() {
        //Precondiciones
        //Ejercitar
        lessonDao.dropLesson(TOTAL_LESSON_COUNT + 1);
        //Assert
        Assert.assertNull(entityManager.find(Lesson.class,TOTAL_LESSON_COUNT +1));
    }

    @Test
    public void testGetLessonsByStatus(){
        //Precondiciones
        int pendingApprovalCount = 4;
        LessonFilter filter = new LessonFilter.Builder().status(Lesson.LessonStatus.PENDING_APPROVAL).build();

        //Test
        List<Lesson> lessons = lessonDao.getLessons(filter,PAGE_NUMBER,MAX_SIZE);

        //Assert
        Assert.assertEquals(pendingApprovalCount,lessons.size());
    }

    @Test
    public void testGetLessonsBySubject(){
        //Precondiciones
        int pendingApprovalCount = 4;
        LessonFilter filter = new LessonFilter.Builder().subject(1).build();

        //Test
        List<Lesson> lessons = lessonDao.getLessons(filter,PAGE_NUMBER,MAX_SIZE);

        //Assert
        Assert.assertEquals(pendingApprovalCount,lessons.size());
    }

    @Test
    public void testGetLessonsByProfessor(){
        //Precondiciones
        int pendingApprovalCount = 4;
        LessonFilter filter = new LessonFilter.Builder().professor(1).build();

        //Test
        List<Lesson> lessons = lessonDao.getLessons(filter,PAGE_NUMBER,MAX_SIZE);

        //Assert
        Assert.assertEquals(pendingApprovalCount,lessons.size());
    }

    @Test
    public void testGetLessonsByStudent(){
        //Precondiciones
        int pendingApprovalCount = 8;
        LessonFilter filter = new LessonFilter.Builder().student(1).build();

        //Test
        List<Lesson> lessons = lessonDao.getLessons(filter,PAGE_NUMBER,MAX_SIZE);

        //Assert
        Assert.assertEquals(pendingApprovalCount,lessons.size());
    }
}
