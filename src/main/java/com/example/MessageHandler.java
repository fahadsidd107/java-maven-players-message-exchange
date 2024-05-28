package com.example;

public class MessageHandler {
    private final Player player1;
    private final Player player2;

    public MessageHandler(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        player1.setPartner(player2);
        player2.setPartner(player1);
    }

    public void start() {
        Thread thread1 = new Thread(player1);
        Thread thread2 = new Thread(player2);
        thread1.start();
        thread2.start();

        for (int i = 0; i < 10; i++) {
            player1.sendMessage("Message " + i);
        }

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
