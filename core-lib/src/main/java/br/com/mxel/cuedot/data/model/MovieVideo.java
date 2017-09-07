package br.com.mxel.cuedot.data.model;

import com.google.gson.annotations.SerializedName;

public class MovieVideo {

    public String id;

    @SerializedName("iso_3166_1")
    public String iso31661;

    @SerializedName("iso_639_1")
    public String iso6391;

    public String key;

    public String name;

    public String site;

    public Long size;

    public String type;
}
