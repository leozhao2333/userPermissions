//package com.hhf.boot.handler;
//
//import com.baomidou.mybatisplus.annotation.IdType;
//import com.baomidou.mybatisplus.generator.FastAutoGenerator;
//import com.baomidou.mybatisplus.generator.config.OutputFile;
//import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
//
//import java.util.Collections;
//
//public class CodeGenerator {
//    public static void main(String[] args) {
//        FastAutoGenerator.create("jdbc:mysql://sh-cynosdbmysql-grp-p1ix9b5m.sql.tencentcdb.com:23659/hhf?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=UTC", "root", "asd123456+")
//                .globalConfig(builder -> {
//                    builder.author("hhf") // 设置作者
//                            .fileOverride() // 覆盖已生成文件
//                            .outputDir("C:\\Users\\hhf\\Desktop\\4.25家\\hhf\\src\\main\\java"); // 指定输出目录
//                })
//                .packageConfig(builder -> {
//                    builder.parent("com.hhf")
//                            .moduleName("common")
//                            .entity("entity")
//                            .service("service")
//                            .serviceImpl("service.impl")
//                            .mapper("mapper")
//                            .controller("controller")
//                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "E:\\java\\hhf\\src\\main\\resources\\mapper"));
//                })
//                .strategyConfig(builder -> {
//                    builder.addInclude("ug_user") // 设置需要生成的表名
//                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
//                    builder.entityBuilder()
//                            .enableTableFieldAnnotation()
//                            .idType(IdType.ASSIGN_UUID);
//                    builder.serviceBuilder()
//                            .formatServiceFileName("%sService")
//                            .formatServiceImplFileName("%sServiceImp")
//                            .build();
//
//                })
//                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
//                .execute();
//    }
//}
