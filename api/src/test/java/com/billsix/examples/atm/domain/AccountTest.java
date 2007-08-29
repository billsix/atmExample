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
package com.billsix.examples.atm.domain;
import java.util.Set;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AccountTest extends TestCase {
    public AccountTest( String testName ) {
        super( testName );
    }
    
    public static Test suite() {
        return new TestSuite( AccountTest.class );
    }
    
    public void setUp() {
        account = new Account("bill", "password", 100.00);
    }
    
    public void testCurrentBalance() {
        assertTrue(account.getCurrentBalance() == 100.0);
    }
    
    public void testDeposit() {
        account.deposit(50.00);
        assertTrue(account.getCurrentBalance() == 150.0);
        Set<FundTransfer> transactions = account.getTransactionHistory(); 
        assertTrue(transactions.size()==1);
        FundTransfer transaction = transactions.iterator().next(); 
        assertTrue(transaction.getBalanceAfterTransaction() == 150.0);
        assertTrue(transaction.getBalanceBeforeTransaction() == 100.0);
    }
    
    public void testWithdraw() {
        account.withdraw(50.00);
        assertTrue(account.getCurrentBalance() == 50.0);
        Set<FundTransfer> transactions = account.getTransactionHistory(); 
        assertTrue(transactions.size()==1);
        FundTransfer transaction = transactions.iterator().next(); 
        assertTrue(transaction.getBalanceAfterTransaction() == 50.0);
        assertTrue(transaction.getBalanceBeforeTransaction() == 100.0);
    }
    
    public void testPasswordIsValid() {
        assertTrue(account.passwordIsValid("password"));
        assertFalse(account.passwordIsValid("passw"));
    }
    
    private Account account;
}


