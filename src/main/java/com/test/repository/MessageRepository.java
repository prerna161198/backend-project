package com.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

import com.test.model.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

}
