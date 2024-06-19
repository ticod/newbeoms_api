package com.ticod.newbeoms_api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "tag", schema = "newbeoms_db")
public class Tag {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "content", nullable = false, length = 50)
    private String content;

    @Builder
    public Tag(String content) {
        this.content = content;
    }
}