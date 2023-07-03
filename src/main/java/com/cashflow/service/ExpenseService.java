package com.cashflow.service;

import com.cashflow.dto.ExpenseDTO;
import com.cashflow.entity.Expense;
import com.cashflow.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;

    public Expense createExpense(ExpenseDTO expenseDTO) {
        Expense expense = new Expense();
        // Set values from DTO to the entity
        expense.setAmount(expenseDTO.getAmount());
        expense.setDate(expenseDTO.getDate());
        expense.setCategory(expenseDTO.getCategory());
        expense.setDescription(expenseDTO.getDescription());

        return expenseRepository.save(expense);
    }

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }
    public Expense getExpenseById(Long id) {
        return expenseRepository.findById(id).orElse(null);
    }

    public Expense updateExpense(Long id, ExpenseDTO expenseDTO) {
        Optional<Expense> optionalExpense = expenseRepository.findById(id);
        if (optionalExpense.isPresent()) {
            Expense expense = optionalExpense.get();
            // Update expense details based on the DTO
            expense.setAmount(expenseDTO.getAmount());
            expense.setDate(expenseDTO.getDate());
            expense.setCategory(expenseDTO.getCategory());
            expense.setDescription(expenseDTO.getDescription());
            return expenseRepository.save(expense);
        } else {
            return null; // Expense with the given ID not found
        }
    }

    public boolean deleteExpense(Long id) {
        Optional<Expense> optionalExpense = expenseRepository.findById(id);
        if (optionalExpense.isPresent()) {
            expenseRepository.deleteById(id);
            return true;
        } else {
            return false; // Expense with the given ID not found
        }
    }

}
