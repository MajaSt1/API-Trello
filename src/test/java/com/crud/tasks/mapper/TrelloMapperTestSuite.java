package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloMapperTestSuite {

    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    public void mapToBoardsTest(){
        //
        List<TrelloBoardDto> trelloBoardDtos= new ArrayList<>();
        trelloBoardDtos.add(new TrelloBoardDto("boardDto1","1",new ArrayList<>()));
        trelloBoardDtos.add(new TrelloBoardDto("boardDto2","2",new ArrayList<>()));
        //
        List<TrelloBoard> testMapper=trelloMapper.mapToBoards(trelloBoardDtos);
        //
        assertEquals(2,testMapper.size());
    }
    @Test
    public void mapToBoardsDto(){
        //
        List<TrelloBoard> trelloBoards= new ArrayList<>();
        trelloBoards.add(new TrelloBoard("1","board1",new ArrayList<>()));
        trelloBoards.add(new TrelloBoard("2","board2",new ArrayList<>()));
        //
        List<TrelloBoardDto> testMapper= trelloMapper.mapToBoardsDto(trelloBoards);
        //
        assertEquals(2,testMapper.size());

    }
    @Test
    public void  mapToList(){
        //
        List<TrelloListDto> trelloListDtos= new ArrayList<>();
        trelloListDtos.add(new TrelloListDto("listDto1","1",false));
        //
        List<TrelloList> trelloLists= trelloMapper.mapToList(trelloListDtos);
        //
        assertEquals(1,trelloLists.size());
        System.out.println(trelloLists);
    }
    @Test
    public void mapToListDto(){
        //
        List<TrelloList> trelloLists= new ArrayList<>();
        trelloLists.add(new TrelloList("1","list1",true));
        //
        List<TrelloListDto> trelloListDtos= trelloMapper.mapToListDto(trelloLists);
        //
        assertEquals(1,trelloListDtos.size());
    }
    @Test
    public void mapToCardDto(){

        TrelloCardDto trelloCardDto= trelloMapper.mapToCardDto(new TrelloCard("Card1","card","card","1"));
        //
        assertEquals("Card1",trelloCardDto.getName());
    }
    @Test
    public void mapToCard(){

        TrelloCard trelloCard= trelloMapper.mapToCard(new TrelloCardDto("CardDto1","CardDto","cardDto","1"));
        //
        assertEquals("CardDto",trelloCard.getDescription());

    }
}
