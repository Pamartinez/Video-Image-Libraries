package com.samsung.android.gallery.app.ui.dialog.switchable;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import android.widget.Button;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;
import com.samsung.android.gallery.support.utils.BundleWrapper;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.gallery.widget.dialog.BaseDialog;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import x6.f;
import y4.a;
import y4.b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SwitchableDialog extends BaseDialog {
    private static final Interpolator PATH_INTERPOLATOR = new PathInterpolator(0.22f, 0.025f, 0.0f, 1.0f);
    private int mButtonColor;
    private LayoutType mCurrentType;
    private final SubscriberListener mDismissListener = new a(this, 0);
    private final String[] mEventIds = new String[3];
    private final SubscriberListener mProgressUpdateListener = new a(this, 2);
    private ViewGroup mRootView;
    private String mScreenId;
    private final SubscriberListener mSwitchListener = new a(this, 1);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class PublishInfo {
        public final Blackboard mBlackboard;
        public final Integer mConfirm;
        public final String mEventId;
        public final String mFilePath;
        public final String mPublishKey;
        public final String mScreenId;

        public PublishInfo(Blackboard blackboard, String str, String str2, String str3, Integer num, String str4) {
            this.mBlackboard = blackboard;
            this.mPublishKey = str;
            this.mScreenId = str2;
            this.mEventId = str3;
            this.mConfirm = num;
            this.mFilePath = str4;
        }
    }

    private void beginChangeBoundsTransition() {
        ChangeBounds changeBounds = new ChangeBounds();
        changeBounds.setDuration(400);
        changeBounds.setInterpolator(PATH_INTERPOLATOR);
        TransitionManager.beginDelayedTransition(this.mRootView, changeBounds);
    }

    private PublishInfo createPublishInfo(int i2) {
        return new PublishInfo(getBlackboard(), getPublishKey(), this.mScreenId, getEventId(i2), getConfirm(i2), (String) null);
    }

    private Integer getConfirm(int i2) {
        if (i2 > 0) {
            return Integer.valueOf(i2);
        }
        return null;
    }

    private String getEventId(int i2) {
        if (i2 > -1) {
            return this.mEventIds[i2];
        }
        return null;
    }

    private void initButtons() {
        String[] buttonStrings = this.mCurrentType.getButtonStrings(getResources());
        initCancelButton(buttonStrings[0]);
        initOption1Button(buttonStrings[1]);
        initOption2Button(buttonStrings[2]);
    }

    private void initCancelButton(String str) {
        Button button = (Button) this.mRootView.findViewById(16908315);
        button.setText(str);
        button.setTextColor(this.mButtonColor);
        button.setOnClickListener(new b(this, 1));
    }

    private void initLogInfo(Bundle bundle) {
        this.mScreenId = bundle.getString("screenId", (String) null);
        this.mEventIds[0] = bundle.getString("cancelEventId", (String) null);
        this.mEventIds[1] = bundle.getString("option1EventId", (String) null);
        this.mEventIds[2] = bundle.getString("option2EventId", (String) null);
        this.mPositiveRedColor = bundle.getBoolean("option1ColorRed", false);
    }

    private void initOption1Button(String str) {
        boolean z;
        int i2;
        Button button = (Button) this.mRootView.findViewById(16908313);
        if (str != null) {
            button.setText(str);
            if (getContext() == null || !this.mPositiveRedColor) {
                i2 = this.mButtonColor;
            } else {
                i2 = Utils.getPermanentDeleteButtonTextColor(getContext());
            }
            button.setTextColor(i2);
            button.setOnClickListener(new b(this, 0));
        }
        boolean z3 = false;
        if (str != null) {
            z = true;
        } else {
            z = false;
        }
        ViewUtils.setVisibleOrGone(button, z);
        View findViewById = this.mRootView.findViewById(R.id.sem_divider1);
        if (str != null) {
            z3 = true;
        }
        ViewUtils.setVisibleOrGone(findViewById, z3);
    }

    private void initOption2Button(String str) {
        boolean z;
        Button button = (Button) this.mRootView.findViewById(16908314);
        if (str != null) {
            button.setText(str);
            button.setTextColor(this.mButtonColor);
            button.setOnClickListener(new b(this, 2));
        }
        boolean z3 = false;
        if (str != null) {
            z = true;
        } else {
            z = false;
        }
        ViewUtils.setVisibleOrGone(button, z);
        View findViewById = this.mRootView.findViewById(R.id.sem_divider2);
        if (str != null) {
            z3 = true;
        }
        ViewUtils.setVisibleOrGone(findViewById, z3);
    }

    private void initType(Bundle bundle) {
        LayoutType layoutType = LayoutType.get(BundleWrapper.getInt(bundle, "type", -1));
        this.mCurrentType = layoutType;
        layoutType.initLayout(this.mRootView, bundle);
        setCancelable(this.mCurrentType.isCancelable());
    }

    private void initViews() {
        initViewsFromArguments(getArguments());
        ViewUtils.setVisibleOrGone(this.mCurrentType.getLayout(), true);
    }

    private void initViewsFromArguments(Bundle bundle) {
        if (bundle != null) {
            initType(bundle);
            initLogInfo(bundle);
            initButtons();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initCancelButton$3(View view) {
        onCancelClicked();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(Object obj, Bundle bundle) {
        dismissAllowingStateLoss();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$1(Object obj, Bundle bundle) {
        onSwitchDialog(bundle);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$2(Object obj, Bundle bundle) {
        onProgressUpdated(bundle);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onSwitchDialog$4(LayoutType layoutType) {
        changeDialog(layoutType, this.mCurrentType);
    }

    private void onCancelClicked() {
        if (this.mCurrentType.onCancelClicked(createPublishInfo(0))) {
            dismiss();
        }
    }

    /* access modifiers changed from: private */
    public void onOption1Clicked(View view) {
        this.mCurrentType.onOption1Clicked(createPublishInfo(1));
    }

    /* access modifiers changed from: private */
    public void onOption2Clicked(View view) {
        this.mCurrentType.onOption2Clicked(createPublishInfo(2));
    }

    private void onProgressUpdated(Bundle bundle) {
        if (BundleWrapper.getString(bundle, "target", "").equals("data://user/dialog/Switchable")) {
            if (this.mCurrentType.getTypeInteger() == 1) {
                this.mCurrentType.initLayout(this.mRootView, bundle);
            } else {
                onSwitchDialog(bundle);
            }
        }
    }

    private void onSwitchDialog(Bundle bundle) {
        if (BundleWrapper.getString(bundle, "target", "").equals("data://user/dialog/Switchable")) {
            LayoutType layoutType = this.mCurrentType;
            initViewsFromArguments(bundle);
            if (!this.mCurrentType.equals(layoutType)) {
                TransitionManager.endTransitions(this.mRootView);
                this.mRootView.post(new f(2, this, layoutType));
            }
        }
    }

    private void subscribeListeners() {
        Blackboard blackboard = getBlackboard();
        if (blackboard != null) {
            blackboard.subscribeOnUi("command://DismissDialog", this.mDismissListener);
            blackboard.subscribeOnUi("command://ShowDialog", this.mSwitchListener);
            blackboard.subscribeOnUi("command://UpdateProgress", this.mProgressUpdateListener);
        }
    }

    private void switchDialogLayout(LayoutType layoutType, LayoutType layoutType2) {
        ViewUtils.setVisibleOrGone(layoutType.getLayout(), false);
        ViewUtils.setVisibleOrGone(layoutType2.getLayout(), true);
    }

    private void unsubscribeListeners() {
        Blackboard blackboard = getBlackboard();
        if (blackboard != null) {
            blackboard.unsubscribe("command://DismissDialog", this.mDismissListener);
            blackboard.unsubscribe("command://ShowDialog", this.mSwitchListener);
            blackboard.unsubscribe("command://UpdateProgress", this.mProgressUpdateListener);
        }
    }

    public void changeDialog(LayoutType layoutType, LayoutType layoutType2) {
        switchDialogLayout(layoutType, layoutType2);
        beginChangeBoundsTransition();
    }

    public String getPublishKey() {
        return "data://user/dialog/Switchable";
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        subscribeListeners();
    }

    public void onCancel(DialogInterface dialogInterface) {
        this.mCurrentType.onCancel(createPublishInfo(-1));
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStyle(1, R.style.SwitchableDialogTheme);
        if (getContext() != null) {
            this.mButtonColor = getContext().getColor(R.color.gallery_color_primary_dark);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mRootView = (ViewGroup) LayoutInflater.from(getContext()).inflate(R.layout.switchable_dialog_layout, (ViewGroup) null);
        initViews();
        return this.mRootView;
    }

    public void onDismiss(DialogInterface dialogInterface) {
        LayoutType.clear();
        unsubscribeListeners();
        super.onDismiss(dialogInterface);
    }

    public boolean supportPopover() {
        return true;
    }
}
