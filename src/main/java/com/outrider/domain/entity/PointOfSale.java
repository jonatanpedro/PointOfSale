package com.outrider.domain.entity;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by jonat on 09/12/2016.
 */
@Entity
public class PointOfSale {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String tradingName;
    private String phone;
    private String address;
    private String openingHours;

    public PointOfSale() {
    }

    public PointOfSale(String tradingName, String phone, String address, String openingHours) {
        this.tradingName = tradingName;
        this.phone = phone;
        this.address = address;
        this.openingHours = openingHours;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTradingName() {
        return tradingName;
    }

    public void setTradingName(String tradingName) {
        this.tradingName = tradingName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PointOfSale that = (PointOfSale) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (tradingName != null ? !tradingName.equals(that.tradingName) : that.tradingName != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        return !(openingHours != null ? !openingHours.equals(that.openingHours) : that.openingHours != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (tradingName != null ? tradingName.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (openingHours != null ? openingHours.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PointOfSale{" +
                "openingHours='" + openingHours + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", tradingName='" + tradingName + '\'' +
                ", id=" + id +
                '}';
    }
}