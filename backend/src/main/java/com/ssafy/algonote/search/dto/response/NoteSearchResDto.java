package com.ssafy.algonote.search.dto.response;

import com.ssafy.algonote.note.domain.NoteDocument;
import lombok.Builder;

@Builder
public record NoteSearchResDto(
    String index,
    Long noteId,
    Long problemId,
    String noteTitle,
    String problemTitle,
    String memberNickname

) {

    public static NoteSearchResDto from(NoteDocument noteDocument) {
        return NoteSearchResDto.builder()
            .index("note")
            .noteId(Long.parseLong(noteDocument.getId()))
            .problemId(Long.parseLong(noteDocument.getProblemId()))
            .noteTitle(noteDocument.getNoteTitle())
            .problemTitle(noteDocument.getProblemTitle())
            .memberNickname(noteDocument.getMemberNickname())
            .build();
    }
}
