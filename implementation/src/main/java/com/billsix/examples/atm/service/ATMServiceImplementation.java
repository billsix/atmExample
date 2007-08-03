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
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Bill Six
 */
public class ATMServiceImplementation extends BaseServiceImplementation implements ATMService{
    
    public ATMServiceImplementation(ServerSideServiceLocator serverServiceLocator) {
        super(serverServiceLocator);
    }
    
    public boolean authenticate(String username, String password) {
        try{
            Account account = getServerServiceLocator().getAccountDataMapper().load(username);
            if(account.passwordIsValid(password)) {
                this.account = account;
                return true;
            }
            return false;
        } catch(DataAccessException dae) {
            return false;
        }
    }
    
    public Double getBalance() {
        getServerServiceLocator().getAccountDataMapper().saveOrUpdate(this.account);
        return this.account.getCurrentBalance();
    }
    
    public void deposit(Double amountToDeposit) {
        getServerServiceLocator().getAccountDataMapper().saveOrUpdate(this.account);
        this.account.deposit(amountToDeposit);
    }
    
    public void withDraw(Double amountToWithdraw) {
        getServerServiceLocator().getAccountDataMapper().saveOrUpdate(this.account);
        this.account.withdraw(amountToWithdraw);
    }
    
    public Account fetchAccountTransactions() {
        getServerServiceLocator().getAccountDataMapper().saveOrUpdate(this.account);
        return getServerServiceLocator().getAccountDataMapper().fetchAccountTransactions(this.account);
    }
    
    
    public Account getAuthenticatedAccount() {
        return this.account;
    }
    
    private Account account;
}
