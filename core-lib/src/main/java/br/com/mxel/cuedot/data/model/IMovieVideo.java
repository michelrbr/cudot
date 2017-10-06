package br.com.mxel.cuedot.data.model;

/**
 * Created by michelribeiro on 05/10/17.
 */

public interface IMovieVideo {
    String getId();

    void setId(String id);

    String getIso31661();

    void setIso31661(String iso31661);

    String getIso6391();

    void setIso6391(String iso6391);

    String getKey();

    void setKey(String key);

    String getName();

    void setName(String name);

    String getSite();

    void setSite(String site);

    Long getSize();

    void setSize(Long size);

    String getType();

    void setType(String type);
}
