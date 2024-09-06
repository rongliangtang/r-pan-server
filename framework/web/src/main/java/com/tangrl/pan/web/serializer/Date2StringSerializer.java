package com.tangrl.pan.web.serializer;

import cn.hutool.core.date.DateUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Date;
import java.util.Objects;

/**
 * Date 转 String 的 JSON 序列化器
 * 用于返回实体Date类型字段的自动序列化
 * 保证所有日期字段在转换为 JSON 时，采用统一的字符串格式。
 */
public class Date2StringSerializer extends JsonSerializer<Date> {
    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (Objects.isNull(date)) {
            // 如果 date 为 null，则使用 StringUtils.EMPTY 写一个空字符串。
            jsonGenerator.writeString(StringUtils.EMPTY);
        } else {
            // 如果 date 不为 null，则使用 DateUtil.formatDateTime(date) 将 Date 对象格式化为字符串，并写入 JSON。
            jsonGenerator.writeString(DateUtil.formatDateTime(date));
        }
    }
}
