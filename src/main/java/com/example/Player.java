package com.example;

import java.io.Serializable;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Player implements Runnable, Serializable {
    private static final long serialVersionUID = 1L;
    private final String name;
    private final BlockingQueue<Message> inbox = new LinkedBlockingQueue<>();
    private int messageCounter = 0;
    private Player partner;
    private int maxMessages = 10;
    private boolean running = true;

    public Player(String name) {
        this.name = name;
    }

    public void setPartner(Player partner) {
        this.partner = partner;
    }

    public void sendMessage(String content) {
        if (messageCounter < maxMessages) {
            Message message = new Message(content, messageCounter++);
            System.out.println(name + " sending: " + content);
            partner.receiveMessage(message);
        }
    }

    public void receiveMessage(Message message) {
        inbox.add(message);
    }

    public void run() {
        while (running) {
            try {
                Message message = inbox.take();
                System.out.println(name + " received: " + message.getContent());
                String responseContent = message.getContent() + " (response " + messageCounter + ")";
                sendMessage(responseContent);
                if (messageCounter >= maxMessages) {
                    running = false;
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public String getName() {
        return name;
    }
}
