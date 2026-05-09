package com.felipesouls.alga_comment_service.api.models.input;

public record ModerationInput (
    String text,
    String commentId
) {
}
