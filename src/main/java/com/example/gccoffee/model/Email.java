package com.example.gccoffee.model;

import org.springframework.util.Assert;

import java.util.Objects;
import java.util.regex.Pattern;

public class Email {
    //이메일 포멧으로 받아야되기 때문에 따로 만듬
    //Value Object으로 정의
    private final String address;

    public Email(String address) {
        Assert.notNull(address, "address should not be null");
        Assert.isTrue(address.length() >= 4 && address.length() <= 50, "address length must be between 4 and 50 characters.");
        Assert.isTrue(checkAddress(address), "Invalid email address");
        this.address = address;
    }

    //https://regexr.com/ 정규표현식 참고사이트
    private static boolean checkAddress(String address) {
        return Pattern.matches("\\b[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,4}\\b", address);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return Objects.equals(address, email.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address);
    }

    @Override
    public String toString() {
        return "Email{" +
                "address='" + address + '\'' +
                '}';
    }

    public String getAddress() {
        return address;
    }
}
