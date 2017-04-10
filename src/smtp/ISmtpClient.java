package smtp;

import model.mail.Message;

import java.io.IOException;

/**
 * Created by Arnold von Bauer Gauss (GaussianBlurs) on 08.04.2017.
 */
public interface ISmtpClient {
    public void sendMessage(Message message) throws IOException;
}
