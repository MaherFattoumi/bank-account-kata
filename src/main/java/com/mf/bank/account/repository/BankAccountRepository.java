package com.mf.bank.account.repository;

import com.mf.bank.account.domain.entities.BankAccount;

/**
 * @author Maher FATTOUMI
 * 
 */
public interface BankAccountRepository {

	
    /**
     * This method return a @BankAccount by it's id
     * @param idBankAccount
     * @return
     */
    public BankAccount findById(Long idBankAccount);
    
    /**
     * This method create or update a @BankAccount
     * @param bankAccount
     */
    public void save(BankAccount bankAccount);
    
}