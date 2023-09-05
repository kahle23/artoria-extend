package artoria.data.serialize.support;

import artoria.codec.CodecUtils;
import artoria.core.Serializer;
import artoria.util.Assert;
import artoria.util.StringUtils;

import static artoria.codec.CodecUtils.BASE64;

public class Base64TextSerializer implements Serializer {
    private final Serializer serializer;

    public Base64TextSerializer(SimpleSerializer serializer) {
        Assert.notNull(serializer, "Parameter \"serializer\" must not null. ");
        this.serializer = serializer;
    }

    public Base64TextSerializer() {

        this(new SimpleSerializer());
    }

    @Override
    public Object serialize(Object object) {
        if (object == null) { return null; }
        byte[] serialize = (byte[]) serializer.serialize(object);
        return CodecUtils.encodeToString(BASE64, serialize);
    }

    @Override
    public Object deserialize(Object data) {
        if (data == null) { return null; }
        String text = (String) data;
        if (StringUtils.isBlank(text)) { return null; }
        byte[] bytes = CodecUtils.decodeFromString(BASE64, (String) data);
        return serializer.deserialize(bytes);
    }

}