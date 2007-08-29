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
 * @author Bill Six
 */
public class Account extends BaseDomainObject{
    
    public Account() {
        
    }
    
    public Account(String username, String password, Double balance) {
        this.username = username;
        this.password = password;
        this.balance = balance;
        fundTransferHistory = new HashSet<FundTransfer>();
    }
    
    public void deposit(Double deposit) {
        Double newBalance = this.balance + deposit;
        FundTransfer fundTransfer = new FundTransfer(this, this.balance, newBalance );
        this.balance = newBalance;
        fundTransferHistory.add(fundTransfer);
    }
    
    public void withdraw(Double withdraw) {
        Double newBalance = this.balance - withdraw;
        FundTransfer fundTransfer = new FundTransfer(this, this.balance, newBalance );
        this.balance = newBalance;
        fundTransferHistory.add(fundTransfer);
    }
    
    public Set<FundTransfer> getFundTransferHistory() {
        return fundTransferHistory;
    }
    
    public boolean passwordIsValid(String password) {
        return this.password.equals(password);
    }
    
    public Double getBalance() {
        return this.balance;
    }
    
    private String username;
    private transient String password;
    private Double balance;
    private Set<FundTransfer> fundTransferHistory;
}
