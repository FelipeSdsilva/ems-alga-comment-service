package com.felipesouls.alga_comment_service.domain.repositories;

import java.util.UUID;

import com.felipesouls.alga_comment_service.domain.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, UUID> {
}
