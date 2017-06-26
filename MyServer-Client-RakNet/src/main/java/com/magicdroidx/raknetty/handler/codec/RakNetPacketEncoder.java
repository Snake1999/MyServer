package com.magicdroidx.raknetty.handler.codec;

import com.magicdroidx.raknetty.protocol.raknet.RakNetPacket;
import io.netty.channel.AddressedEnvelope;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.net.InetSocketAddress;
import java.util.List;

/**
 * RakNetty Project
 * Author: MagicDroidX
 */
public class RakNetPacketEncoder extends MessageToMessageEncoder<AddressedEnvelope<RakNetPacket, InetSocketAddress>> {

    @Override
    protected final void encode(ChannelHandlerContext ctx, AddressedEnvelope<RakNetPacket, InetSocketAddress> msg, List<Object> out) throws Exception {
        assert out.isEmpty();
        RakNetPacket packet = msg.content();
        packet.encode();
        /*if (packet instanceof SessionPacket) {
            ByteBuf buf = packet.copy().retain();
            byte[] bytes = new byte[buf.writerIndex()];
            buf.getBytes(0, bytes);
            System.out.println("Out: " + BaseEncoding.base16().withSeparator(" ", 2).encode(bytes));
        }*/
        out.add(new DatagramPacket(packet.retain(), msg.recipient(), msg.sender()));
    }

}
