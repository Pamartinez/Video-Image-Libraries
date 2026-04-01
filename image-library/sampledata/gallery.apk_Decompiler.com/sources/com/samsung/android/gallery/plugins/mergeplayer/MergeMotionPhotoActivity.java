package com.samsung.android.gallery.plugins.mergeplayer;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.samsung.android.gallery.plugins.R$id;
import com.samsung.android.gallery.plugins.R$layout;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MergeMotionPhotoActivity extends AppCompatActivity {
    public MergeMotionPhotoActivity() {
        super(R$layout.merge_motion_photo_activity);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).add(R$id.content, (Class<? extends Fragment>) MergeMotionPhotoFragment.class, (Bundle) null).commit();
        }
    }
}
