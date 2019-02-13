package com.backend.handlers;

import com.backend.dao.DataBaseService;
import com.backend.domain.Amount;
import com.backend.domain.BankAccount;
import com.backend.domain.Transfer;
import com.backend.utils.CurrencyConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TransferHandler {

    private static final Logger log = LoggerFactory.getLogger(DataBaseService.class);
    private DataBaseService dao = DataBaseService.getInstance();

    private TransferHandler() {
    }

    /**
     * Static Factory method of TransferHandler class
     *
     * @return singleton instance
     */
    public static synchronized TransferHandler getInstance() {
        return TransferHandlerInstanceContainer.instance;
    }

    public void transfer(Transfer transfer) throws Exception {
        if (transfer != null) {
            BankAccount originAccount = dao.getBankAccount(transfer.getOriginAccountNumber());
            if (originAccount != null) {
                BankAccount destinationAccount = dao.getBankAccount(transfer.getDestinationAccountNumber());
                if (destinationAccount != null) {
                    Amount destinationAmount = CurrencyConverter.convertAmount(transfer.getAmount(), destinationAccount.getBalance().getCurrency());
                    if (destinationAmount != null && originAccount.payTransfer(transfer.getAmount())) {
                        destinationAccount.receiveTransfer(destinationAmount);
                        //At this point we should update de DB, but as we are working in-memory it is not needed
                        log.info("Transfer done!");
                    } else {
                        log.error("Error to transfer");
                        throw new Exception("Transfer failed!");
                    }
                }
            }
        } else {
            log.error("Error to transfer");
            throw new Exception("Transfer failed!");
        }
    }

    /**
     * This method ensures that the same singleton reference is returned when
     * the object is serialized and the deserialized back
     *
     * @return singleton instance
     */
    protected Object readResolve() {
        return TransferHandlerInstanceContainer.instance;
    }

    /**
     * Private static instance holder.
     */
    private static final class TransferHandlerInstanceContainer {
        static final TransferHandler instance = new TransferHandler();
    }
}
