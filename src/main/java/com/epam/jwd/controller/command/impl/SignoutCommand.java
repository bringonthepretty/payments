package com.epam.jwd.controller.command.impl;

import com.epam.jwd.controller.command.api.Command;
import com.epam.jwd.controller.command.commandresponse.CommandResponse;
import com.epam.jwd.controller.command.impl.showpage.ShowEditPaymentCommand;
import com.epam.jwd.service.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;

public class SignoutCommand implements Command {

    private static final Logger logger = LogManager.getLogger(SignoutCommand.class);

    private static final String SIGNIN_PAGE_URL = "/payments?command=show_signin";

    @Override
    public CommandResponse execute(HttpServletRequest request, HttpServletResponse response) {

        logger.info("command " + SignoutCommand.class);

        HttpSession session = request.getSession();
        Collections.list(session.getAttributeNames())
                .forEach(session::removeAttribute);
        return new CommandResponse( request.getContextPath() + SIGNIN_PAGE_URL, true);
    }
}
