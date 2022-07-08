package com.everc.automation.lesson2;

 @org.aeonbits.owner.Config.Sources({"file:config.properties"})

public interface MyConfig extends org.aeonbits.owner.Config {
    String browser();
    String url();
}
