package com.ljs.batch.biz.board;

import com.ljs.batch.domain.Board;
import com.ljs.batch.mapper.BoardMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class BoardWriter implements ItemWriter<Board> {

    @Autowired
    private BoardMapper mapper;

    @Override
    public void write(List<? extends Board> list) throws Exception {
        log.info("Writer Size : " + list.size());
        mapper.updateBoards(list);
    }
}
