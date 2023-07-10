package com.mf.bank.account.repository;

import java.util.List;

import com.mf.bank.account.domain.entities.Operation;

/**
 * @author Maher FATTOUMI
 * 
 */
public interface OperationRepository {
	
    /**
     * This method create or update an @Operation
     * @param operation
     */
    public void save(Operation operation);
    
    /**
     * This method return the list of @Operation (s) for the @BankAccount with the id, idParam
     * @param idParam
     * @return
     */
    public List<Operation> findByBankAccountIdBankAccount(Long idParam);
	
	/**
	 * This method return the latest @Operation for a given @BankAccount, 
	 * we will use it to select/update the account balance. 
	 * @param idParam
	 * @return
	 */
	public Operation findLatestOperationByAccountId(Long idParam);
	
}
