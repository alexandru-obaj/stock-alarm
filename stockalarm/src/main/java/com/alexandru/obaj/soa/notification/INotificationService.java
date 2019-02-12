package com.alexandru.obaj.soa.notification;

/**
 * Interface specifying the contract for the {@link NotificationService} bean.
 */
public interface INotificationService {

    /**
     * Send an EMAIL message to the given address to inform upon the stock price change.
     *
     * @param emailAddress the EMAIL address to send the notification to
     * @param stockSymbol  the stock symbol in question
     * @param value        the value of the stock
     */
    void sendMail(String emailAddress, String stockSymbol, int value);
}
