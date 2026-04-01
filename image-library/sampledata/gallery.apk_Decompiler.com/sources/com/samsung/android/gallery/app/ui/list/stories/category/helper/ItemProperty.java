package com.samsung.android.gallery.app.ui.list.stories.category.helper;

import Fa.I;
import V8.a;
import android.content.Context;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ItemProperty {
    private static final HashMap<String, ItemProperty> sDimenHelper = new HashMap<>();
    private Integer mItemEndMargin;
    private Integer mItemHeight;
    private Integer mItemRadius;
    private Integer mItemStartMargin;
    private Integer mItemWidth;
    private final HashMap<String, Property> mProperties = new HashMap<>();
    private Integer mSubTitleSize;
    private Integer mTitleSize;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class BasicItemProperty implements Property {
        public /* synthetic */ BasicItemProperty(int i2) {
            this();
        }

        public int getItemEndMargin(Context context) {
            return ItemProperty.getDimensionPixelOffset(context, R.dimen.stories_category_child_item_horizontal_margin);
        }

        public int getItemRadius(Context context) {
            return ItemProperty.getDimensionPixelOffset(context, R.dimen.stories_category_creation_item_round_radius);
        }

        public int getItemStartMargin(Context context) {
            return ItemProperty.getDimensionPixelOffset(context, R.dimen.stories_category_child_item_horizontal_margin);
        }

        public int getItemWidth(Context context) {
            return ItemProperty.getDimensionPixelOffset(context, R.dimen.stories_category_creation_item_width);
        }

        public int getSubTitleSize(Context context) {
            return ItemProperty.getDimensionPixelOffset(context, R.dimen.stories_category_creation_item_subtitle_size);
        }

        public int getTitleSize(Context context) {
            return ItemProperty.getDimensionPixelOffset(context, R.dimen.stories_category_creation_item_title_size);
        }

        private BasicItemProperty() {
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class BasicItemPropertyV85 implements Property {
        public /* synthetic */ BasicItemPropertyV85(int i2) {
            this();
        }

        public int getItemEndMargin(Context context) {
            return ItemProperty.getDimensionPixelOffset(context, R.dimen.stories_category_child_item_horizontal_margin);
        }

        public int getItemRadius(Context context) {
            return ItemProperty.getDimensionPixelOffset(context, R.dimen.stories_category_creation_item_round_radius_v85);
        }

        public int getItemStartMargin(Context context) {
            return ItemProperty.getDimensionPixelOffset(context, R.dimen.stories_category_child_item_horizontal_margin);
        }

        public int getItemWidth(Context context) {
            return ItemProperty.getDimensionPixelOffset(context, R.dimen.stories_category_creation_item_width_v85);
        }

        public int getSubTitleSize(Context context) {
            return ItemProperty.getDimensionPixelOffset(context, R.dimen.stories_category_creation_item_subtitle_size);
        }

        public int getTitleSize(Context context) {
            return ItemProperty.getDimensionPixelOffset(context, R.dimen.stories_category_creation_item_title_size);
        }

        private BasicItemPropertyV85() {
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Property {
        int getItemEndMargin(Context context);

        int getItemRadius(Context context);

        float getItemRatio(Context context) {
            return 1.13f;
        }

        int getItemStartMargin(Context context);

        int getItemWidth(Context context);

        int getSubTitleSize(Context context);

        int getTitleSize(Context context);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class TransitoryItemProperty implements Property {
        public /* synthetic */ TransitoryItemProperty(int i2) {
            this();
        }

        public int getItemEndMargin(Context context) {
            return ItemProperty.getDimensionPixelOffset(context, R.dimen.stories_category_transitory_side_margin);
        }

        public int getItemRadius(Context context) {
            return ItemProperty.getDimensionPixelOffset(context, R.dimen.stories_category_creation_item_round_radius);
        }

        public int getItemStartMargin(Context context) {
            return ItemProperty.getDimensionPixelOffset(context, R.dimen.stories_category_transitory_side_queue_margin) + ItemProperty.getDimensionPixelOffset(context, R.dimen.stories_category_transitory_side_margin);
        }

        public int getItemWidth(Context context) {
            return ItemProperty.getDimensionPixelOffset(context, R.dimen.stories_category_transitory_content_width);
        }

        public int getSubTitleSize(Context context) {
            return ItemProperty.getDimensionPixelOffset(context, R.dimen.stories_category_transitory_item_subtitle_size);
        }

        public int getTitleSize(Context context) {
            return ItemProperty.getDimensionPixelOffset(context, R.dimen.stories_category_transitory_item_title_size);
        }

        private TransitoryItemProperty() {
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class TransitoryItemPropertyV80 extends TransitoryItemProperty {
        public /* synthetic */ TransitoryItemPropertyV80(int i2) {
            this();
        }

        public int getItemEndMargin(Context context) {
            return ItemProperty.getDimensionPixelOffset(context, R.dimen.stories_category_transitory_side_margin_v80);
        }

        public int getItemStartMargin(Context context) {
            return ItemProperty.getDimensionPixelOffset(context, R.dimen.stories_category_transitory_side_queue_margin_v80) + ItemProperty.getDimensionPixelOffset(context, R.dimen.stories_category_transitory_side_margin_v80);
        }

        public int getItemWidth(Context context) {
            return ItemProperty.getDimensionPixelOffset(context, R.dimen.stories_category_transitory_content_width_v80);
        }

        private TransitoryItemPropertyV80() {
            super(0);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class TransitoryItemPropertyV85 extends TransitoryItemPropertyV80 {
        public /* synthetic */ TransitoryItemPropertyV85(int i2) {
            this();
        }

        public int getItemEndMargin(Context context) {
            return ItemProperty.getDimensionPixelOffset(context, R.dimen.stories_category_transitory_side_margin_v85);
        }

        public int getItemStartMargin(Context context) {
            return ItemProperty.getDimensionPixelOffset(context, R.dimen.stories_category_transitory_side_queue_margin_v85) + ItemProperty.getDimensionPixelOffset(context, R.dimen.stories_category_transitory_side_margin_v85);
        }

        public int getItemWidth(Context context) {
            return ItemProperty.getDimensionPixelOffset(context, R.dimen.stories_category_transitory_content_width_v85);
        }

        private TransitoryItemPropertyV85() {
            super(0);
        }
    }

    private ItemProperty() {
        create();
    }

    private void create() {
        this.mProperties.put("location://stories/category/transitory", createTransitoryProperty());
        this.mProperties.put("location://stories/category/trip", createBasicProperty());
        this.mProperties.put("location://stories/category/creation", createBasicProperty());
        this.mProperties.put("location://search/fileList/Category/Stories/Transitory", createTransitoryProperty());
    }

    private Property createBasicProperty() {
        if (PreferenceFeatures.OneUi8x.STORY_ONE_UI_85) {
            return new BasicItemPropertyV85(0);
        }
        return new BasicItemProperty(0);
    }

    private Property createTransitoryProperty() {
        if (PreferenceFeatures.OneUi8x.STORY_ONE_UI_85) {
            return new TransitoryItemPropertyV85(0);
        }
        if (PreferenceFeatures.OneUi8x.STORY_ONE_UI_80) {
            return new TransitoryItemPropertyV80(0);
        }
        return new TransitoryItemProperty(0);
    }

    /* access modifiers changed from: private */
    public static int getDimensionPixelOffset(Context context, int i2) {
        return context.getResources().getDimensionPixelOffset(i2);
    }

    public static int[] getItemHorizontalMargin(Context context, String str) {
        return getItemProperty(context, str).getItemHorizontalMarginInternal(context, str);
    }

    private int[] getItemHorizontalMarginInternal(Context context, String str) {
        if (this.mItemStartMargin == null || this.mItemEndMargin == null) {
            this.mItemStartMargin = Integer.valueOf(getProperty(str).getItemStartMargin(context));
            this.mItemEndMargin = Integer.valueOf(getProperty(str).getItemEndMargin(context));
        }
        return new int[]{this.mItemStartMargin.intValue(), this.mItemEndMargin.intValue()};
    }

    private static synchronized ItemProperty getItemProperty(Context context, String str) {
        ItemProperty computeIfAbsent;
        synchronized (ItemProperty.class) {
            computeIfAbsent = sDimenHelper.computeIfAbsent(context.toString() + "/" + str, new a(16));
        }
        return computeIfAbsent;
    }

    public static int getItemRadius(Context context, String str) {
        return getItemProperty(context, str).getItemRadiusInternal(context, str);
    }

    private int getItemRadiusInternal(Context context, String str) {
        if (this.mItemRadius == null) {
            this.mItemRadius = Integer.valueOf(getProperty(str).getItemRadius(context));
        }
        return this.mItemRadius.intValue();
    }

    public static int[] getItemSize(Context context, String str) {
        return getItemProperty(context, str).getItemSizeInternal(context, str);
    }

    private int[] getItemSizeInternal(Context context, String str) {
        if (this.mItemWidth == null || this.mItemHeight == null) {
            Integer valueOf = Integer.valueOf(getProperty(str).getItemWidth(context));
            this.mItemWidth = valueOf;
            this.mItemHeight = Integer.valueOf((int) (((float) valueOf.intValue()) * getProperty(str).getItemRatio(context)));
        }
        return new int[]{this.mItemWidth.intValue(), this.mItemHeight.intValue()};
    }

    private Property getProperty(String str) {
        str.getClass();
        char c5 = 65535;
        switch (str.hashCode()) {
            case 199731027:
                if (str.equals("location://stories/category/transitory")) {
                    c5 = 0;
                    break;
                }
                break;
            case 1665016629:
                if (str.equals("location://stories/category/trip")) {
                    c5 = 1;
                    break;
                }
                break;
            case 1752334109:
                if (str.equals("location://search/fileList/Category/Stories/Transitory")) {
                    c5 = 2;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
            case 1:
            case 2:
                return this.mProperties.get(str);
            default:
                return this.mProperties.get("location://stories/category/creation");
        }
    }

    public static int getSubTitleSize(Context context, String str) {
        return getItemProperty(context, str).getSubTitleSizeInternal(context, str);
    }

    private int getSubTitleSizeInternal(Context context, String str) {
        if (this.mSubTitleSize == null) {
            this.mSubTitleSize = Integer.valueOf(getProperty(str).getSubTitleSize(context));
        }
        return this.mSubTitleSize.intValue();
    }

    public static int getTitleSize(Context context, String str) {
        return getItemProperty(context, str).getTitleSizeInternal(context, str);
    }

    private int getTitleSizeInternal(Context context, String str) {
        if (this.mTitleSize == null) {
            this.mTitleSize = Integer.valueOf(getProperty(str).getTitleSize(context));
        }
        return this.mTitleSize.intValue();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ ItemProperty lambda$getItemProperty$0(String str) {
        return new ItemProperty();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$reset$1(Context context, String str) {
        if (str.startsWith(context.toString())) {
            sDimenHelper.remove(str);
        }
    }

    public static void reset(Context context) {
        new ArrayList(sDimenHelper.keySet()).forEach(new I(context, 4));
    }
}
