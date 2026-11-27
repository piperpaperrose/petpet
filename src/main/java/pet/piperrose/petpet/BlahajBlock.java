package pet.piperrose.petpet;

import com.gtnewhorizon.gtnhlib.blockstate.core.BlockPropertyTrait;
import com.gtnewhorizon.gtnhlib.blockstate.core.BlockState;
import com.gtnewhorizon.gtnhlib.blockstate.properties.DirectionBlockProperty;
import com.gtnewhorizon.gtnhlib.blockstate.registry.BlockPropertyRegistry;
import com.gtnewhorizon.gtnhlib.util.DirectionUtil;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import com.gtnewhorizon.gtnhlib.client.model.ModelISBRH;
import net.minecraftforge.common.util.ForgeDirection;

public class BlahajBlock extends Block {
    public BlahajBlock() {
        super(Material.cloth);
        this.setBlockBounds(0.125f, 0.0f, 0.125f, 0.875f, 0.5f, 0.875f);
    }

    public static void register() {
        final var blahajBlock = new BlahajBlock();
        blahajBlock.setBlockName("blahaj");
        GameRegistry.registerBlock(blahajBlock, "blahaj");
        GameRegistry.registerTileEntity(BlahajBlockTile.class, "tile_blahaj");

        final var property = new DirectionBlockProperty() {

            @Override
            public String getName() {
                return "facing";
            }

            @Override
            public boolean hasTrait(BlockPropertyTrait trait) {
                return switch (trait) {
                    case SupportsWorld, WorldMutable, StackMutable, SupportsStacks -> true;
                    default -> false;
                };
            }

            @Override
            public ForgeDirection getValue(IBlockAccess world, int x, int y, int z) {
                TileEntity te = world.getTileEntity(x, y, z);
                if (te instanceof BlahajBlockTile tile) return tile.getFacing();
                return ForgeDirection.NORTH;
            }

            @Override
            public void setValue(World world, int x, int y, int z, ForgeDirection value) {
                TileEntity te = world.getTileEntity(x, y, z);
                if (te instanceof BlahajBlockTile tile) tile.setFacing(value);
            }

            @Override
            public ForgeDirection getValue(ItemStack stack) {
                return ForgeDirection.NORTH;
            }
        };

        BlockPropertyRegistry.registerBlockItemProperty(blahajBlock, property);
    }


    @Override
    public Block setBlockTextureName(String name) {
        this.textureName = "petpet:models/blahaj";
        return this;
    }

    @Override
    public void registerBlockIcons(IIconRegister reg) {
        reg.registerIcon("petpet:models/blahaj");
        super.registerBlockIcons(reg);
    }


    @Override
    public int getRenderType() {
        return ModelISBRH.JSON_ISBRH_ID;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World worldIn, int x, int y, int z) {
        return AxisAlignedBB.getBoundingBox(0.125f, 0.0f, 0.125f, 0.875f, 0.5f, 0.875f);
    }
    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
        this.setBlockBounds(0.125f, 0.0f, 0.125f, 0.875f, 0.5f, 0.875f);
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase placer, ItemStack stack) {
        BlockState state = BlockPropertyRegistry.getBlockState(world, x, y, z);
        state.setPropertyValue("facing", DirectionUtil.yawToDirection(placer.rotationYaw));
        state.place(world, x, y, z, 3);
        state.close();
    }

    @Override
    public boolean hasTileEntity(int meta) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(World world, int meta) {
        return new BlahajBlockTile();
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean isNormalCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }
}
