package com.yeaile.web.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.google.common.base.Splitter;
import com.yeaile.web.generator.config.CustomFileOutConfig;
import com.yeaile.web.generator.config.CustomMySqlTypeConvert;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;
/**
 * @param
 * @author sulaymanyf
 * @Description
 * @date 2019/12/19
 * @return
 **/
public class MybatisGenerator {


    private static final Logger log = LoggerFactory.getLogger(MybatisGenerator.class);
    private static Pattern DM_PATTERN = Pattern.compile("[,;]");
    private String configPath = "generator-config.properties";
    private String driverName;
    private String username;
    private String password;
    private String url;
    private String projectName;
    private String projectRootPath;
    private List<String> tableNames;
    private Boolean fileOverride = false;
    private Boolean ignoreTablePrefix = false;
    private Boolean entityLombokModel = true;
    private List<String> exclusionEntitySuperColumns;
    private Boolean isExclusionEntitySuperColumn = false;
    private Boolean isDeleteFilePrefixModule = false;
    private Boolean isCreateService = true;
    private Boolean isCreateController = true;
    private Boolean isCreateVO = true;
    private String author;
    private String moduleName;
    private String basePackage;
    private String baseOutputDir;
    private String dtoPackage;
    private String dtoSuperClass;
    private String dtoFileName;
    private String dtoOutputDir;
    private String dtoPackageName;
    private String voPackage;
    private String voFileName;
    private String voOutputDir;
    private String voPackageName;
    private String entityPackage;
    private String entitySuperClass;
    private String entityFileName;
    private String entityOutputDir;
    private String entityPackageName;
    private String servicePackage;
    private String serviceSuperClass;
    private String serviceFileName;
    private String serviceOutputDir;
    private String servicePackageName;
    private String serviceImplPackage;
    private String serviceImplSuperClass;
    private String serviceImplFileName;
    private String serviceImplOutputDir;
    private String serviceImplPackageName;
    private String controllerPackage;
    private String controllerSuperClass;
    private String controllerFileName;
    private String controllerOutputDir;
    private String controllerPackageName;
    private String daoPackage;
    private String daoSuperClass;
    private String daoFileName;
    private String daoOutputDir;
    private String daoPackageName;
    private String mapperPackage;
    private String mapperFileName;
    private String mapperOutputDir;
    private String mapperPackageName;
    private StrategyConfig strategyConfig;
    private GlobalConfig globalConfig;
    private AutoGenerator autoGenerator = new AutoGenerator();
    private DataSourceConfig dataSourceConfig;
    private InjectionConfig injectionConfig;
    private PackageConfig packageConfig;
    private List<FileOutConfig> fileOutConfigs = new ArrayList();

    public MybatisGenerator() {
        this.init();
    }

    public MybatisGenerator(String configPath) {
        this.configPath = configPath;
        this.init();
    }

    public void init() {
        this.loadConfig();
        this.globalConfig = new GlobalConfig();
        this.globalConfig.setOutputDir(this.projectRootPath);
        this.globalConfig.setFileOverride(this.fileOverride);
        this.globalConfig.setActiveRecord(true);
        this.globalConfig.setEnableCache(false);
        this.globalConfig.setBaseResultMap(true);
        this.globalConfig.setBaseColumnList(true);
        this.globalConfig.setAuthor(this.author);
        this.autoGenerator.setGlobalConfig(this.globalConfig);
        this.dataSourceConfig = new DataSourceConfig();
        if (this.driverName.toLowerCase().contains(DbType.MYSQL.getDb())) {
            this.dataSourceConfig.setDbType(DbType.MYSQL);
        } else {
            this.dataSourceConfig.setDbType(DbType.ORACLE);
        }

        this.dataSourceConfig.setTypeConvert(new CustomMySqlTypeConvert());
        this.dataSourceConfig.setDriverName(this.driverName);
        this.dataSourceConfig.setUsername(this.username);
        this.dataSourceConfig.setPassword(this.password);
        this.dataSourceConfig.setUrl(this.url);
        this.autoGenerator.setDataSource(this.dataSourceConfig);
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setInclude((String[])((String[])this.tableNames.toArray(new String[this.tableNames.size()])));
        strategy.setEntityLombokModel(this.entityLombokModel);
        this.autoGenerator.setStrategy(strategy);
        this.packageConfig = new PackageConfig();
        this.packageConfig.setModuleName(this.moduleName);
        this.autoGenerator.setPackageInfo(this.packageConfig);
        final Map<String, Object> map = new HashMap(3);
        map.put("isExclusionEntitySuperColumn", this.isExclusionEntitySuperColumn);
        map.put("exclusionEntitySuperColumns", this.exclusionEntitySuperColumns);
        map.put("moduleName", this.moduleName);
        this.injectionConfig = new InjectionConfig(){

            @Override
            public void initMap() {
                setMap(map);
            }
        };

        CustomFileOutConfig dtoFileConfig = new CustomFileOutConfig("/mybatis/templates/dto.java.vm", this.dtoPackage, this.dtoPackageName, this.dtoFileName, this.dtoOutputDir, "domain", this.isDeleteFilePrefixModule, this.injectionConfig, this.packageConfig);
        dtoFileConfig.setSuperClass(this.dtoSuperClass);
        dtoFileConfig.setIgnoreTablePrefix(this.ignoreTablePrefix);
        this.fileOutConfigs.add(dtoFileConfig);
        CustomFileOutConfig entityFileConfig = new CustomFileOutConfig("/mybatis/templates/entity.java.vm", this.entityPackage, this.entityPackageName, this.entityFileName, this.entityOutputDir, "entity", this.isDeleteFilePrefixModule, this.injectionConfig, this.packageConfig);
        entityFileConfig.setSuperClass(this.entitySuperClass);
        entityFileConfig.setIgnoreTablePrefix(this.ignoreTablePrefix);
        this.fileOutConfigs.add(entityFileConfig);
        CustomFileOutConfig daoFileConfig = new CustomFileOutConfig("/mybatis/templates/dao.java.vm", this.daoPackage, this.daoPackageName, this.daoFileName, this.daoOutputDir, "dao", this.isDeleteFilePrefixModule, this.injectionConfig, this.packageConfig);
        daoFileConfig.setSuperClass(this.daoSuperClass);
        daoFileConfig.setIgnoreTablePrefix(this.ignoreTablePrefix);
        this.fileOutConfigs.add(daoFileConfig);
        CustomFileOutConfig mapperFileConfig = new CustomFileOutConfig("/mybatis/templates/mapper.xml.vm", this.mapperPackage, this.mapperPackageName, this.mapperFileName, this.mapperOutputDir, "mapper", this.isDeleteFilePrefixModule, this.injectionConfig, this.packageConfig);
        mapperFileConfig.setIgnoreTablePrefix(this.ignoreTablePrefix);
        this.fileOutConfigs.add(mapperFileConfig);
        CustomFileOutConfig controllerFileConfig;
        if (this.isCreateService) {
            controllerFileConfig = new CustomFileOutConfig("/mybatis/templates/service.java.vm", this.servicePackage, this.servicePackageName, this.serviceFileName, this.serviceOutputDir, "service", this.isDeleteFilePrefixModule, this.injectionConfig, this.packageConfig);
            controllerFileConfig.setSuperClass(this.serviceSuperClass);
            controllerFileConfig.setIgnoreTablePrefix(this.ignoreTablePrefix);
            this.fileOutConfigs.add(controllerFileConfig);
            CustomFileOutConfig serviceImplFileConfig = new CustomFileOutConfig("/mybatis/templates/serviceImpl.java.vm", this.serviceImplPackage, this.serviceImplPackageName, this.serviceImplFileName, this.serviceImplOutputDir, "serviceImpl", this.isDeleteFilePrefixModule, this.injectionConfig, this.packageConfig);
            serviceImplFileConfig.setSuperClass(this.serviceImplSuperClass);
            serviceImplFileConfig.setIgnoreTablePrefix(this.ignoreTablePrefix);
            this.fileOutConfigs.add(serviceImplFileConfig);
        }

        if (this.isCreateVO) {
            controllerFileConfig = new CustomFileOutConfig("/mybatis/templates/vo.java.vm", this.voPackage, this.voPackageName, this.voFileName, this.voOutputDir, "vo", this.isDeleteFilePrefixModule, this.injectionConfig, this.packageConfig);
            controllerFileConfig.setIgnoreTablePrefix(this.ignoreTablePrefix);
            this.fileOutConfigs.add(controllerFileConfig);
        }

        if (this.isCreateController) {
            controllerFileConfig = new CustomFileOutConfig("/mybatis/templates/controller.java.vm", this.controllerPackage, this.controllerPackageName, this.controllerFileName, this.controllerOutputDir, "user", this.isDeleteFilePrefixModule, this.injectionConfig, this.packageConfig);
            controllerFileConfig.setSuperClass(this.controllerSuperClass);
            controllerFileConfig.setIgnoreTablePrefix(this.ignoreTablePrefix);
            this.fileOutConfigs.add(controllerFileConfig);
        }

        this.injectionConfig.setFileOutConfigList(this.fileOutConfigs);
        this.autoGenerator.setCfg(this.injectionConfig);
        TemplateConfig tc = new TemplateConfig();
        tc.setXml((String)null);
        tc.setController((String)null);
        tc.setService((String)null);
        tc.setServiceImpl((String)null);
        tc.setEntity((String)null);
        tc.setMapper((String)null);
        this.autoGenerator.setTemplate(tc);
    }

