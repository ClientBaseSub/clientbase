package fr.clientbase.client.modules.player;

import org.lwjgl.input.Keyboard;

import fr.clientbase.client.modules.Modules;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucketMilk;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemPotion;
import net.minecraft.network.play.client.C03PacketPlayer;

public class FastEat extends Modules
{
	public FastEat()
	{
		super("FastEat", Keyboard.KEY_V, Category.Player);
	}
	
	public void onUpdate()
	{
		if (isToggled())
		{
			if (mc.thePlayer.isUsingItem())
			{
				Item item = mc.thePlayer.getCurrentEquippedItem().getItem();
				if (item instanceof ItemFood || item instanceof ItemPotion || item instanceof ItemBucketMilk)
				{
					for (int i = 0; i < 20; i++)
					{
						mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer());
					}
					mc.thePlayer.stopUsingItem();
				}
			}
		}
		super.onUpdate();
	}
}