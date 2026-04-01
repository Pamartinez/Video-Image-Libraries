package com.samsung.scsp.framework.core.api;

import O3.b;
import com.samsung.scsp.error.FaultBarrier;
import com.samsung.scsp.framework.core.listeners.Listeners;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AbstractApiControl implements ApiControl {
    private final Map<String, Request> REQUESTS = new HashMap();
    protected Api api;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Request {
        final String name;

        public Request(String str) {
            this.name = str;
        }

        public void execute(ApiContext apiContext, Listeners listeners) {
            apiContext.api.execute(apiContext, listeners);
        }
    }

    public AbstractApiControl() {
        ApiClass apiClass = (ApiClass) getClass().getAnnotation(ApiClass.class);
        if (apiClass != null) {
            FaultBarrier.run(new b(23, this, apiClass.value()));
        }
        FaultBarrier.run(new d(this, 0));
        FaultBarrier.run(new d(this, 1));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Api lambda$new$0(Constructor constructor) {
        return (Api) constructor.newInstance((Object[]) null);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$1(Class cls) {
        Constructor declaredConstructor = cls.getDeclaredConstructor((Class[]) null);
        declaredConstructor.setAccessible(true);
        this.api = (Api) FaultBarrier.get(new a(1, declaredConstructor), null).obj;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$2() {
        String[] value;
        Requests requests = (Requests) getClass().getAnnotation(Requests.class);
        if (requests != null && (value = requests.value()) != null) {
            for (String str : value) {
                this.REQUESTS.put(str, new Request(str));
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$3() {
        Class[] value;
        RequestClasses requestClasses = (RequestClasses) getClass().getAnnotation(RequestClasses.class);
        if (requestClasses != null && (value = requestClasses.value()) != null) {
            for (Class declaredConstructor : value) {
                Constructor declaredConstructor2 = declaredConstructor.getDeclaredConstructor((Class[]) null);
                declaredConstructor2.setAccessible(true);
                Request request = (Request) declaredConstructor2.newInstance((Object[]) null);
                this.REQUESTS.put(request.name, request);
            }
        }
    }

    public void add(Request request) {
        this.REQUESTS.put(request.name, request);
    }

    public void execute(ApiContext apiContext, Listeners listeners) {
        apiContext.api = this.api;
        Request request = this.REQUESTS.get(apiContext.name);
        Objects.requireNonNull(request);
        request.execute(apiContext, listeners);
    }
}
