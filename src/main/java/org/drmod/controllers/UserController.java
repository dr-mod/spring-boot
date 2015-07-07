package org.drmod.controllers;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import org.drmod.beans.User;
import org.drmod.dao.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mobile.device.Device;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 7/6/2015
 *
 * @author drmod
 */
@RestController
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    private AtomicInteger counter = new AtomicInteger();
    private Random random = new Random();

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "{name}/info")
    User getUser(@PathVariable String name) {
        User user = userRepository.findByFirstName(name).orElseThrow(NoSuchUserException::new);
        return user;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    void addUser(@RequestBody User user, Device device) {
        user.setPassword(String.format("%06d", random.nextInt(999999)));
        userRepository.save(user);
        logger.info(user.toString());
        logger.info("IsMobile: {}, IsNormal: {}, IsTablet: {} ", device.isMobile(), device.isNormal(), device.isTablet());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    private class NoSuchUserException extends RuntimeException {
    }

}
