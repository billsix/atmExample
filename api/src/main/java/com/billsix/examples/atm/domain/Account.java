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
import java.util.HashSet;

/**
 *  Responsiblity - Coordinates all of the objects necessary for an account; the
 *  username, password, current balance, and the transaction history.  This object is
 *  also responsible for withdrawals and deposits.
 *
 *
 * @author Bill Six
 */
public class Account extends BaseDomainObject{
    
    public Account() {
        
    }
    
    public Account(String username, String password, Double currentBalance) {
        this.username = username;
        this.password = password;
        this.currentBalance = currentBalance;
        transactionHistory = new HashSet<AccountTransaction>();
    }
    
    public void deposit(Double deposit) {
        Double newBalance = this.currentBalance + deposit;
        AccountTransaction transaction = new AccountTransaction(this, this.currentBalance, newBalance );
        this.currentBalance = newBalance;
        transactionHistory.add(transaction);
    }
    
    public void withdraw(Double withdraw) {
        Double newBalance = this.currentBalance - withdraw;
        AccountTransaction transaction = new AccountTransaction(this, this.currentBalance, newBalance );
        this.currentBalance = newBalance;
        transactionHistory.add(transaction);
    }
    
    public Set<AccountTransaction> getTransactionHistory() {
        return transactionHistory;
    }
    
    public boolean passwordIsValid(String password) {
        return this.password.equals(password);
    }
    
    public Double getCurrentBalance() {
        return this.currentBalance;
    }
    
    private String username;
    private transient String password;
    private Double currentBalance;
    private Set<AccountTransaction> transactionHistory;
}
