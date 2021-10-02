package com.nutrix.command.application.services;

import com.nutrix.command.domain.PaymentMethod;
import com.nutrix.command.infra.IPaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PaymentMethodCommandService extends PaymentMethod {

    @Autowired
    private IPaymentMethodRepository paymentMethodRepository;

    @Override
    @Transactional
    public PaymentMethod save(PaymentMethod paymentMethod) throws Exception {
        return paymentMethodRepository.save(paymentMethod);
    }

    @Override
    @Transactional
    public void delete(Integer id) throws Exception {
        paymentMethodRepository.deleteById(id);
    }
}
