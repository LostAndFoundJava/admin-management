package com.oukingtim.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.oukingtim.domain.NewsModel;
import com.oukingtim.mapper.NewsEditonMapper;
import com.oukingtim.service.NewsEditionService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <br>创建日期：2018/3/24
 *
 * @author JackieChan</b>
 * @version 1.****</b>
 */
@Service
public class NewsEditionServiceImpl extends ServiceImpl<NewsEditonMapper,NewsModel> implements NewsEditionService {
    @Autowired
    private NewsEditonMapper newsEditonMapper;

    @Override
    public List<NewsModel> selectAll() {
        return newsEditonMapper.selectAll();
    }
}
