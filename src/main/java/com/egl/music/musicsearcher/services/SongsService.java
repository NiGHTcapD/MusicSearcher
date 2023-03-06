package com.egl.music.musicsearcher.services;

import com.egl.music.musicsearcher.models.Songs;
import com.egl.music.musicsearcher.models.TimeSignature;
import com.egl.music.musicsearcher.repository.SongsRepository;
import com.egl.music.musicsearcher.repository.TimeSignatureRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.egl.music.musicsearcher.models.Songs.latestHit;

@Service
public class SongsService {

    private final SongsRepository songsRepository;
    BPMService bpmService;
    MusicKeyService musicKeyService;
    TimeSignatureService timeSignatureService;

    public SongsService(SongsRepository songsRepository, BPMService bpmService, MusicKeyService musicKeyService, TimeSignatureService timeSignatureService){
        this.songsRepository=songsRepository;
        this.bpmService=bpmService;
        this.musicKeyService=musicKeyService;
        this.timeSignatureService=timeSignatureService;
    }


    @Transactional
    public void createSong(String songTitle, String Artist, List<Integer> beats, List<String> keys, List<String> sigs) {


        Songs newSong = new Songs(songTitle, Artist);
        Songs sung = songsRepository.saveAndFlush(newSong);

        //use a helper function to get the id ge
        //evil floating point bit level hacking (WTF?)
        int latest=latestHit(songTitle);

        for (int beat : beats)
        {
            bpmService.createBPMPair(beat, songTitle, latest);
        }
        //if more than one beat, add a -1 beat and a -2 if two beats, -3 if 3 beats, etc
        if (beats.size()>1){
            bpmService.createBPMPair(-1, songTitle, latest);
            bpmService.createBPMPair(0-beats.size(), songTitle, latest);
        }
        for (String key : keys)
        {
            musicKeyService.createMusicKeyPair(key, songTitle, latest);
        }
        //if more than one...
        if (beats.size()>1){
            musicKeyService.createMusicKeyPair(String.valueOf(-1), songTitle, latest);
            musicKeyService.createMusicKeyPair(String.valueOf(0-beats.size()), songTitle, latest);
        }
        for (String sig : sigs)
        {
            timeSignatureService.createTimeSignaturePair(sig, songTitle, latest);
        }
        //if more than one...
        if (beats.size()>1){
            timeSignatureService.createTimeSignaturePair(String.valueOf(-1), songTitle, latest);
            timeSignatureService.createTimeSignaturePair(String.valueOf(0-beats.size()), songTitle, latest);
        }
    }


    public List<Integer> getSongByName(String songName) {
        List<Integer> songIdList = songsRepository.findBySongTitle(songName);
        return songIdList;
    }


    public List<Integer> getSongByArtist(String artist) {
        List<java.lang.Integer> songIdList = songsRepository.findBySongArtist(artist);
        return songIdList;
    }


    public void returnSearchedSongs(List<Integer> songList) {

    }


    public List<Integer> ANDTwoLists(List<Integer> songList1, List<Integer> songList2) {

        //probably just AND the IDs, not the songs
        return null;
    }
}
