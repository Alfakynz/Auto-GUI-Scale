package com.alfakynz.autoguiscale.config.config_items;

public class ConfigItemBool extends ConfigItem<Boolean> {

    public ConfigItemBool(String name, String text, boolean value) {
        super(name, text, value);
    }

    @Override
    public String getStrValue() {
        return Boolean.toString(this.value);
    }

    @Override
    public String getStrDefaultValue() {
        return Boolean.toString(this.value);
    }

    @Override
    public void setValue(String value) {
        this.value = Boolean.parseBoolean(value);
    }
}
