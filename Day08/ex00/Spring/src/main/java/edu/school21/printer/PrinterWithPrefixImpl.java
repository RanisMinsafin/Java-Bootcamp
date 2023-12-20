package edu.school21.printer;

import edu.school21.renderer.Renderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("printerWithPrefix")
public class PrinterWithPrefixImpl implements Printer{
    private Renderer renderer;

    @Autowired
    public PrinterWithPrefixImpl(@Qualifier("rendererStandard")
                                     Renderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public void print(String message) {
        String messageWithPrefix = "prefix " + message;
        renderer.renderMessage(messageWithPrefix);
    }
}
