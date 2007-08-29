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
import com.billsix.examples.atm.domain.Account;
import com.billsix.examples.atm.domain.FundTransfer;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

public class ATMServiceTest extends TestCase {
    public ATMServiceTest( String testName ) {
        super( testName );
    }
    
    public static Test suite() {
        return new TestSuite( ATMServiceTest.class );
    }
    
    public void setUp() {
        Main main = new Main();
        localSessionFactoryBean = main.getLocalSessionFactoryBean();
        hibernateTransactionManager = main.getHibernateTransactionManager();
        atmService = main.getAtmService();
        dropAndCreateSchemas();
        populateObjectGraph();
    }
    
    private static void populateObjectGraph() throws HibernateException {
        TransactionTemplate template =  new TransactionTemplate(hibernateTransactionManager);
        template.afterPropertiesSet();
        template.execute(new TransactionCallback() {
            public Object doInTransaction(TransactionStatus transactionStatus) {
                Account account = new Account("bill", "password", 100.00);
                ((SessionFactory)localSessionFactoryBean.
                        getObject()).
                        getCurrentSession().
                        saveOrUpdate(account);
                return null;
                
            }
        });
    }
    
    
    
    private static void dropAndCreateSchemas() throws DataAccessException {
        localSessionFactoryBean.dropDatabaseSchema();
        localSessionFactoryBean.createDatabaseSchema();
    }
    
    public void testIncorrectLogin() {
        if(atmService.authenticate("ll","password")) {
            fail("Invalid user should not be able to log in");
        }
        try{
            atmService.withdraw(100.0);
            fail("Invalid user should not be able to use the ATM Service");
        } catch(IllegalStateException ise) {
        }
    }
    
    public void testCurrentBalance() {
        if( !atmService.authenticate("bill","password")) {
            fail("Valid user should be able to log in");
        }
        assertTrue(atmService.getBalance() == 100.0);
    }
    
    
    public void testWithDraw() {
        if( !atmService.authenticate("bill","password")) {
            fail("Valid user should be able to log in");
        }
        atmService.withdraw(50.0);
        assertTrue(atmService.getBalance() == 50.0);
    }
    
    public void testDeposit() {
        if( !atmService.authenticate("bill","password")) {
            fail("Valid user should be able to log in");
        }
        atmService.deposit(50.0);
        assertTrue(atmService.getBalance() == 150.0);
    }
    
    public void testFundTransferHistory() {
        if( !atmService.authenticate("bill","password")) {
            fail("Valid user should be able to log in");
        }
        atmService.withdraw(50.0);
        atmService.deposit(25.0);
        Account account = atmService.fetchFundTransferHistory();
        for(FundTransfer fundTransfer : account.getFundTransferHistory()) {
            if(fundTransfer.getBalanceAfterTransaction() != 50.0 && 
                    fundTransfer.getBalanceAfterTransaction() != 75.0 ) {
                fail("Incorrect set of fund transfers");
            }
        }
    }
    
    
    
    private static ATMService atmService;
    private static LocalSessionFactoryBean localSessionFactoryBean;
    private static HibernateTransactionManager hibernateTransactionManager;
}


