package com.athleticspot.common.command;

/**
 * @author Tomasz Kasprzycki
 */
public class ResponseAwareDomainCommand<T> {

    private T response;

    public T getResponse() {
        return this.response;
    }

    public void setResponse(T response) {
        this.response = response;
    }
}
