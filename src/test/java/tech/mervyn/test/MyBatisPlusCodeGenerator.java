package tech.mervyn.test;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

import java.util.*;

/**
 * <p>
 * MyBatis-Plus 的代码生成器，通过 MyBatisPlusCodeGenerator 可以快速生成
 * Entity、Mapper、Mapper XML、Service、Controller 等各个模块的代码
 * </p>
 * @author HaoMaoxiang@126.com
 * @since 2020/2/18
 */
public class MyBatisPlusCodeGenerator {

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入" + tip + "：");
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    private static AutoGenerator initConfig(String modelName, String tableName) {

        String parent = "tech.mervyn";

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");    // 生成文件的输出目录
        gc.setFileOverride(true);                           // 是否覆盖已有文件，默认值：false
        gc.setOpen(false);                                  // 是否打开输出目录，默认值：true
        gc.setDateType(DateType.ONLY_DATE);                 // 时间类型对应策略
        gc.setEntityName("%sEntity");                       // 实体命名方式，例如：%sEntity 生成 UserEntity
        gc.setServiceName("%sService");                     // service 命名方式，默认值：null 例如：%sBusiness 生成 UserBusiness
        gc.setIdType(IdType.ASSIGN_ID);                     // 指定生成的主键的ID类型
        gc.setAuthor("haomaoxiang@126.com");
//        gc.setSwagger2(true);                               // 实体属性 Swagger2 注解
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/test?useUnicode=true&useSSL=false&characterEncoding=utf8");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root@2020");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(parent);                              // 父包名。如果为空，将下面子包名必须写全部， 否则就只需写子包名
        pc.setModuleName("");                                   // 父包模块名
        pc.setEntity("dao.entity." + modelName);                // Entity包名
        pc.setMapper("dao.mapper." + modelName);                // Mapper包名
        pc.setService("service." + modelName);                  // Service包名
        pc.setServiceImpl("service." + modelName + ".impl");    // Service Impl包名
        pc.setController("controller." + modelName);            // Controller包名
        mpg.setPackageInfo(pc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);          // 数据库表映射到实体的命名策略
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);    // 数据库表字段映射到实体的命名策略, 未指定按照 naming 执行
        strategy.setEntityLombokModel(true);                            // 是否为lombok模型（默认 false）
        strategy.setEntityBuilderModel(true);                           // 是否为构建者模型（默认 false）
        strategy.setRestControllerStyle(true);                         // 生成 @RestController 控制器
        strategy.setControllerMappingHyphenStyle(true);                 // 驼峰转连字符
        strategy.setEntityTableFieldAnnotationEnable(true);             // 是否生成实体时，生成字段注解
        strategy.setInclude(tableName);
//        strategy.setTablePrefix("表前缀");                               // 表前缀
//        strategy.setFieldPrefix("fieldPrefix");                         // 字段前缀
//        strategy.setSuperEntityClass(parent + ".common.entity.BaseEntity");
//        strategy.setSuperEntityColumns("id");                           // 自定义基础的Entity类，公共字段
//        strategy.setSuperMapperClass("");                               // 自定义继承的Mapper类全称，带包名
//        strategy.setSuperServiceClass("");                              // 自定义继承的Service类全称，带包名
//        strategy.setSuperServiceImplClass("");                          // 自定义继承的ServiceImpl类全称，带包名
//        strategy.setSuperControllerClass("");                           // 自定义继承的Controller类全称，带包名
//        strategy.setLogicDeleteFieldName("del_flag");                   // 逻辑删除属性名称
        mpg.setStrategy(strategy);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
                String camelTableName = underlineToCamel(tableName);

                Map<String, Object> map = new HashMap<>();
                // 实体对象名称
                map.put("entityObjectName", camelTableName);
                // service对象名称
                map.put("serviceObjectName", camelTableName + "Service");
                // ResponseVo
                map.put("parent", parent);
                // model名字
                map.put("modelName", modelName);
                this.setMap(map);
            }
        };

        // 如果模板引擎是 freemarker
//        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/" + modelName
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        templateConfig.setController("templates/controller.java");
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);
        mpg.setTemplateEngine(new VelocityTemplateEngine());

        return mpg;
    }

    public static void main(String[] args) {

        String modelName = scanner("模块名");
        String[] tableNames = scanner("表名，多个英文逗号分割").split(",");

        for (String tableName : tableNames) {
            AutoGenerator mpg = initConfig(modelName, tableName);
            mpg.execute();
        }
    }

    /**
     * 下划线转成驼峰命名
     * sys_user --> sysUser
     */
    public static String underlineToCamel(String underline) {
        if (StringUtils.isNotBlank(underline)) {
            return NamingStrategy.underlineToCamel(underline);
        }
        return null;
    }

    /**
     * 下划线转换成帕斯卡命名
     * sys_user --> SysUser
     */
    public static String underlineToPascal(String underline) {
        if (StringUtils.isNotBlank(underline)) {
            return NamingStrategy.capitalFirst(NamingStrategy.underlineToCamel(underline));
        }
        return null;
    }

    /**
     * 下划线转换成冒号连接命名
     * sys_user --> sys:user
     */
    public static String underlineToColon(String underline) {
        if (StringUtils.isNotBlank(underline)) {
            String string = underline.toLowerCase();
            return string.replaceAll("_", ":");
        }
        return null;
    }


}
