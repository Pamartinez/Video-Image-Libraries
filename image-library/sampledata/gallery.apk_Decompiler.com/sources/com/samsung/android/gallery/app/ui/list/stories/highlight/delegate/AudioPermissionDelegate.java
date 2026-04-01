package com.samsung.android.gallery.app.ui.list.stories.highlight.delegate;

import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Delegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Event;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.IStoryHighlightView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.utils.AudioPermissionHelper;
import com.samsung.android.gallery.support.utils.Log;
import o4.a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AudioPermissionDelegate extends Delegate {
    private AudioPermissionHelper mAudioPermissionHelper;

    public AudioPermissionDelegate(IStoryHighlightView iStoryHighlightView) {
        super(iStoryHighlightView);
    }

    public void addListenEvent() {
        addEvent(Event.CHECK_AUDIO_PERMISSION, new a(1, this));
    }

    public void onDestroy() {
        AudioPermissionHelper audioPermissionHelper = this.mAudioPermissionHelper;
        if (audioPermissionHelper != null) {
            audioPermissionHelper.clear();
        }
    }

    public void requestAudioRuntimePermission(Object... objArr) {
        if (objArr == null || objArr.length < 2) {
            Log.e(this.TAG, "no detail for audio permission");
            return;
        }
        Runnable runnable = objArr[0];
        int intValue = objArr[1].intValue();
        if (this.mAudioPermissionHelper == null) {
            this.mAudioPermissionHelper = new AudioPermissionHelper(this.mView);
        }
        this.mAudioPermissionHelper.requestPermission(runnable, intValue);
    }
}
