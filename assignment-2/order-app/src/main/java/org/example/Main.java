package org.example;

import org.example.service.AppService;

public class Main {
    public static void main(String[] args) {
        AppService appService = new AppService();
        appService.run();
    }
}