package ar.edu.itba.paw.services;


import ar.edu.itba.paw.interfaces.persistence.ProfessorDao;
import ar.edu.itba.paw.models.Professor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.eq;

@RunWith(MockitoJUnitRunner.class)
public class ProfessorServiceImplTest {

    private static final Integer subjectId = 1;

    private static final Integer userId = 1234;
    private static final String name = "Gaston";
    private static final String surname = "Donikian";
    private static final String email = "gastondonikian@gmail.com";
    private static final String password = "1234";
    private static final String phoneNumber = "54165353";
    private static final String studies = "ITBA";
    private static final String description = "Hola como estas";

    @InjectMocks
    private ProfessorServiceImpl professorService = new ProfessorServiceImpl();

    @Mock
    private ProfessorDao mockDao;

    @Test
    public void testFindById(){
        final Integer userId = 1234;
         final String name = "Gaston";
        final String surname = "Donikian";
        final String email = "gastondonikian@gmail.com";
        final String password = "1234";
        final String schedule = "From 9 to 5";
        final String phoneNumber = "54165353";
        final String studies = "ITBA";
        final String description = "Hola como estas";
        final Professor.Location location = Professor.Location.Belgrano;
        Integer rating = 2;

        //Setup
        Mockito.when(mockDao.findById(Mockito.eq(userId)))
                .thenReturn(Optional.of(new Professor(userId, name, surname, email, password,schedule, phoneNumber, studies, description,location,rating)));

        //Ejercito la class under test
        Optional<Professor> professorOptional = professorService.findById(userId);

        //Asserts
        Assert.assertNotNull(professorOptional);
        Assert.assertTrue(professorOptional.isPresent());
        Assert.assertEquals(professorOptional.get().getUserId(),userId);
    }
}
