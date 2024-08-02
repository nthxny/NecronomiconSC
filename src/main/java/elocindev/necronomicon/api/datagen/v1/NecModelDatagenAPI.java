package elocindev.necronomicon.api.datagen.v1;

//#if FABRIC==1

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.blockstates.BlockStateGenerator;
import net.minecraft.data.models.blockstates.Condition;
import net.minecraft.data.models.blockstates.MultiPartGenerator;
import net.minecraft.data.models.blockstates.Variant;
import net.minecraft.data.models.blockstates.VariantProperties;
import net.minecraft.data.models.model.ModelLocationUtils;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import com.google.gson.JsonElement;

import elocindev.necronomicon.datagen.ModelGenerator;


/**
 * A class containing methods to make model generation easier using Fabric's Data Generator.
 * 
 * @platform    Fabric
 * @side        Client
 * @minecraft   >= 1.19.2
 * 
 * @deprecated  Use {@link NecDatagenAPI.Model} instead.
 * 
 * @author  ElocinDev
 * @since   1.0.0
 */
@Deprecated(since = "1.1.0", forRemoval = true)
public class NecModelDatagenAPI {
    //#if FABRIC==1

    /**
     *  Registers a basic generated item model.
     * 
     * @param item              The item to register the model for.
     * @param modelCollector    The model collector to register the model to.
     * 
     * @platform Fabric
     * 
     * @author ElocinDev
     * @since 1.0.3
     */
    public static void makeItem(Item item, BiConsumer<ResourceLocation, Supplier<JsonElement>> modelCollector) {
        if (item != Items.AIR) {
            ModelTemplates.FLAT_ITEM.create(ModelLocationUtils.getModelLocation(item), TextureMapping.layer0(item), modelCollector);
        }
    }

    /**
     *  Registers a block's BlockItem model.
     * 
     * @param block             The block to register the BlockItem model for.
     * @param modelCollector    The model collector to register the model to.
     * 
     * @see NecModelDatagenAPI#makeItem
     * 
     * @platform Fabric
     * 
     * @author ElocinDev
     * @since 1.0.3
     */
    public static void makeItem(Block block, BiConsumer<ResourceLocation, Supplier<JsonElement>> modelCollector) {
        makeItem(block.asItem(), modelCollector);
    }

    /**
     *  Registers a basic item model for an array of items.
     * 
     * @param items                 An array that holds all items to be registered.
     * @param modelCollector        The model collector to register the model to.
     * 
     * @platform Fabric
     * 
     * @author ElocinDev
     * @since 1.0.3
     */
    public static void makeItems(Item[] items, BiConsumer<ResourceLocation, Supplier<JsonElement>> modelCollector) {
        for (Item item : items) {
            makeItem(item, modelCollector);
        }
    }

    /**
     *  Registers a basic blockitem model for an array of blocks.
     * 
     * @param blocks                An array that holds all blocks to register the item models.
     * @param modelCollector        The model collector to register the model to.
     * 
     * @platform Fabric
     * 
     * @author ElocinDev
     * @since 1.0.3
     */
    public static void makeItems(Block[] blocks, BiConsumer<ResourceLocation, Supplier<JsonElement>> modelCollector) {
        for (Block block : blocks) {
            makeItem(block, modelCollector);
        }
    }

    /**
     *  Registers a series of cube_all models for blocks inside an array.
     * 
     * @param generator     The model generator to register the blocks to.
     * @param blocks        An array of blocks to register cube_all models for.
     * 
     * @platform Fabric
     * 
     * @author ElocinDev
     * @since 1.0.0
     */
    public static void makeCubeAllBlocks(BlockModelGenerators generator, Block[] blocks) {
        for (Block block : blocks) {
            generator.createTrivialCube(block);
        }
    }

    /**
     * Registers a whole woodset's model generation.
     * 
     * @param generator     The model generator to register the woodset to.
     * @param planks        The planks block of the woodset.
     * @param door          The door block of the woodset.
     * @param trapdoor      The trapdoor block of the woodset.
     * @param slab          The slab block of the woodset.
     * @param stairs        The stairs block of the woodset.
     * @param button        The button block of the woodset.
     * @param fence         The fence block of the woodset.
     * @param fenceGate     The fence gate block of the woodset.
     * 
     * @see NecModelDatagenAPI#makeSlab
     * @see NecModelDatagenAPI#makeStairs
     * 
     * @platform Fabric
     * 
     * @author ElocinDev
     * @since 1.0.0
     */
    public static void makeWoodset(BlockModelGenerators generator, 
        Block planks, Block door, Block trapdoor, Block slab, Block stairs, Block button, Block fence, Block fenceGate) {

        ModelGenerator.registerWoodset(generator, planks, door, trapdoor, slab, stairs, button, fence, fenceGate);
    }

