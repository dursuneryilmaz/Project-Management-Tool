package com.dursuneryilmaz.duscrumtool.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "epics")
public class Epic implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    @Size(min = 32, max = 32)
    private String epicId;
    @NotBlank(message = "Epic content can not be blank!")
    private String content;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "theme_id")
    private Theme theme;
    @JsonIgnore
    @OneToMany(mappedBy = "epic", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Story> storyList;

    public Epic() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEpicId() {
        return epicId;
    }

    public void setEpicId(String epicId) {
        this.epicId = epicId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public List<Story> getStoryList() {
        return storyList;
    }

    public void setStoryList(List<Story> storyList) {
        this.storyList = storyList;
    }
}
