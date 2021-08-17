package yxy.neatnotebook.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import yxy.neatnotebook.domain.Ebook;
import yxy.neatnotebook.domain.EbookExample;
import yxy.neatnotebook.mapper.EbookMapper;
import yxy.neatnotebook.req.EbookQueryReq;
import yxy.neatnotebook.req.EbookSaveReq;
import yxy.neatnotebook.resp.EbookQueryResp;
import yxy.neatnotebook.resp.PageResp;
import yxy.neatnotebook.util.SnowFlake;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class EbookService {

    private static final Logger LOG = LoggerFactory.getLogger(EbookService.class);

    @Resource
    private EbookMapper ebookMapper;

    @Resource
    private SnowFlake snowFlake;

    public PageResp<EbookQueryResp> list(EbookQueryReq req){
        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        if(!ObjectUtils.isEmpty(req.getName())){
            criteria.andNameLike("%"+ req.getName() + "%");
        }
        PageHelper.startPage(req.getPage(), req.getSize());
        List<Ebook> ebookList = ebookMapper.selectByExample(ebookExample);

        PageInfo<Ebook> pageInfo = new PageInfo<>(ebookList);
        LOG.info("总行数: {}", pageInfo.getTotal());
        LOG.info("总页数: {}", pageInfo.getPages());

        List<EbookQueryResp> respList = new ArrayList<>();
        for(Ebook ebook: ebookList){
            EbookQueryResp ebookResp = new EbookQueryResp();
            BeanUtils.copyProperties(ebook, ebookResp);
            respList.add(ebookResp);
        }

        PageResp<EbookQueryResp>  pageResp = new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(respList);

        return pageResp;
    }

    /**
     * 保存
      */
    public void save(EbookSaveReq req){
        Ebook ebook = new Ebook();
        BeanUtils.copyProperties(req, ebook);
        if(ObjectUtils.isEmpty(req.getId())){
            //Insert new note
            ebook.setId(snowFlake.nextId());
            ebookMapper.insert(ebook);
        }else {
            ebookMapper.updateByPrimaryKey(ebook);
        }
    }


    /**
     * 删除
     */
    public void delete(Long id){
        ebookMapper.deleteByPrimaryKey(id);
    }
}
