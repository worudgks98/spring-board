package com.example.board.service;

import com.example.board.dto.BoardDTO;
import com.example.board.entity.BoardEntity;
import com.example.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public void save(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
        boardRepository.save(boardEntity);
    }

    public List<BoardDTO> findAll() {
        List<BoardEntity> boardEntityList = boardRepository.findAll();
        List<BoardDTO> boardDTOList = new ArrayList<>();
        for (BoardEntity boardEntity : boardEntityList) {
            boardDTOList.add(BoardDTO.toBoardDTO(boardEntity));
        }
        return boardDTOList;
    }

    public List<BoardDTO> search(String searchType,
                                 String keyword){

        List<BoardEntity> boardEntityList;

        if(searchType.equals("title")){
            boardEntityList =
                    boardRepository.findByBoardTitleContaining(keyword);

        }else if(searchType.equals("writer")){
            boardEntityList =
                    boardRepository.findByBoardWriterContaining(keyword);

        }else if(searchType.equals("contents")){
            boardEntityList =
                    boardRepository.findByBoardContentsContaining(keyword);

        }else{
            boardEntityList =
                    boardRepository
                            .findByBoardTitleContainingOrBoardContentsContaining(
                                    keyword, keyword);
        }

        List<BoardDTO> boardDTOList = new ArrayList<>();

        for(BoardEntity boardEntity : boardEntityList){
            boardDTOList.add(BoardDTO.toBoardDTO(boardEntity));
        }

        return boardDTOList;
    }

    @Transactional
    public void updateHits(Long id) {
        boardRepository.updateHits(id);

    }

    public BoardDTO findById(Long id) {
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);
        if(optionalBoardEntity.isPresent()) {
            BoardEntity boardEntity = optionalBoardEntity.get();
            return BoardDTO.toBoardDTO(boardEntity);
        } else {
            return null;
        }
    }

    public void delete(Long id) {
        boardRepository.deleteById(id);
    }

    @Transactional
    public void update(BoardDTO boardDTO) {

        BoardEntity boardEntity =
                boardRepository.findById(boardDTO.getId())
                        .orElseThrow(() ->
                                new IllegalArgumentException("해당 게시글을 찾을 수 없습니다."));

        boardEntity.update(boardDTO);
    }
}
