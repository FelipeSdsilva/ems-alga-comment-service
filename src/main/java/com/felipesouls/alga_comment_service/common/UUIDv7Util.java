package com.felipesouls.alga_comment_service.common;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.UUID;

public class UUIDv7Util {

  public UUIDv7Util() {
  }

  public static OffsetDateTime extractOffsetDateTime(UUID uuid) {
    if (uuid == null)
      return null;

    @SuppressWarnings("squid:S1854")
    long timestamp = uuid.getMostSignificantBits() >>> 16;

    return OffsetDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
  }
}
