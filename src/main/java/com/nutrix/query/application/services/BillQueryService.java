package com.nutrix.query.application.services;

import com.nutrix.command.domain.Bill;
import com.nutrix.command.infra.IBillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BillQueryService extends Bill {

    @Autowired
    private IBillRepository billRepository;

    @Override
    public List<Bill> getAll() throws Exception {
        return billRepository.findAll();
    }

    @Override
    public Optional<Bill> getById(Integer id) throws Exception {
        return billRepository.findById(id);
    }

    @Override
    public List<Bill> findAllByPatient(Integer patient_id) throws Exception {
        return billRepository.findAllByPatient(patient_id);
    }

    @Override
    public List<Bill> findBetweenDates(Date date1, Date date2) throws Exception{
        return billRepository.findBetweenDates(date1, date2);
    }
}
