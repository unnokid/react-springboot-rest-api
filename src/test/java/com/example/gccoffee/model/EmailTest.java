package com.example.gccoffee.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailTest {


    @Test
    @DisplayName("올바르지 않은 이메일 입력시 테스트")
    void testInvalidEmail() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    var email = new Email("accccc");
                });
    }

    @Test
    @DisplayName("올바른 이메일 입력 테스트")
    void testValidEmail() {
        var email = new Email("kim@gmail.com");
        assertTrue(email.getAddress().equals("kim@gmail.com"));
    }

    @Test
    @DisplayName("같은 이메일이면 동일한지 테스트")
    void testEqualEmail() {
        var email = new Email("kim@gmail.com");
        var email2 = new Email("kim@gmail.com");
        assertEquals(email,email2);
    }
}