package com.example.board.service;

import com.example.board.dto.CommentDTO;
import com.example.board.entity.BoardEntity;
import com.example.board.entity.CommentEntity;
import com.example.board.repository.BoardRepository;
import com.example.board.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    public void save(CommentDTO commentDTO){

        BoardEntity boardEntity = boardRepository.findById(commentDTO.getBoardId()).get();

        CommentEntity commentEntity = new CommentEntity();

        commentEntity.setCommentWriter(commentDTO.getCommentWriter());

        commentEntity.setCommentContents(commentDTO.getCommentContents());

        commentEntity.setBoardEntity(boardEntity);

        commentRepository.save(commentEntity);
    }

    public List<CommentDTO> findAll(Long boardId){

        BoardEntity boardEntity =
                boardRepository.findById(boardId).get();

        List<CommentEntity> commentEntityList =
                commentRepository
                        .findAllByBoardEntityOrderByIdDesc(boardEntity);

        List<CommentDTO> commentDTOList = new ArrayList<>();

        for(CommentEntity commentEntity : commentEntityList){
            commentDTOList.add(CommentDTO.toCommentDTO(commentEntity));
        }

        return commentDTOList;
    }
}
