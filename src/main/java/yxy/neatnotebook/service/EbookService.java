package yxy.neatnotebook.service;

import org.springframework.stereotype.Service;
import yxy.neatnotebook.domain.Ebook;
import yxy.neatnotebook.mapper.EbookMapper;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EbookService {
    @Resource
    private EbookMapper ebookMapper;

    public List<Ebook> list(){
        return ebookMapper.selectByExample(null);
    }
}
