package yxy.neatnotebook.service;

import org.springframework.stereotype.Service;
import yxy.neatnotebook.domain.Demo;
import yxy.neatnotebook.mapper.DemoMapper;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DemoService {
    @Resource
    private DemoMapper demoMapper;

    public List<Demo> list(){
        return demoMapper.selectByExample(null);
    }
}
