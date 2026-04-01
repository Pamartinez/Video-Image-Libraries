package com.samsung.android.gallery.app.ui.list.albums.pictures.setting;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewStub;
import android.widget.CompoundButton;
import android.widget.TextView;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.database.dbtype.ExtrasID;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.TriConsumer;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class ScreenshotFilterCustomViewHolder extends ListViewHolder implements View.OnClickListener {
    private TextView mFilterNameTextView;
    private View mReorderIconView;
    private boolean mReorderMode;
    private SwitchCompat mSwitchCompat;
    private TriConsumer<RecyclerView.ViewHolder, View, MotionEvent> mTouchCallback;

    public ScreenshotFilterCustomViewHolder(View view, int i2, TriConsumer<RecyclerView.ViewHolder, View, MotionEvent> triConsumer) {
        super(view, i2);
        this.mTouchCallback = triConsumer;
    }

    private void inflateReorderIcon() {
        View view = this.mReorderIconView;
        if (view instanceof ViewStub) {
            View inflate = ((ViewStub) view).inflate();
            this.mReorderIconView = inflate;
            inflate.setOnTouchListener(new d(this));
            ViewUtils.setVisibleOrGone(this.mReorderIconView, false);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$inflateReorderIcon$0(View view, MotionEvent motionEvent) {
        return onReorderTouched(this.itemView, motionEvent);
    }

    /* access modifiers changed from: private */
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        this.mMediaItem.setExtra(ExtrasID.SCREENSHOT_FILTER_ON, Boolean.valueOf(z));
        Blackboard.publishGlobal("command:///ScreenshotFilterOrderChanged", new Object[]{this.mMediaItem, Boolean.TRUE});
    }

    private boolean onReorderTouched(View view, MotionEvent motionEvent) {
        TriConsumer<RecyclerView.ViewHolder, View, MotionEvent> triConsumer;
        if (!this.mReorderMode || (triConsumer = this.mTouchCallback) == null) {
            return false;
        }
        triConsumer.accept(this, view, motionEvent);
        return false;
    }

    public void bind(MediaItem mediaItem) {
        super.bind(mediaItem);
        this.mFilterNameTextView.setText(mediaItem.getTitle());
        this.mSwitchCompat.setChecked(((Boolean) mediaItem.getExtra(ExtrasID.SCREENSHOT_FILTER_ON, Boolean.TRUE)).booleanValue());
        this.mSwitchCompat.setOnCheckedChangeListener(new c(this));
    }

    public void bindView(View view) {
        this.mFilterNameTextView = (TextView) view.findViewById(R.id.screenshot_filter_name);
        this.mSwitchCompat = (SwitchCompat) view.findViewById(R.id.switch_category);
        this.mReorderIconView = view.findViewById(R.id.reorder_icon);
    }

    public void onClick(View view) {
        SwitchCompat switchCompat = this.mSwitchCompat;
        onCheckedChanged(switchCompat, switchCompat.isChecked());
    }

    public void recycle() {
        super.recycle();
        this.mFilterNameTextView.setText((CharSequence) null);
        this.mSwitchCompat.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) null);
    }

    public void setReorderMode(boolean z) {
        this.mReorderMode = z;
        if (z) {
            inflateReorderIcon();
        }
        ViewUtils.setVisibleOrGone(this.mReorderIconView, z);
        ViewUtils.setVisibleOrGone(this.mSwitchCompat, !z);
    }
}
