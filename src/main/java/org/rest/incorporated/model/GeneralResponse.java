package org.rest.incorporated.model;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class GeneralResponse {
    private HttpStatus httpMethod;
    private String urlPath;
    private String currentTime;
    private String moreResources;
    private String message;
    private Object object;
    public GeneralResponse(HttpStatus httpMethod, String urlPath, String currentTime, String moreResources, String message, Object object) {
        this.httpMethod = httpMethod;
        this.urlPath = urlPath;
        this.currentTime = currentTime;
        this.moreResources = moreResources;
        this.message = message;
        this.object = object;
    }

    public GeneralResponse() {
    }

    public HttpStatus getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(HttpStatus httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getUrlPath() {
        return urlPath;
    }

    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getMoreResources() {
        return moreResources;
    }

    public void setMoreResources(String moreResources) {
        this.moreResources = moreResources;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return "GeneralResponse{" +
                "httpMethod=" + httpMethod +
                ", urlPath='" + urlPath + '\'' +
                ", currentTime='" + currentTime + '\'' +
                ", moreResources='" + moreResources + '\'' +
                ", message='" + message + '\'' +
                ", object=" + object +
                '}';
    }
}
