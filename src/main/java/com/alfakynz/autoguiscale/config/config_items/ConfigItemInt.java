package com.alfakynz.autoguiscale.config.config_items;

public class ConfigItemInt extends ConfigItem<Integer> {
    public int min;
    public int max;

    public ConfigItemInt(String name, String text, int value, int min, int max) {
        super(name, text, value);
        this.min = min;
        this.max = max;
    }

    @Override
    public String getStrValue() {
        return Integer.toString(this.value);
    }

    @Override
    public String getStrDefaultValue() {
        return Integer.toString(this.value);
    }

    @Override
    public void setValue(String value) {
        this.value = Integer.parseInt(value);
    }
}
