package com.samsung.android.app.sdk.deepsky.objectcapture.controller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.samsung.android.app.sdk.deepsky.objectcapture.video.VideoClipper;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J#\u0010\u000b\u001a\u00020\n2\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0016¢\u0006\u0004\b\u000b\u0010\fR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\r¨\u0006\u000f"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/controller/StickerCenterReceiver;", "Landroid/content/BroadcastReceiver;", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/video/VideoClipper;", "videoClipper", "<init>", "(Lcom/samsung/android/app/sdk/deepsky/objectcapture/video/VideoClipper;)V", "Landroid/content/Context;", "context", "Landroid/content/Intent;", "intent", "Lme/x;", "onReceive", "(Landroid/content/Context;Landroid/content/Intent;)V", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/video/VideoClipper;", "Companion", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class StickerCenterReceiver extends BroadcastReceiver {
    public static final String ACTION_STICKER_CENTER_RECEIVER = "com.samsung.android.app.sdk.deepsky.objectcapture.ACTION_STICKER_CENTER";
    public static final Companion Companion = new Companion((e) null);
    public static final String STICKER_CENTER_GIF_ACCESS_TOKEN = "ACCESS_TOKEN";
    public static final String TAG = "StickerCenterReceiver";
    private final VideoClipper videoClipper;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/controller/StickerCenterReceiver$Companion;", "", "<init>", "()V", "TAG", "", "ACTION_STICKER_CENTER_RECEIVER", "STICKER_CENTER_GIF_ACCESS_TOKEN", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    public StickerCenterReceiver(VideoClipper videoClipper2) {
        j.e(videoClipper2, "videoClipper");
        this.videoClipper = videoClipper2;
    }

    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "onReceive");
        if (intent != null && j.a(intent.getAction(), ACTION_STICKER_CENTER_RECEIVER)) {
            Integer valueOf = Integer.valueOf(intent.getIntExtra("ACCESS_TOKEN", 0));
            Log.i(TAG, "token : " + valueOf);
            this.videoClipper.abort();
            if (context != null) {
                context.unregisterReceiver(this);
            }
        }
    }
}
