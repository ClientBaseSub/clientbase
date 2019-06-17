package fr.clientbase.client.modules.combat;

import org.lwjgl.input.Keyboard;

import fr.clientbase.client.modules.Modules;
import fr.clientbase.client.modules.Modules.Category;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.play.client.C02PacketUseEntity;

public class KillAura extends Modules
{
	private int cps;
	private int delay;
	
	public KillAura()
	{
		super("KillAura", Keyboard.KEY_K, Category.Combat);
	}
	
	public void onUpdate()
	{
		if (!isToggled()) return;
		
		this.cps = 13;
		try
		{
			this.delay += 1;
			if (this.delay >= -1 / this.cps)
			{
				this.delay = 0;
				for (Object o : this.mc.theWorld.loadedEntityList)
				{
					if ((o instanceof EntityLivingBase))
					{
						EntityLivingBase e = (EntityLivingBase) o;
						if (e != null && e != mc.thePlayer && !e.isInvisible() && e.isEntityAlive() && mc.thePlayer.getDistanceToEntity(e) <= mc.playerController.getBlockReachDistance())
						{
							mc.thePlayer.swingItem();
							mc.thePlayer.sendQueue.addToSendQueue(new C02PacketUseEntity(e, C02PacketUseEntity.Action.ATTACK));
						}
					}
				}
			}
		} catch (Exception localException1) {}
		super.onUpdate();
	}
}