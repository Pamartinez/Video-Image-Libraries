package com.samsung.android.sum.core.plugin;

import com.samsung.android.sum.core.functional.BufferProcessor;
import com.samsung.android.sum.core.functional.ExecuteDelegator;
import com.samsung.android.sum.core.functional.ModelLoader;
import com.samsung.android.sum.core.functional.ModelPathLoader;
import com.samsung.android.sum.core.functional.ModelSelector;
import com.samsung.android.sum.core.types.nn.NNFileDescriptor;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class NNPlugin extends PluginFixture<NNPlugin> {
    private ExecuteDelegator executeDelegator;
    private ModelLoader<NNFileDescriptor> modelLoader;
    private ModelPathLoader modelPathLoader;
    private ModelSelector modelSelector;
    private BufferProcessor postExecutor;
    private BufferProcessor preExecutor;

    public NNPlugin(Plugin<NNPlugin> plugin) {
        super(plugin);
    }

    public ExecuteDelegator getExecuteDelegator() {
        return this.executeDelegator;
    }

    public ModelLoader<NNFileDescriptor> getModelLoader() {
        return this.modelLoader;
    }

    public ModelPathLoader getModelPathLoader() {
        return this.modelPathLoader;
    }

    public ModelSelector getModelSelector() {
        return this.modelSelector;
    }

    public BufferProcessor getPostExecutor() {
        return this.postExecutor;
    }

    public Optional<BufferProcessor> getPreExecutor() {
        return Optional.ofNullable(this.preExecutor);
    }

    public boolean isValid() {
        if (this.modelLoader == null && this.modelPathLoader == null) {
            return false;
        }
        return true;
    }

    public void setExecuteDelegator(ExecuteDelegator executeDelegator2) {
        this.executeDelegator = executeDelegator2;
    }

    public void setModelLoader(ModelLoader<NNFileDescriptor> modelLoader2) {
        this.modelLoader = modelLoader2;
    }

    public void setModelPathLoader(ModelPathLoader modelPathLoader2) {
        this.modelPathLoader = modelPathLoader2;
    }

    public void setModelSelector(ModelSelector modelSelector2) {
        this.modelSelector = modelSelector2;
    }

    public void setPostExecutor(BufferProcessor bufferProcessor) {
        this.postExecutor = bufferProcessor;
    }

    public void setPreExecutor(BufferProcessor bufferProcessor) {
        this.preExecutor = bufferProcessor;
    }
}
