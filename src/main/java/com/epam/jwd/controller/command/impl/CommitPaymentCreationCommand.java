package com.epam.jwd.controller.command.impl;

import com.epam.jwd.controller.command.ApplicationCommand;
import com.epam.jwd.controller.command.api.Command;
import com.epam.jwd.controller.command.commandresponse.CommandResponse;
import com.epam.jwd.service.dto.paymentdto.PaymentDto;
import com.epam.jwd.service.exception.ExceptionCode;
import com.epam.jwd.service.exception.ServiceException;
import com.epam.jwd.service.impl.CreditCardService;
import com.epam.jwd.service.impl.PaymentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;
import java.util.Objects;

public class CommitPaymentCreationCommand implements Command {

    private static final Logger logger = LogManager.getLogger(CommitPaymentCreationCommand.class);

    private static final String USER_PAYMENTS_PAGE_URL = "/payments?command=show_payments&currentPage=1";
    private static final String SHOW_CHECKOUT_PAGE_URL = "/payments?command=show_checkout";
    private static final String SAVE_AND_PAY_ACTION = "checkout";

    @Override
    public CommandResponse execute(HttpServletRequest request, HttpServletResponse response) {

        logger.info("command " + CommitPaymentCreationCommand.class);

        HttpSession session = request.getSession();

        PaymentDto paymentDto = new PaymentDto((Integer) session.getAttribute("id"),
                request.getParameter("destination"),
                Long.valueOf(request.getParameter("price")),
                false,
                null,
                request.getParameter("name"));

        PaymentService service = new PaymentService();

        if (Objects.equals(request.getParameter("action"), SAVE_AND_PAY_ACTION)){

            try {
                PaymentDto createdPayment = service.create(paymentDto);
                request.setAttribute("payment", createdPayment);
                request.setAttribute("creditcards",
                        new CreditCardService().getByUserId(createdPayment.getUserId()));
            } catch (ServiceException e) {
                if (e.getErrorCode() == ExceptionCode.CREDIT_CARD_IS_NOT_FOUND_EXCEPTION_CODE){
                    request.setAttribute("creditcards",
                            null);
                }
                logger.error(e.getErrorCode());
                return new CommandResponse(request.getContextPath() + ApplicationCommand.SHOW_ERROR_PAGE_URL, true);
            }
            return new CommandResponse(request.getContextPath() + SHOW_CHECKOUT_PAGE_URL, false);
        } else {

            try {
                service.create(paymentDto);
            } catch (ServiceException e) {
                logger.error(e.getErrorCode());
            }
            return new CommandResponse(request.getContextPath() + USER_PAYMENTS_PAGE_URL, true);
        }
    }
}
