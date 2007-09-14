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
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;

/**
 *
 * @author Bill Six
 */
public class ATMServiceImplementation implements ATMService{
    
    public ATMServiceImplementation(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    public boolean authenticate(String username, String password) {
        Account hqlAccount = (Account) sessionFactory.getCurrentSession()
        .createQuery("from Account as account where account.username like :username")
        .setParameter("username", username, Hibernate.STRING)
        .uniqueResult();
        if(hqlAccount == null) {
            return false;
        }
        
        if(hqlAccount.passwordIsValid(password)) {
            account = hqlAccount;
            return true;
        }
        return false;
    }
    
    public Double getBalance() {
        return account.getBalance();
    }
    
    public void deposit(Double amountToDeposit) {
	reloadAccount();
        account.deposit(amountToDeposit);
    }
    
    public void withdraw(Double amountToWithdraw) {
	reloadAccount();
        account.withdraw(amountToWithdraw);
    }
    
    public Account fetchFundTransferHistory() {
        Hibernate.initialize(account.getFundTransferHistory());
        return account;
    }

    public void reloadAccount()
    {
        account = (Account) sessionFactory.getCurrentSession().load(Account.class, account.getId());
    }
    
    public Account getAuthenticatedAccount() {
        return account;
    }
    
    private SessionFactory sessionFactory;
    private Account account;
}
