package io.github.blobanium.lt.util.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TimeLogger {
    private static final Logger LOGGER = LogManager.getLogger("Loading Timer");

    public static void loggerMessage(int messageSelector, double variable, String extraText) {
        if (messageSelector == 1) LOGGER.info("Minecraft took {} seconds to initialize.", variable);
        if (messageSelector == 2) LOGGER.info("Minecraft took {} seconds to fully load.", variable);
        if (messageSelector == 3) LOGGER.info("That is {} seconds worth of raw loading time{}", variable, extraText);
        if (!(messageSelector >= 1 && messageSelector <= 3)) {
            LOGGER.fatal("An IndexOutOfBoundsException has occurred, int messageSelector: {} (Expected range: 1-3)", messageSelector);
            Thread.dumpStack();
        }
    }
}
