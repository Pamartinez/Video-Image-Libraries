package com.samsung.android.gallery.module.robin;

import android.content.Context;
import com.google.android.apps.search.assistant.surfaces.voice.robin.robinkit.proto.ClientInfo;
import com.google.android.apps.search.assistant.surfaces.voice.robin.robinkit.proto.File;
import com.google.android.apps.search.assistant.surfaces.voice.robin.robinkit.proto.LaunchRobinRequest;
import com.google.android.apps.search.assistant.surfaces.voice.robin.robinkit.proto.RobinAvailabilityRequest;
import com.google.android.apps.search.assistant.surfaces.voice.robin.robinkit.proto.RobinAvailabilityResponse;
import com.google.android.apps.search.assistant.surfaces.voice.robin.robinkit.proto.RobinIntegrationCapability;
import com.google.android.apps.search.assistant.surfaces.voice.robin.robinkit.proto.RobinKitServiceGrpc;
import com.google.android.apps.search.assistant.surfaces.voice.robin.robinkit.proto.TextQuery;
import com.samsung.android.gallery.support.utils.Log;
import ee.F;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RobinKitService {
    private final Context mContext;
    private final List<byte[]> mSignatureSha256Hash;

    public RobinKitService(Context context, List<byte[]> list) {
        this.mContext = context;
        this.mSignatureSha256Hash = list;
    }

    private ClientInfo getClientInfo() {
        return (ClientInfo) ClientInfo.newBuilder().setAppName(this.mContext.getApplicationInfo().name).setAppVersion("15.8.00.61").setAppPackageName(this.mContext.getPackageName()).setIsWorkProfile(false).build();
    }

    /* JADX WARNING: type inference failed for: r4v5, types: [ie.c, java.lang.Object] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x010d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private ee.F getManagedChannel() {
        /*
            r20 = this;
            r0 = r20
            java.lang.String r1 = "com.google.android.googlequicksearchbox"
            java.util.List<byte[]> r2 = r0.mSignatureSha256Hash
            r3 = 0
            if (r2 == 0) goto L_0x0163
            android.content.Context r2 = r0.mContext
            android.content.pm.PackageManager r2 = r2.getPackageManager()
            java.util.List<byte[]> r4 = r0.mSignatureSha256Hash
            int r5 = io.grpc.binder.h.f4630a
            r2.getClass()
            r4.getClass()
            boolean r5 = r4.isEmpty()
            r6 = 1
            r5 = r5 ^ r6
            He.F.j(r5)
            F2.Q r5 = F2.U.x()
            java.util.Iterator r4 = r4.iterator()
        L_0x002a:
            boolean r7 = r4.hasNext()
            r8 = 0
            if (r7 == 0) goto L_0x004c
            java.lang.Object r7 = r4.next()
            byte[] r7 = (byte[]) r7
            r7.getClass()
            int r9 = r7.length
            r10 = 32
            if (r9 != r10) goto L_0x0040
            r8 = r6
        L_0x0040:
            He.F.j(r8)
            int r8 = r7.length
            byte[] r7 = java.util.Arrays.copyOf(r7, r8)
            r5.a(r7)
            goto L_0x002a
        L_0x004c:
            F2.y0 r4 = r5.f()
            io.grpc.binder.g r5 = new io.grpc.binder.g
            r5.<init>(r2, r4)
            java.lang.String r2 = "com.google.android.apps.search.assistant.surfaces.voice.robin.robinkit.service.RobinKitGrpcService"
            int r4 = io.grpc.binder.a.e
            android.content.ComponentName r4 = new android.content.ComponentName
            r4.<init>(r1, r2)
            io.grpc.binder.a r1 = new io.grpc.binder.a
            android.content.Intent r2 = new android.content.Intent
            java.lang.String r6 = "grpc.io.action.BIND"
            r2.<init>(r6)
            android.content.Intent r2 = r2.setComponent(r4)
            r1.<init>(r2)
            android.content.Context r0 = r0.mContext
            io.grpc.binder.d r2 = new io.grpc.binder.d
            ie.c r4 = new ie.c
            r4.<init>()
            r2.<init>(r1, r0, r4)
            r2.g = r5
            ge.I0 r10 = r2.d
            r10.getClass()
            ge.K0 r1 = new ge.K0
            ge.F0 r9 = new ge.F0
            A0.l r0 = r10.z
            io.grpc.binder.c r11 = new io.grpc.binder.c
            java.lang.Object r2 = r0.e
            r12 = r2
            android.content.Context r12 = (android.content.Context) r12
            java.lang.Object r2 = r0.f
            r13 = r2
            ie.c r13 = (ie.c) r13
            java.lang.Object r0 = r0.g
            io.grpc.binder.d r0 = (io.grpc.binder.d) r0
            java.util.concurrent.Executor r14 = r0.e
            G0.c r15 = r0.f
            ge.I0 r2 = r0.d
            G0.c r2 = r2.e
            com.samsung.context.sdk.samsunganalytics.internal.sender.c r4 = r0.g
            io.grpc.binder.b r5 = r0.f4628i
            io.grpc.binder.e r0 = r0.f4627h
            r19 = r0
            r16 = r2
            r17 = r4
            r18 = r5
            r11.<init>(r12, r13, r14, r15, r16, r17, r18, r19)
            ge.Q0 r12 = new ge.Q0
            r0 = 4
            r12.<init>(r0)
            ge.Q0 r0 = ge.Z.l
            G0.c r13 = new G0.c
            r2 = 12
            r13.<init>((int) r2, (java.lang.Object) r0)
            ge.Y r14 = ge.Z.n
            java.util.ArrayList r15 = new java.util.ArrayList
            java.util.ArrayList r0 = r10.f
            r15.<init>(r0)
            java.lang.Class<ee.q> r0 = ee.C0984q.class
            monitor-enter(r0)
            monitor-exit(r0)
            boolean r0 = r10.u
            if (r0 == 0) goto L_0x0110
            java.lang.reflect.Method r0 = ge.I0.f4439H
            if (r0 == 0) goto L_0x010a
            boolean r2 = r10.v     // Catch:{ IllegalAccessException -> 0x00f5, InvocationTargetException -> 0x00f3 }
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)     // Catch:{ IllegalAccessException -> 0x00f5, InvocationTargetException -> 0x00f3 }
            boolean r4 = r10.w     // Catch:{ IllegalAccessException -> 0x00f5, InvocationTargetException -> 0x00f3 }
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)     // Catch:{ IllegalAccessException -> 0x00f5, InvocationTargetException -> 0x00f3 }
            java.lang.Boolean r5 = java.lang.Boolean.FALSE     // Catch:{ IllegalAccessException -> 0x00f5, InvocationTargetException -> 0x00f3 }
            boolean r6 = r10.f4446x     // Catch:{ IllegalAccessException -> 0x00f5, InvocationTargetException -> 0x00f3 }
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r6)     // Catch:{ IllegalAccessException -> 0x00f5, InvocationTargetException -> 0x00f3 }
            java.lang.Object[] r2 = new java.lang.Object[]{r2, r4, r5, r6}     // Catch:{ IllegalAccessException -> 0x00f5, InvocationTargetException -> 0x00f3 }
            java.lang.Object r0 = r0.invoke(r3, r2)     // Catch:{ IllegalAccessException -> 0x00f5, InvocationTargetException -> 0x00f3 }
            io.grpc.ClientInterceptor r0 = (io.grpc.ClientInterceptor) r0     // Catch:{ IllegalAccessException -> 0x00f5, InvocationTargetException -> 0x00f3 }
            goto L_0x010b
        L_0x00f3:
            r0 = move-exception
            goto L_0x00f7
        L_0x00f5:
            r0 = move-exception
            goto L_0x0101
        L_0x00f7:
            java.util.logging.Logger r2 = ge.I0.B
            java.util.logging.Level r4 = java.util.logging.Level.FINE
            java.lang.String r5 = "Unable to apply census stats"
            r2.log(r4, r5, r0)
            goto L_0x010a
        L_0x0101:
            java.util.logging.Logger r2 = ge.I0.B
            java.util.logging.Level r4 = java.util.logging.Level.FINE
            java.lang.String r5 = "Unable to apply census stats"
            r2.log(r4, r5, r0)
        L_0x010a:
            r0 = r3
        L_0x010b:
            if (r0 == 0) goto L_0x0110
            r15.add(r8, r0)
        L_0x0110:
            boolean r0 = r10.y
            if (r0 == 0) goto L_0x015c
            java.lang.String r0 = "io.grpc.census.InternalCensusTracingAccessor"
            java.lang.Class r0 = java.lang.Class.forName(r0)     // Catch:{ ClassNotFoundException -> 0x012e, NoSuchMethodException -> 0x012c, IllegalAccessException -> 0x012a, InvocationTargetException -> 0x0128 }
            java.lang.String r2 = "getClientInterceptor"
            java.lang.reflect.Method r0 = r0.getDeclaredMethod(r2, r3)     // Catch:{ ClassNotFoundException -> 0x012e, NoSuchMethodException -> 0x012c, IllegalAccessException -> 0x012a, InvocationTargetException -> 0x0128 }
            java.lang.Object r0 = r0.invoke(r3, r3)     // Catch:{ ClassNotFoundException -> 0x012e, NoSuchMethodException -> 0x012c, IllegalAccessException -> 0x012a, InvocationTargetException -> 0x0128 }
            io.grpc.ClientInterceptor r0 = (io.grpc.ClientInterceptor) r0     // Catch:{ ClassNotFoundException -> 0x012e, NoSuchMethodException -> 0x012c, IllegalAccessException -> 0x012a, InvocationTargetException -> 0x0128 }
            r3 = r0
            goto L_0x0157
        L_0x0128:
            r0 = move-exception
            goto L_0x0130
        L_0x012a:
            r0 = move-exception
            goto L_0x013a
        L_0x012c:
            r0 = move-exception
            goto L_0x0144
        L_0x012e:
            r0 = move-exception
            goto L_0x014e
        L_0x0130:
            java.util.logging.Logger r2 = ge.I0.B
            java.util.logging.Level r4 = java.util.logging.Level.FINE
            java.lang.String r5 = "Unable to apply census stats"
            r2.log(r4, r5, r0)
            goto L_0x0157
        L_0x013a:
            java.util.logging.Logger r2 = ge.I0.B
            java.util.logging.Level r4 = java.util.logging.Level.FINE
            java.lang.String r5 = "Unable to apply census stats"
            r2.log(r4, r5, r0)
            goto L_0x0157
        L_0x0144:
            java.util.logging.Logger r2 = ge.I0.B
            java.util.logging.Level r4 = java.util.logging.Level.FINE
            java.lang.String r5 = "Unable to apply census stats"
            r2.log(r4, r5, r0)
            goto L_0x0157
        L_0x014e:
            java.util.logging.Logger r2 = ge.I0.B
            java.util.logging.Level r4 = java.util.logging.Level.FINE
            java.lang.String r5 = "Unable to apply census stats"
            r2.log(r4, r5, r0)
        L_0x0157:
            if (r3 == 0) goto L_0x015c
            r15.add(r8, r3)
        L_0x015c:
            r9.<init>(r10, r11, r12, r13, r14, r15)
            r1.<init>(r9)
            return r1
        L_0x0163:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.robin.RobinKitService.getManagedChannel():ee.F");
    }

    private RobinAvailabilityRequest getRobinAvailabilityRequest(ClientInfo clientInfo) {
        return (RobinAvailabilityRequest) RobinAvailabilityRequest.newBuilder().setClientInfo(clientInfo).addRequestedCapabilities(RobinIntegrationCapability.TEXT_QUERY_WITH_ATTACHMENTS_CAPABILITY).build();
    }

    private TextQuery getTextQuery(String str, String str2, String str3) {
        return (TextQuery) TextQuery.newBuilder().addFiles((File) File.newBuilder().setUri(str).setDisplayName(str2).setMimeType(str3).build()).setShouldAutoSubmit(false).build();
    }

    public boolean launch(String str, String str2, String str3) {
        F f = null;
        try {
            f = getManagedChannel();
            if (f == null) {
                Log.e("RobinKitService", "failed to get channel");
                if (f != null) {
                    f.h();
                    return false;
                }
                return false;
            }
            ClientInfo clientInfo = getClientInfo();
            RobinKitServiceGrpc.RobinKitServiceFutureStub newFutureStub = RobinKitServiceGrpc.newFutureStub(f);
            TextQuery textQuery = getTextQuery(str, str2, str3);
            if (((RobinAvailabilityResponse) newFutureStub.getRobinAvailability(getRobinAvailabilityRequest(clientInfo)).get()).getStatusCase() == RobinAvailabilityResponse.StatusCase.AVAILABLE) {
                newFutureStub.launchRobin((LaunchRobinRequest) LaunchRobinRequest.newBuilder().setClientInfo(clientInfo).setTextQuery(textQuery).build());
                f.h();
                return true;
            }
            Log.e("RobinKitService", "not available robin service");
            f.h();
            return false;
        } catch (Exception e) {
            Log.e("RobinKitService", "failed to launch robin -> " + e.getMessage());
            if (f != null) {
                f.h();
            }
        } catch (Throwable th) {
            if (f != null) {
                f.h();
            }
            throw th;
        }
    }
}
