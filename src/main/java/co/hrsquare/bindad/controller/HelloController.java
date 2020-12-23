package co.hrsquare.bindad.controller;

import co.hrsquare.bindad.mapper.ITestMapper;
import co.hrsquare.bindad.model.TestModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final ITestMapper testMapper;

    public HelloController(ITestMapper testMapper) {
        this.testMapper = testMapper;
    }

    @RequestMapping("/api/hello")
    public String index() {
        TestModel testModel = testMapper.findById("1");
        return "Hello " + testModel.getName() + "!";
    }

}
