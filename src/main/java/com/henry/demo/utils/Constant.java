package com.henry.demo.utils;

import io.sentry.Sentry;

/**
 * Lớp chỉ chứa các hằng số dạng <b>compile-time</b> (lúc biên dịch).
 */
public final class Constant {
    /**
     * <b>DSN</b> - <b>Data Source Name</b><br><br>
     * Đây là nơi mà {@link Sentry} sẽ gửi tất cả sự kiện mà nó có thể bắt được lên dự án tương ứng. <br>
     * Thường thi bạn có thể lấy được URL trên trong lúc đang cấu hình dự án ở trên đó.
     * @implNote Tất nhiên, hãy nhớ thay đổi giá trị này sau khi bạn đã lấy được URL của riêng bạn.
     * @see <a href="https://docs.sentry.io/concepts/key-terms/dsn-explainer/">Data Source Name (DSN)</a>
     */
    public static final String DSN = "https://72fe9434424caddf9244b5612f390cb4@o4506777310986240.ingest.us.sentry.io/4508036829544448";
}
