package com.egl.music.musicsearcher.services;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SearchServiceTest {

    SearchService searchService = new SearchService(null,null,null,null);

    @Test
    void ANDTwoLists(){

        List<Integer> listOne = Arrays.asList(2, 3, 4, 5);
        List<Integer> listTwo = Arrays.asList(3, 4, 5, 6);
        List<Integer> listEmpty = List.of();

        List<Integer> testBothFull = searchService.ANDTwoLists(listOne, listTwo);
        List<Integer> testOneEmpty = searchService.ANDTwoLists(listEmpty, listTwo);
        List<Integer> testTwoEmpty = searchService.ANDTwoLists(listOne, listEmpty);

        List<Integer> testProof = Arrays.asList(3, 4, 5);

        assertThat(testProof).isEqualTo(testBothFull);
        assertThat(listTwo).isEqualTo(testOneEmpty);
        assertThat(listOne).isEqualTo(testTwoEmpty);

    }
}