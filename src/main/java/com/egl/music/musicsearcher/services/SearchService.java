package com.egl.music.musicsearcher.services;

import com.egl.music.musicsearcher.controllers.FrontPageRequest;
import com.egl.music.musicsearcher.models.SongsReturnable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {

    SongsService songsService;
    BPMService bpmService;
    MusicKeyService musicKeyService;
    TimeSignatureService timeSignatureService;

    @Autowired
    public SearchService(SongsService songsService, BPMService bpmService, MusicKeyService musicKeyService, TimeSignatureService timeSignatureService) {
        this.songsService = songsService;
        this.bpmService = bpmService;
        this.musicKeyService = musicKeyService;
        this.timeSignatureService = timeSignatureService;
    }

    public List<SongsReturnable> getSongsReturnables(FrontPageRequest requestData) {
        List<List<Integer>> identifiers = new ArrayList<>();


        identifiers.add(songsService.getSongByName(requestData.getSongTitle()));
        identifiers.add(songsService.getSongByArtist(requestData.getArtist()));

        searchBPM(requestData, identifiers);

        searchMusicKeys(requestData, identifiers);

        searchTimeSignatures(requestData, identifiers);

        //Consider adding logic to find multiple of an aspect, and then AND it separately.

        //Add logic to detect any NULL lists, and add them to a list of aspects that returned no results, so they can be ignored

        processLists(identifiers);

        if (identifiers.size()>0) {
            return songsService.returnSearchedSongs(identifiers.get(0));
        }
        else {
            return Collections.emptyList();
        }
    }

    private void searchBPM(FrontPageRequest requestData, List<List<Integer>> identifiers) {
        //if beats-per-many is not 1 or 0, send it in. Otherwise, go right ahead and search.
        if (requestData.getBeatsPerMany() > 1) {
            identifiers.add(bpmService.getSongsWithMultipleBPM(requestData.getBeatsPerMany()));
        } else {
            if (!"".equals(requestData.getBeatsPer())) {
                identifiers.add(bpmService.getSongsByBPM(Integer.parseInt(requestData.getBeatsPer())));
            } else {
                identifiers.add(List.of());
            }
        }
    }

    private void searchMusicKeys(FrontPageRequest requestData, List<List<Integer>> identifiers) {
        //if music-keys-many is not 1 or 0, send it in. Otherwise, go right ahead and search.
        if (requestData.getMusicKeysMany() > 1) {
            identifiers.add(musicKeyService.getSongsWithMultipleMusicKeys(requestData.getMusicKeysMany()));
        } else {
            identifiers.add(musicKeyService.getSongsByMusicKeys(requestData.getMusicKeys()));
        }
    }

    private void searchTimeSignatures(FrontPageRequest requestData, List<List<Integer>> identifiers) {
        //if time-signs-many is not 1 or 0, send it in. Otherwise, go right ahead and search.
        if (requestData.getTimeSignsMany() > 1) {
            identifiers.add(timeSignatureService.getSongsWithMultipleTimeSignatures(requestData.getTimeSignsMany()));
        } else {
            identifiers.add(timeSignatureService.getSongsByTimeSignatures(requestData.getTimeSigns()));
        }
    }

    private void processLists(List<List<Integer>> identifiers) {
        for (int index = identifiers.size() - 1; index >= 0; index--) {
            if (identifiers.get(index).size() == 0) {
                //Nothing found for that aspect.
                //Excise element.
                identifiers.remove(index);
            }
        }
        //AND the other lists together
        //Loop ANDTwoLists(identifiers.get(0), identifiers.get(1))
        // and excising element 1
        // until identifiers is 1 long.
        while (identifiers.size() > 1) {
            identifiers.set(0, ANDTwoLists(identifiers.get(0), identifiers.get(1)));
            identifiers.remove(1);
            if (identifiers.get(0).size() == 0) {
                break;
            }
        }
    }

    protected List<Integer> ANDTwoLists(List<Integer> baseList, List<Integer> otherList) {
        if (baseList.size() == 0) {
            return otherList;
        }
        if (otherList.size() == 0) {
            return baseList;
        }
        return baseList.stream()
                .distinct()
                .filter(otherList::contains)
                .collect(Collectors.toList());
    }

}
