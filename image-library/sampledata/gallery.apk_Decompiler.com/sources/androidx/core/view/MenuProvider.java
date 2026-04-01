package androidx.core.view;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface MenuProvider {
    void onCreateMenu(Menu menu, MenuInflater menuInflater);

    boolean onMenuItemSelected(MenuItem menuItem);

    void onMenuClosed(Menu menu) {
    }

    void onPrepareMenu(Menu menu) {
    }
}
