package com.mf.bank.account.domain.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Digits;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Maher FATTOUMI
 * 
 * This class represent operations of the personal bank account. 
 */
@Entity
@NoArgsConstructor
public class Operation {
	
	@Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOperation;
    
	@Getter @Setter
    @ManyToOne
    @JoinColumn(name="idBankAccount", nullable=false)
    private BankAccount bankAccount;
    
    @Getter @Setter
    private String operationType;
    
    @Getter @Setter
    private LocalDateTime operationDate;
    
    @Getter @Setter
    @Digits(integer = 31, fraction = 2)
    private BigDecimal amount;
    
    @Getter @Setter
    @Digits(integer = 31, fraction = 2)
    private BigDecimal balance;
}
