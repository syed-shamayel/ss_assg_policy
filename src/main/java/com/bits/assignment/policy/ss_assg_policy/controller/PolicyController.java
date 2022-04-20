package com.bits.assignment.policy.ss_assg_policy.controller;

import com.bits.assignment.policy.ss_assg_policy.model.Policy;
import com.bits.assignment.policy.ss_assg_policy.model.Premium;
import com.bits.assignment.policy.ss_assg_policy.reepository.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

@RestController
public class PolicyController {


    @Autowired
    PolicyRepository policyRepo;

    @Autowired
    PremiumController premCont;

    @GetMapping("/policies")
    public List<Policy> getPolicies() {

        List<Policy> policies =  policyRepo.findAll();
        return policies;
    }

    @PutMapping("/policy/{id}")
    public Policy updatePolicy(@PathVariable Long id) {
        Optional<Policy> policy = policyRepo.findById(id);
        if(policy.isPresent()) {
            Premium oldPrem= premCont.getPremium(id);
            BigDecimal prem =  policy.get().getAmount().divide(BigDecimal.valueOf(policy.get().getTenure()));
            oldPrem.setDueAmount(dueAmountCalc(policy.get(), oldPrem));
            oldPrem.setRemainingTenure(oldPrem.getRemainingTenure()-1);
            oldPrem.setPaidAmount(oldPrem.getPaidAmount().add(prem));
            premCont.updatePremium(oldPrem);
            policy.get().setPremium(oldPrem);
            return policy.get();
        }
        else
            throw new RuntimeException("Policy Not Found");

    }

    @PostMapping("/policy/create")
    public Policy createPolicy(@RequestBody Policy policy) {
       policy = policyRepo.save(policy);
        return  policy;
    }

    private Date dueDateCalc(Policy policy) {
        Calendar startCalendar = new GregorianCalendar();
        startCalendar.setTime(policy.getStartDate());
        Calendar presentCalendar = new GregorianCalendar();
        int month = presentCalendar.get(Calendar.MONTH);

        startCalendar.set(Calendar.MONTH,month);
        java.util.Date uid = startCalendar.getTime();
        Date dueDate = new Date(uid.getTime());
        return dueDate;

    }

    /*
     * Customer pays first premium at the policy beginning
     * we will check his paid premium Tenure with respect to dueDate and with respect to
     * the remaining tenure from the DB.
     * If both tenures are equal , show 0 as dueAmount else show Montly Premium amount
     *
     */
    private BigDecimal f(Policy policy, Premium prem) {
        int totalTenure = policy.getTenure();
        BigDecimal premAmount = policy.getAmount().divide(BigDecimal.valueOf(totalTenure));

        Calendar startCalendar = new GregorianCalendar();
        startCalendar.setTime(policy.getStartDate());
        Calendar dueCalendar = new GregorianCalendar();
        dueCalendar.setTime(prem.getDueDate());

        int diffYear = dueCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
        int expPaidTenure = diffYear * 12 + dueCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH)+1;
        int actPaidTenure = totalTenure - prem.getRemainingTenure();

        if(expPaidTenure==actPaidTenure){
            premAmount= BigDecimal.valueOf(0);
        }
        return premAmount;
    }
    private BigDecimal dueAmountCalc(Policy policy, Premium prem) {
        int totalTenure = policy.getTenure();
        BigDecimal premAmount = policy.getAmount().divide(BigDecimal.valueOf(totalTenure));

        Calendar startCalendar = new GregorianCalendar();
        startCalendar.setTime(policy.getStartDate());
        Calendar dueCalendar = new GregorianCalendar();
        dueCalendar.setTime(prem.getDueDate());

        int diffYear = dueCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
        int expPaidTenure = diffYear * 12 + dueCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH)+1;
        int actPaidTenure = totalTenure - prem.getRemainingTenure();

        if(expPaidTenure==actPaidTenure){
            premAmount= BigDecimal.valueOf(0);
        }
        return premAmount;
    }

}
