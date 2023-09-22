package ssong.boardspring.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import ssong.boardspring.domain.Board;
import ssong.boardspring.dto.BoardCreateDto;
import ssong.boardspring.dto.BoardUpdateDto;
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
        BoardCreateDto boardDto = new BoardCreateDto();
        boardDto.setBoardTitle("제목???");
        boardDto.setBoardContent("내용???");
        boardDto.setMemberId(35);

        Long saveId = boardService.createBoard(boardDto);
        Long findBoardId = boardService.findOne(saveId).getId();
        assertThat(findBoardId).isNotNull();
    }

    @Test
    void update() {
        BoardUpdateDto boardUpdateDto = new BoardUpdateDto();
        boardUpdateDto.setId(33);
        boardUpdateDto.setBoardTitle("히히히");
        boardUpdateDto.setBoardContent("죠아떠");

        Long saveId = boardService.updateBoard(boardUpdateDto);
        Board findBoard = boardService.findOne(saveId);
        assertThat(boardUpdateDto.getBoardTitle()).isEqualTo(findBoard.getTitle());
        assertThat(boardUpdateDto.getBoardContent()).isEqualTo(findBoard.getContent());
    }


}
