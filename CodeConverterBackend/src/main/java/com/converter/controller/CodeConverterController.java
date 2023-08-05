package com.converter.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.converter.model.CodeRequest;
import com.converter.model.ConversionResponse;

@RestController
public class CodeConverterController {

    @PostMapping("/convert")
    public ConversionResponse convertCode(@RequestParam String source, @RequestParam String target, @RequestBody CodeRequest codeRequest) {
        // Call the ChatGPT API to perform code conversion
        String convertedCode = callChatGPTAPI(source, target, codeRequest.getCode());
        return new ConversionResponse(convertedCode);
    }

    private String callChatGPTAPI(String source, String target, String code) {
        // Replace the following values with your actual API endpoint and authentication details
        String apiUrl = "https://api.chatgpt.com/convert";
        String apiKey = "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\r\n"
        		+ "";

        RestTemplate restTemplate = new RestTemplate();

        // Prepare headers with API key (if required)
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Prepare the request body with code and language details
        CodeRequest request = new CodeRequest();
        request.setCode(code);

        // Prepare the request entity with headers and body
        HttpEntity<CodeRequest> requestEntity = new HttpEntity<>(request, headers);

        // Send the POST request to the ChatGPT API
        ResponseEntity<ConversionResponse> responseEntity = restTemplate.exchange(
                apiUrl + "?source=" + source + "&target=" + target,
                HttpMethod.POST,
                requestEntity,
                ConversionResponse.class
        );

        // Retrieve the converted code from the response
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return responseEntity.getBody().getConvertedCode();
        } else {
            // Handle error cases
            return "Error occurred during code conversion.";
        }
    }
}
