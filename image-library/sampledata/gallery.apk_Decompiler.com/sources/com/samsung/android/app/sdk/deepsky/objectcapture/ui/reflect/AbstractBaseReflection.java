package com.samsung.android.app.sdk.deepsky.objectcapture.ui.reflect;

import N2.j;
import android.util.Log;
import i.C0212a;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AbstractBaseReflection {
    private static final String TAG = "ObjectCaptureReflection";
    protected Class<?> mBaseClass = null;
    private HashMap<String, Class<?>> mClassMap = new HashMap<>();
    private ArrayList<String> mNameList = new ArrayList<>();
    private ArrayList<Object> mReflectionList = new ArrayList<>();

    public AbstractBaseReflection() {
        loadReflection();
    }

    private void addReflectionInstance(String str, Object obj) {
        synchronized (this.mNameList) {
            this.mNameList.add(str);
            this.mReflectionList.add(obj);
        }
    }

    private Object getReflectionInstance(String str) {
        synchronized (this.mNameList) {
            if (str == null) {
                try {
                    return null;
                } catch (Throwable th) {
                    throw th;
                }
            } else {
                int size = this.mNameList.size();
                for (int i2 = 0; i2 < size; i2++) {
                    String str2 = this.mNameList.get(i2);
                    int length = str2.length();
                    if (length == str.length()) {
                        int i7 = length - 1;
                        char[] charArray = str2.toCharArray();
                        char[] charArray2 = str.toCharArray();
                        int i8 = 0;
                        while (true) {
                            if (i8 >= length) {
                                continue;
                                break;
                            }
                            char c5 = charArray[i8];
                            if ((charArray2[i8] & c5) != c5) {
                                break;
                            } else if (i8 == i7) {
                                Object obj = this.mReflectionList.get(i2);
                                return obj;
                            } else {
                                i8++;
                            }
                        }
                    }
                }
                return null;
            }
        }
    }

    private String getUniqueConstructorName(Class<?>[] clsArr) {
        StringBuilder sb2 = new StringBuilder(getBaseClassName());
        if (clsArr == null) {
            sb2.append("_EMPTY");
            return sb2.toString();
        }
        for (Class<?> name : clsArr) {
            try {
                sb2.append(name.getName());
            } catch (NullPointerException e) {
                Log.e(TAG, getBaseClassName() + " getUniqueConstructorName " + e);
            }
        }
        return sb2.toString();
    }

    private String getUniqueFieldName(String str) {
        return C0212a.l("FIELD_", str);
    }

    private String getUniqueMethodName(String str, Class<?>[] clsArr) {
        if (clsArr == null) {
            return str;
        }
        StringBuilder s = C0212a.s(str);
        for (Class<?> cls : clsArr) {
            if (cls != null) {
                s.append(cls.getName());
            }
        }
        return s.toString();
    }

    private Object invokeMethod(Object obj, String str, Class<?>[] clsArr, Object... objArr) {
        if (str == null || str.isEmpty()) {
            j.w("Cannot invoke ", str, getBaseClassName());
            return null;
        }
        if (objArr == null) {
            objArr = new Object[0];
        }
        Method loadMethodIfNeeded = loadMethodIfNeeded(str, clsArr);
        if (loadMethodIfNeeded == null) {
            Log.d(getBaseClassName(), "Cannot invoke there's no method reflection : ".concat(str));
            return null;
        }
        try {
            return loadMethodIfNeeded.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            Log.e(TAG, getBaseClassName() + " IllegalAccessException encountered invoking " + str + e);
            return null;
        } catch (InvocationTargetException e7) {
            Log.e(TAG, getBaseClassName() + " InvocationTargetException encountered invoking " + str + e7);
            return null;
        }
    }

    public Object createInstance() {
        return createInstance(new Object[0]);
    }

    public abstract String getBaseClassName();

    public Class<?> getClass(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            Log.e(TAG, str + " Unable to load class " + e);
            return null;
        }
    }

    public int getIntStaticValue(String str) {
        Object staticValue = getStaticValue(str);
        if (staticValue == null) {
            return -1;
        }
        return ((Integer) staticValue).intValue();
    }

    public Object getNormalValue(Object obj, String str) {
        if (obj == null || str == null || str.isEmpty()) {
            j.w("Cannot get value : ", str, getBaseClassName());
            return null;
        }
        Field loadFieldIfNeeded = loadFieldIfNeeded(str);
        if (loadFieldIfNeeded == null) {
            Log.d(getBaseClassName(), "Cannot get value : ".concat(str));
            return null;
        }
        try {
            return loadFieldIfNeeded.get(obj);
        } catch (IllegalAccessException e) {
            Log.e(TAG, getBaseClassName() + " IllegalAccessException encountered get " + str + e);
            return null;
        }
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x0040 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object getStaticValue(java.lang.String r6) {
        /*
            r5 = this;
            java.lang.String r0 = " IllegalAccessException encountered get "
            java.lang.String r1 = "ObjectCaptureReflection"
            java.lang.Class<?> r2 = r5.mBaseClass
            r3 = 0
            if (r2 == 0) goto L_0x0088
            if (r6 == 0) goto L_0x0088
            boolean r2 = r6.isEmpty()
            if (r2 == 0) goto L_0x0013
            goto L_0x0088
        L_0x0013:
            java.lang.Class<?> r2 = r5.mBaseClass     // Catch:{ NoSuchFieldException -> 0x0040, IllegalAccessException -> 0x0022 }
            java.lang.reflect.Field r2 = r2.getDeclaredField(r6)     // Catch:{ NoSuchFieldException -> 0x0040, IllegalAccessException -> 0x0022 }
            r4 = 1
            r2.setAccessible(r4)     // Catch:{ NoSuchFieldException -> 0x0040, IllegalAccessException -> 0x0022 }
            java.lang.Object r5 = r2.get(r3)     // Catch:{ NoSuchFieldException -> 0x0040, IllegalAccessException -> 0x0022 }
            return r5
        L_0x0022:
            r2 = move-exception
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = r5.getBaseClassName()
            r4.append(r5)
            r4.append(r0)
            r4.append(r6)
            r4.append(r2)
            java.lang.String r5 = r4.toString()
            android.util.Log.e(r1, r5)
            goto L_0x0087
        L_0x0040:
            java.lang.Class<?> r2 = r5.mBaseClass     // Catch:{ NoSuchFieldException -> 0x004d, IllegalAccessException -> 0x004b }
            java.lang.reflect.Field r2 = r2.getField(r6)     // Catch:{ NoSuchFieldException -> 0x004d, IllegalAccessException -> 0x004b }
            java.lang.Object r5 = r2.get(r3)     // Catch:{ NoSuchFieldException -> 0x004d, IllegalAccessException -> 0x004b }
            return r5
        L_0x004b:
            r2 = move-exception
            goto L_0x004f
        L_0x004d:
            r6 = move-exception
            goto L_0x006c
        L_0x004f:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = r5.getBaseClassName()
            r4.append(r5)
            r4.append(r0)
            r4.append(r6)
            r4.append(r2)
            java.lang.String r5 = r4.toString()
            android.util.Log.e(r1, r5)
            goto L_0x0087
        L_0x006c:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r5 = r5.getBaseClassName()
            r0.append(r5)
            java.lang.String r5 = " No field "
            r0.append(r5)
            r0.append(r6)
            java.lang.String r5 = r0.toString()
            android.util.Log.e(r1, r5)
        L_0x0087:
            return r3
        L_0x0088:
            java.lang.String r5 = r5.getBaseClassName()
            java.lang.String r0 = "Cannot get static value : "
            N2.j.w(r0, r6, r5)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.objectcapture.ui.reflect.AbstractBaseReflection.getStaticValue(java.lang.String):java.lang.Object");
    }

    public Object invokeNormalMethod(Object obj, String str, Object... objArr) {
        return invokeNormalMethod(obj, str, (Class<?>[]) null, objArr);
    }

    public Class<?> loadClassIfNeeded(String str) {
        Class<?> cls = this.mClassMap.get(str);
        if (cls == null && (cls = getClass(str)) != null) {
            this.mClassMap.put(str, cls);
        }
        return cls;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        r2 = r3.mBaseClass.getDeclaredConstructor(r4);
        r2.setAccessible(true);
        addReflectionInstance(r0, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0037, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0038, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0039, code lost:
        android.util.Log.e(TAG, getBaseClassName() + " No method " + r4);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x002a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.reflect.Constructor loadConstructorIfNeeded(java.lang.Class<?>[] r4) {
        /*
            r3 = this;
            java.lang.String r0 = r3.getUniqueConstructorName(r4)
            java.lang.Object r1 = r3.getReflectionInstance(r0)
            if (r1 == 0) goto L_0x000d
            java.lang.reflect.Constructor r1 = (java.lang.reflect.Constructor) r1
            return r1
        L_0x000d:
            java.lang.Class<?> r1 = r3.mBaseClass
            r2 = 0
            if (r1 == 0) goto L_0x0056
            if (r0 == 0) goto L_0x0056
            boolean r1 = r0.isEmpty()
            if (r1 == 0) goto L_0x001b
            goto L_0x0056
        L_0x001b:
            if (r4 != 0) goto L_0x0020
            r4 = 0
            java.lang.Class[] r4 = new java.lang.Class[r4]
        L_0x0020:
            java.lang.Class<?> r1 = r3.mBaseClass     // Catch:{ NoSuchMethodException -> 0x002a }
            java.lang.reflect.Constructor r2 = r1.getConstructor(r4)     // Catch:{ NoSuchMethodException -> 0x002a }
            r3.addReflectionInstance(r0, r2)     // Catch:{ NoSuchMethodException -> 0x002a }
            return r2
        L_0x002a:
            java.lang.Class<?> r1 = r3.mBaseClass     // Catch:{ NoSuchMethodException -> 0x0038 }
            java.lang.reflect.Constructor r2 = r1.getDeclaredConstructor(r4)     // Catch:{ NoSuchMethodException -> 0x0038 }
            r4 = 1
            r2.setAccessible(r4)     // Catch:{ NoSuchMethodException -> 0x0038 }
            r3.addReflectionInstance(r0, r2)     // Catch:{ NoSuchMethodException -> 0x0038 }
            return r2
        L_0x0038:
            r4 = move-exception
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r3 = r3.getBaseClassName()
            r0.append(r3)
            java.lang.String r3 = " No method "
            r0.append(r3)
            r0.append(r4)
            java.lang.String r3 = r0.toString()
            java.lang.String r4 = "ObjectCaptureReflection"
            android.util.Log.e(r4, r3)
        L_0x0056:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.objectcapture.ui.reflect.AbstractBaseReflection.loadConstructorIfNeeded(java.lang.Class[]):java.lang.reflect.Constructor");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        r0 = r3.mBaseClass.getDeclaredField(r4);
        r0.setAccessible(true);
        addReflectionInstance(r1, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0031, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0032, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0033, code lost:
        android.util.Log.e(TAG, getBaseClassName() + " No field " + r4);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x0024 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.reflect.Field loadFieldIfNeeded(java.lang.String r4) {
        /*
            r3 = this;
            r0 = 0
            if (r4 == 0) goto L_0x0050
            boolean r1 = r4.isEmpty()
            if (r1 == 0) goto L_0x000a
            goto L_0x0050
        L_0x000a:
            java.lang.String r1 = r3.getUniqueFieldName(r4)
            java.lang.Object r2 = r3.getReflectionInstance(r1)
            if (r2 == 0) goto L_0x0017
            java.lang.reflect.Field r2 = (java.lang.reflect.Field) r2
            return r2
        L_0x0017:
            java.lang.Class<?> r2 = r3.mBaseClass
            if (r2 != 0) goto L_0x001c
            return r0
        L_0x001c:
            java.lang.reflect.Field r0 = r2.getField(r4)     // Catch:{ NoSuchFieldException -> 0x0024 }
            r3.addReflectionInstance(r1, r0)     // Catch:{ NoSuchFieldException -> 0x0024 }
            return r0
        L_0x0024:
            java.lang.Class<?> r2 = r3.mBaseClass     // Catch:{ NoSuchFieldException -> 0x0032 }
            java.lang.reflect.Field r0 = r2.getDeclaredField(r4)     // Catch:{ NoSuchFieldException -> 0x0032 }
            r4 = 1
            r0.setAccessible(r4)     // Catch:{ NoSuchFieldException -> 0x0032 }
            r3.addReflectionInstance(r1, r0)     // Catch:{ NoSuchFieldException -> 0x0032 }
            return r0
        L_0x0032:
            r4 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = r3.getBaseClassName()
            r1.append(r3)
            java.lang.String r3 = " No field "
            r1.append(r3)
            r1.append(r4)
            java.lang.String r3 = r1.toString()
            java.lang.String r4 = "ObjectCaptureReflection"
            android.util.Log.e(r4, r3)
        L_0x0050:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.objectcapture.ui.reflect.AbstractBaseReflection.loadFieldIfNeeded(java.lang.String):java.lang.reflect.Field");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        r4 = r3.mBaseClass.getDeclaredMethod(r4, r5);
        r4.setAccessible(true);
        addReflectionInstance(r0, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0037, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0038, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0039, code lost:
        android.util.Log.e(TAG, getBaseClassName() + " No method " + r4);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x002a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.reflect.Method loadMethodIfNeeded(java.lang.String r4, java.lang.Class<?>[] r5) {
        /*
            r3 = this;
            java.lang.String r0 = r3.getUniqueMethodName(r4, r5)
            java.lang.Object r1 = r3.getReflectionInstance(r0)
            if (r1 == 0) goto L_0x000d
            java.lang.reflect.Method r1 = (java.lang.reflect.Method) r1
            return r1
        L_0x000d:
            java.lang.Class<?> r1 = r3.mBaseClass
            r2 = 0
            if (r1 == 0) goto L_0x0056
            if (r4 == 0) goto L_0x0056
            boolean r1 = r4.isEmpty()
            if (r1 == 0) goto L_0x001b
            goto L_0x0056
        L_0x001b:
            if (r5 != 0) goto L_0x0020
            r5 = 0
            java.lang.Class[] r5 = new java.lang.Class[r5]
        L_0x0020:
            java.lang.Class<?> r1 = r3.mBaseClass     // Catch:{ NoSuchMethodException -> 0x002a }
            java.lang.reflect.Method r1 = r1.getMethod(r4, r5)     // Catch:{ NoSuchMethodException -> 0x002a }
            r3.addReflectionInstance(r0, r1)     // Catch:{ NoSuchMethodException -> 0x002a }
            return r1
        L_0x002a:
            java.lang.Class<?> r1 = r3.mBaseClass     // Catch:{ NoSuchMethodException -> 0x0038 }
            java.lang.reflect.Method r4 = r1.getDeclaredMethod(r4, r5)     // Catch:{ NoSuchMethodException -> 0x0038 }
            r5 = 1
            r4.setAccessible(r5)     // Catch:{ NoSuchMethodException -> 0x0038 }
            r3.addReflectionInstance(r0, r4)     // Catch:{ NoSuchMethodException -> 0x0038 }
            return r4
        L_0x0038:
            r4 = move-exception
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r3 = r3.getBaseClassName()
            r5.append(r3)
            java.lang.String r3 = " No method "
            r5.append(r3)
            r5.append(r4)
            java.lang.String r3 = r5.toString()
            java.lang.String r4 = "ObjectCaptureReflection"
            android.util.Log.e(r4, r3)
        L_0x0056:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.objectcapture.ui.reflect.AbstractBaseReflection.loadMethodIfNeeded(java.lang.String, java.lang.Class[]):java.lang.reflect.Method");
    }

    public void loadReflection() {
        loadReflection(getBaseClassName());
    }

    public void setNormalValue(Object obj, String str, Object obj2) {
        if (obj == null || str == null || str.isEmpty()) {
            j.w("Cannot set value : ", str, TAG);
            return;
        }
        Field loadFieldIfNeeded = loadFieldIfNeeded(str);
        if (loadFieldIfNeeded == null) {
            Log.d(TAG, "Cannot set value : ".concat(str));
            return;
        }
        try {
            loadFieldIfNeeded.set(obj, obj2);
        } catch (IllegalAccessException e) {
            Log.e(TAG, " IllegalAccessException encountered set " + str + e);
        }
    }

    public Object createInstance(Object... objArr) {
        return createInstance((Class<?>[]) null, objArr);
    }

    public Object invokeNormalMethod(Object obj, String str, Class<?>[] clsArr, Object... objArr) {
        if (obj != null) {
            return invokeMethod(obj, str, clsArr, objArr);
        }
        j.w("Cannot invoke ", str, getBaseClassName());
        return null;
    }

    public void loadReflection(String str) {
        loadReflection(getClass(str));
    }

    public Object createInstance(Class<?>[] clsArr, Object... objArr) {
        if (objArr == null) {
            objArr = new Object[0];
        }
        Constructor loadConstructorIfNeeded = loadConstructorIfNeeded(clsArr);
        if (loadConstructorIfNeeded == null) {
            Log.d(getBaseClassName(), "Cannot invoke there's no constructor.");
            return null;
        }
        try {
            loadConstructorIfNeeded.setAccessible(true);
            return loadConstructorIfNeeded.newInstance(objArr);
        } catch (IllegalAccessException e) {
            Log.e(TAG, e.toString());
            Log.e(TAG, getBaseClassName() + " IllegalAccessException encountered invoking constructor " + e);
            return null;
        } catch (InvocationTargetException e7) {
            Log.e(TAG, e7.toString());
            Log.e(TAG, getBaseClassName() + " InvocationTargetException encountered invoking constructor " + e7);
            return null;
        } catch (InstantiationException e8) {
            Log.e(TAG, e8.toString());
            Log.e(TAG, getBaseClassName() + " InstantiationException encountered invoking constructor " + e8);
            return null;
        }
    }

    public void loadReflection(Class<?> cls) {
        this.mBaseClass = cls;
        if (cls == null) {
            Log.d(TAG, "There's no class.");
        } else {
            loadStaticFields();
        }
    }

    public AbstractBaseReflection(String str) {
        loadReflection(str);
    }

    public AbstractBaseReflection(Class<?> cls) {
        loadReflection(cls);
    }

    public void loadStaticFields() {
    }
}
