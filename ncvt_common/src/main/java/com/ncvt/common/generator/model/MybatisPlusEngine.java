package com.ncvt.common.generator.model;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xueneng on 2020/9/1.
 *         Description:
 */
public class MybatisPlusEngine {

    private static String packageToPath(String packageName){
        return packageName.replaceAll("\\.","/");
    }

    public void start(Config.Model... model){
        for (int i = 0; i < model.length; i++) {
             core(new Config().setModel(model[i]));
        }
    }
    public void start(Config config, Config.Model... model) {
        for (int i = 0; i < model.length; i++) {
            core(config.setModel(model[i]));
        }
    }
    private void core(Config config) {
        //代码生成器
        AutoGenerator mpg = new AutoGenerator();

        //全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath);
        gc.setAuthor(config.author);
        //        gc.setIdType(IdType.AUTO);
//        gc.setFileOverride(true);
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(true);
        gc.setOpen(false);
        gc.setActiveRecord(true);
//        gc.setEntityName("%");
//        gc.setXmlName("%Mapper");
//        gc.setMapperName("%Mapper");
//        gc.setServiceName("I%Service");
//        gc.setServiceImplName("%ServiceImpl");
//        gc.setControllerName("%Controller");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(config.db_url);
        // dsc.setSchemaName("public");
        dsc.setDriverName(config.db_driver);
        dsc.setUsername(config.db_username);
        dsc.setPassword(config.db_password);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
//        pc.setModuleName(scanner("模块名"));
        pc.setModuleName(config.module_name);
        pc.setParent(config.pg_parent);
        pc.setEntity(config.pg_entity);
        pc.setMapper(config.pg_mapper);
        pc.setService(config.pg_service);
        pc.setServiceImpl(config.pg_service_impl);
        pc.setController(config.pg_controller);
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();


        //mapper xml
        if (config.xml_enable) {
            focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输入文件名称
                    return projectPath + config.getXmlPath() + "/src/main/resources/mapper/"
                            + tableInfo.getXmlName() + StringPool.DOT_XML;
                }
            });
        }
        //entity
        if (config.entity_enable) {
            focList.add(new FileOutConfig("/templates/entity.java.ftl") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输入文件名称
                    return projectPath + config.getEntityPath() + "/src/main/java/" + packageToPath(pc.getParent()) + "/" + "model/entity"
                            + "/" + tableInfo.getEntityName() + StringPool.DOT_JAVA;
                }
            });
        }
        //mapper
        if (config.mapper_enable) {
            focList.add(new FileOutConfig("/templates/mapper.java.ftl") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输入文件名称
                    return projectPath + config.getMapperPath() + "/src/main/java/" + packageToPath(pc.getParent()) + "/" + packageToPath(pc.getMapper())
                            + "/" + tableInfo.getMapperName() + StringPool.DOT_JAVA;
                }
            });
        }
        //service
        if (config.service_enable) {
            focList.add(new FileOutConfig("/templates/service.java.ftl") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输入文件名称
                    return projectPath + config.getServicePath() + "/src/main/java/" + packageToPath(pc.getParent()) + "/" + packageToPath(pc.getService())
                            + "/" + tableInfo.getServiceName() + StringPool.DOT_JAVA;
                }
            });
        }
        //serviceImpl
        if (config.service_impl_enable) {
            focList.add(new FileOutConfig("/templates/serviceImpl.java.ftl") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输入文件名称
                    return projectPath + config.getServiceImplPath() + "/src/main/java/" + packageToPath(pc.getParent()) + "/" + packageToPath(pc.getServiceImpl())
                            + "/" + tableInfo.getServiceImplName() + StringPool.DOT_JAVA;
                }
            });
        }
        //controller
        //       if (config.controller_enable) {
//        focList.add(new FileOutConfig("/templates/controller.java.ftl") {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                // 自定义输入文件名称
//                return projectPath + config.getControllerPath() + "[表情]c/main/java/" + packageToPath(pc.getParent()) + "/" + packageToPath(pc.getController())
//                        + "/" + tableInfo.getControllerName() + StringPool.DOT_JAVA;
//            }
//        });
        // };

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        mpg.setTemplate(new TemplateConfig().setXml(null)
                .setEntity(null).setMapper(null)
                .setService(null).setServiceImpl(null).setController(null));

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);

        strategy.setSuperEntityClass("com.ncvt.common.base.BaseEntity");
        strategy.setSuperEntityColumns("id","created_by","created_dt","updated_by","updated_dt","status","is_deleted","remark");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setEntityColumnConstant(false);
//        strategy.setSuperControllerClass("com.haier.educloud.oss.web.common.BaseController");
//        strategy.setInclude(scanner("表名"));
        strategy.setInclude(config.table_include);
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(config.table_prefix);
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
}
