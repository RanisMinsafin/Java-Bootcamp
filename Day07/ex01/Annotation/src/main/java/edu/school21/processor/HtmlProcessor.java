package edu.school21.processor;

import edu.school21.annotation.HtmlForm;
import edu.school21.annotation.HtmlInput;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

@SupportedSourceVersion(SourceVersion.RELEASE_20)
@SupportedAnnotationTypes("edu.school21.annotation.HtmlForm")
public class HtmlProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(HtmlForm.class)) {
            if (element.getKind() == ElementKind.CLASS) {
                createHtml((TypeElement) element);
            }
        }
        return true;
    }

    private void createHtml(TypeElement classElement) {
        HtmlForm htmlForm = classElement.getAnnotation(HtmlForm.class);
        String fileName = htmlForm.fileName();
        String action = htmlForm.action();
        String method = htmlForm.method();

        StringBuilder htmlContent = new StringBuilder();
        htmlContent.append("<form action=\"").
                append(action).append("\" method=\"").
                append(method).append("\">\n");

        for (Element element : classElement.getEnclosedElements()) {
            if (element.getKind() == ElementKind.FIELD) {
                createHtmlInput(htmlContent, element);
            }
        }
        htmlContent.append("\t<input type=\"submit\" value=\"Send\">\n");
        htmlContent.append("</form>");
        saveAsFile(fileName, htmlContent);
    }

    private void createHtmlInput(StringBuilder htmlContent, Element element) {
        HtmlInput htmlInput = element.getAnnotation(HtmlInput.class);
        String type = htmlInput.type();
        String name = htmlInput.name();
        String placeholder = htmlInput.placeholder();

        htmlContent.append("\t<input type=\"").append(type)
                .append("\" name=\"").append(name)
                .append("\" placeholder=\"").append(placeholder)
                .append("\">\n");
    }

    private void saveAsFile(String fileName, StringBuilder htmlContent) {
        try {
            File file = new File("target/classes/" + fileName);
            try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))){
                bufferedWriter.write(htmlContent.toString());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
