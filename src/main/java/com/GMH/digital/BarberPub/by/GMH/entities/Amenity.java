package com.GMH.digital.BarberPub.by.GMH.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Amenity {
    WIFI,
    PARKING,
    AIR_CONDITIONING,
    ACCESSIBLE_FOR_PEOPLE_WITH_DISABILITIES,
    CHILDREN_FRIENDLY,
    PET_FRIENDLY;

    @JsonCreator
    public static Amenity fromJson(String value) {
        return switch (value) {
            case "wifi" -> WIFI;
            case "parking" -> PARKING;
            case "airConditioning" -> AIR_CONDITIONING;
            case "accesibleForPeopleWithDisabilities" -> ACCESSIBLE_FOR_PEOPLE_WITH_DISABILITIES;
            case "childrenFriendly" -> CHILDREN_FRIENDLY;
            case "petFriendly" -> PET_FRIENDLY;
            default -> throw new IllegalArgumentException("Unknown amenity: " + value);
        };
    }

    @JsonValue
    public String toJson() {
        return switch (this) {
            case WIFI -> "wifi";
            case PARKING -> "parking";
            case AIR_CONDITIONING -> "airConditioning";
            case ACCESSIBLE_FOR_PEOPLE_WITH_DISABILITIES -> "accesibleForPeopleWithDisabilities";
            case CHILDREN_FRIENDLY -> "childrenFriendly";
            case PET_FRIENDLY -> "petFriendly";
        };
    }
}
