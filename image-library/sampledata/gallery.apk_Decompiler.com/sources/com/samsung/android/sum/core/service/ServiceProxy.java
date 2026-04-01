package com.samsung.android.sum.core.service;

import android.content.Context;
import android.os.IBinder;
import com.samsung.android.sum.core.controller.MediaController;
import com.samsung.android.sum.core.functional.ExceptionHandler;
import com.samsung.android.sum.core.message.Request;
import com.samsung.android.sum.core.message.Response;
import com.samsung.android.sum.core.service.ServiceProxySupplier;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.Future;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ServiceProxy {
    public static final String ACTION_START_FOREGROUND = "start-foreground";
    public static final String ACTION_STOP_FOREGROUND = "stop-foreground";
    public static final int OPTION_AS_DAEMON = 1;
    public static final int OPTION_AS_FOREGROUND = 0;

    @Retention(RetentionPolicy.SOURCE)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public @interface Action {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public @interface Option {
    }

    static ServiceProxySupplier of(Context context) {
        return new ServiceProxySupplier.PlaceHolderImpl(context);
    }

    IBinder getBinder();

    ExceptionHandler getExceptionHandler();

    void release();

    Future<Response> request(Request request);

    void setEventListener(MediaController.OnEventListener onEventListener);

    void setExceptionHandler(ExceptionHandler exceptionHandler);

    static ServiceProxySupplier of(Context context, Class<?> cls) {
        return new ServiceProxySupplier(context, cls);
    }

    static ServiceProxySupplier of(Context context, String str) {
        return of(context, Class.forName(str));
    }

    static ServiceProxySupplier of(Context context, String str, String str2) {
        return new ServiceProxySupplier(context, str, str2);
    }
}
