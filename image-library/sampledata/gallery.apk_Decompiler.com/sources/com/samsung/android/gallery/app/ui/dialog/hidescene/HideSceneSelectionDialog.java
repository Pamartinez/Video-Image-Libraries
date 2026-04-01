package com.samsung.android.gallery.app.ui.dialog.hidescene;

import F9.e;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.BundleWrapper;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.dialog.BaseDialog;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import f0.C0185a;
import java.util.Objects;
import java.util.Optional;
import v7.w;
import w4.C0531a;
import w4.C0532b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class HideSceneSelectionDialog extends BaseDialog {
    private RadioButton mHideAllButton;
    private RadioButton mHideThisButton;
    private int mTotalCount;
    private int mType;

    private void bindView(View view, MediaItem[] mediaItemArr) {
        boolean z = false;
        int i2 = new int[]{getResources().getDimensionPixelOffset(R.dimen.story_hide_scene_selection_dialog_thumbnail_padding_x2), getResources().getDimensionPixelOffset(R.dimen.story_hide_scene_selection_dialog_thumbnail_padding_x3), getResources().getDimensionPixelOffset(R.dimen.story_hide_scene_selection_dialog_thumbnail_padding_x4)}[Math.max(mediaItemArr.length - 2, 0)];
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.scene_listview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
        recyclerView.setAdapter(new HideSceneSelectionDialogAdapter(mediaItemArr, this.mTotalCount));
        recyclerView.post(new e((Object) this, (Object) recyclerView, (Object) mediaItemArr, i2, 8));
        if (this.mTotalCount > 0) {
            z = true;
        }
        ViewUtils.setVisibleOrGone(recyclerView, z);
        this.mHideThisButton = (RadioButton) view.findViewById(R.id.radio_hide_this);
        this.mHideAllButton = (RadioButton) view.findViewById(R.id.radio_hide_all);
        this.mHideThisButton.setOnCheckedChangeListener(new C0532b(this, 0));
        this.mHideAllButton.setOnCheckedChangeListener(new C0532b(this, 1));
        if (this.mType == 1) {
            this.mHideThisButton.setChecked(true);
        } else {
            this.mHideAllButton.setChecked(true);
        }
    }

    private Object[] getData(boolean z) {
        if (z) {
            return new Object[]{Boolean.TRUE, Integer.valueOf(getOption())};
        }
        return new Object[]{Boolean.FALSE};
    }

    /* access modifiers changed from: private */
    public int getItemGap(View view, RecyclerView recyclerView, int i2, int i7) {
        if (i2 == 1) {
            return recyclerView.getWidth() - (view.getLayoutParams().width * i2);
        }
        int width = recyclerView.getWidth() - ((view.getLayoutParams().width + i7) * i2);
        if (i2 > 2) {
            return width - ((i2 - 2) * i7);
        }
        return width;
    }

    private int getOption() {
        if (this.mHideThisButton.isChecked()) {
            return 1;
        }
        if (this.mHideAllButton.isChecked()) {
            return 0;
        }
        return this.mType;
    }

    private void init() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mTotalCount = BundleWrapper.getInt(arguments, "count");
            this.mType = BundleWrapper.getInt(arguments, "option1");
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindView$0(RecyclerView recyclerView, final MediaItem[] mediaItemArr, final int i2) {
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
                super.getItemOffsets(rect, view, recyclerView, state);
                if (recyclerView.getLayoutManager() != null) {
                    int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
                    int j2 = HideSceneSelectionDialog.this.getItemGap(view, recyclerView, mediaItemArr.length, i2);
                    if (childAdapterPosition != 0) {
                        rect.left = i2;
                    }
                    if (childAdapterPosition != mediaItemArr.length - 1) {
                        rect.right = i2;
                    }
                    if (childAdapterPosition == 0 && j2 > 0) {
                        rect.left = (j2 / 2) + rect.left;
                    }
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindView$1(CompoundButton compoundButton, boolean z) {
        trigger(this.mHideAllButton, z);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindView$2(CompoundButton compoundButton, boolean z) {
        trigger(this.mHideThisButton, z);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$onConfigurationChanged$3(RecyclerView recyclerView) {
        Objects.requireNonNull(recyclerView);
        recyclerView.post(new C0185a(recyclerView, 1));
    }

    /* access modifiers changed from: private */
    public void onClickNegative(View view) {
        getBlackboard().post("data://user/dialog/HideSceneSelection", getData(false));
        dismiss();
    }

    /* access modifiers changed from: private */
    public void onClickPositive(View view) {
        String str;
        getBlackboard().post("data://user/dialog/HideSceneSelection", getData(true));
        dismiss();
        AnalyticsEventId analyticsEventId = AnalyticsEventId.EVENT_STORY_HIDE_SCENE_HIDE_OPTION;
        if (getOption() == 1) {
            str = AnalyticsDetail.EVENT_DETAIL_HIDE_SCENE_OPTION_THIS_PERSON.toString();
        } else {
            str = AnalyticsDetail.EVENT_DETAIL_HIDE_SCENE_OPTION_ALL_CONTENTS.toString();
        }
        postAnalyticsLog(analyticsEventId, str);
    }

    private void trigger(RadioButton radioButton, boolean z) {
        if (z) {
            radioButton.setChecked(false);
        }
    }

    public Dialog createDialog(Bundle bundle) {
        MediaItem[] mediaItemArr = (MediaItem[]) getBlackboard().read("data://user/hideSceneHiddenItems");
        if (mediaItemArr == null) {
            Log.e(this.TAG, "skip, items is null");
            return super.createDialog(bundle);
        }
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.hide_scene_selection_dialog, (ViewGroup) null);
        init();
        bindView(inflate, mediaItemArr);
        return new AlertDialog.Builder(getContext()).setTitle((int) R.string.hide_people_and_pets).setView(inflate).setNegativeButton((int) R.string.cancel, (DialogInterface.OnClickListener) null).setPositiveButton((int) R.string.done, (DialogInterface.OnClickListener) null).create();
    }

    public void onCancel(DialogInterface dialogInterface) {
        getBlackboard().post("data://user/dialog/HideSceneSelection", (Object) null);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        Dialog dialog = getDialog();
        if (dialog != null) {
            Optional.ofNullable((RecyclerView) dialog.findViewById(R.id.scene_listview)).ifPresent(new w(1));
        }
    }

    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        getBlackboard().erase("data://user/hideSceneHiddenItems");
    }

    public void onResume() {
        super.onResume();
        if (getDialog() instanceof AlertDialog) {
            ((AlertDialog) getDialog()).getButton(-1).setOnClickListener(new C0531a(this, 0));
            ((AlertDialog) getDialog()).getButton(-2).setOnClickListener(new C0531a(this, 1));
        }
    }
}
