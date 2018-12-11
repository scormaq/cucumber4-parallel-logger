package com.github.scormaq;

import static java.nio.charset.Charset.defaultCharset;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import cucumber.api.event.ConcurrentEventListener;
import cucumber.api.event.EventHandler;
import cucumber.api.event.EventPublisher;
import cucumber.api.event.TestCaseFinished;

public final class PrettyLogFormatter extends AbstractPrettyFormatter implements ConcurrentEventListener {

    private static final Map<Long, List<byte[]>> THREAD_LOGS = new ConcurrentHashMap<>();

    private EventHandler<TestCaseFinished> caseFinishedHandler = new EventHandler<TestCaseFinished>() {
        @Override
        public void receive(TestCaseFinished event) {
            handleTestCaseFinished();
        }
    };

    @SuppressWarnings("WeakerAccess") // Used by PluginFactory
    public PrettyLogFormatter(Appendable out) {
        super(out);
    }

    @Override
    public void setEventPublisher(EventPublisher publisher) {
        super.setEventPublisher(publisher);
        publisher.registerHandlerFor(TestCaseFinished.class, caseFinishedHandler);
    }

    static void appendLogs(byte[] logEntry) {
        Long currentThreadId = Thread.currentThread().getId();
        if (!THREAD_LOGS.containsKey(currentThreadId)) {
            THREAD_LOGS.put(currentThreadId, new ArrayList<byte[]>());
        }
        THREAD_LOGS.get(currentThreadId).add(logEntry);
    }

    @Override
    protected void printlnLog(String logEntry) {
        appendLogs(logEntry.getBytes());
    }

    @Override
    void finishReport() {
        THREAD_LOGS.clear();
    }

    protected void handleTestCaseFinished() {
        Long currentThreadId = Thread.currentThread().getId();
        for (byte[] log : THREAD_LOGS.get(currentThreadId)) {
            out().println(new String(log, defaultCharset()));
        }
        THREAD_LOGS.get(currentThreadId).clear();
    }
}
