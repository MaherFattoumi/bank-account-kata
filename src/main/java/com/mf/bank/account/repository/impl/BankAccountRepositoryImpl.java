package com.mf.bank.account.repository.impl;

import com.mf.bank.account.domain.entities.BankAccount;
import com.mf.bank.account.repository.BankAccountRepository;
import com.mf.bank.account.repository.entity.manager.EntityManagerFactorySingleton;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
/**
 * @author Maher FATTOUMI
 * 
 * The repository needed to manage the @BankAccount
 */
@PersistenceContext
public class BankAccountRepositoryImpl implements BankAccountRepository {

    private EntityManagerFactory emf;

    public BankAccountRepositoryImpl() {
        emf = EntityManagerFactorySingleton.getInstance();
    }

    @Override
    public BankAccount findById(Long idBankAccount) {
    	EntityManager em = emf.createEntityManager();
        BankAccount bankAccount = null;

        try {
            bankAccount = em.find(BankAccount.class, idBankAccount);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return bankAccount;
    }
    
    @Override
    public void save(BankAccount bankAccount) {
    	EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(bankAccount);
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
    
}

