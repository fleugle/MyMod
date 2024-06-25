package nikita.uniquescythe.utility;


import net.minecraft.block.dispenser.DispenserBehavior;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Position;
import net.minecraft.util.math.Direction.Axis;
import net.minecraft.world.World;
import nikita.uniquescythe.blocks.custom.MinerBlock;

public class MinerDispenseBehavior implements DispenserBehavior {
	public MinerDispenseBehavior() {
	}

	public final ItemStack dispense(BlockPointer blockPointer, ItemStack itemStack) {
		ItemStack itemStack2 = this.dispenseSilently(blockPointer, itemStack);
		/*this.playSound(blockPointer);*/
		this.spawnParticles(blockPointer, (Direction)blockPointer.getBlockState().get(MinerBlock.FACING));
		return itemStack2;
	}

	protected ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
		Direction direction = (Direction)pointer.getBlockState().get(MinerBlock.FACING);
		Position position = MinerBlock.getOutputLocation(pointer);
		ItemStack itemStack = stack.split(1);
		spawnItem(pointer.getWorld(), itemStack, 6, direction, position);
		return stack;
	}

	public static void spawnItem(World world, ItemStack stack, int offset, Direction side, Position pos) {
		double d = pos.getX();
		double e = pos.getY();
		double f = pos.getZ();
		if (side.getAxis() == Axis.Y) {
			e -= 0.125;
		} else {
			e -= 0.15625;
		}

		ItemEntity itemEntity = new ItemEntity(world, d, e, f, stack);
		double g = world.random.nextDouble() * 0.1 + 0.2;
		itemEntity.setVelocity(world.random.nextTriangular((double)side.getOffsetX() * g, 0.0172275 * (double)offset), world.random.nextTriangular(0.2, 0.0172275 * (double)offset), world.random.nextTriangular((double)side.getOffsetZ() * g, 0.0172275 * (double)offset));
		world.spawnEntity(itemEntity);
	}

	/*protected void playSound(BlockPointer pointer) {
		pointer.getWorld().syncWorldEvent(1000, pointer.getPos(), 0);
	}*/

	protected void spawnParticles(BlockPointer pointer, Direction side) {
		pointer.getWorld().syncWorldEvent(2000, pointer.getPos(), side.getId());
	}

}
