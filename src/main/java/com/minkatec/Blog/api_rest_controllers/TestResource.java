package com.minkatec.Blog.api_rest_controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
public class TestResource {

    @GetMapping("/test")
    public Collection<String> test() {
        return IntStream.range(0, 10)
                .mapToObj(i -> "Test number " + i)
                .collect(Collectors.toList());
    }
}
