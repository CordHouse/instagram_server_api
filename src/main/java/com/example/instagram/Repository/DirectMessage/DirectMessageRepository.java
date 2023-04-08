package com.example.instagram.Repository.DirectMessage;

import com.example.instagram.Entity.DirectMessage.DirectMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectMessageRepository extends JpaRepository<DirectMessage, Long>, DirectMessageRepositoryCustom {
}
