
<h1 align="center" style="font-weight: bold;">Comic App 💻 (nhưng là backend)</h1>

<p align="center">
<a href="#tech">Công nghệ</a> |
<a href="#started">Yêu cầu tiên quyết</a> |
<a href="#routes">Endpoint API</a> |
<a href="#colab">Thành viên tham gia</a>

</p>


<p align="center">Đây là dự án backend được thiết kế để sử dụng cho dự án Flutter: <a href="https://github.com/henry0337/flutter_comic_app">Comic App</a></p>

<h2 id="technologies">💻 Công nghệ</h2>

- **Spring Boot** (v3.3.4)
- **PostgreSQL** (v16.0)
- Xác thực **JSON Web Token (JWT)**
- **Swagger & OpenAPI Specification (OAS)** (v3.1)
- **Gradle** (v8.10)
- **Docker** (v27) & **Docker Compose** (v2.29)
- **Sentry** (v4.11)

<h3 id="started">Yêu cầu tiên quyết</h3>
Đảm bảo bạn có những thứ phía dưới trước khi bắt đầu dự án:

- [JDK 17](https://www.oracle.com/java/technologies/downloads/#java17)
- Bất cứ IDE nào hỗ trợ viết mã Java ([IntelliJ IDEA](https://www.jetbrains.com/idea/), [Eclipse](https://www.eclipse.org/downloads/) hoặc [NetBeans](https://netbeans.apache.org/front/main/index.html)), ngoại trừ [Android Studio](https://developer.android.com/studio).
- (Tùy chọn) [Docker Engine](https://docs.docker.com/engine/) hoặc [Docker Desktop](https://docs.docker.com/desktop/) (**Docker Desktop** đã bao gồm **Docker Engine**)
- Nên dùng **Windows 10, 11 Professional** hoặc **Enterprise**<br> (do Docker sẽ hỗ trợ tốt hơn trên 2 bản này.)
- Nếu dùng **Linux** thì nên dùng Ubuntu 22.04 (do Docker chưa hỗ trợ chính thức phiên bản LTS mới nhất (Ubuntu v24.04)).

<h3>Nhân bản dự án</h3>

Dùng lệnh sau trong Terminal để nhân bản dự án về thiết bị của bạn:

```bash
git clone https://github.com/henry0337/comic_app_be.git
```

<h3>Cấu hình biến môi trường</h2>

(Sau khi đã nhân bản dự án về, hãy nhớ **dừng** đồng bộ dự án trước khi tiếp tục.)<br>
Đầu tiên hãy tạo tệp `local.properties`, sau đó thêm vào trong nội dung sau:
```properties
SENTRY_AUTH_TOKEN=mã_token_của_Sentry_ở_đây
JWT_ENCODED_KEY=37405a74de628d7d66e7af2ce4aea13076b382685498876bca5e76cb4a8a73f4
```
về phần cấu hình để lấy mã xác thực của Sentry, hãy vào [đây](https://sentry.io/) để tự tạo cho mình một dự án trước, hoặc bạn có thể lấy mã xác thực này từ dự án có nếu đã có trước đó.<br><br>
Sau khi đã có tệp này thì hãy chạy lệnh build trong Terminal:
```bash
# Cấp quyền thực thi tệp này cho người dùng hiện tại đang tương tác
# (Chỉ những ai dùng Linux mới cần chạy dòng này, áp dụng cho toàn bộ user)
sudo chmod +x ./gradlew
./gradlew build
```

<h3>Khởi động dự án</h3>

**NHẮC NHỞ QUAN TRỌNG**: Dự án này đang sử dụng [Docker](https://www.docker.com/) và [Docker Compose](https://docs.docker.com/compose/) để cấu hình và khởi chạy dự án. Nếu như bạn KHÔNG có ý định dùng Docker thì đừng chạy lệnh build như phía trên vội, mà hãy mở tệp `build.gradle.kts`, sau đó tìm đến dòng sau rồi comment lại để vô hiệu hóa Docker:
```kts
...

dependencies {
    ...
    // Comment dòng này và mọi thứ sẽ trở lại bình thường :v
    developmentOnly(libs.docker.compose.support)

    ...
}

...
```
sau đó bạn có thể rebuild mà không gặp vấn đề gì.

Còn nếu bạn chọn dùng **Docker** và bạn đang dùng hệ điều hành **Windows**, hãy đảm bảo rằng cấu hình phần cứng thiết bị của bạn có thể đáp ứng yêu cầu tối thiểu để khởi chạy Docker và WSL cùng lúc (do Docker sử dụng [Hyper-V](https://learn.microsoft.com/en-us/virtualization/hyper-v-on-windows/about/) hoặc là [Windows Subsystem for Linux](https://learn.microsoft.com/en-us/windows/wsl/about) làm máy ảo), do đó Docker tiêu thụ khá đáng kể tài nguyên của thiết bị.<br> (Toàn bộ lõi của CPU và ~6-8GB RAM sẽ được sử dụng nếu bạn không cấu hình WSL để giới hạn tài nguyên.)<br>

Hãy nhớ kiểm tra tệp `compose.yaml` để chỉnh sửa thông tin liên quan đến cơ sở dữ liệu PostgreSQL của bạn như tên cơ sở dữ liệu, tài khoản và mật khẩu.<br>

Tiếp theo tìm đến tệp `applicaton.properties.txt` nằm trong thư mục `src/main/resources`, thay đổi tên file thành `applicaton.properties`, cuối cùng thay đổi hết giá trị các thuộc tính bắt đầu bằng `spring.datasource.*` thành giá trị mà bạn muốn. <br>

Sau khi hoàn thành đủ các bước như trên, bạn có thể chạy các lệnh sau trong Terminal để khởi chạy dự án:
```bash
# Nếu như bạn không dùng Docker
./gradlew build -x test
./gradlew bootRun

# Nếu như bạn dùng Docker
docker compose up --watch
```

<h2 id="colab">🤝 Thành viên tham gia</h2>

<p>Cảm ơn những thành viên đã đóng góp vào dự án này:</p>
<table>
<tr>

<td align="center">
<a href="https://github.com/Hoanganhtudz">
<img src="https://avatars.githubusercontent.com/u/105731691?v=4" width="100px;" alt="Hoàng Anh Tú Profile Picture"/><br>
<sub>
<b>Hoàng Anh Tú</b>
</sub>
</a>
</td>

<td align="center">
<a href="https://github.com/henry0337">
<img src="https://avatars.githubusercontent.com/u/147426864?v=4" width="100px;" alt="Quốc Hưng Profile Picture"/><br>
<sub>
<b>Quốc Hưng (tôi)</b>
</sub>
</a>
</td>

</tr>
</table>

<h3>Tài liệu tham khảo thêm</h3>

- [Spring Boot](https://spring.io/projects/spring-boot)

- [Sentry](https://sentry.io/)

- [MapStruct](https://mapstruct.org/)
