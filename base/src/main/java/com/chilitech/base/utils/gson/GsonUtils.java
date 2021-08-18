//package com.chilitech.base.utils.gson;
//
//
//import android.util.JsonReader;
//import android.util.JsonToken;
//import android.util.JsonWriter;
//
//import java.io.IOException;
//import java.lang.reflect.Type;
//
//
///**
// * @描述 gson解析类，防止空指针异常，防止类int型转换异常
// **/
//public class GsonUtils {
//    static class DoubleDefault0Adapter implements JsonSerializer<Double>,
//            JsonDeserializer<Double> {
//        @Override
//        public Double deserialize(JsonElement json, Type typeOfT,
//                                  JsonDeserializationContext context)
//                throws JsonParseException {
//            try {
//                if ("".equals(json.getAsString())) {
//                    return 0D;
//                }
//            } catch (Exception ignore) {
//            }
//            try {
//                return json.getAsDouble();
//            } catch (NumberFormatException e) {
//                throw new JsonSyntaxException(e);
//            }
//        }
//
//        @Override
//        public JsonElement serialize(Double src, Type typeOfSrc, JsonSerializationContext
//                context) {
//            return new JsonPrimitive(src);
//        }
//    }
//
//    static class FloatDefault0Adapter implements JsonSerializer<Float>,
//            JsonDeserializer<Float> {
//        @Override
//        public Float deserialize(JsonElement json, Type typeOfT,
//                                 JsonDeserializationContext context)
//                throws JsonParseException {
//            try {
//                if ("".equals(json.getAsString())) {
//                    return 0F;
//                }
//            } catch (Exception ignore) {
//            }
//            try {
//                return json.getAsFloat();
//            } catch (NumberFormatException e) {
//                throw new JsonSyntaxException(e);
//            }
//        }
//
//        @Override
//        public JsonElement serialize(Float src, Type typeOfSrc, JsonSerializationContext
//                context) {
//            return new JsonPrimitive(src);
//        }
//    }
//
//    static class IntegerDefault0Adapter implements JsonSerializer<Integer>,
//            JsonDeserializer<Integer> {
//        @Override
//        public Integer deserialize(JsonElement json, Type typeOfT,
//                                   JsonDeserializationContext context)
//                throws JsonParseException {
//            try {
//                if ("".equals(json.getAsString())) {
//                    return 0;
//                }
//            } catch (Exception ignore) {
//            }
//            try {
//                return json.getAsInt();
//            } catch (NumberFormatException e) {
//                throw new JsonSyntaxException(e);
//            }
//        }
//
//        @Override
//        public JsonElement serialize(Integer src, Type typeOfSrc, JsonSerializationContext
//                context) {
//            return new JsonPrimitive(src);
//        }
//    }
//
//    static class StringNullAdapter extends TypeAdapter<String> {
//        @Override
//        public String read(JsonReader reader) throws IOException {
//            // TODO Auto-generated method stub
//            if (reader.peek() == JsonToken.NULL) {
//                reader.nextNull();
//                return "";
//            }
//            return reader.nextString();
//        }
//
//        @Override
//        public void write(JsonWriter writer, String value) throws IOException {
//            // TODO Auto-generated method stub
//            if (value == null || "null".equals(value)) {
//                writer.value("");
//                return;
//            }
//            writer.value(value);
//        }
//    }
//
//    private volatile static Gson gson;
//
//    public static Gson getGson() {
//        if (gson == null) {
//            synchronized (GsonUtils.class) {
//                if (gson == null) {
//                    GsonBuilder gb = new GsonBuilder();
//                    gb.registerTypeAdapter(Double.class, new DoubleDefault0Adapter());
//                    gb.registerTypeAdapter(double.class, new DoubleDefault0Adapter());
//                    gb.registerTypeAdapter(Float.class, new FloatDefault0Adapter());
//                    gb.registerTypeAdapter(float.class, new FloatDefault0Adapter());
//                    gb.registerTypeAdapter(Integer.class, new IntegerDefault0Adapter());
//                    gb.registerTypeAdapter(int.class, new IntegerDefault0Adapter());
//                    gb.registerTypeAdapter(String.class, new StringNullAdapter());
//                    gb.serializeNulls();
//                    gson = gb.create();
//                }
//            }
//        }
//        return gson;
//    }
//
//    /**
//     * 对象转成json字符串
//     *
//     * @param obj
//     * @return
//     */
//    public static String toJson(Object obj) {
//        String json = "";
//        if (getGson() != null) {
//            json = getGson().toJson(obj);
//        }
//        return json;
//    }
//
//    /**
//     * json字符串转成对象
//     *
//     * @param str
//     * @param type
//     * @return
//     */
//    public static <T> T fromJson(String str, Class<T> type) {
//        T t = null;
//        if (getGson() != null) {
//            t = fromJson(getGson().fromJson(str, type), type);
//        }
//        return t;
//    }
//
//    /**
//     * Object字符串转成对象
//     *
//     * @param str
//     * @param type
//     * @return
//     */
//    public static <T> T fromJson(Object str, Class<T> type) {
//        T t = null;
//        if (getGson() != null) {
//            t = getGson().fromJson(toJson(str), type);
//        }
//        return t;
//    }
//
//    /**
//     * Object字符串转成对象
//     *
//     * @param str
//     * @param typeOfT
//     * @return
//     */
//    public static <T> T fromJson(Object str, Type typeOfT) {
//        T t = null;
//        if (getGson() != null) {
//            t = getGson().fromJson(toJson(str), typeOfT);
//        }
//        return t;
//    }
//    /**
//     * json字符串转成对象数组
//     *
//     * @param str
//     * @return
//     */
//    /*public static <T> List<T> fromJson(String str,Type typeOfT) {
//        List<T> t = null;
//        if (getGson() != null) {
//            t = getGson().fromJson(str, typeOfT);
//        }
//        return t;
//    }*/
//
//    /**
//     * json字符串转成对象
//     *
//     * @param str
//     * @param typeOfT
//     * @return
//     */
//    public static <T> T fromJson(String str, Type typeOfT) {
//        T t = null;
//        if (getGson() != null) {
//            t = fromJson(getGson().fromJson(str, typeOfT), typeOfT);
//        }
//        return t;
//    }
//
//    /**
//     * json字符串转成对象
//     *
//     * @param json
//     * @param type
//     * @return
//     */
//    public static <T> T fromJson(JsonElement json, Class<T> type) {
//        T t = null;
//        if (getGson() != null) {
//            t = fromJson(getGson().fromJson(json, type), type);
//        }
//        return t;
//    }
//
//}
