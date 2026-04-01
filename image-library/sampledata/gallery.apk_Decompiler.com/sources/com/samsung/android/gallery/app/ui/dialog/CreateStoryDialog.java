package com.samsung.android.gallery.app.ui.dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.MediaDataFactory;
import com.samsung.android.gallery.module.dialog.ErrorType;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.sec.android.gallery3d.R;
import f4.a;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CreateStoryDialog extends CreateNameDialog {
    final ArrayList<String> mStoryTitleList = new ArrayList<>();

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$loadTitle$0(ArrayList arrayList, MediaItem mediaItem) {
        if (mediaItem != null) {
            arrayList.add(mediaItem.getTitle());
        }
    }

    private void loadStoryList() {
        MediaData open = MediaDataFactory.getInstance(getBlackboard()).open("location://story/albums");
        try {
            loadTitle(open, this.mStoryTitleList);
            if (PreferenceFeatures.OneUi7x.STORY_ONE_UI_70) {
                int childCount = open.getChildCount();
                for (int i2 = 0; i2 < childCount; i2++) {
                    loadTitle(open.getChildMediaData(i2), this.mStoryTitleList);
                }
            }
            if (open != null) {
                open.close();
                return;
            }
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private void loadTitle(MediaData mediaData, ArrayList<String> arrayList) {
        ArrayList<MediaItem> arrayList2;
        if (mediaData != null) {
            arrayList2 = mediaData.getAllData();
        } else {
            arrayList2 = null;
        }
        if (arrayList2 != null) {
            arrayList2.forEach(new a(arrayList, 19));
        }
    }

    public String getDefaultName() {
        String str;
        Bundle arguments = getArguments();
        if (arguments != null) {
            str = arguments.getString("name");
        } else {
            str = null;
        }
        if (str != null && !this.mStoryTitleList.contains(str)) {
            return str;
        }
        int i2 = 1;
        while (true) {
            String format = String.format(getContext().getString(R.string.event_number), new Object[]{Integer.valueOf(i2)});
            if (!this.mStoryTitleList.contains(format)) {
                return format;
            }
            i2++;
        }
    }

    public String getScreenId() {
        return AnalyticsScreenId.SCREEN_EVENT_VIEW_NORMAL.toString();
    }

    public String getTitle() {
        return getResources().getString(R.string.create_story_manually);
    }

    public void initDialog() {
        loadStoryList();
        super.initDialog();
        this.mTextInputCompat.setPrivateImeOptions("disableGifKeyboard=true;disableSticker=true");
    }

    public ErrorType isValid(String str) {
        if (isDottedText(str)) {
            return ErrorType.CANNOT_START_WITH_A_PERIOD_FOR_ALBUM_NAME;
        }
        return ErrorType.NONE;
    }

    public void onCancel(DialogInterface dialogInterface) {
        getBlackboard().post("data://user/dialog/StoryName", (Object) null);
    }

    public boolean predicateText(CharSequence charSequence) {
        if (!charSequence.equals("%") || supportCmh()) {
            return false;
        }
        return true;
    }

    public void publishCancel() {
        postAnalyticsLog(AnalyticsEventId.EVENT_CREATE_STORY_CANCEL);
    }

    public void publishData(String str) {
        postAnalyticsLog(AnalyticsEventId.EVENT_CREATE_STORY_CREATE);
        getBlackboard().post("data://user/dialog/StoryName", str);
    }
}
