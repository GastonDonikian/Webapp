package ar.edu.itba.paw.persistence;


import ar.edu.itba.paw.persistence.config.TestConfig;
import ar.edu.itba.paw.persistence.jpa.ContractDaoJpa;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

@RunWith(SpringJUnit4ClassRunner.class)
@Rollback
@Sql(scripts = "classpath:/contract-test-populator.sql")
@ContextConfiguration(classes = TestConfig.class)
public class ContractDaoJpaTest {


//    @Autowired
//    private DataSource ds;
//
//    private JdbcTemplate jdbcTemplate;


//
//    @Before
//    public void setUp() {
//        jdbcTemplate = new JdbcTemplate(ds);
//    }

    @Autowired
    private ContractDaoJpa contractDao;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void testDoesUserTeachTrue(){
        Integer professorId = 1;
        Integer subjectId = 3;

        boolean expectedTrue = contractDao.doesUserTeach(professorId,subjectId);

        Assert.assertTrue(expectedTrue);
    }

    @Test
    public void testDoesUserTeachNoTeacher(){
        Integer professorId = 100;
        Integer subjectId = 3;

        boolean expectedTrue = contractDao.doesUserTeach(professorId,subjectId);

        Assert.assertFalse(expectedTrue);
    }

    @Test
    public void testDoesUserTeachNoSubject(){
        Integer professorId = 1;
        Integer subjectId = 300;

        boolean expectedTrue = contractDao.doesUserTeach(professorId,subjectId);

        Assert.assertFalse(expectedTrue);
    }

    @Test
    public void testDoesUserTeachNoSubjectNoTeacher(){
        Integer professorId = 300;
        Integer subjectId = 300;

        boolean expectedTrue = contractDao.doesUserTeach(professorId,subjectId);
        Assert.assertFalse(expectedTrue);
    }


}


