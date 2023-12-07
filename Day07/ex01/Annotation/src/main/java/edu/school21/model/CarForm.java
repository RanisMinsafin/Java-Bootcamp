package edu.school21.model;

import edu.school21.annotation.HtmlForm;
import edu.school21.annotation.HtmlInput;

@HtmlForm(fileName = "car.html", action = "/cars", method = "post")
public class CarForm {
    @HtmlInput(type = "text", name = "model", placeholder = "Enter the model of the car")
    private String model;
    @HtmlInput(type = "text", name = "owner", placeholder = "Enter the name of the owner")
    private String owner;
    @HtmlInput(type = "text", name = "mileage", placeholder = "Enter the mileage of the car")
    private String mileage;
}
