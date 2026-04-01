package com.samsung.android.gallery.plugins.mergeplayer;

import A.a;
import A4.C0366a;
import A4.C0384t;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.RelativeLayout;
import androidx.fragment.app.Fragment;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.plugins.R$id;
import com.samsung.android.gallery.plugins.R$layout;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.videoview.mediaplayer.IMediaPlayerView;
import com.samsung.android.gallery.widget.videoview.mediaplayer.MediaTextureViewCompat;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MergeMotionPhotoFragment extends Fragment implements MergePlayerListener {
    private static final String TAG = "MergeMotionPhotoFragment";
    private final Blackboard mBlackboard = Blackboard.getInstance(toString());
    private RelativeLayout mLayout;
    private IMediaPlayerView mMediaPlayerView;
    private MergeMotionPhotoModel mModel = new MergeMotionPhotoModel();

    private IMediaPlayerView[] prepareView(int i2) {
        IMediaPlayerView[] iMediaPlayerViewArr = new IMediaPlayerView[i2];
        IMediaPlayerView iMediaPlayerView = this.mMediaPlayerView;
        iMediaPlayerViewArr[0] = iMediaPlayerView;
        ViewGroup.LayoutParams layoutParams = iMediaPlayerView.getLayoutParams();
        for (int i7 = 1; i7 < i2; i7++) {
            MediaTextureViewCompat mediaTextureViewCompat = new MediaTextureViewCompat(this.mMediaPlayerView.getContext(), (AttributeSet) null, 0, 0, 3);
            mediaTextureViewCompat.setLayoutParams(layoutParams);
            iMediaPlayerViewArr[i7] = mediaTextureViewCompat;
            this.mLayout.addView(mediaTextureViewCompat.getView());
            mediaTextureViewCompat.setVisibility(8);
            mediaTextureViewCompat.setAlpha(0.0f);
        }
        return iMediaPlayerViewArr;
    }

    public int getDiff(IMediaPlayerView iMediaPlayerView, IMediaPlayerView iMediaPlayerView2) {
        if (iMediaPlayerView == null || iMediaPlayerView2 == null) {
            return 0;
        }
        iMediaPlayerView.getDuration();
        return iMediaPlayerView2.getDuration() - ((int) (iMediaPlayerView2.getMediaItem().getDateTaken() - iMediaPlayerView.getMediaItem().getDateTaken()));
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R$layout.merge_motion_photo_fragment, viewGroup, false);
    }

    public void onDestroyView() {
        for (int i2 = 1; i2 < this.mModel.getSubItemCount(); i2++) {
            this.mLayout.removeView((View) this.mModel.getPlayer(i2).getView());
        }
        try {
            this.mModel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.onDestroyView();
    }

    public void onResume() {
        ArrayList<PlayerInstance> playerList = this.mModel.getPlayerList();
        if (playerList != null && playerList.size() > 0) {
            Optional.ofNullable(playerList.get(this.mModel.getCurrentPosition())).ifPresent(new C0366a(18));
            if (this.mModel.getCurrentPosition() + 1 < playerList.size()) {
                Optional.ofNullable(playerList.get(this.mModel.getCurrentPosition() + 1)).ifPresent(new C0366a(19));
            }
        }
        super.onResume();
    }

    public void onVideoCompleted(int i2) {
        int i7 = i2 + 1;
        if (this.mModel.getPlayerList().size() <= i7) {
            a.k(i2, "onVideoCompleted end:", TAG);
        } else if (i2 == this.mModel.getCurrentPosition()) {
            ArrayList<PlayerInstance> playerList = this.mModel.getPlayerList();
            String str = TAG;
            a.k(i2, "onVideoCompleted :", str);
            if (this.mModel.getCurrentPosition() + 1 < this.mModel.getSubItemCount()) {
                Log.d(str, "onVideoCompleted : resume " + i2 + 1);
                playerList.get(this.mModel.getCurrentPosition() + 1).getView().resumeVideo();
            }
            if (this.mModel.getCurrentPosition() + 2 < this.mModel.getSubItemCount()) {
                Log.d(str, "onVideoCompleted : open " + i2 + 2);
                playerList.get(this.mModel.getCurrentPosition() + 2).open();
            }
            this.mModel.setCurrentPosition(i7);
        }
    }

    public void onVideoPlay(int i2) {
        String str = TAG;
        a.k(i2, "onVideoPlay : ", str);
        IMediaPlayerView view = this.mModel.getPlayerList().get(i2).getView();
        int i7 = 0;
        if (this.mModel.getCurrentPosition() != i2) {
            view.setAlpha(0.0f);
            view.pauseVideo();
            if (i2 > 0) {
                i7 = getDiff(this.mModel.getPlayer(i2 - 1).getView(), view);
                view.seekTo(i7 + 200);
            }
            StringBuilder h5 = a.h(i2, i7, "onVideoPlay : pause ", "/ SEEK : ", " / V: ");
            h5.append(view.isVisible());
            h5.append(" / A :");
            h5.append(view.getAlpha());
            Log.d(str, h5.toString());
            return;
        }
        view.setVisibility(0);
        view.setAlpha(1.0f);
        Log.d(str, "onVideoPlay : " + i2 + " / V: " + view.isVisible() + " / A :" + view.getAlpha());
        if (i2 > 0) {
            int i8 = i2 - 1;
            this.mModel.getPlayerList().get(i8).getView().setAlpha(0.0f);
            this.mModel.getPlayerList().get(i8).close();
        }
    }

    public void onVideoPrepared(int i2) {
        this.mModel.getPlayer(i2).getDuration();
    }

    public void onViewCreated(View view, Bundle bundle) {
        Object[] objArr;
        this.mLayout = (RelativeLayout) view.findViewById(R$id.content_layout);
        ViewStub viewStub = (ViewStub) view.findViewById(R$id.media_view_stub);
        if (viewStub != null) {
            this.mMediaPlayerView = (IMediaPlayerView) viewStub.inflate();
        }
        String str = null;
        Intent intent = (Intent) Optional.ofNullable(getActivity()).map(new C0384t(16)).orElse((Object) null);
        if (intent != null) {
            str = intent.getStringExtra("data-key");
        }
        if (str != null && (objArr = (Object[]) Blackboard.getApplicationInstance().pop(str)) != null && objArr.length > 1) {
            this.mModel.setMediaData((MediaData) objArr[0], (MediaItem) objArr[1]);
            IMediaPlayerView[] prepareView = prepareView(this.mModel.getSubItemCount());
            for (int i2 = 0; i2 < prepareView.length; i2++) {
                MergeMotionPhotoModel mergeMotionPhotoModel = this.mModel;
                mergeMotionPhotoModel.addPlayer(new PlayerInstance(i2, mergeMotionPhotoModel.getSubItem(i2), prepareView[i2], this));
            }
        }
    }
}
