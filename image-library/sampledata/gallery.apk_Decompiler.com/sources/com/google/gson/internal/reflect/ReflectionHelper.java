package com.google.gson.internal.reflect;

import N2.j;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.google.gson.JsonIOException;
import com.google.gson.internal.TroubleshootingGuide;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ReflectionHelper {
    private static final RecordHelper RECORD_HELPER;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class RecordHelper {
        private RecordHelper() {
        }

        public abstract Method getAccessor(Class<?> cls, Field field);

        public abstract <T> Constructor<T> getCanonicalRecordConstructor(Class<T> cls);

        public abstract String[] getRecordComponentNames(Class<?> cls);

        public abstract boolean isRecord(Class<?> cls);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class RecordNotSupportedHelper extends RecordHelper {
        private RecordNotSupportedHelper() {
            super();
        }

        public Method getAccessor(Class<?> cls, Field field) {
            throw new UnsupportedOperationException("Records are not supported on this JVM, this method should not be called");
        }

        public <T> Constructor<T> getCanonicalRecordConstructor(Class<T> cls) {
            throw new UnsupportedOperationException("Records are not supported on this JVM, this method should not be called");
        }

        public String[] getRecordComponentNames(Class<?> cls) {
            throw new UnsupportedOperationException("Records are not supported on this JVM, this method should not be called");
        }

        public boolean isRecord(Class<?> cls) {
            return false;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class RecordSupportedHelper extends RecordHelper {
        private final Method getName;
        private final Method getRecordComponents;
        private final Method getType;
        private final Method isRecord;

        public Method getAccessor(Class<?> cls, Field field) {
            try {
                return cls.getMethod(field.getName(), (Class[]) null);
            } catch (ReflectiveOperationException e) {
                throw ReflectionHelper.createExceptionForRecordReflectionException(e);
            }
        }

        public <T> Constructor<T> getCanonicalRecordConstructor(Class<T> cls) {
            try {
                Object[] objArr = (Object[]) this.getRecordComponents.invoke(cls, (Object[]) null);
                Class[] clsArr = new Class[objArr.length];
                for (int i2 = 0; i2 < objArr.length; i2++) {
                    clsArr[i2] = (Class) this.getType.invoke(objArr[i2], (Object[]) null);
                }
                return cls.getDeclaredConstructor(clsArr);
            } catch (ReflectiveOperationException e) {
                throw ReflectionHelper.createExceptionForRecordReflectionException(e);
            }
        }

        public String[] getRecordComponentNames(Class<?> cls) {
            try {
                Object[] objArr = (Object[]) this.getRecordComponents.invoke(cls, (Object[]) null);
                String[] strArr = new String[objArr.length];
                for (int i2 = 0; i2 < objArr.length; i2++) {
                    strArr[i2] = (String) this.getName.invoke(objArr[i2], (Object[]) null);
                }
                return strArr;
            } catch (ReflectiveOperationException e) {
                throw ReflectionHelper.createExceptionForRecordReflectionException(e);
            }
        }

        public boolean isRecord(Class<?> cls) {
            try {
                return ((Boolean) this.isRecord.invoke(cls, (Object[]) null)).booleanValue();
            } catch (ReflectiveOperationException e) {
                throw ReflectionHelper.createExceptionForRecordReflectionException(e);
            }
        }

        private RecordSupportedHelper() {
            super();
            Class<Class> cls = Class.class;
            this.isRecord = cls.getMethod("isRecord", (Class[]) null);
            this.getRecordComponents = cls.getMethod("getRecordComponents", (Class[]) null);
            Class<?> cls2 = Class.forName("java.lang.reflect.RecordComponent");
            this.getName = cls2.getMethod("getName", (Class[]) null);
            this.getType = cls2.getMethod("getType", (Class[]) null);
        }
    }

    static {
        RecordHelper recordHelper;
        try {
            recordHelper = new RecordSupportedHelper();
        } catch (ReflectiveOperationException unused) {
            recordHelper = new RecordNotSupportedHelper();
        }
        RECORD_HELPER = recordHelper;
    }

    private ReflectionHelper() {
    }

    private static void appendExecutableParameters(AccessibleObject accessibleObject, StringBuilder sb2) {
        Class[] clsArr;
        sb2.append('(');
        if (accessibleObject instanceof Method) {
            clsArr = ((Method) accessibleObject).getParameterTypes();
        } else {
            clsArr = ((Constructor) accessibleObject).getParameterTypes();
        }
        for (int i2 = 0; i2 < clsArr.length; i2++) {
            if (i2 > 0) {
                sb2.append(ArcCommonLog.TAG_COMMA);
            }
            sb2.append(clsArr[i2].getSimpleName());
        }
        sb2.append(')');
    }

    public static String constructorToString(Constructor<?> constructor) {
        StringBuilder sb2 = new StringBuilder(constructor.getDeclaringClass().getName());
        appendExecutableParameters(constructor, sb2);
        return sb2.toString();
    }

    /* access modifiers changed from: private */
    public static RuntimeException createExceptionForRecordReflectionException(ReflectiveOperationException reflectiveOperationException) {
        throw new RuntimeException("Unexpected ReflectiveOperationException occurred (Gson 2.13.1). To support Java records, reflection is utilized to read out information about records. All these invocations happens after it is established that records exist in the JVM. This exception is unexpected behavior.", reflectiveOperationException);
    }

    public static RuntimeException createExceptionForUnexpectedIllegalAccess(IllegalAccessException illegalAccessException) {
        throw new RuntimeException("Unexpected IllegalAccessException occurred (Gson 2.13.1). Certain ReflectionAccessFilter features require Java >= 9 to work correctly. If you are not using ReflectionAccessFilter, report this to the Gson maintainers.", illegalAccessException);
    }

    public static String fieldToString(Field field) {
        return field.getDeclaringClass().getName() + "#" + field.getName();
    }

    public static String getAccessibleObjectDescription(AccessibleObject accessibleObject, boolean z) {
        String str;
        if (accessibleObject instanceof Field) {
            str = "field '" + fieldToString((Field) accessibleObject) + "'";
        } else if (accessibleObject instanceof Method) {
            Method method = (Method) accessibleObject;
            StringBuilder sb2 = new StringBuilder(method.getName());
            appendExecutableParameters(method, sb2);
            str = "method '" + method.getDeclaringClass().getName() + "#" + sb2.toString() + "'";
        } else if (accessibleObject instanceof Constructor) {
            str = "constructor '" + constructorToString((Constructor) accessibleObject) + "'";
        } else {
            str = "<unknown AccessibleObject> " + accessibleObject.toString();
        }
        if (!z || !Character.isLowerCase(str.charAt(0))) {
            return str;
        }
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }

    public static Method getAccessor(Class<?> cls, Field field) {
        return RECORD_HELPER.getAccessor(cls, field);
    }

    public static <T> Constructor<T> getCanonicalRecordConstructor(Class<T> cls) {
        return RECORD_HELPER.getCanonicalRecordConstructor(cls);
    }

    private static String getInaccessibleTroubleshootingSuffix(Exception exc) {
        String str;
        if (!exc.getClass().getName().equals("java.lang.reflect.InaccessibleObjectException")) {
            return "";
        }
        String message = exc.getMessage();
        if (message == null || !message.contains("to module com.google.gson")) {
            str = "reflection-inaccessible";
        } else {
            str = "reflection-inaccessible-to-module-gson";
        }
        return "\nSee " + TroubleshootingGuide.createUrl(str);
    }

    public static String[] getRecordComponentNames(Class<?> cls) {
        return RECORD_HELPER.getRecordComponentNames(cls);
    }

    public static boolean isAnonymousOrNonStaticLocal(Class<?> cls) {
        if (isStatic(cls)) {
            return false;
        }
        if (cls.isAnonymousClass() || cls.isLocalClass()) {
            return true;
        }
        return false;
    }

    public static boolean isRecord(Class<?> cls) {
        return RECORD_HELPER.isRecord(cls);
    }

    public static boolean isStatic(Class<?> cls) {
        return Modifier.isStatic(cls.getModifiers());
    }

    public static void makeAccessible(AccessibleObject accessibleObject) {
        try {
            accessibleObject.setAccessible(true);
        } catch (Exception e) {
            StringBuilder k = j.k("Failed making ", getAccessibleObjectDescription(accessibleObject, false), " accessible; either increase its visibility or write a custom TypeAdapter for its declaring type.");
            k.append(getInaccessibleTroubleshootingSuffix(e));
            throw new JsonIOException(k.toString(), e);
        }
    }

    public static String tryMakeAccessible(Constructor<?> constructor) {
        try {
            constructor.setAccessible(true);
            return null;
        } catch (Exception e) {
            return "Failed making constructor '" + constructorToString(constructor) + "' accessible; either increase its visibility or write a custom InstanceCreator or TypeAdapter for its declaring type: " + e.getMessage() + getInaccessibleTroubleshootingSuffix(e);
        }
    }
}
