package com.oos.payment.controllers;

import com.oos.payment.entities.Dto;
import com.oos.payment.entities.Payment;
import com.oos.payment.entities.User;
import com.oos.payment.service.PaymentService;
import com.oos.payment.utils.PaymentVerification;
import com.oos.payment.utils.Utils;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Set;


@CrossOrigin(origins = {"https://www.mycecpayment.com", "https://mycecpayment.com"}, maxAge = 3600)
@RestController
@RequestMapping("/api")
public class PaymentController {

    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/payments")
    public Page<User> getPayments(@RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "1000") int size) {
        return paymentService.list(PageRequest.of(page, size));
    }

    @PostMapping("/payment")
    public User payment(@RequestBody Dto dto) {


        User user = dto.getUser();

        Set<Payment> payments = dto.getPayments();

        String transactionId = "";
        User byRef = null;

        do {
            transactionId = Utils.getTransactionId() + user.getMatric().substring(3).replace("/", "").replace("-", "");
            byRef = paymentService.findByRef(transactionId);
        } while (Objects.nonNull(byRef));

        user.setRef("CEC01" + transactionId);
        user.setPayments(payments);
        user.setStatus("Pending");
        user.setDate(Utils.getCurrentDate());
        user.setTotal(user.getAmount() + user.getFee());

        return paymentService.save(user);
    }


    @GetMapping("/payment/update/{ref}")
    public User updatePayment(@PathVariable String ref) {
        User user = paymentService.findByRef(ref);
        if (Objects.nonNull(user)) {
            PaymentVerification paymentVerification = new PaymentVerification();
            JSONObject verify = paymentVerification.verify(user.getRef(), "", user.getTotal());
            if (Objects.isNull(verify)) {
                return user;
            }
            user.setStatus("Success");
            return paymentService.save(user);
        }
        return null;
    }


    @GetMapping("/payment/{ref}")
    public User payment(@PathVariable String ref) {
        return paymentService.findByRef(ref);
    }

    @GetMapping("/payment/find/{ref}")
    public User paymentStatus(@PathVariable String ref) {

        User byRef = paymentService.findByRef(ref);

        if (Objects.nonNull(byRef)) {
            PaymentVerification paymentVerification = new PaymentVerification();
            //FLWSECK-c9a104353fc49e5c1ac31da422dfe1c3-X
            JSONObject verify = paymentVerification.verify(byRef.getRef(), "", byRef.getTotal());

            if (Objects.isNull(verify)) {
                return null;
            }

            if (byRef.getStatus().equals("Pending")) {
                byRef.setStatus("Success");
                User updated = paymentService.save(byRef);
                return updated;
            }

            return byRef;
        }

        return null;
    }

}
