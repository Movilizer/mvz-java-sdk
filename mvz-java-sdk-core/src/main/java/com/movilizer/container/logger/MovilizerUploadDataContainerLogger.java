package com.movilizer.container.logger;

import com.movilitas.movilizer.v14.MovilizerGenericUploadDataContainer;
import com.movilitas.movilizer.v14.MovilizerUploadDataContainer;
import com.movilizer.util.logger.ComponentLogger;
import com.movilizer.util.logger.ILogger;

import java.text.MessageFormat;

public class MovilizerUploadDataContainerLogger {
    private static final ILogger LOGGER = ComponentLogger.getInstance(MovilizerUploadDataContainerLogger.class);

    public static void log(MovilizerUploadDataContainer movilizerUploadDataContainer) {
        LOGGER.info(MessageFormat.format("Movilizer upload data container priority is {0}", movilizerUploadDataContainer.getContainerUploadPriority()));
        MovilizerGenericUploadDataContainer movilizerUploadDataContainerContainer = movilizerUploadDataContainer.getContainer();
        if(movilizerUploadDataContainerContainer != null) {
            MovilizerGenericUploadDataContainerLogger.log(movilizerUploadDataContainerContainer);
        } else {
            LOGGER.warn("Movilizer upload data container is null");
        }
    }
}
