package fr.clientbase.client.modules;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

public class Modules 
{
	protected Minecraft mc = Minecraft.getMinecraft();
	protected FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
	private String name;
	private int key;
	private boolean toggled;
	private boolean pressed;
	protected boolean enabled;
	private Category category;
	
	public enum Category
	{
		Combat, Movement, Player, Render, World, GUI;
		
		private Category() {}
	}

	public Modules(String nm, int k, Category c) {
		this.name = nm;
		this.key = k;
		this.category = c;
		this.toggled = false;
	}

	public void toggle() {
		this.toggled = (!this.toggled);
		if (this.toggled) {
			onEnable();
		} else
			onDisable();
	}

	public void pressed() {
		this.pressed = (!this.pressed);
		if (this.pressed) {
			onEnable();
		} else
			onDisable();
	}

	public boolean isEnabled() {
		return toggled;
	}
			
	public void onEnable() {}

	public void onDisable() {}

	public void onUpdate() {}

	public void onPressed() {}

	public void onRender() {}

	public Minecraft getMc() {
		return this.mc;
	}

	public void setMc(Minecraft mc) {
		this.mc = mc;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKey() {
		return this.key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public boolean isToggled() {
		return this.toggled;
	}

	public boolean isPressed() {
		return this.pressed;
	}

	public void setToggled(boolean toggled) {
		this.toggled = toggled;
	}

	public boolean isActive() {
		return this.enabled;
	}

	public Category getCategory() {
		return this.category;
	}

	public boolean isCategory(Category cat) {
		return this.category == cat;
	}
}