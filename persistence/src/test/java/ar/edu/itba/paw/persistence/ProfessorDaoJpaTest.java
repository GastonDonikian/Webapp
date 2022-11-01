package ar.edu.itba.paw.persistence;


import ar.edu.itba.paw.models.Professor;
import ar.edu.itba.paw.models.Subject;
import ar.edu.itba.paw.persistence.config.TestConfig;
import ar.edu.itba.paw.persistence.jpa.ProfessorDaoJpa;
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
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@Rollback
@Sql(scripts = "classpath:/professor-test-populator.sql")
@ContextConfiguration(classes = TestConfig.class)
@Transactional
public class ProfessorDaoJpaTest {


//    ESTOS TESTS FALLAN POR UN TEMA DE HSQLDB;
    @Autowired
    private ProfessorDaoJpa professorDao;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private DataSource ds;

    private JdbcTemplate jdbcTemplate;

    @Before
    public void setUp(){
        jdbcTemplate = new JdbcTemplate(ds);
    }

    @Test
    public void testFindByIdTrue(){
        //Precondiciones
        final int professorId = 1;
        //Ejercitar
        Optional<Professor> professor = professorDao.findById(professorId);

        //Postcondiciones
        Assert.assertNotNull(professor);
        Assert.assertTrue(professor.isPresent());
        Assert.assertEquals(Optional.of(professorId), Optional.ofNullable(professor.get().getUserId()));
        Assert.assertEquals("Gaston",professor.get().getName());
        Assert.assertEquals("gastondonikian@gmail.com",professor.get().getEmail());
    }

    @Test
    public void testFindByIdFalse(){
        //Precondiciones
        final int professorId= 1025;
        //Ejercitar
        Optional<Professor> professor = professorDao.findById(professorId);

        //Postcondiciones
        Assert.assertNotNull(professor);
        Assert.assertFalse(professor.isPresent());
    }

}
