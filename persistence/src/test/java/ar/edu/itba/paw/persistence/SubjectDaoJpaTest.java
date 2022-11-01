package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.models.Subject;
import ar.edu.itba.paw.models.SubjectFilter;
import ar.edu.itba.paw.persistence.config.TestConfig;
import ar.edu.itba.paw.persistence.jpa.SubjectDaoJpa;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.jdbc.JdbcTestUtils;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@RunWith(SpringJUnit4ClassRunner.class)
@Rollback
@Sql(scripts = "classpath:/subject-test-populator.sql")
@ContextConfiguration(classes = TestConfig.class)
public class SubjectDaoJpaTest {


    private static final Integer ID = 1;
    private static final int SUBJECT_COUNT = 14;
    private static final int SUBJECTS_BY_SCIENCE = 6;
    private static final int SUBJECTS_BY_ARTS = 2;
    private static final int SUBJECTS_BY_LANGUAGE = 3;



    @Autowired
    private SubjectDaoJpa subjectDao;

    @Autowired
    private DataSource ds;

    private JdbcTemplate jdbcTemplate;

    @Before
    public void setUp(){
        jdbcTemplate = new JdbcTemplate(ds);
    }



    @Test
    public void testGetAllSubjectsWhitoutBuilder(){
        //Precondiciones

        //Ejercitar
        List<Subject> subject = subjectDao.getSubjects(null,1,100);

        //Assert
        Assert.assertNotNull(subject);
        Assert.assertEquals(SUBJECT_COUNT,subject.size());
    }

    @Test
    public void testGetSubjectsByCategory(){
        //Precondiciones
        List<Subject.Categories> categories = new ArrayList<>();
        categories.add(Subject.Categories.Science);

        //Ejercitar
        List<Subject> subject = subjectDao.getSubjects(new SubjectFilter.Builder().categories(categories).build(),1,100);

        //Assert
        Assert.assertEquals(SUBJECTS_BY_SCIENCE,subject.size());
    }

    @Test
    public void testGetSubjectsByCategories(){
        //Precondiciones
        List<Subject.Categories> categories = new ArrayList<>();
        categories.add(Subject.Categories.Science);
        categories.add(Subject.Categories.Language);
        categories.add(Subject.Categories.Arts);

        //Ejercitar
        List<Subject> subject = subjectDao.getSubjects(new SubjectFilter.Builder().categories(categories).build(),1,100);

        //Assert
        Assert.assertEquals(SUBJECTS_BY_SCIENCE + SUBJECTS_BY_LANGUAGE + SUBJECTS_BY_ARTS,subject.size());
    }

    @Test
    public void testGetSubjectsByCategoriesAll(){
        //Precondiciones
        List<Subject.Categories> categories = new ArrayList<>();
        categories.add(Subject.Categories.All);

        //Ejercitar
        List<Subject> subject = subjectDao.getSubjects(new SubjectFilter.Builder().categories(categories).build(),1,100);

        //Assert
        Assert.assertEquals(SUBJECT_COUNT,subject.size());
    }

    @Test
    public void testGetAllSubjectsWithBuilder(){
        //

        //Ejercitar
        List<Subject> subject = subjectDao.getSubjects(new SubjectFilter.Builder().build(),1,100);

        //Assert
        Assert.assertNotNull(subject);
        Assert.assertEquals(SUBJECT_COUNT,subject.size());
    }

    @Test
    public void testGetSubjectsCount(){
        //Precondiciones

        //Ejercitar
        int subjectCount = subjectDao.getSubjectsCount(null);

        //Assert
        Assert.assertEquals(SUBJECT_COUNT,subjectCount);
    }

    @Test
    public void testFindByIdTrue(){
        //Precondiciones

        //Ejercitar
        Optional<Subject> subject = subjectDao.findById(ID);

        //Postcondiciones
        Assert.assertNotNull(subject);
        Assert.assertTrue(subject.isPresent());
    }

    @Test
    public void testFindByIdFalse(){
        //Precondiciones

        //Ejercitar
        //Si falla es porque tenes mas de 10 elementos chapulin
        Optional<Subject> subject = subjectDao.findById(ID + 5000);

        //Postcondiciones
        Assert.assertNotNull(subject);
        Assert.assertFalse(subject.isPresent());
    }




}
