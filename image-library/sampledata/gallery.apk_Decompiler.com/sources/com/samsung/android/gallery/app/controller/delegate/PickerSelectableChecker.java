package com.samsung.android.gallery.app.controller.delegate;

import A4.C0370e;
import Ab.b;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.gallery.widget.abstraction.SelectableChecker;
import com.sec.android.gallery3d.R;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PickerSelectableChecker implements SelectableChecker<MediaItem> {
    Blackboard mBlackboard;
    protected final Handler mHandler;
    private final AtomicBoolean mIsToastShowing = new AtomicBoolean(false);

    public PickerSelectableChecker(Blackboard blackboard) {
        this.mBlackboard = blackboard;
        this.mHandler = new Handler(Looper.getMainLooper()) {
            public void handleMessage(Message message) {
                int i2 = message.what;
                if (i2 == 1000 || i2 == 1001) {
                    Object obj = message.obj;
                    if (obj instanceof Runnable) {
                        ((Runnable) obj).run();
                    }
                }
            }
        };
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showToast$1(Context context, int i2) {
        Utils.showToast(context, i2);
        this.mIsToastShowing.set(false);
    }

    private void showToast(int i2) {
        if (!this.mIsToastShowing.getAndSet(true)) {
            ThreadUtil.postOnUiThreadDelayed(new b((Object) this, (Object) BlackboardUtils.readAppContext(this.mBlackboard), i2, 22), 100);
        }
    }

    public void done(MediaItem[] mediaItemArr) {
    }

    public int getMaxCount() {
        return PickerUtil.getMaxPickCount(this.mBlackboard);
    }

    public int getUnSelectableStringId() {
        return R.string.unsupported_file_deselected;
    }

    public void lazyFilterLog(int i2, Runnable runnable, int i7) {
        if (!this.mHandler.hasMessages(i2)) {
            Handler handler = this.mHandler;
            handler.sendMessageDelayed(handler.obtainMessage(i2, runnable), (long) i7);
        }
    }

    public boolean selectable(MediaItem mediaItem) {
        if (mediaItem == null || mediaItem.isDrm() || mediaItem.isBroken() || mediaItem.isPostProcessing()) {
            return false;
        }
        return true;
    }

    public void showExceedMaxCountToast(Context context) {
        String str;
        int maxCount = getMaxCount();
        if (maxCount > 1) {
            str = context.getString(R.string.max_size_reached, new Object[]{Integer.valueOf(maxCount)});
        } else {
            str = context.getString(R.string.max_size_reached_for_one);
        }
        Utils.showToast(context, str);
    }

    public boolean isSupported(MediaItem mediaItem) {
        if (selectable(mediaItem)) {
            return true;
        }
        showToast(getUnSelectableStringId());
        lazyFilterLog(1000, new C0370e(mediaItem, 1), 100);
        return false;
    }
}
