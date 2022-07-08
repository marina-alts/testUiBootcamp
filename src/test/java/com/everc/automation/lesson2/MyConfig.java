package com.everc.automation.lesson2;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;

@Config.Sources({"classpath:config.properties"})
public interface MyConfig extends Config {

    MyConfig config = ConfigFactory.create(MyConfig.class, System.getProperties(), System.getenv());

    String browser();

    String url();
}
