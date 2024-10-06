package com.henry.demo.utility;

import io.sentry.Sentry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.lang.Nullable;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Slf4j
public final class JwtHelper {

    /**
     * Lấy ra mã bí mật có độ dài 256-bit nguyên bản từ tệp {@code local.properties}.
     * @return Mã bí mật 256-bit nếu có, trả về {@code null} nếu như biến {@code JWT_ENCODED_KEY} không được thiết lập hoặc không tồn tại.
     * @apiNote {@code local.properties} là tệp được chính bạn tạo ra, không phải là tệp {@code application.properties} do {@link SpringBootApplication Spring Boot} quản lý.
     * Hãy đọc tệp {@code README.md} để biết rõ hơn.
     */
    @Nullable
    public static String findBareTokenInProperty() {
        var props = new Properties();
        try {
            props.load(new FileInputStream(System.getProperty("user.dir") + "\\local.properties"));
            return props.getProperty("JWT_ENCODED_KEY");
        } catch (IOException e) {
            Sentry.captureException(e);
            Sentry.captureMessage(e.getLocalizedMessage());
            log.error("Cõ lỗi xảy ra khi thực hiện load tệp: ", e);
        }
        return null;
    }
}
