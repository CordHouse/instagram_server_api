package com.example.instagram.Repository.Feed;

import com.example.instagram.Entity.Feed.Feed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FeedRepository extends JpaRepository<Feed, Long> {
    Optional<Feed> findByUser_Id(long id);
}
