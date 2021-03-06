# 简介

> spring-boot-init是一个基于SpringBoot2.0，集成了常用开发组件的后台开发框架。

此项目借鉴于[spring-boot-plus](https://gitee.com/geekidea/spring-boot-plus)

# 主要特性

1. 集成了常用组件，AOP日志等
2. 集成MybatisPlus，自动生产dao、mapper、service以及controller代码
3. 集成druid连接池，JDBC性能和慢查询检测
4. 集成swagger2，自动生成api文档
5. 集成spring boot admin，实时检测项目运行情况
6. 持续更新

# 项目环境

依赖 | 版本 | 备注
---- | ---- | ----
JAVA | 1.8+ | JDK1.8及以上
SpringBoot | 2.2.4.RELEASE | 最新发布稳定版
MySql | 5.7+ | 5.7及以上

# 技术选型
技术 | 版本 | 备注
---- | ---- | ----
MyBatis Plus | 3.3.1.tmp | MyBatis增强框架
Alibaba Druid | 1.1.21 | 数据库连接池
fastjson | 1.2.62 | JSON处理工具
swagger2 | 2.9.2 | API文档生成工具
lombok | 1.18.12 | 注解生成Java Bean等工具
commons-lang3 |	3.9 | 常用工具包
commons-io | 2.6 | IO工具包
commons-codec | 1.14 | 加密解密等工具包
commons-collections4 | 4.4 | 集合工具包
reflections | 0.9.12 | 反射工具包
hibernate-validator | 6.1.2.Final | 后台参数校验注解
mapstruct-jdk8 | 1.3.1.Final | 对象属性复制工具

# 项目部署

# 代码生成
## 1. 数据库初始化
```mysql
DROP TABLE IF EXISTS user;

CREATE TABLE user
(
	id BIGINT(20) NOT NULL COMMENT '主键ID',
	name VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
	age INT(11) NULL DEFAULT NULL COMMENT '年龄',
	email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
	PRIMARY KEY (id)
);

DELETE FROM user WHERE 1;

INSERT INTO user (id, name, age, email) VALUES
(1, 'Jone', 18, 'test1@baomidou.com'),
(2, 'Jack', 20, 'test2@baomidou.com'),
(3, 'Tom', 28, 'test3@baomidou.com'),
(4, 'Sandy', 21, 'test4@baomidou.com'),
(5, 'Billie', 24, 'test5@baomidou.com');
```

## 2. 自动生成CURD代码

> 1. 修改数据库链接信息，以及作者
> 2. 根据需求更改配置信息

```java
/**
 * <p>
 * src/test/java/me/mervyn/test/MyBatisPlusCodeGenerator.java
 * 
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

    public static void main(String[] args) {
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
        pc.setParent("tech.mervyn");                              // 父包名。如果为空，将下面子包名必须写全部， 否则就只需写子包名
        pc.setModuleName("");                                   // 父包模块名
        String modelName = scanner("模块名");
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
        strategy.setRestControllerStyle(false);                         // 生成 @RestController 控制器
        strategy.setControllerMappingHyphenStyle(true);                 // 驼峰转连字符
        strategy.setEntityTableFieldAnnotationEnable(true);             // 是否生成实体时，生成字段注解
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
//        strategy.setTablePrefix("表前缀");                               // 表前缀
//        strategy.setFieldPrefix("fieldPrefix");                         // 字段前缀
//        strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
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
        // templateConfig.setController();
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);
        mpg.setTemplateEngine(new VelocityTemplateEngine());

        mpg.execute();
    }

}
```

> 运行MyBatisPlusCodeGenerator的main方法

<img src="http://q5wi0ugwv.bkt.clouddn.com/spring-boot-init-01.png"/>

> 生成的代码结构

<img src="http://q5wi0ugwv.bkt.clouddn.com/spring-boot-init-02.png"/>