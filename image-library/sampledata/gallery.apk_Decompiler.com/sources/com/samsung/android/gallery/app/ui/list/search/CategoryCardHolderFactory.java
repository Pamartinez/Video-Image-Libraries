package com.samsung.android.gallery.app.ui.list.search;

import android.view.View;
import android.view.ViewGroup;
import c0.C0086a;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class CategoryCardHolderFactory {
    private static final int ITEM_DEFAULT_CARD_LAYOUT_ID;

    static {
        int i2;
        if (PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71) {
            i2 = R.layout.recycler_item_cardlist_layout_7x;
        } else if (PreferenceFeatures.OneUi6x.VISUAL_SEARCH_61) {
            i2 = R.layout.recycler_item_cardlist_layout_61;
        } else {
            i2 = R.layout.recycler_item_cardlist_layout_v2;
        }
        ITEM_DEFAULT_CARD_LAYOUT_ID = i2;
    }

    public static ListViewHolder create(ViewGroup viewGroup, int i2) {
        switch (i2) {
            case 2:
                if (PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71) {
                    return new CategoryFlexboxCardHolder(inflateCardView(viewGroup, ITEM_DEFAULT_CARD_LAYOUT_ID), i2);
                }
                return new CategoryTag2CardHolder(inflateCardView(viewGroup, R.layout.recycler_item_cardlist_tag_layout_v2), i2);
            case 3:
                return createPeopleCardCompat(viewGroup, i2);
            case 4:
                if (PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85 || !PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71) {
                    return new CategoryLocation2CardHolder(inflateCardView(viewGroup, R.layout.recycler_item_cardlist_location_layout_v2), i2);
                }
                return new CategoryLocation3CardHolder(inflateCardView(viewGroup, ITEM_DEFAULT_CARD_LAYOUT_ID), i2);
            case 5:
                if (PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71) {
                    return new CategoryShotModeCardHolder(inflateCardView(viewGroup, ITEM_DEFAULT_CARD_LAYOUT_ID), i2);
                }
                return new CategoryFlexboxCardHolder(inflateCardView(viewGroup, ITEM_DEFAULT_CARD_LAYOUT_ID), i2);
            case 6:
                return new CategoryCardHolderV2(inflateCardView(viewGroup, R.layout.recycler_item_cardlist_other_scene_layout), i2);
            case 7:
                return new CategoryActivityCardHolder(inflateCardView(viewGroup, ITEM_DEFAULT_CARD_LAYOUT_ID), i2);
            case 8:
            case 9:
                return new CategoryFlexboxCardHolder(inflateCardView(viewGroup, ITEM_DEFAULT_CARD_LAYOUT_ID), i2);
            case 10:
                return new CategoryStoriesCardHolder(inflateCardView(viewGroup, R.layout.recycler_item_cardlist_stories_layout), i2);
            default:
                return new CategoryCardHolderV2(inflateCardView(viewGroup, ITEM_DEFAULT_CARD_LAYOUT_ID), i2);
        }
    }

    private static ListViewHolder createPeopleCardCompat(ViewGroup viewGroup, int i2) {
        View inflateCardView = inflateCardView(viewGroup, ITEM_DEFAULT_CARD_LAYOUT_ID);
        if (Features.isEnabled(Features.SUPPORT_PET_CLUSTER)) {
            return new CategoryPeoplePets2CardHolder(inflateCardView, i2);
        }
        return new CategoryPeople2CardHolder(inflateCardView, i2);
    }

    private static View inflateCardView(ViewGroup viewGroup, int i2) {
        return C0086a.d(viewGroup, i2, viewGroup, false);
    }
}
