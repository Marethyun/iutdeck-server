package fr.iutdeck.messages;

import java.util.ArrayList;
import java.util.HashMap;

public final class PushHistoryMessage implements GameMessage {
    public static final String NAME = "push_history";
    private static final String PARAMETER_APPLICATION_TOKEN = "application_token";
    private static final String PARAMETER_PLAYER1_ID = "player1_id";
    private static final String PARAMETER_PLAYER2_ID = "player2_id";
    private static final String PARAMETER_WINNER_ID = "winner_id";
    private static final String PARAMETER_TIME_STARTED = "time_started";
    private static final String PARAMETER_TIME_ENDED = "time_ended";
    private static final String PARAMETER_EVENTS = "events";
    private static final String PARAMETER_EVENT_TYPE = "type";
    private static final String PARAMETER_EVENT_PLAYER_ID = "player_id";
    private static final String PARAMETER_EVENT_TIME_FIRED = "time_fired";
    private static final String PARAMETER_EVENT_PROPERTIES = "properties";

    public final String applicationToken;
    public final int player1Id;
    public final int player2Id;
    public final Integer winnerId; // Integer boxing because it can be null
    public final long timeStarted;
    public final long timeEnded;
    public final ArrayList<Event> events;

    public PushHistoryMessage(String applicationToken, int player1Id, int player2Id, Integer winnerId, long timeStarted, long timeEnded, ArrayList<Event> events) {
        this.applicationToken = applicationToken;
        this.player1Id = player1Id;
        this.player2Id = player2Id;
        this.winnerId = winnerId;
        this.timeStarted = timeStarted;
        this.timeEnded = timeEnded;
        this.events = events;
    }

    public static final class Event {
        public final EventType type;
        public final int playerId;
        public final long timeFired;
        public final HashMap<String, Object> properties;

        public Event(EventType type, int playerId, long timeFired, HashMap<String, Object> properties) {
            this.type = type;
            this.playerId = playerId;
            this.timeFired = timeFired;
            this.properties = properties;
        }
    }

    public enum EventType {
        ; // TODO À compléter

        public final String name;

        EventType(String name) {
            this.name = name;
        }

        public static EventType fromName(String name) {
            for (EventType value : EventType.values())
                if (value.name.equals(name))
                    return value;

            return null;
        }
    }

    public static final class Formalizer implements MessageFormalizer<PushHistoryMessage> {
        @Override
        public FormalizedMessage formalize(PushHistoryMessage message) {
            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put(PARAMETER_APPLICATION_TOKEN, message.applicationToken);
            parameters.put(PARAMETER_PLAYER1_ID, message.player1Id);
            parameters.put(PARAMETER_PLAYER2_ID, message.player2Id);
            parameters.put(PARAMETER_WINNER_ID, message.winnerId);
            parameters.put(PARAMETER_TIME_STARTED, message.timeStarted);
            parameters.put(PARAMETER_TIME_ENDED, message.timeEnded);

            ArrayList<HashMap<String, Object>> mappedEvents = new ArrayList<>();

            for (Event event : message.events) {
                HashMap<String, Object> mappedEvent = new HashMap<>();
                mappedEvent.put(PARAMETER_EVENT_TYPE, event.type.name);
                mappedEvent.put(PARAMETER_EVENT_PLAYER_ID, event.playerId);
                mappedEvent.put(PARAMETER_EVENT_TIME_FIRED, event.timeFired);
                mappedEvent.put(PARAMETER_EVENT_PROPERTIES, event.properties);
                mappedEvents.add(mappedEvent);
            }

            parameters.put(PARAMETER_EVENTS, mappedEvents);

            return new FormalizedMessage(NAME, parameters);
        }
    }


    public static final class Specializer implements MessageSpecializer<PushHistoryMessage> {
        @SuppressWarnings("unchecked")
        @Override
        public PushHistoryMessage specialize(FormalizedMessage message) {
            HashMap<String, Object> parameters = message.getParameters();

            int playerId1 = ((Number) parameters.get(PARAMETER_PLAYER1_ID)).intValue();
            int playerId2 = ((Number) parameters.get(PARAMETER_PLAYER2_ID)).intValue();
            long timeStarted = ((Number) parameters.get(PARAMETER_TIME_STARTED)).longValue();
            long timeEnded = ((Number) parameters.get(PARAMETER_TIME_ENDED)).longValue();

            ArrayList<HashMap<String, Object>> mappedEvents = (ArrayList<HashMap<String, Object>>) parameters.get(PARAMETER_EVENTS);
            ArrayList<Event> events = new ArrayList<>();

            for (HashMap<String, Object> mappedEvent : mappedEvents) {
                EventType type = EventType.fromName((String) mappedEvent.get(PARAMETER_EVENT_TYPE));
                if (type == null)
                    throw new RuntimeException("Encountered an invalid event type"); // TODO Specialize exception
                int playerId = ((Number) mappedEvent.get(PARAMETER_EVENT_PLAYER_ID)).intValue();
                long timeFired = ((Number) mappedEvent.get(PARAMETER_EVENT_TIME_FIRED)).longValue();

                events.add(new Event(type, playerId, timeFired, (HashMap<String, Object>) mappedEvent.get(PARAMETER_EVENT_PROPERTIES)));
            }

            return new PushHistoryMessage(
                    (String) parameters.get(PARAMETER_APPLICATION_TOKEN),
                    playerId1,
                    playerId2,
                    (Integer) parameters.get(PARAMETER_WINNER_ID),
                    timeStarted,
                    timeEnded,
                    events
            );
        }
    }
}
