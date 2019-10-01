package com.oos.payment.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "sample_user")
public class User implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String surname;
    private String othername;
    private String matric;
    private String department;
    private String school;
    private String level;
    private String ses;
    private String email;
    private String phone;
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE,
        CascadeType.PERSIST, CascadeType.REFRESH})
    private Set<Payment> payments;
    private String ref;
    private String status;
    private String date;
    private int amount;
    private int fee;
    private int total;

    public User() {
    }


    public Long getId() {
        return id;
    }

    public Set<Payment> getPayments() {
        return payments;
    }

    public void setPayments(Set<Payment> payments) {
        this.payments = payments;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getOthername() {
        return othername;
    }

    public void setOthername(String othername) {
        this.othername = othername;
    }

    public String getMatric() {
        return matric;
    }

    public void setMatric(String matric) {
        this.matric = matric;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getSes() {
        return ses;
    }

    public void setSes(String ses) {
        this.ses = ses;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", surname='" + surname + '\'' +
            ", othername='" + othername + '\'' +
            ", matric='" + matric + '\'' +
            ", department='" + department + '\'' +
            ", school='" + school + '\'' +
            ", level='" + level + '\'' +
            ", ses='" + ses + '\'' +
            ", email='" + email + '\'' +
            ", phone='" + phone + '\'' +
            ", payments=" + payments +
            ", ref='" + ref + '\'' +
            ", status='" + status + '\'' +
            ", date='" + date + '\'' +
            ", amount=" + amount +
            ", fee=" + fee +
            ", total=" + total +
            '}';
    }
}
