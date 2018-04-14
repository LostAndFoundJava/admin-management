package com.oukingtim.web;

import com.oukingtim.domain.Exhibition;
import com.oukingtim.service.MgrExhibitionService;
import com.oukingtim.web.vm.ResultVM;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by xufan on 2018/03/18.
 */
@RestController
@Api(description = "展会管理API")
@RequestMapping("/api/mgr/exhibition/management")
public class MgrExhibitionController extends MgrBaseController<MgrExhibitionService, Exhibition> {


    private static Logger logger = LoggerFactory.getLogger(MgrExhibitionController.class);


    @ApiOperation(value = "获取展会列表", notes = "根据行业ids获取展会列表")
    @RequestMapping(value = "/exhibitions", method = RequestMethod.GET)
    public ResultVM getExhibitionsByCategoryId(@Valid @RequestParam("categoryIds") List<String> categoryIds) {
        List<Exhibition> exhibitions = service.getExhibitionsByCategoryId(categoryIds);
        return ResultVM.ok(exhibitions);
    }
}
