package com.samsung.android.gallery.app.ui.dialog.creature.merge;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.RenderEffect;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.samsung.android.gallery.app.ui.dialog.creature.merge.IMergeCreatureDialogView;
import com.samsung.android.gallery.app.ui.dialog.creature.merge.MergeCreatureDialogPresenter;
import com.samsung.android.gallery.app.ui.dialog.creature.picker.CreaturePickerDialog;
import com.samsung.android.gallery.app.ui.list.search.category.people.CreatureCategoryLayoutManagerV2;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blur.BlurEffectHolder;
import com.samsung.android.gallery.support.blur.BlurParams;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import o6.t;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MergeCreatureDialog<V extends IMergeCreatureDialogView, P extends MergeCreatureDialogPresenter> extends CreaturePickerDialog<V, P> implements IMergeCreatureDialogView {
    private int mColumnCount;
    ImageView mHeaderImage;
    TextView mHeaderName;
    private View mHeaderNameArea;

    private int getTitleResId() {
        return R.string.merge_face_group;
    }

    private void initHeader() {
        if (TextUtils.isEmpty(((MergeCreatureDialogPresenter) this.mPresenter).getHeaderName())) {
            ViewUtils.setVisibility(this.mHeaderNameArea, 4);
        } else {
            ViewUtils.setVisibility(this.mHeaderNameArea, 0);
            this.mHeaderName.setText(((MergeCreatureDialogPresenter) this.mPresenter).getHeaderName());
        }
        ((MergeCreatureDialogPresenter) this.mPresenter).loadHeaderThumb();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindHeaderImage$0() {
        int width = this.mHeaderImage.getWidth();
        int height = this.mHeaderImage.getHeight();
        if (width > 0 && height > 0) {
            this.mHeaderImage.setRenderEffect(BlurEffectHolder.getRenderEffectForAlbumCover(new BlurParams.Builder(width, height).setPercent(0.5f).setPivotPercent(0.25f).build()));
        }
    }

    private void loadPinchColumns() {
        if (getContext() != null) {
            this.mColumnCount = getContext().getResources().getIntArray(R.array.visual_search_people_item_column_count_expansion)[0];
        }
    }

    public void bindHeaderImage(Bitmap bitmap) {
        this.mHeaderImage.setClipToOutline(true);
        this.mHeaderImage.setImageBitmap(bitmap);
        if (!PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85 || Build.VERSION.SDK_INT < 31) {
            return;
        }
        if (ViewUtils.isVisible(this.mHeaderNameArea)) {
            this.mHeaderImage.post(new t(28, this));
        } else {
            this.mHeaderImage.setRenderEffect((RenderEffect) null);
        }
    }

    public void bindViews(View view) {
        super.bindViews(view);
        this.mHeaderName = (TextView) view.findViewById(R.id.header_name);
        this.mHeaderImage = (ImageView) view.findViewById(R.id.header_image);
        this.mHeaderNameArea = view.findViewById(R.id.header_name_area);
    }

    public MediaItem getHeaderItem() {
        return ((MergeCreatureDialogPresenter) this.mPresenter).getHeaderItem();
    }

    public int getLayoutId() {
        return R.layout.merge_creature_dialog;
    }

    public String getScreenId() {
        return AnalyticsScreenId.SCREEN_VISUAL_SEARCH_VIEW_MERGE_PEOPLE.toString();
    }

    public String getTitle() {
        Context applicationContext = getApplicationContext();
        if (applicationContext != null) {
            return applicationContext.getResources().getString(getTitleResId());
        }
        Log.e(this.TAG, "Could not open the merge people dialog -> context is null");
        dismissDialog();
        return null;
    }

    public void onConfigurationChanged(Configuration configuration) {
        loadPinchColumns();
        super.onConfigurationChanged(configuration);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        loadPinchColumns();
        return onCreateView;
    }

    public void onDestroyDialog() {
        onDestroyView();
    }

    public void onDismiss(DialogInterface dialogInterface) {
        getBlackboard().post("data://user/dialog/MergeCreature", (Object) null);
        super.onDismiss(dialogInterface);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        initHeader();
    }

    public void setAdapter() {
        MergeCreatureAdapter mergeCreatureAdapter = new MergeCreatureAdapter(this);
        this.mListView.setAdapter(mergeCreatureAdapter);
        mergeCreatureAdapter.load();
    }

    public void setLayoutManager() {
        if (getContext() != null) {
            this.mListView.setLayoutManager(new CreatureCategoryLayoutManagerV2(getContext(), this.mColumnCount));
        }
    }

    public MergeCreatureDialogPresenter createDialogPresenter(IMergeCreatureDialogView iMergeCreatureDialogView) {
        return new MergeCreatureDialogPresenter(iMergeCreatureDialogView);
    }
}
