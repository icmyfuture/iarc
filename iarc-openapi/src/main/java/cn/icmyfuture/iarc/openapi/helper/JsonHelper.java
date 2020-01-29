package cn.icmyfuture.iarc.openapi.helper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

public class JsonHelper {
    private static Gson gson = new GsonBuilder().create();;

    public static String toJson(Object obj){
        return gson.toJson(obj);
    }

    public static void fromJson(String str, Type type) {
        gson.fromJson(str, type);
    }

    public static void fromJson(String str, Class clazz) {
        gson.fromJson(str, clazz);
    }
}
