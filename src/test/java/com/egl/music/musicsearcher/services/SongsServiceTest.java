package com.egl.music.musicsearcher.services;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SongsServiceTest {


    @Test
    void stringifyListTest() {
        List<String> subdivisionSignatures = Arrays.asList("3/4", "4/4", "7/4");

        String returnedString = new SongsService(null,null,null,null)
                .stringifyList(subdivisionSignatures);

        assertThat(returnedString).isEqualTo("3/4, 4/4, 7/4");
    }
}