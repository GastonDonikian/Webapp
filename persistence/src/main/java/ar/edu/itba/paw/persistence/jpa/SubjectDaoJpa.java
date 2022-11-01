package ar.edu.itba.paw.persistence.jpa;

import ar.edu.itba.paw.interfaces.persistence.SubjectDao;


import ar.edu.itba.paw.models.Subject;
import ar.edu.itba.paw.models.SubjectFilter;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.*;
import java.util.stream.Collectors;

@Primary
@Repository
public class SubjectDaoJpa implements SubjectDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<Subject> findById(Integer id) {
        return Optional.ofNullable(em.find(Subject.class, id));
    }

    @Override
    public List<Subject> getSubjects(SubjectFilter subjectFilter, int pageNumber, int size) {
        final Query nativeQuery = queryGetSubjects("SELECT subject_id ", subjectFilter, true, pageNumber, size);

        final List<Integer> subjectIdList = ((List<Integer>) nativeQuery.getResultList());

        if (subjectIdList.isEmpty())
            return Collections.emptyList();
        final TypedQuery<Subject> query = em.createQuery("from Subject where subjectId IN :subjectIdList", Subject.class);
        query.setParameter("subjectIdList", subjectIdList);
        return query.getResultList();
    }

    @Override
    public List<Subject> getUntaughtSubjects(Integer professorId, SubjectFilter subjectFilter, int pageNumber, int size) {
        StringBuilder stringQuery = new StringBuilder("SELECT subject_id FROM subjects WHERE subject_id NOT IN (SELECT subject_id FROM full_contract WHERE :professorId = professor_id)");
        Map<String, Object> params = new HashMap<>();
        if (subjectFilter != null) {


            if (!Objects.equals(subjectFilter.getSearch(), "")) {
                stringQuery.append(" AND ( (lower(unaccent(name) ) ) LIKE :textSearch )");
                String aux = "%" + subjectFilter.getSearch() + "%";
                params.put("textSearch", aux);
            }

            if (!(subjectFilter.getLevel().isEmpty() || subjectFilter.getLevel().contains(Subject.Levels.All))) {
                stringQuery.append(" AND (");
                for (Subject.Levels level : subjectFilter.getLevel()) {
                    stringQuery.append(" level = :" + level.toString() + "  OR ");
                    params.put(level.toString(), level.name());
                }
                stringQuery.append(" 1!= 1) ");
            }

            if (!(subjectFilter.getCategory().isEmpty() || subjectFilter.getCategory().contains(Subject.Categories.All))) {
                stringQuery.append(" AND (");
                for (Subject.Categories category : subjectFilter.getCategory()) {
                    stringQuery.append(" category = :" + category.toString() + " OR ");
                    params.put(category.toString(), category.name());
                }
                stringQuery.append("1 != 1)");

            }
        }
        stringQuery.append("LIMIT :size OFFSET :skip ");

        int skip = 0;
        if (pageNumber != 1) {
            skip = (pageNumber - 1) * size;
        }
        final Query nativeQuery = em.createNativeQuery(stringQuery.toString());
        nativeQuery.setParameter("professorId", professorId);
        nativeQuery.setParameter("size", size);
        nativeQuery.setParameter("skip", skip);
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            nativeQuery.setParameter(entry.getKey(), entry.getValue());
        }
        final List<Integer> subjectIdList = ((List<Integer>) nativeQuery.getResultList());

        if (subjectIdList.isEmpty())
            return Collections.emptyList();
        final TypedQuery<Subject> query = em.createQuery("from Subject where subjectId IN :subjectIdList", Subject.class);
        query.setParameter("subjectIdList", subjectIdList);
        return query.getResultList();
    }

    @Override
    public int getUntaughtSubjectsCount(Integer professorId,SubjectFilter subjectFilter) {
        StringBuilder stringQuery = new StringBuilder("SELECT subject_id FROM subjects WHERE subject_id NOT IN (SELECT subject_id FROM full_contract WHERE :professorId = professor_id)");
        Map<String, Object> params = new HashMap<>();
        if (subjectFilter != null) {


            if (!Objects.equals(subjectFilter.getSearch(), "")) {
                stringQuery.append(" AND ( (lower(unaccent(name) ) ) LIKE :textSearch )");
                String aux = "%" + subjectFilter.getSearch() + "%";
                params.put("textSearch", aux);
            }

            if (!(subjectFilter.getLevel().isEmpty() || subjectFilter.getLevel().contains(Subject.Levels.All))) {
                stringQuery.append(" AND (");
                for (Subject.Levels level : subjectFilter.getLevel()) {
                    stringQuery.append(" level = :" + level.toString() + "  OR ");
                    params.put(level.toString(), level.name());
                }
                stringQuery.append(" 1!= 1) ");
            }

            if (!(subjectFilter.getCategory().isEmpty() || subjectFilter.getCategory().contains(Subject.Categories.All))) {
                stringQuery.append(" AND (");
                for (Subject.Categories category : subjectFilter.getCategory()) {
                    stringQuery.append(" category = :" + category.toString() + " OR ");
                    params.put(category.toString(), category.name());
                }
                stringQuery.append("1 != 1)");

            }
        }
        final Query nativeQuery = em.createNativeQuery(stringQuery.toString());
        nativeQuery.setParameter("professorId", professorId);
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            nativeQuery.setParameter(entry.getKey(), entry.getValue());
        }
        return nativeQuery.getResultList().size();
    }

    private Query queryGetSubjects(String preString, final SubjectFilter subjectFilter, boolean orderAndPage, int pageNumber, int size) {

        StringBuilder query = new StringBuilder(preString);
        query.append(" FROM subjects WHERE 1=1 ");
        Map<String, Object> params = new HashMap<>();

        if (subjectFilter != null) {
            if (!Objects.equals(subjectFilter.getSearch(), "")) {
                query.append(" AND ( (lower(unaccent(name) ) ) LIKE :textSearch )");
                String aux = "%" + subjectFilter.getSearch() + "%";
                params.put("textSearch", aux);
            }

            if (!(subjectFilter.getLevel().isEmpty() || subjectFilter.getLevel().contains(Subject.Levels.All))) {
                query.append(" AND (");
                for (Subject.Levels level : subjectFilter.getLevel()) {
                    query.append(" level = :" + level.toString() + "  OR ");
                    params.put(level.toString(), level.name());
                }
                query.append(" 1!= 1) ");
            }

            if (!(subjectFilter.getCategory().isEmpty() || subjectFilter.getCategory().contains(Subject.Categories.All))) {
                query.append(" AND (");
                for (Subject.Categories category : subjectFilter.getCategory()) {
                    query.append(" category = :" + category.toString() + " OR ");
                    params.put(category.toString(), category.name());
                }
                query.append("1 != 1)");

            }
        }
        if (orderAndPage) {
            int skip = 0;
            if (pageNumber != 1) {
                skip = (pageNumber - 1) * size;
            }
            query.append(" LIMIT :size OFFSET :skip ");
            params.put("size", size);
            params.put("skip", skip);
        }
        final Query nativeQuery = em.createNativeQuery(query.toString());
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            nativeQuery.setParameter(entry.getKey(), entry.getValue());
        }
        return nativeQuery;
    }

    @Override
    public int getSubjectsCount(SubjectFilter subjectFilter) {
        final Query queryCount = queryGetSubjects("SELECT subject_id ", subjectFilter, false, 0, 0);
        return queryCount.getResultList().size();
    }

    @Override
    public List<Subject.Categories> getSubjectsCategories(){
        final Query nativeQuery = em.createNativeQuery("SELECT DISTINCT category FROM subjects");
        final List<String> categoriesStringList = nativeQuery.getResultList();

        if (categoriesStringList.isEmpty())
            return Collections.emptyList();
        List<Subject.Categories> categoriesList = new ArrayList<>();
        categoriesList.add(Subject.Categories.All);
        for (String category : categoriesStringList) {
            categoriesList.add(Subject.Categories.valueOf(category));
        }
        return categoriesList;
    }
    
    @Override
    public List<Subject.Levels> getSubjectsLevels(){
        final Query nativeQuery = em.createNativeQuery("SELECT DISTINCT level FROM subjects");
        final List<String> levelsStringList = nativeQuery.getResultList();

        if (levelsStringList.isEmpty())
            return Collections.emptyList();
        List<Subject.Levels> levelsList = new ArrayList<>();
        levelsList.add(Subject.Levels.All);
        for (String level : levelsStringList) {
            levelsList.add(Subject.Levels.valueOf(level));
        }
        return levelsList;
    }

}
