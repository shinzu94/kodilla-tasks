package com.crud.tasks;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;

import java.util.ArrayList;

public class TestLogAppender extends AppenderBase<ILoggingEvent> {

    private final ArrayList<ILoggingEvent> loggingEvents = new ArrayList<>();

    @Override
    protected void append(ILoggingEvent eventObject) {
        loggingEvents.add(eventObject);
    }

    public ILoggingEvent getLastLoggedEvent() {
        return loggingEvents.isEmpty() ?
                null :
                loggingEvents.get(loggingEvents.size() - 1);
    }
}
