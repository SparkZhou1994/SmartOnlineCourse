package spark.course.security.browser;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import spark.course.error.BusinessException;
import spark.course.error.EmBusinessError;
import spark.course.response.CommonReturnType;
import spark.course.security.SecurityProperties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName SecurityController
 * @Description TODO
 * @Author Spark
 * @Date 2/5/2019 8:33 PM
 * @Version 1.0
 **/
@RestController
public class SecurityController {
    private RequestCache requestCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    @Autowired
    private SecurityProperties securityProperties;
    @RequestMapping("/authentication/require")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public CommonReturnType requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws Exception {
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null) {
            String targetUrl = savedRequest.getRedirectUrl();
            if (StringUtils.endsWithIgnoreCase(targetUrl, ".html")) {
                try {
                    redirectStrategy.sendRedirect(request, response, securityProperties.getBrowser().getLoginPage());
                } catch (IOException e) {
                    throw new BusinessException(EmBusinessError.AUTHORIZED_REDIRECT);
                }
            }
        }
        return CommonReturnType.create(EmBusinessError.UNAUTHORIZED, "fail");
    }
}
