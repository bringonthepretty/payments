package com.epam.jwd.controller.command.impl.action;

import com.epam.jwd.controller.command.api.Command;
import com.epam.jwd.controller.command.commandresponse.CommandResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChangeLanguageCommand implements Command {

    private static final Logger logger = LogManager.getLogger(ChangeLanguageCommand.class);
    private static final String LANG_STRING = "lang";

    private static final String REFERER_NAME = "referer";

    @Override
    public CommandResponse execute(HttpServletRequest request, HttpServletResponse response) {
        logger.info("command " + ChangeLanguageCommand.class);
        HttpSession session = request.getSession();
        session.setAttribute(LANG_STRING, request.getParameter(LANG_STRING));
        return new CommandResponse(request.getContextPath() + request.getHeader(REFERER_NAME), true);
    }
}
