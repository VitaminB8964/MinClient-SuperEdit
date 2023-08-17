package net.minecraft.client.resources;

import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface IResourceManager
{
    Set<String> getResourceDomains();

    @Nullable
    IResource getResource(ResourceLocation location) throws IOException;

    List<IResource> getAllResources(ResourceLocation location) throws IOException;
}
