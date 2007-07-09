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
package com.billsix.examples.atm.util;

import com.billsix.examples.atm.domain.Account;

/**
 *  Responsiblity - Create instances of the domain model object graph
 *
 * @author Bill Six
 */
public class Fixtures {

    /**
     * @return An Account no AccountTransactions
     */
    public static Account createAccountWith0TransactionsWith100Dollars() {
        return new Account("bill", "password", 100.00);
    }
    
    /**
     * @return An Account which has one AccountTransaction, which brings the
     * new balance to $200.
     */
    public static Account createAccountWith1DepositWith200Balance() {
        Account account = new Account("bill", "password", 100.00);
        account.deposit(100.0);
        return account ;
    }
    
    
}
