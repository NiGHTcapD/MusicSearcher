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


    //GetMapping("/getSongsByBPM")
    public List<Integer> getSongsByBPM(int bpm) {
        //log.warn("Searching BPM table for entries...");
        List<Integer> songIdList = bpmRepository.findByBPM(bpm);
        return songIdList;
    }


    public List<Integer> getSongsWithMultipleBPM() {
        //log.warn("Searching BPM table for entries...");
        List<Integer> songIdList = bpmRepository.findByBPM(-1);
        return songIdList;
    }



    //@GetMapping("/getSongsByBPM")
    public List<Integer> getSongsWithMultipleBPM(int atLeastThisMany) {
        //log.warn("Searching BPM table for entries...");
        List<Integer> songIdList = bpmRepository.findByBPM(-atLeastThisMany);
        return songIdList;
    }



    public List<Integer> getBPMsForSong(int songId) {
        List<Integer> songIdList = bpmRepository.findBPMBySongId(songId);
        return songIdList;
    }

}
