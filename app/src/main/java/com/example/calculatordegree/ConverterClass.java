package com.example.calculatordegree;

public class ConverterClass {

    public double celsiusToFahrenheit(double celsius) {
        return (celsius*1.8)+32;
    }

    public double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit-32)/32;
    }
}
