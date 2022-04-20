package com.bits.assignment.policy.ss_assg_policy.controller;

import com.bits.assignment.policy.ss_assg_policy.model.Premium;
import com.bits.assignment.policy.ss_assg_policy.reepository.PremiumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PremiumController {

    @Autowired
    PremiumRepository premiumRepo;

    @GetMapping("/{policyId}/premium")
    public Premium getPremium(@PathVariable Long policyId) {
        Premium prem = premiumRepo.findByPolicyId(policyId);
        return prem;
    }

    @GetMapping("/premium/getAll")
    public List<Premium> getPremium() {
        return premiumRepo.findAll();

    }

    @PostMapping("/premium/create")
    public Premium createPremium( @RequestBody Premium premium) {
       premium = premiumRepo.save(premium);
       return  premium;
    }


    @PutMapping("/premium")
    public void updatePremium(@RequestBody Premium premium) {
        Optional<Premium> oldP =  premiumRepo.findById(premium.getId());
        if(oldP.isPresent()) {
            premiumRepo.save(premium);
        }
    }
}
