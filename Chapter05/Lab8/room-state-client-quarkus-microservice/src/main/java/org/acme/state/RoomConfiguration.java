package org.acme.state;

import io.smallrye.config.ConfigMapping;

@ConfigMapping(prefix = "manning.workout")
public interface RoomConfiguration {
     int room1();
     int room2();
     int room3();
     int room4();
     int room5();

}
