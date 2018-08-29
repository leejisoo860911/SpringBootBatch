package com.ljs.batch.biz.board;

import com.ljs.batch.domain.Board;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class BoardProcessor implements ItemProcessor<Board, Board> {

    @Override
    public Board process(Board board) throws Exception {
        board.setTitle("변경 000");
        return board;
    }

}
