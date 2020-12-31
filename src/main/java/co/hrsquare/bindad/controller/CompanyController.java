package co.hrsquare.bindad.controller;

import co.hrsquare.bindad.controller.input.DepartmentsInput;
import co.hrsquare.bindad.service.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(
        value = "/co",
        headers = {"Accept=application/json"},
        produces = {APPLICATION_JSON_VALUE}
)
@Slf4j
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping("/newDepartments")
    public String newDepartments(DepartmentsInput input) {
        log.info("Handling newDepartments request for {}", input);
        validate(input);

        String res = companyService.createOrUpdateDepartments(input);

        log.info("DONE Handling newDepartments request for {}", input);
        return res;
    }

    private void validate(DepartmentsInput input) {
        Objects.requireNonNull(input);
        Objects.requireNonNull(input.getCoFullName());
        Objects.requireNonNull(input.getDepartments());
    }


}
