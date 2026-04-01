package com.samsung.android.gallery.app.ui.list.albums;

import A4.O;
import B4.a;
import B4.b;
import B4.c;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.album.CreateFolderCmd;
import com.samsung.android.gallery.module.album.AlbumHelper;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceName;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumsFooterView extends LinearLayout {
    private TextView mAlbumHideButton;
    private TextView mCreateFolderButton;
    private View mFooterView;
    private EventContext mHandler;
    private OnDataChangeListener mListener;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnDataChangeListener {
    }

    public AlbumsFooterView(EventContext eventContext, int i2) {
        super(eventContext.getContext());
        this.mHandler = eventContext;
        if (i2 > 12 && getState()) {
            initView();
            SimpleThreadPool.getInstance().execute(new a(this, 0));
        }
    }

    private void initView() {
        View.inflate(getContext(), R.layout.album_inline_cue_layout, this);
        this.mFooterView = findViewById(R.id.footer_layout);
        TextView textView = (TextView) findViewById(R.id.create_group_link);
        this.mCreateFolderButton = textView;
        textView.setOnClickListener(new b(this, 0));
        TextView textView2 = (TextView) findViewById(R.id.album_hide_link);
        this.mAlbumHideButton = textView2;
        textView2.setOnClickListener(new b(this, 1));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(boolean z) {
        setViewEnabled(!z);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$1() {
        post(new c((Object) this, AlbumHelper.getInstance().hasGroupNHideAlbum(this.mHandler.getContext()), 0));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onClickCreateGroup$3() {
        setViewEnabled(false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onClickHideAlbum$2() {
        setViewEnabled(false);
    }

    /* access modifiers changed from: private */
    public void onClickCreateGroup(View view) {
        Log.d("AlbumsFooterView", "onClickCreateGroup");
        new CreateFolderCmd().execute(this.mHandler, null, CreateFolderCmd.Type.CREATE_EMPTY, Boolean.TRUE, null);
        saveState(false);
        postDelayed(new a(this, 1), 600);
    }

    /* access modifiers changed from: private */
    public void onClickHideAlbum(View view) {
        Log.d("AlbumsFooterView", "onClickHideAlbum");
        this.mHandler.getBlackboard().post("command://MoveURL", "location://albums/hide");
        saveState(false);
        postDelayed(new a(this, 2), 600);
    }

    public void destroy() {
        this.mHandler = null;
    }

    public boolean getState() {
        return GalleryPreference.getInstance().loadBoolean(PreferenceName.SHOW_ALBUM_INLINE_CUE, true);
    }

    public void onEnableFooterView(boolean z) {
        float f;
        if (z) {
            f = 1.0f;
        } else {
            f = 0.4f;
        }
        setAlpha(f);
        setEnabled(z);
        TextView textView = this.mCreateFolderButton;
        if (textView != null) {
            textView.setClickable(z);
        }
        TextView textView2 = this.mAlbumHideButton;
        if (textView2 != null) {
            textView2.setClickable(z);
        }
    }

    public void saveState(boolean z) {
        GalleryPreference.getInstance().saveState(PreferenceName.SHOW_ALBUM_INLINE_CUE, z);
    }

    public void setOnDataChangedListener(OnDataChangeListener onDataChangeListener) {
        this.mListener = onDataChangeListener;
    }

    public void setViewEnabled(boolean z) {
        int i2;
        Log.d("AlbumsFooterView", "setViewEnabled", Boolean.valueOf(z));
        View view = this.mFooterView;
        if (z) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        ViewUtils.setVisibility(view, i2);
        ((AlbumsViewAdapter) ((O) this.mListener).e).lambda$onCreateViewHolder$0();
    }
}
