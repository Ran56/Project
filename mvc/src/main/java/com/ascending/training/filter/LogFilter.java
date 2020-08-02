package com.ascending.training.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;


@WebFilter(filterName = "logFilter", urlPatterns = {"/*"}, dispatcherTypes = {DispatcherType.REQUEST})//servlet的annotation
//让filter知道该class是一个filter    //   filterName = "logFilter"是一个instance name，不一定需要和class的name同名
//系统扫描该annotation后会自动实例化 Filter logFilter = new LogFilter();这里面的logFilter就是filterName = "logFilter"中的logFilter。
//urlPatterns = {"/*"}表示在/*下的uri请求都会调用filter
public class LogFilter implements Filter {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private final List<String> excludedWords = Arrays.asList("newPasswd", "confirmPasswd", "passwd", "password");
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss,SSS");
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override//doFilter主要是做pre-processing和post-processing
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Instant startTime = Instant.now(); // pre-processing
        HttpServletRequest req = (HttpServletRequest) servletRequest;//一切的信息都是从servletRequest中抽出来的
        String logInfo = logInfo(req);
        filterChain.doFilter(servletRequest,servletResponse);//把这个请求往下丢，到下一个filter里。保证了请求不断通过陆陆续续的filter
                                                            //直到送到第n个filter后，送到controller
        // logger.info 中的Instant.now().getEpochSecond()是post-processing
        //注意！！！！！该步骤之前是pre-processing， 该步骤之后是post-processing
        //如果把该步骤注释掉则请求将会终止于此不会送到controller里
        logger.info(logInfo.replace("responseTime",String.valueOf(Instant.now().getEpochSecond()-startTime.getEpochSecond())));

    }
    @Override
    public void destroy() {

    }

    private boolean isIgnoredWord(String word, List<String> excludedWords) {//该方法是为了把不想在log中显示的信息ignore掉比如请求
                                                                            //过程中输入的密码等
        for (String excludedWord : excludedWords) {
            if (word.toLowerCase().contains(excludedWord)) return true;
        }
        return false;
    }


    private String logInfo(HttpServletRequest req) {//该方法会把所有的请求整理一遍，整理成想要的输入输出格式
//2020-07-02 22:39:46.568  INFO 6119 --- [nio-8080-exec-1] com.ascending.training.filter.LogFilter  : | 2020-07-02 22:39:46,501
// | 0:0:0:0:0:0:0:1 | POST | /auth | 18D2E7DA6C9B69EE2901A0A9CEAF32F2 | 0 ms | username=[admin]
// "|" 为 append(" | ")，2020-07-02 22:39:46,501为.append(formatter.format(startDateTime)).append(" | ")， 0:0:0:0:0:0:0:1为userIP，
// POST为httpMethod，/auth为.append(requestURL).append(" | ")
//18D2E7DA6C9B69EE2901A0A9CEAF32F2为.append(sessionID).append(" | ")等
        String formData = null;
        String httpMethod = req.getMethod();
        LocalDateTime startDateTime = LocalDateTime.now();
        String requestURL = req.getRequestURI();
        String userIP = req.getRemoteHost();
        String sessionID = req.getSession().getId();
        Enumeration<String> parameterNames = req.getParameterNames();
        List<String> parameters = new ArrayList();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            if (isIgnoredWord(paramName, excludedWords)) continue;
            String paramValues = Arrays.asList(req.getParameterValues(paramName)).toString();
            parameters.add(paramName + "=" + paramValues);
        }
        if (!parameters.isEmpty()) {
            formData = parameters.toString().replaceAll("^.|.$", "");
        }
        return  new StringBuilder("| ")
                .append(formatter.format(startDateTime)).append(" | ")
                .append(userIP).append(" | ")
                .append(httpMethod).append(" | ")
                .append(requestURL).append(" | ")
                .append(sessionID).append(" | ")
                .append("responseTime ms").append(" | ")
                .append(formData).toString();
    }
}
