package one.oktw.mixin.hack;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.packet.c2s.login.LoginHelloC2SPacket;
import net.minecraft.network.packet.s2c.login.LoginQueryRequestS2CPacket;
import net.minecraft.server.network.ServerLoginNetworkHandler;
import one.oktw.VelocityLib;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerLoginNetworkHandler.class)
public class ServerLoginNetworkHandler_EarlySendPacket {
    @Inject(method = "onHello", at = @At(value = "HEAD"), cancellable = true)
    private void skipKeyPacket(LoginHelloC2SPacket packet, CallbackInfo ci) {
        if (packet.getProfile().isComplete()) return; // Already receive profile form velocity.

       connection.send(new LoginQueryRequestS2CPacket(ThreadLocalRandom.current().nextInt(), PLAYER_INFO_CHANNEL, PLAYER_INFO_PACKET));
    }
}
