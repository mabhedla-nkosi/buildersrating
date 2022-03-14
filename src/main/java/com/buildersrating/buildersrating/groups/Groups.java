package com.buildersrating.buildersrating.groups;

import javax.persistence.*;

@Entity
@Table(name = "tblGroups")
public class Groups {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupId;
    private String groupName;
    private int deleted;
    private int suspended;

    public Groups() {
    }

    public Groups(Long groupId, String groupName, int deleted, int suspended) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.deleted=deleted;
        this.suspended=suspended;
    }

    public Groups(String groupName, int deleted, int suspended) {
        this.groupName = groupName;
        this.deleted=deleted;
        this.suspended=suspended;
    }

    public Groups(String groupName) {
        this.groupName = groupName;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public int getSuspended() {
        return suspended;
    }

    public void setSuspended(int suspended) {
        this.suspended = suspended;
    }

    @Override
    public String toString() {
        return "Groups{" +
                "groupId=" + groupId +
                ", groupName='" + groupName + '\'' +
                ", deleted=" + deleted +
                ", suspended=" + suspended +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Groups groups = (Groups) o;
        return groupId.equals(groups.groupId) && groupName.equals(groups.groupName);
    }

}
