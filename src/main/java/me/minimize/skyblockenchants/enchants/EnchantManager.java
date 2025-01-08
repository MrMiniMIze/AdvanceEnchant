package me.minimize.skyblockenchants.enchants;

import me.minimize.skyblockenchants.SkyblockEnchants;
import org.bukkit.configuration.ConfigurationSection;

import java.util.HashMap;
import java.util.Map;

/**
 * Loads and stores custom enchants from config.
 * Author: MrMiniMize & minimize0x
 */
public final class EnchantManager {

    private final SkyblockEnchants plugin;
    private final Map<String, CustomEnchant> enchantMap = new HashMap<>();

    public EnchantManager(SkyblockEnchants plugin) {
        this.plugin = plugin;
        loadEnchantsFromConfig();
    }

    private void loadEnchantsFromConfig() {
        ConfigurationSection enchSec = plugin.getConfig().getConfigurationSection("enchants");
        if (enchSec == null) return;

        for (String key : enchSec.getKeys(false)) {
            String displayName = enchSec.getString(key + ".displayName", key);
            String tier = enchSec.getString(key + ".tier", "COMMON");
            int maxLevel = enchSec.getInt(key + ".maxLevel", 1);
            double baseCost = enchSec.getDouble(key + ".baseCost", 100);
            String effectDesc = enchSec.getString(key + ".effectDescription", "No effect desc");

            CustomEnchant enchant = new CustomEnchant(key, displayName, tier, maxLevel, baseCost, effectDesc);
            enchantMap.put(key.toUpperCase(), enchant);
        }
    }

    public CustomEnchant getEnchant(String name) {
        return enchantMap.get(name.toUpperCase());
    }

    public Map<String, CustomEnchant> getAllEnchants() {
        return enchantMap;
    }
}
