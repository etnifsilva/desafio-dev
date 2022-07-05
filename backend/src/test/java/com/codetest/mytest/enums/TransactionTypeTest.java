package com.codetest.mytest.enums;

import com.codetest.mytest.enums.TransactionType;
import com.codetest.mytest.exception.BusinessException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class TransactionTypeTest {

    @Test
    void givenAValidCodeThenReturnTheTransactionType() {
        TransactionType transactionType = TransactionType.getByCode(1);
        assertEquals(TransactionType.DEBIT, transactionType);
    }

    @Test
    void givenAInvalidCodeThenReturnTheTransactionType() {
        assertThrows(BusinessException.class, () -> TransactionType.getByCode(100));
    }
}
