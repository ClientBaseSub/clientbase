package fr.clientbase.client.modules.movements;

import org.lwjgl.input.Keyboard;

import fr.clientbase.client.modules.Modules;

public class Sprint extends Modules
{
	public Sprint()
	{
		super("Sprint", Keyboard.KEY_NONE, Category.Movement);
		setToggled(true);
	}
	
	public void onUpdate()
	{
		if (!isToggled()) return;
		
		if ((!this.mc.thePlayer.isCollidedHorizontally) && (this.mc.thePlayer.moveForward > 0.0F))
		{
			this.mc.thePlayer.setSprinting(true);
		} else {
			this.mc.thePlayer.setSprinting(false);
		}
	}
}	