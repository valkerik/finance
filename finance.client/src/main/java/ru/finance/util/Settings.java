package ru.finance.util;

import java.io.File;
import java.util.Locale;

public class Settings {
    public static final File SAVE_DIR = new File("saves/");
    public static final String SAVE_FILE_EXT = "save";

    public static final String FORMAT_AMOUNT = "%.2f";
    public static final String FORMAT_RATE = "%.4f";
    public static final String FORMAT_DATE = "dd.MM.yyyy";
    public static final String FORMAT_DATE_MONTH = "MMMM yyyy";
    public static final String FORMAT_DATE_YEAR = "yyyy";

    public static final int COUNT_OVERVIEW_ROWS = 10;

    public static final String[] CURRENCIES_CODE = new String[] {"RUB", "USD", "EUR", "BYN", "UAH"};

    public static final File FILE_SETTING = new File("saves/setting.ini");
    public static File FILE_SAVE = new File("saves/default.save");

//    public static void init(){
//        try {
//            Ini ini = new Ini(FILE_SETTING);
//            Preferences preferences = new IniPreferences(ini);
//            String file = preferences.node("Settings").get("FILE_SAVE", null);
//            if(file != null) FILE_SAVE = new File(file);
//        } catch (IOException e) {
//            save();
//        }
//    }

    private static void setLocale(){
        Locale.setDefault(new Locale("ru"));
    }

    public static File getFileSave() {
        return FILE_SAVE;
    }

    public static void setFileSave(File fileSave) {
        FILE_SAVE = fileSave;
    }

//    private static void save(){
//        try {
//            Wini ini = new Wini(FILE_SETTING);
//            ini.put("Settings", "FILE_SAVE", FILE_SAVE.getAbsolutePath().replace("\\", "\\\\"));
//            ini.store();
//        } catch (IOException e) {
//            Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, e);
//            e.printStackTrace();
//        }
//    }
}

