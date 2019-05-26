package com.example.domain.model.project;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

@Entity
public class Project implements Serializable {

    /**
     * id : 4138003
     * name : Harley Davidson Catalog & Custom 2012
     * published_on : 1338907230
     * created_on : 1338906135
     * modified_on : 1345798802
     * url : http://www.behance.net/gallery/Harley-Davidson-Catalog-Custom-2012/4138003
     * fields : ["Digital Art","Graphic Design","Illustration"]
     * covers : {"115":"http://behance.vo.llnwd.net/profiles3/129052/projects/4138003/115x5fbeddb9956459a3fb7300f78faf0f6e.jpg","202":"http://behance.vo.llnwd.net/profiles3/129052/projects/4138003/5fbeddb9956459a3fb7300f78faf0f6e.jpg"}
     * mature_content : 0
     * owners : {"129052":{"id":129052,"first_name":"Jeremy","last_name":"Packer","username":"zombieyeti","city":"Elkhart","state":"Indiana","country":"United States","company":"Zombie Yeti Studios","occupation":"","created_on":1256039356,"url":"http://www.behance.net/zombieyeti","display_name":"Jeremy Packer","images":{"32":"http://behance.vo.llnwd.net/profiles3/129052/32x67de83ba6f6acad093832861dea34a66.jpg","50":"http://behance.vo.llnwd.net/profiles3/129052/50x67de83ba6f6acad093832861dea34a66.jpg","78":"http://behance.vo.llnwd.net/profiles3/129052/78x67de83ba6f6acad093832861dea34a66.jpg","115":"http://behance.vo.llnwd.net/profiles3/129052/115x67de83ba6f6acad093832861dea34a66.jpg","129":"http://behance.vo.llnwd.net/profiles3/129052/129x67de83ba6f6acad093832861dea34a66.jpg","138":"http://behance.vo.llnwd.net/profiles3/129052/67de83ba6f6acad093832861dea34a66.jpg"},"fields":["Graphic Design","Illustration","Digital Art"]}}
     * stats : {"views":1510,"appreciations":179,"comments":21}
     */

    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    private int mId;

    @ColumnInfo(name = "name")
    @SerializedName("name")
    private String mName;

    @ColumnInfo(name = "published_on")
    @SerializedName("published_on")
    private int mPublishedOn;

    @ColumnInfo(name = "created_on")
    @SerializedName("created_on")
    private int mCreatedOn;

    @SerializedName("covers")
    @Ignore
    private Cover mCover;

    @SerializedName("owners")
    @Ignore
    private List<Owner> mOwners;

    @SerializedName("stats")
    @Ignore
    private Stats mStats;

    @SerializedName("fields")
    @Ignore
    private List<String> mFields;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getPublishedOn() {
        return mPublishedOn;
    }

    public void setPublishedOn(int publishedOn) {
        mPublishedOn = publishedOn;
    }

    public int getCreatedOn() {
        return mCreatedOn;
    }

    public void setCreatedOn(int createdOn) {
        mCreatedOn = createdOn;
    }

    public Cover getCover() {
        return mCover;
    }

    public void setCover(Cover cover) {
        mCover = cover;
    }

    public List<Owner> getOwners() {
        return mOwners;
    }

    public void setOwners(List<Owner> owners) {
        mOwners = owners;
    }

    public Stats getStats() {
        return mStats;
    }

    public void setStats(Stats stats) {
        mStats = stats;
    }

    public List<String> getFields() {
        return mFields;
    }

    public void setFields(List<String> fields) {
        mFields = fields;
    }
}