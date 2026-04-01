package com.samsung.android.gallery.app.ui.tipcard;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TipCardViewHolder extends RecyclerView.ViewHolder {
    TextView mCancel = ((TextView) this.itemView.findViewById(R.id.cancelView));
    TextView mContent = ((TextView) this.itemView.findViewById(R.id.headerContent));
    TextView mDone = ((TextView) this.itemView.findViewById(R.id.doneView));
    TextView mTitle = ((TextView) this.itemView.findViewById(R.id.headerTitle));

    public TipCardViewHolder(View view) {
        super(view);
    }

    public TextView getCancelView() {
        return this.mCancel;
    }

    public TextView getContentsView() {
        return this.mContent;
    }

    public TextView getDoneView() {
        return this.mDone;
    }

    public View getItemView() {
        return this.itemView;
    }

    public TextView getTitleView() {
        return this.mTitle;
    }
}
