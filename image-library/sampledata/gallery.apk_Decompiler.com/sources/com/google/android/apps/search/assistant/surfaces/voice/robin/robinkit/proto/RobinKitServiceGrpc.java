package com.google.android.apps.search.assistant.surfaces.voice.robin.robinkit.proto;

import A0.l;
import He.F;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.protobuf.ExtensionRegistryLite;
import ee.C0984q;
import ee.O;
import ee.X;
import he.C1077b;
import he.C1078c;
import i.C0212a;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.MethodDescriptor;
import io.grpc.ServerServiceDefinition;
import io.grpc.ServiceDescriptor;
import io.grpc.stub.AbstractStub;
import io.grpc.stub.StreamObserver;
import io.grpc.stub.a;
import io.grpc.stub.b;
import io.grpc.stub.c;
import io.grpc.stub.d;
import io.grpc.stub.e;
import io.grpc.stub.f;
import io.grpc.stub.i;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.logging.Logger;
import o1.C0246a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class RobinKitServiceGrpc {
    private static final int METHODID_GET_ROBIN_AVAILABILITY = 0;
    private static final int METHODID_LAUNCH_ROBIN = 1;
    public static final String SERVICE_NAME = "com.google.android.apps.search.assistant.surfaces.voice.robin.robinkit.proto.RobinKitService";
    private static volatile MethodDescriptor getGetRobinAvailabilityMethod;
    private static volatile MethodDescriptor getLaunchRobinMethod;
    private static volatile ServiceDescriptor serviceDescriptor;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface AsyncService {
        void getRobinAvailability(RobinAvailabilityRequest robinAvailabilityRequest, StreamObserver streamObserver) {
            C0246a.I(RobinKitServiceGrpc.getGetRobinAvailabilityMethod(), streamObserver);
        }

        void launchRobin(LaunchRobinRequest launchRobinRequest, StreamObserver streamObserver) {
            C0246a.I(RobinKitServiceGrpc.getLaunchRobinMethod(), streamObserver);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class RobinKitServiceBlockingStub extends b {
        public /* synthetic */ RobinKitServiceBlockingStub(Channel channel, CallOptions callOptions, int i2) {
            this(channel, callOptions);
        }

        public RobinAvailabilityResponse getRobinAvailability(RobinAvailabilityRequest robinAvailabilityRequest) {
            return (RobinAvailabilityResponse) i.b(getChannel(), RobinKitServiceGrpc.getGetRobinAvailabilityMethod(), getCallOptions(), robinAvailabilityRequest);
        }

        public LaunchRobinResponse launchRobin(LaunchRobinRequest launchRobinRequest) {
            return (LaunchRobinResponse) i.b(getChannel(), RobinKitServiceGrpc.getLaunchRobinMethod(), getCallOptions(), launchRobinRequest);
        }

        private RobinKitServiceBlockingStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        public RobinKitServiceBlockingStub build(Channel channel, CallOptions callOptions) {
            return new RobinKitServiceBlockingStub(channel, callOptions);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class RobinKitServiceFutureStub extends c {
        public /* synthetic */ RobinKitServiceFutureStub(Channel channel, CallOptions callOptions, int i2) {
            this(channel, callOptions);
        }

        public ListenableFuture getRobinAvailability(RobinAvailabilityRequest robinAvailabilityRequest) {
            C0984q g = getChannel().g(RobinKitServiceGrpc.getGetRobinAvailabilityMethod(), getCallOptions());
            Logger logger = i.f4634a;
            e eVar = new e(g);
            i.a(g, robinAvailabilityRequest, new f(eVar));
            return eVar;
        }

        public ListenableFuture launchRobin(LaunchRobinRequest launchRobinRequest) {
            C0984q g = getChannel().g(RobinKitServiceGrpc.getLaunchRobinMethod(), getCallOptions());
            Logger logger = i.f4634a;
            e eVar = new e(g);
            i.a(g, launchRobinRequest, new f(eVar));
            return eVar;
        }

        private RobinKitServiceFutureStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        public RobinKitServiceFutureStub build(Channel channel, CallOptions callOptions) {
            return new RobinKitServiceFutureStub(channel, callOptions);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class RobinKitServiceImplBase implements AsyncService {
        public final ServerServiceDefinition bindService() {
            return RobinKitServiceGrpc.bindService(this);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class RobinKitServiceStub extends a {
        public /* synthetic */ RobinKitServiceStub(Channel channel, CallOptions callOptions, int i2) {
            this(channel, callOptions);
        }

        public void getRobinAvailability(RobinAvailabilityRequest robinAvailabilityRequest, StreamObserver streamObserver) {
            C0984q g = getChannel().g(RobinKitServiceGrpc.getGetRobinAvailabilityMethod(), getCallOptions());
            Logger logger = i.f4634a;
            F.n(streamObserver, "responseObserver");
            i.a(g, robinAvailabilityRequest, new f(streamObserver, new d(g)));
        }

        public void launchRobin(LaunchRobinRequest launchRobinRequest, StreamObserver streamObserver) {
            C0984q g = getChannel().g(RobinKitServiceGrpc.getLaunchRobinMethod(), getCallOptions());
            Logger logger = i.f4634a;
            F.n(streamObserver, "responseObserver");
            i.a(g, launchRobinRequest, new f(streamObserver, new d(g)));
        }

        private RobinKitServiceStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        public RobinKitServiceStub build(Channel channel, CallOptions callOptions) {
            return new RobinKitServiceStub(channel, callOptions);
        }
    }

    private RobinKitServiceGrpc() {
    }

    /* JADX WARNING: type inference failed for: r10v9, types: [java.lang.Object, io.grpc.ServerServiceDefinition] */
    public static final ServerServiceDefinition bindService(AsyncService asyncService) {
        l lVar = new l(getServiceDescriptor());
        MethodDescriptor getRobinAvailabilityMethod = getGetRobinAvailabilityMethod();
        new MethodHandlers(asyncService, 0);
        F.n(getRobinAvailabilityMethod, "method must not be null");
        X x9 = new X(getRobinAvailabilityMethod);
        String str = (String) lVar.e;
        boolean equals = str.equals(getRobinAvailabilityMethod.f4621c);
        String str2 = getRobinAvailabilityMethod.b;
        F.l(equals, "Method name should be prefixed with service name and separated with '/'. Expected service name: '%s'. Actual fully qualifed method name: '%s'.", str, str2);
        HashMap hashMap = (HashMap) lVar.g;
        F.q("Method by same name already registered: %s", str2, !hashMap.containsKey(str2));
        hashMap.put(str2, x9);
        MethodDescriptor launchRobinMethod = getLaunchRobinMethod();
        new MethodHandlers(asyncService, 1);
        F.n(launchRobinMethod, "method must not be null");
        X x10 = new X(launchRobinMethod);
        boolean equals2 = str.equals(launchRobinMethod.f4621c);
        String str3 = launchRobinMethod.b;
        F.l(equals2, "Method name should be prefixed with service name and separated with '/'. Expected service name: '%s'. Actual fully qualifed method name: '%s'.", str, str3);
        F.q("Method by same name already registered: %s", str3, !hashMap.containsKey(str3));
        hashMap.put(str3, x10);
        ServiceDescriptor serviceDescriptor2 = (ServiceDescriptor) lVar.f;
        if (serviceDescriptor2 == null) {
            ArrayList arrayList = new ArrayList(hashMap.size());
            for (X x11 : hashMap.values()) {
                arrayList.add(x11.f4284a);
            }
            D0.e eVar = new D0.e(22);
            ArrayList arrayList2 = new ArrayList();
            eVar.f = arrayList2;
            F.n(str, "name");
            eVar.e = str;
            arrayList2.addAll(arrayList);
            serviceDescriptor2 = new ServiceDescriptor(eVar);
        }
        HashMap hashMap2 = new HashMap(hashMap);
        for (MethodDescriptor methodDescriptor : serviceDescriptor2.b) {
            String str4 = methodDescriptor.b;
            X x12 = (X) hashMap2.remove(str4);
            if (x12 == null) {
                throw new IllegalStateException(C0212a.l("No method bound for descriptor entry ", str4));
            } else if (x12.f4284a != methodDescriptor) {
                throw new IllegalStateException(C0212a.m("Bound method for ", str4, " not same instance as method in service descriptor"));
            }
        }
        if (hashMap2.size() <= 0) {
            ? obj = new Object();
            Collections.unmodifiableMap(new HashMap(hashMap));
            return obj;
        }
        throw new IllegalStateException("No entry in descriptor matching bound method " + ((X) hashMap2.values().iterator().next()).f4284a.b);
    }

    public static MethodDescriptor getGetRobinAvailabilityMethod() {
        MethodDescriptor methodDescriptor;
        MethodDescriptor methodDescriptor2 = getGetRobinAvailabilityMethod;
        if (methodDescriptor2 != null) {
            return methodDescriptor2;
        }
        synchronized (RobinKitServiceGrpc.class) {
            try {
                methodDescriptor = getGetRobinAvailabilityMethod;
                if (methodDescriptor == null) {
                    O o2 = O.UNARY;
                    String a7 = MethodDescriptor.a(SERVICE_NAME, "GetRobinAvailability");
                    RobinAvailabilityRequest defaultInstance = RobinAvailabilityRequest.getDefaultInstance();
                    ExtensionRegistryLite extensionRegistryLite = C1078c.f4582a;
                    MethodDescriptor methodDescriptor3 = new MethodDescriptor(o2, a7, new C1077b(defaultInstance), new C1077b(RobinAvailabilityResponse.getDefaultInstance()), true);
                    getGetRobinAvailabilityMethod = methodDescriptor3;
                    methodDescriptor = methodDescriptor3;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor getLaunchRobinMethod() {
        MethodDescriptor methodDescriptor;
        MethodDescriptor methodDescriptor2 = getLaunchRobinMethod;
        if (methodDescriptor2 != null) {
            return methodDescriptor2;
        }
        synchronized (RobinKitServiceGrpc.class) {
            try {
                methodDescriptor = getLaunchRobinMethod;
                if (methodDescriptor == null) {
                    O o2 = O.UNARY;
                    String a7 = MethodDescriptor.a(SERVICE_NAME, "LaunchRobin");
                    LaunchRobinRequest defaultInstance = LaunchRobinRequest.getDefaultInstance();
                    ExtensionRegistryLite extensionRegistryLite = C1078c.f4582a;
                    MethodDescriptor methodDescriptor3 = new MethodDescriptor(o2, a7, new C1077b(defaultInstance), new C1077b(LaunchRobinResponse.getDefaultInstance()), true);
                    getLaunchRobinMethod = methodDescriptor3;
                    methodDescriptor = methodDescriptor3;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return methodDescriptor;
    }

    public static ServiceDescriptor getServiceDescriptor() {
        ServiceDescriptor serviceDescriptor2;
        ServiceDescriptor serviceDescriptor3 = serviceDescriptor;
        if (serviceDescriptor3 != null) {
            return serviceDescriptor3;
        }
        synchronized (RobinKitServiceGrpc.class) {
            try {
                serviceDescriptor2 = serviceDescriptor;
                if (serviceDescriptor2 == null) {
                    D0.e eVar = new D0.e(22);
                    ArrayList arrayList = new ArrayList();
                    eVar.f = arrayList;
                    eVar.e = SERVICE_NAME;
                    MethodDescriptor getRobinAvailabilityMethod = getGetRobinAvailabilityMethod();
                    F.n(getRobinAvailabilityMethod, "method");
                    arrayList.add(getRobinAvailabilityMethod);
                    MethodDescriptor launchRobinMethod = getLaunchRobinMethod();
                    F.n(launchRobinMethod, "method");
                    arrayList.add(launchRobinMethod);
                    serviceDescriptor2 = new ServiceDescriptor(eVar);
                    serviceDescriptor = serviceDescriptor2;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return serviceDescriptor2;
    }

    public static RobinKitServiceBlockingStub newBlockingStub(Channel channel) {
        return (RobinKitServiceBlockingStub) b.newStub(new AbstractStub.StubFactory() {
            public RobinKitServiceBlockingStub newStub(Channel channel, CallOptions callOptions) {
                return new RobinKitServiceBlockingStub(channel, callOptions, 0);
            }
        }, channel);
    }

    public static RobinKitServiceFutureStub newFutureStub(Channel channel) {
        return (RobinKitServiceFutureStub) c.newStub(new AbstractStub.StubFactory() {
            public RobinKitServiceFutureStub newStub(Channel channel, CallOptions callOptions) {
                return new RobinKitServiceFutureStub(channel, callOptions, 0);
            }
        }, channel);
    }

    public static RobinKitServiceStub newStub(Channel channel) {
        return (RobinKitServiceStub) a.newStub(new AbstractStub.StubFactory() {
            public RobinKitServiceStub newStub(Channel channel, CallOptions callOptions) {
                return new RobinKitServiceStub(channel, callOptions, 0);
            }
        }, channel);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class MethodHandlers<Req, Resp> {
        private final int methodId;
        private final AsyncService serviceImpl;

        public MethodHandlers(AsyncService asyncService, int i2) {
            this.serviceImpl = asyncService;
            this.methodId = i2;
        }

        public void invoke(Req req, StreamObserver streamObserver) {
            int i2 = this.methodId;
            if (i2 == 0) {
                this.serviceImpl.getRobinAvailability((RobinAvailabilityRequest) req, streamObserver);
            } else if (i2 == 1) {
                this.serviceImpl.launchRobin((LaunchRobinRequest) req, streamObserver);
            } else {
                throw new AssertionError();
            }
        }

        public StreamObserver invoke(StreamObserver streamObserver) {
            throw new AssertionError();
        }
    }
}
