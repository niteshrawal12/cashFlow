package com.cashflow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseDTO {
    private BigDecimal amount;
    private Date date;
    private String category;
    private String description;

}
