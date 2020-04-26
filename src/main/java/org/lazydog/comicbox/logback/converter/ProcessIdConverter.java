package org.lazydog.comicbox.logback.converter;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import java.lang.management.ManagementFactory;

/**
 * Process ID converter.
 * 
 * @author rjrjr
 */
public class ProcessIdConverter extends ClassicConverter {
    
    private static final String PROCESS_ID = ManagementFactory.getRuntimeMXBean().getName().split("@")[0];
    
    @Override
    public String convert(final ILoggingEvent event) {
        return PROCESS_ID;
    }
}
