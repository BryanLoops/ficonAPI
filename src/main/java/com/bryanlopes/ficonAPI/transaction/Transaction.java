package com.bryanlopes.ficonAPI.transaction;

import com.bryanlopes.ficonAPI.user.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.grammars.hql.HqlParser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Table(name="transactions")
@Entity(name="Transaction")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double value;
    private LocalDateTime registerDate;
    private String settlingDate;
    @Enumerated(EnumType.STRING)
    private TransactionType type;
    @Setter
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id", nullable = false)
    private User user;
    @Setter
    private Boolean active;




    public Transaction(DataTransactionRegister data) {
        this.name = data.name();
        this.description = data.description();
        this.value = data.value();
        this.registerDate = LocalDateTime.now();
        this.settlingDate = data.settlingDate();
        this.type = data.type();
        this.user = data.user();
        this.active = true;
    }

    public void updateWallet() {

        if (!this.getActive()) {
            if (this.getType() == TransactionType.CREDIT) {
                this.getUser().setWallet(this.getUser().getWallet() - this.getValue());
            } else if (this.getType() == TransactionType.DEBIT) {
                this.getUser().setWallet(this.getUser().getWallet() + this.getValue());
            }
        }

        if (this.getActive()) {
            if (this.getType() == TransactionType.CREDIT) {
                this.getUser().setWallet(this.getUser().getWallet() + this.getValue());
            } else if (this.getType() == TransactionType.DEBIT) {
                this.getUser().setWallet(this.getUser().getWallet() - this.getValue());
            }
        }


    }

}
