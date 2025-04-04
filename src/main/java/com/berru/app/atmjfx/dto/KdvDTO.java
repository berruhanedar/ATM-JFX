package com.berru.app.atmjfx.dto;

import lombok.*;

import java.time.LocalDate;

/**
 * 📦 VAT Calculation Data Transfer Object (DTO)
 * Includes amount, rate, receipt details, and calculation results.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class KdvDTO {

    private Integer id;
    private Double amount;
    private Double kdvRate;
    private Double kdvAmount;
    private Double totalAmount;
    private String receiptNumber;
    private LocalDate transactionDate;
    private String description;
    private String exportFormat;

    public boolean isValid() {
        return amount != null && kdvRate != null && amount > 0 && kdvRate >= 0 && transactionDate != null;
    }

    public void calculateTotals() {
        this.kdvAmount = amount * kdvRate / 100;
        this.totalAmount = amount + this.kdvAmount;
    }

    public String toExportString() {
        return String.format("""
                        Receipt No  : %s
                        Date        : %s
                        Description : %s
                        Amount      : %.2f ₺
                        VAT Rate    : %% %.1f
                        VAT Amount  : %.2f ₺
                        Total Amount: %.2f ₺
                        """,
                receiptNumber,
                transactionDate,
                description != null ? description : "-",
                amount,
                kdvRate,
                kdvAmount,
                totalAmount
        );
    }
}
