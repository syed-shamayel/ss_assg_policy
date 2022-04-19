package com.bits.assignment.policy.ss_assg_policy.reepository;

import com.bits.assignment.policy.ss_assg_policy.model.Premium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface PremiumRepository  extends JpaRepository<Premium, Long>{

    @Query("select p from premium p where p.policyId=?1")
    public Premium findByPolicyId(Long policyId);

}
