package fr.clientbase.util;

import org.lwjgl.input.Keyboard;

import fr.clientbase.ClientBase;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

public class EventReceiver 
{
	@SubscribeEvent
	public void onRender2D(RenderGameOverlayEvent event)
	{
		if (event.type == ElementType.TEXT)
		{
			try
			{
				ClientBase.onRender();
				
			} catch (Exception localException) {}
		}
	}
	
	@SubscribeEvent
	public void onUpdate(LivingEvent.LivingUpdateEvent event)
	{
		if (event.entityLiving == null)
		{
			return;
		}
		if ((event.entityLiving instanceof EntityPlayerSP))
		{
			try
			{
				ClientBase.onUpdate();
				
			} catch (Exception localException) {}
		}
	}
	
	@SubscribeEvent
	public void onKeyInput(InputEvent.KeyInputEvent event)
	{
		if (!Keyboard.getEventKeyState())
		{
			return;
		}
		try
		{
			ClientBase.onKeyPressed(Keyboard.getEventKey());
			
		} catch (Exception localException) {}
	}
}