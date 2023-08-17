package net.minecraft.client.resources;

import net.minecraft.util.ResourceLocation;

import java.io.File;

public class ResourceIndexFolder extends ResourceIndex
{
    private final File baseDir;

    public ResourceIndexFolder(File folder)
    {
        this.baseDir = folder;
    }

    public File getFile(ResourceLocation location)
    {
        return new File(this.baseDir, location.toString().replace(':', '/'));
    }

    public File getPackMcmeta()
    {
        return new File(this.baseDir, "pack.mcmeta");
    }
}
