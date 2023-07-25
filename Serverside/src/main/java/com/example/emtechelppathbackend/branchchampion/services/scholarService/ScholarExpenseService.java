package com.example.emtechelppathbackend.branchchampion.services.scholarService;

import com.example.emtechelppathbackend.branchchampion.dtos.scholars.ScholarExpenseDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ScholarExpenseService {
    ScholarExpenseDto addScholarExpenses(Long scholarId,ScholarExpenseDto scholarExpenseDto);
    List<ScholarExpenseDto> findExpensesByApplicationId(Long scholarId);

    ScholarExpenseDto updateScholarExpenses(Long scholarExpensesId, ScholarExpenseDto updatedScholarEducationDto);

    void deleteScholarExpenseById(Long scholarExpenseId);

    int getTotalExpenditurePerScholar(Long applicationId);

    int getGrandExpenditureForAllScholars();
}
