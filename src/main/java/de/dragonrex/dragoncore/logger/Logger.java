package de.dragonrex.dragoncore.logger;

public class Logger {
    public static void log(LoggerType type, String logMessage) {
        switch (type) {
            case INFO:
                System.out.println("§2[INFO] " + logMessage);
                break;
            case WARNING:
                System.out.println("§6[WARNING] " + logMessage);
                break;
        }
    }
}
