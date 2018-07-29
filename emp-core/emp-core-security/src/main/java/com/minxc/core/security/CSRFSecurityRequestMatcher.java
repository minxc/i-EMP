package com.minxc.core.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @ClassName CSRFSecurityRequestMatcher
 * @Description TODO
 * @Author Xianchang.min
 * @Date 2018/7/29 15:02
 * @Version 1.0
 **/
@Slf4j
public class CSRFSecurityRequestMatcher implements RequestMatcher {

    private Pattern allowedMethods = Pattern.compile("^(GET|HEAD|TRACE|OPTIONS)$");
    /**
     * 需要排除的url列表
     */
    private List<String> execludedUrls;

    @Override
    public boolean matches(HttpServletRequest request) {
        if (execludedUrls != null && execludedUrls.size() > 0) {
            String servletPath = request.getServletPath();
            for (String url : execludedUrls) {
                if (servletPath.contains(url)) {
                    log.info("++++" + servletPath);
                    return false;
                }
            }
        }
        return !allowedMethods.matcher(request.getMethod()).matches();
    }

    public List<String> getExecludedUrls() {
        return execludedUrls;
    }

    public void setExecludedUrls(List<String> execludeUrls) {
        this.execludedUrls = execludeUrls;
    }
}
