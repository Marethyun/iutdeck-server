package fr.iutdeck.messages;

import fr.iutdeck.messages.FormalizedMessage;
import fr.iutdeck.messages.GameMessage;

public interface MessageFormalizer<T extends GameMessage> {
    FormalizedMessage formalize(T message);
}