    /**
     * Registers a slab block model generation.
     * 
     * @param slabBlock         The slab block to register.
     * @param parentBlock       The parent block of the slab. (Full block)
     * @param modelCollector    The model collector to register the model to.
     * @param stateCollector    The block state collector to register the block state to.
     * 
     * @see NecModelDatagenAPI#makeSlabStair
     * 
     * @platform Fabric
     * 
     * @author ElocinDev
     * @since 1.0.0
     */
    public static void makeSlab(Block slabBlock, Block parentBlock, BiConsumer<ResourceLocation, Supplier<JsonElement>> modelCollector, Consumer<BlockStateGenerator> stateCollector) {
        ModelGenerator.registerSlab(slabBlock, parentBlock, modelCollector, stateCollector);
    }

    /**
     * Registers a stairs block model generation.
     * 
     * @param stairBlock        The stairs block to register.
     * @param parentBlock       The parent block of the stairs. (Full block)
     * @param modelCollector    The model collector to register the model to.
     * @param stateCollector    The block state collector to register the block state to.
     * 
     * @see NecModelDatagenAPI#makeSlabStair
     *
     * @platform Fabric
     * 
     * @author ElocinDev
     * @since 1.0.0
     */
    public static void makeStair(Block stairBlock, Block parentBlock, BiConsumer<ResourceLocation, Supplier<JsonElement>> modelCollector, Consumer<BlockStateGenerator> stateCollector) {
        ModelGenerator.registerStairs(stairBlock, parentBlock, modelCollector, stateCollector);
    }

    /**
     * Makes models for a slab and stair block from a parent block.
     * 
     * @param generator     The model generator to register the blocks to.
     * @param slab          The slab block to register.
     * @param stairs        The stairs block to register.
     * @param parentBlock   The parent block of the slab and stairs. (Full block)
     * 
     * @see NecModelDatagenAPI#makeSlab
     * @see NecModelDatagenAPI#makeStair
     * 
     * @platform Fabric
     * 
     * @author ElocinDev
     * @since 1.0.0
     */
    public static void makeSlabStair(BlockModelGenerators generator, Block slab, Block stairs, Block parentBlock) {
        ModelGenerator.registerSlab(slab, parentBlock, generator.modelOutput, generator.blockStateOutput);
        ModelGenerator.registerStairs(stairs, parentBlock, generator.modelOutput, generator.blockStateOutput);
    }


