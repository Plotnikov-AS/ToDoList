package main;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import response.Case;

import java.util.List;

@RestController
public class CaseController {

    @GetMapping("/cases/")
    public List<Case> listAllCases(){
        return null;
    }

    @PostMapping("/cases/")
    public int addCase(Case newCase){
        return newCase.getId();
    }

    @GetMapping("/cases/{id}")
    public ResponseEntity getCase(int id){
        return null;
    }
}
