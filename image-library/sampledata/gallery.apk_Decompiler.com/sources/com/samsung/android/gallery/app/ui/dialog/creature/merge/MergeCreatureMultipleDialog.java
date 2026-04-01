package com.samsung.android.gallery.app.ui.dialog.creature.merge;

import Ba.g;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import c0.C0086a;
import com.samsung.android.gallery.app.ui.dialog.abstraction.EditBaseDialog;
import com.samsung.android.gallery.module.data.CreatureData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.Arrays;
import q8.a;
import qa.e;
import t4.C0517a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MergeCreatureMultipleDialog extends EditBaseDialog {
    private Blackboard mBlackboard;
    private TextView mBodyTextView;
    private MediaItem[] mItems;
    private RecyclerView mListView;
    private RadioGroup mRadioGroup;
    private MediaItem mRepresentativeItem;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class FaceListAdapter extends RecyclerView.Adapter<FaceViewHolder> {
        private final MediaItem[] items;

        public /* synthetic */ FaceListAdapter(MediaItem[] mediaItemArr, int i2) {
            this(mediaItemArr);
        }

        public int getItemCount() {
            return this.items.length;
        }

        private FaceListAdapter(MediaItem[] mediaItemArr) {
            this.items = mediaItemArr;
        }

        public void onBindViewHolder(FaceViewHolder faceViewHolder, int i2) {
            faceViewHolder.loadThumbnail(this.items[i2]);
            if (this.items.length == 2) {
                int dimensionPixelOffset = faceViewHolder.itemView.getContext().getResources().getDimensionPixelOffset(R.dimen.merge_creature_dialog_item_thumb_size);
                int dimensionPixelOffset2 = faceViewHolder.itemView.getContext().getResources().getDimensionPixelOffset(R.dimen.merge_people_dialog_list_horizontal_margin_2_item);
                if (((dimensionPixelOffset2 * 2) + dimensionPixelOffset) * 2 < ResourceCompat.getWindowWidth(faceViewHolder.itemView.getContext())) {
                    ViewMarginUtils.setHorizontalMargin(faceViewHolder.itemView, dimensionPixelOffset2);
                    return;
                }
                View view = faceViewHolder.itemView;
                ViewMarginUtils.setHorizontalMargin(view, view.getContext().getResources().getDimensionPixelOffset(R.dimen.merge_people_dialog_list_horizontal_margin));
            }
        }

        public FaceViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
            return new FaceViewHolder(C0086a.d(viewGroup, R.layout.recycler_item_merge_creature_multiplie_layout, viewGroup, false));
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class FaceViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;

        public FaceViewHolder(View view) {
            super(view);
            this.imageView = (ImageView) view.findViewById(R.id.thumbnail);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$loadThumbnail$1(Bitmap bitmap, MediaItem mediaItem) {
            this.imageView.setImageBitmap(bitmap);
            this.imageView.setTag(mediaItem);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$loadThumbnail$2(MediaItem mediaItem, Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
            ThreadUtil.postOnUiThread(new d(this, bitmap, mediaItem));
        }

        /* access modifiers changed from: private */
        public void loadThumbnail(MediaItem mediaItem) {
            if (mediaItem != null) {
                ThumbnailLoader.getInstance().loadThumbnail(mediaItem, ThumbKind.MEDIUM_KIND, new b(mediaItem), new c(this, mediaItem));
            }
        }
    }

    private int getTitleResId() {
        return R.string.merge_these_face_groups_q;
    }

    private void initBodyText(int i2) {
        String str;
        Context context = getContext();
        if (context == null) {
            this.mBodyTextView.setText("");
            return;
        }
        if (i2 > 1) {
            str = context.getString(R.string.body_merge_people_select_confirm_dialog);
        } else {
            MediaItem mediaItem = this.mRepresentativeItem;
            if (mediaItem == null) {
                str = context.getString(R.string.body_merge_people_no_name_confirm_dialog);
            } else {
                str = context.getString(R.string.body_merge_people_confirm_dialog, new Object[]{CreatureData.of(mediaItem).creatureName});
            }
        }
        this.mBodyTextView.setText(SeApiCompat.naturalizeText(str));
    }

    private void initListView() {
        this.mListView.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
        this.mListView.setAdapter(new FaceListAdapter(this.mItems, 0));
    }

    private void initRadioButton(RadioButton radioButton, MediaItem mediaItem) {
        radioButton.setText(CreatureData.of(mediaItem).creatureName);
        radioButton.setOnClickListener(new g(29, this, mediaItem));
        this.mRadioGroup.addView(radioButton);
    }

    private void initRadioButtons(MediaItem[] mediaItemArr) {
        if (mediaItemArr.length > 1) {
            this.mRadioGroup.setVisibility(0);
            for (MediaItem mediaItem : mediaItemArr) {
                RadioButton radioButton = new RadioButton(new ContextThemeWrapper(getContext(), R.style.DialogRadioButtonListStyle));
                initRadioButton(radioButton, mediaItem);
                if (mediaItem == this.mRepresentativeItem) {
                    this.mRadioGroup.check(radioButton.getId());
                }
            }
        }
    }

    private void initRepresentativeName() {
        for (MediaItem mediaItem : this.mItems) {
            if (!TextUtils.isEmpty(CreatureData.of(mediaItem).creatureName)) {
                this.mRepresentativeItem = mediaItem;
                return;
            }
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ MediaItem[] lambda$initDialog$0(int i2) {
        return new MediaItem[i2];
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ MediaItem[] lambda$initDialog$1(int i2) {
        return new MediaItem[i2];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initRadioButton$2(MediaItem mediaItem, View view) {
        postAnalyticsLog(AnalyticsEventId.EVENT_SEARCH_FACE_TAG_MERGE_PEOPLE_SELECT_NAME);
        this.mRepresentativeItem = mediaItem;
    }

    public void bindViews(View view) {
        super.bindViews(view);
        this.mListView = (RecyclerView) view.findViewById(R.id.list_view);
        this.mBodyTextView = (TextView) view.findViewById(R.id.dialog_body);
        this.mRadioGroup = (RadioGroup) view.findViewById(R.id.radio_grp_layout);
    }

    public int getLayoutId() {
        return R.layout.merge_creature_multiple_dialog;
    }

    public String getScreenId() {
        return AnalyticsScreenId.SCREEN_VISUAL_SEARCH_VIEW_MERGE_PEOPLE.toString();
    }

    public String getTitle() {
        Context context = getContext();
        if (context != null) {
            return context.getString(getTitleResId());
        }
        return "";
    }

    public void initDialog() {
        Blackboard blackboard = getBlackboard();
        this.mBlackboard = blackboard;
        MediaItem[] mediaItemArr = (MediaItem[]) Arrays.stream((MediaItem[]) blackboard.pop("data://user/selected")).map(new a(5)).toArray(new C0517a(0));
        this.mItems = mediaItemArr;
        if (mediaItemArr.length < 2) {
            Log.w(this.TAG, "Select more than 2 items");
            dismiss();
            return;
        }
        initRepresentativeName();
        initListView();
        MediaItem[] mediaItemArr2 = (MediaItem[]) Arrays.stream(this.mItems).filter(new e(2)).toArray(new C0517a(1));
        initBodyText(mediaItemArr2.length);
        initRadioButtons(mediaItemArr2);
    }

    public void onCancel(DialogInterface dialogInterface) {
        getBlackboard().post("data://user/dialog/MergeCreatureMultiple", (Object) null);
    }

    public void onClickNegative(View view) {
        AnalyticsEventId analyticsEventId;
        if (ViewUtils.isVisible(this.mRadioGroup)) {
            analyticsEventId = AnalyticsEventId.EVENT_SEARCH_FACE_TAG_MERGE_PEOPLE_SELECT_CANCEL;
        } else {
            analyticsEventId = AnalyticsEventId.EVENT_SEARCH_FACE_TAG_MERGE_PEOPLE_CANCEL;
        }
        postAnalyticsLog(analyticsEventId);
        getBlackboard().post("data://user/dialog/MergeCreatureMultiple", (Object) null);
        super.onClickNegative(view);
    }

    public void onClickPositive(View view) {
        AnalyticsEventId analyticsEventId;
        if (ViewUtils.isVisible(this.mRadioGroup)) {
            analyticsEventId = AnalyticsEventId.EVENT_SEARCH_FACE_TAG_MERGE_PEOPLE_SELECT_OK;
        } else {
            analyticsEventId = AnalyticsEventId.EVENT_SEARCH_FACE_TAG_MERGE_PEOPLE_OK;
        }
        postAnalyticsLog(analyticsEventId);
        super.onClickPositive(view);
        if (this.mRepresentativeItem == null) {
            this.mRepresentativeItem = this.mItems[0];
        }
        MediaItem mediaItem = this.mRepresentativeItem;
        this.mBlackboard.post("data://user/dialog/MergeCreatureMultiple", new Object[]{mediaItem, this.mItems, CreatureData.of(mediaItem).creatureName});
        dismiss();
    }
}
