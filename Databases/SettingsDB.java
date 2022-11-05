package Databases;

// import java.util.Map;
// import java.util.ArrayList;
// import java.time.LocalDateTime;
import Objects.Settings;

public class SettingsDB extends SerializeDB<Settings> {
    public SettingsDB() {
        this.filename = "Databases/settings.dat";
    }
}
