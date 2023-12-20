package edu.school21.preprocessor;

import org.springframework.stereotype.Component;

@Component("preProcessorToLower")
public class PreProcessorToLower implements  PreProcessor{
    @Override
    public String process(String message) {
        return message.toLowerCase();
    }
}
