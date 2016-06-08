package com.movilizer.container.logger;

import com.google.common.base.Splitter;
import com.movilitas.movilizer.v14.MovilizerGenericDataContainerEntry;
import com.movilizer.util.logger.ComponentLogger;
import com.movilizer.util.logger.ILogger;

import java.text.MessageFormat;

/**
 * @author philippe.guillamet@gdfsuez.com
 */
public class MovilizerGenericDataContainerEntryLogger {
    private static final ILogger LOGGER = ComponentLogger.getInstance(MovilizerGenericDataContainerEntryLogger.class);

    public static void log(MovilizerGenericDataContainerEntry movilizerGenericDataContainerEntry) {
        String name = movilizerGenericDataContainerEntry.getName();
        String value = movilizerGenericDataContainerEntry.getValstr();
        LOGGER.trace(MessageFormat.format("Movilizer generic data container entry name is: {0}", name));
        LOGGER.trace("Movilizer generic data container entry value is: ");
        for (final String token : Splitter
                .fixedLength(3000)
                .split(value)) {
            LOGGER.trace(token);
        }
    }
}
