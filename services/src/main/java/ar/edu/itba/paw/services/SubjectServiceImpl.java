package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.persistence.SubjectDao;
import ar.edu.itba.paw.interfaces.services.ContractService;
import ar.edu.itba.paw.interfaces.services.SubjectService;
import ar.edu.itba.paw.models.Contract;
import ar.edu.itba.paw.models.ContractFilter;
import ar.edu.itba.paw.models.Subject;
import ar.edu.itba.paw.models.SubjectFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.Normalizer;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.text.Normalizer.normalize;

@Service
public class SubjectServiceImpl implements SubjectService {
    final int PAGE_SIZE = 10;

    @Autowired
    private SubjectDao subjectDao;

    @Autowired
    private ContractService contractService;

    @Override
    @Transactional(readOnly = true)
    public Optional<Subject> findById(Integer id) {
        return subjectDao.findById(id);
    }

    //@Override
    //public List<Subject> findByName(String name){
     //   return subjectDao.findByName(normalize(name, Normalizer.Form.NFD).toLowerCase());
   // }

    @Override
    @Transactional(readOnly = true)
    public List<Subject> getSubjects(SubjectFilter subjectFilter,int pageNumber) {
        return subjectDao.getSubjects(subjectFilter,pageNumber,PAGE_SIZE);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Subject> getUntaughtSubjects(Integer professorId,SubjectFilter subjectFilter, int pageNumber){
        return subjectDao.getUntaughtSubjects(professorId,subjectFilter,pageNumber,PAGE_SIZE);
    }

    @Override
    @Transactional(readOnly = true)
    public int getUntaughtSubjectsCount(Integer professorId,SubjectFilter subjectFilter){
        return (int) Math.ceil((double) subjectDao.getUntaughtSubjectsCount(professorId,subjectFilter) / PAGE_SIZE);
    }



    @Override
    @Transactional(readOnly = true)
    public int getSubjectsCount(SubjectFilter subjectFilter) {
        return (int) Math.ceil((double) subjectDao.getSubjectsCount(subjectFilter) / PAGE_SIZE);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Subject.Categories> getSubjectCategories(){
        return subjectDao.getSubjectsCategories();
    }
    @Override
    @Transactional(readOnly = true)
    public List<Subject.Levels> getSubjectLevels(){
        return subjectDao.getSubjectsLevels();
    }

}
