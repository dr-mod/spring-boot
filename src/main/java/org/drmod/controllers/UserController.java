package org.drmod.controllers;

import org.drmod.beans.User;
import org.drmod.dao.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

/**
 * Created on 7/6/2015
 *
 * @author drmod
 */
@RestController
@RequestMapping(value = "{name}/info")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    private AtomicInteger counter = new AtomicInteger();
    private Random random = new Random();

    @Autowired
    private UserRepository userRepository;

    @RequestMapping
    User getUser(@PathVariable String name) {
        User user = userRepository.findByFirstName(name).orElseThrow(NoSuchUserException::new);
        return user;
    }

    @RequestMapping(method = RequestMethod.POST)
    void putUser(@RequestBody User user) {
        user.setPassword(String.format("%06d", random.nextInt(999999)));
        userRepository.save(user);
        logger.info(user.toString());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    private class NoSuchUserException extends RuntimeException {
    }

}
