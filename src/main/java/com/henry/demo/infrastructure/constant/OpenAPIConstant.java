package com.henry.demo.infrastructure.constant;

public final class OpenAPIConstant {

    /**
     * Phiên bản của <b>OpenAPI Specification</b>.
     * @apiNote Chỉ có thể là các phiên bản sau: {@code 1.2}, {@code 2.0}, {@code 3.0.0}, {@code 3.0.1}, {@code 3.0.2}, {@code 3.0.3}, {@code 3.1.0}
     * @see <a href="https://github.com/OAI/OpenAPI-Specification/tree/main/versions">OpenAPI-Specification's version</a>
     */
    public static final String OPENAPI_VERSION = "3.1.0";

    /**
     * Tiêu đề của API sẽ được hiển thị lên Swagger UI.
     */
    public static final String TITLE = "ComicApp";

    /**
     * Phiên bản hiện tại của API.<br>Luôn nên thay đổi giá trị này mỗi khi cấu trúc API thay đổi.
     */
    public static final String API_VERSION = "1.0";
}
