package com.mf.bank.account.repository.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mf.bank.account.domain.entities.BankAccount;
import com.mf.bank.account.repository.impl.BankAccountRepositoryImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

/**
 * @author Maher FATTOUMI
 * 
 * This class contains tests of the interface @BankAccountRepository
 */
public class BankAccountRepositoryTest {

    private BankAccountRepositoryImpl bankAccountRepository;
    private EntityManagerFactory emf;
    private EntityManager em;

    @BeforeEach
    public void setUp() {
    	emf = mock(EntityManagerFactory.class);
    	em = mock(EntityManager.class);
    	when(emf.createEntityManager()).thenReturn(em);
    }

    @Test
    public void shouldFindByIdAccount() {
    	bankAccountRepository = new BankAccountRepositoryImpl();
    	
    	// given
        BankAccount expectedBankAccount = new BankAccount();
        expectedBankAccount.setIban("SG00-0000-0000-0001");
        bankAccountRepository.save(expectedBankAccount);

        // when
        BankAccount result = bankAccountRepository.findById(1L);

        // then
        assertEquals(expectedBankAccount.getIban(), result.getIban());
    }
    
    @Test
    public void shouldRetunrNullForNotFoundIdAccount() {
    	bankAccountRepository = new BankAccountRepositoryImpl();
    	
    	// when
        BankAccount result = bankAccountRepository.findById(10L);

        // then
        assertNull(result);
    }
}