package com.samsung.android.gallery.app.activity.external;

import D3.a;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;
import com.samsung.android.gallery.app.activity.GalleryActivity;
import com.samsung.android.gallery.app.activity.GalleryActivityHandler;
import com.samsung.android.gallery.app.activity.abstraction.IGalleryActivityView;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.Subscriber;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CropImageActivity extends GalleryActivity {
    private boolean checkIntentSpoofing() {
        Intent intent = getIntent();
        if (intent == null || "com.sec.android.gallery3d".equals(getRefererPackageName())) {
            return true;
        }
        if (getDataAuthority(intent).contains("com.sec.android.gallery3d") || getOutputPath(intent).contains("com.sec.android.gallery3d") || getClipDataAuthority(intent).contains("com.sec.android.gallery3d")) {
            return false;
        }
        return true;
    }

    private String getClipDataAuthority(Intent intent) {
        ClipData.Item itemAt;
        Uri uri;
        String authority;
        ClipData clipData = intent.getClipData();
        if (clipData == null || (itemAt = clipData.getItemAt(0)) == null || (uri = itemAt.getUri()) == null || (authority = uri.getAuthority()) == null) {
            return "";
        }
        return authority;
    }

    private String getDataAuthority(Intent intent) {
        String authority;
        Uri data = intent.getData();
        if (data == null || (authority = data.getAuthority()) == null) {
            return "";
        }
        return authority;
    }

    private String getOutputPath(Intent intent) {
        Object obj;
        Bundle extras = intent.getExtras();
        if (extras == null || (obj = extras.get("output")) == null) {
            return "";
        }
        return obj.toString();
    }

    private boolean isInternalCrop() {
        LaunchIntent launchIntent = (LaunchIntent) getBlackboard().read("data://launch_intent");
        if (launchIntent == null || (!launchIntent.getIntent().getBooleanExtra("FromStoryCover", false) && !launchIntent.getIntent().getBooleanExtra("FromSharedAlbumCover", false) && !launchIntent.getIntent().getBooleanExtra("FromAlbumCover", false))) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ String lambda$onStart$0() {
        return "" + getReferrer();
    }

    public Subscriber createActivityHandler(Blackboard blackboard) {
        return new CropImageActivityHandler(blackboard, this);
    }

    public Subscriber createViewNavigator(Blackboard blackboard) {
        return new ViewNavigatorExternal(blackboard, this);
    }

    public String getRefererPackageName() {
        return AppResources.getCallingPackageCompat(this, new a(this, 1));
    }

    public void handleNightModeChange(Bundle bundle) {
        super.handleNightModeChange(bundle);
        if (bundle.getBoolean("is_internal_crop")) {
            Log.e(this.TAG, "Finish: night mode changed");
            finish();
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("last_shape_button_status", isShapeButtonEnabled());
        bundle.putBoolean("shape_button_status_changed", isShapeButtonStatusChanged());
        bundle.putBoolean("is_internal_crop", isInternalCrop());
    }

    public void onStart() {
        if (!checkIntentSpoofing()) {
            Log.w((CharSequence) this.TAG, "onStart failed. authority not allowed", Optional.ofNullable(getCallingPackage()).orElseGet(new a(this, 0)));
            Toast.makeText(this, "Authority is not allowed", 0).show();
            finish();
        }
        super.onStart();
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class CropImageActivityHandler extends GalleryActivityHandler {
        public CropImageActivityHandler(Blackboard blackboard, IGalleryActivityView iGalleryActivityView) {
            super(blackboard, iGalleryActivityView);
        }

        public void showSystemWarning() {
        }

        public void resumeServiceIfPresent(Context context) {
        }

        public void onActivityPauseBg(Object obj, Bundle bundle) {
        }
    }
}
