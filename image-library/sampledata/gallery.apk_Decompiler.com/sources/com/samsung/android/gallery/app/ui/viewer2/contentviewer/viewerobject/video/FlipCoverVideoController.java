package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video;

import Ab.a;
import android.view.View;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FlipCoverVideoController extends VideoController {
    View mVideoPlayControlView;

    public void setClickTouchListener() {
        ViewUtils.setOnClickListener(this.mVideoPlayControlView, new a(27, this));
    }

    public void setControllerBinding(View view) {
        this.mVideoPlayControlView = view.findViewById(R.id.flip_cover_video_play_control_view);
        super.setControllerBinding(view);
    }

    public void updateTouchArea() {
        ViewUtils.setTouchAreaComposite(this.mVideoPlayControlView, R.dimen.decor_button_touch_area);
    }
}
