package com.oos.payment.service;

import com.oos.payment.entities.User;
import com.oos.payment.repository.PaymentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    private PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Page<User> list(Pageable pageable) {
        return paymentRepository.findAll(pageable);
    }

    @Override
    public User save(User payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public User update(User payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public User findById(Long id) {
//        return paymentRepository.findById(id);
        return null;
    }

    @Override
    public User findByRef(String ref) {
        return paymentRepository.findByRef(ref);
    }

    @Override
    public User findByRefAndStatus(String ref, String status) {
        return paymentRepository.findByRefAndStatus(ref,status);
    }
}
