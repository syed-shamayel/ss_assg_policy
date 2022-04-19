package com.bits.assignment.policy.ss_assg_policy.reepository;

import com.bits.assignment.policy.ss_assg_policy.model.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface PolicyRepository  extends JpaRepository<Policy, Long> {


}
