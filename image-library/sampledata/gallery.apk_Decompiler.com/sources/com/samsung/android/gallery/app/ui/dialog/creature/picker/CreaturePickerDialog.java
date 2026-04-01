package com.samsung.android.gallery.app.ui.dialog.creature.picker;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.dialog.abstraction.MvpDialog;
import com.samsung.android.gallery.app.ui.dialog.creature.picker.CreaturePickerDialogPresenter;
import com.samsung.android.gallery.app.ui.dialog.creature.picker.ICreaturePickerDialogView;
import com.samsung.android.gallery.module.abstraction.RelationshipKeySet;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import com.sec.android.gallery3d.R;
import o5.C0496a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CreaturePickerDialog<V extends ICreaturePickerDialogView, P extends CreaturePickerDialogPresenter> extends MvpDialog<V, P> implements ICreaturePickerDialogView {
    protected RecyclerView mListView;
    protected GalleryToolbar mToolbar;

    private void initListView() {
        if (getActivity() != null) {
            setLayoutManager();
            setAdapter();
        }
    }

    private void initToolbar() {
        this.mToolbar.setTitle(getTitle());
        setNavigationUpButton(this.mToolbar);
        this.mToolbar.setNavigationOnClickListener(new C0496a(20, this));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initToolbar$0(View view) {
        postAnalyticsLog(AnalyticsEventId.EVENT_UP_KEY);
        dismissDialog();
    }

    public void bindViews(View view) {
        this.mToolbar = (GalleryToolbar) view.findViewById(R.id.toolbar);
        this.mListView = (RecyclerView) view.findViewById(R.id.creature_list);
    }

    public int getLayoutId() {
        return R.layout.people_picker_dialog;
    }

    public String getScreenId() {
        return AnalyticsScreenId.SCREEN_VISUAL_SEARCH_VIEW_PEOPLE_PICKER.toString();
    }

    public String getTitle() {
        String relationshipType = ((CreaturePickerDialogPresenter) this.mPresenter).getRelationshipType();
        Context applicationContext = getApplicationContext();
        if (relationshipType == null || applicationContext == null) {
            String str = this.TAG;
            Log.e(str, "Could not open the people picker dialog -> relationship: " + relationshipType + " context: " + applicationContext);
            dismissDialog();
            return null;
        }
        return applicationContext.getResources().getString(R.string.relationship_suggestion_header, new Object[]{RelationshipKeySet.getRelationshipName(getApplicationContext(), RelationshipKeySet.parseRelationshipType(relationshipType))});
    }

    public void onCancel(DialogInterface dialogInterface) {
        getBlackboard().post("data://user/dialog/CreaturePicker", (Object) null);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        setLayoutManager();
        this.mListView.getRecycledViewPool().clear();
    }

    public void onDestroyView() {
        ((CreaturePickerDialogPresenter) this.mPresenter).destroy();
        super.onDestroyView();
    }

    public void onItemClicked(MediaItem mediaItem) {
        ((CreaturePickerDialogPresenter) this.mPresenter).onItemClicked(mediaItem);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        initToolbar();
        initListView();
    }

    public void setAdapter() {
        CreaturePickerAdapter creaturePickerAdapter = new CreaturePickerAdapter(this);
        this.mListView.setAdapter(creaturePickerAdapter);
        creaturePickerAdapter.load();
    }

    public void setLayoutManager() {
        Context context = getContext();
        if (context != null) {
            int windowWidth = ResourceCompat.getWindowWidth(getContext());
            int dimensionPixelOffset = context.getResources().getDimensionPixelOffset(R.dimen.search_face_tag_registered_name_margin_horizontal) * 2;
            this.mListView.setLayoutManager(new GridLayoutManager(context, (windowWidth - dimensionPixelOffset) / (dimensionPixelOffset + context.getResources().getDimensionPixelOffset(R.dimen.search_face_tag_registered_name_icon_size))));
        }
    }

    public CreaturePickerDialogPresenter createDialogPresenter(ICreaturePickerDialogView iCreaturePickerDialogView) {
        return new CreaturePickerDialogPresenter(iCreaturePickerDialogView);
    }
}
