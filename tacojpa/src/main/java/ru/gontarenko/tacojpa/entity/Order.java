package ru.gontarenko.tacojpa.entity;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "order_table")
public final class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "placed_at")
    private Date placedAt;

    @NotBlank(message = "Name is required")
    @Column(name = "delivery_name")
    private String deliveryName;

    @NotBlank(message = "Street is required")
    @Column(name = "delivery_street")
    private String deliveryStreet;

    @NotBlank(message = "City is required")
    @Column(name = "delivery_city")
    private String deliveryCity;

    @NotBlank(message = "State is required")
    @Size(max = 2, message = "Max length 2 char")
    @Column(name = "delivery_state")
    private String deliveryState;

    @NotBlank(message = "Zip code is required")
    @Column(name = "delivery_zip")
    private String deliveryZip;

    @CreditCardNumber(message = "Not a valid credit card number")
    @Column(name = "cc_number")
    private String ccNumber;

    @Pattern(regexp = "^(0[1-9]|1[0-2])(/)([1-9][0-9])$",
            message = "Must be formatted MM/YY")
    @Column(name = "cc_expiration")
    private String ccExpiration;

    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    @Column(name = "cc_CVV")
    private String ccCVV;

    @ManyToMany()
    @JoinTable(
            name = "taco_order",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "taco_id")
    )
    private List<Taco> tacos = new ArrayList<>();

    public void addTaco(Taco taco) {
        tacos.add(taco);
    }

    @PrePersist
    void placeAt() {
        this.placedAt = new Date();
    }
}