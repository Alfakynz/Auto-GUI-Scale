package com.alfakynz.autoguiscale.config.config_items;

public class ConfigItemDouble extends ConfigItem<Double> {
    public double min;
    public double max;

    public ConfigItemDouble(String name, String text, double value, double min, double max) {
        super(name, text, value);
        this.min = value;
        this.max = value;
    }

    @Override
    public String getStrValue() {
        return Double.toString(this.value);
    }

    @Override
    public String getStrDefaultValue() {
        return Double.toString(this.value);
    }

    @Override
    public void setValue(String value) {
        this.value = Double.parseDouble(value);
    }
}
