package com.samsung.android.gallery.module.story;

import android.content.Context;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoryAppBarManager {
    private static volatile StoryAppBarManager sInstance;
    private ArrayList<Object> mData = new ArrayList<>();

    private StoryAppBarManager() {
    }

    public static StoryAppBarManager getInstance() {
        if (sInstance == null) {
            synchronized (StoryAppBarManager.class) {
                try {
                    if (sInstance == null) {
                        sInstance = new StoryAppBarManager();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return sInstance;
    }

    public FileItemInterface getMediaItem(int i2) {
        if (this.mData.size() <= i2) {
            return null;
        }
        this.mData.get(i2).getClass();
        throw new ClassCastException();
    }

    public int getStoryId(int i2) {
        if (this.mData.size() <= i2) {
            return -1;
        }
        this.mData.get(i2).getClass();
        throw new ClassCastException();
    }

    public String getTitle(Context context, int i2) {
        if (this.mData.size() <= i2) {
            return null;
        }
        this.mData.get(i2).getClass();
        throw new ClassCastException();
    }

    public boolean isVisibleCondition() {
        if (this.mData.size() >= 2) {
            return true;
        }
        return false;
    }
}
