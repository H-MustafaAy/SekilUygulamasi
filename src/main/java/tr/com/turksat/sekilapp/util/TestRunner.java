package tr.com.turksat.sekilapp.util;

import tr.com.turksat.sekilapp.annotation.TestableClass;
import tr.com.turksat.sekilapp.annotation.TestableMethod;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

public class TestRunner {
    public static String runTests(Class<?>[] classes, Object[][][] params) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < classes.length; i++) {
            Class<?> clazz = classes[i];
            if (!clazz.isAnnotationPresent(TestableClass.class)) {
                continue;
            }
            String className = clazz.getSimpleName();
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(TestableMethod.class)) {
                    Object[][] methodParamsArr = (params.length > i) ? params[i] : new Object[0][0];
                    for (Object[] methodParams : methodParamsArr) {
                        try {
                            // Parametrelerle nesne oluştur
                            Object instance = createInstance(clazz, methodParams);
                            method.setAccessible(true);
                            Object result = method.invoke(instance);
                            sb.append(className).append(".")
                              .append(method.getName())
                              .append("(")
                              .append(paramString(methodParams))
                              .append(") -> Başarılı, Sonuç: ")
                              .append(result)
                              .append("\n");
                        } catch (Exception e) {
                            sb.append(className).append(".")
                              .append(method.getName())
                              .append("(")
                              .append(paramString(methodParams))
                              .append(") -> Hata: ")
                              .append(e.getCause() != null ? e.getCause().getMessage() : e.getMessage())
                              .append("\n");
                        }
                    }
                }
            }
        }
        return sb.toString();
    }

    private static Object createInstance(Class<?> clazz, Object[] params) throws Exception {
        for (var ctor : clazz.getConstructors()) {
            Class<?>[] ctorParams = ctor.getParameterTypes();
            if (ctorParams.length != params.length) continue;
            boolean uyumlu = true;
            for (int i = 0; i < ctorParams.length; i++) {
                if (!isAssignable(ctorParams[i], params[i])) {
                    uyumlu = false;
                    break;
                }
            }
            if (uyumlu) {
                return ctor.newInstance(params);
            }
        }
        throw new NoSuchMethodException("Uygun constructor bulunamadı: " + clazz.getName());
    }

    private static boolean isAssignable(Class<?> paramType, Object arg) {
        if (arg == null) return !paramType.isPrimitive();
        if (paramType.isPrimitive()) {
            if (paramType == int.class && arg instanceof Integer) return true;
            if (paramType == double.class && arg instanceof Double) return true;
            if (paramType == boolean.class && arg instanceof Boolean) return true;
            if (paramType == long.class && arg instanceof Long) return true;
            if (paramType == float.class && arg instanceof Float) return true;
            if (paramType == char.class && arg instanceof Character) return true;
            if (paramType == byte.class && arg instanceof Byte) return true;
            if (paramType == short.class && arg instanceof Short) return true;
        }
        return paramType.isInstance(arg) || paramType.isAssignableFrom(arg.getClass());
    }

    private static String paramString(Object[] params) {
        if (params == null || params.length == 0) return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < params.length; i++) {
            if (i > 0) sb.append(",");
            sb.append(params[i]);
        }
        return sb.toString();
    }
} 