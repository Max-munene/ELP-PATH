package com.example.emtechelppathbackend.branchchampion.controllers.scholar;

import com.example.emtechelppathbackend.branchchampion.dtos.scholars.ScholarEducationDto;
import com.example.emtechelppathbackend.branchchampion.dtos.scholars.ScholarExpenseDto;
import com.example.emtechelppathbackend.branchchampion.services.scholarService.ScholarExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/scholar/expenses")
public class ScholarExpenseController {
    private final ScholarExpenseService expenseService;

   /* public ScholarExpenseController(ScholarExpenseService expenseService) {
        this.expenseService = expenseService;
    }*/
    @PostMapping("/{scholarId}/add")
    public ResponseEntity<ScholarExpenseDto> addScholarExpense(@PathVariable(value = "scholarId")Long scholarId, @RequestBody ScholarExpenseDto scholarExpenseDto){
        return new ResponseEntity<>(expenseService.addScholarExpenses(scholarId,scholarExpenseDto), HttpStatus.CREATED);
    }
    @GetMapping("/{scholarId}/view")
    public List<ScholarExpenseDto> viewScholarExpenses(@PathVariable(value = "scholarId")Long scholarId){
        return expenseService.findExpensesByApplicationId(scholarId);
    }

    //Totals per student
    @GetMapping("/student-total-expense/{applicationId}")
    public int getScholarTotalExpenses(@PathVariable Long applicationId){
        return expenseService.getTotalExpenditurePerScholar(applicationId);
    }

    //grand Totals i.e. for all students
    @GetMapping("/grand-totals")
    public  int getGrossTotalExpenses(){
        return expenseService.getGrandExpenditureForAllScholars();
    }


    @PutMapping("/update/{scholarExpensesId}")
    public ResponseEntity<ScholarExpenseDto> updateScholarExpenses(
            @PathVariable Long scholarExpensesId,
            @RequestBody ScholarExpenseDto updatedScholarExpenseDto) {
        ScholarExpenseDto updatedScholarExpenses = expenseService.updateScholarExpenses(scholarExpensesId, updatedScholarExpenseDto);
        return ResponseEntity.ok(updatedScholarExpenses);
    }
    @DeleteMapping("/delete/{scholarExpenseId}")
    public ResponseEntity<String> deleteScholarExpense(@PathVariable Long scholarExpenseId) {
        expenseService.deleteScholarExpenseById(scholarExpenseId);
        return ResponseEntity.ok("Scholar expense deleted successfully");
    }
}
