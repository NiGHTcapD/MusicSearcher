package com.egl.music.musicsearcher.services;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class TimeSignatureServiceTest {
    @Autowired
    SongsService songsService;
    @Autowired
    TimeSignatureService timeSignatureService;

    @Test
    void getTimeSignaturesForSong() {
        songsService.createSong("Subdivisions","Rush", Arrays.asList(125),Arrays.asList("A"),Arrays.asList("3","4","5"));

        List<String> timeSigs = timeSignatureService.getTimeSignaturesForSong(1);

        Assertions.assertThat(timeSigs).containsExactly("3","4","5","-1","-3");
    }
}