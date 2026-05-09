package com.felipesouls.alga_comment_service.api.controllers;


import com.felipesouls.alga_comment_service.api.models.input.CommentInput;
import com.felipesouls.alga_comment_service.api.models.output.CommentOutput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface CommentController {

    @GetMapping
    ResponseEntity<Page<CommentOutput>> getAllComments(@PageableDefault Pageable pageable);

    @GetMapping(value = "/{id}")
    ResponseEntity<CommentOutput> getCommentById(@PathVariable String id);

    @PostMapping
    ResponseEntity<CommentOutput> createComment(@RequestBody CommentInput commentInput);
}
