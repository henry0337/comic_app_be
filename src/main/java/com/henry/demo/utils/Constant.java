package com.henry.demo.utils;

import io.sentry.Sentry;

public final class Constant {
    /**
     * Địa chỉ nơi mà {@link Sentry} sẽ gửi tất cả sự kiện mà nó có thể bắt được lên. <br>
     * Thường thi bạn có thể lấy được URL trên thông qua việc cấu hình dự án ở trên đó.
     * @apiNote Tất nhiên hãy thay đổi giá trị này sau khi bạn đã lấy được URL của riêng bạn.
     */
    public static final String DSN = "https://72fe9434424caddf9244b5612f390cb4@o4506777310986240.ingest.us.sentry.io/4508036829544448";
}
