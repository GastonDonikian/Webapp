package ar.edu.itba.paw.interfaces.persistence;

import ar.edu.itba.paw.models.Contract;
import ar.edu.itba.paw.models.ContractFilter;
import ar.edu.itba.paw.models.Professor;
import ar.edu.itba.paw.models.Subject;

import java.util.List;
import java.util.Optional;

public interface ContractDao {


    List<Contract> getContracts(final ContractFilter filter,int pageNumber,int size);
    int getContractsCount(final ContractFilter filter);
    void changeContractStatus(Integer contractId, Contract.ContractStatus status);
    void newContract(Integer professorId, Integer subjectId, String contractDescription, boolean local, boolean remote, Float price);
    void dropContract(Integer professorId,Integer subjectId);
    boolean doesUserTeach(Integer professorId, Integer subjectId);
    List<Professor.Location> getContractsLocations();
    List<Subject.Categories> getContractsCategories();
    List<Subject.Levels> getContractsLevels();
}
