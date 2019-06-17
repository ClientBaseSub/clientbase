package fr.clientbase.client;

import fr.clientbase.common.CommonProxy;
import fr.clientbase.util.EventReceiver;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class ClientProxy extends CommonProxy 
{
	public void registerRenders()
	{
		EventReceiver eventReceiver = new EventReceiver();
		
		MinecraftForge.EVENT_BUS.register(eventReceiver);
		MinecraftForge.EVENT_BUS.register(this);
		
		FMLCommonHandler.instance().bus().register(eventReceiver);
		super.registerRenders();
	}
}