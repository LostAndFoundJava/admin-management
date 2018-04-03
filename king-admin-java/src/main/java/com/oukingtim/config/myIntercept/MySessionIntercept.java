package com.oukingtim.config.myIntercept;

import com.alibaba.fastjson.JSONObject;
import com.oukingtim.web.vm.ResultVM;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.PrintWriter;

/**
 * Created by chenjian on 2018/4/3.
 */
@Slf4j
public class MySessionIntercept extends AccessControlFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request, response);

        if (!subject.isAuthenticated() && !subject.isRemembered()) {
            //如果没有登录,AJAX
            PrintWriter out = null;
            try {
                response.setCharacterEncoding("UTF-8");//设置编码
                response.setContentType("application/json");//设置返回类型
                out = response.getWriter();
                out.println(JSONObject.toJSONString(new ResultVM().error(401, "为登录")).toString());//输出
            } catch (Exception e) {
                log.error("自定义拦截器异常");
            } finally {
                if (null != out) {
                    out.flush();
                    out.close();
                }
            }
            //直接返回，不用继续下面的拦截器
            return false;
        }
        //已登录
        return true;
    }

}
