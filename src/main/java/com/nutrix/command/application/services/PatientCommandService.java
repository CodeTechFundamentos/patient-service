package com.nutrix.command.application.services;

import com.nutrix.command.domain.Patient;
import com.nutrix.command.infra.IPatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PatientCommandService extends Patient {

    @Autowired
    private IPatientRepository patientRepository;

    @Override
    @Transactional
    public Patient save(Patient patient) throws Exception {
        return patientRepository.save(patient);
    }

    @Override
    @Transactional
    public void delete(Integer id) throws Exception {
        patientRepository.deleteById(id);
    }
}