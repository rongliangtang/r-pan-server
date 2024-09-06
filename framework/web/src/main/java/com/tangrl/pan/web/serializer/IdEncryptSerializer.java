package com.tangrl.pan.web.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.tangrl.pan.core.utils.IdUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Objects;

/**
 * Id 自动加密的 JSON 序列化器
 * 用于返回实体 Long 类型 ID 字段的自动序列化
 * 控制 Long 类型的 ID 字段在序列化为 JSON 时的加密处理，保护 ID 敏感信息的安全
 */
public class IdEncryptSerializer extends JsonSerializer<Long> {
    /**
     * Method that can be called to ask implementation to serialize
     * values of type this serializer handles.
     *
     * @param value       Value to serialize; can <b>not</b> be null.
     * @param gen         Generator used to output resulting Json content
     * @param serializers Provider that can be used to get serializers for
     *                    serializing Objects value contains, if any.
     */
    @Override
    public void serialize(Long value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (Objects.isNull(value)) {
            // 如果 value 为 null，则使用 StringUtils.EMPTY 写一个空字符串。
            gen.writeString(StringUtils.EMPTY);
        } else {
            // 如果 value 不为 null，则使用 IdUtil.encrypt(value) 将 Long 值加密，并写入 JSON。
            gen.writeString(IdUtil.encrypt(value));
        }
    }

}
