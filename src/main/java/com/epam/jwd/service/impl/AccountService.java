package com.epam.jwd.service.impl;

import com.epam.jwd.dao.api.Dao;
import com.epam.jwd.dao.impl.AccountDao;
import com.epam.jwd.dao.model.user.Account;
import com.epam.jwd.service.api.Service;
import com.epam.jwd.service.comparator.accountcomparator.SurnameSortingComparator;
import com.epam.jwd.service.converter.api.Converter;
import com.epam.jwd.service.converter.impl.AccountConverter;
import com.epam.jwd.service.dto.userdto.AccountDto;
import com.epam.jwd.service.exception.ExceptionCode;
import com.epam.jwd.service.exception.ServiceException;
import com.epam.jwd.service.validator.api.Validator;
import com.epam.jwd.service.validator.impl.AccountValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AccountService implements Service<AccountDto, Integer> {

    private final Dao<Account, Integer> dao = new AccountDao();
    private final Validator<AccountDto, Integer> validator = new AccountValidator();
    private final Converter<Account, AccountDto, Integer> converter = new AccountConverter();

    @Override
    public AccountDto create(AccountDto value) throws ServiceException {
        validator.validate(value, false);
        Account createdAccount = converter.convert(value);
        return converter.convert(dao.save(createdAccount));
    }

    @Override
    public Boolean update(AccountDto value) throws ServiceException {
        validator.validate(value, true);
        return dao.update(converter.convert(value));
    }

    @Override
    public Boolean delete(AccountDto value) throws ServiceException {
        validator.validate(value, true);
        return dao.delete(converter.convert(value));
    }

    @Override
    public AccountDto getById(Integer id) throws ServiceException {
        validator.validateIdNotNull(id);
        Account result = dao.findById(id);
        if (Objects.isNull(result)){
            throw new ServiceException(ExceptionCode.ACCOUNT_IS_NOT_FOUND_EXCEPTION_CODE);
        }
        return converter.convert(result);
    }

    @Override
    public List<AccountDto> getAll() throws ServiceException {
        List<AccountDto> result = new ArrayList<>();
        List<Account> daoResult = dao.findAll();
        if (daoResult.isEmpty()){
            throw new ServiceException(ExceptionCode.REPOSITORY_IS_EMPTY_EXCEPTION_CODE);
        }
        daoResult.forEach(user -> result.add(converter.convert(user)));
        return result;
    }

    public List<AccountDto> sortBySurname (List<AccountDto> accounts){
        accounts.sort(new SurnameSortingComparator());
        return accounts;
    }
}
