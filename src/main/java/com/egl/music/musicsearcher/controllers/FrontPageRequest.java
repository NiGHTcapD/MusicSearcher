package com.egl.music.musicsearcher.controllers;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="FrontPageRequest", description="It comes from the front page in many (MANY) boxes. The logic to handle all this can be found in SongSearchController, so don't worry about the vast quantities.")
public class FrontPageRequest {

    @ApiModelProperty(name="song-title",required = true)
    String songTitle;
    @ApiModelProperty(name="artist",required = true)
    String artist;

    @ApiModelProperty(name="beats-per",required = true)
    String beatsPer;
    @ApiModelProperty(name="beats-per-many",required = true)
    int beatsPerMany;

    @ApiModelProperty(name="music-keys",required = true)
    String musicKeys;
    @ApiModelProperty(name="music-keys-many",required = true)
    int musicKeysMany;

    @ApiModelProperty(name="time-signs",required = true)
    String timeSigns;
    @ApiModelProperty(name="time-signs-many",required = true)
    int timeSignsMany;


}
