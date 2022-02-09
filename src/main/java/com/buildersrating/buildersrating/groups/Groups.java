package com.buildersrating.buildersrating.groups;

import javax.persistence.*;

@Entity
@Table(name = "tblGroups")
public class Groups {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupId;
    private String groupName;

    public Groups() {
    }

    public Groups(Long groupId, String groupName) {
        this.groupId = groupId;
        this.groupName = groupName;
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

    public void setGroup_name(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public String toString() {
        return "Groups{" +
                "groupId=" + groupId +
                ", groupName='" + groupName + '\'' +
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
