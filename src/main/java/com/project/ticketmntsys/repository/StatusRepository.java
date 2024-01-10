package com.project.ticketmntsys.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ticketmntsys.entity.Status;

import jakarta.validation.Valid;

public interface StatusRepository extends JpaRepository<Status, Integer> {

}
