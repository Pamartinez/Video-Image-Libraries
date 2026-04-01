package androidx.core.widget;

import androidx.core.widget.SeslGoToTopController;
import androidx.core.widget.SeslGoToTopControllerBuilder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SeslGoToTopControllerBuilder<T extends SeslGoToTopController, B extends SeslGoToTopControllerBuilder<T, B>> {
    protected SeslGoToTopConfig config;
    protected SeslGoToTopController.Host host;

    public B setConfig(SeslGoToTopConfig seslGoToTopConfig) {
        this.config = seslGoToTopConfig;
        return this;
    }

    public B setHost(SeslGoToTopController.Host host2) {
        this.host = host2;
        return this;
    }

    public void validate() {
        if (this.host == null) {
            throw new IllegalStateException("host required");
        } else if (this.config == null) {
            throw new IllegalStateException("config required");
        }
    }
}
