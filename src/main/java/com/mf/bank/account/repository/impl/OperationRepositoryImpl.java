package com.mf.bank.account.repository.impl;

import java.util.ArrayList;
import java.util.List;

import com.mf.bank.account.domain.entities.Operation;
import com.mf.bank.account.repository.OperationRepository;
import com.mf.bank.account.repository.entity.manager.EntityManagerFactorySingleton;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

/**
 * @author Maher FATTOUMI
 * 
 * The repository needed to manage the @Operation
 */
@PersistenceContext
public class OperationRepositoryImpl implements OperationRepository {
	
    private EntityManagerFactory emf;
    
    public OperationRepositoryImpl() {
        emf = EntityManagerFactorySingleton.getInstance();
    }

    @Override
	public void save(Operation operation) {
    	EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();

		try {
			transaction.begin();
			em.persist(operation);
			transaction.commit();
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			em.close();
		}
	}
    
    @Override
    public List<Operation> findByBankAccountIdBankAccount(Long idParam){
    	EntityManager em = emf.createEntityManager();
		List<Operation> operations = new ArrayList<>();
		String queryString = "SELECT op FROM Operation op WHERE op.bankAccount.idBankAccount= :idBankAccount ORDER BY op.operationDate DESC";

		try {
			TypedQuery<Operation> query = em.createQuery(queryString, Operation.class);
			query.setParameter("idBankAccount", idParam);
			operations = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}

		return operations;
    }
	
    @Override
	public Operation findLatestOperationByAccountId(Long idParam) {
    	EntityManager em = emf.createEntityManager();
		Operation operation = null;
		String queryString = "SELECT op FROM Operation op WHERE op.bankAccount.idBankAccount = :idBankAccount ORDER BY op.operationDate DESC LIMIT 1";

		try {
			TypedQuery<Operation> query = em.createQuery(queryString, Operation.class);
			query.setParameter("idBankAccount", idParam);
			operation = query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}

		return operation;
	}
	
}
