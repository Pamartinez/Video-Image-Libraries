package com.samsung.android.gallery.app.ui.list.picker.search;

import android.graphics.drawable.Drawable;
import androidx.appcompat.widget.Toolbar;
import androidx.window.embedding.c;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.list.picker.PickerMenuFactory;
import com.samsung.android.gallery.app.ui.list.search.pictures.SearchPicturesPresenter;
import com.samsung.android.gallery.app.ui.list.search.pictures.abstraction.ISearchPicturesView;
import com.samsung.android.gallery.app.ui.list.search.toolbar.SearchToolbarDelegate;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.app.ui.menu.MenuHandler;
import com.samsung.android.gallery.app.ui.menu.picker.PickerMenuHandler;
import com.samsung.android.gallery.module.abstraction.LaunchModeType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.search.root.SearchLocationKeyBuilder;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.ThreadUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchPicturesPickerPresenter<V extends ISearchPicturesView> extends SearchPicturesPresenter<V> {
    protected LaunchModeType mLaunchModeType;
    String[] mSearchParams;

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v12, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: java.lang.String} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SearchPicturesPickerPresenter(com.samsung.android.gallery.support.blackboard.Blackboard r2, V r3) {
        /*
            r1 = this;
            r1.<init>(r2, r3)
            com.samsung.android.gallery.support.blackboard.Blackboard r2 = r1.mBlackboard
            java.lang.String r3 = "data://launch_mode_type"
            java.lang.Object r2 = r2.read(r3)
            com.samsung.android.gallery.module.abstraction.LaunchModeType r2 = (com.samsung.android.gallery.module.abstraction.LaunchModeType) r2
            r1.mLaunchModeType = r2
            com.samsung.android.gallery.module.abstraction.LaunchModeType r3 = com.samsung.android.gallery.module.abstraction.LaunchModeType.ACTION_MULTIPLE_PICK
            if (r2 != r3) goto L_0x001c
            a6.g r2 = new a6.g
            r0 = 7
            r2.<init>(r0, r1)
            r1.setOnSelectionListener(r2)
        L_0x001c:
            com.samsung.android.gallery.support.blackboard.Blackboard r2 = r1.mBlackboard
            com.samsung.android.gallery.module.abstraction.LaunchModeType r2 = com.samsung.android.gallery.module.utils.PickerUtil.getPickerLaunchMode(r2)
            if (r3 != r2) goto L_0x0056
            com.samsung.android.gallery.support.blackboard.Blackboard r2 = r1.mBlackboard
            java.lang.String r3 = "data://launch_intent"
            r0 = 0
            java.lang.Object r2 = r2.read(r3, r0)
            com.samsung.android.gallery.module.abstraction.LaunchIntent r2 = (com.samsung.android.gallery.module.abstraction.LaunchIntent) r2
            if (r2 != 0) goto L_0x0032
            goto L_0x003b
        L_0x0032:
            java.lang.String r3 = "SearchParams"
            java.lang.Object r2 = r2.popExtra(r3, r0)
            r0 = r2
            java.lang.String r0 = (java.lang.String) r0
        L_0x003b:
            boolean r2 = android.text.TextUtils.isEmpty(r0)
            if (r2 != 0) goto L_0x0056
            java.lang.String r2 = "="
            r3 = 2
            java.lang.String[] r2 = r0.split(r2, r3)
            int r3 = r2.length
            r0 = 1
            if (r3 <= r0) goto L_0x0056
            r3 = r2[r0]
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L_0x0056
            r1.mSearchParams = r2
        L_0x0056:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.list.picker.search.SearchPicturesPickerPresenter.<init>(com.samsung.android.gallery.support.blackboard.Blackboard, com.samsung.android.gallery.app.ui.list.search.pictures.abstraction.ISearchPicturesView):void");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createSearchToolbarDelegate$0(String str) {
        this.mBlackboard.publish("command://MoveURL", str);
    }

    /* access modifiers changed from: private */
    public boolean onSelectionCompleted(EventContext eventContext, MediaItem[] mediaItemArr) {
        if (mediaItemArr == null || mediaItemArr.length <= 0) {
            return false;
        }
        eventContext.getBlackboard().post("command://MultiplePickerItemsSelection", mediaItemArr);
        return true;
    }

    public boolean checkOptionMenuEnabled() {
        return false;
    }

    public MenuDataBinding createMenuDataBinding() {
        return PickerMenuFactory.create(this.mBlackboard, getLocationKey());
    }

    public MenuHandler createMenuHandler() {
        LaunchModeType launchModeType = this.mLaunchModeType;
        if (launchModeType == LaunchModeType.ACTION_PICK || launchModeType == LaunchModeType.ACTION_COVER_ITEM_PICK) {
            return new PickerMenuHandler();
        }
        return null;
    }

    public SearchToolbarDelegate createSearchToolbarDelegate() {
        String str;
        String fromShotModeType;
        SearchToolbarDelegate createSearchToolbarDelegate = super.createSearchToolbarDelegate();
        String[] strArr = this.mSearchParams;
        if (strArr == null || !"Keyword".equals(strArr[0])) {
            str = null;
        } else {
            str = this.mSearchParams[1];
        }
        createSearchToolbarDelegate.query(str, true);
        String[] strArr2 = this.mSearchParams;
        if (!(strArr2 == null || !"ShotModeType".equals(strArr2[0]) || (fromShotModeType = SearchLocationKeyBuilder.fromShotModeType(this.mSearchParams[1])) == null)) {
            ThreadUtil.postOnUiThread(new c(3, this, fromShotModeType));
        }
        return createSearchToolbarDelegate;
    }

    public int getMaxCount() {
        return PickerUtil.getMaxPickCount(this.mBlackboard);
    }

    public void onListItemClickInternal(int i2, MediaItem mediaItem) {
        LaunchModeType launchModeType = this.mLaunchModeType;
        if (launchModeType == LaunchModeType.ACTION_PICK || launchModeType == LaunchModeType.ACTION_COVER_ITEM_PICK) {
            postAnalyticsLog(AnalyticsEventId.EVENT_SINGLE_PICK_SELECTION_ITEM);
            this.mBlackboard.post("command://SinglePickerItemSelection", mediaItem);
            return;
        }
        super.onListItemClickInternal(i2, mediaItem);
    }

    public void updateToolbar(Toolbar toolbar) {
        if (toolbar != null) {
            toolbar.setTitle((CharSequence) PickerUtil.getUsageTitle(getBlackboard()));
            if (((ISearchPicturesView) this.mView).supportTabLayout()) {
                toolbar.setNavigationIcon((Drawable) null);
            } else {
                setNavigationUpButton(getToolbar());
            }
        }
    }

    public void initMenu() {
    }
}
