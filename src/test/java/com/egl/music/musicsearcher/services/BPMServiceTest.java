package com.egl.music.musicsearcher.services;

import com.egl.music.musicsearcher.models.BPM;
import com.egl.music.musicsearcher.repository.BPMRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BPMServiceTest {

    @Autowired
    BPMService bpmService;

    @Autowired
    BPMRepository bpmRepository;

    @Test
    void addNewBPMEntry(){
        BPM expectedBPM = new BPM(137, "Hey love me", 1337);

        BPM returnedBPM = bpmService.createBPMPair(137, "Hey love me", 1337);

        assertThat(returnedBPM.getBeatsPerMinute()).isEqualTo(expectedBPM.getBeatsPerMinute());
        assertThat(returnedBPM.getSongName()).isEqualTo(expectedBPM.getSongName());
        assertThat(returnedBPM.getSongId()).isEqualTo(expectedBPM.getSongId());
        assertThat(returnedBPM.getIDgnorable()).isEqualTo(expectedBPM.getIDgnorable()+1);
    }
}