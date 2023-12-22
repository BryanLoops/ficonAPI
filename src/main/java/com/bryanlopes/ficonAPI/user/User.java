package com.bryanlopes.ficonAPI.user;

import com.bryanlopes.ficonAPI.transaction.Transaction;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Table(name="users")
@Entity(name="User")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Setter
    private Double wallet;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Transaction> transactions;

    public User(DataUserRegister data) {
        this.name = data.name();
        this.wallet = 0.0;
    }
}
