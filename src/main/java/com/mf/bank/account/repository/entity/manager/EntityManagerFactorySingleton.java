package com.mf.bank.account.repository.entity.manager;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerFactorySingleton {
    
	private static EntityManagerFactory emf;

    private EntityManagerFactorySingleton() {
    	
    }

    public static EntityManagerFactory getInstance() {
        if (emf == null) {
            // Crée l'EntityManagerFactory en utilisant le nom de persistence unit spécifié dans le persistence.xml
            emf = Persistence.createEntityManagerFactory("bankAccountPersistenceUnit");
        }
        return emf;
    }
}


