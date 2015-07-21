package com.pomela.springmvc.test.mvc;

import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import java.util.Locale;

/**
 * Created by hetor on 15/3/24.
 */
public class MyFreeMarkerkView extends FreeMarkerView {
    @Override
    public boolean checkResource(Locale locale) throws Exception {
        return true;
    }
}
