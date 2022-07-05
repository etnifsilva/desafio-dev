package com.codetest.mytest.mapper;

import com.codetest.mytest.entity.StoreTransaction;
import com.codetest.mytest.enums.TransactionType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionMapper {
    public Optional<StoreTransaction> toTransaction(String fileLine) {
        if(Objects.nonNull(fileLine) && !"".equals(fileLine)) {
            StoreTransaction storeTransaction = StoreTransaction.builder()
                    .id(UUID.randomUUID().toString())
                    .transactionType(TransactionType.getByCode(Integer.parseInt(fileLine.substring(0, 1))))
                    .eventDate(LocalDate.of(
                            Integer.parseInt(fileLine.substring(1, 5)),
                            Integer.parseInt(fileLine.substring(5, 7)),
                            Integer.parseInt(fileLine.substring(7, 9))))
                    .amount(new BigDecimal(fileLine.substring(9, 19))
                            .setScale(2, RoundingMode.HALF_UP)
                            .divide(new BigDecimal(100L), 2, RoundingMode.HALF_UP))
                    .cpfNumber(fileLine.substring(19, 30))
                    .cardNumber(fileLine.substring(30, 42))
                    .transactionHour(LocalTime.of(
                            Integer.parseInt(fileLine.substring(42, 44)),
                            Integer.parseInt(fileLine.substring(44, 46)),
                            Integer.parseInt(fileLine.substring(46, 48))
                    ))
                    .storeOwnerName(fileLine.substring(48, 62).trim())
                    .storeName(fileLine.substring(62, 80).trim())
                    .build();
            return Optional.of(storeTransaction);
        } else {
            return Optional.empty();
        }
    }
}
