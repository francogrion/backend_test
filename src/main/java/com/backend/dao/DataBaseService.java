package com.backend.dao;

import com.backend.domain.BankAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.ArrayList;

public class DataBaseService implements Serializable, Cloneable {

    private static final Logger log = LoggerFactory.getLogger(DataBaseService.class);

    //variables to keep in-memory datastore
    private ArrayList<BankAccount> accounts;
    private int accountNumberIncrementer;

    private DataBaseService() {
        accountNumberIncrementer = 0;
    }

    /**
     * Static Factory method of DataBaseService class
     *
     * @return singleton instance
     */
    public static synchronized DataBaseService getInstance() {
        return DataBaseServiceInstanceContainer.instance;
    }

    public String saveAccount(BankAccount bankAccount) throws Exception {
        if (accounts == null) {
            accounts = new ArrayList();
        }
        if (bankAccount != null) {
            bankAccount.setBankAccountNumber(getNextAccountNumber());
            accounts.add(bankAccount);
            log.info("Account {} added to the DB!", bankAccount.getBankAccountNumber());
            return bankAccount.getBankAccountNumber();
        }
        log.error("Bank Account is null.");
        throw new Exception("Bank Account is null.");
    }

    private String getNextAccountNumber() {
        accountNumberIncrementer++;
        return String.valueOf(accountNumberIncrementer);
    }

    public BankAccount getBankAccount(String accountNumber) throws Exception {
        if (accounts != null) {
            for (BankAccount account : accounts) {
                if (accountNumber.equals(account.getBankAccountNumber())) {
                    return account;
                }
            }
        }
        log.error("Account number {} not found.", accountNumber);
        throw new Exception("Account number " + accountNumber + " not found.");
    }

    /**
     * This method ensures that the same singleton reference is returned when
     * the object is serialized and the deserialized back
     *
     * @return singleton instance
     */
    protected Object readResolve() {
        return DataBaseServiceInstanceContainer.instance;
    }

    /**
     * Private static instance holder.
     */
    private static final class DataBaseServiceInstanceContainer {
        static final DataBaseService instance = new DataBaseService();
    }

}
