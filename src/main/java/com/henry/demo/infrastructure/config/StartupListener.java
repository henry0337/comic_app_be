package com.henry.demo.infrastructure.config;

import com.henry.demo.infrastructure.constant.TerminalCommand;
import io.sentry.Sentry;
import io.sentry.SentryEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Slf4j
@Component
//@Profile({"dev"})
public class StartupListener implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    public void onApplicationEvent(@NonNull ApplicationReadyEvent event) {
        final String swagger = "http://localhost:8080" + Endpoint.SWAGGER_UI + "/index.html";
        final String os = System.getProperty("os.name").toLowerCase();

        try {
            if (os.contains("win")) {
                Runtime.getRuntime().exec(TerminalCommand.WINDOWS_START_COMMAND + swagger);
            } else if (os.contains("mac")) {
                Runtime.getRuntime().exec(TerminalCommand.MACOS_OPEN_COMMAND + swagger);
            } else if (os.contains("nix") || os.contains("nux")) {
                Runtime.getRuntime().exec(TerminalCommand.LINUX_OPEN_COMMAND + swagger);
            }
        } catch (Exception e) {
            Sentry.captureException(e);
            Sentry.captureMessage(e.getLocalizedMessage());
            Sentry.captureEvent(new SentryEvent(e));
        }
    }
}
