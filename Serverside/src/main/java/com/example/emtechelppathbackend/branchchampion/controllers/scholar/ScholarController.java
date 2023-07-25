/*
package com.example.emtechelppathbackend.branchchampion.controllers.scholar;

import lombok.AllArgsConstructor;
import com.example.emtechelppathbackend.entities.Scholar;
import com.example.emtechelppathbackend.branchchampion.services.scholarService.ScholarService;
import com.example.emtechelppathbackend.dtos.ScholarsDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/scholars")
public class ScholarController {

    private ScholarService scholarService;

    // build create User REST API
    @PostMapping("/create")
    public ResponseEntity<ScholarsDTO> createScholar(@RequestBody ScholarsDTO scholar){
        return new ResponseEntity<>(scholarService.createScholar(scholar), HttpStatus.CREATED);
    }

    // Build Get All Scholars REST API
    @GetMapping("/all")
    public ResponseEntity<List<Scholar>> viewAllScholars(){
        List <Scholar> scholars = scholarService.viewAllScholars();
        return new ResponseEntity<>(scholars, HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<ScholarsDTO>> findScholarByName(@RequestParam("name")String name) {
        return new ResponseEntity<>(scholarService.findScholarByName(name), HttpStatus.OK);
    }

    @GetMapping("/{year}")
    public ResponseEntity<List<ScholarsDTO>> findScholarByYearOfApplication(@RequestParam("year")String year) {
        return new ResponseEntity<>(scholarService.findScholarByYearOfApplication(year), HttpStatus.OK);
    }

    @GetMapping("/{branch}")
    public ResponseEntity<List<ScholarsDTO>> findScholarByBranch(@RequestParam("branch")String branch) {
        return new ResponseEntity<>(scholarService.findScholarByBranch(branch), HttpStatus.OK);
    }

    @GetMapping("/{status}")
    public ResponseEntity<List<ScholarsDTO>> findScholarByStatus(@RequestParam("status")String status) {
        return new ResponseEntity<>(scholarService.findScholarByStatus(status), HttpStatus.OK);
    }

    @PutMapping("/{scholarId}/update")
    public ResponseEntity<ScholarsDTO>updateScholar(@PathVariable(value = "scholarId")Long scholarId, @RequestBody ScholarsDTO scholarDto){
        ScholarsDTO updatedScholar = scholarService.updateScholar(scholarId, scholarDto);
        return new ResponseEntity<>(updatedScholar,HttpStatus.OK);
    }
    @DeleteMapping("/{scholarId}/delete")
    public ResponseEntity<?> deleteScholar(@PathVariable(value = "scholarId")Long scholarId){
        scholarService.deleteScholar(scholarId);
        return new ResponseEntity<>("Scholar deleted successfully",HttpStatus.OK);
    }

}
*/
