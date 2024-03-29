package com.epam.jwd.controller.command.impl.action;

import com.epam.jwd.controller.command.ApplicationCommand;
import com.epam.jwd.controller.command.api.Command;
import com.epam.jwd.controller.command.commandresponse.CommandResponse;
import com.epam.jwd.service.dto.paymentdto.PaymentDto;
import com.epam.jwd.service.exception.ServiceException;
import com.epam.jwd.service.impl.PaymentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class CommitPaymentCreationCommand implements Command {

    private static final Logger logger = LogManager.getLogger(CommitPaymentCreationCommand.class);

    private static final String USER_PAYMENTS_PAGE_URL = "/payments?command=show_payments&currentPage=1";
    private static final String SHOW_CHECKOUT_PAGE_URL = "/payments?command=show_checkout";
    private static final String SAVE_AND_PAY_ACTION = "checkout";

    private static final String DESTINATION_PARAMETER_NAME = "destination";
    private static final String PRICE_PARAMETER_NAME = "price";
    private static final String NAME_PARAMETER_NAME = "name";
    private static final String ACTION_PARAMETER_NAME = "action";
    private static final String PAYMENT_PARAMETER_NAME = "payment";

    @Override
    public CommandResponse execute(HttpServletRequest request, HttpServletResponse response) {

        logger.info("command " + CommitPaymentCreationCommand.class);

        HttpSession session = request.getSession();

        String destination = request.getParameter(DESTINATION_PARAMETER_NAME);
        String name = request.getParameter(NAME_PARAMETER_NAME);
        Long price;

        if (Objects.isNull(destination) || Objects.isNull(name)){
            logger.error("required data not exists");
            return new CommandResponse(request.getContextPath() + ApplicationCommand.SHOW_ERROR_PAGE_URL, true);
        }

        try {
            price = Long.valueOf(request.getParameter(PRICE_PARAMETER_NAME));
        } catch (NumberFormatException e){
            logger.error(e);
            return new CommandResponse(request.getContextPath() + ApplicationCommand.SHOW_ERROR_PAGE_URL, true);
        }

        PaymentDto paymentDto = new PaymentDto((Integer) session.getAttribute(ID_ATTRIBUTE_NAME),
                destination,
                price,
                false,
                null,
                name);

        PaymentService service = new PaymentService();

        if (Objects.equals(request.getParameter(ACTION_PARAMETER_NAME), SAVE_AND_PAY_ACTION)){

            try {
                PaymentDto createdPayment = service.create(paymentDto);
                request.setAttribute(PAYMENT_PARAMETER_NAME, createdPayment);
            } catch (ServiceException e) {
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
