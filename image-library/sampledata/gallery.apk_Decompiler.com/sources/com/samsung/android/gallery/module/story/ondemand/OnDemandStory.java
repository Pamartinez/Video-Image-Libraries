package com.samsung.android.gallery.module.story.ondemand;

import A.a;
import ba.C0582a;
import c4.C0431a;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PackageMonitorCompat;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class OnDemandStory {
    private static final OnDemandStory instance = new OnDemandStory();
    final String KEY;
    final boolean LANGUAGE_CHECK_REQUIRED;
    final Object LOCK;
    public final boolean SUPPORT;
    final long VERSION;
    volatile HashMap<String, Boolean> map;

    public OnDemandStory() {
        long j2;
        boolean z;
        boolean isEnabled = Features.isEnabled(Features.SUPPORT_ON_DEMAND_STORY);
        this.SUPPORT = isEnabled;
        if (isEnabled) {
            j2 = PackageMonitorCompat.getInstance().getPackageVersion("com.samsung.storyservice");
        } else {
            j2 = 0;
        }
        this.VERSION = j2;
        if (!isEnabled || j2 < 1005700000 || !SdkConfig.atLeast(SdkConfig.SEM.V)) {
            z = false;
        } else {
            z = true;
        }
        this.LANGUAGE_CHECK_REQUIRED = z;
        this.KEY = a.f("OnDemandStory#", j2);
        this.LOCK = new Object();
    }

    public static OnDemandStory getInstance() {
        return instance;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$computeIfAbsent$0(Map.Entry entry) {
        String str;
        StringBuilder sb2 = new StringBuilder();
        sb2.append((String) entry.getKey());
        sb2.append("=");
        if (((Boolean) entry.getValue()).booleanValue()) {
            str = "T";
        } else {
            str = "F";
        }
        sb2.append(str);
        return sb2.toString();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$load$2(String[] strArr) {
        this.map.put(strArr[0], Boolean.valueOf("T".equalsIgnoreCase(strArr[1])));
    }

    public boolean computeIfAbsent(String str) {
        Boolean bool = load().get(str);
        if (bool != null) {
            return bool.booleanValue();
        }
        long currentTimeMillis = System.currentTimeMillis();
        boolean isOnDemandSupportLanguage = StoryGenApi.create().isOnDemandSupportLanguage();
        Boolean valueOf = Boolean.valueOf(isOnDemandSupportLanguage);
        synchronized (this.LOCK) {
            this.map.put(str, valueOf);
        }
        String str2 = (String) this.map.entrySet().stream().map(new C0431a(16)).collect(Collectors.joining(GlobalPostProcInternalPPInterface.SPLIT_REGEX));
        GalleryPreference.getInstanceCache().saveState(this.KEY, str2);
        Log.d("OnDemandStory", "support" + Logger.vt(str, valueOf, Long.valueOf(currentTimeMillis)) + " " + str2);
        return isOnDemandSupportLanguage;
    }

    public boolean computeLanguageIfAbsent(Locale locale) {
        if (!this.SUPPORT) {
            return false;
        }
        if (computeIfAbsent(locale.toString()) || !this.LANGUAGE_CHECK_REQUIRED) {
            return true;
        }
        return false;
    }

    public boolean isEnabled() {
        return isEnabled(Locale.getDefault());
    }

    public Map<String, Boolean> load() {
        HashMap<String, Boolean> hashMap;
        synchronized (this.LOCK) {
            try {
                if (this.map == null) {
                    this.map = new HashMap<>();
                    String loadString = GalleryPreference.getInstanceCache().loadString(this.KEY, (String) null);
                    if (loadString != null) {
                        Arrays.stream(loadString.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX)).map(new C0431a(15)).forEach(new C0582a(12, this));
                    }
                }
                hashMap = this.map;
            } catch (Throwable th) {
                throw th;
            }
        }
        return hashMap;
    }

    public boolean isEnabled(Locale locale) {
        Boolean bool = load().get(locale.toString());
        return bool != null && bool.booleanValue();
    }
}
