package com.samsung.android.gallery.module.dataset;

import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaDataStoryHighlight;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaDataStoryHighlightList extends MediaDataStoryHighlight {
    private int mCount;
    private final List<MediaItem> mItems = new ArrayList();
    /* access modifiers changed from: private */
    public int mMode;

    public MediaDataStoryHighlightList(Blackboard blackboard, String str) {
        super(blackboard, str);
        this.mMode = getMode(str);
    }

    private int getMode(String str) {
        String argValue = ArgumentsUtil.getArgValue(str, "highlight_list_mode", "highlight_list_curation");
        Log.d(this.TAG, "getMode", argValue);
        if ("highlight_list_full".equals(argValue)) {
            return 0;
        }
        return 1;
    }

    public MediaDataStoryHighlight.ItemCurationProcessor createItemCurationProcessor() {
        return new MediaDataStoryHighlight.ItemCurationProcessor() {
            public int getCount() {
                if (MediaDataStoryHighlightList.this.mMode == 0) {
                    return MediaDataStoryHighlightList.this.mFullItems.size();
                }
                return super.getCount();
            }
        };
    }

    public int getCount() {
        return this.mCount;
    }

    public List<Long> getFileIds() {
        return (List) this.mItems.stream().map(new K(23)).collect(Collectors.toList());
    }

    public ArrayList<MediaItem> getFullData() {
        return new ArrayList<>(this.mFullItems);
    }

    public int getRealCount() {
        return this.mCount;
    }

    public void onDestroy() {
        super.onDestroy();
        this.mItems.clear();
        this.mCount = 0;
        this.mMode = 1;
    }

    public MediaItem readInternal(int i2, String str) {
        try {
            acquireReadLock("readInternal");
            if (i2 < this.mCount && i2 >= 0) {
                return this.mItems.get(i2);
            }
            StringCompat stringCompat = this.TAG;
            Log.w(stringCompat, "fail to read from " + str + "{" + i2 + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.mCount + "}");
            releaseReadLock("readInternal");
            return null;
        } finally {
            releaseReadLock("readInternal");
        }
    }

    public void reopen(String str) {
        this.mMode = getMode(str);
        super.reopen(str);
    }

    public void reorderData(int i2, int i7) {
        if (i2 < 0 || i2 >= this.mItems.size()) {
            Log.e((CharSequence) this.TAG, "reorderData failed e=invalid index", Integer.valueOf(i2), Integer.valueOf(i7), Integer.valueOf(this.mItems.size()));
            return;
        }
        this.mItems.add(i7, this.mItems.remove(i2));
    }

    public void swapItems() {
        super.swapItems();
        this.mItems.clear();
        if (this.mMode == 0) {
            this.mItems.addAll(this.mFullItems);
        } else {
            this.mItems.addAll(this.mCurationItems);
        }
        this.mCount = this.mItems.size();
    }
}
