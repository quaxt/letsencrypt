package com.munichre.letsencrypt;

import javax.json.*;
import java.io.StringReader;
import java.math.BigDecimal;
import java.lang.reflect.Array;

public class JsonUtils {

    public static void main (String[] args) {
        JsonObject jo = o("x", 1, "y", new Object[]{1,2,"badger"});
        System.out.println(jo);
    }

    /*** create JsonObject from a list of key1, value1, key2, value2 ... */
    public static JsonObject o(Object... kv) {
        javax.json.JsonObjectBuilder jb = Json.createObjectBuilder();
        for (int i = 0; i < kv.length; i += 2) {
            String k = (String) kv[i];
            Object v = kv[i + 1];
             if (v instanceof Boolean) jb.add(k, (Boolean) v);
             else if (v instanceof Double) jb.add(k, (Double) v);
             else if (v instanceof Integer) jb.add(k, (Integer) v);
             else if (v instanceof java.lang.String) jb.add(k, (java.lang.String) v);
             else if (v instanceof java.math.BigDecimal) jb.add(k, (java.math.BigDecimal) v);
             else if (v instanceof java.math.BigInteger) jb.add(k, (java.math.BigInteger) v);
             else if (v instanceof javax.json.JsonArrayBuilder) jb.add(k, (javax.json.JsonArrayBuilder) v);
             else if (v instanceof javax.json.JsonObjectBuilder) jb.add(k, (javax.json.JsonObjectBuilder) v);
             else if (v instanceof javax.json.JsonValue) jb.add(k, (javax.json.JsonValue) v);
             else if (v instanceof Long) jb.add(k, (Long) v);
             else if (v == null) jb.addNull(k);
             else if (v.getClass().isArray()) jb.add(k, makeArrayBuilder(v));
             else throw new RuntimeException("Can't handle value " + v);
        }
        return jb.build();
    }

    public static JsonArray a(Object... array) {
        return makeArrayBuilder(array).build();
    }

    private static JsonArrayBuilder makeArrayBuilder (Object array) {
        int len = Array.getLength(array);
        JsonArrayBuilder ab = Json.createArrayBuilder();
        for (int i = 0; i < len; i++) {
            Object v = Array.get(array, i);
            if (v instanceof Boolean) ab.add((Boolean) v);
            else if (v instanceof Double) ab.add((Double) v);
            else if (v instanceof Integer) ab.add((Integer) v);
            else if (v instanceof java.lang.String) ab.add((java.lang.String) v);
            else if (v instanceof java.math.BigDecimal) ab.add((java.math.BigDecimal) v);
            else if (v instanceof java.math.BigInteger) ab.add((java.math.BigInteger) v);
            else if (v instanceof javax.json.JsonArrayBuilder) ab.add((javax.json.JsonArrayBuilder) v);
            else if (v instanceof javax.json.JsonObjectBuilder) ab.add((javax.json.JsonObjectBuilder) v);
            else if (v instanceof javax.json.JsonValue) ab.add((javax.json.JsonValue) v);
            else if (v instanceof Long) ab.add((Long) v);
            else if (v == null) ab.addNull();
            else if (v.getClass().isArray()) ab.add(makeArrayBuilder(v));
            else throw new RuntimeException("Can't handle value " + v);
        }
        return ab;
    }
}