    /**
     * Makes models and blockstates for a bar block.
     * 
     * @param barBlock          The bar block to register.
     * @param modelCollector    The model collector to register the model to.
     * @param stateCollector    The blockState collector to register the block state to.
     * 
     * @platform Fabric
     * 
     * @author ElocinDev
     * @since 1.0.0
     */
    public static void makeBarBlock(Block barBlock, BiConsumer<ResourceLocation, Supplier<JsonElement>> modelCollector, Consumer<BlockStateGenerator> stateCollector) {
        ResourceLocation identifier = ModelLocationUtils.getModelLocation(barBlock, "_post_ends");
        ResourceLocation identifier2 = ModelLocationUtils.getModelLocation(barBlock, "_post");
        ResourceLocation identifier3 = ModelLocationUtils.getModelLocation(barBlock, "_cap");
        ResourceLocation identifier4 = ModelLocationUtils.getModelLocation(barBlock, "_cap_alt");
        ResourceLocation identifier5 = ModelLocationUtils.getModelLocation(barBlock, "_side");
        ResourceLocation identifier6 = ModelLocationUtils.getModelLocation(barBlock, "_side_alt");
        stateCollector.accept(MultiPartGenerator.multiPart(barBlock).with(Variant.variant().with(VariantProperties.MODEL, identifier)).with(Condition.condition().term(BlockStateProperties.NORTH, false).term(BlockStateProperties.EAST, false).term(BlockStateProperties.SOUTH, false).term(BlockStateProperties.WEST, false), Variant.variant().with(VariantProperties.MODEL, identifier2)).with(Condition.condition().term(BlockStateProperties.NORTH, true).term(BlockStateProperties.EAST, false).term(BlockStateProperties.SOUTH, false).term(BlockStateProperties.WEST, false), Variant.variant().with(VariantProperties.MODEL, identifier3)).with(Condition.condition().term(BlockStateProperties.NORTH, false).term(BlockStateProperties.EAST, true).term(BlockStateProperties.SOUTH, false).term(BlockStateProperties.WEST, false), Variant.variant().with(VariantProperties.MODEL, identifier3).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)).with(Condition.condition().term(BlockStateProperties.NORTH, false).term(BlockStateProperties.EAST, false).term(BlockStateProperties.SOUTH, true).term(BlockStateProperties.WEST, false), Variant.variant().with(VariantProperties.MODEL, identifier4)).with(Condition.condition().term(BlockStateProperties.NORTH, false).term(BlockStateProperties.EAST, false).term(BlockStateProperties.SOUTH, false).term(BlockStateProperties.WEST, true), Variant.variant().with(VariantProperties.MODEL, identifier4).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)).with(Condition.condition().term(BlockStateProperties.NORTH, true), Variant.variant().with(VariantProperties.MODEL, identifier5)).with(Condition.condition().term(BlockStateProperties.EAST, true), Variant.variant().with(VariantProperties.MODEL, identifier5).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)).with(Condition.condition().term(BlockStateProperties.SOUTH, true), Variant.variant().with(VariantProperties.MODEL, identifier6)).with(Condition.condition().term(BlockStateProperties.WEST, true), Variant.variant().with(VariantProperties.MODEL, identifier6).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)));
        ModelGenerator.registerItemModel(barBlock, modelCollector);
    }

    /**
     * Makes models and blockstates for a fence block.
     * 
     * @param fenceBlock        The fence block to register.
     * @param parentBlock       The parent block of the fence (Full block).
     * @param modelCollector    The model collector to register the model to.
     * @param stateCollector    The blockState collector to register the block state to.
     * 
     * @platform Fabric
     * 
     * @author ElocinDev
     * @since 1.0.0
     */
    public static void makeFenceBlock(Block fenceBlock, Block parentBlock, BiConsumer<ResourceLocation, Supplier<JsonElement>> modelCollector, Consumer<BlockStateGenerator> stateCollector) {
        TextureMapping textureMap = TextureMapping.cube(parentBlock);
        ResourceLocation identifier = ModelTemplates.FENCE_POST.create(fenceBlock, textureMap, modelCollector);
        ResourceLocation identifier2 = ModelTemplates.FENCE_SIDE.create(fenceBlock, textureMap, modelCollector);
        stateCollector.accept(BlockModelGenerators.createFence(fenceBlock, identifier, identifier2));
        ResourceLocation identifier3 = ModelTemplates.FENCE_INVENTORY.create(fenceBlock, textureMap, modelCollector);
        ModelGenerator.registerParentedItemModel(fenceBlock, identifier3, modelCollector);
    }

    /**
     * Makes models and blockstates for a fence block.
     * 
     * @param fenceGateBlock    The fence gate block to register.
     * @param parentBlock       The parent block of the fence (Full block).
     * @param modelCollector    The model collector to register the model to.
     * @param stateCollector    The blockState collector to register the block state to.
     * 
     * @platform Fabric
     * 
     * @author ElocinDev
     * @since 1.0.0
     */
    public static void makeFenceGateBlock(Block fenceGateBlock, Block parentBlock, BiConsumer<ResourceLocation, Supplier<JsonElement>> modelCollector, Consumer<BlockStateGenerator> stateCollector) {
        TextureMapping textureMap = TextureMapping.cube(parentBlock);
        ResourceLocation identifier = ModelTemplates.FENCE_GATE_OPEN.create(fenceGateBlock, textureMap, modelCollector);
        ResourceLocation identifier2 = ModelTemplates.FENCE_GATE_CLOSED.create(fenceGateBlock, textureMap,modelCollector);
        ResourceLocation identifier3 = ModelTemplates.FENCE_GATE_WALL_OPEN.create(fenceGateBlock, textureMap, modelCollector);
        ResourceLocation identifier4 = ModelTemplates.FENCE_GATE_WALL_CLOSED.create(fenceGateBlock, textureMap, modelCollector);
        stateCollector.accept(BlockModelGenerators.createFenceGate(fenceGateBlock, identifier, identifier2, identifier3, identifier4, false));

        ResourceLocation identifier5 = ModelTemplates.FENCE_INVENTORY.create(fenceGateBlock, textureMap, modelCollector);
        ModelGenerator.registerParentedItemModel(fenceGateBlock, identifier5, modelCollector);
    }

    /**
     * Makes models and blockstates for a button block.
     * 
     * @param buttonBlock       The button block to register.
     * @param parentBlock       The parent block of the button (Full block).
     * @param modelCollector    The model collector to register the model to.
     * @param stateCollector    The blockState collector to register the bloc
     *
     * @platform Fabric
     * 
     * @author ElocinDev
     * @since 1.0.0
     */
    public static void makeButtonBlock(Block buttonBlock, Block parentBlock, BiConsumer<ResourceLocation, Supplier<JsonElement>> modelCollector, Consumer<BlockStateGenerator> stateCollector) {
        TextureMapping textureMap = TextureMapping.cube(parentBlock);

        ResourceLocation identifier = ModelTemplates.BUTTON.create(buttonBlock, textureMap, modelCollector);
        ResourceLocation identifier2 = ModelTemplates.BUTTON_PRESSED.create(buttonBlock, textureMap, modelCollector);
        ResourceLocation identifier3 = ModelTemplates.BUTTON_INVENTORY.create(buttonBlock, textureMap, modelCollector);

        stateCollector.accept(ModelGenerator.createButtonBlockState(buttonBlock, identifier, identifier2));
        ModelGenerator.registerParentedItemModel(buttonBlock, identifier3, modelCollector);
    }
}
//#endif
