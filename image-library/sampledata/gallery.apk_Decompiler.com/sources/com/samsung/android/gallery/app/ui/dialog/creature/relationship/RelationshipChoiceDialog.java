package com.samsung.android.gallery.app.ui.dialog.creature.relationship;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.dialog.abstraction.MvpDialog;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import com.sec.android.gallery3d.R;
import o5.C0496a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RelationshipChoiceDialog extends MvpDialog<IRelationshipChoiceDialogView, RelationshipChoiceDialogPresenter> implements IRelationshipChoiceDialogView {
    RecyclerView mListView;
    private final View.OnClickListener mOnToolbarClickListener = new C0496a(23, this);
    GalleryToolbar mToolbar;

    private void initListView() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            this.mListView.setAdapter(new RelationshipChoiceAdapter(getApplicationContext(), this));
            this.mListView.setLayoutManager(new LinearLayoutManager(activity.getApplicationContext()));
        }
    }

    private void initToolbar() {
        setNavigationUpButton(this.mToolbar);
        this.mToolbar.setTitle((int) R.string.relationship_choice_dialog_title);
        this.mToolbar.setNavigationOnClickListener(this.mOnToolbarClickListener);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(View view) {
        postAnalyticsLog(AnalyticsEventId.EVENT_UP_KEY);
        dismissDialog();
    }

    public void bindViews(View view) {
        this.mToolbar = (GalleryToolbar) view.findViewById(R.id.toolbar);
        this.mListView = (RecyclerView) view.findViewById(R.id.relationship_list);
    }

    public RelationshipChoiceAdapter getAdapter() {
        return (RelationshipChoiceAdapter) this.mListView.getAdapter();
    }

    public String getInitialType() {
        if (getArguments() != null) {
            return getArguments().getString("relationship_initial_type", "");
        }
        return "";
    }

    public int getLayoutId() {
        return R.layout.relationship_choice_dialog;
    }

    public String getScreenId() {
        return AnalyticsScreenId.SCREEN_VISUAL_SEARCH_VIEW_RELATIONSHIP_CHOICE_LIST.toString();
    }

    public void onCancel(DialogInterface dialogInterface) {
        ((RelationshipChoiceDialogPresenter) this.mPresenter).onCancel();
        super.onCancel(dialogInterface);
    }

    public void onDismiss(DialogInterface dialogInterface) {
        getBlackboard().post("data://user/dialog/RelationshipChoice", (Object) null);
        super.onDismiss(dialogInterface);
    }

    public void onItemClicked(String str) {
        String str2 = this.TAG;
        Log.d(str2, "onItemClicked: " + str);
        ((RelationshipChoiceDialogPresenter) this.mPresenter).onItemClicked(str);
        dismissDialog();
    }

    public void onItemRemoved() {
        ((RelationshipChoiceDialogPresenter) this.mPresenter).onItemRemoved();
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        initToolbar();
        initListView();
    }

    public RelationshipChoiceDialogPresenter createDialogPresenter(IRelationshipChoiceDialogView iRelationshipChoiceDialogView) {
        return new RelationshipChoiceDialogPresenter(iRelationshipChoiceDialogView);
    }
}
