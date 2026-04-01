package com.samsung.android.gallery.image360.activity.options;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.samsung.android.gallery.image360.R$id;
import com.samsung.android.gallery.image360.R$layout;
import com.samsung.android.gallery.image360.R$string;
import com.samsung.android.gallery.image360.activity.abstraction.IActivityView;
import com.samsung.android.gallery.image360.systemui.BaseSystemUi;
import com.samsung.android.gallery.image360.systemui.SystemUiBos;
import com.samsung.android.gallery.image360.systemui.SystemUiLegacy;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import k2.q;
import t8.e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PlayBackOptionsFragment extends Fragment implements CompoundButton.OnCheckedChangeListener, View.OnClickListener, q {
    private BottomNavigationView mBottomNavigationView;
    private Bitmap mFrontBitmap;
    private ImageView mFrontImageView;
    private int mOrientation;
    private final PlayBackOptionsPresenter mPresenter;
    private RadioButton mRadioButtonFront;
    private RadioButton mRadioButtonRear;
    private Bitmap mRearBitmap;
    private ImageView mRearImageView;
    private final BaseSystemUi mSystemUi;
    private View mView;

    public PlayBackOptionsFragment() {
        BaseSystemUi baseSystemUi;
        if (SdkConfig.atLeast(SdkConfig.GED.B)) {
            baseSystemUi = new SystemUiBos();
        } else {
            baseSystemUi = new SystemUiLegacy();
        }
        this.mSystemUi = baseSystemUi;
        this.mPresenter = new PlayBackOptionsPresenter();
        Log.d("PlayBackOptionsFragment", "construct : " + hashCode());
    }

    private void handleConfigurationChange(Configuration configuration) {
        if (configuration == null) {
            Log.e("PlayBackOptionsFragment", "handleConfigurationChange null Configuration");
        } else {
            this.mSystemUi.setOrientation(configuration.orientation);
        }
    }

    private void initBar() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            this.mSystemUi.showNavigationBar(activity);
            this.mSystemUi.removeFullScreenFlags(activity);
            this.mSystemUi.updateBarColors(activity);
        }
    }

    private void initBottomNavigation(View view) {
        BottomNavigationView bottomNavigationView = (BottomNavigationView) view.findViewById(R$id.edit_bottom_navigation);
        this.mBottomNavigationView = bottomNavigationView;
        bottomNavigationView.setOnItemSelectedListener(this);
        updateSaveButton(this.mPresenter.isSaveButtonEnabled());
    }

    private void initImageViews(View view) {
        ImageView imageView = (ImageView) view.findViewById(R$id.rear_image_view);
        this.mRearImageView = imageView;
        imageView.setOnClickListener(this);
        ImageView imageView2 = (ImageView) view.findViewById(R$id.front_image_view);
        this.mFrontImageView = imageView2;
        imageView2.setOnClickListener(this);
        setBitmaps();
    }

    private void initRadioButtons(View view) {
        RadioButton radioButton = (RadioButton) view.findViewById(R$id.rear_radio_button);
        this.mRadioButtonRear = radioButton;
        radioButton.setOnCheckedChangeListener(this);
        RadioButton radioButton2 = (RadioButton) view.findViewById(R$id.front_radio_button);
        this.mRadioButtonFront = radioButton2;
        radioButton2.setOnCheckedChangeListener(this);
        if (this.mPresenter.getCurrentDirection() == 1) {
            this.mRadioButtonFront.setChecked(true);
            this.mRadioButtonRear.requestFocus();
            return;
        }
        this.mRadioButtonRear.setChecked(true);
        this.mRadioButtonFront.requestFocus();
    }

    private void initTextView(View view) {
        ((TextView) view.findViewById(R$id.description)).setMovementMethod(new ScrollingMovementMethod());
    }

    private void initViews(View view) {
        initImageViews(view);
        initRadioButtons(view);
        initBottomNavigation(view);
        initTextView(view);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setBitmaps$0() {
        ImageView imageView = this.mFrontImageView;
        if (imageView != null && this.mRearImageView != null) {
            imageView.setImageBitmap(this.mFrontBitmap);
            this.mRearImageView.setImageBitmap(this.mRearBitmap);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setBitmaps$1(Resources resources) {
        if (this.mFrontImageView != null && this.mRearImageView != null) {
            if (this.mFrontBitmap == null) {
                Bitmap[] decodedBitmaps = this.mPresenter.getDecodedBitmaps();
                if (decodedBitmaps != null) {
                    Bitmap[] resizeBitmaps = this.mPresenter.resizeBitmaps(decodedBitmaps, resources);
                    this.mFrontBitmap = resizeBitmaps[0];
                    this.mRearBitmap = resizeBitmaps[1];
                } else {
                    Bitmap brokenBitmap = this.mPresenter.getBrokenBitmap(getContext());
                    this.mFrontBitmap = brokenBitmap;
                    this.mRearBitmap = brokenBitmap;
                }
            }
            ThreadUtil.postOnUiThread(new e(18, this));
        }
    }

    private void recycleBitmaps() {
        if (this.mFrontBitmap != null && !ThumbnailLoader.getInstance().isReplacedThumbnail(this.mFrontBitmap) && !this.mFrontBitmap.isRecycled()) {
            BitmapUtils.putBitmap(this.mFrontBitmap);
            this.mFrontBitmap = null;
        }
        if (this.mRearBitmap != null && !ThumbnailLoader.getInstance().isReplacedThumbnail(this.mRearBitmap) && !this.mRearBitmap.isRecycled()) {
            BitmapUtils.putBitmap(this.mRearBitmap);
            this.mRearBitmap = null;
        }
    }

    private void setBitmaps() {
        SimpleThreadPool.getInstance().execute(new q6.e(25, this, getResources()));
    }

    private void setLayout(LayoutInflater layoutInflater) {
        ViewGroup viewGroup = (ViewGroup) this.mView.findViewById(R$id.main_layout);
        ViewUtils.removeAllViews(viewGroup);
        View inflate = layoutInflater.inflate(R$layout.gallery360viewer_playback, viewGroup, false);
        viewGroup.addView(inflate);
        initViews(inflate);
    }

    private void updateSaveButton(boolean z) {
        BottomNavigationView bottomNavigationView = this.mBottomNavigationView;
        if (bottomNavigationView != null) {
            int i2 = R$id.menu_playback_save;
            View findViewById = bottomNavigationView.findViewById(i2);
            MenuItem findItem = this.mBottomNavigationView.getMenu().findItem(i2);
            if (z) {
                findViewById.setAlpha(1.0f);
            } else {
                findViewById.setAlpha(0.4f);
            }
            findItem.setEnabled(z);
        }
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (this.mRadioButtonRear != null && this.mRadioButtonFront != null) {
            if (compoundButton.getId() == R$id.rear_radio_button) {
                if (z) {
                    this.mPresenter.setCurrentDirection(0, getResources().getString(R$string.gallery360viewer_playback_rear));
                }
                this.mRadioButtonRear.setChecked(z);
                this.mRadioButtonFront.setChecked(!z);
            } else {
                if (z) {
                    this.mPresenter.setCurrentDirection(1, getResources().getString(R$string.gallery360viewer_playback_front));
                }
                this.mRadioButtonFront.setChecked(z);
                this.mRadioButtonRear.setChecked(!z);
            }
            updateSaveButton(this.mPresenter.isSaveButtonEnabled());
        }
    }

    public void onClick(View view) {
        if (this.mRadioButtonRear != null && this.mRadioButtonFront != null) {
            int id = view.getId();
            if (id == R$id.rear_image_view) {
                if (!this.mRadioButtonRear.isChecked()) {
                    this.mRadioButtonRear.setChecked(true);
                }
            } else if (id == R$id.front_image_view && !this.mRadioButtonFront.isChecked()) {
                this.mRadioButtonFront.setChecked(true);
            }
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        int i2 = this.mOrientation;
        int i7 = configuration.orientation;
        if (i2 != i7) {
            this.mOrientation = i7;
            if (getActivity() != null) {
                setLayout(getActivity().getLayoutInflater());
            }
        }
        handleConfigurationChange(configuration);
        initBar();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mPresenter.loadSavedInstanceState(bundle);
        this.mOrientation = getResources().getConfiguration().orientation;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        handleConfigurationChange(getResources().getConfiguration());
        this.mView = layoutInflater.inflate(R$layout.gallery360viewer_playback_main, viewGroup, false);
        setLayout(layoutInflater);
        return this.mView;
    }

    public void onDestroy() {
        super.onDestroy();
        recycleBitmaps();
    }

    public boolean onNavigationItemSelected(MenuItem menuItem) {
        if (getActivity() == null) {
            Log.e("PlayBackOptionsFragment", "fail back");
            return false;
        } else if (menuItem.getItemId() == R$id.menu_playback_cancel) {
            this.mPresenter.onCancelClicked((IActivityView) getActivity());
            return true;
        } else if (menuItem.getItemId() != R$id.menu_playback_save) {
            return true;
        } else {
            this.mPresenter.onSaveClicked((IActivityView) getActivity());
            return true;
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        this.mPresenter.onSaveInstanceState(bundle);
        super.onSaveInstanceState(bundle);
    }

    public void setInitValues(String str, int i2) {
        this.mPresenter.setInitValues(str, i2, i2);
    }
}
