package com.egl.music.musicsearcher.services;

import com.egl.music.musicsearcher.models.Songs;
import com.egl.music.musicsearcher.models.SongsReturnable;
import com.egl.music.musicsearcher.models.TimeSignature;
import com.egl.music.musicsearcher.repository.SongsRepository;
import com.egl.music.musicsearcher.repository.TimeSignatureRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.egl.music.musicsearcher.models.Songs.latestHit;

@Service
public class SongsService {

    private final SongsRepository songsRepository;
    BPMService bpmService;
    MusicKeyService musicKeyService;
    TimeSignatureService timeSignatureService;

    public SongsService(SongsRepository songsRepository, BPMService bpmService, MusicKeyService musicKeyService, TimeSignatureService timeSignatureService) {
        this.songsRepository = songsRepository;
        this.bpmService = bpmService;
        this.musicKeyService = musicKeyService;
        this.timeSignatureService = timeSignatureService;
    }


    @Transactional
    public void createSong(String songTitle, String Artist, List<Integer> beats, List<String> keys, List<String> sigs) {

        Songs newSong = new Songs(songTitle, Artist);
        songsRepository.saveAndFlush(newSong);

        int latest = songsRepository.LatestHit();

        for (int beat : beats) {
            bpmService.createBPMPair(beat, songTitle, latest);
        }
        //if more than one beat, add a -1 beat and a -2 if two beats, -3 if 3 beats, etc
        if (beats.size() > 1) {
            bpmService.createBPMPair(-1, songTitle, latest);
            bpmService.createBPMPair(-beats.size(), songTitle, latest);
        }

        for (String key : keys) {
            musicKeyService.createMusicKeyPair(key, songTitle, latest);
        }
        //if more than one...
        if (keys.size() > 1) {
            musicKeyService.createMusicKeyPair(String.valueOf(-1), songTitle, latest);
            musicKeyService.createMusicKeyPair(String.valueOf(-keys.size()), songTitle, latest);
        }

        for (String sig : sigs) {
            timeSignatureService.createTimeSignaturePair(sig, songTitle, latest);
        }
        //if more than one...
        if (sigs.size() > 1) {
            timeSignatureService.createTimeSignaturePair(String.valueOf(-1), songTitle, latest);
            timeSignatureService.createTimeSignaturePair(String.valueOf(-sigs.size()), songTitle, latest);
        }
    }

    public List<Integer> getSongByName(String songName) {
        return songsRepository.findBySongTitle(songName);
    }


    public List<Integer> getSongByArtist(String artist) {
        return songsRepository.findBySongArtist(artist);
    }


    public List<SongsReturnable> returnSearchedSongs(List<Integer> songList) {
        List<SongsReturnable> returnables = new ArrayList<>();

        for (int song : songList) {
            SongsReturnable newEntry = new SongsReturnable(songsRepository.findTitleBySongId(song),
                    songsRepository.findArtistBySongId(song), stringifyList(bpmService.getBPMsForSong(song)),
                    stringifyList(musicKeyService.getMusicKeysForSong(song)), stringifyList(timeSignatureService.getTimeSignaturesForSong(song)));

            returnables.add(newEntry);
        }

        return returnables;

    }

    public String stringifyList(List<?> input) {

        return input.stream()
                .map(String::valueOf)
                .filter(part -> part.charAt(0) != '-')//filter out negatives, or those that start with a minus
                .collect(Collectors.joining(", "));
    }
}
