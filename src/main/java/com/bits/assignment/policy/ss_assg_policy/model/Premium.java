package com.bits.assignment.policy.ss_assg_policy.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity(name="premium")
@Getter @Setter
public class Premium {

    @Id
    @GeneratedValue
    private Long id;

    private Long policyId;

    private BigDecimal dueAmount;

    private Date dueDate;

    private int remainingTenure;

    private BigDecimal paidAmount;
}
