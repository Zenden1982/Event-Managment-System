package com.zenden.task_management_system.Handler;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice(basePackages = "src\\main\\java\\com\\zenden\\task_management_system\\Controllers")
public class RestControllerExceptionHandler extends ResponseEntityExceptionHandler {

}
