package com.example.demo.exception;

import lombok.Getter;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "error")
@Getter
public class ErrorResponse
{
    public ErrorResponse(String message, List<String> details) {
        super();
        this.message = message;
        this.details = details;
    }

    private String message;

    private List<String> details;

}