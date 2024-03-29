package com.epam.jwd.controller.filter;


import com.epam.jwd.controller.command.ApplicationCommand;
import com.epam.jwd.dao.model.user.Role;
import com.epam.jwd.service.dto.userdto.UserDto;
import com.epam.jwd.service.exception.ServiceException;
import com.epam.jwd.service.impl.UserService;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@WebFilter("/*")
public class AccessFilter implements Filter {

    private static final Logger logger = LogManager.getLogger(AccessFilter.class);

    String ID_ATTRIBUTE_NAME = "id";

    private static final String ROLE_ATTRIBUTE_NAME = "role";
    private static final String COMMAND_PARAMETER_NAME = "command";

    private static final String SIGNIN_PAGE_URL = "/payments?command=show_signin";

    private Boolean isPassed = false;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.info("filter " + AccessFilter.class);
        if (request instanceof HttpServletRequest){
            List<Role> roles = ApplicationCommand.getByString(request.getParameter(COMMAND_PARAMETER_NAME))
                    .getAllowedRoles();
            if (!roles.isEmpty()){
                HttpSession session = ((HttpServletRequest) request).getSession();
                isPassed = roles.get(0) == session.getAttribute(ROLE_ATTRIBUTE_NAME) && !Objects.isNull(session.getAttribute(ID_ATTRIBUTE_NAME));
                if (roles.get(0) == Role.CUSTOMER){
                    UserService service = new UserService();
                    try {
                        UserDto userDto = service.getById((Integer) session.getAttribute(ID_ATTRIBUTE_NAME));
                        if (!userDto.getActive()){
                            isPassed = false;
                        }
                    } catch (ServiceException e) {
                        logger.error(e.getErrorCode());
                        isPassed = false;
                    }
                }
            } else {
                isPassed = true;
            }
        }
        if (isPassed){
            forwardToNextFilter(request, response, chain);
        } else {
            forwardToLoginPage(request, response);
        }
    }

    private void forwardToNextFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        logger.info("is signed in filter passed");
        chain.doFilter(request, response);
    }

    private void forwardToLoginPage(ServletRequest request, ServletResponse response) throws IOException {
        logger.info("required data is not exists in session");
        String url = ((HttpServletRequest) request).getContextPath() + SIGNIN_PAGE_URL;
        ((HttpServletResponse)response).sendRedirect(url);
    }
}