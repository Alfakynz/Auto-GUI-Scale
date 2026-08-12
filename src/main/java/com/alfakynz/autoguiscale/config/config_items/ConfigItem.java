package com.alfakynz.autoguiscale.config.config_items;

public abstract class ConfigItem<T> {
    public String name;
    public String text;
    public T value;
    public T defaultValue;

    public ConfigItem(String name, String text, T value) {
        this.name = name;
        this.text = text;
        this.value = value;
        this.defaultValue = value;
    }

    public abstract String getStrValue();

    public abstract String getStrDefaultValue();

    public abstract void setValue(String value);

    public String getName() {
        return name + "=";
    }

    public String getConfigText() {
        return this.getName() + this.getStrValue() + "\n";
    }

    public String getConfigDefaultText() {
        return this.getName() + this.getStrDefaultValue() + "\n";
    }
}
