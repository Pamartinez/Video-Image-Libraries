package com.samsung.android.gallery.app.ui.container.picker;

import android.graphics.Bitmap;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import com.samsung.android.gallery.app.ui.container.factory.ChildFragmentFactory;
import com.samsung.android.gallery.app.ui.container.menu.BottomMenuDelegate;
import com.samsung.android.gallery.app.ui.container.phone.BottomTabFragment;
import com.samsung.android.gallery.app.ui.container.phone.IBottomTabView;
import com.samsung.android.gallery.app.ui.container.picker.BottomTabPickerPresenter;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.module.search.rubin.SearchWordCollector;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.gallery.widget.search.constants.ForegroundMode;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BottomTabPickerFragment<V extends IBottomTabView, P extends BottomTabPickerPresenter<V>> extends BottomTabFragment<V, P> implements IBottomTabView {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class BottomMenuDelegatePicker extends BottomMenuDelegate {
        public BottomMenuDelegatePicker(Blackboard blackboard) {
            super(blackboard);
        }

        public boolean supportMenu(int i2) {
            if (i2 == R.id.action_search) {
                return PreferenceFeatures.OneUi7x.SUPPORT_BOTTOM_TAB_PICKER_SEARCH;
            }
            if (i2 == R.id.action_search_keyword) {
                return PreferenceFeatures.OneUi8x.COLLECTION_TAB;
            }
            return super.supportMenu(i2);
        }
    }

    public boolean checkTabSelectable() {
        return true;
    }

    public BottomMenuDelegate createBottomMenuDelegate(Blackboard blackboard) {
        return new BottomMenuDelegatePicker(blackboard);
    }

    public String getChildFragmentFactoryType() {
        return ChildFragmentFactory.BOTTOM_PICKER;
    }

    public String getFragmentTag(String str) {
        return "BottomTabPickerFragment";
    }

    public String getScreenId() {
        String str = (String) this.mBlackboard.read("location://variable/currentv1");
        if (str == null) {
            return null;
        }
        if (str.equals("location://albums")) {
            return AnalyticsScreenId.SCREEN_ALBUM_VIEW_PICK.toString();
        }
        if (!str.equals("location://timeline")) {
            return null;
        }
        return AnalyticsScreenId.SCREEN_TIME_VIEW_PICK.toString();
    }

    public String getTimelineFakeLocationKey() {
        return PickerUtil.appendPickerArgs(this.mBlackboard, "location://timeline/fake");
    }

    public void setArgumentOnSwitchFragment(Fragment fragment, String str) {
        if (PreferenceFeatures.OneUi8x.COLLECTION_TAB && LocationKey.isSearchKeywordTab(str)) {
            getBlackboard().publish("data:///CapturedBitmap", BitmapUtils.createBitmap(100, 100, Bitmap.Config.RGB_565, AppResources.getColor(R.color.default_fw_background)));
            str = new UriBuilder(str).appendArg("term", "key_word").appendArg("collect_type", SearchWordCollector.Type.KEYWORD_INPUT.toString()).appendArg("searchMode", ForegroundMode.SEARCH.name()).build();
        }
        Bundle bundle = new Bundle();
        bundle.putString("locationKey", PickerUtil.appendPickerArgs(this.mBlackboard, str));
        fragment.setArguments(bundle);
    }

    public BottomTabPickerPresenter<?> createTabPresenter(IBottomTabView iBottomTabView) {
        return new BottomTabPickerPresenter<>(this.mBlackboard, iBottomTabView);
    }

    public void hideTabLayout() {
    }

    public void loadBadge() {
    }

    public void saveCurrentState(String str) {
    }
}
