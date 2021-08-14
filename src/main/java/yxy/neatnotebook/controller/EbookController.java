package yxy.neatnotebook.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yxy.neatnotebook.domain.Ebook;
import yxy.neatnotebook.service.EbookService;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/ebook")
public class EbookController {

    @Resource
    private EbookService ebookService;


    @RequestMapping("/list")
    public List<Ebook> list(){
        return ebookService.list();
    }
    
}
