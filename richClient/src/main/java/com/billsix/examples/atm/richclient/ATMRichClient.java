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
package com.billsix.examples.atm.richclient;

import com.billsix.examples.atm.domain.Account;
import com.billsix.examples.atm.domain.AccountTransaction;
import com.billsix.examples.atm.service.ATMService;
import com.billsix.examples.atm.service.ClientSideServiceLocator;
import com.billsix.examples.atm.service.Main;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 * Swing GUI for the ATM.
 *
 *
 * @author Bill Six
 */
public class ATMRichClient extends javax.swing.JFrame {
    
    /** Creates new form ATM */
    public ATMRichClient(ATMService atmService) {
        this.atmService = atmService;
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        loginDialog = new javax.swing.JDialog(atmRichClient);

        loginTextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        passwordTextField = new javax.swing.JPasswordField();
        desktopPane = new javax.swing.JDesktopPane();
        atmInternalFrame = new javax.swing.JInternalFrame();
        tabbedPane = new javax.swing.JTabbedPane();
        currentBalancePanel = new javax.swing.JPanel();
        currentBalanceLabel = new javax.swing.JLabel();
        withdrawPanel = new javax.swing.JPanel();
        amountToWithdrawTextField = new javax.swing.JTextField();
        amountToWithdrawLabel = new javax.swing.JLabel();
        amountToWithdrawButton = new javax.swing.JButton();
        depositPanel = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        _amountToDepositTextField = new javax.swing.JTextField();
        _amountToDepositLabel = new javax.swing.JLabel();
        _amountToDepositButton = new javax.swing.JButton();
        accountHistoryPanel = new javax.swing.JPanel();
        scrollPane = new javax.swing.JScrollPane();
        transactionHistoryTable = new javax.swing.JTable();
        menuBar = new javax.swing.JMenuBar();
        menu = new javax.swing.JMenu();
        fileMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();

        loginDialog.setTitle("Login");
        loginDialog.setModal(true);

        jLabel5.setText("Username:");

        jLabel6.setText("Password:");

        jButton3.setText("Login");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                loginDialogClicked(evt);
            }
        });

        javax.swing.GroupLayout loginDialogLayout = new javax.swing.GroupLayout(loginDialog.getContentPane());
        loginDialog.getContentPane().setLayout(loginDialogLayout);
        loginDialogLayout.setHorizontalGroup(
            loginDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginDialogLayout.createSequentialGroup()
                .addGroup(loginDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(loginDialogLayout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addGroup(loginDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(47, 47, 47)
                        .addGroup(loginDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(passwordTextField, 0, 0, Short.MAX_VALUE)
                            .addComponent(loginTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)))
                    .addGroup(loginDialogLayout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addComponent(jButton3)))
                .addContainerGap(87, Short.MAX_VALUE))
        );
        loginDialogLayout.setVerticalGroup(
            loginDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginDialogLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(loginDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(loginTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(loginDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(passwordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        atmInternalFrame.setIconifiable(true);
        atmInternalFrame.setMaximizable(true);
        atmInternalFrame.setResizable(true);
        atmInternalFrame.setTitle("ATM");
        tabbedPane.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tabbedPaneStateChanged(evt);
            }
        });

        currentBalanceLabel.setText("$");

        javax.swing.GroupLayout currentBalancePanelLayout = new javax.swing.GroupLayout(currentBalancePanel);
        currentBalancePanel.setLayout(currentBalancePanelLayout);
        currentBalancePanelLayout.setHorizontalGroup(
            currentBalancePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(currentBalancePanelLayout.createSequentialGroup()
                .addGap(298, 298, 298)
                .addComponent(currentBalanceLabel)
                .addContainerGap(375, Short.MAX_VALUE))
        );
        currentBalancePanelLayout.setVerticalGroup(
            currentBalancePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(currentBalancePanelLayout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addComponent(currentBalanceLabel)
                .addContainerGap(243, Short.MAX_VALUE))
        );
        tabbedPane.addTab("Current Balance", currentBalancePanel);

        amountToWithdrawLabel.setText("Amount To Withdraw");

        amountToWithdrawButton.setText("Submit");
        amountToWithdrawButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                amountToWithdrawButtonMousePressed(evt);
            }
        });

        javax.swing.GroupLayout withdrawPanelLayout = new javax.swing.GroupLayout(withdrawPanel);
        withdrawPanel.setLayout(withdrawPanelLayout);
        withdrawPanelLayout.setHorizontalGroup(
            withdrawPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(withdrawPanelLayout.createSequentialGroup()
                .addGap(222, 222, 222)
                .addGroup(withdrawPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(withdrawPanelLayout.createSequentialGroup()
                        .addComponent(amountToWithdrawTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(amountToWithdrawButton))
                    .addComponent(amountToWithdrawLabel))
                .addContainerGap(314, Short.MAX_VALUE))
        );
        withdrawPanelLayout.setVerticalGroup(
            withdrawPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(withdrawPanelLayout.createSequentialGroup()
                .addGap(103, 103, 103)
                .addComponent(amountToWithdrawLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(withdrawPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(amountToWithdrawTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(amountToWithdrawButton))
                .addContainerGap(223, Short.MAX_VALUE))
        );
        tabbedPane.addTab("Withdraw", withdrawPanel);

        _amountToDepositLabel.setText("Amount To Deposit");

        _amountToDepositButton.setText("Submit");
        _amountToDepositButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                _amountToDepositButtonMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(222, 222, 222)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(_amountToDepositTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(_amountToDepositButton))
                    .addComponent(_amountToDepositLabel))
                .addContainerGap(299, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(103, 103, 103)
                .addComponent(_amountToDepositLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(_amountToDepositTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(_amountToDepositButton))
                .addContainerGap(212, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout depositPanelLayout = new javax.swing.GroupLayout(depositPanel);
        depositPanel.setLayout(depositPanelLayout);
        depositPanelLayout.setHorizontalGroup(
            depositPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        depositPanelLayout.setVerticalGroup(
            depositPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        tabbedPane.addTab("Deposit", depositPanel);

        transactionHistoryTable.setModel(getTableModel());
        scrollPane.setViewportView(transactionHistoryTable);

        javax.swing.GroupLayout accountHistoryPanelLayout = new javax.swing.GroupLayout(accountHistoryPanel);
        accountHistoryPanel.setLayout(accountHistoryPanelLayout);
        accountHistoryPanelLayout.setHorizontalGroup(
            accountHistoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 679, Short.MAX_VALUE)
        );
        accountHistoryPanelLayout.setVerticalGroup(
            accountHistoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(accountHistoryPanelLayout.createSequentialGroup()
                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
                .addContainerGap())
        );
        tabbedPane.addTab("Account History", accountHistoryPanel);

        tabbedPane.getAccessibleContext().setAccessibleName("Withdraw");

        javax.swing.GroupLayout atmInternalFrameLayout = new javax.swing.GroupLayout(atmInternalFrame.getContentPane());
        atmInternalFrame.getContentPane().setLayout(atmInternalFrameLayout);
        atmInternalFrameLayout.setHorizontalGroup(
            atmInternalFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 684, Short.MAX_VALUE)
        );
        atmInternalFrameLayout.setVerticalGroup(
            atmInternalFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
        );
        atmInternalFrame.setBounds(100, 50, 690, 420);
        desktopPane.add(atmInternalFrame, javax.swing.JLayeredPane.DEFAULT_LAYER);

        menu.setText("File");
        fileMenuItem.setText("Log in");
        fileMenuItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                loginMenuItemPressed(evt);
            }
        });

        menu.add(fileMenuItem);

        exitMenuItem.setText("Quit");
        exitMenuItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                quitMenuItemPressed(evt);
            }
        });

        menu.add(exitMenuItem);

        menuBar.add(menu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1205, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 595, Short.MAX_VALUE)
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void amountToWithdrawButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_amountToWithdrawButtonMousePressed
        try{
            Double amountToWithdraw = Double.parseDouble(amountToWithdrawTextField.getText());
            atmService.withDraw(amountToWithdraw);
            tabbedPane.setSelectedIndex(0);
            
        } catch(NumberFormatException nfe) {
            JOptionPane.showMessageDialog(atmRichClient, "Please enter a decimal") ;
        }
    }//GEN-LAST:event_amountToWithdrawButtonMousePressed
    
    private void _amountToDepositButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event__amountToDepositButtonMousePressed
        try{
            Double amountToDeposit = Double.parseDouble(_amountToDepositTextField.getText());
            atmService.deposit(amountToDeposit);
            tabbedPane.setSelectedIndex(0);
        } catch(NumberFormatException nfe) {
            JOptionPane.showMessageDialog(atmRichClient, "Please enter a decimal") ;
        }
    }//GEN-LAST:event__amountToDepositButtonMousePressed
    
    private void tabbedPaneStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tabbedPaneStateChanged
        if(atmInternalFrame.isVisible()) {
            JTabbedPane tabbedPane = (JTabbedPane) evt.getSource();
            if(tabbedPane.getSelectedIndex() == 0) {
                refreshCurrentBalanceFigure();
            }
            if(tabbedPane.getSelectedIndex() == 3) {
                refreshAccountHistoryTable();
            }
        }
    }//GEN-LAST:event_tabbedPaneStateChanged
    
    private void refreshCurrentBalanceFigure() {
        Double currentBalance = atmService.getBalance();
        currentBalanceLabel.setText("$" + currentBalance.toString());
    }
    
    private void refreshAccountHistoryTable() {
        Account account =  atmService.fetchAccountTransactions();
        while(tableModel.getRowCount() > 0) {
            tableModel.removeRow(0);
        }
        for(AccountTransaction transaction : account.getTransactionHistory()) {
            String id = transaction.getAccount().getId().toString();
            tableModel.addRow(new Object[]{id, dateFormat.format(transaction.getDate().getTime()),transaction.getBalanceBeforeTransaction(), transaction.getBalanceAfterTransaction()});
        }
        transactionHistoryTable.repaint();
    }
    
    
    private void quitMenuItemPressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_quitMenuItemPressed
        System.exit(0);
    }//GEN-LAST:event_quitMenuItemPressed
    
    private void loginMenuItemPressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginMenuItemPressed
        loginDialog.pack();
        loginDialog.setLocationRelativeTo(atmRichClient);
        loginDialog.setVisible(true);
    }//GEN-LAST:event_loginMenuItemPressed
    
    private void loginDialogClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginDialogClicked
        String username = loginTextField.getText();
        String password = passwordTextField.getText();
        if(atmService.authenticate(username, password)) {
            loginDialog.setVisible(false);
            atmInternalFrame.setVisible(true);
            refreshCurrentBalanceFigure();
        } else {
            System.out.println("failed to log in");
        }
    }//GEN-LAST:event_loginDialogClicked
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                atmRichClient = new ATMRichClient(new Main().getATMService());
                atmRichClient.setVisible(true);
            }
        });
        
    }    
    
    public TableModel getTableModel() {
        return tableModel;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton _amountToDepositButton;
    private javax.swing.JLabel _amountToDepositLabel;
    private javax.swing.JTextField _amountToDepositTextField;
    private javax.swing.JPanel accountHistoryPanel;
    private javax.swing.JButton amountToWithdrawButton;
    private javax.swing.JLabel amountToWithdrawLabel;
    private javax.swing.JTextField amountToWithdrawTextField;
    private javax.swing.JInternalFrame atmInternalFrame;
    private javax.swing.JLabel currentBalanceLabel;
    private javax.swing.JPanel currentBalancePanel;
    private javax.swing.JPanel depositPanel;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenuItem fileMenuItem;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JDialog loginDialog;
    private javax.swing.JTextField loginTextField;
    private javax.swing.JMenu menu;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JPasswordField passwordTextField;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JTable transactionHistoryTable;
    private javax.swing.JPanel withdrawPanel;
    // End of variables declaration//GEN-END:variables
    
    private javax.swing.table.DefaultTableModel tableModel = new DefaultTableModel(new String[]{"Account Number","Transaction Date","Balance Before Transaction","Balance After Transaction"}, 0);
    private DateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
    private static ATMRichClient atmRichClient;
    private static ATMService atmService;    
}
