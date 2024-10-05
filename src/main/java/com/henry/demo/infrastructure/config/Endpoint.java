package com.henry.demo.infrastructure.config;

/**
 * Lớp định nghĩa các <b>route</b> của API.
 *
 * @implNote Nếu bạn muốn định nghĩa thêm route mới thì hãy định nghĩa thêm vào trong này.<br><br>
 * Tin tôi đi, bạn sẽ không muốn nhìn dự án trở nên dễ hiểu quá đâu :D
 * @apiNote Do liên quan trực tiếp tới một trong những thứ quan trọng nhất của một backend, <b>Route</b>,
 * nên lớp này được ưu tiên đặt trong thư mục {@code config} thay vì {@code constant}.
 */
public final class Endpoint {

    public static final String BASE_ROUTE = "/api/v1";

    // Danh sách các route
    // Cú pháp đặt tên: <tên_model>_ROUTE
    private static final String ACTOR_ROUTE = "/actors";
    private static final String AUTHENTICATION_ROUTE = "/auth";
    private static final String COMIC_ROUTE = "/comics";
    private static final String EPISODE_ROUTE = "/episodes";
    private static final String GENRE_ROUTE = "/genres";
    private static final String IMAGE_ROUTE = "/images";
    private static final String REVIEW_ROUTE = "/reviews";
    private static final String USER_ROUTE = "/users";
    public static final String ALL_INSIDE = "/**";

    // Danh sách các tham số (thường được dùng trong @PathVariable hoặc là @RequestParams)
    // Cú pháp đặt tên: WITH_<tên_tham_số>_AS_PARAM
    public static final String WITH_ID_AS_PARAM = "/{id}";
    public static final String WITH_EMAIL_AS_PARAM = "/{email}";

    // Endpoint của `Actor`
    public static final String ACTOR = BASE_ROUTE + ACTOR_ROUTE;

    // Endpoint của `Comic`
    public static final String COMIC = BASE_ROUTE + COMIC_ROUTE;

    // Endpoint của `Episode`
    public static final String EPISODE = BASE_ROUTE + EPISODE_ROUTE;

    // Endpoint của `Episode`
    public static final String GENRE = BASE_ROUTE + GENRE_ROUTE;

    // Endpoint của `Episode`
    public static final String IMAGE = BASE_ROUTE + IMAGE_ROUTE;

    // Endpoint của `Episode`
    public static final String REVIEW = BASE_ROUTE + REVIEW_ROUTE;

    // Endpoint của `User`
    public static final String USER = BASE_ROUTE + USER_ROUTE;

    // Các endpoint liên quan tới xác thực
    public static final String AUTH = BASE_ROUTE + AUTHENTICATION_ROUTE;
    public static final String LOGIN = "/login";
    public static final String SIGNUP = "/register";
    public static final String CHANGE_PASSWORD = "/changePassword";
    public static final String USER_INFO = "/userInfo";

    // Các endpoint liên quan tới Swagger và OpenAPI
    public static final String SWAGGER_UI = "/swagger-ui";
    public static final String OPENAPI_DOCS = "/v3/api-docs";
}
