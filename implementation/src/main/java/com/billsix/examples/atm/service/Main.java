/*
 Copyright (c) 2007 Bill Six
 
Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:
 
The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.
 
THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
 */
package com.billsix.examples.atm.service;

import com.billsix.examples.atm.dataacess.HibernateAccountDataMapper;
import com.billsix.examples.atm.dataacess.AccountDataMapper;
import com.billsix.examples.atm.registry.RegistryImplementation;

import java.rmi.RemoteException;
import java.util.Properties;
import org.apache.commons.dbcp.BasicDataSource ;
import org.hibernate.SessionFactory;
import org.springframework.aop.framework.AopConfigException;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.beans.BeansException;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.remoting.rmi.RmiServiceExporter;
import org.springframework.transaction.interceptor.TransactionInterceptor;

/**
 *
 * @author Bill Six
 */
public class Main {
    
    public Main() {
        try{
            registry =  RegistryImplementation.getInstance();
            initializeLocalSessionFactoryBean();
            initializeTransactionManager();
            initializeAccountDataMapper();
            initializeATMService();
        
            
        } catch(Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void initializeATMService() throws AopConfigException, BeansException {
        ATMService atmService = new ATMServiceImplementation(registry);
        atmService = addAuthenticationInterceptor(atmService);
        registry.setAtmService(addTransactionInterceptor(atmService));
    }

    private ATMService addTransactionInterceptor(final ATMService atmService) throws AopConfigException, BeansException {

        NameMatchTransactionAttributeSource transactionAttributeSource = new NameMatchTransactionAttributeSource() ;
        DefaultTransactionAttribute transactionAttribute = new DefaultTransactionAttribute();
        transactionAttribute.setIsolationLevel(transactionAttribute.ISOLATION_DEFAULT);
        transactionAttribute.setPropagationBehavior(transactionAttribute.PROPAGATION_REQUIRED);
        transactionAttributeSource.addTransactionalMethod("*", transactionAttribute);
        
        TransactionInterceptor transactionInterceptor = new TransactionInterceptor(registry.getTransactionManager(),transactionAttributeSource);
        transactionInterceptor.afterPropertiesSet();
        
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.addAdvice(transactionInterceptor);
        proxyFactoryBean.setTarget(atmService);
        return (ATMService) proxyFactoryBean.getObject();
    }

    private ATMService addAuthenticationInterceptor(ATMService atmService) throws AopConfigException, BeansException {
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.addAdvice(new AuthenticationInterceptor());
        proxyFactoryBean.setTarget(atmService);
        atmService = (ATMService) proxyFactoryBean.getObject();
        return atmService;
    }

    private void initializeAccountDataMapper() throws AopConfigException, BeansException {
        HibernateAccountDataMapper accountDataMapper;
        accountDataMapper = new HibernateAccountDataMapper((SessionFactory)registry.getLocalSessionFactoryBean().getObject());
        
        NameMatchTransactionAttributeSource transactionAttributeSource = new NameMatchTransactionAttributeSource() ;
        DefaultTransactionAttribute transactionAttribute = new DefaultTransactionAttribute();
        transactionAttribute.setIsolationLevel(transactionAttribute.ISOLATION_DEFAULT);
        transactionAttribute.setPropagationBehavior(transactionAttribute.PROPAGATION_REQUIRED);
        transactionAttributeSource.addTransactionalMethod("*", transactionAttribute);
        
        TransactionInterceptor transactionInterceptor = new TransactionInterceptor(registry.getTransactionManager(),transactionAttributeSource);
        transactionInterceptor.afterPropertiesSet();
        
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.addAdvice(transactionInterceptor);
        proxyFactoryBean.setTarget(accountDataMapper);
        registry.setAccountDataMapper((AccountDataMapper) proxyFactoryBean.getObject());
    }

    private void initializeTransactionManager() {
        registry.setTransactionManager(new HibernateTransactionManager((org.hibernate.SessionFactory)registry.getLocalSessionFactoryBean().getObject()));
    }

    private void initializeLocalSessionFactoryBean() throws Exception {
        LocalSessionFactoryBean localSessionFactoryBean;
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost/hibernatetest");
        dataSource.setUsername("wsix");
        dataSource.setPassword("password");
        localSessionFactoryBean = new LocalSessionFactoryBean();
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.dialect","org.hibernate.dialect.PostgreSQLDialect");
        localSessionFactoryBean.setDataSource(dataSource);
        localSessionFactoryBean.setMappingResources(new String[]{"com/billsix/examples/atm/DomainObjects.hbm.xml"});
        localSessionFactoryBean.setHibernateProperties(hibernateProperties);
        localSessionFactoryBean.afterPropertiesSet();
        registry.setLocalSessionFactoryBean(localSessionFactoryBean);
    }
    
    private void startRMI() throws RemoteException {
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setServiceName("ATM");
        rmiServiceExporter.setService(registry.getAtmService());
        rmiServiceExporter.setServiceInterface(ATMService.class);
        rmiServiceExporter.setRegistryPort(1199);
        rmiServiceExporter.afterPropertiesSet();
    }

    public RegistryImplementation getRegistry() {
        return registry;
    }

    public void setRegistry(RegistryImplementation registry) {
        this.registry = registry;
    }
    
    
    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.startRMI();
        while(true) {
            Thread.currentThread().sleep(99999);
        }
    }
    
    private RegistryImplementation registry;

}
