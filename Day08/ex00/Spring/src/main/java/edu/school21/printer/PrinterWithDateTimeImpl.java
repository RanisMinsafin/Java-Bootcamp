package edu.school21.printer;

import edu.school21.renderer.Renderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component("printerWithDateTime")
public class PrinterWithDateTimeImpl implements Printer {
    private Renderer renderer;

    @Autowired
    public PrinterWithDateTimeImpl(@Qualifier("rendererStandard")
                                       Renderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public void print(String message) {
        LocalDateTime dateTime = LocalDateTime.now();
        String formattedDateTime = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String messageWithDateTime = formattedDateTime + " " + message;
        renderer.renderMessage(messageWithDateTime);
    }
}
