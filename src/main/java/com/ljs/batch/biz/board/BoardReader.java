package com.ljs.batch.biz.board;

import com.ljs.batch.domain.Board;
import com.ljs.batch.mapper.BoardMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Component
public class BoardReader implements ItemReader<Board> {

    private int nextIdx = 0;

    private List<Board> boardList;

    @Autowired
    private BoardMapper mapper;


    @PostConstruct
    private void init() {
        boardList = mapper.selectBoards();
    }

    @Override
    public Board read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        Board board = null;

        if (nextIdx < boardList.size()) {
            synchronized (this) {
                board = boardList.get(nextIdx++);
            }
            log.info("ItemReader read " + board.getBno());
        }

        return board;
    }


}
