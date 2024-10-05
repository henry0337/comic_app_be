package com.henry.demo.infrastructure.constant;

import io.sentry.Sentry;

public final class SentryConstant {
    /**
     * <b>DSN</b> - <b>Data Source Name</b><br><br>
     * Đây là nơi mà {@link Sentry} sẽ gửi tất cả sự kiện mà nó có thể bắt được lên dự án tương ứng. <br>
     * Thường thi bạn có thể lấy được URL trên trong lúc đang cấu hình dự án ở trên đó.
     * @implNote Tất nhiên, hãy nhớ thay đổi giá trị này sau khi bạn đã lấy được URL của riêng bạn. <br>
     * Nếu như bạn đang làm với team và được cung cấp URL này thì có thể không cần thay, có thể do đây là URL chung của cả team rồi.
     * @see <a href="https://docs.sentry.io/concepts/key-terms/dsn-explainer/">Data Source Name (DSN)</a>
     */
    public static final String DSN = "https://72fe9434424caddf9244b5612f390cb4@o4506777310986240.ingest.us.sentry.io/4508036829544448";
}
