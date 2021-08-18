package yxy.neatnotebook.controller;

import org.springframework.web.bind.annotation.*;
import yxy.neatnotebook.req.CategoryQueryReq;
import yxy.neatnotebook.req.CategorySaveReq;
import yxy.neatnotebook.resp.CommonResp;
import yxy.neatnotebook.resp.CategoryQueryResp;
import yxy.neatnotebook.resp.PageResp;
import yxy.neatnotebook.service.CategoryService;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;


    @GetMapping("/list")
    public CommonResp list(@Valid CategoryQueryReq req){
        CommonResp<PageResp<CategoryQueryResp>> resp = new CommonResp<>();
        PageResp<CategoryQueryResp> list = categoryService.list(req);
        resp.setContent(list);
        resp.setSuccess(true);
        return resp;
    }

    @GetMapping("/all")
    public CommonResp all(){
        CommonResp<List<CategoryQueryResp>> resp = new CommonResp<>();
        List<CategoryQueryResp> list = categoryService.all();
        resp.setContent(list);
        resp.setSuccess(true);
        return resp;
    }


    @PostMapping("/save")
    public CommonResp save(@RequestBody @Valid CategorySaveReq req){
        CommonResp resp = new CommonResp<>();
        categoryService.save(req);
        resp.setSuccess(true);
        return resp;
    }


    @DeleteMapping("/delete/{id}")
    public CommonResp delete(@PathVariable Long id){
        CommonResp resp = new CommonResp<>();
        categoryService.delete(id);
        resp.setSuccess(true);
        return resp;
    }
    
}
