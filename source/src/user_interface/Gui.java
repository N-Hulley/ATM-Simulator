package user_interface;

import java.awt.CardLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import account.abstracts.LimitedInterest;
import account.abstracts.Account;
import account.implementations.Cheque;
import account.implementations.Fixed;
import account.implementations.NetSavings;
import account.implementations.Savings;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import exceptions.IncorrectPinException;
import exceptions.AccountNotFoundException;
import exceptions.TransactionFailedException;
import java.io.IOException;

/**
 *  This is a GUI created to interact with accounts that were designed as part of
 *  a program for AIT Banks. This GUI follows all the rules that were given as
 *  part of the assignment.
 *
 *  Date : 20190125
 *  @author Nick Hulley
 */
public final class Gui extends javax.swing.JFrame {

    // Keep track of cards
    CardLayout layout;
    String currentPage;

    // Keep track of accounts
    ArrayList<Account> accounts = new ArrayList<>();
    Account currentAccount;
    
    // Keep track of the pin number of the current account
    String pinTracker;
    
    // Keep track of the page visited before help
    public String pageBeforeHelp;
    
    /**
     * Constructor function for GUI
     * 
     * @param bankAccounts 
     */
    public Gui(ArrayList<Account> bankAccounts) {

        // Add listener for window closing 
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    // Create file stream
                    FileOutputStream saveFile = new FileOutputStream("Accounts.txt");

                    // Save the objects
                    try ( // use file stream to save objects
                            ObjectOutputStream save = new ObjectOutputStream(saveFile)) {
                        // Save the objects
                        save.writeObject(accounts);
                    }
                } catch (IOException ex) {
                    System.err.println("Failed to save accounts");
                }
            }

        });

        // Set accounts to bankaccounts
        this.accounts = bankAccounts;

        initComponents();

        // Use card layout and set first page
        layout = (CardLayout) pnlCardMaster.getLayout();
        changePage("accountnumber");

    }

    /**
     * Change the displayed card to a selected page
     *
     * @param page The new page that will be shown
     */
    public void changePage(String page) {
        // Change the displated page to the input
        currentPage = page;
        layout.show(pnlCardMaster, currentPage);
    }

    /**
     *  Change how the summary page looks based on account type
     */
    private void setUpSummary() {

        if (currentAccount instanceof Cheque) {

            // Hide the withdraw limit and interest data
            pnlWithdrawLimit.setVisible(false);
            lblInterestRateDisplay.setVisible(false);
            lblInterestRate.setVisible(false);
            lblChangeLimitNotice.setVisible(false);

        } else if (currentAccount instanceof Savings) {

            // Show the interest rate and withdraw limit
            Savings currentAcc = ((Savings) currentAccount);
            lblInterestRateDisplay.setText(String.valueOf(currentAcc.getInterestRate()) + "%");
            setUpWithdrawLimit(currentAcc);

            // Show that changes can be made to limit
            lblChangeLimitNotice.setVisible(true);

        } else if (currentAccount instanceof NetSavings) {

            // Show the interest rate and withdraw limit
            NetSavings currentAcc = ((NetSavings) currentAccount);
            lblInterestRateDisplay.setText(String.valueOf(currentAcc.getInterestRate()) + "%");
            setUpWithdrawLimit(currentAcc);
            lblChangeLimitNotice.setVisible(false);

        } else if (currentAccount instanceof Fixed) {

            // Hide withdraw limits
            pnlWithdrawLimit.setVisible(false);
            lblChangeLimitNotice.setVisible(false);

            // Show the interest rate
            Fixed currentAcc = ((Fixed) currentAccount);
            if (currentAcc.getEarlyWithdrawal()) {

                // If this account withdraws early, show warning
                lblInterestRateDisplay.setText("Early Withdraw");
                lblInterestRateDisplay.setForeground(Color.red);

            } else {
                // Show standard interest rate
                lblInterestRateDisplay.setText(String.valueOf(currentAcc.getInterestRate()) + "%");
            }
        }

        // Change label values
        lblAccountTypeDisplay.setText(currentAccount.getType());
        lblAccountNumberDisplay.setText(currentAccount.getAccountNumber());
        lblBalanceDispla.setText("$" + String.valueOf(currentAccount.getBalance()));
        lblBsbDisplay.setText(currentAccount.getBsb());
        lblHolderDisplay.setText(currentAccount.getHolder().getName());

    }

    /**
     * Set up the withdraw limit display
     * @param currentAcc 
     */
    private void setUpWithdrawLimit(LimitedInterest currentAcc) {
        
        // Change withdraw limit label text
        lblWithdrawLimit.setText("$" + String.valueOf(currentAcc.getDailyLimit()));
        
        // Change withdrawn amount label text
        lblWithdrawnAmount.setText("$" + String.valueOf(currentAcc.getWithDrawnAmount()));
        
        // Change withdraw limit bar maximum
        withDrawLimitBar.setMaximum((int) currentAcc.getDailyLimit());
        
        // Set withdraw limit bar value
        withDrawLimitBar.setValue((int) currentAcc.getWithDrawnAmount());
    }

    /**
     *  Insert a character into the selected text box
     * 
     * @param value The character that will go into the text box
     */
    public void insertChar(char value) {

        // Change which text box the value is entered into
        switch (currentPage) {
            case "pin":
                // Change pin code text box
                txtPinCode.setText(txtPinCode.getText() + value);
                break;
            case "accountnumber":
                // Change account number text box
                txtAccountNumber.setText(txtAccountNumber.getText() + value);
                break;
            case "withdraw":
                // Change withdraw textbox
                txtWithdrawAmount.setText(txtWithdrawAmount.getText() + value);
                break;
            case "deposit":
                // Change the deposit textbox
                txtDepositAmount.setText(txtDepositAmount.getText() + value);
                break;
            case "changewithdraw":
                // Change withdraw limit textbox
                txtNewDailyLimit.setText(txtNewDailyLimit.getText() + value);
                break;
            default:
                break;
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */ 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlCardMaster = new javax.swing.JPanel();
        pnlChangeDaily = new javax.swing.JPanel();
        pnlWithdrawLimitHeading = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        lblDollarSign2 = new javax.swing.JLabel();
        txtNewDailyLimit = new javax.swing.JTextField();
        lblBackFromWithdraw = new javax.swing.JLabel();
        pnlHelp = new javax.swing.JPanel();
        imgHelpImage = new javax.swing.JLabel();
        pnlHistory = new javax.swing.JPanel();
        pnlHistoryHeading = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtareaHistoryDisplay = new javax.swing.JScrollPane();
        txtTransactionHistory = new javax.swing.JTextArea();
        lblReturnNotice = new javax.swing.JLabel();
        pnlWithdraw = new javax.swing.JPanel();
        pnlWithdrawHeading = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        lblDollarSign1 = new javax.swing.JLabel();
        txtWithdrawAmount = new javax.swing.JTextField();
        lblWithdrawFailed = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtNoteOutput = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lblWithdrawFailed2 = new javax.swing.JLabel();
        pnlDeposit = new javax.swing.JPanel();
        pnlDepositHeading = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        lblDollarSign = new javax.swing.JLabel();
        txtDepositAmount = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        summaryPage = new javax.swing.JPanel();
        lblBsb = new javax.swing.JLabel();
        lblAccountNumberDisplay = new javax.swing.JLabel();
        lblHolder = new javax.swing.JLabel();
        lblChangeLimitNotice = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lblInterestRate = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        pnlWithdrawLimit = new javax.swing.JPanel();
        withDrawLimitBar = new javax.swing.JProgressBar();
        lblWithdrawLimit = new javax.swing.JLabel();
        lblWithdrawnAmount = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lblBsbDisplay = new javax.swing.JLabel();
        lblBalanceDispla = new javax.swing.JLabel();
        lblInterestRateDisplay = new javax.swing.JLabel();
        lblHolderDisplay = new javax.swing.JLabel();
        lblAccountTypeDisplay = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        accountNumber = new javax.swing.JPanel();
        txtAccountNumber = new javax.swing.JTextField();
        notFoundNotice = new javax.swing.JLabel();
        pnlAccountHeading = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        pinCode = new javax.swing.JPanel();
        pnlPinHeading = new javax.swing.JPanel();
        lblPinCodeTitle = new javax.swing.JLabel();
        txtPinCode = new javax.swing.JPasswordField();
        lblIncorrectPin = new javax.swing.JLabel();
        pnlKeyPad = new javax.swing.JPanel();
        btn0 = new javax.swing.JButton();
        btn1 = new javax.swing.JButton();
        btn2 = new javax.swing.JButton();
        btn3 = new javax.swing.JButton();
        btn4 = new javax.swing.JButton();
        btn5 = new javax.swing.JButton();
        btn6 = new javax.swing.JButton();
        btn7 = new javax.swing.JButton();
        btn8 = new javax.swing.JButton();
        btn9 = new javax.swing.JButton();
        btnPeriod = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnWithdraw = new javax.swing.JButton();
        btnDeposit = new javax.swing.JButton();
        btnHistory = new javax.swing.JButton();
        btnHelp = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        btnEnter = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Nick Hulley - Simple ATM");
        setBackground(new java.awt.Color(255, 102, 102));
        setBounds(new java.awt.Rectangle(500, 500, 500, 500));
        setMinimumSize(new java.awt.Dimension(350, 500));
        setName("pnlMain"); // NOI18N
        setSize(new java.awt.Dimension(350, 500));

        pnlCardMaster.setBackground(new java.awt.Color(223, 242, 242));
        pnlCardMaster.setAlignmentX(0.0F);
        pnlCardMaster.setAlignmentY(0.0F);
        pnlCardMaster.setMaximumSize(new java.awt.Dimension(350, 200));
        pnlCardMaster.setLayout(new java.awt.CardLayout());

        pnlChangeDaily.setMaximumSize(new java.awt.Dimension(350, 200));
        pnlChangeDaily.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlWithdrawLimitHeading.setBackground(new java.awt.Color(153, 0, 153));
        pnlWithdrawLimitHeading.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Change Withdraw Limit");

        javax.swing.GroupLayout pnlWithdrawLimitHeadingLayout = new javax.swing.GroupLayout(pnlWithdrawLimitHeading);
        pnlWithdrawLimitHeading.setLayout(pnlWithdrawLimitHeadingLayout);
        pnlWithdrawLimitHeadingLayout.setHorizontalGroup(
            pnlWithdrawLimitHeadingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlWithdrawLimitHeadingLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addContainerGap(109, Short.MAX_VALUE))
        );
        pnlWithdrawLimitHeadingLayout.setVerticalGroup(
            pnlWithdrawLimitHeadingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlWithdrawLimitHeadingLayout.createSequentialGroup()
                .addGap(0, 14, Short.MAX_VALUE)
                .addComponent(jLabel8))
        );

        pnlChangeDaily.add(pnlWithdrawLimitHeading, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, -1));

        lblDollarSign2.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        lblDollarSign2.setText("$");
        pnlChangeDaily.add(lblDollarSign2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, -1, -1));

        txtNewDailyLimit.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        pnlChangeDaily.add(txtNewDailyLimit, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, 130, 30));

        lblBackFromWithdraw.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBackFromWithdraw.setText("Press \"Back\" to return");
        lblBackFromWithdraw.setName(""); // NOI18N
        pnlChangeDaily.add(lblBackFromWithdraw, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 180, 160, 20));

        pnlCardMaster.add(pnlChangeDaily, "changewithdraw");

        pnlHelp.setMaximumSize(new java.awt.Dimension(350, 200));
        pnlHelp.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        imgHelpImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/imgHelpDisplay.png"))); // NOI18N
        pnlHelp.add(imgHelpImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 360, 200));

        pnlCardMaster.add(pnlHelp, "help");

        pnlHistory.setMaximumSize(new java.awt.Dimension(350, 200));
        pnlHistory.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlHistoryHeading.setBackground(new java.awt.Color(255, 204, 255));
        pnlHistoryHeading.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Transaction History");

        javax.swing.GroupLayout pnlHistoryHeadingLayout = new javax.swing.GroupLayout(pnlHistoryHeading);
        pnlHistoryHeading.setLayout(pnlHistoryHeadingLayout);
        pnlHistoryHeadingLayout.setHorizontalGroup(
            pnlHistoryHeadingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHistoryHeadingLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addContainerGap(153, Short.MAX_VALUE))
        );
        pnlHistoryHeadingLayout.setVerticalGroup(
            pnlHistoryHeadingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHistoryHeadingLayout.createSequentialGroup()
                .addGap(0, 14, Short.MAX_VALUE)
                .addComponent(jLabel7))
        );

        pnlHistory.add(pnlHistoryHeading, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, -1));

        txtTransactionHistory.setColumns(20);
        txtTransactionHistory.setRows(5);
        txtareaHistoryDisplay.setViewportView(txtTransactionHistory);

        pnlHistory.add(txtareaHistoryDisplay, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 320, 120));

        lblReturnNotice.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblReturnNotice.setText("Press \"Back\" to return");
        pnlHistory.add(lblReturnNotice, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 180, 160, 20));

        pnlCardMaster.add(pnlHistory, "history");

        pnlWithdraw.setMaximumSize(new java.awt.Dimension(350, 200));
        pnlWithdraw.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlWithdrawHeading.setBackground(new java.awt.Color(255, 255, 0));
        pnlWithdrawHeading.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Withdraw Funds");

        javax.swing.GroupLayout pnlWithdrawHeadingLayout = new javax.swing.GroupLayout(pnlWithdrawHeading);
        pnlWithdrawHeading.setLayout(pnlWithdrawHeadingLayout);
        pnlWithdrawHeadingLayout.setHorizontalGroup(
            pnlWithdrawHeadingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlWithdrawHeadingLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addContainerGap(189, Short.MAX_VALUE))
        );
        pnlWithdrawHeadingLayout.setVerticalGroup(
            pnlWithdrawHeadingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlWithdrawHeadingLayout.createSequentialGroup()
                .addGap(0, 14, Short.MAX_VALUE)
                .addComponent(jLabel6))
        );

        pnlWithdraw.add(pnlWithdrawHeading, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, -1));

        lblDollarSign1.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        lblDollarSign1.setText("$");
        pnlWithdraw.add(lblDollarSign1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));

        txtWithdrawAmount.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        pnlWithdraw.add(txtWithdrawAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 130, 30));

        lblWithdrawFailed.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        lblWithdrawFailed.setForeground(new java.awt.Color(255, 0, 51));
        lblWithdrawFailed.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        pnlWithdraw.add(lblWithdrawFailed, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 170, 20));

        txtNoteOutput.setColumns(20);
        txtNoteOutput.setRows(5);
        jScrollPane2.setViewportView(txtNoteOutput);

        pnlWithdraw.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 80, 130, 80));

        jLabel2.setText("Note output");
        pnlWithdraw.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 60, -1, -1));

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Press \"Back\" to return");
        pnlWithdraw.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 170, 160, 20));

        lblWithdrawFailed2.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        lblWithdrawFailed2.setForeground(new java.awt.Color(255, 0, 51));
        lblWithdrawFailed2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        pnlWithdraw.add(lblWithdrawFailed2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 170, 20));

        pnlCardMaster.add(pnlWithdraw, "withdraw");

        pnlDeposit.setMaximumSize(new java.awt.Dimension(350, 200));
        pnlDeposit.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlDepositHeading.setBackground(new java.awt.Color(0, 204, 204));
        pnlDepositHeading.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("Deposit Funds");

        javax.swing.GroupLayout pnlDepositHeadingLayout = new javax.swing.GroupLayout(pnlDepositHeading);
        pnlDepositHeading.setLayout(pnlDepositHeadingLayout);
        pnlDepositHeadingLayout.setHorizontalGroup(
            pnlDepositHeadingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDepositHeadingLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addContainerGap(210, Short.MAX_VALUE))
        );
        pnlDepositHeadingLayout.setVerticalGroup(
            pnlDepositHeadingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDepositHeadingLayout.createSequentialGroup()
                .addGap(0, 14, Short.MAX_VALUE)
                .addComponent(jLabel5))
        );

        pnlDeposit.add(pnlDepositHeading, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, -1));

        lblDollarSign.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        lblDollarSign.setText("$");
        pnlDeposit.add(lblDollarSign, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, -1, -1));

        txtDepositAmount.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        pnlDeposit.add(txtDepositAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, 130, 30));

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Press \"Back\" to return");
        pnlDeposit.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 170, 160, 20));

        pnlCardMaster.add(pnlDeposit, "deposit");

        summaryPage.setMaximumSize(new java.awt.Dimension(350, 200));
        summaryPage.setMinimumSize(new java.awt.Dimension(350, 200));
        summaryPage.setPreferredSize(new java.awt.Dimension(350, 200));
        summaryPage.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblBsb.setText("Bsb");
        summaryPage.add(lblBsb, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 60, -1, -1));

        lblAccountNumberDisplay.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        lblAccountNumberDisplay.setText("000000");
        summaryPage.add(lblAccountNumberDisplay, new org.netbeans.lib.awtextra.AbsoluteConstraints(127, 62, -1, -1));

        lblHolder.setText("Holder");
        summaryPage.add(lblHolder, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        lblChangeLimitNotice.setForeground(new java.awt.Color(102, 0, 102));
        lblChangeLimitNotice.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblChangeLimitNotice.setText("Press '1' to change daily limit");
        summaryPage.add(lblChangeLimitNotice, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 180, 20));

        jPanel1.setBackground(new java.awt.Color(204, 102, 0));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Account Summary");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addContainerGap(137, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 14, Short.MAX_VALUE)
                .addComponent(jLabel4))
        );

        summaryPage.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel12.setText("Balance");
        summaryPage.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 90, -1, -1));

        lblInterestRate.setText("Interest Rate");
        summaryPage.add(lblInterestRate, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 120, -1, -1));

        jLabel18.setText("Account Type");
        summaryPage.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        withDrawLimitBar.setForeground(new java.awt.Color(0, 153, 0));
        withDrawLimitBar.setValue(50);

        lblWithdrawLimit.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        lblWithdrawLimit.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblWithdrawLimit.setText("$1000");

        lblWithdrawnAmount.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        lblWithdrawnAmount.setText("Withdrawn");

        javax.swing.GroupLayout pnlWithdrawLimitLayout = new javax.swing.GroupLayout(pnlWithdrawLimit);
        pnlWithdrawLimit.setLayout(pnlWithdrawLimitLayout);
        pnlWithdrawLimitLayout.setHorizontalGroup(
            pnlWithdrawLimitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
            .addGroup(pnlWithdrawLimitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlWithdrawLimitLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(pnlWithdrawLimitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlWithdrawLimitLayout.createSequentialGroup()
                            .addGap(220, 220, 220)
                            .addComponent(lblWithdrawLimit, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(withDrawLimitBar, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblWithdrawnAmount))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        pnlWithdrawLimitLayout.setVerticalGroup(
            pnlWithdrawLimitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
            .addGroup(pnlWithdrawLimitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlWithdrawLimitLayout.createSequentialGroup()
                    .addGap(10, 15, Short.MAX_VALUE)
                    .addComponent(withDrawLimitBar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 5, Short.MAX_VALUE))
                .addGroup(pnlWithdrawLimitLayout.createSequentialGroup()
                    .addComponent(lblWithdrawLimit, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
                .addGroup(pnlWithdrawLimitLayout.createSequentialGroup()
                    .addComponent(lblWithdrawnAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        summaryPage.add(pnlWithdrawLimit, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, -1, -1));

        jLabel13.setText("Account Number");
        summaryPage.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        lblBsbDisplay.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        lblBsbDisplay.setText("000000");
        summaryPage.add(lblBsbDisplay, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 62, -1, -1));

        lblBalanceDispla.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        lblBalanceDispla.setText("000000");
        summaryPage.add(lblBalanceDispla, new org.netbeans.lib.awtextra.AbsoluteConstraints(264, 92, -1, -1));

        lblInterestRateDisplay.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        lblInterestRateDisplay.setText("000000");
        lblInterestRateDisplay.setName(""); // NOI18N
        summaryPage.add(lblInterestRateDisplay, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 120, -1, -1));

        lblHolderDisplay.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        lblHolderDisplay.setText("000000");
        summaryPage.add(lblHolderDisplay, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 92, -1, -1));

        lblAccountTypeDisplay.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        lblAccountTypeDisplay.setText("000000");
        summaryPage.add(lblAccountTypeDisplay, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 122, -1, -1));

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Press \"Back\" to sign out");
        summaryPage.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 180, 160, 20));

        pnlCardMaster.add(summaryPage, "summary");

        accountNumber.setAlignmentX(0.0F);
        accountNumber.setAlignmentY(0.0F);
        accountNumber.setMaximumSize(new java.awt.Dimension(350, 200));
        accountNumber.setMinimumSize(new java.awt.Dimension(350, 200));
        accountNumber.setName(""); // NOI18N
        accountNumber.setPreferredSize(new java.awt.Dimension(350, 200));
        accountNumber.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtAccountNumber.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txtAccountNumber.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        accountNumber.add(txtAccountNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, 240, 50));

        notFoundNotice.setForeground(new java.awt.Color(255, 0, 0));
        notFoundNotice.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        accountNumber.add(notFoundNotice, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 130, 200, 40));

        pnlAccountHeading.setBackground(new java.awt.Color(0, 0, 153));
        pnlAccountHeading.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Account Number");

        javax.swing.GroupLayout pnlAccountHeadingLayout = new javax.swing.GroupLayout(pnlAccountHeading);
        pnlAccountHeading.setLayout(pnlAccountHeadingLayout);
        pnlAccountHeadingLayout.setHorizontalGroup(
            pnlAccountHeadingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAccountHeadingLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel10)
                .addContainerGap(158, Short.MAX_VALUE))
        );
        pnlAccountHeadingLayout.setVerticalGroup(
            pnlAccountHeadingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAccountHeadingLayout.createSequentialGroup()
                .addGap(0, 14, Short.MAX_VALUE)
                .addComponent(jLabel10))
        );

        accountNumber.add(pnlAccountHeading, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, -10, 370, 50));

        pnlCardMaster.add(accountNumber, "accountnumber");

        pinCode.setAlignmentX(0.0F);
        pinCode.setAlignmentY(0.0F);
        pinCode.setMaximumSize(new java.awt.Dimension(350, 200));
        pinCode.setMinimumSize(new java.awt.Dimension(350, 200));
        pinCode.setPreferredSize(new java.awt.Dimension(350, 200));
        pinCode.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlPinHeading.setBackground(new java.awt.Color(255, 102, 204));
        pnlPinHeading.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblPinCodeTitle.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblPinCodeTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblPinCodeTitle.setText("Pin Code");

        javax.swing.GroupLayout pnlPinHeadingLayout = new javax.swing.GroupLayout(pnlPinHeading);
        pnlPinHeading.setLayout(pnlPinHeadingLayout);
        pnlPinHeadingLayout.setHorizontalGroup(
            pnlPinHeadingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPinHeadingLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(lblPinCodeTitle)
                .addContainerGap(243, Short.MAX_VALUE))
        );
        pnlPinHeadingLayout.setVerticalGroup(
            pnlPinHeadingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPinHeadingLayout.createSequentialGroup()
                .addGap(0, 14, Short.MAX_VALUE)
                .addComponent(lblPinCodeTitle))
        );

        pinCode.add(pnlPinHeading, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, -10, 370, 50));

        txtPinCode.setFont(new java.awt.Font("Dialog", 0, 48)); // NOI18N
        txtPinCode.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pinCode.add(txtPinCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 89, 242, 48));

        lblIncorrectPin.setForeground(new java.awt.Color(255, 0, 51));
        lblIncorrectPin.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        pinCode.add(lblIncorrectPin, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 140, 240, -1));

        pnlCardMaster.add(pinCode, "pin");

        pnlKeyPad.setBackground(new java.awt.Color(204, 204, 204));
        pnlKeyPad.setAlignmentX(0.0F);
        pnlKeyPad.setAlignmentY(0.0F);
        pnlKeyPad.setMaximumSize(new java.awt.Dimension(350, 290));
        pnlKeyPad.setMinimumSize(new java.awt.Dimension(350, 290));
        pnlKeyPad.setPreferredSize(new java.awt.Dimension(350, 290));

        btn0.setText("0");
        btn0.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btn0.setMaximumSize(new java.awt.Dimension(80, 65));
        btn0.setMinimumSize(new java.awt.Dimension(80, 65));
        btn0.setPreferredSize(new java.awt.Dimension(60, 50));
        btn0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn0ActionPerformed(evt);
            }
        });

        btn1.setText("1");
        btn1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btn1.setMaximumSize(new java.awt.Dimension(80, 65));
        btn1.setMinimumSize(new java.awt.Dimension(80, 65));
        btn1.setPreferredSize(new java.awt.Dimension(60, 50));
        btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1ActionPerformed(evt);
            }
        });

        btn2.setText("2");
        btn2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btn2.setMaximumSize(new java.awt.Dimension(80, 65));
        btn2.setMinimumSize(new java.awt.Dimension(80, 65));
        btn2.setPreferredSize(new java.awt.Dimension(60, 50));
        btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn2ActionPerformed(evt);
            }
        });

        btn3.setText("3");
        btn3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btn3.setMaximumSize(new java.awt.Dimension(80, 65));
        btn3.setMinimumSize(new java.awt.Dimension(80, 65));
        btn3.setPreferredSize(new java.awt.Dimension(60, 50));
        btn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn3ActionPerformed(evt);
            }
        });

        btn4.setText("4");
        btn4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btn4.setMaximumSize(new java.awt.Dimension(80, 65));
        btn4.setMinimumSize(new java.awt.Dimension(80, 65));
        btn4.setPreferredSize(new java.awt.Dimension(60, 50));
        btn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn4ActionPerformed(evt);
            }
        });

        btn5.setText("5");
        btn5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btn5.setMaximumSize(new java.awt.Dimension(80, 65));
        btn5.setMinimumSize(new java.awt.Dimension(80, 65));
        btn5.setPreferredSize(new java.awt.Dimension(60, 50));
        btn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn5ActionPerformed(evt);
            }
        });

        btn6.setText("6");
        btn6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btn6.setMaximumSize(new java.awt.Dimension(80, 65));
        btn6.setMinimumSize(new java.awt.Dimension(80, 65));
        btn6.setPreferredSize(new java.awt.Dimension(60, 50));
        btn6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn6ActionPerformed(evt);
            }
        });

        btn7.setText("7");
        btn7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btn7.setMaximumSize(new java.awt.Dimension(80, 65));
        btn7.setMinimumSize(new java.awt.Dimension(80, 65));
        btn7.setPreferredSize(new java.awt.Dimension(60, 50));
        btn7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn7ActionPerformed(evt);
            }
        });

        btn8.setText("8");
        btn8.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btn8.setMaximumSize(new java.awt.Dimension(80, 65));
        btn8.setMinimumSize(new java.awt.Dimension(80, 65));
        btn8.setPreferredSize(new java.awt.Dimension(60, 50));
        btn8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn8ActionPerformed(evt);
            }
        });

        btn9.setText("9");
        btn9.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btn9.setMaximumSize(new java.awt.Dimension(80, 65));
        btn9.setMinimumSize(new java.awt.Dimension(80, 65));
        btn9.setPreferredSize(new java.awt.Dimension(60, 50));
        btn9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn9ActionPerformed(evt);
            }
        });

        btnPeriod.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        btnPeriod.setText(".");
        btnPeriod.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnPeriod.setMaximumSize(new java.awt.Dimension(80, 65));
        btnPeriod.setMinimumSize(new java.awt.Dimension(80, 65));
        btnPeriod.setPreferredSize(new java.awt.Dimension(60, 50));
        btnPeriod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPeriodActionPerformed(evt);
            }
        });

        btnClear.setText("CLR");
        btnClear.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnClear.setMaximumSize(new java.awt.Dimension(80, 65));
        btnClear.setMinimumSize(new java.awt.Dimension(80, 65));
        btnClear.setPreferredSize(new java.awt.Dimension(60, 50));
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnWithdraw.setBackground(new java.awt.Color(255, 255, 51));
        btnWithdraw.setText("Withdraw");
        btnWithdraw.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnWithdraw.setMaximumSize(new java.awt.Dimension(80, 65));
        btnWithdraw.setMinimumSize(new java.awt.Dimension(80, 65));
        btnWithdraw.setPreferredSize(new java.awt.Dimension(70, 50));
        btnWithdraw.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnWithdrawActionPerformed(evt);
            }
        });

        btnDeposit.setBackground(new java.awt.Color(153, 255, 204));
        btnDeposit.setText("Deposit");
        btnDeposit.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnDeposit.setMaximumSize(new java.awt.Dimension(80, 65));
        btnDeposit.setMinimumSize(new java.awt.Dimension(80, 65));
        btnDeposit.setPreferredSize(new java.awt.Dimension(70, 50));
        btnDeposit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDepositActionPerformed(evt);
            }
        });

        btnHistory.setBackground(new java.awt.Color(204, 204, 255));
        btnHistory.setText("History");
        btnHistory.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnHistory.setMaximumSize(new java.awt.Dimension(80, 65));
        btnHistory.setMinimumSize(new java.awt.Dimension(80, 65));
        btnHistory.setPreferredSize(new java.awt.Dimension(70, 50));
        btnHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistoryActionPerformed(evt);
            }
        });

        btnHelp.setBackground(new java.awt.Color(51, 102, 255));
        btnHelp.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        btnHelp.setForeground(new java.awt.Color(255, 255, 255));
        btnHelp.setText("?");
        btnHelp.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnHelp.setMaximumSize(new java.awt.Dimension(80, 65));
        btnHelp.setMinimumSize(new java.awt.Dimension(80, 65));
        btnHelp.setPreferredSize(new java.awt.Dimension(70, 50));
        btnHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHelpActionPerformed(evt);
            }
        });

        btnBack.setBackground(new java.awt.Color(255, 102, 0));
        btnBack.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnBack.setForeground(new java.awt.Color(255, 255, 255));
        btnBack.setText("Back");
        btnBack.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnBack.setMaximumSize(new java.awt.Dimension(164, 45));
        btnBack.setMinimumSize(new java.awt.Dimension(164, 45));
        btnBack.setPreferredSize(new java.awt.Dimension(164, 45));
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btnEnter.setBackground(new java.awt.Color(0, 102, 0));
        btnEnter.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnEnter.setForeground(new java.awt.Color(255, 255, 255));
        btnEnter.setText("Enter");
        btnEnter.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnEnter.setMaximumSize(new java.awt.Dimension(164, 45));
        btnEnter.setMinimumSize(new java.awt.Dimension(164, 45));
        btnEnter.setPreferredSize(new java.awt.Dimension(164, 45));
        btnEnter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlKeyPadLayout = new javax.swing.GroupLayout(pnlKeyPad);
        pnlKeyPad.setLayout(pnlKeyPadLayout);
        pnlKeyPadLayout.setHorizontalGroup(
            pnlKeyPadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKeyPadLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(pnlKeyPadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlKeyPadLayout.createSequentialGroup()
                        .addComponent(btn1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(btn2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(btn3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(btnWithdraw, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlKeyPadLayout.createSequentialGroup()
                        .addComponent(btn4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(btn5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(btn6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(btnDeposit, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlKeyPadLayout.createSequentialGroup()
                        .addComponent(btn7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(btn8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(btn9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(btnHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlKeyPadLayout.createSequentialGroup()
                        .addComponent(btnPeriod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(btn0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(btnHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlKeyPadLayout.createSequentialGroup()
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEnter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlKeyPadLayout.setVerticalGroup(
            pnlKeyPadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKeyPadLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlKeyPadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnWithdraw, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(pnlKeyPadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDeposit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(pnlKeyPadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHistory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(pnlKeyPadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPeriod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHelp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(pnlKeyPadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnEnter, javax.swing.GroupLayout.PREFERRED_SIZE, 39, Short.MAX_VALUE)
                    .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlKeyPad, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(pnlCardMaster, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlCardMaster, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlKeyPad, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE))
        );

        setBounds(0, 0, 368, 541);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * When the enter key is pressed, can be called by other functions
     * 
     * @param evt 
     */
    private void btnEnterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnterActionPerformed
        
        // Change action based on current page
        switch (currentPage) {
            case "accountnumber":
                try {

                    // Search through each account
                    for (Account account : accounts) {

                        if (account.getAccountNumber().equals(txtAccountNumber.getText())) {
                            // Change current account to this account
                            currentAccount = account;
                            // Change the page
                            changePage("pin");
                            // Stop method
                            return;
                        }
                    }
                    // If the account is not found, throw an error
                    throw new AccountNotFoundException("Failed to find account number in bank accounts", "02", txtAccountNumber.getText());

                } catch (AccountNotFoundException ex) {

                    // Print error message
                    System.err.println("Exception thrown (Code:  " + ex.getErrorcode() + "): " + ex.getMessage() + "\n" + ex.getAccountNumber());

                    // Show message to user
                    notFoundNotice.setText("Account not found");
                    // Clear box
                    txtAccountNumber.setText("");
                }
                break;
                
            case "pin":
                try {
                    if (currentAccount.correctPin(txtPinCode.getText())) {

                        // Set up the summary page
                        setUpSummary();
                        changePage("summary");

                    } else {
                        // Throw incorrect password exception
                        throw new IncorrectPinException("Incorrect pincode on account login", "03");
                    }

                } catch (IncorrectPinException ex) {
                    // Print error message
                    System.err.println("Exception thrown (Code:  " + ex.getErrorcode() + "): " + ex.getMessage());

                    // Reset textbox
                    txtPinCode.setText("");
                    // Show incorrect pin notice
                    lblIncorrectPin.setText("Incorrect Pin");
                }
                break;
            
            case "withdraw":
                // Reset label text
                lblWithdrawFailed.setText("");
                lblWithdrawFailed2.setText("");
                // Store response from withdraw
                List<Float> notes;
                // Attempt a transaction
                try {
                    notes = currentAccount.withdraw(
                            Float.parseFloat(txtWithdrawAmount.getText()));

                    notes.forEach((Float note) -> {
                        // Add notes withdrawn to the textbox
                        txtNoteOutput.setText(txtNoteOutput.getText() + "$" + note.toString() + "\n");
                    });

                } catch (TransactionFailedException ex) {
                    System.err.println(ex.getErrorcode() + ": " + ex.getMessage());
                    lblWithdrawFailed.setText("Failed to withdraw $" + txtWithdrawAmount.getText());
                    lblWithdrawFailed2.setText(ex.getMessage() + " (" + ex.getErrorcode() + ")");
                }   // Reset textbox
                txtWithdrawAmount.setText("");
                break;
            case "deposit":
                // Add money to account
                currentAccount.deposit(Float.parseFloat(txtDepositAmount.getText()));
                // Reset textbox
                txtDepositAmount.setText("");
                break;
            case "changewithdraw":
                // Change withdraw limit to the textbox value
                ((Savings) currentAccount).setDailtLimit(Float.parseFloat(txtNewDailyLimit.getText()));
                // Reset textbox
                txtNewDailyLimit.setText("");
                break;
            default:
                break;
        }


    }//GEN-LAST:event_btnEnterActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed

        // Clear the relevant textbox on each page
        switch (currentPage) {
            case "accountnumber":
                txtAccountNumber.setText("");
                break;
            case "pin":
                txtPinCode.setText("");
                break;
            case "withdraw":
                txtDepositAmount.setText("");
                break;
            default:
                break;
        }
    }//GEN-LAST:event_btnClearActionPerformed

//<editor-fold defaultstate="collapsed" desc="Numeric inputs">
    private void btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1ActionPerformed

        // If the current account is a Savings account, change page to withdraw
        if (currentPage.equals("summary")) {
            if (currentAccount instanceof Savings) {
                changePage("changewithdraw");
            }
        } else {
            insertChar('1');
        }
    }//GEN-LAST:event_btn1ActionPerformed

    private void btn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn2ActionPerformed
        insertChar('2');
    }//GEN-LAST:event_btn2ActionPerformed

    private void btn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn3ActionPerformed
        insertChar('3');
    }//GEN-LAST:event_btn3ActionPerformed

    private void btn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn4ActionPerformed
        insertChar('4');
    }//GEN-LAST:event_btn4ActionPerformed

    private void btn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn5ActionPerformed
        insertChar('5');
    }//GEN-LAST:event_btn5ActionPerformed

    private void btn6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn6ActionPerformed
        insertChar('6');
    }//GEN-LAST:event_btn6ActionPerformed

    private void btn7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn7ActionPerformed
        insertChar('7');
    }//GEN-LAST:event_btn7ActionPerformed

    private void btn8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn8ActionPerformed
        insertChar('8');
    }//GEN-LAST:event_btn8ActionPerformed

    private void btn9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn9ActionPerformed
        insertChar('9');
    }//GEN-LAST:event_btn9ActionPerformed

    private void btn0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn0ActionPerformed
        insertChar('0');
    }//GEN-LAST:event_btn0ActionPerformed

    // </editor-fold>  
    
    /**
     * Withdraw key pressed
     * @param evt 
     */
    private void btnWithdrawActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWithdrawActionPerformed

        if (currentPage.equals("summary")) {
            // Go to withdraw screen
            changePage("withdraw");
        } else if (currentPage.equals("withdraw")) {
            // Simulate enter click
            btnEnterActionPerformed(evt);
        }
    }//GEN-LAST:event_btnWithdrawActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed

        // Change the action depending on the current page
        switch (currentPage) {
            case "pin":
                // Logout
                // Reset the Gui
                new Gui(accounts).setVisible(true);
                this.dispose();
                break;
            case "summary":
                // Logout
                // Reset the Gui
                new Gui(accounts).setVisible(true);
                this.dispose();
                break;
            case "accountnumber":
                // Reset the account number text box
                txtAccountNumber.setText("");
                break;
            case "withdraw":
                // reset summary page incase of new data
                setUpSummary();
                // Set text boxes to blank
                txtWithdrawAmount.setText("");
                lblWithdrawFailed.setText("");
                lblWithdrawFailed2.setText("");
                // Change the page
                changePage("summary");
                break;
            case "deposit":
                // Set text boxes to blank
                txtDepositAmount.setText("");
                // reset summary page incase of new data
                setUpSummary();
                // Change the page
                changePage("summary");
                break;
            case "history":
                // Change the page
                changePage("summary");
                break;
            case "help":
                // Change the page to the previous page
                changePage(pageBeforeHelp);
                break;
            case "changewithdraw":
                // reset summary page incase of new data
                setUpSummary();
                // Change the page
                changePage("summary");
                break;
            default:
                break;
        }
    }//GEN-LAST:event_btnBackActionPerformed

    /**
     * When deposit key is clicked 
     * 
     * @param evt 
     */
    private void btnDepositActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDepositActionPerformed

        if (currentPage.equals("summary")) {
            // Change the page to the deposit page
            changePage("deposit");

        } else if (currentPage.equals("deposit")) {
            // Perform same action as enter click
            btnEnterActionPerformed(evt);
        }

    }//GEN-LAST:event_btnDepositActionPerformed

    /**
     * When period key is clicked
     * there is no need to insert the period unless it is in depositing or
     * changing withdraw limit
     * 
     * @param evt 
     */
    private void btnPeriodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPeriodActionPerformed

        if (currentPage.equals("deposit")) {

            // make sure there is no period already 
            if (!txtDepositAmount.getText().contains(".")) {
                insertChar('.');
            }
        }
        else if (currentPage.equals("changewithdraw")) {

            // make sure there is no period already 
            if (!txtNewDailyLimit.getText().contains(".")) {
                insertChar('.');
            }
        }
    }//GEN-LAST:event_btnPeriodActionPerformed

    /**
     * When the history key is pushed
     * 
     * @param evt 
     */
    private void btnHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistoryActionPerformed

        if (currentPage.equals("summary")) {

            // Set the text area to the transaction history
            txtTransactionHistory.setText(currentAccount.getHistory());

            // If no transactions have been then show a default message
            if (txtTransactionHistory.getText().equals("")) {
                txtTransactionHistory.setText("No transactions have been made from this account!");
            }

            // Change the page
            changePage("history");
        }
    }//GEN-LAST:event_btnHistoryActionPerformed

    

    private void btnHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHelpActionPerformed

        // If the current page is not help
        if (!currentPage.equals("help")) {
            // Keep track of current page
            pageBeforeHelp = currentPage;

            // Change page to help
            changePage("help");
        }

    }//GEN-LAST:event_btnHelpActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
            // Generated by Netbeans
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
        });
    }

    //<editor-fold defaultstate="collapsed" desc="Generated Code">            
    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JPanel accountNumber;
    javax.swing.JButton btn0;
    javax.swing.JButton btn1;
    javax.swing.JButton btn2;
    javax.swing.JButton btn3;
    javax.swing.JButton btn4;
    javax.swing.JButton btn5;
    javax.swing.JButton btn6;
    javax.swing.JButton btn7;
    javax.swing.JButton btn8;
    javax.swing.JButton btn9;
    javax.swing.JButton btnBack;
    javax.swing.JButton btnClear;
    javax.swing.JButton btnDeposit;
    javax.swing.JButton btnEnter;
    javax.swing.JButton btnHelp;
    javax.swing.JButton btnHistory;
    javax.swing.JButton btnPeriod;
    javax.swing.JButton btnWithdraw;
    javax.swing.JLabel imgHelpImage;
    javax.swing.JLabel jLabel10;
    javax.swing.JLabel jLabel12;
    javax.swing.JLabel jLabel13;
    javax.swing.JLabel jLabel14;
    javax.swing.JLabel jLabel15;
    javax.swing.JLabel jLabel17;
    javax.swing.JLabel jLabel18;
    javax.swing.JLabel jLabel2;
    javax.swing.JLabel jLabel4;
    javax.swing.JLabel jLabel5;
    javax.swing.JLabel jLabel6;
    javax.swing.JLabel jLabel7;
    javax.swing.JLabel jLabel8;
    javax.swing.JPanel jPanel1;
    javax.swing.JScrollPane jScrollPane2;
    javax.swing.JLabel lblAccountNumberDisplay;
    javax.swing.JLabel lblAccountTypeDisplay;
    javax.swing.JLabel lblBackFromWithdraw;
    javax.swing.JLabel lblBalanceDispla;
    javax.swing.JLabel lblBsb;
    javax.swing.JLabel lblBsbDisplay;
    javax.swing.JLabel lblChangeLimitNotice;
    javax.swing.JLabel lblDollarSign;
    javax.swing.JLabel lblDollarSign1;
    javax.swing.JLabel lblDollarSign2;
    javax.swing.JLabel lblHolder;
    javax.swing.JLabel lblHolderDisplay;
    javax.swing.JLabel lblIncorrectPin;
    javax.swing.JLabel lblInterestRate;
    javax.swing.JLabel lblInterestRateDisplay;
    javax.swing.JLabel lblPinCodeTitle;
    javax.swing.JLabel lblReturnNotice;
    javax.swing.JLabel lblWithdrawFailed;
    javax.swing.JLabel lblWithdrawFailed2;
    javax.swing.JLabel lblWithdrawLimit;
    javax.swing.JLabel lblWithdrawnAmount;
    javax.swing.JLabel notFoundNotice;
    javax.swing.JPanel pinCode;
    javax.swing.JPanel pnlAccountHeading;
    javax.swing.JPanel pnlCardMaster;
    javax.swing.JPanel pnlChangeDaily;
    javax.swing.JPanel pnlDeposit;
    javax.swing.JPanel pnlDepositHeading;
    javax.swing.JPanel pnlHelp;
    javax.swing.JPanel pnlHistory;
    javax.swing.JPanel pnlHistoryHeading;
    javax.swing.JPanel pnlKeyPad;
    javax.swing.JPanel pnlPinHeading;
    javax.swing.JPanel pnlWithdraw;
    javax.swing.JPanel pnlWithdrawHeading;
    javax.swing.JPanel pnlWithdrawLimit;
    javax.swing.JPanel pnlWithdrawLimitHeading;
    javax.swing.JPanel summaryPage;
    javax.swing.JTextField txtAccountNumber;
    javax.swing.JTextField txtDepositAmount;
    javax.swing.JTextField txtNewDailyLimit;
    javax.swing.JTextArea txtNoteOutput;
    javax.swing.JPasswordField txtPinCode;
    javax.swing.JTextArea txtTransactionHistory;
    javax.swing.JTextField txtWithdrawAmount;
    javax.swing.JScrollPane txtareaHistoryDisplay;
    javax.swing.JProgressBar withDrawLimitBar;
    // End of variables declaration//GEN-END:variables
// </editor-fold>  
}
