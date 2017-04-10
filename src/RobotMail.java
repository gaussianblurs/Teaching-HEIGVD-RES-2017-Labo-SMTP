import config.ConfigurationManager;
import model.prank.Prank;
import model.prank.PrankGenerator;
import smtp.ISmtpClient;
import smtp.SmtpClient;

import java.io.IOException;
import java.util.List;

/**
 * Created by Arnold von Bauer Gauss on 08.04.2017.
 */
public class RobotMail {
    public static void main(String[] args) throws IOException {
        ISmtpClient smtpClient = new SmtpClient("localhost", 25);
        PrankGenerator pg = new PrankGenerator(new ConfigurationManager());
        List<Prank> pranks = pg.generatePranks();
        for(Prank prank : pranks) {
            smtpClient.sendMessage(prank.generateMailMessage());
        }
    }
}
