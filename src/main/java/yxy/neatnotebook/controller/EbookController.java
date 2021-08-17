package yxy.neatnotebook.controller;

import org.springframework.web.bind.annotation.*;
import yxy.neatnotebook.req.EbookQueryReq;
import yxy.neatnotebook.req.EbookSaveReq;
import yxy.neatnotebook.resp.CommonResp;
import yxy.neatnotebook.resp.EbookQueryResp;
import yxy.neatnotebook.resp.PageResp;
import yxy.neatnotebook.service.EbookService;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/ebook")
public class EbookController {

    @Resource
    private EbookService ebookService;


    @GetMapping("/list")
    public CommonResp list(@Valid EbookQueryReq req){
        CommonResp<PageResp<EbookQueryResp>> resp = new CommonResp<>();
        PageResp<EbookQueryResp> list = ebookService.list(req);
        resp.setContent(list);
        return resp;
    }


    @PostMapping("/save")
    public CommonResp save(@RequestBody EbookSaveReq req){
        CommonResp resp = new CommonResp<>();
        ebookService.save(req);
        return resp;
    }


    @DeleteMapping("/delete/{id}")
    public CommonResp delete(@PathVariable Long id){
        CommonResp resp = new CommonResp<>();
        ebookService.delete(id);
        return resp;
    }
    
}
