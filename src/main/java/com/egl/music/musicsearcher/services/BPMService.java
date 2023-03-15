package com.egl.music.musicsearcher.services;

import com.egl.music.musicsearcher.repository.BPMRepository;
import com.egl.music.musicsearcher.models.BPM;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BPMService {

    private final BPMRepository bpmRepository;

    public BPMService(BPMRepository bpmRepository){
        this.bpmRepository = bpmRepository;
    }

    public BPM createBPMPair(int beats, String songName, int id) {
        BPM newBPM = new BPM(beats, songName, id);
        return bpmRepository.saveAndFlush(newBPM);
    }

    public List<Integer> getSongsByBPM(int bpm) {
        return bpmRepository.findByBPM(bpm);
    }

    public List<Integer> getSongsWithMultipleBPM() {
        return bpmRepository.findByBPM(-1);
    }

    public List<Integer> getSongsWithMultipleBPM(int atLeastThisMany) {
        return bpmRepository.findByBPM(-atLeastThisMany);
    }

    public List<Integer> getBPMsForSong(int songId) {
        return bpmRepository.findBPMBySongId(songId);
    }

}
