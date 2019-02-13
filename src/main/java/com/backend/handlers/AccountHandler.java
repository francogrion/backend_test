package com.backend.handlers;

import com.backend.dao.DataBaseService;
import com.backend.domain.BankAccount;

public class AccountHandler {

    private DataBaseService dao = DataBaseService.getInstance();

    private AccountHandler() {
    }

    /**
     * Static Factory method of AccountHandler class
     *
     * @return singleton instance
     */
    public static synchronized AccountHandler getInstance() {
        return AccountHandlerInstanceContainer.instance;
    }

    public String createAccount(BankAccount bankAccount) throws Exception {
        return dao.saveAccount(bankAccount);
    }

    public BankAccount getBankAccount(String accountNumber) throws Exception {
        return dao.getBankAccount(accountNumber);
    }

    /**
     * This method ensures that the same singleton reference is returned when
     * the object is serialized and the deserialized back
     *
     * @return singleton instance
     */
    protected Object readResolve() {
        return AccountHandlerInstanceContainer.instance;
    }

    /**
     * Private static instance holder.
     */
    private static final class AccountHandlerInstanceContainer {
        static final AccountHandler instance = new AccountHandler();
    }
}
