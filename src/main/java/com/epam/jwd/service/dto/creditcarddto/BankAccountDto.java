package com.epam.jwd.service.dto.creditcarddto;

import com.epam.jwd.service.dto.AbstractDto;

import java.util.Objects;

public class BankAccountDto extends AbstractDto<Integer> {
    private Integer balance;
    private Boolean blocked;

    public BankAccountDto(Integer balance, Boolean blocked) {
        this.balance = balance;
        this.blocked = blocked;
    }

    public BankAccountDto(Integer id, Integer balance, Boolean blocked) {
        this.balance = balance;
        this.blocked = blocked;
        this.id = id;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccountDto bankAccountDTO = (BankAccountDto) o;
        return Objects.equals(balance, bankAccountDTO.getBalance())
                && Objects.equals(blocked, bankAccountDTO.getBlocked())
                && Objects.equals(id, bankAccountDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(balance, blocked, id);
    }

    @Override
    public String toString() {
        return "BankAccountDTO{" +
                "id=" + id +
                ", balance=" + balance +
                ", blocked=" + blocked +
                '}';
    }
}