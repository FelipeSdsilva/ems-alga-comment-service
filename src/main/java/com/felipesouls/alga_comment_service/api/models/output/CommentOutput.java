package com.felipesouls.alga_comment_service.api.models.output;

import java.time.Instant;

import com.felipesouls.alga_comment_service.domain.entities.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentOutput {

  private String id;
  private String text;
  private String author;
  private Instant createdAt;

  public CommentOutput(Comment comment) {
    this.id = comment.getId().toString();
    BeanUtils.copyProperties(comment, this);
  }
}
