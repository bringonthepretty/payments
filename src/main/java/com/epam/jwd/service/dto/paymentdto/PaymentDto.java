package com.epam.jwd.service.dto.paymentdto;

import com.epam.jwd.service.dto.AbstractDto;

import java.util.Date;
import java.util.Objects;

public class PaymentDto extends AbstractDto<Integer> {
    private Integer userId;
    private String destinationAddress;
    private Long price;
    private Boolean committed;
    private Date time;
    private String name;

    public PaymentDto(Integer userId, String destinationAddress, Long price, Boolean committed, Date time, String name) {
        this.userId = userId;
        this.destinationAddress = destinationAddress;
        this.price = price;
        this.committed = committed;
        this.time = time;
        this.name = name;
    }

    public PaymentDto(Integer id, Integer userId, String destinationAddress, Long price, Boolean committed, Date time, String name) {
        this.userId = userId;
        this.destinationAddress = destinationAddress;
        this.price = price;
        this.committed = committed;
        this.time = time;
        this.name = name;
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Boolean getCommitted() {
        return committed;
    }

    public void setCommitted(Boolean committed) {
        this.committed = committed;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentDto paymentDTO = (PaymentDto) o;
        return Objects.equals(userId, paymentDTO.getUserId())
                && Objects.equals(destinationAddress, paymentDTO.getDestinationAddress())
                && Objects.equals(price, paymentDTO.getPrice())
                && Objects.equals(committed, paymentDTO.getCommitted())
                && Objects.equals(time, paymentDTO.getTime())
                && Objects.equals(name, paymentDTO.getName())
                && Objects.equals(id, paymentDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, destinationAddress, price, committed, time, name, id);
    }

    @Override
    public String toString() {
        return "PaymentDTO{" +
                "id=" + id +
                ", accountId=" + userId +
                ", destinationAddress='" + destinationAddress + '\'' +
                ", price=" + price +
                ", committed=" + committed +
                ", time=" + time +
                ", name='" + name + '\'' +
                '}';
    }
}
