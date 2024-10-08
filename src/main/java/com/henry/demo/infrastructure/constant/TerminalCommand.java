package com.henry.demo.infrastructure.constant;

/**
 * Lớp chứa các lệnh tương tác trong CLI của các hệ điều hành.
 */
public final class TerminalCommand {

    /**
     * Lệnh này dùng để khởi động một cửa sổ {@code Command Prompt} riêng biệt để khởi chạy chương trình cụ thể hoặc câu lệnh.
     * @apiNote Chỉ hoạt động trên hệ điều hành <a href="https://vi.wikipedia.org/wiki/Microsoft_Windows">Windows</a>.
     * @see <a href="https://learn.microsoft.com/en-us/windows-server/administration/windows-commands/start">Windows Server's administration command</a>
     */
    public static final String WINDOWS_START_COMMAND = "cmd /c start ";

    /**
     * Lệnh này dùng để mở một ứng dụng cụ thể.
     * @apiNote Chỉ hoạt động trên hệ điều hành <a href="https://vi.wikipedia.org/wiki/MacOS">MacOS</a>.
     * @see <a href="https://support.apple.com/en-vn/guide/terminal/apdb66b5242-0d18-49fc-9c47-a2498b7c91d5/mac">Execute commands and run tools in Terminal on Mac</a>
     */
    public static final String MACOS_OPEN_COMMAND = "open ";

    /**
     * Lệnh này dùng để mở một tệp hoặc URL trong ứng dụng được ưu tiên của người dùng.
     * @apiNote Chỉ hoạt động trên các các bản phân phối của hệ điều hành <a href="https://www.linux.org/">Linux</a>.
     * @see <a href="https://man.archlinux.org/man/xdg-open.1.en">Arch Linux's manual page</a>
     */
    public static final String LINUX_OPEN_COMMAND = "xdg-open ";
}
