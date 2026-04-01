package com.samsung.android.gallery.app.ui.viewer2.container.delegate;

import B7.d;
import Fa.K;
import a6.C0419b;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.container.abstraction.IVuContainerView;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObjectComposite;
import com.samsung.android.gallery.app.ui.viewer2.delegate.AbsVuDelegate;
import com.samsung.android.gallery.app.ui.viewer2.model.ContainerModel;
import com.samsung.android.gallery.module.clipboard.Clipboard;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.abstraction.SelectableChecker;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import i.C0212a;
import java.util.Locale;
import k7.q;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SelectModeDelegate extends AbsVuDelegate<IVuContainerView> {
    /* access modifiers changed from: private */
    public CheckBox mCheckBox;
    private final View.AccessibilityDelegate mCheckBoxAccessibilityDelegate = new View.AccessibilityDelegate() {
        public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            super.onPopulateAccessibilityEvent(view, accessibilityEvent);
            if (accessibilityEvent.getEventType() == 32768 && ViewUtils.isVisible(SelectModeDelegate.this.mCheckBox)) {
                SelectModeDelegate.this.mCheckBox.setContentDescription(SelectModeDelegate.this.mCheckBox.getResources().getString(SelectModeDelegate.this.getCheckedStateResId()));
            }
        }
    };
    private TextView mCountView;
    private View mLayout;
    private View mMainLayout;
    private SelectableChecker<MediaItem> mSelectableChecker;

    public SelectModeDelegate(IVuContainerView iVuContainerView) {
        super(iVuContainerView);
        ((ContainerModel) this.mModel).setSelectMode(true);
    }

    private boolean checkShowToast() {
        if (this.mSelectableChecker == null || !this.mCheckBox.isChecked()) {
            return false;
        }
        if (Clipboard.getInstance(this.mBlackboard).getTotalCount() != this.mSelectableChecker.getMaxCount()) {
            return !this.mSelectableChecker.isSupported(getCurrentItem());
        }
        this.mSelectableChecker.showExceedMaxCountToast(this.mCheckBox.getContext());
        return true;
    }

    private void enterSelfSelectCountMode() {
        View view;
        GalleryToolbar toolbar = ((IVuContainerView) this.mView).getToolbar();
        if (toolbar != null && (view = this.mLayout) != null) {
            toolbar.enterSelfSelectCountMode(view, view.getResources().getDimensionPixelSize(R.dimen.select_toolbar_height));
        }
    }

    /* access modifiers changed from: private */
    public int getCheckedStateResId() {
        CheckBox checkBox = this.mCheckBox;
        if (checkBox == null) {
            return -1;
        }
        if (checkBox.isChecked()) {
            return R.string.speak_checked;
        }
        return R.string.speak_not_checked;
    }

    private MediaItem getCurrentItem() {
        return ((ContainerModel) this.mModel).getCurrentMediaItem();
    }

    private int getMaxSelectCount() {
        SelectableChecker<MediaItem> selectableChecker = this.mSelectableChecker;
        if (selectableChecker != null) {
            return selectableChecker.getMaxCount();
        }
        return -1;
    }

    private View inflateLayout(View view) {
        return LayoutInflater.from(view.getContext()).inflate(R.layout.viewer_select_info_layout, (ViewGroup) null);
    }

    private void initSelectView() {
        View view = this.mLayout;
        if (view != null) {
            this.mCountView = (TextView) view.findViewById(R.id.select_count);
            CheckBox checkBox = (CheckBox) this.mLayout.findViewById(R.id.check_box);
            this.mCheckBox = checkBox;
            checkBox.setOnClickListener(new C0419b(19, this));
            this.mCheckBox.setAccessibilityDelegate(this.mCheckBoxAccessibilityDelegate);
            this.mCheckBox.setOnCheckedChangeListener(new K(3, this));
        }
    }

    private boolean isFromSearchTopResult() {
        return ArgumentsUtil.getArgValue(((ContainerModel) this.mModel).getLocationKey(), "from_top_result", false);
    }

    public static boolean isSelectMode(String str, Blackboard blackboard) {
        if (!ArgumentsUtil.getArgValue(str, "from_expand", false) || PickerUtil.isSinglePickLaunchMode(blackboard)) {
            return false;
        }
        return true;
    }

    private boolean isSelectable(MediaItem mediaItem) {
        SelectableChecker<MediaItem> selectableChecker = this.mSelectableChecker;
        if (selectableChecker == null || selectableChecker.isSelectable(mediaItem)) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initSelectView$2(CompoundButton compoundButton, boolean z) {
        if (ViewUtils.isVisible(this.mCheckBox)) {
            String string = this.mCheckBox.getResources().getString(getCheckedStateResId());
            CheckBox checkBox = this.mCheckBox;
            StringBuilder t = C0212a.t(string, ArcCommonLog.TAG_COMMA);
            t.append(this.mCheckBox.getResources().getString(R.string.speak_checkbox));
            checkBox.setContentDescription(t.toString());
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onBindView$1() {
        enterSelfSelectCountMode();
        updateSelectedCount();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setActionInvokeListener$0(Object[] objArr) {
        setVisibility(objArr[0].intValue());
    }

    /* access modifiers changed from: private */
    public void onClicked(View view) {
        MediaItem currentItem = getCurrentItem();
        if (currentItem != null) {
            if (!isSelectable(currentItem)) {
                CheckBox checkBox = this.mCheckBox;
                checkBox.setChecked(!checkBox.isChecked());
                this.mSelectableChecker.isSupported(currentItem);
            } else if (checkShowToast()) {
                this.mCheckBox.setChecked(false);
            } else {
                Blackboard blackboard = this.mBlackboard;
                boolean isChecked = this.mCheckBox.isChecked();
                int position = ((ContainerModel) this.mModel).getPosition();
                if (!isFromSearchTopResult()) {
                    currentItem = null;
                }
                blackboard.postEvent(EventMessage.obtain(1027, isChecked ? 1 : 0, position, currentItem));
                ThreadUtil.postOnUiThread(new q(this, 1));
                AnalyticsLogger.getInstance().postLog(((IVuContainerView) this.mView).getScreenId(), AnalyticsEventId.EVENT_VIEWER_CHECKBOX_SELECT_ITEM.toString());
            }
        }
    }

    /* access modifiers changed from: private */
    public void updateSelectedCount() {
        if (this.mCountView != null) {
            int totalCount = Clipboard.getInstance(this.mBlackboard).getTotalCount();
            if (getMaxSelectCount() <= 0) {
                this.mCountView.setText(String.format(Locale.getDefault(), "%d", new Object[]{Integer.valueOf(totalCount)}));
            } else {
                this.mCountView.setText(String.format(Locale.getDefault(), "%d/%d", new Object[]{Integer.valueOf(totalCount), Integer.valueOf(getMaxSelectCount())}));
            }
        }
    }

    private void updateState() {
        if (this.mCheckBox != null && getCurrentItem() != null) {
            this.mCheckBox.setChecked(Clipboard.getInstance(this.mBlackboard).contains(Long.valueOf(getCurrentItem().getFileId())));
        }
    }

    public void onBindView(View view) {
        int i2;
        this.mMainLayout = view.findViewById(R.id.main_layout);
        if (this.mLayout == null) {
            this.mLayout = inflateLayout(view);
        }
        initSelectView();
        this.mSelectableChecker = (SelectableChecker) this.mBlackboard.pop("data://user/pick/ItemChecker");
        if (((ContainerModel) this.mModel).isOsdVisible()) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        setVisibility(i2);
        updateState();
        ViewUtils.post(this.mMainLayout, new q(this, 0));
    }

    public void onConfigurationChanged(Configuration configuration, boolean z, boolean z3, boolean z7, boolean z9) {
        View view;
        super.onConfigurationChanged(configuration, z, z3, z7, z9);
        if (z7) {
            GalleryToolbar toolbar = ((IVuContainerView) this.mView).getToolbar();
            if (!(toolbar == null || (view = this.mLayout) == null || this.mMainLayout == null)) {
                toolbar.exitSelfSelectCountMode(view);
                ViewUtils.replaceView(this.mLayout, inflateLayout(this.mMainLayout));
                initSelectView();
                View view2 = this.mLayout;
                toolbar.enterSelfSelectCountMode(view2, view2.getResources().getDimensionPixelSize(R.dimen.select_toolbar_height));
            }
            updateSelectedCount();
        }
    }

    public void onDestroy() {
        CheckBox checkBox = this.mCheckBox;
        if (checkBox != null) {
            checkBox.setOnClickListener((View.OnClickListener) null);
            this.mCheckBox.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) null);
            this.mCheckBox = null;
        }
        View view = this.mLayout;
        if (view != null) {
            view.setAccessibilityDelegate((View.AccessibilityDelegate) null);
            GalleryToolbar toolbar = ((IVuContainerView) this.mView).getToolbar();
            if (toolbar != null) {
                toolbar.exitSelfSelectCountMode(this.mLayout);
            }
            ViewUtils.removeSelf(this.mLayout);
            this.mLayout = null;
        }
        this.mSelectableChecker = null;
    }

    public void onPageSelected(int i2, ViewerObjectComposite viewerObjectComposite) {
        updateState();
    }

    public void setActionInvokeListener(ActionInvoker actionInvoker) {
        actionInvoker.add(ViewerAction.UPDATE_CONTAINER_DECOR_VISIBILITY, new d(21, this));
    }

    public void setVisibility(int i2) {
        CheckBox checkBox = this.mCheckBox;
        if (checkBox != null) {
            checkBox.setVisibility(i2);
        }
    }
}
