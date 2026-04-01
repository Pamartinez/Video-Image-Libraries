package com.samsung.android.gallery.app.ui.dialog.creature.merge;

import B8.e;
import Ba.g;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.samsung.android.gallery.app.ui.dialog.abstraction.EditBaseDialog;
import com.samsung.android.gallery.module.data.CreatureData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemBuilder;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import l6.a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MergeCreatureChoiceDialog extends EditBaseDialog {
    TextView mBodyTextView;
    ViewGroup mCreatureImageContainer;
    private MediaItem mCurrentItem;
    ImageView mImageViewForCurrent;
    ImageView mImageViewForTarget;
    RadioButton mRadioBtnForCurrent;
    RadioButton mRadioBtnForTarget;
    RadioGroup mRadioGroup;
    private String mSelection;
    private boolean mSimpleMergeStyle;
    private MediaItem mTargetItem;

    private String getBodyMessage() {
        Context context = getContext();
        if (context == null) {
            return "";
        }
        if (!CreatureData.hasName(this.mTargetItem) && !CreatureData.hasName(this.mCurrentItem)) {
            return context.getString(R.string.body_merge_people_no_name_confirm_dialog);
        }
        if (!CreatureData.hasName(this.mTargetItem)) {
            return context.getString(R.string.body_merge_people_confirm_dialog, new Object[]{CreatureData.of(this.mCurrentItem).creatureName});
        }
        if (!CreatureData.hasName(this.mCurrentItem)) {
            return context.getString(R.string.body_merge_people_confirm_dialog, new Object[]{CreatureData.of(this.mTargetItem).creatureName});
        }
        return context.getString(R.string.body_merge_people_select_confirm_dialog);
    }

    private String getMergedTargetLocation() {
        MediaItem mediaItem;
        if (this.mCurrentItem.getSubCategory().equals(this.mSelection)) {
            mediaItem = this.mCurrentItem;
        } else {
            mediaItem = this.mTargetItem;
        }
        return ArgumentsUtil.appendArgs(ArgumentsUtil.appendArgs(BlackboardUtils.readLocationKeyCurrent(getBlackboard()), "title", CreatureData.of(mediaItem).creatureName), "sub", this.mSelection);
    }

    private int getTitleResId() {
        return R.string.merge_these_face_groups_q;
    }

    private void initBodyText() {
        this.mBodyTextView.setText(SeApiCompat.naturalizeText(getBodyMessage()));
    }

    private void initHeaderViews() {
        loadThumbnail(this.mCurrentItem, this.mImageViewForCurrent);
        loadThumbnail(this.mTargetItem, this.mImageViewForTarget);
        updatePadding(this.mImageViewForCurrent);
        updatePadding(this.mImageViewForTarget);
    }

    private void initRadioButton(RadioButton radioButton, MediaItem mediaItem, boolean z) {
        radioButton.setText(CreatureData.of(mediaItem).creatureName);
        radioButton.setChecked(z);
        radioButton.setOnClickListener(new g(28, this, mediaItem));
    }

    private void initRadioButtons() {
        if (!CreatureData.hasName(this.mTargetItem)) {
            this.mSelection = this.mCurrentItem.getSubCategory();
            return;
        }
        if (CreatureData.hasName(this.mCurrentItem)) {
            this.mRadioGroup.setVisibility(0);
            initRadioButton(this.mRadioBtnForCurrent, this.mCurrentItem, false);
            initRadioButton(this.mRadioBtnForTarget, this.mTargetItem, true);
        }
        this.mSelection = this.mTargetItem.getSubCategory();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initRadioButton$0(MediaItem mediaItem, View view) {
        AnalyticsEventId analyticsEventId;
        if (this.mSimpleMergeStyle) {
            analyticsEventId = AnalyticsEventId.EVENT_SEARCH_FACE_CLUSTER_MERGE_PEOPLE_SELECT_NAME;
        } else {
            analyticsEventId = AnalyticsEventId.EVENT_SEARCH_FACE_TAG_MERGE_PEOPLE_SELECT_NAME;
        }
        postAnalyticsLog(analyticsEventId);
        this.mSelection = mediaItem.getSubCategory();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$loadThumbnail$2(ImageView imageView, Bitmap bitmap, MediaItem mediaItem) {
        imageView.setImageBitmap(bitmap);
        imageView.setTag(mediaItem);
    }

    private void loadThumbnail(MediaItem mediaItem, ImageView imageView) {
        if (mediaItem != null) {
            ThumbnailLoader.getInstance().loadThumbnail(mediaItem, ThumbKind.MEDIUM_KIND, new e(mediaItem, 4), new a(8, imageView, mediaItem));
        }
    }

    private void updatePadding(View view) {
        Context context = getContext();
        if (context != null) {
            int dimensionPixelOffset = context.getResources().getDimensionPixelOffset(R.dimen.merge_creature_dialog_item_thumb_size);
            int dimensionPixelOffset2 = context.getResources().getDimensionPixelOffset(R.dimen.merge_people_dialog_list_horizontal_margin_2_item);
            if (((dimensionPixelOffset2 * 2) + dimensionPixelOffset) * 2 < ResourceCompat.getWindowWidth(context)) {
                ViewMarginUtils.setHorizontalMargin(view, dimensionPixelOffset2);
            } else {
                ViewMarginUtils.setHorizontalMargin(view, context.getResources().getDimensionPixelOffset(R.dimen.merge_people_dialog_list_horizontal_margin));
            }
        }
    }

    public void bindViews(View view) {
        super.bindViews(view);
        this.mBodyTextView = (TextView) view.findViewById(R.id.dialog_body);
        this.mRadioGroup = (RadioGroup) view.findViewById(R.id.radio_grp_layout);
        this.mRadioBtnForCurrent = (RadioButton) view.findViewById(R.id.radio_btn_current_creature);
        this.mRadioBtnForTarget = (RadioButton) view.findViewById(R.id.radio_btn_candidate_creature);
        this.mImageViewForCurrent = (ImageView) view.findViewById(R.id.current_creature);
        this.mImageViewForTarget = (ImageView) view.findViewById(R.id.target_creature);
        this.mCreatureImageContainer = (ViewGroup) view.findViewById(R.id.creatureImageContainer);
    }

    public int getLayoutId() {
        return R.layout.merge_creature_choice_dialog;
    }

    public String getScreenId() {
        if (this.mSimpleMergeStyle) {
            return AnalyticsScreenId.SCREEN_VISUAL_SEARCH_VIEW_FACE_TAG.toString();
        }
        return AnalyticsScreenId.SCREEN_VISUAL_SEARCH_VIEW_MERGE_PEOPLE.toString();
    }

    public String getTitle() {
        Context context = getContext();
        if (context == null || this.mCurrentItem == null) {
            return "";
        }
        return context.getString(getTitleResId());
    }

    public void initDialog() {
        this.mCurrentItem = MediaItemBuilder.cloneCreatureItem((MediaItem) getBlackboard().pop("data:///MergeCreatureSource"));
        this.mTargetItem = MediaItemBuilder.cloneCreatureItem((MediaItem) getBlackboard().pop("data:///MergeCreatureTarget"));
        this.mSimpleMergeStyle = ((Boolean) getBlackboard().pop("data://user/faceCluster/UseSimpleMergeStyle", Boolean.FALSE)).booleanValue();
        if (this.mCurrentItem == null || this.mTargetItem == null) {
            String str = this.TAG;
            Log.e(str, "null item - current:" + this.mCurrentItem + " target:" + this.mTargetItem);
            dismiss();
            return;
        }
        this.mImageViewForCurrent.setClipToOutline(true);
        this.mImageViewForTarget.setClipToOutline(true);
        initBodyText();
        initRadioButtons();
        if (this.mSimpleMergeStyle) {
            ViewUtils.setVisibility(this.mCreatureImageContainer, 8);
        } else {
            initHeaderViews();
        }
    }

    public void onCancel(DialogInterface dialogInterface) {
        getBlackboard().post("data://user/dialog/MergePeopleChoice", (Object) null);
    }

    public void onClickNegative(View view) {
        AnalyticsEventId analyticsEventId;
        if (this.mSimpleMergeStyle) {
            analyticsEventId = AnalyticsEventId.EVENT_SEARCH_FACE_CLUSTER_MERGE_PEOPLE_SELECT_CANCEL;
        } else if (this.mRadioGroup.getVisibility() == 0) {
            analyticsEventId = AnalyticsEventId.EVENT_SEARCH_FACE_TAG_MERGE_PEOPLE_SELECT_CANCEL;
        } else {
            analyticsEventId = AnalyticsEventId.EVENT_SEARCH_FACE_TAG_MERGE_PEOPLE_CANCEL;
        }
        postAnalyticsLog(analyticsEventId);
        super.onClickNegative(view);
    }

    public void onClickPositive(View view) {
        AnalyticsEventId analyticsEventId;
        MediaItem mediaItem;
        boolean z;
        if (this.mSimpleMergeStyle) {
            analyticsEventId = AnalyticsEventId.EVENT_SEARCH_FACE_CLUSTER_MERGE_PEOPLE_SELECT_OK;
        } else if (this.mRadioGroup.getVisibility() == 0) {
            analyticsEventId = AnalyticsEventId.EVENT_SEARCH_FACE_TAG_MERGE_PEOPLE_SELECT_OK;
        } else {
            analyticsEventId = AnalyticsEventId.EVENT_SEARCH_FACE_TAG_MERGE_PEOPLE_OK;
        }
        postAnalyticsLog(analyticsEventId);
        super.onClickPositive(view);
        if (this.mCurrentItem.getSubCategory().equals(this.mSelection)) {
            mediaItem = this.mTargetItem;
        } else {
            mediaItem = this.mCurrentItem;
        }
        Object[] objArr = {mediaItem, this.mSelection, getMergedTargetLocation()};
        if (CreatureData.hasName(this.mTargetItem) || CreatureData.hasName(this.mCurrentItem)) {
            z = false;
        } else {
            getBlackboard().publish("data:///MergeCreatureTarget", this.mTargetItem);
            z = true;
        }
        getBlackboard().post("data://user/dialog/MergePeopleChoice", new Object[]{objArr, Boolean.valueOf(z)});
        dismiss();
    }
}
