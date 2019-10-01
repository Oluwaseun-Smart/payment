package com.oos.payment.entities;

import java.util.Set;

public class Dto {


    private User user;

    private Set<Payment> payments;

    public Dto() {
    }

    public Dto(User user, Set<Payment> payments) {
        this.user = user;
        this.payments = payments;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Payment> getPayments() {
        return payments;
    }

    public void setPayments(Set<Payment> payments) {
        this.payments = payments;
    }

    @Override
    public String toString() {
        return "Dto{" +
            "user=" + user +
            ", payments=" + payments +
            '}';
    }
}
