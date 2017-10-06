package br.com.mxel.cuedot.data.remote.model;

import com.google.gson.annotations.SerializedName;

import br.com.mxel.cuedot.data.model.IMovieVideo;

public class MovieVideo implements IMovieVideo {

    @SerializedName("id")
    public String _id;

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

    @Override
    public String getId() {
        return _id;
    }

    @Override
    public void setId(String id) {
        this._id = id;
    }

    @Override
    public String getIso31661() {
        return _iso31661;
    }

    @Override
    public void setIso31661(String iso31661) {
        this._iso31661 = iso31661;
    }

    @Override
    public String getIso6391() {
        return _iso6391;
    }

    @Override
    public void setIso6391(String iso6391) {
        this._iso6391 = iso6391;
    }

    @Override
    public String getKey() {
        return _key;
    }

    @Override
    public void setKey(String key) {
        this._key = key;
    }

    @Override
    public String getName() {
        return _name;
    }

    @Override
    public void setName(String name) {
        this._name = name;
    }

    @Override
    public String getSite() {
        return _site;
    }

    @Override
    public void setSite(String site) {
        this._site = site;
    }

    @Override
    public Long getSize() {
        return _size;
    }

    @Override
    public void setSize(Long size) {
        this._size = size;
    }

    @Override
    public String getType() {
        return _type;
    }

    @Override
    public void setType(String type) {
        this._type = type;
    }
}
