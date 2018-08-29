package com.ljs.batch.mapper;

import com.ljs.batch.domain.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<Board> selectBoards();
    void insertBoard(Board board);
    void insertBoards(List<? extends Board> list);
    void updateBoards(List<? extends Board> list);
    int deleteBoard(Long bno);
}
