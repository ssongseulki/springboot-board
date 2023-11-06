package ssong.boardspring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
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
    @DisplayName("게시글 생성되었는지 확인")
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
    @DisplayName("게시글 수정되었는지 확인")
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

    @Test
    @DisplayName("게시글 리스트 반환 확인")
    void boards() {
        Page<Board> result1 = boardService.findBoardList(1, 5);

        BoardDto boardDto = new BoardDto();
        boardDto.setBoardTitle("테스트 제목1");
        boardDto.setBoardContent("테스트 내용1");
        boardDto.setMemberId(1);
        boardService.createBoard(boardDto);

        BoardDto boardDto2 = new BoardDto();
        boardDto2.setBoardTitle("테스트 제목22");
        boardDto2.setBoardContent("테스트 내용22");
        boardDto2.setMemberId(1);
        boardService.createBoard(boardDto2);

        Page<Board> result2 = boardService.findBoardList(1, 5);

        assertThat(result2.getTotalElements()).isEqualTo(result1.getTotalElements() + 2);
    }

}
