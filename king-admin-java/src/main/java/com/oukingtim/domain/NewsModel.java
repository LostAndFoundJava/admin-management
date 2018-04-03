package com.oukingtim.domain;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <br>创建日期：2018/3/24
 *
 * @author JackieChan</b>
 * @version 1.****</b>
 * 新闻表
 */
@TableName("news")
@Data
@EqualsAndHashCode(callSuper = false)
public class NewsModel extends MgrBaseModel<NewsModel> {
    //关联行业
    private String categoryId;
    //新闻内容
    private String content;
    //新闻来源
    private String original;
    //新闻热度
    private Integer hot;
    //新闻热度排名
    private Integer hotLevel;
    //新闻标题
    private  String title;
}
