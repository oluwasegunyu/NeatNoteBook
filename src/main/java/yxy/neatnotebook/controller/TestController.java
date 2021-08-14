package yxy.neatnotebook.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yxy.neatnotebook.domain.Test;
import yxy.neatnotebook.service.TestService;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class TestController {

    @Resource
    private TestService testService;

    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }

    @RequestMapping("/list")
    public List<Test> list(){
        return testService.list();
    }
}
