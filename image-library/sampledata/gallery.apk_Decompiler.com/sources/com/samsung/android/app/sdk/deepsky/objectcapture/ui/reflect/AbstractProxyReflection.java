package com.samsung.android.app.sdk.deepsky.objectcapture.ui.reflect;

import V2.b;
import android.util.Log;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AbstractProxyReflection {
    protected static final String TAG = "ObjectCaptureReflection";
    protected Class<?> mBaseClass;
    protected String mClassName;
    protected Object mProxyInstance;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class InvocationHooker implements InvocationHandler {
        public InvocationHooker() {
        }

        public Object invoke(Object obj, Method method, Object[] objArr) {
            return AbstractProxyReflection.this.invokeInternal(obj, method, objArr);
        }
    }

    public AbstractProxyReflection(String str) {
        this(str, new Class[0], new Object[0]);
    }

    public Class<?> getClassType() {
        return this.mBaseClass;
    }

    public Object getProxyInstance() {
        return this.mProxyInstance;
    }

    public Object invokeInternal(Object obj, Method method, Object[] objArr) {
        try {
            Map map = b.g;
            return obj.getClass().getMethod(b.c(method), method.getParameterTypes()).invoke(obj, objArr);
        } catch (InvocationTargetException e) {
            throw e.getCause();
        } catch (Throwable th) {
            Log.e(TAG, th.toString());
            return null;
        }
    }

    public AbstractProxyReflection(String str, Class<?>[] clsArr, Object[] objArr) {
        this.mBaseClass = null;
        this.mProxyInstance = null;
        this.mClassName = str;
        try {
            this.mBaseClass = Class.forName(str);
        } catch (ClassNotFoundException e) {
            Log.e(TAG, " Unable to instantiate class " + e);
        }
        Class<?> cls = this.mBaseClass;
        if (cls == null) {
            Log.d(TAG, "There's no " + this.mClassName);
            return;
        }
        try {
            this.mProxyInstance = Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{this.mBaseClass}, new InvocationHooker());
        } catch (Exception e7) {
            try {
                b bVar = new b(this.mBaseClass);
                bVar.d = clsArr;
                bVar.e = objArr;
                bVar.f881c = new InvocationHooker();
                this.mProxyInstance = bVar.a();
            } catch (IOException e8) {
                Log.e(TAG, "Occur IOException during build proxy instance : " + e8);
                Log.e(TAG, e7.toString());
            }
        }
    }
}
