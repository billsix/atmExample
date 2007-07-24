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
package com.billsix.examples.atm.dataacess;
import com.billsix.examples.atm.domain.Account;
import com.billsix.examples.atm.service.ServerSideServiceLocatorImplementation;

/**
 *
 * @author Bill Six <billsix@billsix.com>
 */
public class DatabasePopulator {
    
    public static void main(String[] args) {
        try{
            ServerSideServiceLocatorImplementation.getInstance().dropAndCreateSchemas();
            _accountDAO = ServerSideServiceLocatorImplementation.getInstance().getAccountDataMapper();
            Account account = new Account("bill", "password", 100.00);
            _accountDAO.saveOrUpdate(account);
            Account bill =  _accountDAO.load("bill");
        } catch(Throwable re) {
            re.printStackTrace();
        }
    }
    
    private static AccountDataMapper _accountDAO;
}