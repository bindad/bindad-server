package co.hrsquare.bindad.controller;

import co.hrsquare.bindad.controller.input.CompanyInput;
import co.hrsquare.bindad.controller.input.CompanyPayrollInput;
import co.hrsquare.bindad.controller.input.DepartmentsInput;
import co.hrsquare.bindad.service.CompanyPayrollService;
import co.hrsquare.bindad.service.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(
        value = "/company",
        headers = {"Accept=application/json"},
        produces = {APPLICATION_JSON_VALUE}
)
@Slf4j
public class CompanyController {
    private final CompanyService companyService;
    private final CompanyPayrollService companyPayrollService;

    public CompanyController(final CompanyService companyService,
                             final CompanyPayrollService companyPayrollService) {
        this.companyService = companyService;
        this.companyPayrollService = companyPayrollService;
    }

    @PostMapping("/editCompanyDetails")
    public String editCompanyDetails(CompanyInput input) {
        log.info("Handling editCompanyDetails request for {}", input);
        validate(input);

        String res = companyService.editCompanyDetails(input);

        log.info("DONE Handling editCompanyDetails request for {}", input);
        return res;
    }

    private void validate(CompanyInput input) {
        Objects.requireNonNull(input);
        Objects.requireNonNull(input.getTradingName());
        Objects.requireNonNull(input.getRegisteredName());
        Objects.requireNonNull(input.getAdminNameDetails().getFirstName());
        Objects.requireNonNull(input.getAdminNameDetails().getLastName());
        Objects.requireNonNull(input.getAdminContactDetails().getEmail());
        Objects.requireNonNull(input.getPrimaryAddress().getLine1());
        Objects.requireNonNull(input.getPrimaryAddress().getTown());
        Objects.requireNonNull(input.getPrimaryAddress().getCountry());
        Objects.requireNonNull(input.getPrimaryAddress().getPostCode());
    }

    @PostMapping("/editDepartments")
    public String editDepartments(DepartmentsInput input) {
        log.info("Handling editDepartments request for {}", input);
        validate(input);

        String res = companyService.editDepartments(input);

        log.info("DONE Handling editDepartments request for {}", input);
        return res;
    }

    private void validate(DepartmentsInput input) {
        Objects.requireNonNull(input);
        Objects.requireNonNull(input.getCoFullName());
        Objects.requireNonNull(input.getDepartments());
    }

    @PostMapping("/editPayroll")
    public String editCompanyPayroll(CompanyPayrollInput input) {
        log.info("Handling editCompanyPayroll request for {}", input);
        validate(input);

        String res = companyPayrollService.editPayroll(input);

        log.info("DONE Handling editCompanyPayroll request for {}", input);
        return res;
    }

    private void validate(CompanyPayrollInput input) {
        Objects.requireNonNull(input.getCompanyFullName());
        if (input.getPensionSchemes() != null && !CollectionUtils.isEmpty(input.getPensionSchemes().getPensionSchemeInputs())) {
            if (input.getPensionSchemes().getCompanyFullName() == null) {
                input.getPensionSchemes().setCompanyFullName(input.getCompanyFullName());
            }

            input.getPensionSchemes().getPensionSchemeInputs().forEach(p -> {
                Objects.requireNonNull(p.getName());
                Objects.requireNonNull(p.getProvider());
            });
        }

    }


}
