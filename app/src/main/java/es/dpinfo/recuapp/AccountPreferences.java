package es.dpinfo.recuapp;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by dprimenko on 2/02/17.
 */
public class AccountPreferences {

    private static AccountPreferences accountPreferences;
    private Context context;
    private SharedPreferences preferences;

    public static final String SPORTS = "sports";
    public static final String TECH = "tech";
    public static final String HOME = "home";
    public static final String IMPORTANT = "important";

    public static AccountPreferences getInstance(Context context) {

        if (accountPreferences == null) {
            accountPreferences = new AccountPreferences(context);
        }

        return accountPreferences;
    }

    private AccountPreferences(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences("acc_pref", Context.MODE_PRIVATE);
    }

    private SharedPreferences.Editor getEditor() {
        return preferences.edit();
    }

    public void deletePrefs() {
        getEditor().clear().apply();
    }

    public boolean accountPrefExists() {
        return preferences.contains(SPORTS);
    }

    public void putSportsConfig(boolean confirm) {
        getEditor().putBoolean(SPORTS, confirm).apply();
    }

    public void putTechConfig(boolean confirm) {
        getEditor().putBoolean(TECH, confirm).apply();
    }

    public void putHomeConfig(boolean confirm) {
        getEditor().putBoolean(HOME, confirm).apply();
    }

    public void putImportantConfig(boolean confirm) {
        getEditor().putBoolean(IMPORTANT, confirm).apply();
    }

    public boolean getSportsConfig() {
        return preferences.getBoolean(SPORTS, false);
    }

    public boolean getTechConfig() {
        return preferences.getBoolean(TECH, false);
    }

    public boolean getHomeConfig() {
        return preferences.getBoolean(HOME, false);
    }

    public boolean getImportantConfig() {
        return preferences.getBoolean(IMPORTANT, false);
    }
}
