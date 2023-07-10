package com.mf.bank.account.domain.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Maher FATTOUMI
 * 
 * This class represent the personal bank account.
 */
@Entity
@NoArgsConstructor
public class BankAccount {
    
	@Getter 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBankAccount;
    
    @Getter @Setter
    private String iban;

    @OneToMany(mappedBy="bankAccount", fetch = FetchType.LAZY)
    private List<Operation> operations;
}
