package net.mod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.MessageType;

import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class autonexus {
	int waitTicks = 0;
	boolean activated = false;
	@Inject(at = @At("HEAD"), method = "tick()V")
	private void init(CallbackInfo info) {
		MinecraftClient client = MinecraftClient.getInstance();
		ClientPlayerEntity player = client.player;
        if (player != null) {
        	if (waitTicks == 0)
			{
				client.inGameHud.addChatMessage(MessageType.SYSTEM, new LiteralText("§k§0-§r   §c§l§nStarting AutoNexus in 15 seconds§r...   §r§k§0-§r"), client.player.getUuid());
			}
        	if (waitTicks == 199){
				client.inGameHud.addChatMessage(MessageType.SYSTEM, new LiteralText(""), client.player.getUuid());
				client.inGameHud.addChatMessage(MessageType.SYSTEM, new LiteralText("§k§0-§r   §a§lAutoNexus Active (@ §r§63 §r§a§lhearts)§r   §k§0-§r"), client.player.getUuid());
				client.inGameHud.addChatMessage(MessageType.SYSTEM, new LiteralText(""), client.player.getUuid());
			}
        	if (player.getHealth() <= 6 && player.getHealth() != 0 && waitTicks == 200 && activated == false) {
	        		player.sendChatMessage("/hub");
					client.inGameHud.addChatMessage(MessageType.SYSTEM, new LiteralText(""), client.player.getUuid());
	        		client.inGameHud.addChatMessage(MessageType.SYSTEM, new LiteralText("§k§0-§r   §c§l§nAutoNexus Triggered§r   §k§0-§r"), client.player.getUuid());
					client.inGameHud.addChatMessage(MessageType.SYSTEM, new LiteralText(""), client.player.getUuid());
					activated = true;
        	}
			if (waitTicks < 200){
				waitTicks += 1;
			}
        }
	}
}