package net.benwoodworth.katas.socialNetwork;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.TemporalAmount;

public class MockClock extends Clock {
    private Instant instant;
    final private ZoneId zone;

    MockClock(Instant instant, ZoneId zone) {
        this.instant = instant;
        this.zone = zone;
    }

    @Override
    public ZoneId getZone() {
        return zone;
    }

    @Override
    public Clock withZone(ZoneId zone) {
        return new MockClock(instant, zone);
    }

    @Override
    public Instant instant() {
        return instant;
    }

    public void advance(TemporalAmount amount) {
        instant = instant.plus(amount);
    }
}
