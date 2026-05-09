package com.felipesouls.alga_comment_service.api.models.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModerationOutput {

  private boolean approved;
  private String reason;

}
