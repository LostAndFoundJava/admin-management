package com.oukingtim.web;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.oukingtim.domain.SrcModel;
import com.oukingtim.domain.SysRole;
import com.oukingtim.domain.SysUser;
import com.oukingtim.service.SrcService;
import com.oukingtim.service.SysUserService;
import com.oukingtim.web.vm.ResultVM;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * Created by oukingtim
 */
@RestController
@RequestMapping("/sys/src")
public class SysSrcController extends BaseController<SrcService,SrcModel>{

    /**
     * 获取渠道集合
     */
    @GetMapping("/getlist")
    public ResultVM getList() {
        List<SrcModel> srcModelList = service.selectList(new EntityWrapper<>());
        return ResultVM.ok(srcModelList);
    }
}
