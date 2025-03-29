package dev.cesar.hermes_whisper.model.xai;

public record Choice(
        int index,
        Message message,
        String finish_reason
) {
}
