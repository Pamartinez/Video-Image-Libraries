package com.samsung.android.gallery.app.ui.list.stories.hiderule.v1;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.module.data.HideRuleData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.scs.ai.language.a;
import com.samsung.scsp.media.api.d;
import com.sec.android.gallery3d.R;
import f6.C0454a;
import i.C0212a;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class HideRuleViewHolder extends HideRuleBaseViewHolder {
    TextView mAddButton;
    ViewGroup mAddButtonLayout;
    TextView mDescription;
    View mDivider;
    private GridLayoutManager mGridLayoutManager;
    private HideRuleSceneItemAdapter mItemAdapter;
    RecyclerView mListView;
    View mPickerButton;
    TextView mTitle;

    public HideRuleViewHolder(View view, int i2) {
        super(view, i2);
    }

    private void bindButton(MediaItem mediaItem) {
        int i2;
        Context context = this.itemView.getContext();
        if (HideRuleData.isHideDate(mediaItem)) {
            i2 = R.string.add_dates;
        } else {
            i2 = getHideSceneButtonStringId();
        }
        String string = context.getString(i2);
        this.mAddButton.setText(string);
        ViewGroup viewGroup = this.mAddButtonLayout;
        StringBuilder t = C0212a.t(string, ArcCommonLog.TAG_COMMA);
        t.append(this.itemView.getContext().getString(R.string.speak_button));
        viewGroup.setContentDescription(t.toString());
    }

    private void bindListView(MediaItem mediaItem) {
        Context context = this.itemView.getContext();
        int i2 = 0;
        int i7 = 1;
        if (this.mItemAdapter == null) {
            if (HideRuleData.isHideDate(mediaItem)) {
                this.mItemAdapter = new HideRuleDateItemAdapter(context, mediaItem);
                ViewMarginUtils.setTopMargin(this.mListView, 0);
            } else {
                this.mItemAdapter = new HideRuleSceneItemAdapter(context, mediaItem);
                i7 = context.getResources().getIntArray(R.array.hide_rule_scene_column_count)[0];
                MediaItem[] itemsInFolder = mediaItem.getItemsInFolder();
                ViewGroup viewGroup = this.mAddButtonLayout;
                if (itemsInFolder.length > 0) {
                    i2 = 8;
                }
                viewGroup.setVisibility(i2);
                ViewMarginUtils.setTopMargin(this.mListView, context.getResources().getDimensionPixelOffset(R.dimen.story_hide_rule_item_list_margin_vertical));
            }
            this.mItemAdapter.setOnItemClickListener(new a(17, this));
            this.mListView.setAdapter(this.mItemAdapter);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(context, i7);
            this.mGridLayoutManager = gridLayoutManager;
            this.mListView.setLayoutManager(gridLayoutManager);
            ViewUtils.setVisibleOrGone(this.mListView, ((Boolean) Optional.ofNullable(mediaItem.getItemsInFolder()).map(new d(14)).orElse(Boolean.FALSE)).booleanValue());
        } else if (!HideRuleData.isHideDate(mediaItem)) {
            i7 = context.getResources().getIntArray(R.array.hide_rule_scene_column_count)[0];
        }
        this.mItemAdapter.setColumnCount(i7);
        this.mGridLayoutManager.setSpanCount(i7);
    }

    private void bindPickerButton(MediaItem mediaItem) {
        boolean z;
        MediaItem[] itemsInFolder = mediaItem.getItemsInFolder();
        View view = this.mPickerButton;
        if (HideRuleData.isHideDate(mediaItem) || itemsInFolder.length <= 0) {
            z = false;
        } else {
            z = true;
        }
        ViewUtils.setVisibleOrGone(view, z);
    }

    private void bindText(MediaItem mediaItem) {
        Context context = this.itemView.getContext();
        if (HideRuleData.isHideDate(mediaItem)) {
            this.mTitle.setText(context.getString(R.string.hide_dates));
            this.mDescription.setText(context.getString(R.string.hide_dates_description));
            return;
        }
        this.mTitle.setText(context.getString(getHideSceneTitleStringId()));
        this.mDescription.setText(context.getString(getHideSceneDescriptionStringId()));
    }

    private int getHideSceneButtonStringId() {
        if (Features.isEnabled(Features.SUPPORT_HIDE_RULE_PETS)) {
            return R.string.add_people_and_pets;
        }
        return R.string.add_people;
    }

    private int getHideSceneDescriptionStringId() {
        if (Features.isEnabled(Features.SUPPORT_HIDE_RULE_PETS)) {
            return R.string.hide_people_and_pets_description;
        }
        return R.string.hide_people_description;
    }

    private int getHideSceneTitleStringId() {
        if (Features.isEnabled(Features.SUPPORT_HIDE_RULE_PETS)) {
            return R.string.hide_people_and_pets;
        }
        return R.string.hide_people;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Boolean lambda$bindListView$0(MediaItem[] mediaItemArr) {
        boolean z;
        if (mediaItemArr.length > 0) {
            z = true;
        } else {
            z = false;
        }
        return Boolean.valueOf(z);
    }

    /* access modifiers changed from: private */
    public void onClickHideRuleSubItem(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        if (HideRuleData.isHideDate(this.mMediaItem)) {
            if (i7 == 1002) {
                onClickHideRuleSubItemInternal(1002, i2);
            }
        } else if (HideRuleData.isHideScene(this.mMediaItem)) {
            onClickHideRuleSubItemInternal(1003, i2);
        }
    }

    private void onClickHideRuleSubItemInternal(int i2, int i7) {
        if (i7 == -1 || this.mOnItemClickListener == null) {
            String str = this.TAG;
            Log.w(str, "invalid click=" + i7);
            return;
        }
        MediaItem mediaItem = getMediaItem();
        if (mediaItem != null) {
            this.mOnItemClickListener.onItemClick(this, i7, mediaItem, i2);
        } else {
            Log.e(this.TAG, "ignore onClick. item is not ready");
        }
    }

    public void bind(MediaItem mediaItem) {
        super.bind(mediaItem);
        bindButton(mediaItem);
        bindText(mediaItem);
        bindListView(mediaItem);
        bindPickerButton(mediaItem);
    }

    public final void bindView(View view) {
        this.mTitle = (TextView) view.findViewById(R.id.hide_rule_header_title);
        this.mDescription = (TextView) view.findViewById(R.id.hide_rule_header_description);
        this.mListView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        this.mDivider = view.findViewById(R.id.hide_rule_card_divider);
        ViewGroup viewGroup = (ViewGroup) view.findViewById(R.id.add_button_layout);
        this.mAddButtonLayout = viewGroup;
        viewGroup.setOnClickListener(new C0454a(this, 0));
        this.mAddButton = (TextView) view.findViewById(R.id.hide_rule_add);
        View findViewById = view.findViewById(R.id.execute_picker);
        this.mPickerButton = findViewById;
        findViewById.setOnClickListener(new C0454a(this, 1));
    }

    public void onClickAddHideRule(View view) {
        if (HideRuleData.isHideDate(this.mMediaItem)) {
            onItemClickInternal(1001);
        } else {
            onItemClickInternal(1003);
        }
    }

    public void onClickExecuteFacePicker(View view) {
        onItemClickInternal(1003);
    }

    public void onConfigurationChanged() {
        HideRuleSceneItemAdapter hideRuleSceneItemAdapter = this.mItemAdapter;
        if (hideRuleSceneItemAdapter != null) {
            hideRuleSceneItemAdapter.notifyDataSetChanged();
        }
        ViewMarginUtils.setVerticalMargin(this.mAddButtonLayout, getResources().getDimensionPixelOffset(R.dimen.story_hide_rule_add_button_margin_vertical));
        ViewMarginUtils.setHorizontalPadding(this.mAddButtonLayout, getResources().getDimensionPixelOffset(R.dimen.story_hide_rule_add_button_padding_horizontal));
        ViewMarginUtils.setTopMargin(this.mDivider, getResources().getDimensionPixelOffset(R.dimen.story_hide_rule_divider_margin_top));
    }

    public void recycle() {
        super.recycle();
        this.mItemAdapter = null;
        RecyclerView recyclerView = this.mListView;
        if (recyclerView != null) {
            recyclerView.setAdapter((RecyclerView.Adapter) null);
        }
        this.mGridLayoutManager = null;
    }

    public void setDividerVisibility(boolean z) {
        int i2;
        View view = this.mDivider;
        if (z) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        view.setVisibility(i2);
    }
}
