package com.samsung.android.gallery.support.utils;

import A.a;
import N2.j;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import com.samsung.android.sum.core.Def;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SafeClipboard {
    private ClipboardManager mManager;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ImageClipStatusHolder {
        static final ImageClipStatusHolder instance = new ImageClipStatusHolder();
        Long time;
        boolean value;

        public boolean compute(boolean z) {
            if (this.time == null || System.currentTimeMillis() - this.time.longValue() > Def.MEDIAFILTER_MESSAGE_TIMEOUT_MILLIS || z) {
                long currentTimeMillis = System.currentTimeMillis();
                this.value = new SafeClipboard(AppResources.getAppContext()).hasImageClip();
                this.time = Long.valueOf(System.currentTimeMillis());
                a.A(new Object[]{Boolean.valueOf(this.value), Long.valueOf(currentTimeMillis)}, new StringBuilder("compute"), "SafeClipboard");
            }
            return this.value;
        }
    }

    public SafeClipboard(Context context) {
        try {
            this.mManager = (ClipboardManager) context.getSystemService("clipboard");
        } catch (Error | Exception e) {
            Log.e((CharSequence) "SafeClipboard", j.i(e, new StringBuilder("Failed to get clipboard manager : ")), e.getCause());
            e.printStackTrace();
        }
    }

    public static boolean computeImageClipAvailable(boolean z) {
        return ImageClipStatusHolder.instance.compute(z);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getPrimaryClip$1(ClipData[] clipDataArr) {
        try {
            clipDataArr[0] = this.mManager.getPrimaryClip();
        } catch (Error | Exception e) {
            Log.e((CharSequence) "SafeClipboard", j.i(e, new StringBuilder("Failed to getPrimaryClip : ")), e.getCause());
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$getPrimaryClip$2(Boolean bool) {
        if (bool != null && bool.booleanValue()) {
            Log.w("SafeClipboard", "get primary clip interrupted");
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getPrimaryClipDescription$3(ClipDescription[] clipDescriptionArr) {
        try {
            clipDescriptionArr[0] = this.mManager.getPrimaryClipDescription();
        } catch (Error | Exception e) {
            Log.e((CharSequence) "SafeClipboard", j.i(e, new StringBuilder("Failed to getPrimaryClipDescription : ")), e.getCause());
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$getPrimaryClipDescription$4(Boolean bool) {
        if (bool != null && bool.booleanValue()) {
            Log.w("SafeClipboard", "get primary clip description interrupted");
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$hasImageClip$0(AtomicBoolean atomicBoolean) {
        try {
            ClipDescription primaryClipDescription = this.mManager.getPrimaryClipDescription();
            boolean z = false;
            if (primaryClipDescription != null && primaryClipDescription.getMimeTypeCount() == 1 && primaryClipDescription.getMimeType(0).contains("image/")) {
                z = true;
            }
            atomicBoolean.set(z);
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            Log.w("SafeClipboard", "get primary clip description failed. e=" + e.getMessage());
        } catch (Error | Exception e7) {
            Log.e((CharSequence) "SafeClipboard", "get primary clip description failed", e7);
        }
    }

    public ClipData getPrimaryClip() {
        if (this.mManager == null) {
            return null;
        }
        ClipData[] clipDataArr = {null};
        new LatchBuilder("SafeClipboard").addWorker(new C0685x(2, this, clipDataArr)).setTimeout(Def.MEDIAFILTER_MESSAGE_TIMEOUT_MILLIS).setPostExecutor((Consumer<Boolean>) new O(0)).start();
        return clipDataArr[0];
    }

    public ClipDescription getPrimaryClipDescription() {
        if (this.mManager == null) {
            return null;
        }
        ClipDescription[] clipDescriptionArr = {null};
        new LatchBuilder("SafeClipboard").addWorker(new C0685x(4, this, clipDescriptionArr)).setTimeout(Def.MEDIAFILTER_MESSAGE_TIMEOUT_MILLIS).setPostExecutor((Consumer<Boolean>) new O(1)).start();
        return clipDescriptionArr[0];
    }

    public boolean hasImageClip() {
        if (this.mManager == null) {
            return false;
        }
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        LatchBuilder.executeSilentLatch("SafeClipboard", Def.MEDIAFILTER_MESSAGE_TIMEOUT_MILLIS, new C0685x(3, this, atomicBoolean));
        return atomicBoolean.get();
    }

    public boolean hasMimeType(String str) {
        try {
            ClipDescription primaryClipDescription = getPrimaryClipDescription();
            if (primaryClipDescription == null || !primaryClipDescription.hasMimeType(str)) {
                return false;
            }
            return true;
        } catch (Error | Exception e) {
            Log.e((CharSequence) "SafeClipboard", j.i(e, new StringBuilder("Failed to hasMimeType : ")), e.getCause());
            return false;
        }
    }

    public boolean hasService() {
        if (this.mManager != null) {
            return true;
        }
        return false;
    }

    public void setPrimaryClip(ClipData clipData) {
        try {
            ClipboardManager clipboardManager = this.mManager;
            if (clipboardManager != null) {
                clipboardManager.setPrimaryClip(clipData);
            }
        } catch (Error | Exception e) {
            Log.e((CharSequence) "SafeClipboard", j.i(e, new StringBuilder("Failed to setPrimaryClip : ")), e.getCause());
        }
    }
}
