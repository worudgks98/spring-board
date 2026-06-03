package com.example.board.service;

import com.example.board.dto.BoardDTO;
import com.example.board.entity.BoardEntity;
import com.example.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

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

    public Page<BoardDTO> paging(int page){

        Pageable pageable =
                PageRequest.of(page - 1, 5);

        Page<BoardEntity> boardEntities =
                boardRepository.findAll(pageable);

        return boardEntities.map(BoardDTO::toBoardDTO);
    }

    public Page<BoardDTO> search(String searchType,
                                 String keyword,
                                 Pageable pageable){

        Page<BoardEntity> boardEntities;

        if(searchType.equals("title")){
            boardEntities =
                    boardRepository.findByBoardTitleContaining(
                            keyword, pageable);

        }else if(searchType.equals("writer")){
            boardEntities =
                    boardRepository.findByBoardWriterContaining(
                            keyword, pageable);

        }else if(searchType.equals("contents")){
            boardEntities =
                    boardRepository.findByBoardContentsContaining(
                            keyword, pageable);

        }else{
            boardEntities =
                    boardRepository
                            .findByBoardTitleContainingOrBoardContentsContaining(
                                    keyword,
                                    keyword,
                                    pageable);
        }

        return boardEntities.map(BoardDTO::toBoardDTO);
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
