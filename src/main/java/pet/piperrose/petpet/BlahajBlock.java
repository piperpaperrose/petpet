package pet.piperrose.petpet;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import com.gtnewhorizon.gtnhlib.client.model.ModelISBRH;

public class BlahajBlock extends Block {
    public BlahajBlock() {
        super(Material.cloth);
        this.setBlockBounds(0.125f, 0.0f, 0.125f, 0.875f, 0.5f, 0.875f);
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
    public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {
        return switch (meta) {
            case 2 -> 1;
            case 3 -> 2;
            case 4 -> 3;
            default -> 0;
        };
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
