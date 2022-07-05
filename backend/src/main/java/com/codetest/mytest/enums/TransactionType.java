package com.codetest.mytest.enums;

import com.codetest.mytest.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum TransactionType {

    DEBIT(1, TransactionSignal.POSITIVE),
    BOLETO(2, TransactionSignal.NEGATIVE),
    LOAN(3, TransactionSignal.NEGATIVE),
    CREDIT(4, TransactionSignal.POSITIVE),
    LOAN_INCOMING(5, TransactionSignal.POSITIVE),
    SELLS(6, TransactionSignal.POSITIVE),
    TED_INCOMING(7, TransactionSignal.POSITIVE),
    DOC_INCOMING(8, TransactionSignal.POSITIVE),
    RENT(9, TransactionSignal.NEGATIVE);

    private Integer transactionCode;
    private TransactionSignal transactionSignal;

    public static TransactionType getByCode(Integer transactionCode) {
        return Arrays.stream(TransactionType.values())
                .filter(transactionType -> transactionType.getTransactionCode().equals(transactionCode))
                .reduce((a, b) -> {
                    throw new BusinessException("Multiples values to transaction code reached");
                })
                .orElseThrow(() -> new BusinessException("Invalid transaction code"));
    }
}
