package com.ascending.training.filter;

import com.ascending.training.model.User;
import com.ascending.training.service.JWTService;
import com.ascending.training.service.UserService;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@WebFilter(filterName = "securityFilter", urlPatterns = {"/*"}, dispatcherTypes = {DispatcherType.REQUEST})//servlet的annotation
public class SecurityFilter implements Filter {
    @Autowired
    private JWTService jwtService;
    @Autowired
    private UserService userService;
    private Logger logger = LoggerFactory.getLogger(getClass());
    private Set<String> AUTH_URI= new HashSet<>(Arrays.asList("/auth", "/auth/"));//using the HashSet constructor;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
       //1. extract Authorization header
       //2.remove Bearer to get token
       //3. decrypt token to get claim
       //4. verify username information in our database from claim
       //5. doFilter dispatch to controller
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        int statusCode = authorization(req);
        if(statusCode == HttpServletResponse.SC_ACCEPTED) filterChain.doFilter(servletRequest,servletResponse);
        else ((HttpServletResponse)servletResponse).sendError(statusCode);

    }

    private  int authorization(HttpServletRequest request)//extract Authorization header
    {
        int statusCode = HttpServletResponse.SC_UNAUTHORIZED;
        String uri = request.getRequestURI();
        String verb = request.getMethod();
       // if(uri.equalsIgnoreCase(AUTH_URI)) return HttpServletResponse.SC_ACCEPTED;//ignore掉一个login的uri
        if(AUTH_URI.contains(uri)) return HttpServletResponse.SC_ACCEPTED;//ignore掉一个login的uri

        try{
            String token = request.getHeader("Authorization").replaceAll("^(.*?) ","");
            //抽取header中的token并把个别符号去掉
            if(token == null || token.isEmpty()) return statusCode;

            Claims claims = jwtService.decryptJwtToken(token);
            //TODO pass username and check role
            if(claims.getId()!=null){
                User u = userService.getById(Long.valueOf(claims.getId()));
                if(u==null) return statusCode;
                //statusCode = HttpServletResponse.SC_ACCEPTED;
            }


            String allowedResources = "";
            switch(verb) {
                case "GET"    : allowedResources = (String)claims.get("allowedReadResources");   break;
                case "POST"   : allowedResources = (String)claims.get("allowedCreateResources"); break;
                case "PUT"    : allowedResources = (String)claims.get("allowedUpdateResources"); break;
                case "DELETE" : allowedResources = (String)claims.get("allowedDeleteResources"); break;
                case "PATCH" : allowedResources = (String)claims.get("allowedUpdateResources"); break;
            }
//
            for (String s : allowedResources.split(",")) {
                if (s!=""&&uri.trim().toLowerCase().startsWith(s.trim().toLowerCase())) {
                                                    //trim: remove leading and trailing spaces
                    statusCode = HttpServletResponse.SC_ACCEPTED;
                    break;
                }
            }
//            allowedResources.split(",").fo(s->{});
        }
        catch (Exception e) {
            logger.error("can't verify the token",e);
        }
        return statusCode;
    }


    @Override
    public void destroy() {

    }
}
