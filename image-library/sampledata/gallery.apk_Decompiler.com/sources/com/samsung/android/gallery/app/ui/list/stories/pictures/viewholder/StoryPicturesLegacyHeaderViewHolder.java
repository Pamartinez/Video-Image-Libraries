package com.samsung.android.gallery.app.ui.list.stories.pictures.viewholder;

import Ab.a;
import Da.f;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.samsung.android.gallery.app.ui.list.stories.pictures.abstraction.StoryHeaderViewListener;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.gallery.widget.abstraction.SharedTransition;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoryPicturesLegacyHeaderViewHolder extends ListViewHolder {
    protected TextView mDurationText;
    private StoryHeaderViewListener mListener;
    LinearLayout mTitleContainer;
    TextView mTitleTextView;

    public StoryPicturesLegacyHeaderViewHolder(View view, int i2) {
        super(view, i2);
        SeApiCompat.setButtonShapeEnabled(this.mTitleTextView);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindView$0(View view) {
        onTitleClicked();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateDuration$1(String str) {
        TextView textView = this.mDurationText;
        if (textView != null) {
            textView.setText(str);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateDuration$2(MediaItem mediaItem) {
        long[] storyTimeDurationArray = MediaItemStory.getStoryTimeDurationArray(mediaItem);
        ViewUtils.post(this.mDurationText, new f(5, this, TimeUtil.getEventDatePeriod(storyTimeDurationArray[0], storyTimeDurationArray[1], 50)));
        DbCompat.storyApi().updateStoryTime(mediaItem.getAlbumID());
    }

    private void setTransitionName() {
        String str;
        MediaItem mediaItem = this.mMediaItem;
        if (mediaItem != null) {
            str = String.valueOf(MediaItemStory.getStoryId(mediaItem));
        } else {
            str = "";
        }
        TextView titleView = getTitleView();
        SharedTransition.setTransitionName(titleView, "story/title/" + str);
        TextView textView = this.mDurationText;
        SharedTransition.setTransitionName(textView, "story/duration/" + str);
    }

    private void updateTitle(MediaItem mediaItem) {
        if (TextUtils.isEmpty(mediaItem.getTitle())) {
            this.mTitleTextView.setVisibility(8);
            return;
        }
        this.mTitleTextView.setVisibility(0);
        this.mTitleTextView.setText(mediaItem.getTitle());
    }

    public void bind(MediaItem mediaItem) {
        super.bind(mediaItem);
        updateTitle(mediaItem);
        setTransitionName();
    }

    public final void bindView(View view) {
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.title_container);
        this.mTitleContainer = linearLayout;
        linearLayout.setOnClickListener(new a(18, this));
        this.mTitleTextView = (TextView) view.findViewById(R.id.title);
        this.mDurationText = (TextView) view.findViewById(R.id.duration);
    }

    public View getDecoView(int i2) {
        if (i2 == 31) {
            return this.mDurationText;
        }
        return super.getDecoView(i2);
    }

    public TextView getTitleView() {
        return this.mTitleTextView;
    }

    public void onTitleClicked() {
        StoryHeaderViewListener storyHeaderViewListener = this.mListener;
        if (storyHeaderViewListener != null) {
            storyHeaderViewListener.onTitleClicked();
        }
    }

    public void recycle() {
        super.recycle();
        this.mTitleTextView.setText((CharSequence) null);
        this.mDurationText.setText((CharSequence) null);
    }

    public void setEnabledHeader(boolean z) {
        float f;
        if (z) {
            f = 1.0f;
        } else {
            f = 0.4f;
        }
        this.mTitleContainer.setAlpha(f);
        this.mTitleContainer.setEnabled(z);
        this.mDurationText.setAlpha(f);
        this.mDurationText.setEnabled(z);
    }

    public void setListener(StoryHeaderViewListener storyHeaderViewListener) {
        this.mListener = storyHeaderViewListener;
    }

    public void updateDuration(Context context, MediaItem mediaItem) {
        if (mediaItem != null) {
            ThreadUtil.postOnBgThread(new f(4, this, mediaItem));
        } else {
            Log.e(this.TAG, "fail update duration");
        }
    }
}
