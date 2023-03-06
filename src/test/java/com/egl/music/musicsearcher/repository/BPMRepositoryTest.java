package com.egl.music.musicsearcher.repository;

import com.egl.music.musicsearcher.models.BPM;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
class BPMRepositoryTest {

    @Autowired
    BPMRepository bpmRepository;

    @Test
    void addNewBPMEntry(){
        BPM newBPM = new BPM(137, "Hey love me", 1337);
        BPM beatsEntryActual =  bpmRepository.saveAndFlush(newBPM);
        assertThat(beatsEntryActual).isEqualTo(newBPM);
    }

    @Test
    void searchForBPMEntry(){
        bpmRepository.saveAndFlush(new BPM(125, "A", 1324));
        bpmRepository.saveAndFlush(new BPM(127, "Aaa", 1234));
        bpmRepository.saveAndFlush(new BPM(125, "Aaaaa", 11111));

        List<Integer> oneTwentyFives = bpmRepository.findByBPM(125);

        assertThat(oneTwentyFives).containsExactly(1324, 11111);
    }



}