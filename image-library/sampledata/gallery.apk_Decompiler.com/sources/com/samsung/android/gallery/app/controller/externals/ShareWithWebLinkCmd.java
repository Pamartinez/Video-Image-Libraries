package com.samsung.android.gallery.app.controller.externals;

import A.a;
import android.app.ActivityOptions;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.RectF;
import android.os.Bundle;
import android.text.TextUtils;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.DetailsData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.share.ShareComponent;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.gallery.widget.utils.SystemUi;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ShareWithWebLinkCmd extends BaseCommand {
    private ShareComponent mComponent;
    private MediaItem mMediaItem;

    private String getUrl() {
        DetailsData of2 = DetailsData.of(this.mMediaItem);
        String str = of2.capturedUrl;
        if (TextUtils.isEmpty(str)) {
            return of2.capturedPath;
        }
        return str;
    }

    /* access modifiers changed from: private */
    public void onDataCompleted(EventContext eventContext, ArrayList<Object> arrayList) {
        if (arrayList != null && !arrayList.isEmpty()) {
            String str = (String) arrayList.get(0);
            str.getClass();
            if (str.equals("web_link")) {
                showShareWebLinkDialog(getContext());
            } else if (str.equals("image")) {
                new ShareViaCmd().execute(eventContext, new MediaItem[]{this.mMediaItem}, this.mComponent);
            }
        }
    }

    private void showShareWebLinkDialog(Context context) {
        Bundle bundle;
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.TEXT", getUrl());
        ShareComponent shareComponent = this.mComponent;
        if (shareComponent == null || !shareComponent.hasComponents()) {
            Intent createChooser = Intent.createChooser(intent, context.getString(R.string.share_short));
            if (SystemUi.supportPopoverUi(getContext(), true)) {
                Point point = (Point) getBlackboard().pop("data://user/shareViaAnchorPos");
                RectF activityBounds = getActivityBounds();
                ActivityOptions popoverActivityOptions = SeApiCompat.getPopoverActivityOptions(activityBounds, point);
                if (popoverActivityOptions != null) {
                    bundle = popoverActivityOptions.toBundle();
                } else {
                    bundle = null;
                }
                context.startActivity(createChooser, bundle);
                String str = this.TAG;
                Log.d(str, "popover anchor pos=" + point + ", activityBounds=" + activityBounds);
                return;
            }
            context.startActivity(createChooser);
            return;
        }
        intent.addFlags(185073664);
        intent.setComponent(new ComponentName(this.mComponent.getPackageName(), this.mComponent.getClassName()));
        if (this.mComponent.isFromBixby()) {
            intent.putExtra("action_send_addition", "com.sec.android.gallery3d");
        }
        try {
            context.startActivity(intent);
        } catch (Exception e) {
            a.s(e, new StringBuilder("share web link failed e="), this.TAG);
        }
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        this.mMediaItem = objArr[0];
        this.mComponent = objArr[1];
        DataCollectionDelegate.getInitInstance(eventContext).setRequestData(new UriBuilder("data://user/dialog/ShareWithWebLink").appendArg("screenId", getScreenId()).build()).setOnDataCompleteListener(new K4.a(13, this)).start();
    }
}
