package com.samsung.scsp.framework.core.ers;

import android.content.Context;
import com.samsung.scsp.common.ContextFactory;
import com.samsung.scsp.common.FeatureConfigurator;
import com.samsung.scsp.common.Holder;
import com.samsung.scsp.framework.core.SContext;
import com.samsung.scsp.framework.core.network.Network;
import com.samsung.scsp.framework.core.network.NetworkPermissionFactoryLoader;
import com.samsung.scsp.framework.core.network.base.NetworkImpl;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ScspErs {
    private static final Holder<Boolean> IS_ONSTAGE_HOLDER = new Holder<>();
    private static final String STG_API = "https://stg-api.samsungcloud.com";
    private static final String STG_PLAY = "https://stg-play.samsungcloud.com";

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class DefaultNetworkHolder {
        /* access modifiers changed from: private */
        public static final Network network = new NetworkImpl(NetworkPermissionFactoryLoader.load(ContextFactory.getApplicationContext()).get());

        private DefaultNetworkHolder() {
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LazyHolder {
        /* access modifiers changed from: private */
        public static final ErsImpl ers = new ErsImpl();

        private LazyHolder() {
        }
    }

    public static void clear(Context context) {
        ContextFactory.attachApplicationContext(context);
        ErsPreferences.get().clear();
    }

    public static String getBaseUrl(Context context, String str) {
        return getBaseUrl(context, DefaultNetworkHolder.network, str);
    }

    public static DomainVo getDomain() {
        return getDomain(DefaultNetworkHolder.network);
    }

    public static boolean isOnStage(Context context) {
        Holder<Boolean> holder = IS_ONSTAGE_HOLDER;
        if (holder.get() == null) {
            holder.hold(Boolean.valueOf(FeatureConfigurator.get(context, "ENABLE_STAGE_SERVER", false)));
        }
        return holder.get().booleanValue();
    }

    public static String getBaseUrl(Context context, Network network, String str) {
        ContextFactory.attachApplicationContext(context);
        if (isOnStage(context)) {
            return STG_API;
        }
        ErsPreferences ersPreferences = ErsPreferences.get();
        if (ersPreferences.expiredTime.get().longValue() < System.currentTimeMillis()) {
            LazyHolder.ers.getDomain(context, network, str);
        }
        return ersPreferences.defaultUrl.get();
    }

    public static DomainVo getDomain(Network network) {
        Context applicationContext = ContextFactory.getApplicationContext();
        if (isOnStage(applicationContext)) {
            DomainVo domainVo = new DomainVo();
            domainVo.playUrl = STG_PLAY;
            domainVo.defaultUrl = STG_API;
            return domainVo;
        }
        ErsPreferences ersPreferences = ErsPreferences.get();
        if (ersPreferences.expiredTime.get().longValue() < System.currentTimeMillis()) {
            LazyHolder.ers.getDomain(applicationContext, network, SContext.getInstance().getAppId());
        }
        DomainVo domainVo2 = new DomainVo();
        domainVo2.playUrl = ersPreferences.playUrl.get();
        domainVo2.defaultUrl = ersPreferences.defaultUrl.get();
        return domainVo2;
    }
}
