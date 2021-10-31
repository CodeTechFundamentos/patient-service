package com.nutrix.command.infra;

import com.nutrix.command.domain.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPaymentMethodRepository extends JpaRepository<PaymentMethod, Integer> {
    @Query("Select b from PaymentMethod b where b.patient.id = :patient_id")
    public List<PaymentMethod> findAllByPatient(@Param("patient_id") Integer patient_id);
}