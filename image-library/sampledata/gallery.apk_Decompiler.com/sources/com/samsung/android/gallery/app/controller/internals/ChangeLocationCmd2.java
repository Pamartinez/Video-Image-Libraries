package com.samsung.android.gallery.app.controller.internals;

import A.a;
import A9.c;
import E3.e;
import O3.i;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.camera.GppmHelper;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.fileio.redact.FileRedactor;
import com.samsung.android.gallery.module.fileio.redact.FileRedactorBuilder;
import com.samsung.android.gallery.module.fileio.redact.OnProgress;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.search.IntelligentSearchIndex;
import com.samsung.android.gallery.support.utils.ExifUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.MapUtil;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.sdk.sgpl.media.MediaMetadataInterface;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ChangeLocationCmd2 extends FixDateTimeCmd implements OnProgress {
    private FileRedactor mFileRedactor;
    private MediaItem[] mSelectedItems;
    private String mUpdatedAddress;

    private boolean changeIsoLocation(MediaItem mediaItem, double d, double d2) {
        try {
            MediaMetadataInterface mediaMetadataInterface = new MediaMetadataInterface(mediaItem.getPath());
            mediaMetadataInterface.setAttribute(MediaMetadataInterface.Attribute.ATTR_LATITUDE, String.valueOf(d));
            mediaMetadataInterface.setAttribute(MediaMetadataInterface.Attribute.ATTR_LONGITUDE, String.valueOf(d2));
            return mediaMetadataInterface.saveAttributes();
        } catch (Exception e) {
            a.s(e, new StringBuilder("changeIsoLocation failed. e="), this.TAG);
            return false;
        }
    }

    private void changeLocation(MediaItem[] mediaItemArr, String str, double d, double d2) {
        initResultList();
        showProgressDialog(getTotalCount(mediaItemArr));
        this.mUpdatedAddress = str;
        FileRedactor build = FileRedactorBuilder.edit(mediaItemArr).setOperation(new i(this, d, d2)).setCallback(this).withMediaScan(new c(2)).build();
        this.mFileRedactor = build;
        build.run();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$changeLocation$3(double d, double d2, FileItemInterface fileItemInterface) {
        return Boolean.valueOf(changeLocation((MediaItem) fileItemInterface, d, d2));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onExecute$2(EventContext eventContext, ArrayList arrayList) {
        Object[] objArr;
        if (arrayList != null && arrayList.size() > 0 && (objArr = (Object[]) arrayList.get(0)) != null && objArr.length > 2) {
            changeLocation(this.mSelectedItems, (String) objArr[0], ((Double) objArr[1]).doubleValue(), ((Double) objArr[2]).doubleValue());
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onPreExecute$0(MediaItem[] mediaItemArr, long j2, EventContext eventContext, Integer num) {
        String str = this.TAG;
        Log.d(str, "onPreExecute#PppChecker" + Logger.vt(Integer.valueOf(mediaItemArr.length), num, Long.valueOf(j2)));
        if (num.intValue() > 0) {
            mediaItemArr = replacePppItem(eventContext, mediaItemArr);
        }
        super.onPreExecute(eventContext, mediaItemArr);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onPreExecute$1(EventContext eventContext, MediaItem[] mediaItemArr) {
        if (GppmHelper.SUPPORT_DONATION_MULTIPLE) {
            EventContext eventContext2 = eventContext;
            MediaItem[] mediaItemArr2 = mediaItemArr;
            executePppChecker(eventContext2, mediaItemArr2, new e(this, mediaItemArr2, System.currentTimeMillis(), eventContext2, 3));
            return;
        }
        super.onPreExecute(eventContext, mediaItemArr);
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_MENU_EDIT_LOCATION.toString();
    }

    public String getIndexingValue() {
        return this.mUpdatedAddress;
    }

    public Map<String, String> getIndexingValueMap(String str) {
        return IntelligentSearchIndex.TagType.LOCATION.getValueMap(str, str);
    }

    public String getProgressDialogTitle() {
        return getContext().getString(R.string.updating_location_info);
    }

    public boolean isAnalyticsEnabled() {
        return true;
    }

    public void onCancel() {
        super.onCancel();
        FileRedactor fileRedactor = this.mFileRedactor;
        if (fileRedactor != null) {
            fileRedactor.cancel();
            this.mFileRedactor = null;
        }
    }

    public void onCanceled(int i2, int i7, int i8) {
        onComplete(i7);
    }

    public void onCompleted(int i2, int i7, int i8) {
        onComplete(i7);
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        MediaItem[] mediaItemArr;
        Double valueOf = Double.valueOf(MapUtil.INVALID_LOCATION);
        if (objArr.length > 0) {
            mediaItemArr = objArr[0];
        } else {
            mediaItemArr = eventContext.getSelectedItems();
        }
        this.mSelectedItems = mediaItemArr;
        if (mediaItemArr.length > 5000) {
            Utils.showToast(getContext(), (int) R.string.cant_select_more_than_5000_items);
            return;
        }
        Object[] objArr2 = {"", valueOf, valueOf};
        DataCollectionDelegate.getInitInstance(eventContext).setRequestData(new UriBuilder("data://user/EditLocation").appendArg("address", (String) objArr2[0]).appendArg("latitude", ((Double) objArr2[1]).doubleValue()).appendArg("longitude", ((Double) objArr2[2]).doubleValue()).build(), null).setOnDataCompleteListener(new K4.a(19, this)).start();
    }

    public void onPreExecute(EventContext eventContext, Object... objArr) {
        if (objArr.length != 0 || !eventContext.isSelectionMode()) {
            super.onPreExecute(eventContext, objArr);
            return;
        }
        int selectedItemCount = eventContext.getSelectedItemCount();
        Log.d(this.TAG, "onPreExecute", Integer.valueOf(selectedItemCount));
        if (selectedItemCount > 5000) {
            Utils.showToast(getContext(), (int) R.string.cant_select_more_than_5000_items);
        } else {
            loadAndExecute(eventContext, new N3.c(7, this, eventContext));
        }
    }

    public void onProcess(int i2, int i7) {
        updateProgressMessage();
    }

    public void showSuccessToast(int i2, int i7, int i8) {
        if (i2 == 0) {
            showCannotChangeToast();
        } else if (i2 == i7) {
            showToast(getContext().getResources().getQuantityString(R.plurals.location_info_changed_images, i2, new Object[]{Integer.valueOf(i2)}));
        } else if (i2 == i8) {
            showToast(getContext().getResources().getQuantityString(R.plurals.location_info_changed_videos, i2, new Object[]{Integer.valueOf(i2)}));
        } else {
            showToast(getContext().getResources().getQuantityString(R.plurals.location_info_changed_items, i2, new Object[]{Integer.valueOf(i2)}));
        }
    }

    private boolean changeLocation(MediaItem mediaItem, double d, double d2) {
        if (!isApplicable(mediaItem) || !ExifUtils.changeLocation(mediaItem.getPath(), d, d2)) {
            return isHeifMp4Editable(mediaItem) && changeIsoLocation(mediaItem, d, d2);
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$changeLocation$4(Integer num, Integer num2) {
    }
}
