package com.github.scormaq;

import java.io.Serializable;

import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Core;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;

@Plugin(name = "CucumberLog4j2ParallelAppender", category = Core.CATEGORY_NAME, elementType = Appender.ELEMENT_TYPE)
public final class CucumberLog4j2ParallelAppender extends AbstractAppender {

    private CucumberLog4j2ParallelAppender(String name, Filter filter, Layout<? extends Serializable> layout) {
        super(name, filter, layout);
    }

    @PluginFactory
    public static CucumberLog4j2ParallelAppender createAppender(
        @PluginAttribute("name") String name,
        @PluginElement("filter") Filter filter,
        @PluginElement("layout") Layout<? extends Serializable> layout) {
        return new CucumberLog4j2ParallelAppender(name, filter, layout);
    }

    @Override
    public void append(LogEvent event) {
        PrettyLogFormatter.appendLogs(getLayout().toByteArray(event));
    }
}
