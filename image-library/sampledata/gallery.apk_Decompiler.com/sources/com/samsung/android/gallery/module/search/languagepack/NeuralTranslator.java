package com.samsung.android.gallery.module.search.languagepack;

import I3.h;
import O3.r;
import W.a;
import android.text.TextUtils;
import com.samsung.android.gallery.module.settings.MiscSettingPreference;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.NetworkUtils;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.sdk.scs.ai.AiServices;
import com.samsung.android.sdk.scs.ai.translation.LanguageDirectionState;
import com.samsung.android.sdk.scs.base.tasks.Task;
import com.samsung.android.sdk.spage.card.event.Event;
import com.samsung.android.sivs.ai.sdkcommon.translation.LanguageDirection;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class NeuralTranslator {
    private static final NeuralTranslator sInstance = new NeuralTranslator();
    private String mLanguagePackCode;
    private final ConcurrentHashMap<String, String> mSupportLanguageMap = new ConcurrentHashMap<>();
    private volatile com.samsung.android.sdk.scs.ai.translation.NeuralTranslator mTranslator;

    private void constructSupportLanguageMap(String str) {
        try {
            JSONArray jSONArray = new JSONObject(str).getJSONObject("translateSupportLanguageInfo").getJSONArray(Event.DEFAULT_EVENT_TYPE);
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i2);
                this.mSupportLanguageMap.put(jSONObject.getString("ondeviceLangCode"), jSONObject.getString("langpackCode"));
            }
            Log.s("NeuralTranslator", "constructSupportLanguageMap done. map size " + this.mSupportLanguageMap.size());
        } catch (Exception e) {
            Log.se("NeuralTranslator", "constructSupportLanguageMap failed. e=" + e.getMessage());
        }
    }

    private void findLanguagePackCode(String str) {
        this.mLanguagePackCode = this.mSupportLanguageMap.get(str);
        Log.s("NeuralTranslator", "findLanguagePackCode " + Logger.v(Integer.valueOf(this.mSupportLanguageMap.size()), this.mLanguagePackCode));
        if (!TextUtils.isEmpty(this.mLanguagePackCode)) {
            Blackboard.postBroadcastEventGlobal(EventMessage.obtain(8005));
        }
    }

    private void findLanguagePackDownloadCode(String str) {
        if (this.mSupportLanguageMap.isEmpty()) {
            getTranslateSupportLanguage(str);
        } else {
            findLanguagePackCode(str);
        }
    }

    public static NeuralTranslator getInstance() {
        return sInstance;
    }

    private void getTranslateSupportLanguage(String str) {
        AiServices.getConfiguration(AppResources.getAppContext()).getTranslateSupportLanguage().addOnCompleteListener(new r(this, System.currentTimeMillis(), str));
    }

    private boolean isDownloadableLanguage(String str) {
        LanguageDirectionState languageDirectionState;
        if (this.mTranslator != null) {
            long currentTimeMillis = System.currentTimeMillis();
            LanguageDirection languageDirection = new LanguageDirection(str, "en");
            boolean isAvailableDirection = this.mTranslator.isAvailableDirection(languageDirection);
            if (!isAvailableDirection) {
                languageDirectionState = this.mTranslator.getLanguageDirectionStateMap().get(languageDirection);
            } else {
                languageDirectionState = null;
            }
            Log.s("NeuralTranslator", "isDownloadableLanguage" + Logger.vt(str, Boolean.valueOf(isAvailableDirection), languageDirectionState, Long.valueOf(currentTimeMillis)));
            if (languageDirectionState == LanguageDirectionState.DOWNLOADABLE) {
                return true;
            }
        }
        return false;
    }

    private boolean isNetworkNotAvailable() {
        if (Features.isEnabled(Features.IS_CHINESE_DEVICE) && !MiscSettingPreference.AllowDataUsageForChn.isEnabled()) {
            return true;
        }
        if (NetworkUtils.isNetworkConnected(AppResources.getAppContext()) || NetworkUtils.isWifiConnected(AppResources.getAppContext())) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getTranslateSupportLanguage$2(long j2, String str, Task task) {
        if (task.isSuccessful()) {
            Log.s("NeuralTranslator", "getTranslateSupportLanguage done. +" + (System.currentTimeMillis() - j2));
            String str2 = (String) task.getResult();
            if (!TextUtils.isEmpty(str2)) {
                constructSupportLanguageMap(str2);
                findLanguagePackCode(str);
                return;
            }
            Log.se("NeuralTranslator", "getTranslateSupportLanguage result is empty. " + str2);
            return;
        }
        Log.se("NeuralTranslator", "getTranslateSupportLanguage failed. task was unsuccessful.");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$identifyLanguage$1(long j2, Task task) {
        if (task.isSuccessful()) {
            String str = (String) task.getResult();
            Log.s("NeuralTranslator", "identifyLanguage done +" + (System.currentTimeMillis() - j2));
            if (isDownloadableLanguage(str)) {
                findLanguagePackDownloadCode(str);
                return;
            }
            return;
        }
        Log.se("NeuralTranslator", "identifyLanguage failed. task was unsuccessful.");
    }

    private int updateAndGetResultCount() {
        return PreferenceCache.SearchResultNoItemsCount.incrementAndGet();
    }

    public void clearValues() {
        this.mLanguagePackCode = null;
    }

    public void close() {
        long currentTimeMillis = System.currentTimeMillis();
        if (this.mTranslator != null) {
            this.mTranslator.close();
        }
        Log.s("NeuralTranslator", "close +" + (System.currentTimeMillis() - currentTimeMillis));
    }

    public String getLanguagePackCode() {
        return this.mLanguagePackCode;
    }

    public void identifyLanguage(String str) {
        Log.s("NeuralTranslator", "identifyLanguage start");
        int updateAndGetResultCount = updateAndGetResultCount();
        if (updateAndGetResultCount < 5) {
            Log.se("NeuralTranslator", "identifyLanguage skipped. User try count is still " + updateAndGetResultCount);
        } else if (this.mTranslator != null) {
            this.mTranslator.identifyLanguage(str).addOnCompleteListener(new h(this, System.currentTimeMillis()));
        }
    }

    public boolean isLanguagePackDownloadable() {
        if (!isNetworkNotAvailable()) {
            return !TextUtils.isEmpty(this.mLanguagePackCode);
        }
        Log.se("NeuralTranslator", "download is not available. network issue.");
        return false;
    }

    public void open() {
        long currentTimeMillis = System.currentTimeMillis();
        if (this.mTranslator == null) {
            this.mTranslator = new com.samsung.android.sdk.scs.ai.translation.NeuralTranslator(AppResources.getAppContext());
        }
        refresh();
        Log.s("NeuralTranslator", "open +" + (System.currentTimeMillis() - currentTimeMillis));
    }

    public void refresh() {
        if (this.mTranslator != null) {
            this.mTranslator.refresh().addOnCompleteListener(new a(3));
        }
    }
}
