package com.oos.payment.repository;

import com.oos.payment.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<User, Long> {


    Page<User> findAll(Pageable pageable);

    User findByRef(String ref);

    User findByRefAndStatus(String ref, String status);

}
