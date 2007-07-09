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

import java.rmi.RemoteException;
import java.util.Properties;
import org.apache.commons.dbcp.BasicDataSource ;
import org.springframework.aop.framework.AopConfigException;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.TransactionProxyFactoryBean;
import org.springframework.remoting.rmi.RmiServiceExporter;

/**
 *
 * @author Bill Six
 */
public class ServerSideServiceLocatorImplementation implements ServerSideServiceLocator  {
    
    private ServerSideServiceLocatorImplementation() {
        try{
            initializeDataSource();
            initializeSessionFactory();
            initializeTransactionManager();
            initializeAccountDataMapper();
        } catch(Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    private void initializeDataSource() {
        _dataSource = new BasicDataSource();
        _dataSource.setDriverClassName("org.postgresql.Driver");
        _dataSource.setUrl("jdbc:postgresql://localhost/hibernatetest");
        _dataSource.setUsername("wsix");
        _dataSource.setPassword("password");
    }
    
    private void initializeSessionFactory() throws Exception {
        _sessionFactory = new LocalSessionFactoryBean();
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.dialect","org.hibernate.dialect.PostgreSQLDialect");
        _sessionFactory.setDataSource(_dataSource);
        _sessionFactory.setMappingResources(new String[]{"com/billsix/examples/atm/DomainObjects.hbm.xml"});
        _sessionFactory.setHibernateProperties(hibernateProperties);
        _sessionFactory.afterPropertiesSet();
    }
    
    private void initializeTransactionManager() {
        HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
        hibernateTransactionManager.setSessionFactory((org.hibernate.SessionFactory)_sessionFactory.getObject());
        hibernateTransactionManager.afterPropertiesSet();
        _transactionManager = hibernateTransactionManager;
    }
    
    
    private void initializeAccountDataMapper() throws BeanInitializationException, IllegalArgumentException {
        HibernateAccountDataMapper hibernateAccountDataMapper = new HibernateAccountDataMapper();
        hibernateAccountDataMapper.setSessionFactory((org.hibernate.SessionFactory)_sessionFactory.getObject());
        hibernateAccountDataMapper.afterPropertiesSet();
        _accountDataMapper = hibernateAccountDataMapper;
    }
    
    private void initializeATM() {
        _atm = new ATMServiceImplementation(this._instance);
        addSecurityInterceptorToATM();
        addTransactionInterceptorToATM();
    }
    
    private void addSecurityInterceptorToATM() throws AopConfigException {
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.addAdvice(new AuthenticationInterceptor());
        proxyFactoryBean.setTarget(_atm);
        _atm = (ATMService) proxyFactoryBean.getObject();
    }
    
    private void addTransactionInterceptorToATM() throws AopConfigException {
        Properties transactionProperties = new Properties();
        transactionProperties.setProperty("*", "PROPAGATION_REQUIRED,ISOLATION_READ_COMMITTED,-org.springframework.dao.DataAccessException");
        TransactionProxyFactoryBean transactionProxyFactoryBean = new TransactionProxyFactoryBean();
        transactionProxyFactoryBean.setTarget(_atm);
        transactionProxyFactoryBean.setTransactionManager(_transactionManager);
        transactionProxyFactoryBean.setTransactionAttributes(transactionProperties);
        transactionProxyFactoryBean.afterPropertiesSet();
        _atm = (ATMService) transactionProxyFactoryBean.getObject();
    }
    
    private void startRMI() throws RemoteException {
        _rmiServiceExporter = new RmiServiceExporter();
        _rmiServiceExporter.setServiceName("ATM");
        _rmiServiceExporter.setService(_atm);
        _rmiServiceExporter.setServiceInterface(ATMService.class);
        _rmiServiceExporter.setRegistryPort(1199);
        _rmiServiceExporter.prepare();
        _rmiServiceExporter.afterPropertiesSet();
    }
    
    public void dropAndCreateSchemas() {
        _sessionFactory.dropDatabaseSchema();
        _sessionFactory.createDatabaseSchema();
    }
    
    public PlatformTransactionManager getTransactionManager() {
        return _transactionManager;
    }
    
    public  AccountDataMapper getAccountDataMapper() {
        return _accountDataMapper;
    }
    
    public ATMService getAtmService() {
        return _atm;
    }
    
    public synchronized static ServerSideServiceLocator getInstance() {
        return _instance;
    }
    
    public static void main(String[] args) throws Exception {
        _instance.startRMI();
        while(true) {
            Thread.currentThread().sleep(99999);
        }
    }
    
    private RmiServiceExporter _rmiServiceExporter;
    private BasicDataSource _dataSource ;
    private LocalSessionFactoryBean _sessionFactory;
    private PlatformTransactionManager _transactionManager  ;
    private AccountDataMapper _accountDataMapper;
    private ATMService _atm;
    private static ServerSideServiceLocatorImplementation _instance = new ServerSideServiceLocatorImplementation();
    static{
        _instance.initializeATM();
    }
}
