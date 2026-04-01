package com.samsung.android.gallery.app.ui.list.search.category.people;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.samsung.android.gallery.app.ui.list.search.category.CategoryItemViewHolderFactory;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.listviewholder.ViewHolderValue;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CreatureItemViewHolderFactory extends CategoryItemViewHolderFactory {
    private static final int FOOTER_LAYOUT_ID;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class FooterViewHolder extends ListViewHolder {
        private ImageView arrow;
        boolean isRtl = Features.isEnabled(Features.IS_RTL);
        private TextView title;

        public FooterViewHolder(View view, int i2) {
            super(view, i2);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$updateDecoration$0(boolean z, ImageView imageView) {
            int i2 = 270;
            boolean z3 = this.isRtl;
            if (!z ? !z3 : z3) {
                i2 = 90;
            }
            imageView.setRotation((float) i2);
        }

        public void bindView(View view) {
            this.arrow = (ImageView) view.findViewById(R.id.arrow);
            this.title = (TextView) view.findViewById(R.id.title);
        }

        /* JADX WARNING: type inference failed for: r0v0, types: [java.util.function.Consumer, java.lang.Object] */
        public void recycle() {
            super.recycle();
            Optional.ofNullable(this.arrow).ifPresent(new Object());
        }

        public void setEnable(boolean z) {
            float f;
            View view = this.itemView;
            if (z) {
                f = 1.0f;
            } else {
                f = 0.5f;
            }
            ViewUtils.setAlpha(view, f);
            ViewUtils.setViewEnabled(this.itemView, z);
        }

        public boolean updateDecoration(int i2, Object... objArr) {
            int i7;
            if ((i2 & 4) == 0) {
                return false;
            }
            boolean booleanValue = objArr[0].booleanValue();
            Optional.ofNullable(this.arrow).ifPresent(new b(this, booleanValue));
            TextView textView = this.title;
            if (booleanValue) {
                i7 = R.string.show_fewer;
            } else {
                i7 = R.string.view_all;
            }
            textView.setText(i7);
            return true;
        }
    }

    static {
        int i2;
        if (PreferenceFeatures.OneUi8x.SUPPORT_ESSENTIAL_FACES) {
            i2 = R.layout.creature_category_footer_layout_85;
        } else {
            i2 = R.layout.creature_category_footer_layout;
        }
        FOOTER_LAYOUT_ID = i2;
    }

    public CreatureItemViewHolderFactory(Context context) {
        super(context);
    }

    private ListViewHolder createFooterViewHolder(ViewGroup viewGroup) {
        return new FooterViewHolder(this.mLayoutInflater.inflate(FOOTER_LAYOUT_ID, viewGroup, false), -3);
    }

    public ListViewHolder createListViewHolder(ViewGroup viewGroup, int i2, boolean z) {
        if (ViewHolderValue.isHeader(i2)) {
            return createHeaderViewHolder(R.layout.creature_category_header_layout, viewGroup);
        }
        if (ViewHolderValue.isFooter(i2)) {
            return createFooterViewHolder(viewGroup);
        }
        return createPeopleItemViewHolder(viewGroup, i2, z);
    }

    public int getPeopleItemLayoutId(boolean z) {
        if (PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85) {
            return R.layout.recycler_category_item_removable_creature_layout_85;
        }
        return R.layout.recycler_category_item_removable_creature_layout;
    }
}
