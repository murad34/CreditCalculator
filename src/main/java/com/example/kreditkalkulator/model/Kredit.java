package com.example.kreditkalkulator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Kredit {

    private Double totalPrice;

    private Double downPayment;

    private Integer totalMonths;

    private Double monthlyPayment;

    private String error;

}
