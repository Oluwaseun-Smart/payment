package com.oos.payment.controllers.export;

import com.oos.payment.repository.PaymentRepository;
import com.oos.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExportController {

    @Autowired
    private PaymentRepository paymentRepository;

    /**
     * Handle request to download an Excel document
     */
    @GetMapping("/download")
    public String download(Model model) {

        model.addAttribute("users", paymentRepository.findAll());
        return "";
    }


}