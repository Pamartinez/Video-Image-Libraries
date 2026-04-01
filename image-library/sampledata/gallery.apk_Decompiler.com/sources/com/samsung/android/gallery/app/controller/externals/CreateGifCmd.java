package com.samsung.android.gallery.app.controller.externals;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.samsung.android.app.sdk.deepsky.objectcapture.utils.ServiceManagerUtil;
import com.samsung.android.gallery.database.dal.local.table.HistoryTable;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.media.CreateMediaHelper;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.Log;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Collections;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CreateGifCmd extends CreateMediaCmd {
    private boolean mIsGroupShot;

    public CreateGifCmd() {
        this.mIsGroupShot = false;
    }

    private Intent getGifMakerIntent(MediaItem[] mediaItemArr) {
        ArrayList arrayList = new ArrayList();
        Intent intent = new Intent("com.sec.android.mimage.photoretouching.motionphoto");
        Bundle bundle = new Bundle();
        if (loadUriList(mediaItemArr, arrayList)) {
            Collections.reverse(arrayList);
            intent.putExtra("burst_shot", mediaItemArr[0].isBurstShot());
            intent.putExtra("similar_shot", mediaItemArr[0].isSimilarShot());
        }
        bundle.putParcelableArrayList("selectedItems", arrayList);
        intent.setFlags(67108864);
        intent.putExtras(bundle);
        intent.putExtra("selectedCount", arrayList.size());
        return intent;
    }

    private boolean isGroupShot(MediaItem mediaItem) {
        if (mediaItem.isSimilarShot() || mediaItem.isBurstShot()) {
            return true;
        }
        return false;
    }

    private boolean loadUriList(MediaItem[] mediaItemArr, ArrayList<Uri> arrayList) {
        long j2;
        boolean z = false;
        if (isGroupShot(mediaItemArr[0])) {
            j2 = mediaItemArr[0].getGroupMediaId();
        } else {
            j2 = 0;
        }
        for (MediaItem mediaItem : mediaItemArr) {
            if (isSupported(mediaItem)) {
                arrayList.add(ContentUri.getUri(mediaItem));
                if (arrayList.size() >= 100) {
                    break;
                } else if (j2 > 0 && j2 != mediaItem.getGroupMediaId()) {
                    j2 = 0;
                }
            }
        }
        if (j2 > 0 && arrayList.size() > 1) {
            z = true;
        }
        if (arrayList.size() > 50 && !z) {
            arrayList.subList(50, arrayList.size()).clear();
        }
        return z;
    }

    public void createMedia(MediaItem[] mediaItemArr) {
        if (mediaItemArr != null) {
            try {
                getActivity().startActivityForResult(getGifMakerIntent(mediaItemArr), 789);
            } catch (ActivityNotFoundException unused) {
                Log.d(this.TAG, "Activity Not Found !!!");
                guideDownloadPackage(ServiceManagerUtil.PHOTO_EDIT_PACKAGE_NAME, getContext().getString(R.string.photo_editor));
            } catch (Exception unused2) {
                Log.e(this.TAG, "There is problem in startGifMaker");
            }
            addOperationHistory(HistoryTable.ActionKeyword.CREATE_GIF, mediaItemArr);
            return;
        }
        Log.d(this.TAG, "selected items are null");
    }

    public /* bridge */ /* synthetic */ void done(MediaItem[] mediaItemArr) {
        super.done(mediaItemArr);
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_MENU_GIF.toString();
    }

    public String getExceedMaxCountToastMessage() {
        return getContext().getString(R.string.gif_maker_max_size_reached, new Object[]{Integer.valueOf(getMaxCount())});
    }

    public int getMaxCount() {
        if (this.mIsGroupShot) {
            return 100;
        }
        return 50;
    }

    public CreateMediaHelper.SupportType getSupportType(MediaItem mediaItem) {
        return CreateMediaHelper.supportGifMaker(mediaItem.getMediaType());
    }

    public int getTitleRes() {
        return R.string.create_gif_menu;
    }

    public /* bridge */ /* synthetic */ boolean isSupported(MediaItem mediaItem) {
        return super.isSupported(mediaItem);
    }

    public /* bridge */ /* synthetic */ void showExceedMaxCountToast(Context context) {
        super.showExceedMaxCountToast(context);
    }

    public CreateGifCmd(boolean z) {
        this.mIsGroupShot = z;
    }
}
