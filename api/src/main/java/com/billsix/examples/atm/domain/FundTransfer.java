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
 * @author Bill Six
 */

public class FundTransfer extends BaseDomainObject{
    
    public FundTransfer() {
        
    }
    
    protected FundTransfer(Account account, Double balanceBeforeTransaction,Double balanceAfterTransaction) {
        this.account = account;
        this.balanceBeforeTransaction = balanceBeforeTransaction;
        this.balanceAfterTransaction = balanceAfterTransaction;
        date = Calendar.getInstance();
    }
    
    public boolean equals(Object object) {
        FundTransfer accountTransaction = (FundTransfer) object;
        return date.equals(accountTransaction.date)
        && this.balanceAfterTransaction.equals(accountTransaction.balanceAfterTransaction)
        && this.balanceBeforeTransaction.equals(accountTransaction.balanceBeforeTransaction);
    }    
        
    public Double getBalanceAfterTransaction() {
        return this.balanceAfterTransaction;
    }
    
    
    public Double getBalanceBeforeTransaction() {
        return this.balanceBeforeTransaction;
    }
    
    public Calendar getDate() {
        return date;
    }
    
    public Account getAccount() {
        return this.account;
    }

    private Account account;
    private Double balanceBeforeTransaction;
    private Double balanceAfterTransaction;
    private Calendar date;
}
