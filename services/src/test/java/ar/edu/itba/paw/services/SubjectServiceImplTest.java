package ar.edu.itba.paw.services;


import ar.edu.itba.paw.interfaces.persistence.SubjectDao;
import ar.edu.itba.paw.models.Subject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class SubjectServiceImplTest {

    private static final String name = "Algebra Lineal";
    private static final Integer id = 5416535;
    private static final Subject.Levels level = Subject.Levels.College;
    private static final Subject.Categories category = Subject.Categories.Science;

    @InjectMocks
    private SubjectServiceImpl subjectService = new SubjectServiceImpl();

    @Mock
    private SubjectDao mockDao;

    @Test
    public void testFindById() {
        //Setup
        Mockito.when(mockDao.findById(Mockito.eq(id)))
                .thenReturn(Optional.of(new Subject(id, name, level, category)));

        //Ejercito la class under test
        Optional<Subject> subjectOptional = subjectService.findById(id);

        //Asserts
        Assert.assertNotNull(subjectOptional);
        Assert.assertTrue(subjectOptional.isPresent());
        Assert.assertEquals(subjectOptional.get().getId(),id);
        Assert.assertEquals(subjectOptional.get().getName(),name);
        Assert.assertEquals(subjectOptional.get().getLevel(),level);
        Assert.assertEquals(subjectOptional.get().getCategory(),category);
    }

//    @Test
//    public void testGetSubjects(){
//        //Setup
//    }
}
