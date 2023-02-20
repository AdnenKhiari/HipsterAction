package com.mycompany.myapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ActeurMapperTest {

    private ActeurMapper acteurMapper;

    @BeforeEach
    public void setUp() {
        acteurMapper = new ActeurMapperImpl();
    }
}
