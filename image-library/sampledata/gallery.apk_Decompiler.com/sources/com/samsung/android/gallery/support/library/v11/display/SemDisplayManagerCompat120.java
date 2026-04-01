package com.samsung.android.gallery.support.library.v11.display;

import S9.b;
import android.content.Context;
import android.hardware.display.SemWifiDisplayParameter;
import android.hardware.display.SemWifiDisplayParameterListener;
import android.os.Handler;
import android.util.Log;
import com.samsung.android.gallery.support.library.abstraction.WifiDisplayParameterListener;
import com.samsung.android.gallery.support.library.v0.display.SemDisplayManagerCompat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SemDisplayManagerCompat120 extends SemDisplayManagerCompat {
    private final SemWifiDisplayParameterListener mSemWifiDisplayParameterListener = new SemWifiDisplayParameterListener() {
        public void onParametersChanged(List<SemWifiDisplayParameter> list) {
            synchronized (SemDisplayManagerCompat120.this.mWifiDisplayParamListeners) {
                try {
                    if (SemDisplayManagerCompat120.this.mWifiDisplayParamListeners.size() > 0) {
                        String str = "";
                        String str2 = "";
                        HashMap hashMap = new HashMap();
                        for (SemWifiDisplayParameter next : list) {
                            String key = next.getKey();
                            switch (key.hashCode()) {
                                case -1353529967:
                                    if (!key.equals("wfd_sec_wirelessdex_support")) {
                                        break;
                                    } else {
                                        hashMap.put("wireless_dex", Boolean.valueOf("enable".equals(next.getValue())));
                                        break;
                                    }
                                case -958714025:
                                    if (!key.equals("wfd_sec_mirroring_mode")) {
                                        break;
                                    } else {
                                        str2 = next.getValue();
                                        break;
                                    }
                                case 110056161:
                                    if (!key.equals("wfd_sec_dmr_support")) {
                                        break;
                                    } else {
                                        hashMap.put("dmr", Boolean.valueOf("enable".equals(next.getValue())));
                                        break;
                                    }
                                case 1442236741:
                                    if (!key.equals("wfd_sec_view_mode")) {
                                        break;
                                    } else {
                                        str = next.getValue();
                                        break;
                                    }
                            }
                        }
                        Iterator it = SemDisplayManagerCompat120.this.mWifiDisplayParamListeners.iterator();
                        while (it.hasNext()) {
                            ((b) ((WifiDisplayParameterListener) it.next())).f2875a.lambda$new$3(str2, str, hashMap);
                        }
                    }
                } finally {
                }
            }
        }
    };
    /* access modifiers changed from: private */
    public final ArrayList<WifiDisplayParameterListener> mWifiDisplayParamListeners = new ArrayList<>();

    public SemDisplayManagerCompat120(Context context) {
        super(context);
    }

    public void registerWifiDisplayParameterListener(WifiDisplayParameterListener wifiDisplayParameterListener, Handler handler) {
        synchronized (this.mWifiDisplayParamListeners) {
            this.mWifiDisplayParamListeners.add(wifiDisplayParameterListener);
            if (this.mWifiDisplayParamListeners.size() == 1) {
                long currentTimeMillis = System.currentTimeMillis();
                try {
                    this.mDisplayManager.semRegisterWifiDisplayParameterListener(this.mSemWifiDisplayParameterListener, handler);
                    String str = this.TAG;
                    Log.i(str, "registerWifiDisplayParameterListener +" + (System.currentTimeMillis() - currentTimeMillis));
                } catch (Exception e) {
                    Log.w(this.TAG, "registerWifiDisplayParameterListener failed", e);
                }
            }
        }
    }

    public String tag() {
        return "SemDisplayManagerCompat120";
    }

    public void unregisterWifiDisplayParameterListener(WifiDisplayParameterListener wifiDisplayParameterListener) {
        synchronized (this.mWifiDisplayParamListeners) {
            this.mWifiDisplayParamListeners.remove(wifiDisplayParameterListener);
            if (this.mWifiDisplayParamListeners.size() == 0) {
                long currentTimeMillis = System.currentTimeMillis();
                try {
                    this.mDisplayManager.semUnregisterWifiDisplayParameterListener(this.mSemWifiDisplayParameterListener);
                    String str = this.TAG;
                    Log.i(str, "unregisterWifiDisplayParameterListener +" + (System.currentTimeMillis() - currentTimeMillis));
                } catch (Exception e) {
                    Log.w(this.TAG, "unregisterWifiDisplayParameterListener failed", e);
                }
            }
        }
    }
}
