package yxy.neatnotebook.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yxy.neatnotebook.domain.Demo;
import yxy.neatnotebook.service.DemoService;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Resource
    private DemoService demoService;


    @RequestMapping("/list")
    public List<Demo> list(){
        return demoService.list();
    }
    
}
