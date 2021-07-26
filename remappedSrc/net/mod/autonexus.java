package net.mod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class autonexus {
	//int waitTicks = 0;
	boolean retriggerProtection = false;
	boolean cooldown = false;
	@Inject(at = @At("HEAD"), method = "tick()V")
	private void init(CallbackInfo info) {
		ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if (player != null) {
	        if (player.getHealth() <= 4 && player.getHealth() != 0) {
				if (cooldown == false){
					player.sendChatMessage("/hub");
					cooldown = true;
				}
				else{
					retriggerProtection = true;
				}
					//somehow wait here
	         }
			 if (player.getHealth() > 4 && player.getHealth() != 0 && cooldown == true && retriggerProtection == true){
				cooldown = false;
				retriggerProtection = false;
			}
        }
	}
}