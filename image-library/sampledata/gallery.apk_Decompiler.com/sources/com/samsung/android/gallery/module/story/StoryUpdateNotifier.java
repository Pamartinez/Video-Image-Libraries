package com.samsung.android.gallery.module.story;

import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.samsung.android.gallery.support.providers.CmhUri;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoryUpdateNotifier {
    /* access modifiers changed from: private */
    public static final Uri AUTHORITY_URI = CmhUri.getAuthority();
    private static final Uri CMH_STORY_TABLE_URI = CmhUri.getStory();
    private static volatile StoryUpdateNotifier sInstance;
    protected final Handler mMainHandler = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            Context context = (Context) message.obj;
            if (message.what == 0) {
                context.getContentResolver().notifyChange(StoryUpdateNotifier.AUTHORITY_URI, (ContentObserver) null);
            }
        }
    };

    public static StoryUpdateNotifier getInstance() {
        if (sInstance == null) {
            synchronized (StoryUpdateNotifier.class) {
                try {
                    if (sInstance == null) {
                        sInstance = new StoryUpdateNotifier();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return sInstance;
    }

    public void notifyStory(Context context, boolean z) {
        C0212a.x("notifyStory -> onDataChanged ", "StoryUpdateNotifier", z);
        if (z) {
            try {
                if (this.mMainHandler.hasMessages(0)) {
                    this.mMainHandler.removeMessages(0);
                }
                context.getContentResolver().notifyChange(CMH_STORY_TABLE_URI, (ContentObserver) null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (!this.mMainHandler.hasMessages(0)) {
            Message obtain = Message.obtain();
            obtain.what = 0;
            obtain.obj = context;
            this.mMainHandler.sendMessageDelayed(obtain, 1000);
        }
    }
}
