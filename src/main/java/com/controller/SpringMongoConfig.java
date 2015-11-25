package com.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;

import com.mongodb.MongoClient;
import com.mongodb.Mongo;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

@Configuration
@EnableMongoRepositories
public class SpringMongoConfig extends AbstractMongoConfiguration
{   
	@Bean
    public GridFsTemplate gridFsTemplate() throws Exception 
    {
        return new GridFsTemplate(mongoDbFactory(), mappingMongoConverter());
    }

    @Override
    protected String getDatabaseName() 
    {
        return "global";
    }

    @Override
    @Bean
    public Mongo mongo() throws Exception 
    {
        return new MongoClient("localhost" , 27017 );
    }

    public @Bean MongoTemplate mongoTemplate() throws Exception 
    {
        return new MongoTemplate(mongo(), getDatabaseName());
    }    
}