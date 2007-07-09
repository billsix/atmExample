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
        _username = username;
        _password = password;
        _currentBalance = currentBalance;
        _transactionHistory = new HashSet<AccountTransaction>();
    }
    
    public void deposit(Double deposit) {
        Double newBalance = _currentBalance + deposit;
        AccountTransaction transaction = new AccountTransaction(this, _currentBalance, newBalance );
        _currentBalance = newBalance;
        _transactionHistory.add(transaction);
    }
    
    public void withdraw(Double withdraw) {
        Double newBalance = _currentBalance - withdraw;
        AccountTransaction transaction = new AccountTransaction(this, _currentBalance, newBalance );
        _currentBalance = newBalance;
        _transactionHistory.add(transaction);
    }
    
    public Set<AccountTransaction> getTransactionHistory() {
        return _transactionHistory;
    }
    
    public boolean passwordIsValid(String password) {
        return _password.equals(password);
    }
    
    public Double getCurrentBalance() {
        return _currentBalance;
    }
    
    private String _username;
    private transient String _password;
    private Double _currentBalance;
    private Set<AccountTransaction> _transactionHistory;
}
