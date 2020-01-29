package com.iutdeck.server.objects;

public class TestModifier {

    public static void main(String[] args) {
        Card crack = new Card(124697, CardType.CRACK);
        Card buffer = new Card(10587, CardType.BUFFER);

        buffer.cast(crack);

        System.out.println(crack.modified());
    }
}
