package com.ripudamanSingh.blog.blogappapi.repositories;

import com.ripudamanSingh.blog.blogappapi.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Jpa21Utils;

import javax.persistence.criteria.CriteriaBuilder;

public interface RoleRepo extends JpaRepository<Role,Integer> {

}
