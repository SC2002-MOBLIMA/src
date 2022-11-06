package Databases;

import Objects.Settings;

public class SettingsDB extends SerializeDB<Settings> {
    public SettingsDB() {
        this.filename = "Databases/settings.dat";
    }
}
