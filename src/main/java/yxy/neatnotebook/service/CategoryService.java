package yxy.neatnotebook.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import yxy.neatnotebook.domain.Category;
import yxy.neatnotebook.domain.CategoryExample;
import yxy.neatnotebook.mapper.CategoryMapper;
import yxy.neatnotebook.req.CategoryQueryReq;
import yxy.neatnotebook.req.CategorySaveReq;
import yxy.neatnotebook.resp.CategoryQueryResp;
import yxy.neatnotebook.resp.PageResp;
import yxy.neatnotebook.util.SnowFlake;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    private static final Logger LOG = LoggerFactory.getLogger(CategoryService.class);

    @Resource
    private CategoryMapper CategoryMapper;

    @Resource
    private SnowFlake snowFlake;

    public PageResp<CategoryQueryResp> list(CategoryQueryReq req){
        CategoryExample CategoryExample = new CategoryExample();
        CategoryExample.Criteria criteria = CategoryExample.createCriteria();
        PageHelper.startPage(req.getPage(), req.getSize());
        List<Category> CategoryList = CategoryMapper.selectByExample(CategoryExample);

        PageInfo<Category> pageInfo = new PageInfo<>(CategoryList);
        LOG.info("总行数: {}", pageInfo.getTotal());
        LOG.info("总页数: {}", pageInfo.getPages());

        List<CategoryQueryResp> respList = new ArrayList<>();
        for(Category Category: CategoryList){
            CategoryQueryResp CategoryResp = new CategoryQueryResp();
            BeanUtils.copyProperties(Category, CategoryResp);
            respList.add(CategoryResp);
        }

        PageResp<CategoryQueryResp>  pageResp = new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(respList);

        return pageResp;
    }

    /**
     * 保存
      */
    public void save(CategorySaveReq req){
        Category Category = new Category();
        BeanUtils.copyProperties(req, Category);
        if(ObjectUtils.isEmpty(req.getId())){
            //Insert new note
            Category.setId(snowFlake.nextId());
            CategoryMapper.insert(Category);
        }else {
            CategoryMapper.updateByPrimaryKey(Category);
        }
    }


    /**
     * 删除
     */
    public void delete(Long id){
        CategoryMapper.deleteByPrimaryKey(id);
    }
}
