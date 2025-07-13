package com.k8s.observebility.wa1.pgl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.k8s.observebility.wa1.pgl.entity.Message;
@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
	
	
}
