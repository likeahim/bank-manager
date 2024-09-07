package com.likeahim.bank.account.manager.strategy.fee.transaction;

import com.likeahim.bank.account.manager.domain.transaction.AccountTransaction;
import com.likeahim.bank.account.manager.domain.transaction.TransactionFee;
import com.likeahim.bank.account.manager.exception.UnknownTransactionType;

public class TransactionFeeStrategy {

    public static void assignTransferFee(AccountTransaction transaction) throws UnknownTransactionType {
        switch (transaction.getType()) {
            case EXTERN_TRANSFER -> transaction.setTransactionFee(TransactionFee.EXTERN_TRANSFER.getFee());
            case INTERN_TRANSFER -> transaction.setTransactionFee(TransactionFee.INTERN_TRANSFER.getFee());
            case CASH_IN -> transaction.setTransactionFee(TransactionFee.CASH_IN.getFee());
            case CASH_OUT -> transaction.setTransactionFee(TransactionFee.CASH_OUT.getFee());
            case BLIK -> transaction.setTransactionFee(TransactionFee.BLIK.getFee());
            case CREDIT_IN -> transaction.setTransactionFee(TransactionFee.CREDIT_IN.getFee());
            default -> throw new UnknownTransactionType();
        }
    }
}
