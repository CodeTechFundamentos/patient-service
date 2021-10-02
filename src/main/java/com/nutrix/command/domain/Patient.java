package com.nutrix.command.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "patient")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username", nullable = false, length = 16)
    private String username;

    @Column(name = "password", nullable = false, length = 60)
    private String password;

    @Column(name="firstname", nullable = false, length = 50)
    private String firstName;

    @Column(name="lastname", nullable = false, length = 50)
    private String lastName;

    @Column(name="email", nullable = false, length = 50)
    private String email;

    @Column(name="created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    public Patient save(Patient patient) throws Exception{return null;}
    public void delete(Integer id) throws Exception{}
    protected List<Patient> getAll() throws  Exception{return null;}
    public Optional<Patient> getById(Integer id) throws Exception{return null;}
    public Patient findByUsername (String username) throws Exception{return null;}
    public List<Patient> findByLastName(String lastname) throws Exception{return null;}
    public List<Patient> findByFirstNameAndLastName(String firstname, String lastname) throws Exception{return null;}
    public List<Patient> findByFirstName(String firstName) throws Exception{return null;}
}