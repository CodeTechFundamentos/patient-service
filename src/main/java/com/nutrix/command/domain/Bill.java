package com.nutrix.command.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name="Bill")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bill implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="patient_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Patient patient;

    @Column(name="bill_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date billDate;

    @Column(name="amount", nullable = false)
    private Double amount;

    @Column(name="ruc", nullable = true)
    private Integer ruc;


    public Bill save(Bill bill) throws Exception{return null;}
    public void delete(Integer id) throws Exception{};
    protected List<Bill> getAll() throws  Exception{return null;}
    public Optional<Bill> getById(Integer id) throws Exception{return null;}
    public List<Bill> findAllByPatient(Integer patient_id) throws Exception{return null;}
    public List<Bill> findBetweenDates(Date date1, Date date2) throws Exception{return null;}
}

