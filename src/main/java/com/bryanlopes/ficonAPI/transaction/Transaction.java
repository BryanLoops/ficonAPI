package com.bryanlopes.ficonAPI.transaction;

import com.bryanlopes.ficonAPI.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private TransactionType type;
    @OneToMany(mappedBy = "User")
    private User user;
    private Boolean active;

    public Transaction(DataTransactionRegister data) {
        this.name = data.name();
        this.description = data.description();
        this.value = data.value();
        this.type = data.type();
        this.active = true;
    }
}
