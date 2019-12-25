package com.yeaile.web.generator.config;


import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import java.io.File;

/**
 * @param
 * @author sulaymanyf
 * @Description
 * @date 2019/12/19
 * @return
 **/
public class CustomFileOutConfig extends FileOutConfig {
    private String basePackage;
    private String outputDir;
    private String fileName;
    private String packageName;
    private boolean ignoreTablePrefix;
    private InjectionConfig cfg;
    private PackageConfig pc;
    private String superClass;
    private String name;
    private String outputFile;
    private String prefixName;
    private boolean isDeleteFilePrefixModule;

    public CustomFileOutConfig(String templatePath, String basePackage, String packageName, String fileName, String outputDir, String prefixName, boolean isDeleteFilePrefixModule, InjectionConfig cfg, PackageConfig pc) {
        super(templatePath);
        this.basePackage = basePackage;
        this.outputDir = outputDir;
        this.fileName = fileName;
        this.packageName = packageName;
        this.cfg = cfg;
        this.pc = pc;
        this.prefixName = prefixName;
        this.isDeleteFilePrefixModule = isDeleteFilePrefixModule;
    }

    public void init(TableInfo tableInfo) {
        Assert.notNull(this.basePackage, "basePackage不能为null");
        Assert.notNull(this.packageName, "packageName不能为null");
        Assert.notNull(this.fileName, "fileName不能为null");
        Assert.notNull(this.cfg, "fileName不能为null");
        Assert.notNull(this.pc, "pc不能为null");
        Assert.notNull(this.outputDir, "outputDir不能为null");
        Assert.notNull(this.prefixName, "prefixName不能为null");
        String entityName = tableInfo.getEntityName();
        String tableName = tableInfo.getName();
        String tablePrefix = null;
        int prefixIndex = tableName.indexOf("_");
        if (prefixIndex > -1) {
            tablePrefix = tableName.substring(0, prefixIndex).toLowerCase();
        }

        if (this.ignoreTablePrefix && tablePrefix != null) {
            entityName = tableInfo.getEntityName().replaceFirst("(?i)" + tablePrefix, "");
        }

        if (this.isDeleteFilePrefixModule) {
        }

        this.name = String.format(this.fileName, entityName);
        String moduleName = (String)StringUtils.defaultIfEmpty(this.pc.getModuleName(), tablePrefix);
        if (this.isDeleteFilePrefixModule && StringUtils.isNotEmpty(moduleName) && (this.name.startsWith(StringUtils.capitalize(moduleName)) || this.name.startsWith("I" + StringUtils.capitalize(moduleName)))) {
            this.name = this.name.replaceFirst(StringUtils.capitalize(moduleName), "");
        }

        String packagePath = this.basePackage + (StringUtils.isNotEmpty(moduleName) ? "." + moduleName : "") + (StringUtils.isNoneEmpty(new CharSequence[]{this.packageName}) ? "." + this.packageName : "");
        this.cfg.getMap().put(this.prefixName + "Package", packagePath);
        this.cfg.getMap().put(this.prefixName + "Name", this.name.substring(0, this.name.indexOf(".") > -1 ? this.name.indexOf(".") : this.name.length()));
        if (StringUtils.isNotEmpty(this.superClass)) {
            this.cfg.getMap().put(this.prefixName + "SuperClass", this.superClass);
            this.cfg.getMap().put(this.prefixName + "SuperClassName", StringUtils.substring(this.superClass, this.superClass.lastIndexOf(".") + 1));
        }

        this.outputFile = this.joinPath(this.outputDir, packagePath) + File.separator + this.name;
    }

    public String outputFile(TableInfo tableInfo) {
        this.init(tableInfo);
        return this.outputFile;
    }

    private String joinPath(String parentDir, String packageName) {
        if (StringUtils.isEmpty(parentDir)) {
            parentDir = System.getProperty("java.io.tmpdir");
        }

        if (!StringUtils.endsWith(parentDir, File.separator)) {
            parentDir = parentDir + File.separator;
        }

        packageName = packageName.replaceAll("\\.", "\\" + File.separator);
        return parentDir + packageName;
    }

    public String getBasePackage() {
        return this.basePackage;
    }

    public String getOutputDir() {
        return this.outputDir;
    }

    public String getFileName() {
        return this.fileName;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public boolean isIgnoreTablePrefix() {
        return this.ignoreTablePrefix;
    }

    public InjectionConfig getCfg() {
        return this.cfg;
    }

    public PackageConfig getPc() {
        return this.pc;
    }

    public String getSuperClass() {
        return this.superClass;
    }

    public String getName() {
        return this.name;
    }

    public String getOutputFile() {
        return this.outputFile;
    }

    public String getPrefixName() {
        return this.prefixName;
    }

    public boolean isDeleteFilePrefixModule() {
        return this.isDeleteFilePrefixModule;
    }

    public void setBasePackage(final String basePackage) {
        this.basePackage = basePackage;
    }

    public void setOutputDir(final String outputDir) {
        this.outputDir = outputDir;
    }

    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }

    public void setPackageName(final String packageName) {
        this.packageName = packageName;
    }

    public void setIgnoreTablePrefix(final boolean ignoreTablePrefix) {
        this.ignoreTablePrefix = ignoreTablePrefix;
    }

    public void setCfg(final InjectionConfig cfg) {
        this.cfg = cfg;
    }

    public void setPc(final PackageConfig pc) {
        this.pc = pc;
    }

