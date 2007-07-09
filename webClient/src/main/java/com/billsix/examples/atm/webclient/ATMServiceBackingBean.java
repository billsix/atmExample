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
package com.billsix.examples.atm.webclient;

import com.billsix.examples.atm.domain.AccountTransaction;
import com.billsix.examples.atm.service.ATMService;
import com.billsix.examples.atm.service.ServerSideServiceLocatorImplementation;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *  Responsiblity - Server side "backing bean" which holds
 *       the state of the form.
 *
 * @author Bill Six
 */

public class ATMServiceBackingBean {
    
    public ATMServiceBackingBean() {
    }
    
    
    public String login() {
        if (_atmService.authenticate(_username, _password))
            return "loginSuccess";
        return "loginFailure";
    }
    
    public String withdraw() {
        FacesContext context = FacesContext.getCurrentInstance();
        try{
            Double amountToWithdraw = Double.parseDouble(_withdrawal);
            _atmService.withDraw(amountToWithdraw);
            return "transactionSuccess";
        } catch(NumberFormatException nfe) {
            context.addMessage("withdrawalForm:withdrawalInput", new FacesMessage("Please enter a decimal number"));
            return "transactionFailure";
        }
    }
    
    public String deposit() {
        FacesContext context = FacesContext.getCurrentInstance();
        try{
            Double amountToDeposit = Double.parseDouble(_deposit);
            _atmService.deposit(amountToDeposit);
            return "transactionSuccess";
        } catch(NumberFormatException nfe) {
            context.addMessage("depositForm:depositInput", new FacesMessage("Please enter a decimal number"));
            return "transactionFailure";
        }
    }
    
    public String viewSingleTransaction() {
        _selectedTransaction = (AccountTransaction) _transactions.getRowData();
        return "viewTransactionDetails";
    }
    
    public DataModel getTransactions() {
        _transactions = new ListDataModel();
        ArrayList<AccountTransaction> wrappedList = new ArrayList<AccountTransaction>();
        wrappedList.addAll(_atmService.fetchAccountTransactions().getTransactionHistory());
        _transactions.setWrappedData(wrappedList);
        return _transactions;
    }
    
    public String getUsername() {
        return _username;
    }
    
    public void setUsername(String username) {
        _username = username;
    }
    
    public String getPassword() {
        return _password;
    }
    
    public void setPassword(String password) {
        _password = password;
    }
    
    public ATMService getAtmService() {
        return _atmService;
    }
    
    
    public String getWithdrawal() {
        return _withdrawal;
    }
    
    public void setWithdrawal(String withdrawal) {
        _withdrawal = withdrawal;
    }
    
    public String getDeposit() {
        return _deposit;
    }
    
    public void setDeposit(String deposit) {
        _deposit = deposit;
    }
    
    public AccountTransaction getSelectedTransaction() {
        return _selectedTransaction;
    }
    
    private ATMService _atmService = ServerSideServiceLocatorImplementation.getInstance().getAtmService();
    private String _username;
    private String _password;
    private String _withdrawal;
    private String _deposit;
    private DataModel _transactions ;
    private AccountTransaction _selectedTransaction;
    
}
