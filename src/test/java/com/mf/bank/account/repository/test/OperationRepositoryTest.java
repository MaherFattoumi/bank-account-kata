package com.mf.bank.account.repository.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mf.bank.account.domain.entities.BankAccount;
import com.mf.bank.account.domain.entities.Operation;
import com.mf.bank.account.repository.impl.BankAccountRepositoryImpl;
import com.mf.bank.account.repository.impl.OperationRepositoryImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

/**
 * @author Maher FATTOUMI
 * 
 * This class contains tests of the interface @OperationRepository
 */
public class OperationRepositoryTest {

    private OperationRepositoryImpl operationRepository;
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
    void shouldRetunrNullForNotFoundIdOperation() {
    	bankAccountRepository = new BankAccountRepositoryImpl();
    	operationRepository = new OperationRepositoryImpl();
    	
    	// given
        BankAccount bankAccount = new BankAccount();
        bankAccount.setIban("SG00-0000-0000-0001");
        bankAccountRepository.save(bankAccount);
        BankAccount bankAccountCreated = bankAccountRepository.findById(1L);
        
        Operation operation = new Operation();
        operation.setBankAccount(bankAccountCreated);
        operation.setAmount(BigDecimal.TEN);
        operation.setBalance(BigDecimal.TEN);
        operation.setOperationDate(LocalDateTime.now().withNano(0));
        operation.setOperationType("Deposit");
        
        // when
        operationRepository.save(operation);
        Operation result1 = operationRepository.findLatestOperationByAccountId(1L);
        List<Operation> operations = operationRepository.findByBankAccountIdBankAccount(1L);

        // then
        assertEquals(result1.getIdOperation(), 1L);
        assertNotNull(operations);
        assertEquals(operations.size(), 1);
        
    }
}