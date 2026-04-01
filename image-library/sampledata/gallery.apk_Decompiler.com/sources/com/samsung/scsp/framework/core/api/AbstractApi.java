package com.samsung.scsp.framework.core.api;

import android.util.Pair;
import com.samsung.scsp.error.FaultBarrier;
import com.samsung.scsp.framework.core.listeners.Listeners;
import com.samsung.scsp.framework.core.network.HttpRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AbstractApi implements Api {
    private final Map<String, Job> API_JOBS = new HashMap();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class JobFactory {
        private JobFactory() {
        }

        public static Job create(String str, HttpRequest.Method method, String str2, Class<?> cls, Class<? extends Job> cls2, Property[] propertyArr) {
            Class<? extends Job> cls3 = cls2;
            Job job = (Job) FaultBarrier.get(new c(cls3, method, str, str2, cls), null).obj;
            if (!(job == null || propertyArr == null)) {
                job.attachProperties(propertyArr);
            }
            return job;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ Constructor lambda$create$0(Class cls) {
            Class<String> cls2 = String.class;
            return cls.getDeclaredConstructor(new Class[]{HttpRequest.Method.class, cls2, cls2, Class.class});
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ Job lambda$create$1(Class cls, HttpRequest.Method method, String str, String str2, Class cls2) {
            if (cls != EmptyJobImpl.class) {
                Class<HttpRequest.Method> cls3 = HttpRequest.Method.class;
                Class<String> cls4 = String.class;
                if (FileTransferableJob.class.isAssignableFrom(cls)) {
                    Constructor declaredConstructor = cls.getDeclaredConstructor(new Class[]{cls3, cls4, cls4});
                    declaredConstructor.setAccessible(true);
                    return (Job) declaredConstructor.newInstance(new Object[]{method, str, str2});
                } else if (ResponsiveJob.class.isAssignableFrom(cls)) {
                    Object[] objArr = {method, str, str2, cls2};
                    Constructor constructor = (Constructor) FaultBarrier.get(new b(cls, 0), null).obj;
                    if (constructor == null) {
                        constructor = cls.getDeclaredConstructor(new Class[]{cls3, cls4, cls4});
                        objArr = new Object[]{method, str, str2};
                    }
                    constructor.setAccessible(true);
                    return (Job) constructor.newInstance(objArr);
                } else if (SimpleJob.class.isAssignableFrom(cls)) {
                    Constructor declaredConstructor2 = cls.getDeclaredConstructor(new Class[]{cls3, cls4, cls4});
                    declaredConstructor2.setAccessible(true);
                    return (Job) declaredConstructor2.newInstance(new Object[]{method, str, str2});
                }
            }
            if (cls2 != EmptyResponse.class) {
                return new ResponsiveJob(method, str, str2, cls2);
            }
            return new SimpleJob(method, str, str2);
        }
    }

    public AbstractApi() {
        FaultBarrier.run(new a(0, this));
    }

    private void createJob(Class<?> cls) {
        Job createJob;
        for (Field field : cls.getFields()) {
            field.setAccessible(true);
            Annotation[] annotations = field.getAnnotations();
            if (annotations.length > 0 && (createJob = createJob((String) field.get((Object) null), annotations[0])) != null) {
                this.API_JOBS.put(createJob.getName(), createJob);
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0() {
        ApiSpec apiSpec = (ApiSpec) getClass().getAnnotation(ApiSpec.class);
        if (apiSpec != null) {
            createJob(apiSpec.value());
        }
    }

    public void add(Job job) {
        this.API_JOBS.put(job.getName(), job);
    }

    public void attachHeaderFunction(String str, Function<ApiContext, Pair<String, String>[]> function) {
        this.API_JOBS.get(str).attachHeaderFunction(function);
    }

    public void attachJournalSupplier(String str, Supplier<Pair<String, String>> supplier) {
        this.API_JOBS.get(str).attachJournalSupplier(supplier);
    }

    public void attachUrlFunction(String str, BiFunction<ApiContext, String, String> biFunction) {
        this.API_JOBS.get(str).attachUrlFunction(biFunction);
    }

    public void execute(ApiContext apiContext, Listeners listeners) {
        this.API_JOBS.get(apiContext.name).execute(apiContext, listeners);
    }

    private Job createJob(String str, Annotation annotation) {
        if (annotation instanceof Post) {
            Post post = (Post) annotation;
            return JobFactory.create(str, HttpRequest.Method.POST, post.value(), post.response(), post.jobImpl(), post.properties());
        }
        String str2 = str;
        if (annotation instanceof Get) {
            Get get = (Get) annotation;
            return JobFactory.create(str2, HttpRequest.Method.GET, get.value(), get.response(), get.jobImpl(), get.properties());
        } else if (annotation instanceof Put) {
            Put put = (Put) annotation;
            return JobFactory.create(str2, HttpRequest.Method.PUT, put.value(), put.response(), put.jobImpl(), put.properties());
        } else if (annotation instanceof Delete) {
            Delete delete = (Delete) annotation;
            return JobFactory.create(str2, HttpRequest.Method.DELETE, delete.value(), delete.response(), delete.jobImpl(), delete.properties());
        } else if (annotation instanceof Patch) {
            Patch patch = (Patch) annotation;
            return JobFactory.create(str2, HttpRequest.Method.PATCH, patch.value(), patch.response(), patch.jobImpl(), patch.properties());
        } else if (!(annotation instanceof Head)) {
            return null;
        } else {
            Head head = (Head) annotation;
            return JobFactory.create(str2, HttpRequest.Method.HEAD, head.value(), head.response(), head.jobImpl(), head.properties());
        }
    }
}
