package com.epam.jwd.controller.command.impl.showpage;

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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ShowPaymentsCommand implements Command {

    private static final Logger logger = LogManager.getLogger(ShowPaymentsCommand.class);

    private static final String USER_PAYMENTS_PAGE_URL = "/WEB-INF/jsp/payments.jsp";
    private static final Integer MAX_ITEMS_IN_PAGE = 5;

    private final PaymentService service = new PaymentService();

    @Override
    public CommandResponse execute(HttpServletRequest request, HttpServletResponse response) {
        logger.info("command " + ShowPaymentsCommand.class);

        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("id");
        Integer pageNumber = Integer.valueOf(!Objects.isNull(request.getParameter("currentPage")) ? request.getParameter("currentPage") : "1");
        Integer lastPage = getLastPage(request, userId);
        List<PaymentDto> payments;

        if (pageNumber > lastPage){
            pageNumber = 1;
        }

        try {
            payments = service.getByUserIdWithinRange(userId,
                    MAX_ITEMS_IN_PAGE,
                    (pageNumber -1) * MAX_ITEMS_IN_PAGE);
        } catch (ServiceException e) {
            logger.error(e.getErrorCode());
            payments = new ArrayList<>();
        }

        request.setAttribute("payments", payments);
        request.setAttribute("currentPage", pageNumber);

        return new CommandResponse(request.getContextPath() + USER_PAYMENTS_PAGE_URL, false);
    }

    private Integer getLastPage(HttpServletRequest request, Integer userId){
        Integer lastPage;
        Integer paymentsAmount;
        try {
            paymentsAmount = service.getAmountWithUserId(userId);
        } catch (ServiceException e) {
            paymentsAmount = 0;
            logger.error(e.getErrorCode());
        }
        if (Double.compare(paymentsAmount / MAX_ITEMS_IN_PAGE.doubleValue(), paymentsAmount / MAX_ITEMS_IN_PAGE) == 0){
            lastPage = paymentsAmount / MAX_ITEMS_IN_PAGE;
        } else {
            lastPage = paymentsAmount / MAX_ITEMS_IN_PAGE + 1;
        }
        request.setAttribute("lastPage", lastPage);
        return lastPage;
    }
}
