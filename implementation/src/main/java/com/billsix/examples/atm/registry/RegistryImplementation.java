/*
 * RegistryImplementation.java
 *
 * Created on August 2, 2007, 10:42 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.billsix.examples.atm.registry;

import com.billsix.examples.atm.dataacess.AccountDataMapper;
import com.billsix.examples.atm.service.ATMService;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

/**
 *
 * @author bsix
 */
public class RegistryImplementation implements Registry {
    
    private RegistryImplementation() {
    }


    public AccountDataMapper getAccountDataMapper() {
        return accountDataMapper;
    }

    public void setAccountDataMapper(AccountDataMapper accountDataMapper) {
        this.accountDataMapper = accountDataMapper;
    }

    public PlatformTransactionManager getTransactionManager() {
        return transactionManager;
    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public ATMService getAtmService() {
        return atmService;
    }

    public void setAtmService(ATMService atmService) {
        this.atmService = atmService;
    }

    public LocalSessionFactoryBean getLocalSessionFactoryBean() {
        return localSessionFactoryBean;
    }

    public void setLocalSessionFactoryBean(LocalSessionFactoryBean localSessionFactoryBean) {
        this.localSessionFactoryBean = localSessionFactoryBean;
    }
    
    
    public static RegistryImplementation getInstance()
    {
        return instance;
    }
    
    private AccountDataMapper accountDataMapper;
    private PlatformTransactionManager transactionManager;
    private ATMService atmService;
    private LocalSessionFactoryBean localSessionFactoryBean;
    private static RegistryImplementation instance = new RegistryImplementation();    
}
