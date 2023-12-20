package edu.school21.renderer;

import edu.school21.preprocessor.PreProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("rendererStandard")
public class RendererStandardImpl implements Renderer{
    private PreProcessor preProcessor;

    @Autowired
    public RendererStandardImpl(@Qualifier("preProcessorToUpper")
                                    PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }

    @Override
    public void renderMessage(String message) {
        String processedMessage = preProcessor.process(message);
        System.out.println(processedMessage);
    }
}
