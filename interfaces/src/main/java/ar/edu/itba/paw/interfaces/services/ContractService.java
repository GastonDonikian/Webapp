package ar.edu.itba.paw.interfaces.services;

import ar.edu.itba.paw.models.*;

import java.util.List;

public interface ContractService {
    List<Contract> getContracts(final ContractFilter filter,int pageNumber);
    List<Professor.Location> getContractsLocations();
    List<Subject.Categories> getContractsCategories();
    List<Subject.Levels> getContractsLevels();
    int getContractsCount(ContractFilter contractFilter);
    int totalContracts(ContractFilter contractFilter);
    void changeContractStatus(Integer contractId, Contract.ContractStatus status);
    void newContract(Integer professorId, Integer subjectId, String contractDescription, boolean local, boolean remote, Float price);
    void dropContract(Integer professorId,Integer subjectId);
    boolean doesUserTeach(Integer professorId, Integer subjectId);
}
