package com.bits.assignment.policy.ss_assg_policy.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;

@Entity(name="policy")
@Getter
@Setter
public class Policy {
    @Id
    @GeneratedValue
    private Long id;
    private String type;

    private Date startDate;

    private Date endDate;


    private BigDecimal amount;

    private int tenure;

    @Transient
    private Premium premium;
}
