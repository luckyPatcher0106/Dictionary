package com.example.settings;

public class AudioSetting {
    public static boolean getConfig() {
        return readwriteLocal.getAllConfigs().getBoolean("play-audio-online");
    }

    public static void setConfig(boolean value) {readwriteLocal.getAllConfigs().put("play-audio-online", value);}
}
