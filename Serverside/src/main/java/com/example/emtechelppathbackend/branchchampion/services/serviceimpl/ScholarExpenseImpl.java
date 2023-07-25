package com.example.emtechelppathbackend.branchchampion.services.serviceimpl;

import com.example.emtechelppathbackend.branchchampion.dtos.scholars.ScholarExpenseDto;
import com.example.emtechelppathbackend.branchchampion.entities.Application;
import com.example.emtechelppathbackend.branchchampion.entities.scholars.ScholarExpenses;
import com.example.emtechelppathbackend.branchchampion.repositories.scholarRepo.ScholarExpenseRepo;
import com.example.emtechelppathbackend.branchchampion.services.scholarService.ScholarExpenseService;
/*import com.example.emtechelppathbackend.entities.Scholar;*/
import com.example.emtechelppathbackend.customexceptions.ScholarEducationNotFoundException;
import com.example.emtechelppathbackend.exceptions.UserDetailsNotFoundException;
import com.example.emtechelppathbackend.branchchampion.repositories.ApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScholarExpenseImpl implements ScholarExpenseService {
    private final ApplicationRepository applicationRepository;
    private final ScholarExpenseRepo scholarExpenseRepo;

    public ScholarExpenseImpl(ApplicationRepository applicationRepository, ScholarExpenseRepo scholarExpenseRepo) {
        this.applicationRepository = applicationRepository;
        this.scholarExpenseRepo = scholarExpenseRepo;
    }

    @Override
    public ScholarExpenseDto addScholarExpenses(Long scholarId, ScholarExpenseDto scholarExpenseDto) {
        ScholarExpenses scholarExpenses = mapToEntity(scholarExpenseDto);
        Application application = applicationRepository.findById(scholarId).orElseThrow(()->new UserDetailsNotFoundException("applicant not found"));
       /* scholarExpenses.setScholar(scholar);*/
        scholarExpenses.setApplication(application);
        scholarExpenses.setId(scholarExpenseDto.getId());
//        scholarExpenses.setSchoolName(scholarExpenseDto.getSchoolName());
        scholarExpenses.setForm(scholarExpenseDto.getForm());
        scholarExpenses.setTerm(scholarExpenseDto.getTerm());
        scholarExpenses.setStart_date(scholarExpenseDto.getStart_date());
        scholarExpenses.setShopping(scholarExpenseDto.getShopping());
        scholarExpenses.setSchoolFees(scholarExpenseDto.getSchoolFees());
        scholarExpenses.setTransport(scholarExpenseDto.getTransport());
        scholarExpenses.setUpkeep(scholarExpenseDto.getUpkeep());
        //Integer total = scholarExpenseRepo.findAll().stream().map(ScholarExpenses::getTotal).sum();
        scholarExpenses.setScholarTotalPerTerm(scholarExpenseDto.getTotal());
        //scholarExpenses.setApplication(scholarExpenseDto.getApplication());
        ScholarExpenses newScholarExpenses= scholarExpenseRepo.save(scholarExpenses);
        return mapToDto(newScholarExpenses);
    }

    @Override
    public List<ScholarExpenseDto> findExpensesByApplicationId(Long applicationId) {
        List<ScholarExpenses> scholarExpenses = scholarExpenseRepo.findByApplicationId(applicationId);
        return scholarExpenses.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public ScholarExpenseDto updateScholarExpenses(Long scholarExpensesId, ScholarExpenseDto updatedScholarExpenseDto) {
        ScholarExpenses oldScholarExpenses = scholarExpenseRepo.findById(scholarExpensesId)
                .orElseThrow(()->new ScholarEducationNotFoundException("No scholar expenses found"));// why not reuse a custom exception?

        oldScholarExpenses.setForm(updatedScholarExpenseDto.getForm());
        oldScholarExpenses.setTerm(updatedScholarExpenseDto.getTerm());
//        oldScholarExpenses.setSchoolName(updatedScholarExpenseDto.getSchoolName());
        oldScholarExpenses.setStart_date(updatedScholarExpenseDto.getStart_date());
        oldScholarExpenses.setSchoolFees(updatedScholarExpenseDto.getSchoolFees());
        oldScholarExpenses.setShopping(updatedScholarExpenseDto.getShopping());
        oldScholarExpenses.setTransport(updatedScholarExpenseDto.getTransport());
        oldScholarExpenses.setUpkeep(updatedScholarExpenseDto.getUpkeep());
        oldScholarExpenses.setScholarTotalPerTerm(updatedScholarExpenseDto.getTotal());

        ScholarExpenses updatedScholarExpenses = scholarExpenseRepo.save(oldScholarExpenses);

        return mapToDto(updatedScholarExpenses);
    }

    @Override
    public void deleteScholarExpenseById(Long scholarExpenseId) {
        if (!scholarExpenseRepo.existsById(scholarExpenseId)) {
            throw new ScholarEducationNotFoundException("No scholar education found for ID: " + scholarExpenseId);
        }
        scholarExpenseRepo.deleteById(scholarExpenseId);
    }

    // Get the total expenditure per scholar
    public int getTotalExpenditurePerScholar(Long applicationId) {
        List<ScholarExpenses> expensesList = scholarExpenseRepo.findByApplicationId(applicationId);
        int totalExpenditurePerScholar = 0;
        for (ScholarExpenses expenses : expensesList) {
            totalExpenditurePerScholar += expenses.getScholarTotalPerTerm();
        }
        return totalExpenditurePerScholar;
    }


    // Get the grand expenditure for all scholars
    public int getGrandExpenditureForAllScholars() {
        List<ScholarExpenses> allExpenses = scholarExpenseRepo.findAll();
        int grandExpenditure = 0;
        for (ScholarExpenses expenses : allExpenses) {
            grandExpenditure += expenses.getScholarTotalPerTerm();
        }
        return grandExpenditure;
    }

    private ScholarExpenseDto mapToDto(ScholarExpenses scholarExpenses){
        ScholarExpenseDto scholarExpenseDto = new ScholarExpenseDto();
        scholarExpenseDto.setId(scholarExpenses.getId());
        scholarExpenseDto.setTerm(scholarExpenses.getTerm());
        scholarExpenseDto.setForm(scholarExpenses.getForm());
//        scholarExpenseDto.setSchoolName(scholarExpenses.getSchoolName());
        scholarExpenseDto.setStart_date(scholarExpenses.getStart_date());
        scholarExpenseDto.setShopping(scholarExpenses.getShopping());
        scholarExpenseDto.setTransport(scholarExpenses.getTransport());
        scholarExpenseDto.setSchoolFees(scholarExpenses.getSchoolFees());
        scholarExpenseDto.setUpkeep(scholarExpenses.getUpkeep());
        scholarExpenseDto.setTotal(scholarExpenses.getScholarTotalPerTerm());
        scholarExpenseDto.setApplication(scholarExpenses.getApplication());
        return scholarExpenseDto;
    }
    private ScholarExpenses mapToEntity(ScholarExpenseDto scholarExpenseDto){
        ScholarExpenses scholarExpenses = new ScholarExpenses();
        scholarExpenses.setId(scholarExpenseDto.getId());
        scholarExpenses.setForm(scholarExpenseDto.getForm());
        scholarExpenses.setTerm(scholarExpenseDto.getTerm());
//        scholarExpenses.setSchoolName(scholarExpenseDto.getSchoolName());
        scholarExpenses.setStart_date(scholarExpenseDto.getStart_date());
        scholarExpenses.setShopping(scholarExpenseDto.getShopping());
        scholarExpenses.setSchoolFees(scholarExpenseDto.getSchoolFees());
        scholarExpenses.setTransport(scholarExpenseDto.getTransport());
        scholarExpenses.setUpkeep(scholarExpenseDto.getUpkeep());
        scholarExpenses.setScholarTotalPerTerm(scholarExpenseDto.getTotal());
        scholarExpenses.setApplication(scholarExpenseDto.getApplication());
        return scholarExpenses;
    }
}
