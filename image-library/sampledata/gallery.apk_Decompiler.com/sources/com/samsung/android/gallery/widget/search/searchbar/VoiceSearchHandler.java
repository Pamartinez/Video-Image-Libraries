package com.samsung.android.gallery.widget.search.searchbar;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class VoiceSearchHandler {
    private final Handler mHandler;

    public VoiceSearchHandler(final Activity activity, final int i2) {
        this.mHandler = new Handler(Looper.getMainLooper()) {
            public void handleMessage(Message message) {
                if (message.what == 1) {
                    if (i2 == 1) {
                        try {
                            activity.startActivityForResult(new Intent("samsung.honeyboard.honeyvoice.action.RECOGNIZE_SPEECH"), 2312);
                        } catch (ActivityNotFoundException e) {
                            Log.e("VoiceSearchHandler", e.toString());
                        }
                    } else {
                        Intent intent = new Intent("android.speech.action.RECOGNIZE_SPEECH");
                        intent.putExtra("android.speech.extra.LANGUAGE_MODEL", "free_form");
                        try {
                            activity.startActivityForResult(intent, 2311);
                        } catch (ActivityNotFoundException e7) {
                            Log.e("VoiceSearchHandler", e7.toString());
                        }
                    }
                }
            }
        };
    }

    public void onVoiceRecognitionStarted() {
        this.mHandler.sendEmptyMessageDelayed(1, 10);
    }
}
