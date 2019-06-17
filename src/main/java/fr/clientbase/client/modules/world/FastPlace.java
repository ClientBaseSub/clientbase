package fr.clientbase.client.modules.world;

import org.lwjgl.input.Keyboard;

import java.lang.reflect.Field;

import net.minecraft.client.Minecraft;

import fr.clientbase.client.modules.Modules;

public class FastPlace extends Modules
{
	public FastPlace()
	{
		super("FastPlace", Keyboard.KEY_L, Category.World);
	}
	
	public void onUpdate()
	{
		if (isToggled())
		{
			try
			{
				Field f = mc.getClass().getDeclaredField("rightClickDelayTimer");
				f.setAccessible(true);
				f.set(mc, 0);
			} catch (Exception ex)
			{
				try
				{
					Field f = mc.getClass().getDeclaredField("rightClickDelayTimer");
					f.setAccessible(true);
					f.set(mc, 0);
				} catch (Exception exx)
				{
					ex.printStackTrace();
					exx.printStackTrace();
				}
			}
		}
	}
}