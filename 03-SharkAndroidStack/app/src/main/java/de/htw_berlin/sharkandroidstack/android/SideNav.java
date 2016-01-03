package de.htw_berlin.sharkandroidstack.android;

public class SideNav {

    public final static String[][] system_modules = new String[][]{
            new String[]{"Settings", "system_modules.settings.SettingsActivity"},
            new String[]{"Log", "system_modules.log.LogActivity"},
    };

    public final static String[][] modules = new String[][]{
            new String[]{"NFC Benchmark", "modules.nfc_benchmark.NfcBenchmarkMainActivity"},
            new String[]{"Mario Demo", "modules.mariodemo.MarioDemoMainActivity"},
    };
}
