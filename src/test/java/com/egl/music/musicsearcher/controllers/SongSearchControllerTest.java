package com.egl.music.musicsearcher.controllers;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SongSearchControllerTest {

    SongSearchController songSearchController = new SongSearchController(null,null);

    @Test
    void convertToLists() {
        List<Integer> proveThis = Arrays.asList(2, 3, 4, 5);

        List<Integer> proveThat = songSearchController.convertStringToIntegerList("2, 3 , 4  ,5");

        assertThat(proveThis).isEqualTo(proveThat);
    }

}