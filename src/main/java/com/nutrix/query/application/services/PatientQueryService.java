package com.nutrix.query.application.services;

import com.nutrix.command.domain.Patient;
import com.nutrix.command.infra.IPatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PatientQueryService extends Patient {

    @Autowired
    private IPatientRepository patientRepository;

    @Override
    public List<Patient> findByLastName(String lastname) throws Exception {
        return patientRepository.findByLastName(lastname);
    }

    @Override
    public List<Patient> findByFirstNameAndLastName(String firstname, String lastname) throws Exception {
        return patientRepository.findByFirstNameAndLastName(firstname, lastname);
    }

    @Override
    public List<Patient> findByFirstName(String firstName) throws Exception {
        return patientRepository.findByFirstName(firstName);
    }

    @Override
    public Patient findByUsername (String username) throws Exception {
        return patientRepository.findByUsername(username);
    }

    @Override
    public List<Patient> getAll() throws Exception {
        return patientRepository.findAll();
    }

    @Override
    public Optional<Patient> getById(Integer id) throws Exception {
        return patientRepository.findById(id);
    }
}