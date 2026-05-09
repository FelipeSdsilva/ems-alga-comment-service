package com.felipesouls.alga_comment_service.domain.services;

import static com.felipesouls.alga_comment_service.common.IdGenerator.generateTimeBasedUUID;

import java.time.Instant;
import java.util.UUID;

import com.felipesouls.alga_comment_service.api.client.ModerationClient;
import com.felipesouls.alga_comment_service.api.controllers.exceptions.ResourceNotFoundException;
import com.felipesouls.alga_comment_service.api.controllers.exceptions.CommentValidationException;
import com.felipesouls.alga_comment_service.api.models.input.CommentInput;
import com.felipesouls.alga_comment_service.api.models.input.ModerationInput;
import com.felipesouls.alga_comment_service.api.models.output.CommentOutput;
import com.felipesouls.alga_comment_service.api.models.output.ModerationOutput;
import com.felipesouls.alga_comment_service.domain.entities.Comment;
import com.felipesouls.alga_comment_service.domain.repositories.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {

  private final CommentRepository commentRepository;
  private final ModerationClient moderationClient;

  @Transactional(readOnly = true)
  public Page<CommentOutput> retrieverCommentsPageable(Pageable pageable) {
    return commentRepository.findAll(pageable).map(CommentOutput::new);
  }

  @Transactional(readOnly = true)
  public CommentOutput retrieverCommentPerId(String id) {
    return new CommentOutput(commentRepository.findById(UUID.fromString(id))
        .orElseThrow(() -> new ResourceNotFoundException("Comment not found per id = " + id)));
  }

  @Transactional
  public CommentOutput createComment(CommentInput input) {
    log.info("Starting comment creation for author: {}", input.author());
    Comment comment = buildComment(input);

    validateCommentModeration(comment);

    Comment savedComment = commentRepository.save(comment);

    log.info("Comment approved and saved successfully. commentId={}", savedComment.getId());
    return new CommentOutput(savedComment);
  }

  private Comment buildComment(CommentInput input) {
    Comment comment = new Comment();
    comment.setId(generateTimeBasedUUID());
    comment.setText(input.text());
    comment.setAuthor(input.author());
    comment.setCreatedAt(Instant.now());

    return comment;
  }

  private void validateCommentModeration(Comment comment) {
    ModerationInput moderationInput = new ModerationInput(
        comment.getText(),
        comment.getId().toString()
    );

    ModerationOutput moderationOutput = moderationClient.postValidate(moderationInput);

    if (!moderationOutput.isApproved()) {
      throw new CommentValidationException("Comment was rejected by moderation");
    }
  }
}
