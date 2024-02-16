package com.example.kreditkalkulator.controller;

import com.example.kreditkalkulator.model.Kredit;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;

@RestController
@RequestMapping()
public class KreditController {

    @PostMapping("/kredit")
    public Kredit addOrder(@RequestBody Kredit kredit) {

        BigDecimal bigDecimal = BigDecimal.valueOf(calculateAnnuityMonthlyPayment(kredit.getTotalPrice() - kredit.getDownPayment(), kredit.getTotalMonths())).setScale(2, RoundingMode.HALF_UP);

        kredit.setMonthlyPayment(bigDecimal.doubleValue());

        return kredit;
    }

    public static Double calculateAnnuityMonthlyPayment(Double loanAmount, Integer totalMonths) {
        double monthlyInterestRate = 0.10 / 12;
        return loanAmount * (monthlyInterestRate * Math.pow((1 + monthlyInterestRate), totalMonths)) /
                (Math.pow((1 + monthlyInterestRate), totalMonths) - 1);
    }

}