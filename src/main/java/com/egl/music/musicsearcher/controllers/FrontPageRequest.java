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

    @ApiModelProperty(name="songTitle",required = true)
    String songTitle;
    @ApiModelProperty(name="artist",required = true)
    String artist;

    @ApiModelProperty(name="beatsPer",required = true)
    String beatsPer;
    @ApiModelProperty(name="beatsPerMany",required = true)
    int beatsPerMany;

    @ApiModelProperty(name="musicKeys",required = true)
    String musicKeys;
    @ApiModelProperty(name="musicKeysMany",required = true)
    int musicKeysMany;

    @ApiModelProperty(name="timeSigns",required = true)
    String timeSigns;
    @ApiModelProperty(name="timeSignsMany",required = true)
    int timeSignsMany;


}
