package com.oukingtim.web;

import com.baomidou.mybatisplus.plugins.Page;
import com.oukingtim.domain.AdviceModel;
import com.oukingtim.service.AdviceService;
import com.oukingtim.util.ExportExcelUtil;
import com.oukingtim.web.vm.ResultVM;
import com.oukingtim.web.vm.SmartPageVM;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <br>创建日期：2018/4/1
 */
@RestController
@Api(description = "用户咨询信息execel导入")
@RequestMapping("/mgr/advice/management")
public class AdviceController extends MgrBaseController<AdviceService, AdviceModel> {

    private static int NUMBER = 10000000;

    private static Logger LOGGER = LoggerFactory.getLogger(AdviceController.class);

    @RequestMapping(value = "/export", method = RequestMethod.POST)
    public ResultVM export(@RequestBody SmartPageVM<AdviceModel> spage, HttpServletRequest request, HttpServletResponse response) {
        spage.getPagination().setStart(0);
        spage.getPagination().setNumber(NUMBER);
        ResultVM resultVM = super.getSmartData(spage);
        Page<AdviceModel> page = (Page<AdviceModel>) resultVM.getResult();
        List<AdviceModel> adviceModels = page.getRecords();
        Object path = "";
        if (!CollectionUtils.isEmpty(adviceModels)) {
            path = ExportExcelUtil.exportAdviceExcel(adviceModels);
            if (path == null) {
                return ResultVM.error(500, "文件导出失败!");
            }
        }
        return ResultVM.ok(path);

    }

    @RequestMapping(value = "/deal/{id}",method = RequestMethod.GET)
    public AdviceModel deal(@PathVariable String id) {
        AdviceModel t = service.deal(id);
        return t;
    }

}
