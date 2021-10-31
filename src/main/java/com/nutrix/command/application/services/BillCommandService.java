package com.nutrix.command.application.services;

import com.nutrix.command.domain.Bill;
import com.nutrix.command.infra.IBillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class BillCommandService extends Bill {

    @Autowired
    private IBillRepository billRepository;

    @Override
    @Transactional
    public Bill save(Bill bill) throws Exception {
        return billRepository.save(bill);
    }

    @Override
    @Transactional
    public void delete(Integer id) throws Exception {
        billRepository.deleteById(id);
    }
}
