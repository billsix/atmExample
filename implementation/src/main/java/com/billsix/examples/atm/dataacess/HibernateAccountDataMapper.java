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
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.dao.DataRetrievalFailureException;

/**
 *
 * @author Bill Six
 */
public class HibernateAccountDataMapper extends HibernateBaseDataMapper<Account> implements AccountDataMapper{
    
    public HibernateAccountDataMapper() {
    }
    
    public Account load(final String username) {
        Account toReturn = (Account) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(
                        "from Account as account where account._username like :username");
                query.setParameter("username", username, Hibernate.STRING);
                return (Account) query.uniqueResult();
            }
        });
        if(toReturn == null) {
            throw new DataRetrievalFailureException("No user with name " + username + " exists");
        }
        return toReturn;
    }
    
    public Account fetchAccountTransactions(final Account account) {
        getHibernateTemplate().initialize(account.getTransactionHistory());
        return account;
    }
}
