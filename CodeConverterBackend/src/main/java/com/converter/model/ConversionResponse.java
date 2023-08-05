package com.converter.model;

public class ConversionResponse {
    private String convertedCode;

    public ConversionResponse(String convertedCode) {
        this.convertedCode = convertedCode;
    }

    // Getters and setters (or use Lombok annotations if available)
    public String getConvertedCode() {
        return convertedCode;
    }

    public void setConvertedCode(String convertedCode) {
        this.convertedCode = convertedCode;
    }
}