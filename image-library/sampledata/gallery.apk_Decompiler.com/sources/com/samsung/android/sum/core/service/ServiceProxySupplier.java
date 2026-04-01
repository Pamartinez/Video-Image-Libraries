package com.samsung.android.sum.core.service;

import A8.C0544a;
import android.app.Activity;
import android.content.Context;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.functional.PlaceHolder;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ServiceProxySupplier implements Supplier<ServiceProxy> {
    private static final String TAG = Def.tagOf((Class<?>) ServiceProxySupplier.class);
    protected final Map<Integer, Object> options;
    private final Supplier<ServiceProxy> supplier;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class PlaceHolderImpl extends ServiceProxySupplier implements PlaceHolder<ServiceProxySupplier> {
        private Context context;
        private String packageName;
        private String serviceName;

        public PlaceHolderImpl(Context context2) {
            this.context = context2;
        }

        public /* bridge */ /* synthetic */ Object get() {
            return ServiceProxySupplier.super.get();
        }

        public boolean isEmpty() {
            return false;
        }

        public boolean isNotEmpty() {
            return false;
        }

        public PlaceHolder<ServiceProxySupplier> setParameters(Object... objArr) {
            boolean z;
            if (objArr.length == 2) {
                z = true;
            } else {
                z = false;
            }
            Def.require(z);
            this.packageName = objArr[0];
            this.serviceName = objArr[1];
            return this;
        }

        public void put(ServiceProxySupplier serviceProxySupplier) {
            throw new UnsupportedOperationException();
        }

        public ServiceProxySupplier reset() {
            Def.require((this.packageName == null || this.serviceName == null) ? false : true);
            ServiceProxySupplier of2 = ServiceProxy.of(this.context, this.packageName, this.serviceName);
            of2.options.putAll(this.options);
            this.context = null;
            return of2;
        }

        public PlaceHolderImpl(PlaceHolderImpl placeHolderImpl) {
            super(placeHolderImpl);
            this.context = placeHolderImpl.context;
        }
    }

    public ServiceProxySupplier() {
        this.options = new HashMap();
        this.supplier = null;
    }

    private ServiceProxySupplier addOption(int i2) {
        this.options.put(Integer.valueOf(i2), (Object) null);
        return this;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ ServiceProxy lambda$new$0(Class cls, Context context) {
        if (LocalService.class.isAssignableFrom(cls)) {
            return new LocalServiceProxy(context, cls, this.options);
        }
        return new RemoteServiceProxy(context, cls, this.options);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ ServiceProxy lambda$new$1(String str, Context context, String str2) {
        Class<?> cls;
        try {
            cls = Class.forName(str);
        } catch (ClassNotFoundException unused) {
            cls = null;
        }
        if (cls == null || !LocalService.class.isAssignableFrom(cls)) {
            return new RemoteServiceProxy(context, str2, str, this.options);
        }
        return new LocalServiceProxy(context, cls, this.options);
    }

    public ServiceProxySupplier asDaemon() {
        ServiceProxySupplier serviceProxySupplier;
        if (this instanceof PlaceHolderImpl) {
            serviceProxySupplier = new PlaceHolderImpl((PlaceHolderImpl) this);
        } else {
            serviceProxySupplier = new ServiceProxySupplier(this);
        }
        return serviceProxySupplier.addOption(1);
    }

    public ServiceProxySupplier asForeground(Class<?> cls) {
        ServiceProxySupplier serviceProxySupplier;
        Def.require(Activity.class.isAssignableFrom(cls));
        if (this instanceof PlaceHolderImpl) {
            serviceProxySupplier = new PlaceHolderImpl((PlaceHolderImpl) this);
        } else {
            serviceProxySupplier = new ServiceProxySupplier(this);
        }
        return serviceProxySupplier.addOption(0, cls);
    }

    private ServiceProxySupplier addOption(int i2, Object obj) {
        this.options.put(Integer.valueOf(i2), obj);
        return this;
    }

    public ServiceProxy get() {
        return this.supplier.get();
    }

    public ServiceProxySupplier(Context context, Class<?> cls) {
        this.options = new HashMap();
        this.supplier = new C0544a(this, cls, context, 3);
    }

    public ServiceProxySupplier(ServiceProxySupplier serviceProxySupplier) {
        this.options = serviceProxySupplier.options;
        this.supplier = serviceProxySupplier;
    }

    public ServiceProxySupplier(Context context, String str, String str2) {
        this.options = new HashMap();
        this.supplier = new d(this, str2, context, str);
    }
}
