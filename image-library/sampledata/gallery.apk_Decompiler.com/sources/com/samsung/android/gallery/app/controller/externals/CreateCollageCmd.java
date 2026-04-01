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
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.media.CreateMediaHelper;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StringResources;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CreateCollageCmd extends CreateMediaCmd {
    private final boolean SUPPORT_VIDEO_COLLAGE;

    public CreateCollageCmd() {
        boolean z;
        if (!PreferenceFeatures.OneUi40.SUPPORT_COLLAGE_ON_VIDEO_TRIMMER || !Features.isEnabled(Features.SUPPORT_VIDEO_COLLAGE)) {
            z = false;
        } else {
            z = true;
        }
        this.SUPPORT_VIDEO_COLLAGE = z;
    }

    private Intent getIntentForPhotoEditor(MediaItem[] mediaItemArr) {
        ArrayList<Uri> uriList = getUriList(mediaItemArr);
        Intent intent = new Intent("com.sec.android.mimage.photoretouching.multigrid");
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("selectedItems", uriList);
        intent.putExtras(bundle);
        intent.putExtra("selectedCount", uriList.size());
        setIntentWithCommonExtra(intent);
        return intent;
    }

    private Intent getIntentForVideoTrimmer(MediaItem[] mediaItemArr) {
        ArrayList<Uri> uriList = getUriList(mediaItemArr);
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.setClassName("com.samsung.app.newtrim", "com.samsung.app.newtrim.multigrid.main.MultiGridActivity");
        intent.putExtra("selectedItems", uriList);
        return intent;
    }

    private void requestGuideDownloadPackage() {
        if (!PreferenceFeatures.OneUi40.SUPPORT_COLLAGE_ON_VIDEO_TRIMMER) {
            guideDownloadPackage(ServiceManagerUtil.PHOTO_EDIT_PACKAGE_NAME, getContext().getString(R.string.photo_editor));
        } else if (Features.isEnabled(Features.SUPPORT_DOWNLOAD_VIDEO_TRIMMER)) {
            guideDownloadPackage("com.samsung.app.newtrim", StringResources.getVideoTrimmerName());
        }
    }

    public CreateMediaHelper.SupportType checkSupportType(MediaItem[] mediaItemArr, boolean z) {
        int i2;
        if (!this.SUPPORT_VIDEO_COLLAGE) {
            return super.checkSupportType(mediaItemArr, z);
        }
        AtomicInteger atomicInteger = new AtomicInteger();
        int i7 = 0;
        while (true) {
            if (z) {
                i2 = getMaxCountToCheck(mediaItemArr);
            } else {
                i2 = mediaItemArr.length;
            }
            if (i7 >= i2) {
                return CreateMediaHelper.supportVideoCollageMaker(atomicInteger.get());
            }
            if (mediaItemArr[i7].isVideo() && !mediaItemArr[i7].isCloudOnly()) {
                atomicInteger.incrementAndGet();
            }
            i7++;
        }
    }

    public void createMedia(MediaItem[] mediaItemArr) {
        if (isUpsm()) {
            showToast((int) R.string.cannot_use_this_function_while_mpsm_is_on);
        } else if (mediaItemArr != null) {
            try {
                getContext().startActivity(getIntent(mediaItemArr));
            } catch (ActivityNotFoundException unused) {
                Log.d(this.TAG, "Activity Not Found !!!");
                requestGuideDownloadPackage();
            } catch (Exception unused2) {
                Log.e(this.TAG, "There is problem in collage maker.");
            }
            addOperationHistory(HistoryTable.ActionKeyword.CREATE_COLLAGE, mediaItemArr);
        } else {
            Log.d(this.TAG, "selected items are null");
        }
    }

    public /* bridge */ /* synthetic */ void done(MediaItem[] mediaItemArr) {
        super.done(mediaItemArr);
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_MENU_COLLAGE.toString();
    }

    public String getExceedMaxCountToastMessage() {
        if (this.SUPPORT_VIDEO_COLLAGE) {
            return getContext().getResources().getQuantityString(R.plurals.vidoe_collage_maker_max_size_reached, getMaxCount(), new Object[]{Integer.valueOf(getMaxCount())});
        }
        return getContext().getString(R.string.collage_maker_max_size_reached, new Object[]{Integer.valueOf(getMaxCount())});
    }

    public Intent getIntent(MediaItem[] mediaItemArr) {
        if (PreferenceFeatures.OneUi40.SUPPORT_COLLAGE_ON_VIDEO_TRIMMER) {
            return getIntentForVideoTrimmer(mediaItemArr);
        }
        return getIntentForPhotoEditor(mediaItemArr);
    }

    public int getMaxCount() {
        return CreateMediaHelper.COLLAGE_MAX_ITEM_COUNT;
    }

    public CreateMediaHelper.SupportType getSupportType(MediaItem mediaItem) {
        int i2;
        if (!this.SUPPORT_VIDEO_COLLAGE) {
            return CreateMediaHelper.supportCollageMaker(mediaItem.getMediaType());
        }
        if (mediaItem.isVideo() && mediaItem.isCloudOnly()) {
            return CreateMediaHelper.SupportType.NOT_SUPPORT_CLOUD_VIDEO_FOR_COLLAGE;
        }
        AtomicInteger atomicInteger = new AtomicInteger();
        for (MediaItem mediaItem2 : getHandler().getSelectedItems()) {
            if (!MediaItemUtil.equalsId(mediaItem, mediaItem2) && mediaItem2.isVideo() && !mediaItem2.isCloudOnly()) {
                atomicInteger.incrementAndGet();
            }
        }
        if (mediaItem.isVideo()) {
            i2 = atomicInteger.incrementAndGet();
        } else {
            i2 = atomicInteger.get();
        }
        return CreateMediaHelper.supportVideoCollageMaker(i2);
    }

    public int getTitleRes() {
        return R.string.sa_collage;
    }

    public ArrayList<Uri> getUriList(MediaItem[] mediaItemArr) {
        ArrayList<Uri> arrayList = new ArrayList<>();
        for (MediaItem mediaItem : mediaItemArr) {
            if (isSupported(mediaItem)) {
                arrayList.add(ContentUri.getUri(mediaItem));
            }
            if (arrayList.size() == CreateMediaHelper.COLLAGE_MAX_ITEM_COUNT) {
                break;
            }
        }
        return arrayList;
    }

    public /* bridge */ /* synthetic */ boolean isSupported(MediaItem mediaItem) {
        return super.isSupported(mediaItem);
    }

    public /* bridge */ /* synthetic */ void showExceedMaxCountToast(Context context) {
        super.showExceedMaxCountToast(context);
    }
}
