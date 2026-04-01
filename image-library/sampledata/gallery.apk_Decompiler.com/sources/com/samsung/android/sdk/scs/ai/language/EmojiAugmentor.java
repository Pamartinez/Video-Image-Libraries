package com.samsung.android.sdk.scs.ai.language;

import A4.S;
import a6.g;
import android.content.Context;
import android.os.Parcel;
import android.os.RemoteException;
import ba.C0582a;
import c4.C0431a;
import com.samsung.android.sdk.scs.ai.language.service.AuthHeader;
import com.samsung.android.sdk.scs.ai.language.service.EmojiAugmentationServiceExecutor;
import com.samsung.android.sdk.scs.ai.language.service.LlmServiceObserver2;
import com.samsung.android.sdk.scs.ai.language.service.LlmServiceRunnable;
import com.samsung.android.sdk.scs.ai.language.service.OnDeviceRunnable;
import com.samsung.android.sdk.scs.ai.language.service.OnDeviceServiceExecutor;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.tasks.Task;
import com.samsung.android.sdk.scs.base.utils.Log;
import com.samsung.android.sivs.ai.sdkcommon.language.C0164k;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class EmojiAugmentor {
    private static final String TAG = "EmojiAugmentor";
    private final String featureName = Feature.FEATURE_AI_GEN_EMOJI_AUGMENTATION;
    private final EmojiAugmentationServiceExecutor mServiceExecutor;
    private final OnDeviceServiceExecutor onDeviceServiceExecutor;

    public EmojiAugmentor(Context context) {
        Log.d(TAG, TAG);
        this.mServiceExecutor = new EmojiAugmentationServiceExecutor(context);
        this.onDeviceServiceExecutor = new OnDeviceServiceExecutor(context);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$emojiAugment$0(AppInfo appInfo, String str, Map map, LlmServiceObserver2 llmServiceObserver2) {
        try {
            ((C0164k) this.mServiceExecutor.getService()).a(new AuthHeader(appInfo).generateHeaderMap(this.mServiceExecutor.context), str, llmServiceObserver2, map);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    /* access modifiers changed from: private */
    public void lambda$release$1(LlmServiceObserver2 llmServiceObserver2) {
        Parcel obtain;
        try {
            C0164k kVar = (C0164k) this.mServiceExecutor.getService();
            kVar.getClass();
            obtain = Parcel.obtain();
            obtain.writeInterfaceToken("com.samsung.android.sivs.ai.sdkcommon.language.IEmojiAugmentationService");
            kVar.f1709a.transact(5, obtain, (Parcel) null, 1);
            obtain.recycle();
            llmServiceObserver2.onNext(new ArrayList());
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        } catch (Throwable th) {
            obtain.recycle();
            throw th;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$release$2(Task task) {
        Log.i(TAG, "connected: " + this.mServiceExecutor.isConnected());
        this.mServiceExecutor.deInit();
        this.onDeviceServiceExecutor.deInit();
    }

    public Task<Result> emojiAugment(AppInfo appInfo, String str) {
        return emojiAugment(appInfo, str, new HashMap());
    }

    public void release() {
        Log.i(TAG, "release: " + this.mServiceExecutor.isConnected());
        LlmServiceRunnable llmServiceRunnable = new LlmServiceRunnable(Feature.FEATURE_AI_GEN_EMOJI_AUGMENTATION, false, new C0582a(18, this), new C0431a(22));
        this.mServiceExecutor.execute(llmServiceRunnable);
        llmServiceRunnable.getTask().addOnCompleteListener(new g(26, this));
    }

    @Deprecated
    public void unLoadOndeviceModel(AppInfo appInfo) {
        this.onDeviceServiceExecutor.execute(new OnDeviceRunnable(this.onDeviceServiceExecutor));
    }

    public Task<Result> emojiAugment(AppInfo appInfo, String str, Map<String, String> map) {
        LlmServiceRunnable llmServiceRunnable = new LlmServiceRunnable(Feature.FEATURE_AI_GEN_EMOJI_AUGMENTATION, appInfo.isStreamingMode(), new S(this, appInfo, str, map, 7), new C0431a(22));
        this.mServiceExecutor.execute(llmServiceRunnable);
        return llmServiceRunnable.getTask();
    }
}
