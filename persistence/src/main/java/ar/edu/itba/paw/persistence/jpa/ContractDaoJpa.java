package ar.edu.itba.paw.persistence.jpa;

import ar.edu.itba.paw.interfaces.persistence.ContractDao;
import ar.edu.itba.paw.models.*;
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
public class ContractDaoJpa implements ContractDao {
    @PersistenceContext
    private EntityManager em;


    private Optional<Contract> findById(Integer id) {
        return Optional.ofNullable(em.find(Contract.class, id));
    }


    @Override
    public List<Contract> getContracts(ContractFilter filter, int pageNumber, int size) {
        Query nativeQuery = queryGetContracts("SELECT contract_id", filter, true, pageNumber, size);

        final List<Integer> contractIdList = ((List<Integer>) nativeQuery.getResultList());

        if (contractIdList.isEmpty())
            return Collections.emptyList();
        final TypedQuery<Contract> query = em.createQuery("from Contract where id IN :contractIdList", Contract.class);
        query.setParameter("contractIdList", contractIdList);
        return query.getResultList();
    }

    @Override
    public int getContractsCount(ContractFilter filter) {
        Query nativeQuery = queryGetContracts("SELECT contract_id", filter, false, 0, 0);
        return nativeQuery.getResultList().size();
    }

    private Query queryGetContracts(String preString, final ContractFilter contractFilter, boolean orderAndPage, int pageNumber, int size) {
        StringBuilder query = new StringBuilder(preString);
        query.append(" FROM full_contract WHERE 1=1 ");
        Map<String, Object> params = new HashMap<>();

        if (contractFilter != null) {
            query.append("AND status = :status ");
            params.put("status", contractFilter.getStatus().name());

            if (contractFilter.getLocal()) {
                query.append("AND local = :local ");
                params.put("local", contractFilter.getLocal());
            }

            if (!Objects.equals(contractFilter.getSearch(), "")) {
                String words = contractFilter.getSearch();
                StringTokenizer defaultTokenizer = new StringTokenizer(words);
                query.append("AND ( 1 != 1 ");
                Integer i = 0;
                while (defaultTokenizer.hasMoreTokens()) {

                    String word = defaultTokenizer.nextToken();

                    if (word != null && !word.isEmpty()) {
                        String token = "wordSearch" + i.toString();
                        i++;
                        query.append(" OR (lower(unaccent(subject_name))) LIKE :");
                        query.append(token);
                        query.append(" OR (lower(unaccent(category))) LIKE :");
                        query.append(token);
                        query.append(" OR (lower(unaccent(level))) LIKE :");
                        query.append(token);
                        query.append(" OR (lower(unaccent(professor_name))) LIKE :");
                        query.append(token);
                        query.append(" OR (lower(unaccent(professor_surname))) LIKE :");
                        query.append(token);
                        query.append(" OR (lower(unaccent(professor_location))) LIKE :");
                        query.append(token);
                        params.put(token, "%" + word + "%");
                    }
                }
                query.append(") ");
            }

            if (!(contractFilter.getLevel().isEmpty() || contractFilter.getLevel().contains(Subject.Levels.All))) {
                query.append(" AND (");
                for (Subject.Levels level : contractFilter.getLevel()) {
                    query.append(" level = :" + level.toString() + "  OR ");
                    params.put(level.toString(), level.name());
                }
                query.append(" 1!= 1) ");
            }

            if (!(contractFilter.getCategory().isEmpty() || contractFilter.getCategory().contains(Subject.Categories.All))) {
                query.append(" AND (");
                for (Subject.Categories category : contractFilter.getCategory()) {
                    query.append(" category = :" + category.toString() + " OR ");
                    params.put(category.toString(), category.name());
                }
                query.append("1 != 1)");
            }


            if (contractFilter.getRemote()) {
                query.append("AND remote = :remote ");
                params.put("remote", contractFilter.getRemote());
            }
            if (contractFilter.getProfessorId() != -1) {
                query.append("AND professor_id = :professorId ");
                params.put("professorId", contractFilter.getProfessorId());
            }
            if (contractFilter.getSubjectId() != -1) {
                query.append("AND subject_id = :subjectId ");
                params.put("subjectId", contractFilter.getSubjectId());
            }
            if (!(contractFilter.getLocalidades().isEmpty() || contractFilter.getLocalidades().contains(Professor.Location.All))) {
                query.append(" AND (");
                for (Professor.Location localidad : contractFilter.getLocalidades()) {
                    query.append(" professor_location = :").append(localidad.toString()).append(" OR ");
                    params.put(localidad.toString(), localidad.name());
                }
                query.append("1 != 1)");
            }


            if (orderAndPage) {
                int skip = 0;
                if (pageNumber != 1) {
                    skip = (pageNumber - 1) * size;
                }
                if (contractFilter.getOrderBy() == ContractFilter.OrderBy.rating)
                    query.append("ORDER BY contract_rating DESC");
                else
                    query.append("ORDER BY price");
                query.append(" LIMIT :size OFFSET :skip ");
                params.put("size", size);
                params.put("skip", skip);
            }
        }
        final Query nativeQuery = em.createNativeQuery(query.toString());
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            nativeQuery.setParameter(entry.getKey(), entry.getValue());
        }
        return nativeQuery;
    }

    @Override
    public void changeContractStatus(Integer contractId, Contract.ContractStatus status) {
        Optional<Contract> optionalContract = findById(contractId);
        if (optionalContract.isPresent()) {
            optionalContract.get().setStatus(status);
            em.persist(optionalContract.get());
        }
    }

    @Override
    public void newContract(Integer professorId, Integer subjectId, String contractDescription, boolean local, boolean remote, Float price) {
        final Professor professor = em.getReference(Professor.class, professorId);
        final Subject subject = em.getReference(Subject.class, subjectId);
        final Contract contract = new Contract(null, subject, professor, contractDescription, local, remote, null, price, Contract.ContractStatus.ACTIVE);
        em.persist(contract);
    }

    @Override
    public void dropContract(Integer professorId, Integer subjectId) {
        Optional<Contract> optionalContract = getUserContract(professorId, subjectId);
        optionalContract.ifPresent(contract -> em.remove(contract));
    }

    @Override
    public boolean doesUserTeach(Integer professorId, Integer subjectId) {
        return getUserContract(professorId, subjectId).isPresent();
    }

    @Override
    public List<Professor.Location> getContractsLocations() {
        final Query nativeQuery = em.createNativeQuery("SELECT DISTINCT professor_location FROM full_contract");

        final List<String> locationStringList = nativeQuery.getResultList();

        if (locationStringList.isEmpty())
            return Collections.emptyList();
        List<Professor.Location> locationsList = new ArrayList<>();
        locationsList.add(Professor.Location.All);
        for (String location : locationStringList) {
            locationsList.add(Professor.Location.valueOf(location));
        }
        return locationsList;
    }

    @Override
    public List<Subject.Categories> getContractsCategories() {
        final Query nativeQuery = em.createNativeQuery("SELECT DISTINCT category FROM full_contract");
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
    public List<Subject.Levels> getContractsLevels() {
        final Query nativeQuery = em.createNativeQuery("SELECT DISTINCT level FROM full_contract");
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

    private Optional<Contract> getUserContract(Integer professorId, Integer subjectId) {
        final Professor professor = em.getReference(Professor.class, professorId);
        final Subject subject = em.getReference(Subject.class, subjectId);

        final TypedQuery<Contract> query = em.createQuery("from Contract where professor = :professor AND subject = :subject",
                Contract.class);
        query.setParameter("professor", professor);
        query.setParameter("subject", subject);
        return query.getResultList().stream().findFirst();
    }
}
