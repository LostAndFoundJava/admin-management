package com.oukingtim.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.oukingtim.domain.AboutUsModel;
import com.oukingtim.mapper.AboutUsMapper;
import com.oukingtim.service.AboutUsService;
import org.springframework.stereotype.Service;

/**
 * <br>创建日期：2018/3/31
 *
 * @author JackieChan</b>
 * @version 1.****</b>
 */
@Service
public class AboutUsServiceImpl extends ServiceImpl<AboutUsMapper, AboutUsModel> implements AboutUsService {

    @Override
    public AboutUsModel getAboutUsInfo() {
        AboutUsModel aboutUsModel;
        aboutUsModel = super.selectOne(new EntityWrapper<>());
        return aboutUsModel;
    }
}
