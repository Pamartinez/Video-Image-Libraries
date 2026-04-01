package com.samsung.scsp.framework.core;

import android.content.Context;
import com.samsung.scsp.common.ContextFactory;
import com.samsung.scsp.error.Logger;
import com.samsung.scsp.framework.core.ScspException;
import com.samsung.scsp.framework.core.identity.ScspCorePreferences;
import com.samsung.scsp.framework.core.identity.ScspIdentity;
import com.samsung.scsp.framework.core.util.StringUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Scsp {
    private static final Object LOCK = new Object();
    private static final Logger logger = Logger.get("Scsp");

    private static void checkAppId(String str) {
        if (StringUtil.isEmpty(str)) {
            throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, "AccountInfoSupplier is null or empty");
        }
    }

    private static void checkAppVersion() {
        String str = ScspCorePreferences.get().appVersion.get();
        String appVersion = SContext.getInstance().getAppVersion();
        if (!StringUtil.isEmpty(appVersion) && !StringUtil.equals(str, appVersion)) {
            logger.i("Current app version is not same with stored app version");
            ScspCorePreferences.get().clear();
        }
    }

    private static void checkContext(Context context) {
        if (context != null) {
            ContextFactory.attachApplicationContext(context.getApplicationContext());
            return;
        }
        throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, "The context is null");
    }

    public static String getToken() {
        return ScspIdentity.getToken(true);
    }

    public static void refreshToken() {
        ScspIdentity.onUnauthenticatedForSC(ScspIdentity.getToken(true));
    }

    public static void setUp(Context context, String str) {
        setUp(context, str, (ScspSuppliers) null);
    }

    public static void signOut(Context context) {
        ContextFactory.attachApplicationContext(context);
        ScspIdentity.signOut();
    }

    public static void setUp(Context context, String str, ScspSuppliers scspSuppliers) {
        logger.i("setUp");
        synchronized (LOCK) {
            checkContext(context);
            checkAppId(str);
            SContext.initialize(str, scspSuppliers);
            checkAppVersion();
        }
    }
}
