package Databases;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class SettingsDB extends SerializeDB {
    public SettingsDB() {
        this.filename = "Databases/settings.dat";
    }
}