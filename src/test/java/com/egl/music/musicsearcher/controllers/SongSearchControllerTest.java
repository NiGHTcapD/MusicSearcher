package com.egl.music.musicsearcher.controllers;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SongSearchControllerTest {

    SongSearchController songSearchController = new SongSearchController(null,null,null,null);

    @Test
    void NuttyCastingChainIdiotProofing(){
        List<Integer> proveThis = Arrays.asList(2, 3, 4, 5);

        String readThis = "2, 3 , 4  ,5";
        List<Integer> proveThat = Arrays.stream(readThis.split("\\s*,\\s*"))
                .mapToInt(Integer::valueOf).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

        assertThat(proveThis).isEqualTo(proveThat);
    }



    @Test
    void convertToLists() {
        List<Integer> proveThis = Arrays.asList(2, 3, 4, 5);

        List<Integer> proveThat = songSearchController.convertStringToIntegerList("2, 3 , 4  ,5");

        assertThat(proveThis).isEqualTo(proveThat);
    }
}