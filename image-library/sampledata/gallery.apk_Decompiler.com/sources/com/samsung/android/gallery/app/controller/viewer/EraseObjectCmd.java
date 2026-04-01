package com.samsung.android.gallery.app.controller.viewer;

import U3.a;
import U5.c;
import android.content.Context;
import android.text.TextUtils;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.internals.ShowSnackBarForViewerCmd;
import com.samsung.android.gallery.module.clip.objectcapture.ObjectCaptureEraseInfo;
import com.samsung.android.gallery.module.clip.objectcapture.ObjectCaptureHelper;
import com.samsung.android.gallery.module.deepsky.DeepSkyHelper;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.gallery.support.utils.Utils;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class EraseObjectCmd extends BaseCommand {
    private ObjectCaptureEraseInfo mEraseInfo;
    private ObjectCaptureHelper mHelper;

    private void erase(ObjectCaptureEraseInfo.EraseType eraseType) {
        DeepSkyHelper.post(new c(11, this, eraseType));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$erase$0(String str, ObjectCaptureEraseInfo.EraseType eraseType) {
        int i2;
        if (TextUtils.isEmpty(str)) {
            Utils.showToast(getContext(), (int) R.string.could_not_erase_selection, 1);
            return;
        }
        ShowSnackBarForViewerCmd showSnackBarForViewerCmd = new ShowSnackBarForViewerCmd();
        EventContext handler = getHandler();
        Context context = getContext();
        if (eraseType == ObjectCaptureEraseInfo.EraseType.VIDEO_FROM_FILE) {
            i2 = R.string.video_saved;
        } else {
            i2 = R.string.image_saved;
        }
        showSnackBarForViewerCmd.execute(handler, str, context.getString(i2), Boolean.FALSE);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$erase$1(ObjectCaptureEraseInfo.EraseType eraseType) {
        this.mEraseInfo.setEraseType(eraseType);
        DeepSkyHelper.postDelayed(new R6.c(this, this.mHelper.eraseObject(this.mEraseInfo), eraseType, 19), 500);
    }

    /* access modifiers changed from: private */
    public void onItemSelected(EventContext eventContext, ArrayList<Object> arrayList) {
        if (arrayList != null) {
            try {
                if (!arrayList.isEmpty()) {
                    int intValue = ((Integer) arrayList.get(0)).intValue();
                    if (intValue == 0) {
                        erase(ObjectCaptureEraseInfo.EraseType.IMAGE_FROM_FILE);
                        postAnalyticsLog(AnalyticsEventId.EVENT_DETAIL_VIEW_CLIPPED_IMAGE_ERASE, AnalyticsDetail.EVENT_DETAIL_ERASE_OBJECT_IMAGE.toString());
                        return;
                    } else if (intValue == 1) {
                        erase(ObjectCaptureEraseInfo.EraseType.VIDEO_FROM_FILE);
                        postAnalyticsLog(AnalyticsEventId.EVENT_DETAIL_VIEW_CLIPPED_IMAGE_ERASE, AnalyticsDetail.EVENT_DETAIL_ERASE_OBJECT_VIDEO.toString());
                        return;
                    } else {
                        return;
                    }
                }
            } catch (Exception e) {
                Log.e((CharSequence) this.TAG, "invalid data is selected, e=", e.getMessage());
                return;
            }
        }
        Log.e(this.TAG, "invalid data is selected");
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        boolean z;
        ObjectCaptureEraseInfo.EraseType eraseType;
        boolean z3 = false;
        ObjectCaptureHelper objectCaptureHelper = objArr[0];
        this.mHelper = objectCaptureHelper;
        ObjectCaptureEraseInfo objectCaptureEraseInfo = objArr[1];
        this.mEraseInfo = objectCaptureEraseInfo;
        if (objectCaptureHelper == null || objectCaptureEraseInfo == null) {
            String str = this.TAG;
            if (objectCaptureHelper == null) {
                z = true;
            } else {
                z = false;
            }
            Boolean valueOf = Boolean.valueOf(z);
            if (this.mEraseInfo == null) {
                z3 = true;
            }
            Log.e((CharSequence) str, "invalid input", valueOf, Boolean.valueOf(z3));
        } else if (objectCaptureEraseInfo.showEraseDialog()) {
            DataCollectionDelegate.getInitInstance(eventContext).setRequestData(new UriBuilder("data://user/dialog/EraseObject").build()).setOnDataCompleteListener(new a(15, this)).start();
        } else {
            if (this.mEraseInfo.isVideoMode()) {
                eraseType = ObjectCaptureEraseInfo.EraseType.VIDEO_FROM_FILE;
            } else {
                eraseType = ObjectCaptureEraseInfo.EraseType.IMAGE_FROM_FILE;
            }
            erase(eraseType);
        }
    }
}
