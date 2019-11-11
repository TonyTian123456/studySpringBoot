package com.example.config.datasource;

import com.example.config.datasource.prop.DataBase1Properties;
import com.example.utils.PojoUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.ibatis.io.VFS;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import javax.sql.DataSource;

/**
 * Created by cicada on 2019/9/5.
 */
@Configuration
@MapperScan(basePackages = "com.example.mapper.user", sqlSessionTemplateRef = "userSqlSessionTemplateUser")
public class UserDataSourceConfigration {

    @Autowired
    private DataBase1Properties  dataBase1Properties;

    @Bean(name = "userDataSource")
    @Primary
    public DataSource getUserDataSource() {
        AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
        ds.setXaProperties(PojoUtil.obj2Properties(dataBase1Properties));
        ds.setXaDataSourceClassName("com.alibaba.druid.pool.xa.DruidXADataSource");
        ds.setUniqueResourceName("dataBase1Properties");
        ds.setPoolSize(5);
        ds.setTestQuery("SELECT 1");
        return ds;

    }

    @Bean(name = "sqlSessionFactoryUser")
    @Primary
    public SqlSessionFactory sqlSessionFactoryBlueWhale(@Qualifier("userDataSource") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        VFS.addImplClass(SpringBootVFS.class);
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setTypeAliasesPackage("com.example.domain.user");
        // Mybatis Config
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        sessionFactory.setConfiguration(configuration);

        // Mybatis Mapper XML Config
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        //*.mapper.xml的地址（根据你的项目自行修改）
        String resouces[] = {"classpath*:/mybatis/user/*.xml"};
        Resource[] resourceArray = null;
        for (String resouce : resouces) {
            Resource[] resourceArray1 = resolver.getResources(resouce);
            if (resourceArray == null) {
                resourceArray = resourceArray1;
            } else {
                resourceArray = (Resource[]) ArrayUtils.addAll(resourceArray, resourceArray1);
            }
        }
        sessionFactory.setMapperLocations(resourceArray);
        return sessionFactory.getObject();
    }

    @Bean(name = "userSqlSessionTemplateUser")
    @Primary
    public SqlSessionTemplate sqlSessionTemplateBlueWhale(
            @Qualifier("sqlSessionFactoryUser") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
