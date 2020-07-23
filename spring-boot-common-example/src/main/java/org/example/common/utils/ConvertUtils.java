package org.example.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Field;
import java.util.Arrays;

@Slf4j
public class ConvertUtils {

    /**
     * obj 안에 속성들의 값을 이어 붙인다.
     * @param obj
     * @param separator
     * @return
     */
    public static String convertObjToString(Object obj, String separator) {
        StringBuilder sb = new StringBuilder();
        Field[] fields = obj.getClass().getDeclaredFields();
        Arrays.stream(fields).forEach(field -> {
            try {
                /** private 접근 가능 */
                field.setAccessible(true);
                Object tmp = field.get(obj);
                if ( !ObjectUtils.isEmpty(tmp)) {
                    sb.append(field.get(obj));
                    sb.append(separator);
                }
            } catch (IllegalAccessException e) {
                log.error(e.getMessage(), e);
                throw new RuntimeException(e);
            }
        });
        return sb.length() == 0 ? "" : sb.deleteCharAt(sb.length() - 1).toString();
    }
}
