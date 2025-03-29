package dev.cesar.hermes_whisper.model.xai;

public record Usage(
        int prompt_tokens,
        int completion_tokens,
        int total_tokens
) {
}
