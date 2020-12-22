package co.hrsquare.bindad.controller;

import co.hrsquare.bindad.model.Employee;
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

    @PostMapping("/new")
    public String newEmployee(Employee employee) {
        return "SUCCESS";
    }


}
