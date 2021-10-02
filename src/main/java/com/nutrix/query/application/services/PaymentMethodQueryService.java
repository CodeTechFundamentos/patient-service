package com.nutrix.query.application.services;

import com.nutrix.command.domain.PaymentMethod;
import com.nutrix.command.infra.IPaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PaymentMethodQueryService extends PaymentMethod {

    @Autowired
    private IPaymentMethodRepository paymentMethodRepository;

    @Override
    public List<PaymentMethod> getAll() throws Exception {
        return paymentMethodRepository.findAll();
    }

    @Override
    public Optional<PaymentMethod> getById(Integer id) throws Exception {
        return paymentMethodRepository.findById(id);
    }

    @Override
    public List<PaymentMethod> findAllByPatient(Integer patient_id) throws Exception{
        return paymentMethodRepository.findAllByPatient(patient_id);
    }
}
