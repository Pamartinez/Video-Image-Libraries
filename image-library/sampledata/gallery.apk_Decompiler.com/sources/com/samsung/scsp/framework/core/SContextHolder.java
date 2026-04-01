package com.samsung.scsp.framework.core;

import N2.j;
import android.os.Build;
import c0.C0086a;
import com.samsung.scsp.common.ContextFactory;
import com.samsung.scsp.common.Holder;
import com.samsung.scsp.error.FaultBarrier;
import com.samsung.scsp.framework.core.decorator.SdkConfig;
import com.samsung.scsp.framework.core.network.Network;
import com.samsung.scsp.framework.core.network.NetworkFunction;
import com.samsung.scsp.framework.core.network.NetworkPermissionFactoryLoader;
import com.samsung.scsp.framework.core.network.base.NetworkImpl;
import java.util.Locale;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SContextHolder {
    public final String applicationId;
    public final SdkConfig.Domain domain;
    public final boolean isAccountRequiredFeature;
    public Network network;
    public int requestTimeOut = Network.DEFAULT_TIMEOUT;
    public SContext scontext;
    public final String userAgent;
    public final String version;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class NetworkFactory {
        private NetworkFactory() {
        }

        /* access modifiers changed from: private */
        public static Network get(SContext sContext) {
            Predicate predicate = NetworkPermissionFactoryLoader.load(ContextFactory.getApplicationContext()).get();
            NetworkFunction urlStreamHandlerFactorySupplier = sContext.getUrlStreamHandlerFactorySupplier();
            if (urlStreamHandlerFactorySupplier != null) {
                Holder holder = new Holder();
                FaultBarrier.run(new a(holder, urlStreamHandlerFactorySupplier, predicate));
                if (holder.get() != null) {
                    return (Network) holder.get();
                }
            }
            return new NetworkImpl(predicate);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class UserAgentFactory {
        private UserAgentFactory() {
        }

        /* access modifiers changed from: private */
        public static String get(SContext sContext, String str, String str2) {
            String[] split = str.split("\\.");
            String str3 = split[split.length - 1];
            if ("lite".equals(str3)) {
                str3 = j.f(new StringBuilder(), split[split.length - 2], ".", str3);
            }
            Locale locale = Locale.US;
            String str4 = Build.MODEL;
            String str5 = Build.DISPLAY;
            String packageName = ContextFactory.getApplicationContext().getPackageName();
            String appVersion = sContext.getAppVersion();
            int i2 = Build.VERSION.SDK_INT;
            String str6 = Build.VERSION.RELEASE;
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str4);
            sb2.append("; ");
            sb2.append(str5);
            sb2.append("; ");
            sb2.append(packageName);
            sb2.append("=");
            sb2.append(appVersion);
            sb2.append("; android sdk=");
            sb2.append(i2);
            C0086a.z(sb2, ", sw=", str6, "; ", str3);
            return j.f(sb2, "=", str2, ";");
        }
    }

    public SContextHolder(String str, String str2, boolean z, SdkConfig.Domain domain2) {
        SContext instance = SContext.getInstance();
        this.scontext = instance;
        this.userAgent = UserAgentFactory.get(instance, str, str2);
        this.network = NetworkFactory.get(this.scontext);
        this.isAccountRequiredFeature = z;
        this.applicationId = str;
        this.version = str2;
        this.domain = domain2;
    }
}
