package com.ncvt.common.generator.model;

/**
 * @author xueneng on 2020/9/1.
 *         Description:
 */
public class Config {
    public String author = "Dale";
    /**
     * DB配置
     */
    public String db_url = "jdbc:mysql://192.168.1.150:3306/ncvt?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&useSSL=false&serverTimezone=UTC";
    public String db_driver = "com.mysql.cj.jdbc.Driver";
    public String db_username = "root";
    public String db_password = "yunXin!63#";

    public String module_name = "common";

    public String xml_path = "service";
    public String entity_path = "api";
    public String mapper_path = "service";
    public String service_path = "api";
    public String service_impl_path = "service";
    public String controller_path = "web";

    public String pg_parent = "com.nvct";
    public String pg_entity = "model.entity";
    public String pg_mapper = "service.mapper";
    public String pg_service = "api";
    public String pg_service_impl = "service.impl";
    public String pg_controller = "web.controller";

    public String  module_path = "/ncvt";
    public String table_include = ".*";


    public String table_prefix = "common_";
    public boolean entity_enable = true;
    public boolean mapper_enable = true;
    public boolean xml_enable = true;
    public boolean service_enable = true;
    public boolean service_impl_enable = true;
    public boolean controller_enable = true;


    public Config enableEntity(boolean enable){
        entity_enable = enable;
        return this;
    }

    public Config enableMapper(boolean enable){
        mapper_enable = enable;
        return this;
    }

    public Config enableXML(boolean enable){
        xml_enable = enable;
        return this;
    }

    public Config enableService(boolean enable){
        service_enable = enable;
        return this;
    }

    public Config enableServiceImpl(boolean enable){
        service_impl_enable = enable;
        return this;
    }

    public Config enableController(boolean enable){
        controller_enable = enable;
        return this;
    }

    /**
     * 模板引擎 freemarker
     */
    public String templatePath = "/templates/";

    public Config setModel(Model model) {
        module_name = model.name;
        module_path = "/ncvt_" + model.name + "/ncvt_" + model.name;
        table_include = "^(?:"+"ncvt_" + model.name + "_).*";
        table_prefix = "ncvt_";
       return this;
    }
    public String getXmlPath() {
        return module_path + "_" + xml_path;
    }
    public String getEntityPath() {
        return module_path + "_" + entity_path;
    }
    public String getMapperPath() {
        return module_path + "_" + mapper_path;
    }
    public String getServicePath() {
        return module_path + "_" + service_path;
    }
    public String getServiceImplPath() {
        return module_path + "_" + service_impl_path;
    }
    public String getControllerPath() {
        return module_path + "_" + controller_path;
    }

    public enum Model {
        edu("edu"),
        upms("upms");
        private String name;
        Model(String name){
            this.name = name;
        }
    }


}
