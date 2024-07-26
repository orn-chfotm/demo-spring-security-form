package com.inflearn.demospringsecurityform.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class LoggingFIlter extends GenericFilterBean {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        StopWatch staStopWatch = new StopWatch();
        staStopWatch.start(((HttpServletRequest)request).getRequestURI());

        chain.doFilter(request, response);

        staStopWatch.stop();
        logger.info(staStopWatch.prettyPrint());
    }
}
