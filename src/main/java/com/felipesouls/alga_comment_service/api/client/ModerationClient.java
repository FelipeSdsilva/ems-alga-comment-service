package com.felipesouls.alga_comment_service.api.client;

import com.felipesouls.alga_comment_service.api.models.input.ModerationInput;
import com.felipesouls.alga_comment_service.api.models.output.ModerationOutput;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange("/api/moderate")
public interface ModerationClient {

    @PostExchange
    ModerationOutput postValidate(@RequestBody ModerationInput moderation);
}
