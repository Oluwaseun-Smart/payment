package com.oos.payment.service;

import com.oos.payment.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PaymentService {

    Page<User> list(Pageable pageable);

    User save(User payment);

    User update(User payment);

    User findById(Long id);

    User findByRef(String ref);


    User findByRefAndStatus(String ref, String status);


}
