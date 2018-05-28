package com.oukingtim.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.oukingtim.domain.AdviceModel;
import com.oukingtim.mapper.AdviceMapper;
import com.oukingtim.service.AdviceService;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class AdviceServiceImpl extends ServiceImpl<AdviceMapper, AdviceModel> implements AdviceService {

    private static Integer STATUS_0 = 0;
    private static Integer STATUS_1 = 1;

    @Override
    public AdviceModel deal(String id) {
        AdviceModel entity = super.selectById(id);
        if (entity != null && !STATUS_1.equals(entity.getStatus())) {
            entity.setStatus(1);
            super.updateById(entity);
        }
        return entity;
    }
}
