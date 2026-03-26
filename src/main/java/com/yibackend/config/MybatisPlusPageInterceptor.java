package com.yibackend.config;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisPlusPageInterceptor {

@Bean
    public PaginationInterceptor paginationInterceptor(){
    return new PaginationInterceptor();
}

}
