package com.samsung.android.gallery.app.ui.list.picker.albums;

import android.view.View;
import android.widget.TextView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumFolderPickerHeaderViewHolder extends ListViewHolder {
    private TextView mHeaderDescription;

    public AlbumFolderPickerHeaderViewHolder(View view, int i2) {
        super(view, i2);
    }

    public void bindView(View view) {
        this.mHeaderDescription = (TextView) view.findViewById(R.id.title);
    }

    public TextView getHeaderDescriptionView() {
        return this.mHeaderDescription;
    }
}
