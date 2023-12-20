package edu.school21.preprocessor;

import org.springframework.stereotype.Component;

@Component("preProcessorToUpper")
public class PreProcessorToUpperImpl implements PreProcessor {
    @Override
    public String process(String message) {
        return message.toUpperCase();
    }
}
