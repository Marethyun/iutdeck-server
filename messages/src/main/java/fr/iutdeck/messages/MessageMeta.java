package fr.iutdeck.messages;

public enum MessageMeta {
    AUTH                (AuthMessage.NAME,              AuthMessage.class,              new AuthMessage.Formalizer(),               new AuthMessage.Specializer()),
    CHECK_GAME          (CheckGameMessage.NAME,         CheckGameMessage.class,         new CheckGameMessage.Formalizer(),          new CheckGameMessage.Specializer()),
    ERROR               (ErrorMessage.NAME,             ErrorMessage.class,             new ErrorMessage.Formalizer(),              new ErrorMessage.Specializer()),
    GAME_STARTING       (GameStartingMessage.NAME,      GameStartingMessage.class,      new GameStartingMessage.Formalizer(),       new GameStartingMessage.Specializer()),
    GAME_TOKEN          (GameTokenMessage.NAME,         GameTokenMessage.class,         new GameTokenMessage.Formalizer(),          new GameTokenMessage.Specializer()),
    INFO                (GameTokenMessage.NAME,         GameTokenMessage.class,         new GameTokenMessage.Formalizer(),          new GameTokenMessage.Specializer()),
    JOIN_ROOM           (JoinRoomMessage.NAME,          JoinRoomMessage.class,          new JoinRoomMessage.Formalizer(),           new JoinRoomMessage.Specializer()),
    JOIN_ROOM_OFFLINE   (JoinRoomOfflineMessage.NAME,   JoinRoomOfflineMessage.class,   new JoinRoomOfflineMessage.Formalizer(),    new JoinRoomOfflineMessage.Specializer()),
    OK                  (OkMessage.NAME,                OkMessage.class,                new OkMessage.Formalizer(),                 new OkMessage.Specializer()),
    OPPONENT_EVENT      (OpponentEventMessage.NAME,     OpponentEventMessage.class,     new OpponentEventMessage.Formalizer(),      new OpponentEventMessage.Specializer()),
    POKE                (PokeMessage.NAME,              PokeMessage.class,              new PokeMessage.Formalizer(),               new PokeMessage.Specializer()),
    PREPARE_GAME        (PrepareGameMessage.NAME,       PrepareGameMessage.class,       new PrepareGameMessage.Formalizer(),        new PrepareGameMessage.Specializer()),
    PUSH_HISTORY        (PushHistoryMessage.NAME,       PushHistoryMessage.class,       new PushHistoryMessage.Formalizer(),        new PushHistoryMessage.Specializer()),
    QUIT_ROOM           (QuitRoomMessage.NAME,          QuitRoomMessage.class,          new QuitRoomMessage.Formalizer(),           new QuitRoomMessage.Specializer()),
    READY               (ReadyMessage.NAME,             ReadyMessage.class,             new QuitRoomMessage.Formalizer(),           new QuitRoomMessage.Specializer()),
    SERVER_INFO         (ServerInfoMessage.NAME,        ServerInfoMessage.class,        new ServerInfoMessage.Formalizer(),         new ServerInfoMessage.Specializer()),
    SERVERS_LIST        (ServersListMessage.NAME,       ServersListMessage.class,       new ServersListMessage.Formalizer(),        new ServersListMessage.Specializer()),
    SERVERS             (ServersMessage.NAME,           ServersMessage.class,           new ServersMessage.Formalizer(),            new ServersMessage.Specializer()),
    USER                (UserMessage.NAME,              UserMessage.class,              new UserMessage.Formalizer(),               new UserMessage.Specializer()),
    USER_TOKEN          (UserTokenMessage.NAME,         UserTokenMessage.class,         new UserTokenMessage.Formalizer(),          new UserTokenMessage.Specializer());

    public final String name;
    public final Class<? extends GameMessage> clazz;
    public final MessageFormalizer<? extends GameMessage> formalizer;
    public final MessageSpecializer<? extends GameMessage> specializer;

    MessageMeta(String name, Class<? extends GameMessage> clazz, MessageFormalizer<? extends GameMessage> formalizer, MessageSpecializer<? extends GameMessage> specializer) {
        this.name = name;
        this.clazz = clazz;
        this.formalizer = formalizer;
        this.specializer = specializer;
    }

    @SuppressWarnings("unchecked")
    public static <T extends GameMessage> MessageFormalizer<T> getFormalizer(Class<T> clazz) {
        for (MessageMeta entry : values()) {
            if (entry.clazz.equals(clazz))
                return (MessageFormalizer<T>) entry.formalizer;
        }

        return null;
    }

    public static MessageSpecializer<? extends GameMessage> getSpecializer(String name) {
        for (MessageMeta entry : values()) {
            if (entry.name.equals(name))
                return entry.specializer;
        }

        return null;
    }
}
