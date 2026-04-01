package com.samsung.android.gallery.app.controller.externals;

import K5.a;
import M4.j;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.media.CreateMediaHelper;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.sec.android.gallery3d.R;
import i.C0212a;
import java.util.ArrayList;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CreateMeituPostCmd extends CreateMeituMediaCmd {
    private String getExceedMaxCountToastMessage(Context context, CreateMediaHelper.SupportType supportType) {
        if (CreateMediaHelper.SupportType.NOT_SUPPORT_MEITU_POST_VIDEO_COUNT.equals(supportType)) {
            return context.getResources().getQuantityString(R.plurals.can_not_add_more_than_videos_meitu_post_plurals, 5, new Object[]{5});
        }
        if (CreateMediaHelper.SupportType.NOT_SUPPORT_MEITU_POST_ITEM_COUNT.equals(supportType)) {
            return context.getResources().getQuantityString(R.plurals.can_not_include_more_than_items_meitu_post_plurals, 9, new Object[]{9});
        }
        return "";
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$showCombinationErrorToast$3(CreateMediaHelper.SupportType supportType) {
        if (CreateMediaHelper.SupportType.NOT_SUPPORT_MEITU_POST_VIDEO_COUNT.equals(supportType) || CreateMediaHelper.SupportType.NOT_SUPPORT_MEITU_POST_ITEM_COUNT.equals(supportType)) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showCombinationErrorToast$4(CreateMediaHelper.SupportType supportType) {
        showToast(getExceedMaxCountToastMessage(getContext(), supportType));
    }

    public CreateMediaHelper.SupportType checkSupportType(MediaItem[] mediaItemArr, boolean z) {
        for (MediaItem isCloudOnly : mediaItemArr) {
            if (isCloudOnly.isCloudOnly()) {
                return CreateMediaHelper.SupportType.NOT_SUPPORT_COMMON;
            }
        }
        if (mediaItemArr.length > 9) {
            return CreateMediaHelper.SupportType.NOT_SUPPORT_MEITU_POST_ITEM_COUNT;
        }
        if (Arrays.stream(mediaItemArr).filter(new j(5)).count() > 5) {
            return CreateMediaHelper.SupportType.NOT_SUPPORT_MEITU_POST_VIDEO_COUNT;
        }
        return CreateMediaHelper.SupportType.SUPPORT;
    }

    public /* bridge */ /* synthetic */ void done(MediaItem[] mediaItemArr) {
        super.done(mediaItemArr);
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_MENU_CREATE_MEITU_POST.toString();
    }

    public Intent getIntent(ArrayList<Uri> arrayList) {
        Intent intent = new Intent(C0212a.p(new StringBuilder(), CreateMeituMediaCmd.MT_ACTION, ".PUZZLE"));
        intent.setType("*/*");
        intent.setPackage(CreateMeituMediaCmd.MT_PACKAGE_NAME);
        intent.putParcelableArrayListExtra("android.intent.extra.STREAM", arrayList);
        intent.putExtra("function", "poster");
        intent.putExtra("source", "Samsung");
        return intent;
    }

    public int getMaxCount() {
        return 9;
    }

    public CreateMediaHelper.SupportType getSupportType(MediaItem mediaItem) {
        if (mediaItem.isCloudOnly()) {
            return CreateMediaHelper.SupportType.NOT_SUPPORT_COMMON;
        }
        MediaItem[] selectedItems = getHandler().getSelectedItems();
        if (selectedItems.length > 9) {
            return CreateMediaHelper.SupportType.NOT_SUPPORT_MEITU_POST_ITEM_COUNT;
        }
        if (!mediaItem.isVideo() || Arrays.stream(selectedItems).filter(new j(4)).count() < 5) {
            return CreateMediaHelper.SupportType.SUPPORT;
        }
        return CreateMediaHelper.SupportType.NOT_SUPPORT_MEITU_POST_VIDEO_COUNT;
    }

    public int getTitleRes() {
        return R.string.meitu_poster;
    }

    public boolean isExceedMaxCount() {
        MediaItem[] selectedItems = getHandler().getSelectedItems();
        if (Arrays.stream(selectedItems).filter(new j(6)).count() > 5 || selectedItems.length > 9) {
            return true;
        }
        return false;
    }

    public /* bridge */ /* synthetic */ boolean isSupported(MediaItem mediaItem) {
        return super.isSupported(mediaItem);
    }

    public void showCombinationErrorToast(CreateMediaHelper.SupportType... supportTypeArr) {
        Arrays.stream(supportTypeArr).filter(new j(3)).findFirst().ifPresent(new a(12, this));
    }

    public void showExceedMaxCountToast(Context context) {
        showToast(getExceedMaxCountToastMessage(context, CreateMediaHelper.SupportType.NOT_SUPPORT_MEITU_POST_ITEM_COUNT));
    }

    public void showToast(CreateMediaHelper.SupportType supportType) {
        if (CreateMediaHelper.SupportType.NOT_SUPPORT_MEITU_POST_ITEM_COUNT.equals(supportType) || CreateMediaHelper.SupportType.NOT_SUPPORT_MEITU_POST_VIDEO_COUNT.equals(supportType)) {
            showToast(getExceedMaxCountToastMessage(getContext(), supportType));
        } else {
            super.showToast(supportType);
        }
    }
}
