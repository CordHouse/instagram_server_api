package com.example.instagram.Repository.Replies;

import com.example.instagram.Entity.Replies.Replies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepliesRepository extends JpaRepository<Replies, Long> {
}
