package br.com.mxel.cuedot.data.model;

import com.google.gson.annotations.SerializedName;

public class MovieVideo {

    @SerializedName("id")
    public Integer _id;

    @SerializedName("iso_3166_1")
    public String _iso31661;

    @SerializedName("iso_639_1")
    public String _iso6391;

    @SerializedName("key")
    public String _key;

    @SerializedName("name")
    public String _name;

    @SerializedName("site")
    public String _site;

    @SerializedName("size")
    public Long _size;

    @SerializedName("type")
    public String _type;

    public Integer getId() {
        return _id;
    }

    public void setId(Integer id) {
        this._id = id;
    }

    public String getIso31661() {
        return _iso31661;
    }

    public void setIso31661(String iso31661) {
        this._iso31661 = iso31661;
    }

    public String getIso6391() {
        return _iso6391;
    }

    public void setIso6391(String iso6391) {
        this._iso6391 = iso6391;
    }

    public String getKey() {
        return _key;
    }

    public void setKey(String key) {
        this._key = key;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        this._name = name;
    }

    public String getSite() {
        return _site;
    }

    public void setSite(String site) {
        this._site = site;
    }

    public Long getSize() {
        return _size;
    }

    public void setSize(Long size) {
        this._size = size;
    }

    public String getType() {
        return _type;
    }

    public void setType(String type) {
        this._type = type;
    }
}
