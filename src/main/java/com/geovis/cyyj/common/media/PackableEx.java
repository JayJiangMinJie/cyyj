package com.geovis.cyyj.common.media;

public interface PackableEx extends Packable {
    void unmarshal(ByteBuf in);
}
