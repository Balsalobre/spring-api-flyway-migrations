package com.cbo.account.management.shared.domain;

import java.util.Objects;
import java.util.UUID;

public abstract class Identifier {
   private String value;

   public Identifier(String value) {
       ensureValidUUID(value);
   }

   private void ensureValidUUID(String value) {
       UUID.fromString(value);
   }

    public String value() {
         return value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Identifier that = (Identifier) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
