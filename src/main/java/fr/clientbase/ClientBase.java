package fr.clientbase;

import java.util.ArrayList;

import fr.clientbase.client.modules.Modules;
import fr.clientbase.client.modules.Modules.Category;
import fr.clientbase.client.modules.render.HUD;
import fr.clientbase.common.CommonProxy;
import fr.clientbase.client.modules.combat.*;
import fr.clientbase.client.modules.movements.*;
import fr.clientbase.client.modules.player.*;
import fr.clientbase.client.modules.render.*;
import fr.clientbase.client.modules.world.*;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "clientbase", name = "ClientBase", version = "b1", dependencies = "required-after:Forge@[11.15.0.1655,)", acceptedMinecraftVersions = "[1.8.8]")
public class ClientBase 
{
	@Mod.Instance("ClientBase")
	public static ClientBase INSTANCE;
	
	@SidedProxy(clientSide = "fr.clientbase.client.ClientProxy", serverSide = "fr.clientbase.common.CommonProxy")
	public static CommonProxy proxy;
	private static ArrayList<Modules> modules;
	private static Minecraft mc = Minecraft.getMinecraft();
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		proxy.registerRenders();
	}
	
	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {}
	
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {}
	
	public ClientBase()
	{
		modules = new ArrayList();
		
		//Combat
		register(new KillAura());
		
		//Movements
		register(new Sprint());
		
		//Player
		register(new FastEat());
		
		//Render
		register(new HUD());
		
		//World
		register(new FastPlace());
	}
	
	private void register(Modules m)
	{
		modules.add(m);
	}
	
	public static ArrayList<Modules> getModules()
	{
		return modules;
	}
	
	public static void onUpdate()
	{
		for (Modules m : modules)
		{
			m.onUpdate();
		}
	}
	
	public static void onRender()
	{
		for (Modules m : modules)
		{
			m.onRender();
		}
	}
	
	public static void onKeyPressed(int k)
	{
		for (Modules m : modules)
		{
			if (m.getKey() == k)
			{
				m.toggle();
			}
		}
	}
	
	public static ArrayList<Modules> getModulesInCategory(Category categoryIn)
	{
		ArrayList<Modules> modz = new ArrayList();
		for (Modules m : modules)
		{
			if (m.getCategory() == categoryIn)
				modz.add(m);
		}
		return modz;
	}
}