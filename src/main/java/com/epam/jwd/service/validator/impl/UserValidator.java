package com.epam.jwd.service.validator.impl;

import com.epam.jwd.dao.impl.UserDAO;
import com.epam.jwd.dao.model.user.User;
import com.epam.jwd.service.dto.userdto.AccountDTO;
import com.epam.jwd.service.dto.userdto.UserDTO;
import com.epam.jwd.service.exception.ExceptionCode;
import com.epam.jwd.service.exception.ServiceException;
import com.epam.jwd.service.validator.api.Validator;

import java.util.List;
import java.util.Objects;

public class UserValidator implements Validator<UserDTO, Integer> {

    private static final Integer LOGIN_MIN_LENGTH = 3;
    private static final Integer PASSWORD_MIN_LENGTH = 5;
    private static final Integer NAME_MIN_LENGTH = 2;
    private static final Integer SURNAME_MIN_LENGTH = 2;
    private static final Integer MAX_ROLES = 2;

    @Override
    public void validate(UserDTO value, Boolean checkId) throws ServiceException {
        if (Objects.isNull(value)) {
            throw new ServiceException(ExceptionCode.USER_IS_NULL_EXCEPTION_CODE);
        }
        if (checkId){
            validateId(value.getId());
        }
        validateLogin(value.getLogin());
        validatePassword(value.getPassword());
        validateAccount(value.getAccount(), checkId);
    }

    public void validateLoginNotNull(String login) throws ServiceException{
        if (Objects.isNull(login)){
            throw new ServiceException(ExceptionCode.USER_LOGIN_IS_NULL_EXCEPTION_CODE);
        }
    }

    public void validateLoginUnique(User user) throws ServiceException{
        if (!Objects.isNull(user)){
            throw new ServiceException(ExceptionCode.USER_LOGIN_IS_NOT_UNIQUE_EXCEPTION_CODE);
        }
    }

    private void validateLogin(String login) throws ServiceException {
        if (Objects.isNull(login)
                || login.length() < LOGIN_MIN_LENGTH) {
            throw new ServiceException(ExceptionCode.USER_LOGIN_TOO_SHORT_EXCEPTION_CODE);
        }
    }

    private void validatePassword(String password) throws ServiceException {
        if (Objects.isNull(password)
                || password.length() < PASSWORD_MIN_LENGTH){
            throw new ServiceException(ExceptionCode.USER_PASSWORD_TOO_SHORT_EXCEPTION_CODE);
        }
    }

    private void validateAccount(AccountDTO accountDTO, Boolean checkId) throws ServiceException {
        if (Objects.isNull(accountDTO)){
            throw new ServiceException(ExceptionCode.ACCOUNT_IS_NULL_EXCEPTION_CODE);
        }
        if (checkId){
            validateId(accountDTO.getId());
        }
        validateName(accountDTO.getName());
        validateSurname(accountDTO.getSurname());
        validateRoleId(accountDTO.getRoleId());
    }

    private void validateName(String name) throws ServiceException {
        if (Objects.isNull(name)
                || name.length() < NAME_MIN_LENGTH) {
            throw new ServiceException(ExceptionCode.ACCOUNT_NAME_TOO_SHORT_EXCEPTION_CODE);
        }
    }

    private void validateSurname(String surname) throws ServiceException {
        if (Objects.isNull(surname)
                || surname.length() < SURNAME_MIN_LENGTH) {
            throw new ServiceException(ExceptionCode.ACCOUNT_SURNAME_TOO_SHORT_EXCEPTION_CODE);
        }
    }

    private void validateRoleId(Integer id) throws ServiceException {
        if (Objects.isNull(id)
                || id > MAX_ROLES) {
            throw new ServiceException(ExceptionCode.ACCOUNT_ROLE_ID_IS_WRONG_EXCEPTION_CODE);
        }
    }

    private void validateId(Integer id) throws ServiceException {
        if (Objects.isNull(id)){
            throw new ServiceException(ExceptionCode.USER_ID_IS_NULL_EXCEPTION_CODE);
        }
    }
}
