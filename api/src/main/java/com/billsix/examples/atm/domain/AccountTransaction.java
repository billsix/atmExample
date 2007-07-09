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

import java.util.Calendar;

/**
 *  Responsiblity - Reference all of the Value objects associated with
 *   a transaction of this ATM account
 *
 * @author Bill Six
 */

public class AccountTransaction extends BaseDomainObject{
    
    public AccountTransaction() {
        
    }
    
    protected AccountTransaction(Account account, Double balanceBeforeTransaction,Double balanceAfterTransaction) {
        _account = account;
        _balanceBeforeTransaction = balanceBeforeTransaction;
        _balanceAfterTransaction = balanceAfterTransaction;
        _date = Calendar.getInstance();
        _delta = _balanceBeforeTransaction - _balanceAfterTransaction;
    }
    
    public boolean equals(Object object) {
        AccountTransaction accountTransaction = (AccountTransaction) object;
        return _date.equals(accountTransaction._date)
        && _balanceAfterTransaction.equals(accountTransaction._balanceAfterTransaction)
        && _balanceBeforeTransaction.equals(accountTransaction._balanceBeforeTransaction);
    }    
        
    public Double getBalanceAfterTransaction() {
        return _balanceAfterTransaction;
    }
    
    
    public Double getBalanceBeforeTransaction() {
        return _balanceBeforeTransaction;
    }
    
    public Calendar getDate() {
        return _date;
    }
    
    public Account getAccount() {
        return _account;
    }

    private Account _account;
    private Double _balanceBeforeTransaction;
    private Double _balanceAfterTransaction;
    private Calendar _date;
    private Double _delta;
}
