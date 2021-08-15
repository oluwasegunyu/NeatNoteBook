package yxy.neatnotebook.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yxy.neatnotebook.req.EbookReq;
import yxy.neatnotebook.resp.CommonResp;
import yxy.neatnotebook.resp.EbookResp;
import yxy.neatnotebook.service.EbookService;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/ebook")
public class EbookController {

    @Resource
    private EbookService ebookService;


    @RequestMapping("/list")
    public CommonResp list(EbookReq req){
        CommonResp<List<EbookResp>> resp = new CommonResp<>();
        List<EbookResp>  list = ebookService.list(req);
        resp.setContent(list);
        return resp;
    }
    
}