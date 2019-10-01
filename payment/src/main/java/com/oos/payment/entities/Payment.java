package com.oos.payment.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "payment")
public class Payment implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;

    private int amount;

    private int fee;

    public Payment() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    @Override
    public String toString() {
        return "Payment{" +
            "id=" + id +
            ", itemName='" + itemName + '\'' +
            ", amount=" + amount +
            ", fee=" + fee +
            '}';
    }
}