    public void setSuperClass(final String superClass) {
        this.superClass = superClass;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setOutputFile(final String outputFile) {
        this.outputFile = outputFile;
    }

    public void setPrefixName(final String prefixName) {
        this.prefixName = prefixName;
    }

    public void setDeleteFilePrefixModule(final boolean isDeleteFilePrefixModule) {
        this.isDeleteFilePrefixModule = isDeleteFilePrefixModule;
    }

    public String toString() {
        return "CustomFileOutConfig(basePackage=" + this.getBasePackage() + ", outputDir=" + this.getOutputDir() + ", fileName=" + this.getFileName() + ", packageName=" + this.getPackageName() + ", ignoreTablePrefix=" + this.isIgnoreTablePrefix() + ", cfg=" + this.getCfg() + ", pc=" + this.getPc() + ", superClass=" + this.getSuperClass() + ", name=" + this.getName() + ", outputFile=" + this.getOutputFile() + ", prefixName=" + this.getPrefixName() + ", isDeleteFilePrefixModule=" + this.isDeleteFilePrefixModule() + ")";
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof CustomFileOutConfig)) {
            return false;
        } else {
            CustomFileOutConfig other = (CustomFileOutConfig)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label139: {
                    Object this$basePackage = this.getBasePackage();
                    Object other$basePackage = other.getBasePackage();
                    if (this$basePackage == null) {
                        if (other$basePackage == null) {
                            break label139;
                        }
                    } else if (this$basePackage.equals(other$basePackage)) {
                        break label139;
                    }

                    return false;
                }

                Object this$outputDir = this.getOutputDir();
                Object other$outputDir = other.getOutputDir();
                if (this$outputDir == null) {
                    if (other$outputDir != null) {
                        return false;
                    }
                } else if (!this$outputDir.equals(other$outputDir)) {
                    return false;
                }

                Object this$fileName = this.getFileName();
                Object other$fileName = other.getFileName();
                if (this$fileName == null) {
                    if (other$fileName != null) {
                        return false;
                    }
                } else if (!this$fileName.equals(other$fileName)) {
                    return false;
                }

                label118: {
                    Object this$packageName = this.getPackageName();
                    Object other$packageName = other.getPackageName();
                    if (this$packageName == null) {
                        if (other$packageName == null) {
                            break label118;
                        }
                    } else if (this$packageName.equals(other$packageName)) {
                        break label118;
                    }

                    return false;
                }

                if (this.isIgnoreTablePrefix() != other.isIgnoreTablePrefix()) {
                    return false;
                } else {
                    Object this$cfg = this.getCfg();
                    Object other$cfg = other.getCfg();
                    if (this$cfg == null) {
                        if (other$cfg != null) {
                            return false;
                        }
                    } else if (!this$cfg.equals(other$cfg)) {
                        return false;
                    }

                    label103: {
                        Object this$pc = this.getPc();
                        Object other$pc = other.getPc();
                        if (this$pc == null) {
                            if (other$pc == null) {
                                break label103;
                            }
                        } else if (this$pc.equals(other$pc)) {
                            break label103;
                        }

                        return false;
                    }

                    label96: {
                        Object this$superClass = this.getSuperClass();
                        Object other$superClass = other.getSuperClass();
                        if (this$superClass == null) {
                            if (other$superClass == null) {
                                break label96;
                            }
                        } else if (this$superClass.equals(other$superClass)) {
                            break label96;
                        }

                        return false;
                    }

                    Object this$name = this.getName();
                    Object other$name = other.getName();
                    if (this$name == null) {
                        if (other$name != null) {
                            return false;
                        }
                    } else if (!this$name.equals(other$name)) {
                        return false;
                    }

                    Object this$outputFile = this.getOutputFile();
                    Object other$outputFile = other.getOutputFile();
                    if (this$outputFile == null) {
                        if (other$outputFile != null) {
                            return false;
                        }
                    } else if (!this$outputFile.equals(other$outputFile)) {
                        return false;
                    }

                    label75: {
                        Object this$prefixName = this.getPrefixName();
                        Object other$prefixName = other.getPrefixName();
                        if (this$prefixName == null) {
                            if (other$prefixName == null) {
                                break label75;
                            }
                        } else if (this$prefixName.equals(other$prefixName)) {
                            break label75;
                        }

                        return false;
                    }

                    if (this.isDeleteFilePrefixModule() != other.isDeleteFilePrefixModule()) {
                        return false;
                    } else {
                        return true;
                    }
                }
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof CustomFileOutConfig;
    }

    public int hashCode() {
        int PRIME = 1;
        int result = 1;
        Object $basePackage = this.getBasePackage();
        result = result * 59 + ($basePackage == null ? 43 : $basePackage.hashCode());
        Object $outputDir = this.getOutputDir();
        result = result * 59 + ($outputDir == null ? 43 : $outputDir.hashCode());
        Object $fileName = this.getFileName();
        result = result * 59 + ($fileName == null ? 43 : $fileName.hashCode());
        Object $packageName = this.getPackageName();
        result = result * 59 + ($packageName == null ? 43 : $packageName.hashCode());
        result = result * 59 + (this.isIgnoreTablePrefix() ? 79 : 97);
        Object $cfg = this.getCfg();
        result = result * 59 + ($cfg == null ? 43 : $cfg.hashCode());
        Object $pc = this.getPc();
        result = result * 59 + ($pc == null ? 43 : $pc.hashCode());
        Object $superClass = this.getSuperClass();
        result = result * 59 + ($superClass == null ? 43 : $superClass.hashCode());
        Object $name = this.getName();
        result = result * 59 + ($name == null ? 43 : $name.hashCode());
        Object $outputFile = this.getOutputFile();
        result = result * 59 + ($outputFile == null ? 43 : $outputFile.hashCode());
        Object $prefixName = this.getPrefixName();
        result = result * 59 + ($prefixName == null ? 43 : $prefixName.hashCode());
        result = result * 59 + (this.isDeleteFilePrefixModule() ? 79 : 97);
        return result;
    }
}

