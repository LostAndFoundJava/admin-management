package com.oukingtim.web.news;

import com.oukingtim.web.vm.ResultVM;
import com.oukingtim.web.vm.SmartPageVM;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <br>创建日期：2018/3/22
 * <br>Copyright 2018　上海华信证券　All Rights Reserved</b>
 *
 * @author JackieChan</b>
 * @version 1.****</b>
 */
@RequestMapping("/news/management")
public class NewsManagementController {

    //新闻查询
    public ResultVM queryNews(@RequestBody SmartPageVM<T> spage){
        return null;
    }
    //新闻编辑
    public ResultVM editNews(){
      return null;
    }
    //新闻发布
    public ResultVM publishNews(){
        return null;
    }
    //新闻上线
    public ResultVM nolineNews(){
        return null;
    }
    //新闻上线
    public ResultVM onlineNews(){
        return null;
    }

}
