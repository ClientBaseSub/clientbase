package fr.clientbase.client.modules.render;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import fr.clientbase.ClientBase;
import fr.clientbase.client.modules.Modules;
import fr.clientbase.util.ColorUtils;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.model.b3d.B3DModel.Brush;

public class HUD extends Modules
{
	public HUD()
	{
		super("HUD", Keyboard.KEY_H, Category.Render);
		setToggled(true);
	}
	
	public void onRender()
	{
		if (!isToggled()) return;
		
		GL11.glPushMatrix();
		GL11.glScalef(1.5F, 1.5F, 1.5F);
		drawStringWithRainbow("ClientBase", 2, 2, 0.8F);
		GL11.glScalef(1F, 1F, 1F);
		GL11.glPopMatrix();
		mc.fontRendererObj.drawStringWithShadow(EnumChatFormatting.GRAY + "b1", (int)(mc.fontRendererObj.getStringWidth("ClientBase") * 1.5F) + 8, 2, -1);
		
		ArrayList<Modules> modules = ClientBase.getModules();
		Collections.sort(modules, new Comparator() 
		{
			public int compare(Modules m1, Modules m2) 
			{
				if (mc.fontRendererObj.getStringWidth(m1.getName()) > mc.fontRendererObj.getStringWidth(m2.getName()))
				{
					return -1;
				}
				if (mc.fontRendererObj.getStringWidth(m1.getName()) < mc.fontRendererObj.getStringWidth(m2.getName())) 
				{
					return 1;
				}
				return 0;
			}

			@Override
			public int compare(Object arg0, Object arg1) {return 0;} });
		
		ScaledResolution sr = new ScaledResolution(mc);

		int count = 0;
		for (Modules m : modules)
		{
			if ((m.isToggled()) && (!m.isCategory(Category.GUI))) 
			{
				Gui.drawRect(sr.getScaledWidth() - mc.fontRendererObj.getStringWidth(m.getName()) - 2, count * 10, sr.getScaledWidth(), (count + 1) * 10, 0);
				mc.fontRendererObj.drawStringWithShadow(m.getName(), sr.getScaledWidth() - mc.fontRendererObj.getStringWidth(m.getName()) - 1, count * 10 + 1, Color.GREEN.getRGB());
						
				count++;
			}
		}
		super.onRender();
	}
	
	public void drawStringWithRainbow(String string, int x, int y, float brightness)
	{
		int xpos= x;
		for (int i = 0; i < string.length(); i++)
		{
			String s = string.charAt(i) + "";
			mc.fontRendererObj.drawStringWithShadow(s, xpos, y, ColorUtils.color(i * 3500000L, brightness, 100).getRGB());
			xpos += mc.fontRendererObj.getStringWidth(s);
		}
	}
}