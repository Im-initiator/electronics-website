package com.electronics_store.controller.admin;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "admin/employee")
@RestController(value = "admin/employee")
@RequestMapping("/admin/employee")
@RequiredArgsConstructor
public class EmployeeController {

}
