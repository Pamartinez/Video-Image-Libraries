package androidx.appcompat.widget;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import androidx.appcompat.R$dimen;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\"\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00062\b\u0010\r\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u000e\u0010\b\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Landroidx/appcompat/widget/SeslArrayAdapter;", "Landroid/widget/ArrayAdapter;", "", "context", "Landroid/content/Context;", "resource", "", "(Landroid/content/Context;I)V", "mInitPaddingBottom", "mInitPaddingTop", "getDropDownView", "Landroid/view/View;", "position", "convertView", "parent", "Landroid/view/ViewGroup;", "appcompat_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SeslArrayAdapter extends ArrayAdapter<Object> {
    private int mInitPaddingBottom;
    private int mInitPaddingTop;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SeslArrayAdapter(Context context, int i2) {
        super(context, i2);
        j.e(context, "context");
    }

    public View getDropDownView(int i2, View view, ViewGroup viewGroup) {
        j.e(viewGroup, "parent");
        View dropDownView = super.getDropDownView(i2, view, viewGroup);
        if (view == null) {
            this.mInitPaddingTop = dropDownView.getPaddingTop();
            this.mInitPaddingBottom = dropDownView.getPaddingBottom();
        }
        int dimensionPixelSize = dropDownView.getResources().getDimensionPixelSize(R$dimen.sesl_popup_menu_first_last_item_vertical_edge_padding);
        int i7 = this.mInitPaddingTop + dimensionPixelSize;
        int i8 = this.mInitPaddingBottom + dimensionPixelSize;
        int paddingLeft = dropDownView.getPaddingLeft();
        if (i2 != 0) {
            i7 = this.mInitPaddingTop;
        }
        int paddingRight = dropDownView.getPaddingRight();
        if (i2 != getCount() - 1) {
            i8 = this.mInitPaddingBottom;
        }
        dropDownView.setPadding(paddingLeft, i7, paddingRight, i8);
        return dropDownView;
    }
}
