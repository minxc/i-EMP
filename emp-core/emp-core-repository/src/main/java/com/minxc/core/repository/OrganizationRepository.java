package com.minxc.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.minxc.core.entity.Organization;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, String> {

}
