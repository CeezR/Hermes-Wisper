package dev.cesar.hermes_whisper.model.xai;

import java.util.List;
import java.util.UUID;

public record XaiResponse(
        UUID id,
        String object,
        long created,
        String model,
        List<Choice> choices,
        Usage usage,
        String system_fingerprint

) {
}
