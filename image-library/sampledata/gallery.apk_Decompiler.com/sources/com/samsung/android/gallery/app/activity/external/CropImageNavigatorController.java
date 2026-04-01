package com.samsung.android.gallery.app.activity.external;

import A4.C0368c;
import A4.C0384t;
import Ad.C0720a;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import com.samsung.android.gallery.app.activity.ViewNavigatorController;
import com.samsung.android.gallery.app.activity.abstraction.IGalleryActivityView;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.UriItemLoader;
import com.samsung.android.gallery.module.viewer.VuLauncher;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.CommandKey;
import com.samsung.android.gallery.support.blackboard.key.DataKey;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CropImageNavigatorController extends ViewNavigatorController {
    public CropImageNavigatorController(Blackboard blackboard, IGalleryActivityView iGalleryActivityView) {
        super(blackboard, iGalleryActivityView);
    }

    private MediaItem createCropMediaItem(Object obj) {
        Uri uri = (Uri) obj;
        if (uri == null) {
            Log.e(this.TAG, "data is null");
            finishActivityOnUiThread(R.string.unsupported_file);
            return null;
        }
        try {
            LaunchIntent launchIntent = getLaunchIntent();
            MediaItem createCropMediaItem = UriItemLoader.createCropMediaItem(getContext(), uri, launchIntent.getType(), true);
            String str = (String) launchIntent.getExtra("crop-init-rect", null);
            if (!TextUtils.isEmpty(str)) {
                try {
                    createCropMediaItem.setCropRectRatioList((ArrayList) Stream.of(str.split(";")).map(new C0384t(28)).collect(Collectors.toCollection(new C0720a(1))));
                    return createCropMediaItem;
                } catch (Exception e) {
                    String str2 = this.TAG;
                    Log.e(str2, "init rect paring failed. e=" + e.getMessage());
                }
            }
            return createCropMediaItem;
        } catch (SecurityException e7) {
            String str3 = this.TAG;
            Log.e((CharSequence) str3, "security error for uri=" + uri + ", referrer=" + this.mActivityView.getActivity().getReferrer(), (Throwable) e7);
            finishActivityOnUiThread(R.string.access_denied);
            return null;
        }
    }

    private void finishActivityOnUiThread(int i2) {
        ThreadUtil.postOnUiThread(new C0368c(this, i2, 4));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$finishActivityOnUiThread$0(int i2) {
        Toast.makeText(getContext(), i2, 0).show();
        this.mBlackboard.post("command://request_suicide", (Object) null);
    }

    public void onNavigatorCreated() {
        this.mBlackboard.post(CommandKey.DATA_REQUEST("location://cropView"), getLaunchIntent().getUriData());
        this.mBlackboard.publishIfEmpty("lifecycle://on_thumbnail_load_done", 0);
    }

    public void onRequestCropImage(Object obj, Bundle bundle) {
        MediaItem createCropMediaItem = createCropMediaItem(obj);
        if (createCropMediaItem != null) {
            this.mBlackboard.publish(DataKey.DATA("location://cropView"), createCropMediaItem);
            new VuLauncher(this.mBlackboard).launch("location://cropView", 0, createCropMediaItem);
        }
    }
}
