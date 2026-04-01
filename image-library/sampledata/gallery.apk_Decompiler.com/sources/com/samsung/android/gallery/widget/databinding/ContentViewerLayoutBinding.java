package com.samsung.android.gallery.widget.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.RelativeLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.R$layout;
import com.samsung.android.gallery.widget.abstraction.ViewerContentLayout;
import com.samsung.android.gallery.widget.photoview.PhotoViewCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ContentViewerLayoutBinding implements ViewBinding {
    public final ViewStub aiEditLayout;
    public final ViewStub aiProcessingEffectLayout;
    public final ViewStub audioEraserIconStub;
    public final ConstraintLayout bottomCenterDecorLayout;
    public final ConstraintLayout bottomDecorLayout;
    public final RelativeLayout contentContainer;
    public final ViewerContentLayout contentContainerForTouch;
    public final ViewStub contentsTypeIcon;
    public final ConstraintLayout decorLayout;
    public final ViewStub directorsViewIcon;
    public final ViewStub dlnaButton;
    public final ViewStub dualcaptureOptions;
    public final ConstraintLayout fixedTopDecorLayout;
    public final ViewStub flipCoverDecorLayout;
    public final ViewStub groupCountStub;
    public final ViewStub highlightFrcLayout;
    public final ViewStub instantSlowMoSaveClipStub;
    public final ViewStub motionPhotoViewMode;
    public final ViewStub objectCaptureLayout;
    public final PhotoViewCompat photoView;
    public final ViewStub playAudioIconStub;
    public final ViewStub quickCropStub;
    private final CoordinatorLayout rootView;
    public final ViewStub shotModeButton;
    public final ViewStub shotModeTwoButton;
    public final ViewStub tagLayout;
    public final ViewStub textExtractionButton;
    public final ViewStub textExtractionLayout;
    public final ConstraintLayout topCenterDecorLayout;
    public final ViewStub videoController;
    public final ViewStub videoMirroringUi;
    public final ViewStub videoSeekController;
    public final ViewStub videoViewStub;
    public final ViewStub viewerDebug;
    public final CoordinatorLayout viewerLayout;
    public final ViewStub zoomInOutLayout;

    private ContentViewerLayoutBinding(CoordinatorLayout coordinatorLayout, ViewStub viewStub, ViewStub viewStub2, ViewStub viewStub3, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, RelativeLayout relativeLayout, ViewerContentLayout viewerContentLayout, ViewStub viewStub4, ConstraintLayout constraintLayout3, ViewStub viewStub5, ViewStub viewStub6, ViewStub viewStub7, ConstraintLayout constraintLayout4, ViewStub viewStub8, ViewStub viewStub9, ViewStub viewStub10, ViewStub viewStub11, ViewStub viewStub12, ViewStub viewStub13, PhotoViewCompat photoViewCompat, ViewStub viewStub14, ViewStub viewStub15, ViewStub viewStub16, ViewStub viewStub17, ViewStub viewStub18, ViewStub viewStub19, ViewStub viewStub20, ConstraintLayout constraintLayout5, ViewStub viewStub21, ViewStub viewStub22, ViewStub viewStub23, ViewStub viewStub24, ViewStub viewStub25, CoordinatorLayout coordinatorLayout2, ViewStub viewStub26) {
        this.rootView = coordinatorLayout;
        this.aiEditLayout = viewStub;
        this.aiProcessingEffectLayout = viewStub2;
        this.audioEraserIconStub = viewStub3;
        this.bottomCenterDecorLayout = constraintLayout;
        this.bottomDecorLayout = constraintLayout2;
        this.contentContainer = relativeLayout;
        this.contentContainerForTouch = viewerContentLayout;
        this.contentsTypeIcon = viewStub4;
        this.decorLayout = constraintLayout3;
        this.directorsViewIcon = viewStub5;
        this.dlnaButton = viewStub6;
        this.dualcaptureOptions = viewStub7;
        this.fixedTopDecorLayout = constraintLayout4;
        this.flipCoverDecorLayout = viewStub8;
        this.groupCountStub = viewStub9;
        this.highlightFrcLayout = viewStub10;
        this.instantSlowMoSaveClipStub = viewStub11;
        this.motionPhotoViewMode = viewStub12;
        this.objectCaptureLayout = viewStub13;
        this.photoView = photoViewCompat;
        this.playAudioIconStub = viewStub14;
        this.quickCropStub = viewStub15;
        this.shotModeButton = viewStub16;
        this.shotModeTwoButton = viewStub17;
        this.tagLayout = viewStub18;
        this.textExtractionButton = viewStub19;
        this.textExtractionLayout = viewStub20;
        this.topCenterDecorLayout = constraintLayout5;
        this.videoController = viewStub21;
        this.videoMirroringUi = viewStub22;
        this.videoSeekController = viewStub23;
        this.videoViewStub = viewStub24;
        this.viewerDebug = viewStub25;
        this.viewerLayout = coordinatorLayout2;
        this.zoomInOutLayout = viewStub26;
    }

    public static ContentViewerLayoutBinding bind(View view) {
        View view2 = view;
        int i2 = R$id.ai_edit_layout;
        ViewStub viewStub = (ViewStub) ViewBindings.findChildViewById(view2, i2);
        if (viewStub != null) {
            i2 = R$id.ai_processing_effect_layout;
            ViewStub viewStub2 = (ViewStub) ViewBindings.findChildViewById(view2, i2);
            if (viewStub2 != null) {
                i2 = R$id.audio_eraser_icon_stub;
                ViewStub viewStub3 = (ViewStub) ViewBindings.findChildViewById(view2, i2);
                if (viewStub3 != null) {
                    i2 = R$id.bottom_center_decor_layout;
                    ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view2, i2);
                    if (constraintLayout != null) {
                        i2 = R$id.bottom_decor_layout;
                        ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(view2, i2);
                        if (constraintLayout2 != null) {
                            i2 = R$id.content_container;
                            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view2, i2);
                            if (relativeLayout != null) {
                                i2 = R$id.content_container_for_touch;
                                ViewerContentLayout viewerContentLayout = (ViewerContentLayout) ViewBindings.findChildViewById(view2, i2);
                                if (viewerContentLayout != null) {
                                    i2 = R$id.contents_type_icon;
                                    ViewStub viewStub4 = (ViewStub) ViewBindings.findChildViewById(view2, i2);
                                    if (viewStub4 != null) {
                                        i2 = R$id.decor_layout;
                                        ConstraintLayout constraintLayout3 = (ConstraintLayout) ViewBindings.findChildViewById(view2, i2);
                                        if (constraintLayout3 != null) {
                                            i2 = R$id.directors_view_icon;
                                            ViewStub viewStub5 = (ViewStub) ViewBindings.findChildViewById(view2, i2);
                                            if (viewStub5 != null) {
                                                i2 = R$id.dlna_button;
                                                ViewStub viewStub6 = (ViewStub) ViewBindings.findChildViewById(view2, i2);
                                                if (viewStub6 != null) {
                                                    i2 = R$id.dualcapture_options;
                                                    ViewStub viewStub7 = (ViewStub) ViewBindings.findChildViewById(view2, i2);
                                                    if (viewStub7 != null) {
                                                        i2 = R$id.fixed_top_decor_layout;
                                                        ConstraintLayout constraintLayout4 = (ConstraintLayout) ViewBindings.findChildViewById(view2, i2);
                                                        if (constraintLayout4 != null) {
                                                            i2 = R$id.flip_cover_decor_layout;
                                                            ViewStub viewStub8 = (ViewStub) ViewBindings.findChildViewById(view2, i2);
                                                            if (viewStub8 != null) {
                                                                i2 = R$id.group_count_stub;
                                                                ViewStub viewStub9 = (ViewStub) ViewBindings.findChildViewById(view2, i2);
                                                                if (viewStub9 != null) {
                                                                    i2 = R$id.highlight_frc_layout;
                                                                    ViewStub viewStub10 = (ViewStub) ViewBindings.findChildViewById(view2, i2);
                                                                    if (viewStub10 != null) {
                                                                        i2 = R$id.instant_slow_mo_save_clip_stub;
                                                                        ViewStub viewStub11 = (ViewStub) ViewBindings.findChildViewById(view2, i2);
                                                                        if (viewStub11 != null) {
                                                                            i2 = R$id.motion_photo_view_mode;
                                                                            ViewStub viewStub12 = (ViewStub) ViewBindings.findChildViewById(view2, i2);
                                                                            if (viewStub12 != null) {
                                                                                i2 = R$id.object_capture_layout;
                                                                                ViewStub viewStub13 = (ViewStub) ViewBindings.findChildViewById(view2, i2);
                                                                                if (viewStub13 != null) {
                                                                                    i2 = R$id.photo_view;
                                                                                    PhotoViewCompat photoViewCompat = (PhotoViewCompat) ViewBindings.findChildViewById(view2, i2);
                                                                                    if (photoViewCompat != null) {
                                                                                        i2 = R$id.play_audio_icon_stub;
                                                                                        ViewStub viewStub14 = (ViewStub) ViewBindings.findChildViewById(view2, i2);
                                                                                        if (viewStub14 != null) {
                                                                                            i2 = R$id.quick_crop_stub;
                                                                                            ViewStub viewStub15 = (ViewStub) ViewBindings.findChildViewById(view2, i2);
                                                                                            if (viewStub15 != null) {
                                                                                                i2 = R$id.shot_mode_button;
                                                                                                ViewStub viewStub16 = (ViewStub) ViewBindings.findChildViewById(view2, i2);
                                                                                                if (viewStub16 != null) {
                                                                                                    i2 = R$id.shot_mode_two_button;
                                                                                                    ViewStub viewStub17 = (ViewStub) ViewBindings.findChildViewById(view2, i2);
                                                                                                    if (viewStub17 != null) {
                                                                                                        i2 = R$id.tag_layout;
                                                                                                        ViewStub viewStub18 = (ViewStub) ViewBindings.findChildViewById(view2, i2);
                                                                                                        if (viewStub18 != null) {
                                                                                                            i2 = R$id.text_extraction_button;
                                                                                                            ViewStub viewStub19 = (ViewStub) ViewBindings.findChildViewById(view2, i2);
                                                                                                            if (viewStub19 != null) {
                                                                                                                i2 = R$id.text_extraction_layout;
                                                                                                                ViewStub viewStub20 = (ViewStub) ViewBindings.findChildViewById(view2, i2);
                                                                                                                if (viewStub20 != null) {
                                                                                                                    i2 = R$id.top_center_decor_layout;
                                                                                                                    ConstraintLayout constraintLayout5 = (ConstraintLayout) ViewBindings.findChildViewById(view2, i2);
                                                                                                                    if (constraintLayout5 != null) {
                                                                                                                        i2 = R$id.video_controller;
                                                                                                                        ViewStub viewStub21 = (ViewStub) ViewBindings.findChildViewById(view2, i2);
                                                                                                                        if (viewStub21 != null) {
                                                                                                                            i2 = R$id.video_mirroring_ui;
                                                                                                                            ViewStub viewStub22 = (ViewStub) ViewBindings.findChildViewById(view2, i2);
                                                                                                                            if (viewStub22 != null) {
                                                                                                                                i2 = R$id.video_seek_controller;
                                                                                                                                ViewStub viewStub23 = (ViewStub) ViewBindings.findChildViewById(view2, i2);
                                                                                                                                if (viewStub23 != null) {
                                                                                                                                    i2 = R$id.video_view_stub;
                                                                                                                                    ViewStub viewStub24 = (ViewStub) ViewBindings.findChildViewById(view2, i2);
                                                                                                                                    if (viewStub24 != null) {
                                                                                                                                        i2 = R$id.viewer_debug;
                                                                                                                                        ViewStub viewStub25 = (ViewStub) ViewBindings.findChildViewById(view2, i2);
                                                                                                                                        if (viewStub25 != null) {
                                                                                                                                            CoordinatorLayout coordinatorLayout = (CoordinatorLayout) view2;
                                                                                                                                            i2 = R$id.zoom_in_out_layout;
                                                                                                                                            ViewStub viewStub26 = (ViewStub) ViewBindings.findChildViewById(view2, i2);
                                                                                                                                            if (viewStub26 != null) {
                                                                                                                                                return new ContentViewerLayoutBinding(coordinatorLayout, viewStub, viewStub2, viewStub3, constraintLayout, constraintLayout2, relativeLayout, viewerContentLayout, viewStub4, constraintLayout3, viewStub5, viewStub6, viewStub7, constraintLayout4, viewStub8, viewStub9, viewStub10, viewStub11, viewStub12, viewStub13, photoViewCompat, viewStub14, viewStub15, viewStub16, viewStub17, viewStub18, viewStub19, viewStub20, constraintLayout5, viewStub21, viewStub22, viewStub23, viewStub24, viewStub25, coordinatorLayout, viewStub26);
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                }
                                                                                                                            }
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view2.getResources().getResourceName(i2)));
    }

    public static ContentViewerLayoutBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R$layout.content_viewer_layout, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public CoordinatorLayout getRoot() {
        return this.rootView;
    }
}
