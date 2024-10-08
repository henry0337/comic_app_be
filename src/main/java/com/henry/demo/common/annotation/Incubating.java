package com.henry.demo.common.annotation;

import java.lang.annotation.*;

/**
 * Đánh dấu lớp, phương thức hoặc thuộc tính vẫn đang trong trạng thái phát triển và có thể sẽ được thay đổi hoặc loại bỏ bất cứ lúc nào.
 */
@Target({
        ElementType.FIELD,
        ElementType.TYPE,
        ElementType.METHOD
})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Incubating {
    String message() default "Lớp, phương thức hoặc thuộc tính này đang trong trạng thái phát triển, có thể sẽ thay đổi vào lúc nào đó.";

    /**
     * Thông báo với người dùng rằng lớp, phương thức hoặc thuộc tính được đánh dấu này sẽ bị xóa trong phiên bản nào đó kế tiếp.
     * @return {@code true} nếu như tính năng sẽ bị loại bỏ, không thì {@code false}
     */
    boolean forRemoval() default false;
}
