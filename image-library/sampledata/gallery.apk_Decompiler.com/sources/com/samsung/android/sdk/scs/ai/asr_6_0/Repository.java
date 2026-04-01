package com.samsung.android.sdk.scs.ai.asr_6_0;

import F9.d;
import N2.j;
import android.content.SharedPreferences;
import android.util.Log;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.scs.ai.sdkcommon.asr.ServerFeature;
import com.samsung.android.scs.ai.sdkcommon.asr.ServerType;
import com.samsung.android.sdk.scs.ai.asr.g;
import i.C0212a;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
interface Repository {
    public static final Repository settings = new SharedPrefRepository();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SharedPrefRepository implements Repository {
        private static final String KEY_SERVER_TYPE = "server_type";
        private static final String POSTFIX_IS_DEFAULT = "_is_default";
        private static final int VERSION = 1;
        private final String TAG;
        private final String prefName;
        private final SharedPreferences sharedPrefSupplier;

        public /* synthetic */ SharedPrefRepository() {
            this("scs_asr_settings");
        }

        private String getKey(String str, ServerFeature serverFeature) {
            StringBuilder s = C0212a.s(str);
            s.append(serverFeature.ordinal());
            return s.toString();
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ ServerType lambda$getServerType$1(ServerFeature serverFeature, SharedPreferences sharedPreferences, String str, String str2) {
            return new ServerType(serverFeature, str2, sharedPreferences.getBoolean(str + POSTFIX_IS_DEFAULT, false));
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ ServerType lambda$getServerType$2(String str, ServerFeature serverFeature, SharedPreferences sharedPreferences) {
            return (ServerType) Optional.ofNullable(sharedPreferences.getString(str, (String) null)).map(new d(serverFeature, sharedPreferences, str, 2)).orElse((Object) null);
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ SharedPreferences.Editor lambda$setServerType$0(ServerType serverType, String str, SharedPreferences.Editor editor) {
            if (serverType == null) {
                editor.remove(str);
                editor.remove(str + POSTFIX_IS_DEFAULT);
                return editor;
            }
            editor.putString(str, serverType.getName());
            editor.putBoolean(str + POSTFIX_IS_DEFAULT, serverType.isDefault());
            return editor;
        }

        public ServerType getServerType(ServerFeature serverFeature) {
            return (ServerType) Optional.ofNullable(this.sharedPrefSupplier).map(new g(getKey("server_type", serverFeature), serverFeature)).orElse((Object) null);
        }

        public boolean setServerType(ServerFeature serverFeature, ServerType serverType) {
            try {
                String str = this.TAG;
                Log.i(str, "setServerType " + serverFeature + ArcCommonLog.TAG_COMMA + serverType);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (serverFeature == null) {
                return false;
            }
            try {
                return ((Boolean) Optional.ofNullable(this.sharedPrefSupplier).map(new g(1)).map(new g(serverType, getKey("server_type", serverFeature))).map(new g(2)).orElse(Boolean.FALSE)).booleanValue();
            } catch (Exception e7) {
                j.C(e7, new StringBuilder("Failed to set server type "), this.TAG);
                return false;
            }
        }

        private SharedPrefRepository(String str) {
            String A10 = C0212a.A(str, "_1");
            this.prefName = A10;
            String l = C0212a.l("SharedPrefRepository@", str);
            this.TAG = l;
            this.sharedPrefSupplier = Environment.getContext().getSharedPreferences(str, 0);
            Log.i(l, "created  " + A10);
        }
    }

    ServerType getServerType(ServerFeature serverFeature);

    boolean setServerType(ServerFeature serverFeature, ServerType serverType);
}
