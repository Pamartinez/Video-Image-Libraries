package com.samsung.android.gallery.app.ui.list.search.pictures.cluster.clusteritem;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.TypefaceSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import c0.C0086a;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dbtype.ExtrasID;
import com.samsung.android.gallery.module.abstraction.ClusterResultType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.story.FontTypefaceUtils;
import com.samsung.android.gallery.support.type.IntelligentSearchIndexField;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CarouselClusterResult2ViewHolder extends ClusterResultViewHolder {
    private TextView mClusterInfoTitle;
    private ImageView mClusterTypeIcon;
    private ViewGroup mClusterTypeLayout;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class IconHolder {
        static final HashMap<String, Integer> CLUSTER_TYPE_ICON_MAP = new HashMap<String, Integer>() {
            {
                put("album_cluster", Integer.valueOf(R.drawable.gallery_ic_search_suggested_album));
                Integer valueOf = Integer.valueOf(R.drawable.gallery_ic_search_suggested_location);
                put("facet_location", valueOf);
                put("person_cluster", Integer.valueOf(R.drawable.gallery_ic_search_filtered_person));
                put("ocrtext", Integer.valueOf(R.drawable.gallery_ic_search_vision_ocr_off));
                if (PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71) {
                    put("usertag", Integer.valueOf(R.drawable.gallery_ic_search_suggested_tag));
                    put("pet_cluster", Integer.valueOf(R.drawable.gallery_ic_search_suggested_pets));
                    put("poitag", valueOf);
                }
                if (Features.isEnabled(Features.SUPPORT_PDC_CLUSTER)) {
                    put("pdc_cluster", Integer.valueOf(R.drawable.gallery_ic_search_suggested_special_day));
                }
            }
        };

        public static Drawable getTypeIcon(Context context, String str) {
            Integer num = CLUSTER_TYPE_ICON_MAP.get(str);
            if (num == null) {
                return null;
            }
            return context.getDrawable(num.intValue());
        }
    }

    public CarouselClusterResult2ViewHolder(EventContext eventContext, View view, int i2) {
        super(eventContext, view, i2);
    }

    private int getDimensionPixelOffset(int i2) {
        return getContext().getResources().getDimensionPixelOffset(i2);
    }

    private float getRadius(String str) {
        int i2;
        if ("person_cluster".equals(str)) {
            i2 = R.dimen.search_cluster_results_people_type_item_radius;
        } else {
            i2 = R.dimen.search_cluster_results_album_type_item_radius;
        }
        return (float) getDimensionPixelOffset(i2);
    }

    private Spannable getSpannableTitleString(String str) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            str2 = "";
        } else {
            str2 = C0086a.h('\"', "\"", str);
        }
        SpannableString spannableString = new SpannableString(str2);
        if (!TextUtils.isEmpty(str2)) {
            try {
                String argValue = ArgumentsUtil.getArgValue(this.mHandler.getLocationKey(), "fuzzyKeyword");
                if (TextUtils.isEmpty(argValue)) {
                    argValue = ArgumentsUtil.getArgValue(this.mHandler.getLocationKey(), "title");
                }
                int indexOf = str2.toLowerCase().indexOf(argValue.toLowerCase());
                int length = argValue.length() + indexOf;
                spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this.itemView.getContext(), R.color.search_matched_text_color)), indexOf, length, 33);
                spannableString.setSpan(new TypefaceSpan(FontTypefaceUtils.TextFont.ROBOTO_SEMI_BOLD.getTypeface()), indexOf, length, 33);
                return spannableString;
            } catch (IndexOutOfBoundsException e) {
                Log.w("CarClusterResult2ViewHolder", "getSpannableTitleString failed. " + e);
            }
        }
        return spannableString;
    }

    private void setClusterIcon(String str) {
        if ("ocrtext".equals(str)) {
            ViewGroup viewGroup = this.mClusterTypeLayout;
            if (viewGroup != null) {
                viewGroup.setBackground(IconHolder.getTypeIcon(getContext(), str));
                ViewUtils.setVisibleOrGone(this.mClusterTypeIcon, false);
            }
        } else {
            ViewGroup viewGroup2 = this.mClusterTypeLayout;
            if (viewGroup2 != null) {
                viewGroup2.setBackground(getContext().getDrawable(R.drawable.search_cluster_carousel_cluster_icon_bg));
            }
            ImageView imageView = this.mClusterTypeIcon;
            if (imageView != null) {
                imageView.setImageDrawable(IconHolder.getTypeIcon(getContext(), str));
                ViewUtils.setVisibleOrGone(this.mClusterTypeIcon, true);
            }
        }
        updateIconLayoutSizeAndMargin(str);
    }

    private void setClusterInfo(String str) {
        TextView textView = this.mClusterInfoTitle;
        if (textView != null) {
            textView.setText(ClusterResultType.of(str).titleResId);
        }
    }

    private void setThumbnailShapeByViewType(String str) {
        setThumbnailShape(1, getRadius(str));
    }

    private void updateIconLayoutSizeAndMargin(String str) {
        int i2;
        int i7 = 0;
        str.getClass();
        if (!str.equals("ocrtext")) {
            i2 = getDimensionPixelOffset(R.dimen.search_cluster_results_carousel_icon_layout_size);
            i7 = getDimensionPixelOffset(R.dimen.search_cluster_results_carousel_icon_layout_margin_default);
        } else {
            i2 = getDimensionPixelOffset(R.dimen.search_cluster_results_carousel_icon_layout_size_for_ocrs);
        }
        ViewMarginUtils.setMargin(this.mClusterTypeLayout, Integer.valueOf(i7), 0, 0, Integer.valueOf(i7));
        ViewUtils.setViewSize(this.mClusterTypeLayout, Integer.valueOf(i2), Integer.valueOf(i2));
    }

    public void bind(MediaItem mediaItem) {
        String str = (String) mediaItem.getExtra(ExtrasID.CLUSTER_TYPE);
        Log.d("CarClusterResult2ViewHolder", "bind", str);
        super.bind(mediaItem);
        setThumbnailShapeByViewType(str);
        if (str != null && IntelligentSearchIndexField.isCarouselClusterType(str)) {
            setClusterIcon(str);
            setClusterInfo(str);
        }
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mDecoViewLayout = view.findViewById(R.id.deco_view_layout);
        this.mClusterTypeLayout = (ViewGroup) view.findViewById(R.id.cluster_type_icon_layout);
        this.mClusterTypeIcon = (ImageView) view.findViewById(R.id.cluster_type_icon);
        this.mClusterInfoTitle = (TextView) view.findViewById(R.id.cluster_info_title);
        ViewUtils.setViewShape(view.findViewById(R.id.thumbnail_container), 1, (float) getDimensionPixelOffset(R.dimen.search_cluster_results_carousel_item_radius));
    }

    public String getDurationText(MediaItem mediaItem) {
        if (mediaItem != null) {
            return Integer.toString(mediaItem.getCount());
        }
        return "";
    }

    public String getTitleText(MediaItem mediaItem) {
        if (mediaItem != null) {
            return mediaItem.getTitle();
        }
        return null;
    }

    public void recycle() {
        super.recycle();
        this.mClusterTypeIcon.setImageDrawable((Drawable) null);
        this.mClusterInfoTitle.setText((CharSequence) null);
    }

    public void setTitleText(MediaItem mediaItem) {
        this.mTitleText.setText(getSpannableTitleString(getTitleText(mediaItem)));
    }

    public ListViewHolder setThumbnailShape(int i2, float f) {
        return this;
    }
}
