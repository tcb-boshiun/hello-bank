package com.rick.backend.facade;

import org.springframework.web.bind.annotation.RequestBody;

public interface HelloFacade {

    String hello();

    String echo(@RequestBody String message);
}
