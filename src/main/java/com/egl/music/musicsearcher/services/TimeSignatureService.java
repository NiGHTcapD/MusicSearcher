package com.egl.music.musicsearcher.services;

import com.egl.music.musicsearcher.repository.TimeSignatureRepository;
import com.egl.music.musicsearcher.models.TimeSignature;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeSignatureService{

    private final TimeSignatureRepository timeSignatureRepository;

    public TimeSignatureService(TimeSignatureRepository timeSignatureRepository){
        this.timeSignatureRepository = timeSignatureRepository;
    }

    public TimeSignature createTimeSignaturePair(String sig, String songName, int id) {
        TimeSignature newTimeSignature = new TimeSignature(sig, songName, id);
        return timeSignatureRepository.saveAndFlush(newTimeSignature);
    }


    //GetMapping("/getSongsByBPM")
    public List<Integer> getSongsByTimeSignatures(String sig) {
        //log.warn("Searching BPM table for entries...");
        return timeSignatureRepository.findByTimeSignature(sig);
    }

    public List<Integer> getSongsWithMultipleTimeSignatures() {
        //log.warn("Searching BPM table for entries...");
        return timeSignatureRepository.findByTimeSignature("-1");
    }

    public List<Integer> getSongsWithMultipleTimeSignatures(int atLeastThisMany) {
        //log.warn("Searching BPM table for entries...");
        return timeSignatureRepository.findByTimeSignature(String.valueOf(-atLeastThisMany));
    }

    public List<String> getTimeSignaturesForSong(int songId) {
        return timeSignatureRepository.findTimeSignatureBySongId(songId);
    }
}
