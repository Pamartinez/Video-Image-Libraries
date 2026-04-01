package com.samsung.android.sdk.scs.ai.visual;

import android.content.Context;
import android.os.Bundle;
import com.samsung.android.sdk.scs.ai.language.a;
import com.samsung.android.sdk.scs.base.tasks.Task;
import com.samsung.android.sdk.scs.base.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class WallpaperGenerator {
    private static final String TAG = "ScsApi@WallpaperGenerator";
    private final WallpaperServiceExecutor mCancelServiceExecutor;
    private final WallpaperServiceExecutor mMainServiceExecutor;

    public WallpaperGenerator(Context context) {
        Log.d(TAG, "WallpaperGenerator");
        this.mMainServiceExecutor = new WallpaperServiceExecutor(context);
        this.mCancelServiceExecutor = new WallpaperServiceExecutor(context);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$release$0(Task task) {
        this.mMainServiceExecutor.deInit();
        this.mCancelServiceExecutor.deInit();
    }

    public Task<Boolean> cancel(String str) {
        Log.d(TAG, "cancel()");
        WallpaperCancelRunnable wallpaperCancelRunnable = new WallpaperCancelRunnable(this.mCancelServiceExecutor);
        wallpaperCancelRunnable.setTaskId(str);
        this.mCancelServiceExecutor.execute(wallpaperCancelRunnable);
        return wallpaperCancelRunnable.getTask();
    }

    public Task<WallpaperResult> generate(Bundle bundle) {
        Log.d(TAG, "generate()");
        WallpaperGenerateRunnable wallpaperGenerateRunnable = new WallpaperGenerateRunnable(this.mMainServiceExecutor);
        wallpaperGenerateRunnable.setBundle(bundle);
        this.mMainServiceExecutor.execute(wallpaperGenerateRunnable);
        return wallpaperGenerateRunnable.getTask();
    }

    public Task<Boolean> prepare() {
        Log.d(TAG, "prepare()");
        WallpaperPrepareRunnable wallpaperPrepareRunnable = new WallpaperPrepareRunnable(this.mMainServiceExecutor);
        this.mMainServiceExecutor.execute(wallpaperPrepareRunnable);
        return wallpaperPrepareRunnable.getTask();
    }

    public Task<Boolean> release() {
        Log.d(TAG, "release()");
        WallpaperReleaseRunnable wallpaperReleaseRunnable = new WallpaperReleaseRunnable(this.mMainServiceExecutor);
        this.mMainServiceExecutor.execute(wallpaperReleaseRunnable);
        wallpaperReleaseRunnable.getTask().addOnCompleteListener(new a(6, this));
        return wallpaperReleaseRunnable.getTask();
    }
}
