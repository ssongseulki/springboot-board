package ssong.boardspring.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ssong.boardspring.domain.Board;
import ssong.boardspring.dto.BoardDto;
import ssong.boardspring.repository.BoardRepository;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
public class BoardServiceTest {

    @Autowired
    BoardService boardService;
    @Autowired
    BoardRepository boardRepository;


    @Test
    void save() {
        BoardDto boardDto = new BoardDto();
        boardDto.setBoardTitle("제목???");
        boardDto.setBoardContent("내용???");
        boardDto.setMemberId(1);

        Long saveId = boardService.createBoard(boardDto);
        Long findBoardId = boardService.findOne(saveId).getId();
        assertThat(findBoardId).isNotNull();
    }

    @Test
    void update() {
        BoardDto boardDto = new BoardDto();
        boardDto.setId(1);
        boardDto.setBoardTitle("수정입니다.");
        boardDto.setBoardContent("수정하는 내용");

        Long saveId = boardService.updateBoard(boardDto);
        Board findBoard = boardService.findOne(saveId);
        assertThat(boardDto.getBoardTitle()).isEqualTo(findBoard.getTitle());
        assertThat(boardDto.getBoardContent()).isEqualTo(findBoard.getContent());
    }


}
