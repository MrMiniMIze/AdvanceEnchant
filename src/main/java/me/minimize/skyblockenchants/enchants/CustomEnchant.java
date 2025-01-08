package me.minimize.skyblockenchants.enchants;

/**
 * Represents a single custom enchant definition.
 * Author: MrMiniMize & minimize0x
 */
public final class CustomEnchant {

    private final String key;
    private final String displayName;
    private final String tier;
    private final int maxLevel;
    private final double baseCost;
    private final String effectDescription;

    public CustomEnchant(String key, String displayName, String tier, int maxLevel, double baseCost, String effectDescription) {
        this.key = key;
        this.displayName = displayName;
        this.tier = tier;
        this.maxLevel = maxLevel;
        this.baseCost = baseCost;
        this.effectDescription = effectDescription;
    }

    public String getKey() {
        return key;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getTier() {
        return tier;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public double getBaseCost() {
        return baseCost;
    }

    public String getEffectDescription() {
        return effectDescription;
    }
}
