package com.samsung.android.gallery.app.ui.viewer2.details;

import B2.C0000a;
import H3.l;
import L7.r;
import L7.s;
import L7.t;
import L7.u;
import android.app.Activity;
import android.content.res.Configuration;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import androidx.appcompat.widget.Toolbar;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.app.controller.internals.EditLocationCmd;
import com.samsung.android.gallery.app.controller.viewer.EditDateSingleCmd;
import com.samsung.android.gallery.app.controller.viewer.EditDetailsSaveOrDiscardCmd;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.common.state.OverlayViewState;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.FragmentLifeCycle;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.details.EditDetailsModel;
import com.samsung.android.gallery.module.details.EditDetailsUpdater;
import com.samsung.android.gallery.module.details.EditDetailsUpdater2;
import com.samsung.android.gallery.module.map.manager.AddressHelper;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.module.utils.ViewerUtils;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.gallery.widget.editdetails.EditDetailsView;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import com.samsung.android.gallery.widget.utils.SystemUi;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class EditDetailsHandler extends ViewerObject implements FragmentLifeCycle {
    private final ActionInvokeListener mBackKeyListener = new r(this, 1);
    private EditDetailsModel mEditModel;
    private MediaItem mMediaItem;
    private int mSoftInputMode;
    private Toolbar mToolbar;
    private EditDetailsView mView;

    private void changeSoftInputMode(boolean z) {
        int i2;
        Activity activity = this.mModel.getContainerModel().getActivity();
        if (activity != null) {
            if (z) {
                this.mSoftInputMode = activity.getWindow().getAttributes().softInputMode;
                boolean supportEditDetailsFitsToDetails = this.mModel.getStateHelper().supportEditDetailsFitsToDetails();
                Window window = activity.getWindow();
                if (supportEditDetailsFitsToDetails) {
                    i2 = 32;
                } else {
                    i2 = 16;
                }
                window.setSoftInputMode(i2);
                return;
            }
            activity.getWindow().setSoftInputMode(this.mSoftInputMode);
        }
    }

    private void enterEditDetails(MediaItem mediaItem) {
        this.mMediaItem = mediaItem;
        if (mediaItem == null) {
            Log.e(this.TAG, "enter fail: null item");
        } else if (ViewUtils.isAttachedToWindow(this.mView)) {
            Log.e(this.TAG, "enter skip: view is already attached");
        } else {
            Log.d(this.TAG, "enter edit details", this.mMediaItem);
            Log.majorEvent("enter edit details");
            this.mEditModel = new EditDetailsModel(this.mMediaItem);
            initEditDetailsView();
            changeSoftInputMode(true);
            ActionInvoker actionInvoker = this.mActionInvoker;
            ViewerAction viewerAction = ViewerAction.BACK_KEY_PRESSED;
            if (!actionInvoker.hasExclusive(viewerAction, this.mBackKeyListener)) {
                this.mActionInvoker.addExclusive(viewerAction, this.mBackKeyListener, this.TAG);
            }
            postAnalyticsLog(AnalyticsEventId.EVENT_DETAILS_BUTTON_EDIT);
            getBlackboard().postEvent(EventMessage.obtain(3070, new Object[]{this.mView, OverlayViewState.edit_details, Boolean.TRUE}));
        }
    }

    private void exitEditDetails() {
        Log.d(this.TAG, "exit edit details", this.mMediaItem);
        Log.majorEvent("exit edit details");
        this.mMediaItem = null;
        this.mEditModel = null;
        EditDetailsView editDetailsView = this.mView;
        if (editDetailsView != null) {
            editDetailsView.onViewPause();
        }
        changeSoftInputMode(false);
        ActionInvoker actionInvoker = this.mActionInvoker;
        ViewerAction viewerAction = ViewerAction.BACK_KEY_PRESSED;
        if (actionInvoker.hasExclusive(viewerAction, this.mBackKeyListener)) {
            this.mActionInvoker.removeExclusive(viewerAction, this.mBackKeyListener);
        }
        this.mActionInvoker.invoke(ViewerAction.REQUEST_EXIT_OVERLAY_VIEW, new Object[0]);
    }

    private String getDateTimeString() {
        EditDetailsModel editDetailsModel = this.mEditModel;
        if (editDetailsModel == null) {
            return "";
        }
        if (editDetailsModel.hasValidLocalDateTime()) {
            return TimeUtil.getOriginalDateTime(this.mEditModel.getLocalDateTime());
        }
        return TimeUtil.getLocalizedDateTime(this.mEditModel.getDateTime());
    }

    private boolean hasChanged() {
        if (!isEditable()) {
            return false;
        }
        if (this.mEditModel.hasTitleChanged(this.mView.getEditedTitle()) || this.mEditModel.hasDateChanged(this.mMediaItem) || this.mEditModel.hasLocationChanged(this.mMediaItem) || this.mEditModel.hasCapturedUrlDeleted()) {
            return true;
        }
        return false;
    }

    private void initEditDetailsView() {
        t tVar;
        if (this.mView == null) {
            EditDetailsView editDetailsView = new EditDetailsView(this.mModel.getContext());
            this.mView = editDetailsView;
            editDetailsView.setOnMenuClickListener(new u(this));
            setToolbar(this.mView);
        }
        if (!(this.mEditModel == null || this.mMediaItem == null)) {
            this.mView.onViewResume();
            EditDetailsView editDetailsView2 = this.mView;
            String dateTimeString = getDateTimeString();
            if (this.mEditModel.isDateTimeEditable(this.mMediaItem)) {
                tVar = new t(this, 1);
            } else {
                tVar = null;
            }
            editDetailsView2.updateDateView(dateTimeString, tVar);
            this.mView.updateCapturedUrlView(this.mEditModel.getCapturedUrl(), new t(this, 2));
            if (PreferenceFeatures.isEnabled(PreferenceFeatures.LocationAuth) || Features.isEnabled(Features.SUPPORT_PLACE_GDPR)) {
                this.mView.updateLocationView(this.mEditModel.getAddress(), this.mEditModel.getPoi(), this.mEditModel.isLocationEditable(this.mMediaItem));
                this.mView.setLocationButtonListener(new t(this, 3), new t(this, 4));
            } else {
                this.mView.hideLocationContainer();
            }
            boolean isTitleEditable = this.mEditModel.isTitleEditable(this.mMediaItem);
            this.mView.updateTitleView(this.mEditModel.getTitle(), this.mEditModel.getTitleExtension(), this.mMediaItem.isVideo(), isTitleEditable);
            if (isTitleEditable) {
                this.mView.setTitleFocusChangeListener(new C0000a(2, this));
            }
        }
        if (this.mToolbar == null) {
            Toolbar toolbar = (Toolbar) this.mView.findViewById(R.id.overlay_toolbar);
            this.mToolbar = toolbar;
            toolbar.inflateMenu(R.menu.menu_edit_details);
            ViewUtils.resetAccessibilityFocus(((GalleryToolbar) this.mToolbar).getNaviUpButton());
            this.mToolbar.setOnMenuItemClickListener(new u(this));
        }
    }

    private boolean isEditable() {
        if (!OverlayViewState.isShow((OverlayViewState.StateListener) this.mModel) || this.mView == null || this.mEditModel == null || this.mMediaItem == null) {
            return false;
        }
        return true;
    }

    private boolean isNeedToCheckEditedTitle() {
        if (!isEditable() || !this.mEditModel.hasTitleChanged(this.mView.getEditedTitle())) {
            return false;
        }
        String editedTitle = this.mView.getEditedTitle();
        if (TextUtils.isEmpty(editedTitle) || editedTitle.trim().isEmpty()) {
            Log.d(this.TAG, "stop save: title is empty");
            Utils.showToast(this.mView.getContext(), (int) R.string.field_cannot_empty);
            return true;
        } else if (!FileUtils.exists(EditDetailsUpdater.getNewFilePath(editedTitle, this.mMediaItem.getPath()))) {
            return false;
        } else {
            Log.d(this.TAG, "stop save: file already exists");
            Utils.showToast(this.mView.getContext(), (int) R.string.already_exists);
            return true;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$0(Object[] objArr) {
        onRequestSave();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$1(Object[] objArr) {
        updateLayout();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initEditDetailsView$2(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.action_cancel) {
            exitEditDetails();
            return true;
        } else if (menuItem.getItemId() != R.id.action_save) {
            return true;
        } else {
            onRequestSave();
            return true;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setToolbar$3(View view) {
        BlackboardUtils.publishBackKeyEvent(this.mModel.getBlackboard());
    }

    /* access modifiers changed from: private */
    public void onAddLocation(View view) {
        if (isEditable()) {
            double[] location = this.mEditModel.getLocation();
            new EditLocationCmd().execute(this.mModel.getContainerModel().getEventContext(), "", Double.valueOf(location[0]), Double.valueOf(location[1]), new s(this, 1));
            postAnalyticsLog(AnalyticsEventId.EVENT_DETAILS_ADD_LOCATION);
        }
    }

    /* access modifiers changed from: private */
    public void onDeleteCapturedUrl(View view) {
        if (isEditable()) {
            this.mEditModel.deleteCapturedUrl();
            this.mView.updateCapturedUrlView((String) null, (View.OnClickListener) null);
        }
    }

    /* access modifiers changed from: private */
    public void onDeleteLocation(View view) {
        if (isEditable()) {
            this.mEditModel.deleteLocation();
            this.mView.updateLocationView((String) null, (String) null, this.mEditModel.isLocationEditable(this.mMediaItem));
            postAnalyticsLog(AnalyticsEventId.EVENT_DETAILS_REMOVE_LOCATION);
        }
    }

    /* access modifiers changed from: private */
    public void onEditDateClick(View view) {
        if (isEditable() && this.mEditModel.isDateTimeEditable(this.mMediaItem)) {
            new EditDateSingleCmd().execute(this.mModel.getContainerModel().getEventContext(), Long.valueOf(this.mEditModel.getDateTime()), Long.valueOf(this.mEditModel.getLocalDateTime()), Boolean.valueOf(this.mMediaItem.isVideo()), new s(this, 0));
        }
    }

    /* access modifiers changed from: private */
    public void onEditTitleFocusChanged(View view, boolean z) {
        if (z) {
            postAnalyticsLog(AnalyticsEventId.EVENT_DETAILS_EDIT_TITLE);
        }
    }

    /* access modifiers changed from: private */
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.menu_edit_app_bar_done) {
            postAnalyticsLog(AnalyticsEventId.EVENT_DETAILS_BUTTON_SAVE);
            onRequestSave();
            return true;
        } else if (menuItem.getItemId() != R.id.menu_edit_app_bar_cancel) {
            return true;
        } else {
            postAnalyticsLog(AnalyticsEventId.EVENT_DETAILS_BUTTON_CANCEL);
            exitEditDetails();
            return true;
        }
    }

    /* access modifiers changed from: private */
    public void onRequestExit(Object... objArr) {
        if (hasChanged()) {
            new EditDetailsSaveOrDiscardCmd().execute(this.mModel.getContainerModel().getEventContext(), new Object[0]);
        } else {
            exitEditDetails();
        }
    }

    private void onRequestSave() {
        if (!isEditable()) {
            Log.e(this.TAG, "onRequestSave failed: not editable state");
        } else if (isNeedToCheckEditedTitle()) {
            Log.d(this.TAG, "ignore onRequestSave : title is invalid to save");
        } else if (hasChanged()) {
            Log.d(this.TAG, "onRequestSave", Boolean.valueOf(this.mEditModel.hasTitleChanged(this.mView.getEditedTitle())), Boolean.valueOf(this.mEditModel.hasDateChanged(this.mMediaItem)), Boolean.valueOf(this.mEditModel.hasLocationChanged(this.mMediaItem)), Boolean.valueOf(this.mEditModel.hasCapturedUrlDeleted()));
            if (PocFeatures.isEnabled(PocFeatures.C2paFileEdit)) {
                new EditDetailsUpdater2().onSave(getBlackboard(), this.mEditModel, this.mView.getEditedTitle(), this.mMediaItem, new l(27, this));
            } else {
                new EditDetailsUpdater().onSave(getBlackboard(), this.mEditModel, this.mView.getEditedTitle(), this.mMediaItem, new l(27, this));
            }
        } else {
            Utils.showToast(this.mModel.getContext(), (int) R.string.moreinfo_not_edited);
            exitEditDetails();
        }
    }

    /* access modifiers changed from: private */
    public void onSaveDone() {
        String locationKey = this.mModel.getContainerModel().getLocationKey();
        if (LocationKey.isQuickView(locationKey) && !LocationKey.isPictures(locationKey)) {
            getBlackboard().post("command://event/DataDirty", (Object) null);
        }
        exitEditDetails();
    }

    /* access modifiers changed from: private */
    public void onUpdateDate(Object[] objArr) {
        long j2;
        if (isEditable()) {
            Boolean bool = objArr[1];
            bool.booleanValue();
            long timeInMillis = objArr[0].getTimeInMillis();
            if (this.mEditModel.hasValidLocalDateTime()) {
                j2 = this.mEditModel.getLocalDateTime();
            } else {
                j2 = this.mEditModel.getDateTime();
            }
            if (TimeUtil.compareDateTimeAsMinute(timeInMillis, j2)) {
                Log.d(this.TAG, "onUpdateDate", bool);
                return;
            }
            Log.d(this.TAG, "onUpdateDate", bool, Long.valueOf(timeInMillis), TimeUtil.getExifDateTime(timeInMillis), TimeUtil.getExifDateTime(j2));
            if (Features.isEnabled(Features.SUPPORT_LOCAL_TIME)) {
                this.mEditModel.setLocalDateTime(timeInMillis);
            } else {
                this.mEditModel.setDateTime(timeInMillis);
            }
            this.mView.updateDateText(TimeUtil.getLocalizedDateTime(timeInMillis));
        }
    }

    /* access modifiers changed from: private */
    public void onUpdateLocation(Object[] objArr) {
        if (isEditable()) {
            String str = objArr[0];
            Double d = objArr[1];
            double doubleValue = d.doubleValue();
            Double d2 = objArr[2];
            double doubleValue2 = d2.doubleValue();
            StringCompat stringCompat = this.TAG;
            StringBuilder sb2 = new StringBuilder("onUpdateLocation");
            sb2.append(Logger.getEncodedString(doubleValue + ArcCommonLog.TAG_COMMA + doubleValue2 + ArcCommonLog.TAG_COMMA + str));
            Log.d(stringCompat, sb2.toString());
            if (TextUtils.isEmpty(str)) {
                str = AddressHelper.getLatLongString(d, d2);
            }
            String str2 = str;
            this.mEditModel.setLocation(doubleValue, doubleValue2, str2);
            this.mView.updateLocationView(str2, (String) null, this.mEditModel.isLocationEditable(this.mMediaItem));
        }
    }

    private void setToolbar(View view) {
        ((GalleryToolbar) view.findViewById(R.id.overlay_toolbar)).setNavigationOnClickListener(new t(this, 0));
    }

    private void updateLayout() {
        if (isEditable()) {
            this.mView.updateLayout();
            this.mView.updateBottomViewVisibility(ViewerUtils.isEditDetailsBottomViewVisible(SystemUi.isInMultiWindowMode(this.mModel.getActivity()), this.mModel.getSystemUi().isPortrait()));
            updateMenuVisibility();
            int i2 = this.mModel.getContainerModel().getSystemInsetsWithIme().bottom;
            this.mView.updateBottomMenuBottomMargin((this.mView.getResources().getDimensionPixelSize(R.dimen.edit_details_bottom_menu_bottom_margin) + i2) - this.mModel.getContainerModel().getSystemInsets().bottom);
        }
    }

    private void updateMenuVisibility() {
        Toolbar toolbar = this.mToolbar;
        if (toolbar != null) {
            Menu menu = toolbar.getMenu();
            for (int i2 = 0; i2 < menu.size(); i2++) {
                menu.getItem(i2).setVisible(!ViewerUtils.isEditDetailsBottomViewVisible(this.mModel.isInMultiWindowMode(), ResourceCompat.isPortrait(this.mModel.getContext())));
            }
        }
    }

    public void addActionInvokeListener() {
        this.mActionInvoker.add(ViewerAction.ESCAPE_KEY_PRESSED, new r(this, 0));
        this.mActionInvoker.add(ViewerAction.OVERLAY_VIEW_STATE_CHANGED, new r(this, 2));
    }

    public boolean handleBlackboardEvent(EventMessage eventMessage) {
        int i2 = eventMessage.what;
        if (i2 == 3006) {
            onRequestSave();
            return false;
        } else if (i2 == 3007) {
            exitEditDetails();
            return false;
        } else if (i2 != 3030) {
            return false;
        } else {
            enterEditDetails(this.mModel.getMediaItem());
            return false;
        }
    }

    public void onApplyWindowInsets() {
        updateLayout();
    }

    public void onConfigurationChanged(Configuration configuration) {
        updateLayout();
    }

    public void onPause() {
        if (isEditable()) {
            this.mView.onViewPause();
        }
    }

    public void onResume() {
        if (isEditable()) {
            this.mView.onViewResume();
        }
    }

    public void postAnalyticsLog(AnalyticsEventId analyticsEventId) {
        postAnalyticsLog(AnalyticsScreenId.SCREEN_DETAILS_EDIT, analyticsEventId);
    }
}
