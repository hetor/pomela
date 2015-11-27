package com.pomela.orm.mybatis.generator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by hetor on 15/11/25.
 */
public class MBGenerator {
    public static void main(String[] args) throws Exception {
        String configPath = "generatorConfig.xml";
        List<String> warnings = new ArrayList<>();
        boolean overwrite = true;

        File configFile = new ClassPathResource(configPath).getFile();
        System.out.println("config file --> " + configFile.getAbsoluteFile());

        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);

//        for(Context context : config.getContexts()){
//            context.addProperty("overwrite", String.valueOf(overwrite));
//        }

        DefaultShellCallback callback = new DefaultShellCallback(overwrite);

        MyBatisGenerator generator = new MyBatisGenerator(config, callback, warnings);
        generator.generate(null);

        for (String warn : warnings) {
            System.out.println(warn);
        }
    }
}