    public void loadConfig() {
        String configPath = "generator-config.properties";
        Resource resource = new ClassPathResource(configPath);
        if (resource.exists()) {
            Properties properties = new Properties();

            try {
                properties.load(resource.getInputStream());
                this.driverName = StringUtils.trimToNull(properties.getProperty("driverName"));
                this.url = StringUtils.trimToNull(properties.getProperty("url"));
                this.username = StringUtils.trimToNull(properties.getProperty("username"));
                this.password = StringUtils.trimToNull(properties.getProperty("password"));
                this.tableNames = Splitter.on(DM_PATTERN).omitEmptyStrings().trimResults().splitToList(StringUtils.trimToEmpty(properties.getProperty("tableNames")));
                this.exclusionEntitySuperColumns = Splitter.on(DM_PATTERN).omitEmptyStrings().trimResults().splitToList(StringUtils.trimToEmpty(properties.getProperty("exclusionEntitySuperColumns")));
                this.projectName = StringUtils.trimToNull(properties.getProperty("projectName"));
                this.projectRootPath = StringUtils.trimToNull(properties.getProperty("projectRootPath"));
                if (this.projectName != null) {
                    this.projectRootPath = this.getProjectRootPathByProjectName(this.projectName);
                }

                this.fileOverride = Boolean.valueOf((String)StringUtils.defaultIfEmpty(properties.getProperty("fileOverride"), "false"));
                this.ignoreTablePrefix = Boolean.valueOf((String)StringUtils.defaultIfEmpty(properties.getProperty("ignoreTablePrefix"), "false"));
                this.entityLombokModel = Boolean.valueOf((String)StringUtils.defaultIfEmpty(properties.getProperty("entityLombokModel"), "true"));
                this.isCreateService = Boolean.valueOf((String)StringUtils.defaultIfEmpty(properties.getProperty("isCreateService"), "true"));
                this.isCreateVO = Boolean.valueOf((String)StringUtils.defaultIfEmpty(properties.getProperty("isCreateVO"), "true"));
                this.isCreateController = Boolean.valueOf((String)StringUtils.defaultIfEmpty(properties.getProperty("isCreateController"), "true"));
                this.isDeleteFilePrefixModule = Boolean.valueOf((String)StringUtils.defaultIfEmpty(properties.getProperty("isDeleteFilePrefixModule"), "false"));
                this.author = StringUtils.trimToNull(properties.getProperty("author"));
                this.moduleName = StringUtils.trimToNull(properties.getProperty("moduleName"));
                this.basePackage = StringUtils.trimToNull(properties.getProperty("basePackage"));
                this.baseOutputDir = StringUtils.trimToNull(properties.getProperty("baseOutputDir"));
                this.dtoPackage = (String)StringUtils.defaultIfEmpty(StringUtils.trimToNull(properties.getProperty("dtoPackage")), this.basePackage);
                this.dtoSuperClass = StringUtils.trimToNull(properties.getProperty("dtoSuperClass"));
                this.dtoFileName = (String)StringUtils.defaultIfEmpty(StringUtils.trimToNull(properties.getProperty("dtoFileName")), "%sDTO.java");
                this.dtoOutputDir = this.projectRootPath + (String)StringUtils.defaultIfEmpty(StringUtils.trimToNull(properties.getProperty("dtoOutputDir")), this.baseOutputDir);
                this.dtoPackageName = (String)StringUtils.defaultIfEmpty(StringUtils.trimToNull(properties.getProperty("dtoPackageName")), "domain");
                this.voPackage = (String)StringUtils.defaultIfEmpty(StringUtils.trimToNull(properties.getProperty("voPackage")), this.basePackage);
                this.voFileName = (String)StringUtils.defaultIfEmpty(StringUtils.trimToNull(properties.getProperty("voFileName")), "%sVO.java");
                this.voOutputDir = this.projectRootPath + (String)StringUtils.defaultIfEmpty(StringUtils.trimToNull(properties.getProperty("voOutputDir")), this.baseOutputDir);
                this.voPackageName = (String)StringUtils.defaultIfEmpty(StringUtils.trimToNull(properties.getProperty("voPackageName")), "vo");
                this.entityPackage = (String)StringUtils.defaultIfEmpty(StringUtils.trimToNull(properties.getProperty("entityPackage")), this.basePackage);
                this.entitySuperClass = StringUtils.trimToNull(properties.getProperty("entitySuperClass"));
                this.entityFileName = (String)StringUtils.defaultIfEmpty(StringUtils.trimToNull(properties.getProperty("entityFileName")), "%s.java");
                this.entityOutputDir = this.projectRootPath + (String)StringUtils.defaultIfEmpty(StringUtils.trimToNull(properties.getProperty("entityOutputDir")), this.baseOutputDir);
                this.entityPackageName = (String)StringUtils.defaultIfEmpty(StringUtils.trimToNull(properties.getProperty("entityPackageName")), "entity");
                this.servicePackage = (String)StringUtils.defaultIfEmpty(StringUtils.trimToNull(properties.getProperty("servicePackage")), this.basePackage);
                this.serviceSuperClass = StringUtils.trimToNull(properties.getProperty("serviceSuperClass"));
                this.serviceFileName = (String)StringUtils.defaultIfEmpty(StringUtils.trimToNull(properties.getProperty("serviceFileName")), "I%sService.java");
                this.serviceOutputDir = this.projectRootPath + (String)StringUtils.defaultIfEmpty(StringUtils.trimToNull(properties.getProperty("serviceOutputDir")), this.baseOutputDir);
                this.servicePackageName = (String)StringUtils.defaultIfEmpty(StringUtils.trimToNull(properties.getProperty("servicePackageName")), "service");
                this.serviceImplPackage = (String)StringUtils.defaultIfEmpty(StringUtils.trimToNull(properties.getProperty("serviceImplPackage")), this.basePackage);
                this.serviceImplSuperClass = StringUtils.trimToNull(properties.getProperty("serviceImplSuperClass"));
                this.serviceImplFileName = (String)StringUtils.defaultIfEmpty(StringUtils.trimToNull(properties.getProperty("serviceImplFileName")), "%sServiceImpl.java");
                this.serviceImplOutputDir = this.projectRootPath + (String)StringUtils.defaultIfEmpty(StringUtils.trimToNull(properties.getProperty("serviceImplOutputDir")), this.baseOutputDir);
                this.serviceImplPackageName = (String)StringUtils.defaultIfEmpty(StringUtils.trimToNull(properties.getProperty("serviceImplPackageName")), "service.impl");
                this.controllerPackage = (String)StringUtils.defaultIfEmpty(StringUtils.trimToNull(properties.getProperty("controllerPackage")), this.basePackage);
                this.controllerSuperClass = StringUtils.trimToNull(properties.getProperty("controllerSuperClass"));
                this.controllerFileName = (String)StringUtils.defaultIfEmpty(StringUtils.trimToNull(properties.getProperty("controllerFileName")), "%sController.java");
                this.controllerOutputDir = this.projectRootPath + (String)StringUtils.defaultIfEmpty(StringUtils.trimToNull(properties.getProperty("controllerOutputDir")), this.baseOutputDir);
                this.controllerPackageName = (String)StringUtils.defaultIfEmpty(StringUtils.trimToNull(properties.getProperty("controllerPackageName")), "user");
                this.daoPackage = (String)StringUtils.defaultIfEmpty(StringUtils.trimToNull(properties.getProperty("daoPackage")), this.basePackage);
                this.daoSuperClass = (String)StringUtils.defaultIfBlank(StringUtils.trimToNull(properties.getProperty("daoSuperClass")), "com.baomidou.mybatisplus.core.mapper.BaseMapper");
                this.daoFileName = (String)StringUtils.defaultIfEmpty(StringUtils.trimToNull(properties.getProperty("daoFileName")), "%sMapper.java");
                this.daoOutputDir = this.projectRootPath + (String)StringUtils.defaultIfEmpty(StringUtils.trimToNull(properties.getProperty("daoOutputDir")), this.baseOutputDir);
                this.daoPackageName = (String)StringUtils.defaultIfEmpty(StringUtils.trimToNull(properties.getProperty("daoPackageName")), "mapper");
                this.mapperPackage = (String)StringUtils.defaultIfEmpty(StringUtils.trimToNull(properties.getProperty("mapperPackage")), this.basePackage);
                this.mapperFileName = (String)StringUtils.defaultIfEmpty(StringUtils.trimToNull(properties.getProperty("mapperFileName")), "%sMapper.xml");
                this.mapperOutputDir = this.projectRootPath + (String)StringUtils.defaultIfEmpty(StringUtils.trimToNull(properties.getProperty("mapperOutputDir")), this.baseOutputDir);
                this.mapperPackageName = (String)StringUtils.defaultIfEmpty(StringUtils.trimToNull(properties.getProperty("mapperPackageName")), "mapper");
                if (StringUtils.isNotEmpty(this.entitySuperClass) && !CollectionUtils.isEmpty(this.exclusionEntitySuperColumns)) {
                    this.isExclusionEntitySuperColumn = true;
                }
            } catch (IOException var12) {
                throw new RuntimeException(var12);
            } finally {
                try {
                    resource.getInputStream().close();
                } catch (IOException var11) {
                    log.error(var11.getMessage());
                }

            }

            Assert.notNull(this.driverName, "driverName参数不为空");
            Assert.notNull(this.url, "url参数不为空");
            Assert.notNull(this.username, "username参数不为空");
            Assert.notNull(this.password, "password参数不为空");
            Assert.notNull(this.projectRootPath, "projectRootPath或projectName参数不为空，参数二选一");
            Assert.notEmpty(this.tableNames, "tableNames参数不为空");
            Assert.notNull(this.dtoOutputDir, "dtoOutputDir参数不为空");
            Assert.notNull(this.dtoPackage, "dtoPackage参数不为空");
            Assert.notNull(this.entityOutputDir, "entityOutputDir参数不为空");
            Assert.notNull(this.entityPackage, "entityPackage参数不为空");
        } else {
            throw new RuntimeException(configPath + "配置不存在");
        }
    }

