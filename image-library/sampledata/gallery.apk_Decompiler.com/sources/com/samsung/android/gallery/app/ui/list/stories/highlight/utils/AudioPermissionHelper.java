package com.samsung.android.gallery.app.ui.list.stories.highlight.utils;

import C4.i;
import android.os.Bundle;
import com.samsung.android.gallery.app.controller.internals.RequestRuntimePermissionCmd;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.IStoryHighlightView;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.RuntimePermissionUtil;
import com.sec.android.gallery3d.R;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import k7.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AudioPermissionHelper {
    private final AtomicBoolean mCheckedWhilePlay;
    private final SubscriberListener mPermissionListener;
    private final HashMap<Integer, Runnable> mPostRunnableMap = new HashMap<>();
    private final IStoryHighlightView mView;

    public AudioPermissionHelper(IStoryHighlightView iStoryHighlightView) {
        j jVar = new j(9, this);
        this.mPermissionListener = jVar;
        this.mCheckedWhilePlay = new AtomicBoolean();
        this.mView = iStoryHighlightView;
        if (requireCheckAudioPermission()) {
            iStoryHighlightView.getBlackboard().subscribeOnUi("lifecycle://on_request_permission_result", jVar);
        }
    }

    private void doWhenUnhandled(boolean z, int i2, Runnable runnable) {
        if (!z && i2 == 0 && runnable != null) {
            runnable.run();
        }
    }

    private int getRequestCode(int i2) {
        if (i2 == 0) {
            return 121;
        }
        return 122;
    }

    private boolean ignoreAfterOnceChecked(int i2) {
        if (i2 != 0 || !this.mCheckedWhilePlay.getAndSet(true)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(Object obj, Bundle bundle) {
        onRequestPermissionResult(obj);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$requestPermission$1(int i2, Runnable runnable, Boolean bool) {
        doWhenUnhandled(bool.booleanValue(), i2, runnable);
    }

    private void onRequestPermissionResult(Object obj) {
        if (this.mView.isViewActive()) {
            Object[] objArr = (Object[]) obj;
            int i2 = 0;
            Integer num = (Integer) objArr[0];
            num.intValue();
            String[] strArr = (String[]) objArr[1];
            int[] iArr = (int[]) objArr[2];
            while (i2 < strArr.length) {
                if (iArr[i2] != -1) {
                    i2++;
                } else {
                    return;
                }
            }
            Runnable remove = this.mPostRunnableMap.remove(num);
            if (remove != null) {
                remove.run();
            }
        }
    }

    private boolean requireCheckAudioPermission() {
        return SdkConfig.atLeast(SdkConfig.GED.T);
    }

    public void clear() {
        if (requireCheckAudioPermission()) {
            this.mView.getBlackboard().unsubscribe("lifecycle://on_request_permission_result", this.mPermissionListener);
        }
        this.mPostRunnableMap.clear();
    }

    public void requestPermission(Runnable runnable, int i2) {
        if (!requireCheckAudioPermission()) {
            runnable.run();
            return;
        }
        String[] strArr = RuntimePermissionUtil.AUDIO_PERMISSION_GROUP;
        int requestCode = getRequestCode(i2);
        if (!RuntimePermissionUtil.hasPermission(this.mView.getContext(), strArr)) {
            if (!ignoreAfterOnceChecked(i2)) {
                Object[] objArr = {strArr, Integer.valueOf(requestCode), Integer.valueOf(R.string.permission_function_edit_people_tag), new i((Object) this, i2, (Object) runnable, 10)};
                this.mPostRunnableMap.put(Integer.valueOf(requestCode), runnable);
                new RequestRuntimePermissionCmd().execute(this.mView.getEventContext(), objArr);
            } else if (runnable != null) {
                runnable.run();
            }
        } else if (runnable != null) {
            runnable.run();
        }
    }
}
