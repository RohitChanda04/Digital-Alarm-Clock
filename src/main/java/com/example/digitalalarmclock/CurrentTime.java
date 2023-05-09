package com.example.digitalalarmclock;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class CurrentTime {

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
    LocalDateTime timeNow = LocalDateTime.now();

    public CurrentTime() {
    }

    public String currentTime() {
        return dtf.format(timeNow);
    }

}
