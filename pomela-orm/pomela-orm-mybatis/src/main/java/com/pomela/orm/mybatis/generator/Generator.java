package com.pomela.orm.mybatis.generator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hetor on 15/11/25.
 */
public class Generator {
    public static void main(String[] args) throws Exception {
        String configPath = "generator-config/generatorConfig.xml";
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        File configFile = new ClassPathResource(configPath).getFile();
        System.out.println("config file : " + configFile.getAbsoluteFile());
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        for(Context context : config.getContexts()){
            context.addProperty("overwrite", String.valueOf(overwrite));
        }
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
        for (String warn : warnings) {
            System.out.println(warn);
        }
    }
}
