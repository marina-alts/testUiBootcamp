package com.everc.automation.lesson2;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:config.properties"})
public interface MyConfig extends Config {
    String browser();

    String url();
}
