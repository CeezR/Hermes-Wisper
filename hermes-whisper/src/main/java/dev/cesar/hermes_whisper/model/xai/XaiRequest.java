package dev.cesar.hermes_whisper.model.xai;

import java.util.List;

public record XaiRequest(
        List<Message> messages,
        String model,
        boolean stream,
        int temperature
) {
    public static XaiRequest withDefault(Message message) {
        return  new XaiRequest(
                        List.of(new Message(Role.SYSTEM, "You are Grok, a chatbot inspired by the Hitchhikers Guide to the Galaxy."), message),
                        "grok-beta",
                        false,
                        0
        );
    }
}
