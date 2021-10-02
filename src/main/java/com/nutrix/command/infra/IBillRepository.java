package com.nutrix.command.infra;

import com.nutrix.command.domain.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IBillRepository extends JpaRepository<Bill, Integer> {
    @Query("Select b from Bill b where b.patient.id = :patient_id")
    public List<Bill> findAllByPatient(@Param("patient_id") Integer patient_id);

    @Query("Select b from Bill b where b.billDate BETWEEN :date1 AND :date2")
    public List<Bill> findBetweenDates(@Param("date1") Date date1,
                                       @Param("date2") Date date2);
}

