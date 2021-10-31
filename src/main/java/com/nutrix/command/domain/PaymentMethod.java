package com.nutrix.command.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name="payment_method")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentMethod implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="patient_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Patient patient;

    @Column(name="card_type", nullable = false, length = 50)
    private String cardType;

    @Column(name="card_number", nullable = false)
    private Integer cardNumber;

    @Column(name="exporation_date_month", nullable = false)
    private Integer expirationDateMonth;

    @Column(name="exporation_date_year", nullable = false)
    private Integer expirationDateYear;

    @Column(name="security_code", nullable = false)
    private Integer securityCode;

    @Column(name="owner_firstname", nullable = false, length = 100)
    private String ownerFirstname;

    @Column(name="owner_lastname", nullable = false, length = 100)
    private String ownerLastname;

    @Column(name="city", nullable = false, length = 50)
    private String city;

    @Column(name="billing_address", nullable = false, length = 100)
    private String billingAddress;

    @Column(name="billing_address_line2", nullable = false, length = 100)
    private String billingAddressLine2;

    @Column(name="postal_code", nullable = false, length = 20)
    private String postalCode;

    @Column(name="country", nullable = false, length = 50)
    private String country;

    @Column(name="phone_number", nullable = false)
    private Integer phoneNumber;

    public PaymentMethod save(PaymentMethod paymentMethod) throws Exception{return null;}
    public void delete(Integer id) throws Exception{}
    protected List<PaymentMethod> getAll() throws  Exception{return null;}
    public Optional<PaymentMethod> getById(Integer id) throws Exception{return null;}
    public List<PaymentMethod> findAllByPatient(Integer patient_id) throws Exception{return null;}
}
