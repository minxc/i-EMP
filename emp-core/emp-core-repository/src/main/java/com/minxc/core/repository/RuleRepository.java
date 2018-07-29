package com.minxc.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.minxc.core.entity.Rule;

@Repository
public interface RuleRepository extends JpaRepository<Rule, String> {

}
