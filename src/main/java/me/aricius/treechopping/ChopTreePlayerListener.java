package me.aricius.treechopping;

public class ChopTreePlayerListener {
    private static ChopTree plugin;
    private boolean active;
    private String playerName;

    public ChopTreePlayerListener(ChopTree instance, String playerName) {
        plugin = instance;
        this.playerName = playerName;
        this.active = this.getSetting("active");
        if (plugin.playersDb.get(playerName + ".active") == null) {
            this.addPlayer();
            this.active = plugin.defaultActive;
        }

    }

    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean setting) {
        this.active = setting;
        plugin.playersDb.set(this.playerName + ".active", setting);
        plugin.savePlayers();
    }

    public void toggleActive() {
        this.active = !this.active;
        plugin.playersDb.set(this.playerName + ".active", this.active);
        plugin.savePlayers();
    }

    private boolean getSetting(String setting) {
        boolean value = false;
        if (setting.equalsIgnoreCase("active")) {
            value = plugin.playersDb.getBoolean(this.playerName + "." + setting, plugin.defaultActive);
        }

        return value;
    }

    private void addPlayer() {
        plugin.playersDb.set(this.playerName + ".active", plugin.defaultActive);
        plugin.savePlayers();
    }
}