    public void execute() {
        this.autoGenerator.execute();
    }

    public String getProjectRootPathByProjectName(String projectName) {
        String path = MybatisGenerator.class.getResource("/").getPath();
        int index = path.indexOf(projectName);
        return path.substring(1, index) + projectName + "/";
    }

    public String getConfigPath() {
        return this.configPath;
    }

    public String getDriverName() {
        return this.driverName;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getUrl() {
        return this.url;
    }

    public String getProjectName() {
        return this.projectName;
    }

    public String getProjectRootPath() {
        return this.projectRootPath;
    }

    public List<String> getTableNames() {
        return this.tableNames;
    }

    public Boolean getFileOverride() {
        return this.fileOverride;
    }

    public Boolean getIgnoreTablePrefix() {
        return this.ignoreTablePrefix;
    }

    public Boolean getEntityLombokModel() {
        return this.entityLombokModel;
    }

    public List<String> getExclusionEntitySuperColumns() {
        return this.exclusionEntitySuperColumns;
    }

    public Boolean getIsExclusionEntitySuperColumn() {
        return this.isExclusionEntitySuperColumn;
    }

    public Boolean getIsDeleteFilePrefixModule() {
        return this.isDeleteFilePrefixModule;
    }

    public Boolean getIsCreateService() {
        return this.isCreateService;
    }

    public Boolean getIsCreateController() {
        return this.isCreateController;
    }

    public Boolean getIsCreateVO() {
        return this.isCreateVO;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getModuleName() {
        return this.moduleName;
    }

    public String getBasePackage() {
        return this.basePackage;
    }

    public String getBaseOutputDir() {
        return this.baseOutputDir;
    }

    public String getDtoPackage() {
        return this.dtoPackage;
    }

    public String getDtoSuperClass() {
        return this.dtoSuperClass;
    }

    public String getDtoFileName() {
        return this.dtoFileName;
    }

    public String getDtoOutputDir() {
        return this.dtoOutputDir;
    }

    public String getDtoPackageName() {
        return this.dtoPackageName;
    }

    public String getVoPackage() {
        return this.voPackage;
    }

    public String getVoFileName() {
        return this.voFileName;
    }

    public String getVoOutputDir() {
        return this.voOutputDir;
    }

    public String getVoPackageName() {
        return this.voPackageName;
    }

    public String getEntityPackage() {
        return this.entityPackage;
    }

    public String getEntitySuperClass() {
        return this.entitySuperClass;
    }

    public String getEntityFileName() {
        return this.entityFileName;
    }

    public String getEntityOutputDir() {
        return this.entityOutputDir;
    }

    public String getEntityPackageName() {
        return this.entityPackageName;
    }

    public String getServicePackage() {
        return this.servicePackage;
    }

    public String getServiceSuperClass() {
        return this.serviceSuperClass;
    }

    public String getServiceFileName() {
        return this.serviceFileName;
    }

    public String getServiceOutputDir() {
        return this.serviceOutputDir;
    }

    public String getServicePackageName() {
        return this.servicePackageName;
    }

    public String getServiceImplPackage() {
        return this.serviceImplPackage;
    }

    public String getServiceImplSuperClass() {
        return this.serviceImplSuperClass;
    }

    public String getServiceImplFileName() {
        return this.serviceImplFileName;
    }

    public String getServiceImplOutputDir() {
        return this.serviceImplOutputDir;
    }

    public String getServiceImplPackageName() {
        return this.serviceImplPackageName;
    }

    public String getControllerPackage() {
        return this.controllerPackage;
    }

    public String getControllerSuperClass() {
        return this.controllerSuperClass;
    }

    public String getControllerFileName() {
        return this.controllerFileName;
    }

    public String getControllerOutputDir() {
        return this.controllerOutputDir;
    }

    public String getControllerPackageName() {
        return this.controllerPackageName;
    }

    public String getDaoPackage() {
        return this.daoPackage;
    }

    public String getDaoSuperClass() {
        return this.daoSuperClass;
    }

    public String getDaoFileName() {
        return this.daoFileName;
    }

    public String getDaoOutputDir() {
        return this.daoOutputDir;
    }

    public String getDaoPackageName() {
        return this.daoPackageName;
    }

    public String getMapperPackage() {
        return this.mapperPackage;
    }

    public String getMapperFileName() {
        return this.mapperFileName;
    }

    public String getMapperOutputDir() {
        return this.mapperOutputDir;
    }

    public String getMapperPackageName() {
        return this.mapperPackageName;
    }

    public StrategyConfig getStrategyConfig() {
        return this.strategyConfig;
    }

    public GlobalConfig getGlobalConfig() {
        return this.globalConfig;
    }

    public AutoGenerator getAutoGenerator() {
        return this.autoGenerator;
    }

    public DataSourceConfig getDataSourceConfig() {
        return this.dataSourceConfig;
    }

    public InjectionConfig getInjectionConfig() {
        return this.injectionConfig;
    }

    public PackageConfig getPackageConfig() {
        return this.packageConfig;
    }

    public List<FileOutConfig> getFileOutConfigs() {
        return this.fileOutConfigs;
    }

    public void setConfigPath(final String configPath) {
        this.configPath = configPath;
    }

    public void setDriverName(final String driverName) {
        this.driverName = driverName;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public void setProjectName(final String projectName) {
        this.projectName = projectName;
    }

    public void setProjectRootPath(final String projectRootPath) {
        this.projectRootPath = projectRootPath;
    }

    public void setTableNames(final List<String> tableNames) {
        this.tableNames = tableNames;
    }

    public void setFileOverride(final Boolean fileOverride) {
        this.fileOverride = fileOverride;
    }

    public void setIgnoreTablePrefix(final Boolean ignoreTablePrefix) {
        this.ignoreTablePrefix = ignoreTablePrefix;
    }

    public void setEntityLombokModel(final Boolean entityLombokModel) {
        this.entityLombokModel = entityLombokModel;
    }

    public void setExclusionEntitySuperColumns(final List<String> exclusionEntitySuperColumns) {
        this.exclusionEntitySuperColumns = exclusionEntitySuperColumns;
    }

    public void setIsExclusionEntitySuperColumn(final Boolean isExclusionEntitySuperColumn) {
        this.isExclusionEntitySuperColumn = isExclusionEntitySuperColumn;
    }

    public void setIsDeleteFilePrefixModule(final Boolean isDeleteFilePrefixModule) {
        this.isDeleteFilePrefixModule = isDeleteFilePrefixModule;
    }

    public void setIsCreateService(final Boolean isCreateService) {
        this.isCreateService = isCreateService;
    }

    public void setIsCreateController(final Boolean isCreateController) {
        this.isCreateController = isCreateController;
    }

    public void setIsCreateVO(final Boolean isCreateVO) {
        this.isCreateVO = isCreateVO;
    }

    public void setAuthor(final String author) {
        this.author = author;
    }

    public void setModuleName(final String moduleName) {
        this.moduleName = moduleName;
    }

    public void setBasePackage(final String basePackage) {
        this.basePackage = basePackage;
    }

    public void setBaseOutputDir(final String baseOutputDir) {
        this.baseOutputDir = baseOutputDir;
    }

    public void setDtoPackage(final String dtoPackage) {
        this.dtoPackage = dtoPackage;
    }

    public void setDtoSuperClass(final String dtoSuperClass) {
        this.dtoSuperClass = dtoSuperClass;
    }

    public void setDtoFileName(final String dtoFileName) {
        this.dtoFileName = dtoFileName;
    }

    public void setDtoOutputDir(final String dtoOutputDir) {
        this.dtoOutputDir = dtoOutputDir;
    }

    public void setDtoPackageName(final String dtoPackageName) {
        this.dtoPackageName = dtoPackageName;
    }

    public void setVoPackage(final String voPackage) {
        this.voPackage = voPackage;
    }

    public void setVoFileName(final String voFileName) {
        this.voFileName = voFileName;
    }

    public void setVoOutputDir(final String voOutputDir) {
        this.voOutputDir = voOutputDir;
    }

    public void setVoPackageName(final String voPackageName) {
        this.voPackageName = voPackageName;
    }

    public void setEntityPackage(final String entityPackage) {
        this.entityPackage = entityPackage;
    }

    public void setEntitySuperClass(final String entitySuperClass) {
        this.entitySuperClass = entitySuperClass;
    }

    public void setEntityFileName(final String entityFileName) {
        this.entityFileName = entityFileName;
    }

    public void setEntityOutputDir(final String entityOutputDir) {
        this.entityOutputDir = entityOutputDir;
    }

    public void setEntityPackageName(final String entityPackageName) {
        this.entityPackageName = entityPackageName;
    }

    public void setServicePackage(final String servicePackage) {
        this.servicePackage = servicePackage;
    }

    public void setServiceSuperClass(final String serviceSuperClass) {
        this.serviceSuperClass = serviceSuperClass;
    }

    public void setServiceFileName(final String serviceFileName) {
        this.serviceFileName = serviceFileName;
    }

    public void setServiceOutputDir(final String serviceOutputDir) {
        this.serviceOutputDir = serviceOutputDir;
    }

    public void setServicePackageName(final String servicePackageName) {
        this.servicePackageName = servicePackageName;
    }

    public void setServiceImplPackage(final String serviceImplPackage) {
        this.serviceImplPackage = serviceImplPackage;
    }

    public void setServiceImplSuperClass(final String serviceImplSuperClass) {
        this.serviceImplSuperClass = serviceImplSuperClass;
    }

    public void setServiceImplFileName(final String serviceImplFileName) {
        this.serviceImplFileName = serviceImplFileName;
    }

    public void setServiceImplOutputDir(final String serviceImplOutputDir) {
        this.serviceImplOutputDir = serviceImplOutputDir;
    }

    public void setServiceImplPackageName(final String serviceImplPackageName) {
        this.serviceImplPackageName = serviceImplPackageName;
    }

    public void setControllerPackage(final String controllerPackage) {
        this.controllerPackage = controllerPackage;
    }

    public void setControllerSuperClass(final String controllerSuperClass) {
        this.controllerSuperClass = controllerSuperClass;
    }

    public void setControllerFileName(final String controllerFileName) {
        this.controllerFileName = controllerFileName;
    }

    public void setControllerOutputDir(final String controllerOutputDir) {
        this.controllerOutputDir = controllerOutputDir;
    }

    public void setControllerPackageName(final String controllerPackageName) {
        this.controllerPackageName = controllerPackageName;
    }

    public void setDaoPackage(final String daoPackage) {
        this.daoPackage = daoPackage;
    }

    public void setDaoSuperClass(final String daoSuperClass) {
        this.daoSuperClass = daoSuperClass;
    }

    public void setDaoFileName(final String daoFileName) {
        this.daoFileName = daoFileName;
    }

    public void setDaoOutputDir(final String daoOutputDir) {
        this.daoOutputDir = daoOutputDir;
    }

    public void setDaoPackageName(final String daoPackageName) {
        this.daoPackageName = daoPackageName;
    }

    public void setMapperPackage(final String mapperPackage) {
        this.mapperPackage = mapperPackage;
    }

    public void setMapperFileName(final String mapperFileName) {
        this.mapperFileName = mapperFileName;
    }

    public void setMapperOutputDir(final String mapperOutputDir) {
        this.mapperOutputDir = mapperOutputDir;
    }

    public void setMapperPackageName(final String mapperPackageName) {
        this.mapperPackageName = mapperPackageName;
    }

    public void setStrategyConfig(final StrategyConfig strategyConfig) {
        this.strategyConfig = strategyConfig;
    }

    public void setGlobalConfig(final GlobalConfig globalConfig) {
        this.globalConfig = globalConfig;
    }

    public void setAutoGenerator(final AutoGenerator autoGenerator) {
        this.autoGenerator = autoGenerator;
    }

    public void setDataSourceConfig(final DataSourceConfig dataSourceConfig) {
        this.dataSourceConfig = dataSourceConfig;
    }

    public void setInjectionConfig(final InjectionConfig injectionConfig) {
        this.injectionConfig = injectionConfig;
    }

    public void setPackageConfig(final PackageConfig packageConfig) {
        this.packageConfig = packageConfig;
    }

    public void setFileOutConfigs(final List<FileOutConfig> fileOutConfigs) {
        this.fileOutConfigs = fileOutConfigs;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof MybatisGenerator)) {
            return false;
        } else {
            MybatisGenerator other = (MybatisGenerator)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$configPath = this.getConfigPath();
                Object other$configPath = other.getConfigPath();
                if (this$configPath == null) {
                    if (other$configPath != null) {
                        return false;
                    }
                } else if (!this$configPath.equals(other$configPath)) {
                    return false;
                }

                Object this$driverName = this.getDriverName();
                Object other$driverName = other.getDriverName();
                if (this$driverName == null) {
                    if (other$driverName != null) {
                        return false;
                    }
                } else if (!this$driverName.equals(other$driverName)) {
                    return false;
                }

                Object this$username = this.getUsername();
                Object other$username = other.getUsername();
                if (this$username == null) {
                    if (other$username != null) {
                        return false;
                    }
                } else if (!this$username.equals(other$username)) {
                    return false;
                }

                label782: {
                    Object this$password = this.getPassword();
                    Object other$password = other.getPassword();
                    if (this$password == null) {
                        if (other$password == null) {
                            break label782;
                        }
                    } else if (this$password.equals(other$password)) {
                        break label782;
                    }

                    return false;
                }

                label775: {
                    Object this$url = this.getUrl();
                    Object other$url = other.getUrl();
                    if (this$url == null) {
                        if (other$url == null) {
                            break label775;
                        }
                    } else if (this$url.equals(other$url)) {
                        break label775;
                    }

                    return false;
                }

                Object this$projectName = this.getProjectName();
                Object other$projectName = other.getProjectName();
                if (this$projectName == null) {
                    if (other$projectName != null) {
                        return false;
                    }
                } else if (!this$projectName.equals(other$projectName)) {
                    return false;
                }

                label761: {
                    Object this$projectRootPath = this.getProjectRootPath();
                    Object other$projectRootPath = other.getProjectRootPath();
                    if (this$projectRootPath == null) {
                        if (other$projectRootPath == null) {
                            break label761;
                        }
                    } else if (this$projectRootPath.equals(other$projectRootPath)) {
                        break label761;
                    }

                    return false;
                }

                label754: {
                    Object this$tableNames = this.getTableNames();
                    Object other$tableNames = other.getTableNames();
                    if (this$tableNames == null) {
                        if (other$tableNames == null) {
                            break label754;
                        }
                    } else if (this$tableNames.equals(other$tableNames)) {
                        break label754;
                    }

                    return false;
                }

                Object this$fileOverride = this.getFileOverride();
                Object other$fileOverride = other.getFileOverride();
                if (this$fileOverride == null) {
                    if (other$fileOverride != null) {
                        return false;
                    }
                } else if (!this$fileOverride.equals(other$fileOverride)) {
                    return false;
                }

                Object this$ignoreTablePrefix = this.getIgnoreTablePrefix();
                Object other$ignoreTablePrefix = other.getIgnoreTablePrefix();
                if (this$ignoreTablePrefix == null) {
                    if (other$ignoreTablePrefix != null) {
                        return false;
                    }
                } else if (!this$ignoreTablePrefix.equals(other$ignoreTablePrefix)) {
                    return false;
                }

                label733: {
                    Object this$entityLombokModel = this.getEntityLombokModel();
                    Object other$entityLombokModel = other.getEntityLombokModel();
                    if (this$entityLombokModel == null) {
                        if (other$entityLombokModel == null) {
                            break label733;
                        }
                    } else if (this$entityLombokModel.equals(other$entityLombokModel)) {
                        break label733;
                    }

                    return false;
                }

                label726: {
                    Object this$exclusionEntitySuperColumns = this.getExclusionEntitySuperColumns();
                    Object other$exclusionEntitySuperColumns = other.getExclusionEntitySuperColumns();
                    if (this$exclusionEntitySuperColumns == null) {
                        if (other$exclusionEntitySuperColumns == null) {
                            break label726;
                        }
                    } else if (this$exclusionEntitySuperColumns.equals(other$exclusionEntitySuperColumns)) {
                        break label726;
                    }

                    return false;
                }

                Object this$isExclusionEntitySuperColumn = this.getIsExclusionEntitySuperColumn();
                Object other$isExclusionEntitySuperColumn = other.getIsExclusionEntitySuperColumn();
                if (this$isExclusionEntitySuperColumn == null) {
                    if (other$isExclusionEntitySuperColumn != null) {
                        return false;
                    }
                } else if (!this$isExclusionEntitySuperColumn.equals(other$isExclusionEntitySuperColumn)) {
                    return false;
                }

                label712: {
                    Object this$isDeleteFilePrefixModule = this.getIsDeleteFilePrefixModule();
                    Object other$isDeleteFilePrefixModule = other.getIsDeleteFilePrefixModule();
                    if (this$isDeleteFilePrefixModule == null) {
                        if (other$isDeleteFilePrefixModule == null) {
                            break label712;
                        }
                    } else if (this$isDeleteFilePrefixModule.equals(other$isDeleteFilePrefixModule)) {
                        break label712;
                    }

                    return false;
                }

                Object this$isCreateService = this.getIsCreateService();
                Object other$isCreateService = other.getIsCreateService();
                if (this$isCreateService == null) {
                    if (other$isCreateService != null) {
                        return false;
                    }
                } else if (!this$isCreateService.equals(other$isCreateService)) {
                    return false;
                }

                label698: {
                    Object this$isCreateController = this.getIsCreateController();
                    Object other$isCreateController = other.getIsCreateController();
                    if (this$isCreateController == null) {
                        if (other$isCreateController == null) {
                            break label698;
                        }
                    } else if (this$isCreateController.equals(other$isCreateController)) {
                        break label698;
                    }

                    return false;
                }

                Object this$isCreateVO = this.getIsCreateVO();
                Object other$isCreateVO = other.getIsCreateVO();
                if (this$isCreateVO == null) {
                    if (other$isCreateVO != null) {
                        return false;
                    }
                } else if (!this$isCreateVO.equals(other$isCreateVO)) {
                    return false;
                }

                Object this$author = this.getAuthor();
                Object other$author = other.getAuthor();
                if (this$author == null) {
                    if (other$author != null) {
                        return false;
                    }
                } else if (!this$author.equals(other$author)) {
                    return false;
                }

                Object this$moduleName = this.getModuleName();
                Object other$moduleName = other.getModuleName();
                if (this$moduleName == null) {
                    if (other$moduleName != null) {
                        return false;
                    }
                } else if (!this$moduleName.equals(other$moduleName)) {
                    return false;
                }

                label670: {
                    Object this$basePackage = this.getBasePackage();
                    Object other$basePackage = other.getBasePackage();
                    if (this$basePackage == null) {
                        if (other$basePackage == null) {
                            break label670;
                        }
                    } else if (this$basePackage.equals(other$basePackage)) {
                        break label670;
                    }

                    return false;
                }

                label663: {
                    Object this$baseOutputDir = this.getBaseOutputDir();
                    Object other$baseOutputDir = other.getBaseOutputDir();
                    if (this$baseOutputDir == null) {
                        if (other$baseOutputDir == null) {
                            break label663;
                        }
                    } else if (this$baseOutputDir.equals(other$baseOutputDir)) {
                        break label663;
                    }

                    return false;
                }

                Object this$dtoPackage = this.getDtoPackage();
                Object other$dtoPackage = other.getDtoPackage();
                if (this$dtoPackage == null) {
                    if (other$dtoPackage != null) {
                        return false;
                    }
                } else if (!this$dtoPackage.equals(other$dtoPackage)) {
                    return false;
                }

                label649: {
                    Object this$dtoSuperClass = this.getDtoSuperClass();
                    Object other$dtoSuperClass = other.getDtoSuperClass();
                    if (this$dtoSuperClass == null) {
                        if (other$dtoSuperClass == null) {
                            break label649;
                        }
                    } else if (this$dtoSuperClass.equals(other$dtoSuperClass)) {
                        break label649;
                    }

                    return false;
                }

                label642: {
                    Object this$dtoFileName = this.getDtoFileName();
                    Object other$dtoFileName = other.getDtoFileName();
                    if (this$dtoFileName == null) {
                        if (other$dtoFileName == null) {
                            break label642;
                        }
                    } else if (this$dtoFileName.equals(other$dtoFileName)) {
                        break label642;
                    }

                    return false;
                }

                Object this$dtoOutputDir = this.getDtoOutputDir();
                Object other$dtoOutputDir = other.getDtoOutputDir();
                if (this$dtoOutputDir == null) {
                    if (other$dtoOutputDir != null) {
                        return false;
                    }
                } else if (!this$dtoOutputDir.equals(other$dtoOutputDir)) {
                    return false;
                }

                Object this$dtoPackageName = this.getDtoPackageName();
                Object other$dtoPackageName = other.getDtoPackageName();
                if (this$dtoPackageName == null) {
                    if (other$dtoPackageName != null) {
                        return false;
                    }
                } else if (!this$dtoPackageName.equals(other$dtoPackageName)) {
                    return false;
                }

                label621: {
                    Object this$voPackage = this.getVoPackage();
                    Object other$voPackage = other.getVoPackage();
                    if (this$voPackage == null) {
                        if (other$voPackage == null) {
                            break label621;
                        }
                    } else if (this$voPackage.equals(other$voPackage)) {
                        break label621;
                    }

                    return false;
                }

                label614: {
                    Object this$voFileName = this.getVoFileName();
                    Object other$voFileName = other.getVoFileName();
                    if (this$voFileName == null) {
                        if (other$voFileName == null) {
                            break label614;
                        }
                    } else if (this$voFileName.equals(other$voFileName)) {
                        break label614;
                    }

                    return false;
                }

                Object this$voOutputDir = this.getVoOutputDir();
                Object other$voOutputDir = other.getVoOutputDir();
                if (this$voOutputDir == null) {
                    if (other$voOutputDir != null) {
                        return false;
                    }
                } else if (!this$voOutputDir.equals(other$voOutputDir)) {
                    return false;
                }

                label600: {
                    Object this$voPackageName = this.getVoPackageName();
                    Object other$voPackageName = other.getVoPackageName();
                    if (this$voPackageName == null) {
                        if (other$voPackageName == null) {
                            break label600;
                        }
                    } else if (this$voPackageName.equals(other$voPackageName)) {
                        break label600;
                    }

                    return false;
                }

                Object this$entityPackage = this.getEntityPackage();
                Object other$entityPackage = other.getEntityPackage();
                if (this$entityPackage == null) {
                    if (other$entityPackage != null) {
                        return false;
                    }
                } else if (!this$entityPackage.equals(other$entityPackage)) {
                    return false;
                }

                label586: {
                    Object this$entitySuperClass = this.getEntitySuperClass();
                    Object other$entitySuperClass = other.getEntitySuperClass();
                    if (this$entitySuperClass == null) {
                        if (other$entitySuperClass == null) {
                            break label586;
                        }
                    } else if (this$entitySuperClass.equals(other$entitySuperClass)) {
                        break label586;
                    }

                    return false;
                }

                Object this$entityFileName = this.getEntityFileName();
                Object other$entityFileName = other.getEntityFileName();
                if (this$entityFileName == null) {
                    if (other$entityFileName != null) {
                        return false;
                    }
                } else if (!this$entityFileName.equals(other$entityFileName)) {
                    return false;
                }

                Object this$entityOutputDir = this.getEntityOutputDir();
                Object other$entityOutputDir = other.getEntityOutputDir();
                if (this$entityOutputDir == null) {
                    if (other$entityOutputDir != null) {
                        return false;
                    }
                } else if (!this$entityOutputDir.equals(other$entityOutputDir)) {
                    return false;
                }

                Object this$entityPackageName = this.getEntityPackageName();
                Object other$entityPackageName = other.getEntityPackageName();
                if (this$entityPackageName == null) {
                    if (other$entityPackageName != null) {
                        return false;
                    }
                } else if (!this$entityPackageName.equals(other$entityPackageName)) {
                    return false;
                }

                label558: {
                    Object this$servicePackage = this.getServicePackage();
                    Object other$servicePackage = other.getServicePackage();
                    if (this$servicePackage == null) {
                        if (other$servicePackage == null) {
                            break label558;
                        }
                    } else if (this$servicePackage.equals(other$servicePackage)) {
                        break label558;
                    }

                    return false;
                }

                label551: {
                    Object this$serviceSuperClass = this.getServiceSuperClass();
                    Object other$serviceSuperClass = other.getServiceSuperClass();
                    if (this$serviceSuperClass == null) {
                        if (other$serviceSuperClass == null) {
                            break label551;
                        }
                    } else if (this$serviceSuperClass.equals(other$serviceSuperClass)) {
                        break label551;
                    }

                    return false;
                }

                Object this$serviceFileName = this.getServiceFileName();
                Object other$serviceFileName = other.getServiceFileName();
                if (this$serviceFileName == null) {
                    if (other$serviceFileName != null) {
                        return false;
                    }
                } else if (!this$serviceFileName.equals(other$serviceFileName)) {
                    return false;
                }

                label537: {
                    Object this$serviceOutputDir = this.getServiceOutputDir();
                    Object other$serviceOutputDir = other.getServiceOutputDir();
                    if (this$serviceOutputDir == null) {
                        if (other$serviceOutputDir == null) {
                            break label537;
                        }
                    } else if (this$serviceOutputDir.equals(other$serviceOutputDir)) {
                        break label537;
                    }

                    return false;
                }

                label530: {
                    Object this$servicePackageName = this.getServicePackageName();
                    Object other$servicePackageName = other.getServicePackageName();
                    if (this$servicePackageName == null) {
                        if (other$servicePackageName == null) {
                            break label530;
                        }
                    } else if (this$servicePackageName.equals(other$servicePackageName)) {
                        break label530;
                    }

                    return false;
                }

                Object this$serviceImplPackage = this.getServiceImplPackage();
                Object other$serviceImplPackage = other.getServiceImplPackage();
                if (this$serviceImplPackage == null) {
                    if (other$serviceImplPackage != null) {
                        return false;
                    }
                } else if (!this$serviceImplPackage.equals(other$serviceImplPackage)) {
                    return false;
                }

                Object this$serviceImplSuperClass = this.getServiceImplSuperClass();
                Object other$serviceImplSuperClass = other.getServiceImplSuperClass();
                if (this$serviceImplSuperClass == null) {
                    if (other$serviceImplSuperClass != null) {
                        return false;
                    }
                } else if (!this$serviceImplSuperClass.equals(other$serviceImplSuperClass)) {
                    return false;
                }

                label509: {
                    Object this$serviceImplFileName = this.getServiceImplFileName();
                    Object other$serviceImplFileName = other.getServiceImplFileName();
                    if (this$serviceImplFileName == null) {
                        if (other$serviceImplFileName == null) {
                            break label509;
                        }
                    } else if (this$serviceImplFileName.equals(other$serviceImplFileName)) {
                        break label509;
                    }

                    return false;
                }

                label502: {
                    Object this$serviceImplOutputDir = this.getServiceImplOutputDir();
                    Object other$serviceImplOutputDir = other.getServiceImplOutputDir();
                    if (this$serviceImplOutputDir == null) {
                        if (other$serviceImplOutputDir == null) {
                            break label502;
                        }
                    } else if (this$serviceImplOutputDir.equals(other$serviceImplOutputDir)) {
                        break label502;
                    }

                    return false;
                }

                Object this$serviceImplPackageName = this.getServiceImplPackageName();
                Object other$serviceImplPackageName = other.getServiceImplPackageName();
                if (this$serviceImplPackageName == null) {
                    if (other$serviceImplPackageName != null) {
                        return false;
                    }
                } else if (!this$serviceImplPackageName.equals(other$serviceImplPackageName)) {
                    return false;
                }

                label488: {
                    Object this$controllerPackage = this.getControllerPackage();
                    Object other$controllerPackage = other.getControllerPackage();
                    if (this$controllerPackage == null) {
                        if (other$controllerPackage == null) {
                            break label488;
                        }
                    } else if (this$controllerPackage.equals(other$controllerPackage)) {
                        break label488;
                    }

                    return false;
                }

                Object this$controllerSuperClass = this.getControllerSuperClass();
                Object other$controllerSuperClass = other.getControllerSuperClass();
                if (this$controllerSuperClass == null) {
                    if (other$controllerSuperClass != null) {
                        return false;
                    }
                } else if (!this$controllerSuperClass.equals(other$controllerSuperClass)) {
                    return false;
                }

                label474: {
                    Object this$controllerFileName = this.getControllerFileName();
                    Object other$controllerFileName = other.getControllerFileName();
                    if (this$controllerFileName == null) {
                        if (other$controllerFileName == null) {
                            break label474;
                        }
                    } else if (this$controllerFileName.equals(other$controllerFileName)) {
                        break label474;
                    }

                    return false;
                }

                Object this$controllerOutputDir = this.getControllerOutputDir();
                Object other$controllerOutputDir = other.getControllerOutputDir();
                if (this$controllerOutputDir == null) {
                    if (other$controllerOutputDir != null) {
                        return false;
                    }
                } else if (!this$controllerOutputDir.equals(other$controllerOutputDir)) {
                    return false;
                }

                Object this$controllerPackageName = this.getControllerPackageName();
                Object other$controllerPackageName = other.getControllerPackageName();
                if (this$controllerPackageName == null) {
                    if (other$controllerPackageName != null) {
                        return false;
                    }
                } else if (!this$controllerPackageName.equals(other$controllerPackageName)) {
                    return false;
                }

                Object this$daoPackage = this.getDaoPackage();
                Object other$daoPackage = other.getDaoPackage();
                if (this$daoPackage == null) {
                    if (other$daoPackage != null) {
                        return false;
                    }
                } else if (!this$daoPackage.equals(other$daoPackage)) {
                    return false;
                }

                label446: {
                    Object this$daoSuperClass = this.getDaoSuperClass();
                    Object other$daoSuperClass = other.getDaoSuperClass();
                    if (this$daoSuperClass == null) {
                        if (other$daoSuperClass == null) {
                            break label446;
                        }
                    } else if (this$daoSuperClass.equals(other$daoSuperClass)) {
                        break label446;
                    }

                    return false;
                }

                label439: {
                    Object this$daoFileName = this.getDaoFileName();
                    Object other$daoFileName = other.getDaoFileName();
                    if (this$daoFileName == null) {
                        if (other$daoFileName == null) {
                            break label439;
                        }
                    } else if (this$daoFileName.equals(other$daoFileName)) {
                        break label439;
                    }

                    return false;
                }

                Object this$daoOutputDir = this.getDaoOutputDir();
                Object other$daoOutputDir = other.getDaoOutputDir();
                if (this$daoOutputDir == null) {
                    if (other$daoOutputDir != null) {
                        return false;
                    }
                } else if (!this$daoOutputDir.equals(other$daoOutputDir)) {
                    return false;
                }

                label425: {
                    Object this$daoPackageName = this.getDaoPackageName();
                    Object other$daoPackageName = other.getDaoPackageName();
                    if (this$daoPackageName == null) {
                        if (other$daoPackageName == null) {
                            break label425;
                        }
                    } else if (this$daoPackageName.equals(other$daoPackageName)) {
                        break label425;
                    }

                    return false;
                }

                label418: {
                    Object this$mapperPackage = this.getMapperPackage();
                    Object other$mapperPackage = other.getMapperPackage();
                    if (this$mapperPackage == null) {
                        if (other$mapperPackage == null) {
                            break label418;
                        }
                    } else if (this$mapperPackage.equals(other$mapperPackage)) {
                        break label418;
                    }

                    return false;
                }

                Object this$mapperFileName = this.getMapperFileName();
                Object other$mapperFileName = other.getMapperFileName();
                if (this$mapperFileName == null) {
                    if (other$mapperFileName != null) {
                        return false;
                    }
                } else if (!this$mapperFileName.equals(other$mapperFileName)) {
                    return false;
                }

                Object this$mapperOutputDir = this.getMapperOutputDir();
                Object other$mapperOutputDir = other.getMapperOutputDir();
                if (this$mapperOutputDir == null) {
                    if (other$mapperOutputDir != null) {
                        return false;
                    }
                } else if (!this$mapperOutputDir.equals(other$mapperOutputDir)) {
                    return false;
                }

                label397: {
                    Object this$mapperPackageName = this.getMapperPackageName();
                    Object other$mapperPackageName = other.getMapperPackageName();
                    if (this$mapperPackageName == null) {
                        if (other$mapperPackageName == null) {
                            break label397;
                        }
                    } else if (this$mapperPackageName.equals(other$mapperPackageName)) {
                        break label397;
                    }

                    return false;
                }

                label390: {
                    Object this$strategyConfig = this.getStrategyConfig();
                    Object other$strategyConfig = other.getStrategyConfig();
                    if (this$strategyConfig == null) {
                        if (other$strategyConfig == null) {
                            break label390;
                        }
                    } else if (this$strategyConfig.equals(other$strategyConfig)) {
                        break label390;
                    }

                    return false;
                }

                Object this$globalConfig = this.getGlobalConfig();
                Object other$globalConfig = other.getGlobalConfig();
                if (this$globalConfig == null) {
                    if (other$globalConfig != null) {
                        return false;
                    }
                } else if (!this$globalConfig.equals(other$globalConfig)) {
                    return false;
                }

                label376: {
                    Object this$autoGenerator = this.getAutoGenerator();
                    Object other$autoGenerator = other.getAutoGenerator();
                    if (this$autoGenerator == null) {
                        if (other$autoGenerator == null) {
                            break label376;
                        }
                    } else if (this$autoGenerator.equals(other$autoGenerator)) {
                        break label376;
                    }

                    return false;
                }

                Object this$dataSourceConfig = this.getDataSourceConfig();
                Object other$dataSourceConfig = other.getDataSourceConfig();
                if (this$dataSourceConfig == null) {
                    if (other$dataSourceConfig != null) {
                        return false;
                    }
                } else if (!this$dataSourceConfig.equals(other$dataSourceConfig)) {
                    return false;
                }

                label362: {
                    Object this$injectionConfig = this.getInjectionConfig();
                    Object other$injectionConfig = other.getInjectionConfig();
                    if (this$injectionConfig == null) {
                        if (other$injectionConfig == null) {
                            break label362;
                        }
                    } else if (this$injectionConfig.equals(other$injectionConfig)) {
                        break label362;
                    }

                    return false;
                }

                Object this$packageConfig = this.getPackageConfig();
                Object other$packageConfig = other.getPackageConfig();
                if (this$packageConfig == null) {
                    if (other$packageConfig != null) {
                        return false;
                    }
                } else if (!this$packageConfig.equals(other$packageConfig)) {
                    return false;
                }

                Object this$fileOutConfigs = this.getFileOutConfigs();
                Object other$fileOutConfigs = other.getFileOutConfigs();
                if (this$fileOutConfigs == null) {
                    if (other$fileOutConfigs != null) {
                        return false;
                    }
                } else if (!this$fileOutConfigs.equals(other$fileOutConfigs)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof MybatisGenerator;
    }

    public int hashCode() {
        int PRIME = 1;
        int result = 1;
        Object $configPath = this.getConfigPath();
        result = result * 59 + ($configPath == null ? 43 : $configPath.hashCode());
        Object $driverName = this.getDriverName();
        result = result * 59 + ($driverName == null ? 43 : $driverName.hashCode());
        Object $username = this.getUsername();
        result = result * 59 + ($username == null ? 43 : $username.hashCode());
        Object $password = this.getPassword();
        result = result * 59 + ($password == null ? 43 : $password.hashCode());
        Object $url = this.getUrl();
        result = result * 59 + ($url == null ? 43 : $url.hashCode());
        Object $projectName = this.getProjectName();
        result = result * 59 + ($projectName == null ? 43 : $projectName.hashCode());
        Object $projectRootPath = this.getProjectRootPath();
        result = result * 59 + ($projectRootPath == null ? 43 : $projectRootPath.hashCode());
        Object $tableNames = this.getTableNames();
        result = result * 59 + ($tableNames == null ? 43 : $tableNames.hashCode());
        Object $fileOverride = this.getFileOverride();
        result = result * 59 + ($fileOverride == null ? 43 : $fileOverride.hashCode());
        Object $ignoreTablePrefix = this.getIgnoreTablePrefix();
        result = result * 59 + ($ignoreTablePrefix == null ? 43 : $ignoreTablePrefix.hashCode());
        Object $entityLombokModel = this.getEntityLombokModel();
        result = result * 59 + ($entityLombokModel == null ? 43 : $entityLombokModel.hashCode());
        Object $exclusionEntitySuperColumns = this.getExclusionEntitySuperColumns();
        result = result * 59 + ($exclusionEntitySuperColumns == null ? 43 : $exclusionEntitySuperColumns.hashCode());
        Object $isExclusionEntitySuperColumn = this.getIsExclusionEntitySuperColumn();
        result = result * 59 + ($isExclusionEntitySuperColumn == null ? 43 : $isExclusionEntitySuperColumn.hashCode());
        Object $isDeleteFilePrefixModule = this.getIsDeleteFilePrefixModule();
        result = result * 59 + ($isDeleteFilePrefixModule == null ? 43 : $isDeleteFilePrefixModule.hashCode());
        Object $isCreateService = this.getIsCreateService();
        result = result * 59 + ($isCreateService == null ? 43 : $isCreateService.hashCode());
        Object $isCreateController = this.getIsCreateController();
        result = result * 59 + ($isCreateController == null ? 43 : $isCreateController.hashCode());
        Object $isCreateVO = this.getIsCreateVO();
        result = result * 59 + ($isCreateVO == null ? 43 : $isCreateVO.hashCode());
        Object $author = this.getAuthor();
        result = result * 59 + ($author == null ? 43 : $author.hashCode());
        Object $moduleName = this.getModuleName();
        result = result * 59 + ($moduleName == null ? 43 : $moduleName.hashCode());
        Object $basePackage = this.getBasePackage();
        result = result * 59 + ($basePackage == null ? 43 : $basePackage.hashCode());
        Object $baseOutputDir = this.getBaseOutputDir();
        result = result * 59 + ($baseOutputDir == null ? 43 : $baseOutputDir.hashCode());
        Object $dtoPackage = this.getDtoPackage();
        result = result * 59 + ($dtoPackage == null ? 43 : $dtoPackage.hashCode());
        Object $dtoSuperClass = this.getDtoSuperClass();
        result = result * 59 + ($dtoSuperClass == null ? 43 : $dtoSuperClass.hashCode());
        Object $dtoFileName = this.getDtoFileName();
        result = result * 59 + ($dtoFileName == null ? 43 : $dtoFileName.hashCode());
        Object $dtoOutputDir = this.getDtoOutputDir();
        result = result * 59 + ($dtoOutputDir == null ? 43 : $dtoOutputDir.hashCode());
        Object $dtoPackageName = this.getDtoPackageName();
        result = result * 59 + ($dtoPackageName == null ? 43 : $dtoPackageName.hashCode());
        Object $voPackage = this.getVoPackage();
        result = result * 59 + ($voPackage == null ? 43 : $voPackage.hashCode());
        Object $voFileName = this.getVoFileName();
        result = result * 59 + ($voFileName == null ? 43 : $voFileName.hashCode());
        Object $voOutputDir = this.getVoOutputDir();
        result = result * 59 + ($voOutputDir == null ? 43 : $voOutputDir.hashCode());
        Object $voPackageName = this.getVoPackageName();
        result = result * 59 + ($voPackageName == null ? 43 : $voPackageName.hashCode());
        Object $entityPackage = this.getEntityPackage();
        result = result * 59 + ($entityPackage == null ? 43 : $entityPackage.hashCode());
        Object $entitySuperClass = this.getEntitySuperClass();
        result = result * 59 + ($entitySuperClass == null ? 43 : $entitySuperClass.hashCode());
        Object $entityFileName = this.getEntityFileName();
        result = result * 59 + ($entityFileName == null ? 43 : $entityFileName.hashCode());
        Object $entityOutputDir = this.getEntityOutputDir();
        result = result * 59 + ($entityOutputDir == null ? 43 : $entityOutputDir.hashCode());
        Object $entityPackageName = this.getEntityPackageName();
        result = result * 59 + ($entityPackageName == null ? 43 : $entityPackageName.hashCode());
        Object $servicePackage = this.getServicePackage();
        result = result * 59 + ($servicePackage == null ? 43 : $servicePackage.hashCode());
        Object $serviceSuperClass = this.getServiceSuperClass();
        result = result * 59 + ($serviceSuperClass == null ? 43 : $serviceSuperClass.hashCode());
        Object $serviceFileName = this.getServiceFileName();
        result = result * 59 + ($serviceFileName == null ? 43 : $serviceFileName.hashCode());
        Object $serviceOutputDir = this.getServiceOutputDir();
        result = result * 59 + ($serviceOutputDir == null ? 43 : $serviceOutputDir.hashCode());
        Object $servicePackageName = this.getServicePackageName();
        result = result * 59 + ($servicePackageName == null ? 43 : $servicePackageName.hashCode());
        Object $serviceImplPackage = this.getServiceImplPackage();
        result = result * 59 + ($serviceImplPackage == null ? 43 : $serviceImplPackage.hashCode());
        Object $serviceImplSuperClass = this.getServiceImplSuperClass();
        result = result * 59 + ($serviceImplSuperClass == null ? 43 : $serviceImplSuperClass.hashCode());
        Object $serviceImplFileName = this.getServiceImplFileName();
        result = result * 59 + ($serviceImplFileName == null ? 43 : $serviceImplFileName.hashCode());
        Object $serviceImplOutputDir = this.getServiceImplOutputDir();
        result = result * 59 + ($serviceImplOutputDir == null ? 43 : $serviceImplOutputDir.hashCode());
        Object $serviceImplPackageName = this.getServiceImplPackageName();
        result = result * 59 + ($serviceImplPackageName == null ? 43 : $serviceImplPackageName.hashCode());
        Object $controllerPackage = this.getControllerPackage();
        result = result * 59 + ($controllerPackage == null ? 43 : $controllerPackage.hashCode());
        Object $controllerSuperClass = this.getControllerSuperClass();
        result = result * 59 + ($controllerSuperClass == null ? 43 : $controllerSuperClass.hashCode());
        Object $controllerFileName = this.getControllerFileName();
        result = result * 59 + ($controllerFileName == null ? 43 : $controllerFileName.hashCode());
        Object $controllerOutputDir = this.getControllerOutputDir();
        result = result * 59 + ($controllerOutputDir == null ? 43 : $controllerOutputDir.hashCode());
        Object $controllerPackageName = this.getControllerPackageName();
        result = result * 59 + ($controllerPackageName == null ? 43 : $controllerPackageName.hashCode());
        Object $daoPackage = this.getDaoPackage();
        result = result * 59 + ($daoPackage == null ? 43 : $daoPackage.hashCode());
        Object $daoSuperClass = this.getDaoSuperClass();
        result = result * 59 + ($daoSuperClass == null ? 43 : $daoSuperClass.hashCode());
        Object $daoFileName = this.getDaoFileName();
        result = result * 59 + ($daoFileName == null ? 43 : $daoFileName.hashCode());
        Object $daoOutputDir = this.getDaoOutputDir();
        result = result * 59 + ($daoOutputDir == null ? 43 : $daoOutputDir.hashCode());
        Object $daoPackageName = this.getDaoPackageName();
        result = result * 59 + ($daoPackageName == null ? 43 : $daoPackageName.hashCode());
        Object $mapperPackage = this.getMapperPackage();
        result = result * 59 + ($mapperPackage == null ? 43 : $mapperPackage.hashCode());
        Object $mapperFileName = this.getMapperFileName();
        result = result * 59 + ($mapperFileName == null ? 43 : $mapperFileName.hashCode());
        Object $mapperOutputDir = this.getMapperOutputDir();
        result = result * 59 + ($mapperOutputDir == null ? 43 : $mapperOutputDir.hashCode());
        Object $mapperPackageName = this.getMapperPackageName();
        result = result * 59 + ($mapperPackageName == null ? 43 : $mapperPackageName.hashCode());
        Object $strategyConfig = this.getStrategyConfig();
        result = result * 59 + ($strategyConfig == null ? 43 : $strategyConfig.hashCode());
        Object $globalConfig = this.getGlobalConfig();
        result = result * 59 + ($globalConfig == null ? 43 : $globalConfig.hashCode());
        Object $autoGenerator = this.getAutoGenerator();
        result = result * 59 + ($autoGenerator == null ? 43 : $autoGenerator.hashCode());
        Object $dataSourceConfig = this.getDataSourceConfig();
        result = result * 59 + ($dataSourceConfig == null ? 43 : $dataSourceConfig.hashCode());
        Object $injectionConfig = this.getInjectionConfig();
        result = result * 59 + ($injectionConfig == null ? 43 : $injectionConfig.hashCode());
        Object $packageConfig = this.getPackageConfig();
        result = result * 59 + ($packageConfig == null ? 43 : $packageConfig.hashCode());
        Object $fileOutConfigs = this.getFileOutConfigs();
        result = result * 59 + ($fileOutConfigs == null ? 43 : $fileOutConfigs.hashCode());
        return result;
    }


    public String toString() {
        return "MybatisGenerator(configPath=" + this.getConfigPath() + ", driverName=" + this.getDriverName() + ", username=" + this.getUsername() + ", password=" + this.getPassword() + ", url=" + this.getUrl() + ", projectName=" + this.getProjectName() + ", projectRootPath=" + this.getProjectRootPath() + ", tableNames=" + this.getTableNames() + ", fileOverride=" + this.getFileOverride() + ", ignoreTablePrefix=" + this.getIgnoreTablePrefix() + ", entityLombokModel=" + this.getEntityLombokModel() + ", exclusionEntitySuperColumns=" + this.getExclusionEntitySuperColumns() + ", isExclusionEntitySuperColumn=" + this.getIsExclusionEntitySuperColumn() + ", isDeleteFilePrefixModule=" + this.getIsDeleteFilePrefixModule() + ", isCreateService=" + this.getIsCreateService() + ", isCreateController=" + this.getIsCreateController() + ", isCreateVO=" + this.getIsCreateVO() + ", author=" + this.getAuthor() + ", moduleName=" + this.getModuleName() + ", basePackage=" + this.getBasePackage() + ", baseOutputDir=" + this.getBaseOutputDir() + ", dtoPackage=" + this.getDtoPackage() + ", dtoSuperClass=" + this.getDtoSuperClass() + ", dtoFileName=" + this.getDtoFileName() + ", dtoOutputDir=" + this.getDtoOutputDir() + ", dtoPackageName=" + this.getDtoPackageName() + ", voPackage=" + this.getVoPackage() + ", voFileName=" + this.getVoFileName() + ", voOutputDir=" + this.getVoOutputDir() + ", voPackageName=" + this.getVoPackageName() + ", entityPackage=" + this.getEntityPackage() + ", entitySuperClass=" + this.getEntitySuperClass() + ", entityFileName=" + this.getEntityFileName() + ", entityOutputDir=" + this.getEntityOutputDir() + ", entityPackageName=" + this.getEntityPackageName() + ", servicePackage=" + this.getServicePackage() + ", serviceSuperClass=" + this.getServiceSuperClass() + ", serviceFileName=" + this.getServiceFileName() + ", serviceOutputDir=" + this.getServiceOutputDir() + ", servicePackageName=" + this.getServicePackageName() + ", serviceImplPackage=" + this.getServiceImplPackage() + ", serviceImplSuperClass=" + this.getServiceImplSuperClass() + ", serviceImplFileName=" + this.getServiceImplFileName() + ", serviceImplOutputDir=" + this.getServiceImplOutputDir() + ", serviceImplPackageName=" + this.getServiceImplPackageName() + ", controllerPackage=" + this.getControllerPackage() + ", controllerSuperClass=" + this.getControllerSuperClass() + ", controllerFileName=" + this.getControllerFileName() + ", controllerOutputDir=" + this.getControllerOutputDir() + ", controllerPackageName=" + this.getControllerPackageName() + ", daoPackage=" + this.getDaoPackage() + ", daoSuperClass=" + this.getDaoSuperClass() + ", daoFileName=" + this.getDaoFileName() + ", daoOutputDir=" + this.getDaoOutputDir() + ", daoPackageName=" + this.getDaoPackageName() + ", mapperPackage=" + this.getMapperPackage() + ", mapperFileName=" + this.getMapperFileName() + ", mapperOutputDir=" + this.getMapperOutputDir() + ", mapperPackageName=" + this.getMapperPackageName() + ", strategyConfig=" + this.getStrategyConfig() + ", globalConfig=" + this.getGlobalConfig() + ", autoGenerator=" + this.getAutoGenerator() + ", dataSourceConfig=" + this.getDataSourceConfig() + ", injectionConfig=" + this.getInjectionConfig() + ", packageConfig=" + this.getPackageConfig() + ", fileOutConfigs=" + this.getFileOutConfigs() + ")";
    }
}
