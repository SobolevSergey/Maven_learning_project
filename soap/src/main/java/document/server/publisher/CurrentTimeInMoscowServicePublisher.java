package document.server.publisher;

import document.server.impl.CurrentTimeInMoscowImpl;

import javax.xml.ws.Endpoint;

//Endpoint publisher
public class CurrentTimeInMoscowServicePublisher {

    public static void main(String[] args) {
        Endpoint.publish("http://localhost:9998/ws/time", new CurrentTimeInMoscowImpl());
    }
}
