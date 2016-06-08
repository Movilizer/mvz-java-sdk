package com.movilizer.container.logger;

import com.movilitas.movilizer.v14.MovilizerGenericDataContainer;
import com.movilitas.movilizer.v14.MovilizerGenericDataContainerEntry;
import com.movilizer.util.logger.ComponentLogger;
import com.movilizer.util.logger.ILogger;

import java.text.MessageFormat;
import java.util.List;

import static com.movilizer.util.string.StringUtils.formatNoLocale;

public class MovilizerGenericDataContainerLogger {
    private static final ILogger LOGGER = ComponentLogger.getInstance(MovilizerGenericDataContainerLogger.class);

    public static void log(MovilizerGenericDataContainer movilizerGenericDataContainer) {
        List<MovilizerGenericDataContainerEntry> movilizerGenericDataContainerEntries = movilizerGenericDataContainer.getEntry();
        if (movilizerGenericDataContainerEntries != null && movilizerGenericDataContainerEntries.size() > 0) {
            LOGGER.info(MessageFormat.format("{0} entries in movilizer generic data container", movilizerGenericDataContainerEntries.size()));
            for (MovilizerGenericDataContainerEntry movilizerGenericDataContainerEntry : movilizerGenericDataContainerEntries) {
                MovilizerGenericDataContainerEntryLogger.log(movilizerGenericDataContainerEntry);
            }
        } else {
            LOGGER.warn(formatNoLocale("No entries in movilizer generic data container"));
        }
    }
}
