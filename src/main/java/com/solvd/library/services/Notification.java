package com.solvd.library.services;

import com.solvd.library.interfaces.INotifiable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Notification {
    private static final Logger logger = LogManager.getLogger(Notification.class);
    private String message;
    private INotifiable recipient;

    public Notification(String message, INotifiable recipient) {
        this.message = message;
        this.recipient = recipient;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public INotifiable getRecipient() {
        return recipient;
    }

    public void setRecipient(INotifiable recipient) {
        this.recipient = recipient;
    }

    // Sends a simple notification via logger and recipient
    public void sendNotification() {
        logger.info("Notification processing for " + recipient.getName() + "...");
        recipient.receiveNotification(message);
    }
}
