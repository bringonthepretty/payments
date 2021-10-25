package com.epam.jwd.service.exception;

import java.util.Arrays;
import java.util.Objects;

public enum ExceptionCode {

    UNKNOWN_EXCEPTION_CODE(0),
    REPOSITORY_IS_EMPTY_EXCEPTION_CODE(1),
    ID_IS_NULL_EXCEPTION_CODE(2),

    USER_LOGIN_TOO_SHORT_EXCEPTION_CODE(101),
    USER_PASSWORD_TOO_SHORT_EXCEPTION_CODE(102),
    USER_ID_IS_NULL_EXCEPTION_CODE(103),
    ACCOUNT_NAME_TOO_SHORT_EXCEPTION_CODE(104),
    ACCOUNT_SURNAME_TOO_SHORT_EXCEPTION_CODE(105),
    ACCOUNT_ROLE_ID_IS_WRONG_EXCEPTION_CODE(106),
    USER_IS_NULL_EXCEPTION_CODE(107),
    ACCOUNT_IS_NULL_EXCEPTION_CODE(108),
    USER_IS_NOT_FOUND_EXCEPTION_CODE(109),

    CREDIT_CARD_IS_NULL_EXCEPTION_CODE(201),
    CREDIT_CARD_ID_IS_NULL_EXCEPTION_CODE(202),
    CREDIT_CARD_DATE_IS_NULL_EXCEPTION_CODE(203),
    BANK_ACCOUNT_IS_NULL_EXCEPTION_CODE(204),
    BANK_ACCOUNT_BALANCE_IS_NULL_EXCEPTION_CODE(205),
    BANK_ACCOUNT_BALANCE_IS_NEGATIVE_EXCEPTION_CODE(206),
    BANK_ACCOUNT_BLOCKED_IS_NULL_EXCEPTION_CODE(207),
    CREDIT_CARD_IS_NOT_FOUND_EXCEPTION_CODE(208),

    ROLE_IS_NULL_EXCEPTION_CODE(301),
    ROLE_ID_IS_NULL_EXCEPTION_CODE(302),
    ROLE_NAME_IS_TOO_SHORT_EXCEPTION_CODE(303),
    ROLE_IS_NOT_FOUND_EXCEPTION_CODE(304),

    PAYMENT_IS_NULL_EXCEPTION_CODE(401),
    PAYMENT_ID_IS_NULL_EXCEPTION_CODE(402),
    PAYMENT_USER_ID_IS_NULL_EXCEPTION_CODE(403),
    PAYMENT_ADDRESS_IS_NULL_EXCEPTION_CODE(404),
    PAYMENT_PRICE_IS_NULL_EXCEPTION_CODE(405),
    PAYMENT_PRICE_IS_NEGATIVE_EXCEPTION_CODE(406),
    PAYMENT_COMMITTED_IS_NULL_EXCEPTION_CODE(407),
    PAYMENT_TIME_IS_NULL_EXCEPTION_CODE(408),
    PAYMENT_NAME_TOO_SHORT_EXCEPTION_CODE(409),
    PAYMENT_IS_NOT_FOUND_EXCEPTION_CODE(410);

    private final Integer errorCode;

    ExceptionCode(Integer errorCode){
        this.errorCode = errorCode;
    }

    public Integer getExceptionCode(){
        return errorCode;
    }

    public static ExceptionCode getByExceptionCode(Integer id){
        return Arrays.stream(ExceptionCode.values())
                .filter(exceptionCode -> Objects.equals(exceptionCode.getExceptionCode(), id))
                .findFirst()
                .orElse(UNKNOWN_EXCEPTION_CODE);
    }
}