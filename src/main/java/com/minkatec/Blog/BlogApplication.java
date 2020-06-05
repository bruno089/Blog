package com.minkatec.Blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

	@SpringBootApplication
	public class BlogApplication  extends SpringBootServletInitializer {
		@Override
		protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
			return  application.sources(com.minkatec.Blog.BlogApplication.class);
		}

		public static void main(String[] args) {
			SpringApplication.run(com.minkatec.Blog.BlogApplication.class, args);
		}
}
