package io.github.demo.demoservice.controller;

import io.github.demo.demoservice.service.UserService;
import io.github.demo.demoservice.vo.User;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping(path = "/user/api/user")
@Slf4j
public class UserController {

  private UserService userService;
  private WebClient webClient;
  private RabbitTemplate rabbitTemplate;

  //private final WebClient webClient;
  //
  //    public CameraService(WebClient.Builder webClientBuilder,
  //                         @Value("${therapconnect.admin.altumview-query-service-api.base-url}") final String cameraApiBaseUrl) {
  //        this.webClient = webClientBuilder.baseUrl(cameraApiBaseUrl)
  //                .build();
  //    }

  public UserController(UserService userService, WebClient.Builder webClientBuilder, RabbitTemplate rabbitTemplate) {
    this.userService = userService;
    this.rabbitTemplate = rabbitTemplate;
    this.webClient = webClientBuilder.baseUrl("http://auth-service").build();
  }

//  @Trace
  @GetMapping
  public List<User> getAll() {
    log.info("Hello");
    String response = webClient.get().uri("/auth/check").retrieve().bodyToMono(String.class).block();
    log.info("=====================");
    log.info(response);
    rabbitTemplate.convertAndSend("test-exchange", "test-routing-key", response);
    log.info("=================");
    return userService.getAll();
  }

  @Trace
  @PostMapping
  public User createUser(@RequestBody User user) {
    return userService.createUser(user);
  }

  @PutMapping("/{id}")
  public User updateUser(@PathVariable("id") Integer id, @RequestBody User user) {
    return userService.updateUser(id, user);
  }

  @DeleteMapping("/{id}")
  public void deleteUser(@PathVariable("id") Integer id) {
    userService.deleteUser(id);
  }

  @PostMapping("/batch")
  public void batchPost(@RequestBody List<User> users) {
    userService.batchAddUser(users);
  }

  @PutMapping("/batch")
  public void batchPut(@RequestBody List<User> users) {
    userService.batchUpdateUser(users);
  }
}
