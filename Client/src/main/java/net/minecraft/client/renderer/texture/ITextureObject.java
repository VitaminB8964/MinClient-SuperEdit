package net.minecraft.client.renderer.texture;

import net.minecraft.client.resources.IResourceManager;
import net.optifine.shaders.MultiTexID;

import java.io.IOException;

public interface ITextureObject
{
    void setBlurMipmap(boolean blurIn, boolean mipmapIn);

    void restoreLastBlurMipmap();

    void loadTexture(IResourceManager resourceManager) throws IOException;

    int getGlTextureId();

    MultiTexID getMultiTexID();
}
