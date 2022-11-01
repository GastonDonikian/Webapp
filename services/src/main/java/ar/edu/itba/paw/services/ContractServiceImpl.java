package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.persistence.ContractDao;
import ar.edu.itba.paw.interfaces.services.ContractService;
import ar.edu.itba.paw.models.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Table;
import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {
    final int PAGE_SIZE = 6;


    @Autowired
    private ContractDao contractDao;

    @Override
    @Transactional(readOnly = true)
    public List<Contract> getContracts(final ContractFilter filter,int pageNumber){
        if(pageNumber < 0)
            return contractDao.getContracts(filter,1,100);
        return contractDao.getContracts(filter,pageNumber,PAGE_SIZE);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Professor.Location> getContractsLocations(){
        return contractDao.getContractsLocations();
    }

    @Override
    @Transactional
    public List<Subject.Categories> getContractsCategories(){
        return contractDao.getContractsCategories();
    }

    @Override
    @Transactional
    public List<Subject.Levels> getContractsLevels(){
        return contractDao.getContractsLevels();
    }

    @Override
    @Transactional(readOnly = true)
    public int getContractsCount(ContractFilter contractFilter) {
        return (int) Math.ceil((double) contractDao.getContractsCount(contractFilter) / PAGE_SIZE);
    }

    @Override
    @Transactional(readOnly = true)
    public int totalContracts(ContractFilter contractFilter) {
        return (int) Math.ceil(contractDao.getContractsCount(contractFilter));
    }

     @Override
     @Transactional
     public void changeContractStatus(Integer contractId, Contract.ContractStatus status){
        contractDao.changeContractStatus(contractId,status);
     }

    @Override
    @Transactional
    public void newContract(Integer professorId, Integer subjectId, String contractDescription, boolean local, boolean remote, Float price){
        contractDao.newContract(professorId, subjectId, contractDescription, local, remote, price);
    }

    @Override
    @Transactional
    public void dropContract(Integer professorId,Integer subjectId) {
        contractDao.dropContract(professorId, subjectId);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean doesUserTeach(Integer professorId, Integer subjectId){
        return contractDao.doesUserTeach(professorId, subjectId);
    }

}
