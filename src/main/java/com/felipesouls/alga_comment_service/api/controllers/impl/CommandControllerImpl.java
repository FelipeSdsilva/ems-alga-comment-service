package com.felipesouls.alga_comment_service.api.controllers.impl;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import java.net.URI;

import com.felipesouls.alga_comment_service.api.controllers.CommentController;
import com.felipesouls.alga_comment_service.api.models.input.CommentInput;
import com.felipesouls.alga_comment_service.api.models.output.CommentOutput;
import com.felipesouls.alga_comment_service.domain.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/comments")
public class CommandControllerImpl implements CommentController {

  private final CommentService commentService;

  @Override
  public ResponseEntity<Page<CommentOutput>> getAllComments(Pageable pageable) {
    return ResponseEntity.ok(commentService.retrieverCommentsPageable(pageable));
  }

  @Override
  public ResponseEntity<CommentOutput> getCommentById(String id) {
    return ResponseEntity.ok(commentService.retrieverCommentPerId(id));
  }

  @Override
  public ResponseEntity<CommentOutput> createComment(CommentInput commentInput) {
    CommentOutput commentOutput = commentService.createComment(commentInput);
    URI uri = fromCurrentRequest().path("/{id}").buildAndExpand(commentOutput.getId()).toUri();
    return ResponseEntity.created(uri).body(commentOutput);
  }
}
