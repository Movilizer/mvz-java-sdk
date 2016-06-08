package com.movilizer.container.logger;

import com.movilitas.movilizer.v14.MovilizerGenericDataContainer;
import com.movilitas.movilizer.v14.MovilizerGenericUploadDataContainer;
import com.movilizer.util.logger.ComponentLogger;
import com.movilizer.util.logger.ILogger;

import static com.movilizer.util.string.StringUtils.*;

public class MovilizerGenericUploadDataContainerLogger {
    private static final ILogger LOGGER = ComponentLogger.getInstance(MovilizerGenericUploadDataContainerLogger.class);

    public static void log(MovilizerGenericUploadDataContainer movilizerGenericUploadDataContainer) {
        String message = joinOmitEmpties(", "
                , keyToPrettyPrint(movilizerGenericUploadDataContainer)
                , moveletKeyToPrettyPrint(movilizerGenericUploadDataContainer)
                , moveletKeyExtensionToPrettyPrint(movilizerGenericUploadDataContainer)
                , participantKeyToPrettyPrint(movilizerGenericUploadDataContainer));
        LOGGER.info(formatNoLocale("Movilizer generic upload data container: {0}", message));
        MovilizerGenericDataContainer movilizerGenericDataContainer = movilizerGenericUploadDataContainer.getData();
        MovilizerGenericDataContainerLogger.log(movilizerGenericDataContainer);
    }

    private static String keyToPrettyPrint(MovilizerGenericUploadDataContainer movilizerGenericUploadDataContainer) {
        String key = movilizerGenericUploadDataContainer.getKey();
        return isNullOrEmpty(key) ? null : formatNoLocale("key is {0}", key);
    }

    private static String moveletKeyToPrettyPrint(MovilizerGenericUploadDataContainer movilizerGenericUploadDataContainer) {
        String moveletKey = movilizerGenericUploadDataContainer.getMoveletKey();
        return isNullOrEmpty(moveletKey) ? null : formatNoLocale("movelet key is {0}", moveletKey);
    }

    private static String moveletKeyExtensionToPrettyPrint(MovilizerGenericUploadDataContainer movilizerGenericUploadDataContainer) {
        String moveletKeyExtension = movilizerGenericUploadDataContainer.getMoveletKeyExtension();
        return isNullOrEmpty(moveletKeyExtension) ? null : formatNoLocale("movelet key extension is {0}", moveletKeyExtension);
    }

    private static String participantKeyToPrettyPrint(MovilizerGenericUploadDataContainer movilizerGenericUploadDataContainer) {
        String participantKey = movilizerGenericUploadDataContainer.getParticipantKey();
        return isNullOrEmpty(participantKey) ? null : formatNoLocale("participant key is {0}", participantKey);
    }
}
