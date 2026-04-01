package com.samsung.android.gallery.settings.activity;

import A.a;
import N2.j;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import com.google.android.material.oneui.floatingactioncontainer.FloatingToolbarLayout;
import com.samsung.android.gallery.settings.R$drawable;
import com.samsung.android.gallery.settings.R$id;
import com.samsung.android.gallery.settings.R$layout;
import com.samsung.android.gallery.settings.R$string;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.utils.WindowUtils;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AppPermissionActivity extends BaseActivity {
    /* access modifiers changed from: private */
    public static final boolean IS_TOS;
    /* access modifiers changed from: private */
    public static final int PHOTOS_AND_VIDEOS_ICON_RES;
    /* access modifiers changed from: private */
    public static final int PHOTOS_AND_VIDEOS_TYPE_RES;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum PermissionType {
        PHOTOS_AND_VIDEOS(R$id.photos_and_videos_permission, "android.permission-group.READ_MEDIA_VISUAL", AppPermissionActivity.PHOTOS_AND_VIDEOS_TYPE_RES, R$string.permissions_photos_and_videos_description, AppPermissionActivity.PHOTOS_AND_VIDEOS_ICON_RES, true),
        CONTACTS(R$id.contacts_permission, "android.permission-group.CONTACTS", R$string.permissions_contacts, R$string.permissions_contacts_description, R$drawable.permissions_contact, true),
        LOCATIONS(R$id.locations_permission, "android.permission-group.LOCATION", R$string.searchview_location, R$string.permissions_location_description, R$drawable.permissions_location, true),
        MUSIC_AND_AUDIO(R$id.music_and_audio_permission, "android.permission-group.READ_MEDIA_AURAL", R$string.permissions_music_and_audio, R$string.permissions_music_and_audio_description, R$drawable.permissions_music_and_audio, AppPermissionActivity.IS_TOS),
        NOTIFICATIONS(R$id.notifications_permission, "android.permission-group.NOTIFICATIONS", R$string.permissions_notifications, R$string.permissions_notifications_description, R$drawable.permissions_notifications, AppPermissionActivity.IS_TOS);
        
        final int descriptionRes;
        final int iconRes;
        final String permissionGroup;
        final int typeRes;
        final int viewRes;
        final boolean visible;

        private PermissionType(int i2, String str, int i7, int i8, int i10, boolean z) {
            this.viewRes = i2;
            this.permissionGroup = str;
            this.typeRes = i7;
            this.descriptionRes = i8;
            this.iconRes = i10;
            this.visible = z;
        }
    }

    static {
        int i2;
        int i7;
        boolean atLeast = SdkConfig.atLeast(SdkConfig.GED.T);
        IS_TOS = atLeast;
        if (atLeast) {
            i2 = R$string.permissions_photos_and_videos;
        } else {
            i2 = R$string.permissions_files_and_media;
        }
        PHOTOS_AND_VIDEOS_TYPE_RES = i2;
        if (atLeast) {
            i7 = R$drawable.permissions_photos_and_videos;
        } else {
            i7 = R$drawable.permissions_storage;
        }
        PHOTOS_AND_VIDEOS_ICON_RES = i7;
    }

    private void bindFloatingView() {
        View findViewById = findViewById(R$id.content_app_permission_scroll_view);
        if (findViewById instanceof NestedScrollView) {
            NestedScrollView nestedScrollView = (NestedScrollView) findViewById;
            nestedScrollView.seslSetFadingEdgeEnabled(true);
            View findViewById2 = findViewById(R$id.sesl_floating_toolbar_layout);
            if (findViewById2 instanceof FloatingToolbarLayout) {
                ((FloatingToolbarLayout) findViewById2).setNestedScrollView(nestedScrollView);
            }
        }
    }

    /* access modifiers changed from: private */
    public void bindTypeView(PermissionType permissionType) {
        View findViewById = findViewById(permissionType.viewRes);
        ((TextView) findViewById.findViewById(R$id.type)).setText(getPermissionGroupLabel(permissionType));
        ((TextView) findViewById.findViewById(R$id.description)).setText(permissionType.descriptionRes);
        ((ImageView) findViewById.findViewById(R$id.permission_icon)).setImageDrawable(getDrawable(permissionType.iconRes));
        ViewUtils.setVisibleOrGone(findViewById, permissionType.visible);
    }

    private String getPermissionGroupLabel(PermissionType permissionType) {
        String str = permissionType.permissionGroup;
        try {
            PackageManager packageManager = getPackageManager();
            return packageManager.getPermissionGroupInfo(str, 0).loadLabel(packageManager).toString();
        } catch (PackageManager.NameNotFoundException unused) {
            a.u("Cannot find permission name: ", str, this.TAG);
            return getString(permissionType.typeRes);
        } catch (Exception e) {
            j.v("Unexpected error while loading description: ", e, this.TAG);
            return getString(permissionType.typeRes);
        }
    }

    public void bindView() {
        Arrays.stream(PermissionType.values()).forEach(new a(this, 0));
        ((TextView) findViewById(R$id.special_description)).setText(getString(R$string.appear_on_app) + "\n" + getString(R$string.permissions_special_description));
        bindFloatingView();
    }

    public String getActivityTitle() {
        return getString(R$string.permissions);
    }

    public ViewGroup getContentFrame() {
        return (ViewGroup) findViewById(R$id.content);
    }

    public int getLayoutId() {
        return R$layout.activity_app_permission_layout;
    }

    public /* bridge */ /* synthetic */ void initActionBar() {
        super.initActionBar();
    }

    public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        WindowInsets onApplyWindowInsets = super.onApplyWindowInsets(view, windowInsets);
        ViewMarginUtils.setBottomPadding(view.findViewById(R$id.main_layout), WindowUtils.getSystemInsetsBottom(windowInsets));
        return onApplyWindowInsets;
    }

    public /* bridge */ /* synthetic */ void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    public /* bridge */ /* synthetic */ void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public /* bridge */ /* synthetic */ void onMultiWindowModeChanged(boolean z) {
        super.onMultiWindowModeChanged(z);
    }

    public /* bridge */ /* synthetic */ boolean onOptionsItemSelected(MenuItem menuItem) {
        return super.onOptionsItemSelected(menuItem);
    }

    public /* bridge */ /* synthetic */ void setBackPressedCallbackEnabled(boolean z) {
        super.setBackPressedCallbackEnabled(z);
    }

    public /* bridge */ /* synthetic */ void setSupportActionBar(Toolbar toolbar) {
        super.setSupportActionBar(toolbar);
    }
}
