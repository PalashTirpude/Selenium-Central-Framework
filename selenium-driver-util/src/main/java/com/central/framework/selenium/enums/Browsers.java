package com.central.framework.selenium.enums;

import lombok.Getter;

@Getter
public enum Browsers {
    CHROME("chrome"),EDGE("edge"),FIREFOX("firefox"),SAFARI("safari");

    private final String browserName;


    Browsers(String browserName) {
        this.browserName = browserName;
    }
}
