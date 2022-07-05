package com.codetest.mytest.entity;

import com.codetest.mytest.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "store_transaction", schema = "mytest_schema")
public class StoreTransaction {

    @Id
    @Column(name = "id")
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type")
    private TransactionType transactionType;

    @Column(name = "event_date")
    private LocalDate eventDate;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "cpf_number")
    private String cpfNumber;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "transaction_hour")
    private LocalTime transactionHour;

    @Column(name = "store_owner_name")
    private String storeOwnerName;

    @Column(name = "store_name")
    private String storeName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StoreTransaction that = (StoreTransaction) o;

        if (!id.equals(that.id)) return false;
        if (transactionType != that.transactionType) return false;
        if (!eventDate.equals(that.eventDate)) return false;
        if (!amount.equals(that.amount)) return false;
        if (!cpfNumber.equals(that.cpfNumber)) return false;
        if (!cardNumber.equals(that.cardNumber)) return false;
        if (!transactionHour.equals(that.transactionHour)) return false;
        if (!storeOwnerName.equals(that.storeOwnerName)) return false;
        return storeName.equals(that.storeName);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + transactionType.hashCode();
        result = 31 * result + eventDate.hashCode();
        result = 31 * result + amount.hashCode();
        result = 31 * result + cpfNumber.hashCode();
        result = 31 * result + cardNumber.hashCode();
        result = 31 * result + transactionHour.hashCode();
        result = 31 * result + storeOwnerName.hashCode();
        result = 31 * result + storeName.hashCode();
        return result;
    }
}
