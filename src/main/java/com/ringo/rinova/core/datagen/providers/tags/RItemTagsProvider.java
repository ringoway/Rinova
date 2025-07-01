package com.ringo.rinova.core.datagen.providers.tags;

import com.ringo.rinova.RinovaMod;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class RItemTagsProvider extends ItemTagsProvider {
    public RItemTagsProvider(PackOutput output,
                             CompletableFuture<HolderLookup.Provider> lookupProvider,
                             CompletableFuture<TagLookup<Block>> tagLookupBlock, // Сюда передается contentsGetter()
                             @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, tagLookupBlock, RinovaMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

    }
}
