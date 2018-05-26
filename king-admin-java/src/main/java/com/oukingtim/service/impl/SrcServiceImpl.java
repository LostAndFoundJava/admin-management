package com.oukingtim.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.oukingtim.domain.SrcModel;
import com.oukingtim.mapper.SrcMapper;
import com.oukingtim.service.SrcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 */
@Service
public class SrcServiceImpl extends ServiceImpl<SrcMapper, SrcModel> implements SrcService {
    private static final Logger logger = LoggerFactory.getLogger(SrcServiceImpl.class);

    @Override
    public boolean insert(SrcModel t){
        SrcModel entity = super.selectOne(new EntityWrapper(new SrcModel(t.getSrc())));
        if(entity != null){
            return false;
        }
        return super.insert(t);
    }
}
