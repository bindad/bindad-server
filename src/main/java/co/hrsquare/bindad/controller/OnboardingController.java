package co.hrsquare.bindad.controller;

import co.hrsquare.bindad.model.organisation.Organisation;
import co.hrsquare.bindad.model.employee.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(
        value = "/onboard",
        headers = {"Accept=application/json"},
        produces = {APPLICATION_JSON_VALUE}
)
@Slf4j
public class OnboardingController {

    @PostMapping("/newDemoClient")
    public String newClientDemo() {
        return "SUCCESS";
    }

    @PostMapping("/newLiveClient")
    public String newLiveClient() {
        return "SUCCESS";
    }

    @PostMapping("/upgradeClient")
    public String upgradeClient() {
        return "SUCCESS";
    }

    @PostMapping("/removeClient")
    public String removeClient() {
        return "SUCCESS";
    }

    @PostMapping("/newCompany")
    public String newCompany(Organisation organisation) {
        return "SUCCESS";
    }

    @PostMapping("/newStaff")
    public String newEmployee(Employee employee) {
        return "SUCCESS";
    }


}
