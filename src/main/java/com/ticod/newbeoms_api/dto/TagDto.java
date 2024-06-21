package com.ticod.newbeoms_api.dto;

import com.ticod.newbeoms_api.entity.Tag;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TagDto {

    private String content;

    public TagDto(String content) {
        this.content = content;
    }

    public static TagDto from(Tag tag) {
        return new TagDto(tag.getContent());
    }

    public static Tag toEntity(TagDto tagDto) {
        return Tag.builder()
                .content(tagDto.getContent())
                .build();
    }

}
