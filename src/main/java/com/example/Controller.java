package com.example;

public class Controller {
    public static void main(String[] args) {
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");

        MessageHandler messageHandler = new MessageHandler(player1, player2);
        messageHandler.start();
    }
}
