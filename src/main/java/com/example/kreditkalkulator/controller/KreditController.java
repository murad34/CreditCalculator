package com.example.kreditkalkulator.controller;

import com.example.kreditkalkulator.model.Kredit;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;

@RestController
@RequestMapping("/credit-calculator")
public class KreditController {

    @PostMapping("/volkswagen")
    public Kredit getCreditForVolkswagen(@RequestBody Kredit kredit) {

        if (kredit.getDownPayment() > kredit.getTotalPrice()) {
            kredit.setError("1");
            return kredit;
        }

        double b = 0;

        if (kredit.getTotalMonths().equals(12)) {

            b = (kredit.getDownPayment() - (0.006 * kredit.getTotalPrice()) - (0.025 * kredit.getTotalPrice()) - 10 - (0.0015 * (0.025 * kredit.getTotalPrice()))) / 1.0015;

        } else if (kredit.getTotalMonths().equals(24)) {

            b = (kredit.getDownPayment() - (0.006 * kredit.getTotalPrice()) - (0.045 * kredit.getTotalPrice()) - 10 - (0.0015 * (0.045 * kredit.getTotalPrice()))) / 1.0015;

        } else if (kredit.getTotalMonths().equals(36)) {

            b = (kredit.getDownPayment() - (0.006 * kredit.getTotalPrice()) - (0.056 * kredit.getTotalPrice()) - 10 - (0.0015 * (0.056 * kredit.getTotalPrice()))) / 1.0015;

        } else if (kredit.getTotalMonths().equals(48)) {

            b = (kredit.getDownPayment() - (0.006 * kredit.getTotalPrice()) - (0.068 * kredit.getTotalPrice()) - 10 - (0.0015 * (0.068 * kredit.getTotalPrice()))) / 1.0015;

        } else if (kredit.getTotalMonths().equals(60)) {

            b = (kredit.getDownPayment() - (0.006 * kredit.getTotalPrice()) - (0.078 * kredit.getTotalPrice()) - 10 - (0.0015 * (0.078 * kredit.getTotalPrice()))) / 1.0015;

        }

        if ((b+1) < kredit.getTotalPrice() * 0.2) {

            double minDownPayment = 0;

            if (kredit.getTotalMonths().equals(12)) {

                minDownPayment = (0.2 * kredit.getTotalPrice()) + (0.025 * kredit.getTotalPrice()) + (0.8 * kredit.getTotalPrice() * 0.006) + (0.0015 * (kredit.getTotalPrice() + (0.025 * kredit.getTotalPrice()))) + 10;

            } else if (kredit.getTotalMonths().equals(24)) {

                minDownPayment = (0.2 * kredit.getTotalPrice()) + (0.045 * kredit.getTotalPrice()) + (0.8 * kredit.getTotalPrice() * 0.006) + (0.0015 * (kredit.getTotalPrice() + (0.045 * kredit.getTotalPrice()))) + 10;

            } else if (kredit.getTotalMonths().equals(36)) {

                minDownPayment = (0.2 * kredit.getTotalPrice()) + (0.056 * kredit.getTotalPrice()) + (0.8 * kredit.getTotalPrice() * 0.006) + (0.0015 * (kredit.getTotalPrice() + (0.056 * kredit.getTotalPrice()))) + 10;

            } else if (kredit.getTotalMonths().equals(48)) {

                minDownPayment = (0.2 * kredit.getTotalPrice()) + (0.068 * kredit.getTotalPrice()) + (0.8 * kredit.getTotalPrice() * 0.006) + (0.0015 * (kredit.getTotalPrice() + (0.068 * kredit.getTotalPrice()))) + 10;

            } else if (kredit.getTotalMonths().equals(60)) {

                minDownPayment = (0.2 * kredit.getTotalPrice()) + (0.078 * kredit.getTotalPrice()) + (0.8 * kredit.getTotalPrice() * 0.006) + (0.0015 * (kredit.getTotalPrice() + (0.078 * kredit.getTotalPrice()))) + 10;

            }

            BigDecimal bigDecimal = BigDecimal.valueOf(minDownPayment).setScale(2, RoundingMode.HALF_UP);

            kredit.setError(String.valueOf(bigDecimal));
            return kredit;
        }

        BigDecimal bigDecimal = BigDecimal.valueOf(calculateAnnuityMonthlyPayment(kredit.getTotalPrice() - b, kredit.getTotalMonths())).setScale(2, RoundingMode.HALF_UP);

        kredit.setMonthlyPayment(bigDecimal.doubleValue());

        return kredit;
    }

    @PostMapping("/dongfeng")
    public Kredit getCreditForDongfeng(@RequestBody Kredit kredit) {

        if (kredit.getDownPayment() > kredit.getTotalPrice()) {
            kredit.setError("1");
            return kredit;
        }

        double b = 0;

        if (kredit.getTotalMonths().equals(12)) {

            b = (kredit.getDownPayment() - 158 - (0.025 * kredit.getTotalPrice()));

        } else if (kredit.getTotalMonths().equals(24)) {

            b = (kredit.getDownPayment() - 158 - (0.045 * kredit.getTotalPrice()));

        } else if (kredit.getTotalMonths().equals(36)) {

            b = (kredit.getDownPayment() - 158 - (0.056 * kredit.getTotalPrice()));

        } else if (kredit.getTotalMonths().equals(48)) {

            b = (kredit.getDownPayment() - 158 - (0.068 * kredit.getTotalPrice()));

        } else if (kredit.getTotalMonths().equals(59)) {

            b = (kredit.getDownPayment() - 158 - (0.078 * kredit.getTotalPrice()));

        }

        if ((b+1) < kredit.getTotalPrice() * 0.2) {

            double minDownPayment = 0;

            if (kredit.getTotalMonths().equals(12)) {

                minDownPayment = (0.2 * kredit.getTotalPrice()) + 158 + (0.025 * kredit.getTotalPrice());

            } else if (kredit.getTotalMonths().equals(24)) {

                minDownPayment = (0.2 * kredit.getTotalPrice()) + 158 + (0.045 * kredit.getTotalPrice());

            } else if (kredit.getTotalMonths().equals(36)) {

                minDownPayment = (0.2 * kredit.getTotalPrice()) + 158 + (0.056 * kredit.getTotalPrice());

            } else if (kredit.getTotalMonths().equals(48)) {

                minDownPayment = (0.2 * kredit.getTotalPrice()) + 158 + (0.068 * kredit.getTotalPrice());

            } else if (kredit.getTotalMonths().equals(59)) {

                minDownPayment = (0.2 * kredit.getTotalPrice()) + 158 + (0.078 * kredit.getTotalPrice());

            }

            BigDecimal bigDecimal = BigDecimal.valueOf(minDownPayment).setScale(2, RoundingMode.HALF_UP);

            kredit.setError(String.valueOf(bigDecimal));
            return kredit;
        }

        BigDecimal bigDecimal = BigDecimal.valueOf(calculateAnnuityMonthlyPayment(kredit.getTotalPrice() - b, kredit.getTotalMonths())).setScale(2, RoundingMode.HALF_UP);

        kredit.setMonthlyPayment(bigDecimal.doubleValue());

        return kredit;
    }

    public static Double calculateAnnuityMonthlyPayment(Double loanAmount, Integer totalMonths) {
        double monthlyInterestRate = 0.10 / 12;
        return loanAmount * (monthlyInterestRate * Math.pow((1 + monthlyInterestRate), totalMonths)) /
                (Math.pow((1 + monthlyInterestRate), totalMonths) - 1);
    }

}
