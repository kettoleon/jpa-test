package com.github.kettoleon.jpatest;

import java.nio.ByteBuffer;
import java.util.UUID;

public class UUIDUtils {


    public static byte[] randomUUIDBytes(){

        ByteBuffer bb = ByteBuffer.allocate(16);

        UUID uuid = UUID.randomUUID();

        bb.putLong(uuid.getMostSignificantBits());
        bb.putLong(uuid.getLeastSignificantBits());

        return bb.array();

    }
}
