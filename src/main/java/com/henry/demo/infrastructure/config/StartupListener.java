package com.henry.demo.infrastructure.config;

import io.sentry.Sentry;
import io.sentry.SentryEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StartupListener implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    public void onApplicationEvent(@NonNull ApplicationReadyEvent event) {
        final String url = "http://localhost:8080" + Endpoint.SWAGGER_UI + "/index.html";
        final String os = System.getProperty("os.name").toLowerCase();

        log.info("Current detected operating system: {}", os);
        try {
            if (os.contains("win")) {
                Runtime.getRuntime().exec(TerminalCommand.WINDOWS_COMMAND + url);
            } else if (os.contains("mac")) {
                Runtime.getRuntime().exec(TerminalCommand.MACOS_COMMAND + url);
            } else if (os.contains("nix") || os.contains("nux")) {
                Runtime.getRuntime().exec(TerminalCommand.LINUX_COMMAND + url);
            }
        } catch (Exception e) {
            Sentry.captureException(e);
            Sentry.captureMessage(e.getLocalizedMessage());
            Sentry.captureEvent(new SentryEvent(e));
        }
    }
}
