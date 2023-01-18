package com.example.web_zaim.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "contract")
public class Contract {
    @Id
    @GeneratedValue
    private Long id;
    private String payments;
    @Column(name = "first_payments_date", nullable = false)
    private Date firstPaymentsDate;
}
