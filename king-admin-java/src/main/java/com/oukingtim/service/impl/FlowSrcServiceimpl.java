package com.oukingtim.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.oukingtim.domain.Exhibition;
import com.oukingtim.domain.FlowSrcModel;
import com.oukingtim.domain.RegionData;
import com.oukingtim.domain.VisaModel;
import com.oukingtim.mapper.FlowSrcMapper;
import com.oukingtim.service.FlowSrcService;
import com.oukingtim.service.MgrExhibitionService;
import com.oukingtim.service.RegionDataService;
import com.oukingtim.util.ReadExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <br>创建日期：2018/3/31
 *
 * @author JackieChan</b>
 * @version 1.****</b>
 */
@Service
public class FlowSrcServiceimpl extends ServiceImpl<FlowSrcMapper, FlowSrcModel> implements FlowSrcService {

    @Autowired
    private ReadExcel readExcel;

    @Autowired
    private MgrExhibitionService mgrExhibitionService;
    @Override
    public List<FlowSrcModel> importExcel(String fileName, MultipartFile file) {
        List<FlowSrcModel> list = readExcel.gotExcelInfo(fileName, file);
        return list;
    }


    @Override
    public Page<FlowSrcModel> selectPage(Page<FlowSrcModel> page, Wrapper<FlowSrcModel> wrapper) {
        super.selectPage(page,wrapper);
        if(page != null && !CollectionUtils.isEmpty(page.getRecords())) {
            for (FlowSrcModel flowSrcModel : page.getRecords()) {
                if("0".equals(flowSrcModel.getSrcType())) {
                   List<Exhibition> list =  mgrExhibitionService.selectTitleById();
                   if(list !=null && !list.isEmpty()){
                       for(Exhibition e:list){
                           if(flowSrcModel.getExhibition() != null&&!flowSrcModel.getExhibition().isEmpty()) {
                               if (flowSrcModel.getExhibition().equals(e.getId())) {
                                   flowSrcModel.setExhibition(e.getTitle());
                                   break;
                               }
                           }
                       }
                   }
                }
            }
        }
        return page;
    }
}
