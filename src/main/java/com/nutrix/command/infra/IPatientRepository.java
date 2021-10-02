package com.nutrix.command.infra;

import com.nutrix.command.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPatientRepository extends JpaRepository<Patient, Integer> {
    public Patient findByUsername(String username);

    public List<Patient> findByLastName(String lastname);

    public List<Patient> findByFirstNameAndLastName(String firstname, String lastname);

    public List<Patient> findByFirstName(String firstName);
}