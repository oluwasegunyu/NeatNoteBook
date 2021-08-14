package yxy.neatnotebook.service;

import org.springframework.stereotype.Service;
import yxy.neatnotebook.domain.Test;
import yxy.neatnotebook.mapper.TestMapper;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TestService {
    @Resource
    private TestMapper testMapper;

    public List<Test> list(){
        return testMapper.list();
    }
}
