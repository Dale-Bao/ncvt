package com.ncvt.common.generator;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.ncvt.common.generator.model.Config;
import com.ncvt.common.generator.model.MybatisPlusEngine;
import com.ncvt.common.utils.StringUtils;

import java.util.Scanner;

/**
 * Created by mac on 06/11/2019.
 */
public class MybatisPlusGenerator {
    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        MybatisPlusEngine engine = new MybatisPlusEngine();
        engine.start(new Config()
                        .enableEntity(true)
                        .enableXML(true)
                        .enableMapper(true)
                        .enableController(false)
                        .enableService(true)
                        .enableServiceImpl(true),
                Config.Model.upms,Config.Model.edu);
    }
}
