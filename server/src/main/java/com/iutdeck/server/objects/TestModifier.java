package com.iutdeck.server.objects;

import com.iutdeck.server.GameContext;

public class TestModifier {

    public static void main(String[] args) {
        Card crack = new Card(124697, CardType.CRACK);
        Card buffer = new Card(10587, CardType.BUFFER);

        System.out.println(crack);
        System.out.println(buffer);

        GameContext ctx = new GameContext();

        buffer.cast(ctx, crack);

        System.out.println(crack);
    }
}
