package com.samsung.android.gallery.app.ui.abstraction;

import android.content.Context;
import android.content.res.Resources;
import android.view.MenuItem;
import android.view.View;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.IBaseFragment;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IMvpBaseView extends IBaseFragment {
    void clearScreenLocked();

    void finish();

    FragmentActivity getActivity();

    Context getApplicationContext();

    Blackboard getBlackboard();

    int getBottomTabHeight() {
        return 0;
    }

    FragmentManager getChildFragmentManager();

    View getContentView();

    Context getContext();

    EventContext getEventContext();

    String getLocationKey();

    Resources getResources();

    GalleryToolbar getToolbar();

    View getView();

    String hashTag();

    void initView(View view);

    void invalidateOptionsMenu();

    boolean isActivityResumed();

    boolean isDexMode();

    boolean isDisplayWithDrawer();

    boolean isDrawerMode();

    boolean isDrawerOpen();

    boolean isFirstFragment();

    boolean isFromLockScreen();

    boolean isInMultiWindowMode();

    boolean isLandscape();

    boolean isPortrait();

    boolean isTabletDpi();

    boolean isViewActive();

    boolean isViewHidden();

    boolean isViewReady();

    boolean isViewResumed();

    void keepScreenOn(boolean z);

    boolean onOptionsItemSelected(MenuItem menuItem, boolean z);

    void postAnalyticsLog(AnalyticsEventId analyticsEventId);

    void postAnalyticsLog(AnalyticsEventId analyticsEventId, long j2);

    void postAnalyticsLog(AnalyticsEventId analyticsEventId, String str);

    void postAnalyticsLog(AnalyticsEventId analyticsEventId, String str, String str2);

    void postAnalyticsLog(String str, AnalyticsEventId analyticsEventId);

    void postAnalyticsLog(String str, AnalyticsEventId analyticsEventId, long j2);

    void postAnalyticsLog(String str, AnalyticsEventId analyticsEventId, String str2);

    void predictiveBackCanceled();

    void setOptionsMenu(boolean z);

    void setToolbar(GalleryToolbar galleryToolbar);

    boolean supportActivityToolbar() {
        return false;
    }

    boolean supportTabLayout() {
        return false;
    }

    boolean supportToolbar() {
        return true;
    }

    CharSequence tag();

    void updateToolbar(boolean z);

    boolean useAdvancedMouseDragAndDrop();

    boolean useParentToolbar();
}
