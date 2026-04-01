package com.samsung.android.gallery.app.ui.viewer2.menu;

import android.view.View;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DetailsMenuItem extends ViewerMenuItem {
    protected static final boolean SUPPORT_FAST_OPTION;
    private final int BLOCK_MSEC_WHEN_MENU_SELECTED = 500;

    static {
        boolean z;
        if (!PreferenceFeatures.OneUi6x.SIMPLE_FAST_OPTIONS || PreferenceFeatures.OneUi6x.SUPPORT_PE_GEN_EDIT || Features.isEnabled(Features.SUPPORT_AWESOME_INTELLIGENT_FEATURES)) {
            z = false;
        } else {
            z = true;
        }
        SUPPORT_FAST_OPTION = z;
    }

    public DetailsMenuItem(EventContext eventContext, ActionInvoker actionInvoker) {
        super(eventContext, actionInvoker, R.string.details);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$setDetailsCommonAttribute$2(MediaItem mediaItem, String str) {
        return !isFlipCover();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$setMenuAttribute$0(MediaItem mediaItem, String str) {
        return !MediaItemUtil.isUsbFile(mediaItem);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$setMenuAttribute$1(MediaItem mediaItem, String str) {
        if (SUPPORT_FAST_OPTION || LocationKey.isSharings(str)) {
            return false;
        }
        return true;
    }

    public boolean onMenuSelectInternal(View view) {
        showDetails();
        return true;
    }

    public void setDetailsCommonAttribute() {
        exclude("location://trash", "location://family/shared/trash", "location://mtp/fileList", "location://dynamicViewList", "location://AllDayTimeLapse", "location://LongExposure", "location://superSlowViewList", "location://highlightViewList", "location://SingleTakenShotviewer/suggestionHighlight", "location://SingleTakenShotviewer/highlight", "location://SingleTakenShotviewer/superslow", "location://colorCorrectView", "location://albums/private/fileList", "location://private/trash").setInputBlockTime(500).validate(ViewerMenuItem.IS_NOT_POSTPROCESSING_FOR_PPPV3).validate(ViewerMenuItem.IS_NOT_SUGGESTION_VIEW_LIST).validate(new b(this, 7));
    }

    public void setMenuAttribute() {
        setDetailsCommonAttribute();
        validate(new a(23)).validate(new a(24)).setSupportPpp(false);
    }

    public void showDetails() {
        ActionInvoker actionInvoker = this.mActionInvoker;
        ViewerAction viewerAction = ViewerAction.REQUEST_DETAILS_SHOW_OR_HIDE;
        Boolean bool = Boolean.TRUE;
        actionInvoker.invoke(viewerAction, bool, bool);
        this.mActionInvoker.invoke(ViewerAction.DETAILS_SHOW_ANALYTIC_LOGGING, this.mEventContext.getCurrentItem(), Boolean.FALSE);
        this.mActionInvoker.invoke(ViewerAction.DISABLE_TEXT_EXTRACTION_VIEW, new Object[0]);
        this.mActionInvoker.invoke(ViewerAction.DISABLE_OBJECT_CAPTURE_VIEW, new Object[0]);
    }
}
